package com.org.lucidfusion

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSpecifier
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.org.lucidfusion.databinding.ActivityMainBinding
import com.org.lucidfusion.dbhelper.DBAdapter
import com.org.lucidfusion.dbhelper.DBQueries
import com.org.lucidfusion.dtos.MachineModel
import com.org.lucidfusion.dtos.PipeGrade
import com.org.lucidfusion.dtos.PipeSize
import com.org.lucidfusion.dtos.PressureSize
import com.org.lucidfusion.fragment.SplashFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject lateinit var injectedDbQueries: DBQueries

    @JvmField var dbQueries: DBQueries? = null

    @JvmField var boundNetwork: Network? = null
    fun getBoundNetwork(): Network? = ApplicationClass.boundNetwork ?: boundNetwork
    fun setBoundNetwork(network: Network?) {
        this.boundNetwork = network
        ApplicationClass.boundNetwork = network
    }

    private var wifiNetworkCallback: ConnectivityManager.NetworkCallback? = null
    private var legacyWifiReceiver: BroadcastReceiver? = null

    fun isMachineWifiConnected(): Boolean {
        return getBoundNetwork() != null
    }

    fun connectMachineWifi(
        ssid: String,
        passphrase: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        disconnectMachineWifi()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val specifier = WifiNetworkSpecifier.Builder()
                .setSsid(ssid)
                .setWpa2Passphrase(passphrase)
                .build()

            val request = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .setNetworkSpecifier(specifier)
                .build()

            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

            wifiNetworkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    cm?.bindProcessToNetwork(network)
                    setBoundNetwork(network)
                    runOnUiThread {
                        onSuccess()
                    }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    setBoundNetwork(null)
                    runOnUiThread {
                        onFailure()
                    }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    cm?.bindProcessToNetwork(null)
                    setBoundNetwork(null)
                }
            }
            cm?.requestNetwork(request, wifiNetworkCallback!!)
        } else {
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
            wifiManager?.isWifiEnabled = true

            val conf = WifiConfiguration().apply {
                SSID = "\"$ssid\""
                preSharedKey = "\"$passphrase\""
            }
            wifiManager?.addNetwork(conf)

            val list = wifiManager?.configuredNetworks
            var found = false
            if (list != null) {
                for (i in list) {
                    if (i.SSID != null && i.SSID == "\"$ssid\"") {
                        val netId = i.networkId
                        wifiManager.enableNetwork(netId, true)
                        wifiManager.reconnect()
                        found = true
                        break
                    }
                }
            }

            legacyWifiReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                    val activeNetInfo = cm?.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    if (activeNetInfo?.isConnected == true) {
                        val activeNet = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) cm.activeNetwork else null
                        setBoundNetwork(activeNet)
                        onSuccess()
                    }
                }
            }
            val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
            registerReceiver(legacyWifiReceiver, filter)

            if (!found) {
                onFailure()
            }
        }
    }

    fun disconnectMachineWifi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            try {
                cm?.bindProcessToNetwork(null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            if (wifiNetworkCallback != null) {
                try {
                    cm?.unregisterNetworkCallback(wifiNetworkCallback!!)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                wifiNetworkCallback = null
            }
        } else {
            val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
            wifiManager?.disconnect()
            if (legacyWifiReceiver != null) {
                try {
                    unregisterReceiver(legacyWifiReceiver)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                legacyWifiReceiver = null
            }
        }
        setBoundNetwork(null)
    }

    private var machineModels = ArrayList<MachineModel>()
    private var pipeSizes = ArrayList<PipeSize>()
    private var pipeGrades = ArrayList<PipeGrade>()
    private var pressureSizes = ArrayList<PressureSize>()

    val iv_share: ImageView get() = binding.lTitle.ivShare

    var isHindiSupported = false
        private set

    private var imm: InputMethodManager? = null

    fun finishActivity() {
        finishAffinity()
    }

    fun closeKeyboard(view: View) {
        imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEdgeToEdgeInsets()
        setupPredictiveBackNavigation()

        ApplicationClass.dbAdapter = DBAdapter.getInstance(this)
        ApplicationClass.dbQueries = injectedDbQueries
        dbQueries = injectedDbQueries

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        initApp()
        askRunTimePermission()
    }

    private fun setupPredictiveBackNavigation() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    finish()
                }
            }
        })
    }

    private fun setupEdgeToEdgeInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainRoot) { _, windowInsets ->
            val systemBars = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )

            binding.mainRoot.updatePadding(
                left = systemBars.left,
                right = systemBars.right,
                bottom = systemBars.bottom
            )

            if (binding.lTitle.root.visibility != View.GONE) {
                binding.lTitle.root.updatePadding(top = systemBars.top)
                binding.container.updatePadding(top = 0)
            } else {
                binding.lTitle.root.updatePadding(top = 0)
                binding.container.updatePadding(top = systemBars.top)
            }

            windowInsets
        }
    }

    private fun checkPermission(): Boolean {
        val locResult = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION)
        var granted = (locResult == PackageManager.PERMISSION_GRANTED)
        if (Build.VERSION.SDK_INT >= 33) {
            val wifiResult = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.NEARBY_WIFI_DEVICES)
            granted = granted && (wifiResult == PackageManager.PERMISSION_GRANTED)
        }
        return granted
    }

    private fun initApp() {
        replaceFragment(SplashFragment(), false, "splash", false)

        AppConstants.KEYS["FN"] = AppConstants.KEY_FN
        AppConstants.KEYS["a"] = AppConstants.KEY_MACHINE_ID
        AppConstants.KEYS["b"] = AppConstants.KEY_LOGIC_WELDER
        AppConstants.KEYS["c"] = AppConstants.KEY_WELDNO
        AppConstants.KEYS["d"] = AppConstants.KEY_DATE
        AppConstants.KEYS["e"] = AppConstants.KEY_MONTH
        AppConstants.KEYS["f"] = AppConstants.KEY_YEAR
        AppConstants.KEYS["g"] = AppConstants.KEY_HOUR
        AppConstants.KEYS["h"] = AppConstants.KEY_MIN
        AppConstants.KEYS["i"] = AppConstants.KEY_SEC
        AppConstants.KEYS["j"] = AppConstants.KEY_PIPE_DIA
        AppConstants.KEYS["k"] = AppConstants.KEY_MATERIAL_GRADE
        AppConstants.KEYS["l"] = AppConstants.KEY_PIPE_PRESSURE_RATING
        AppConstants.KEYS["m"] = AppConstants.KEY_WALL_THICKNESS
        AppConstants.KEYS["n"] = AppConstants.KEY_SDR
        AppConstants.KEYS["o"] = AppConstants.KEY_PIPE_LENGTH
        AppConstants.KEYS["p"] = AppConstants.KEY_SET_DRAG_PRESS
        AppConstants.KEYS["q"] = AppConstants.KEY_ACHIVED_DRAG_PRESS
        AppConstants.KEYS["r"] = AppConstants.KEY_SET_JOINT_PRESS
        AppConstants.KEYS["s"] = AppConstants.KEY_SET_PRESS_AT_JOINT
        AppConstants.KEYS["t"] = AppConstants.KEY_ACHI_PRESS_JOINT
        AppConstants.KEYS["u"] = AppConstants.KEY_HOT_PLATE_TEMP
        AppConstants.KEYS["v"] = AppConstants.KEY_BEAD_UP_TIME
        AppConstants.KEYS["w"] = AppConstants.KEY_SOAKING_TIME
        AppConstants.KEYS["x"] = AppConstants.KEY_CHANGEOVER_TIME
        AppConstants.KEYS["y"] = AppConstants.KEY_COOLING_TIME

        AppConstants.MATERIAL_GRADE.put(1, "PE 63")
        AppConstants.MATERIAL_GRADE.put(2, "PE 80")
        AppConstants.MATERIAL_GRADE.put(3, "PE 100")

        AppConstants.LOGIC_WELDER.put(0, "160")
        AppConstants.LOGIC_WELDER.put(1, "250")
        AppConstants.LOGIC_WELDER.put(2, "315")
        AppConstants.LOGIC_WELDER.put(3, "450")
        AppConstants.LOGIC_WELDER.put(4, "630")
        AppConstants.LOGIC_WELDER.put(5, "800")
        AppConstants.LOGIC_WELDER.put(6, "1000")

        AppConstants.PIPE_PRESSURE_RATING.put(1, "PN 2.5")
        AppConstants.PIPE_PRESSURE_RATING.put(2, "PN 4")
        AppConstants.PIPE_PRESSURE_RATING.put(3, "PN 6")
        AppConstants.PIPE_PRESSURE_RATING.put(4, "PN 8")
        AppConstants.PIPE_PRESSURE_RATING.put(5, "PN 10")
        AppConstants.PIPE_PRESSURE_RATING.put(6, "PN 12.5")
        AppConstants.PIPE_PRESSURE_RATING.put(7, "PN 16")
        AppConstants.PIPE_PRESSURE_RATING.put(8, "PN 20")

        AppConstants.PIPE_LENGTH.put(1, "N/A")
        AppConstants.PIPE_LENGTH.put(2, "5 Mtr.")
        AppConstants.PIPE_LENGTH.put(3, "6 Mtr.")

        loadDBDataAsync("4984")

        binding.lTitle.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val locales = Locale.getAvailableLocales()
        for (locale in locales) {
            if (locale.displayName.lowercase(Locale.getDefault()).contains("hindi")) {
                isHindiSupported = true
                break
            }
        }
    }

    private fun loadDBDataAsync(standard: String) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                getDBData(standard)
            }
        }
    }

    fun showHideTitleBar(visibility: Int) {
        binding.lTitle.root.visibility = visibility
        ViewCompat.requestApplyInsets(binding.mainRoot)
    }

    fun showHideBottomBar(visibility: Int) {}

    fun setShareButtonVisibility(visibility: Int) {
        binding.lTitle.ivShare.visibility = visibility
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        binding.lTitle.tvTitle.text = title ?: ""
    }

    fun setBackButtonVisibility(visibility: Int) {
        binding.lTitle.ivBack.visibility = visibility
    }

    fun replaceFragment(
        fragment: Fragment,
        shouldAnimate: Boolean,
        tag: String?,
        shouldAddToBackStack: Boolean
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        if (!shouldAnimate) {
            transaction.replace(R.id.container, fragment)
            if (shouldAddToBackStack) transaction.addToBackStack(tag)
            transaction.commitAllowingStateLoss()
        } else {
            try {
                supportFragmentManager.popBackStack("splash", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            transaction.setCustomAnimations(R.anim.start1, R.anim.start2, R.anim.finish1, R.anim.finish2)
                .replace(R.id.container, fragment)
            if (shouldAddToBackStack) transaction.addToBackStack(tag)
            transaction.commitAllowingStateLoss()
        }
    }

    fun getMachineModels(): ArrayList<MachineModel> = machineModels

    fun getPipeSizes(machineId: Int, standard: String): ArrayList<PipeSize> {
        val queries = injectedDbQueries
        pipeSizes = queries.getPipeSizeForMachine(machineId, standard)
        val placeholder = PipeSize(pipe_size_name = "Select Pipe Size")
        pipeSizes.add(0, placeholder)
        return pipeSizes
    }

    fun resetPipeSizes(): ArrayList<PipeSize> {
        pipeSizes = ArrayList()
        val placeholder = PipeSize(pipe_size_name = "Select Pipe Size")
        pipeSizes.add(0, placeholder)
        return pipeSizes
    }

    fun getPipeGrades(): ArrayList<PipeGrade> = pipeGrades
    fun getPressureSizes(): ArrayList<PressureSize> = pressureSizes

    fun checkAppStatus(): Boolean {
        try {
            val currentDate = Date()
            val expiryDate = "30-08-2015"
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            val currCal = Calendar.getInstance().apply { time = currentDate }
            val exprCal = Calendar.getInstance().apply { time = sdf.parse(expiryDate) ?: Date() }

            if (!currCal.before(exprCal)) {
                finish()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun getDBData(standard: String) {
        val queries = injectedDbQueries
        machineModels = queries.getMachineModels(standard)
        val mPlaceholder = MachineModel(machine_name = "Select Machine Model")
        machineModels.add(0, mPlaceholder)

        pipeGrades = queries.getPipeGrades(standard)
        val gPlaceholder = PipeGrade(p_grade_name = "Select Pipe Grade")
        pipeGrades.add(0, gPlaceholder)

        pressureSizes = queries.getPressureSize(standard)
        val pPlaceholder = PressureSize(pressure_size_name = "Select Pressure Rating")
        pressureSizes.add(0, pPlaceholder)
    }

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        val fineLocationGranted = result[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        if (!fineLocationGranted) {
            AlertDialog.Builder(this)
                .setTitle("Location Permission Needed")
                .setMessage("This app needs the Location permission. Go to app settings and allow permission.")
                .setNegativeButton("Exit App") { _, _ -> finishActivity() }
                .show()
        }
    }

    fun askRunTimePermission() {
        if (!checkPermission()) {
            if (Build.VERSION.SDK_INT >= 33) {
                locationPermissionRequest.launch(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.NEARBY_WIFI_DEVICES)
                )
            } else {
                locationPermissionRequest.launch(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
                )
            }
        }
    }

    fun copyTextToClipBoard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("lucidfusion", text)
        clipboard.setPrimaryClip(clip)

        Utils.shareOnMail(this, text)
    }

    override fun onDestroy() {
        super.onDestroy()
        disconnectMachineWifi()
    }

    companion object {
        const val TAG = "LUCID FUTION"
    }
}
