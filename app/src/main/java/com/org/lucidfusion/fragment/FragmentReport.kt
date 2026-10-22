package com.org.lucidfusion.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.org.lucidfusion.MainActivity
import com.org.lucidfusion.R
import com.org.lucidfusion.Utils
import com.org.lucidfusion.databinding.FragmentReportBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentReport : Fragment(), View.OnClickListener {

    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!

    private var isConnected = false

    private var strConnect = ""
    private var strDisconnect = ""
    private var strCancel = ""
    private var strSSID = ""
    private var strPassword = ""

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = Runnable {
        _binding?.let { b ->
            b.progressbar.visibility = View.GONE
            b.ivNoresult.visibility = View.VISIBLE
            b.btnCancel.text = "Retry"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isHindi = Utils.getStringSetting(requireContext(), Utils.PREF_LANGAUGE) == "hindi"
        if (isHindi) {
            (activity as? MainActivity)?.setTitle(getString(R.string.hindi_lbl_report))
            strCancel = getString(R.string.hindi_lbl_cancel)
            strConnect = getString(R.string.hindi_lbl_connect)
            strDisconnect = getString(R.string.hindi_lbl_disconnect)
            strSSID = getString(R.string.lbl_hindi_wifi_name)
            strPassword = getString(R.string.lbl_hindi_password)
            binding.btnConnect.text = getString(R.string.hindi_lbl_connect)
            binding.btnCancel.text = getString(R.string.hindi_lbl_cancel)
            binding.btnNewReport.text = getString(R.string.hindi_lbl_new_report)
            binding.btnPastReport.text = getString(R.string.hindi_lbl_past_report)
            binding.btnSendServer.text = getString(R.string.hindi_lbl_send_server)
        } else {
            (activity as? MainActivity)?.setTitle(getString(R.string.lbl_report))
            strCancel = getString(R.string.lbl_cancel)
            strConnect = getString(R.string.lbl_connect)
            strDisconnect = getString(R.string.lbl_disconnect)
            strSSID = getString(R.string.lbl_wifi_name)
            strPassword = getString(R.string.lbl_password)
            binding.btnConnect.text = getString(R.string.lbl_connect)
            binding.btnCancel.text = getString(R.string.lbl_cancel)
            binding.btnNewReport.text = getString(R.string.lbl_new_report)
            binding.btnPastReport.text = getString(R.string.lbl_past_report)
            binding.btnSendServer.text = getString(R.string.lbl_send_server)
        }

        binding.edPassword.hint = strPassword
        binding.edSsid.hint = strSSID
        binding.tvPassword.text = strPassword
        binding.tvSsid.text = strSSID

        binding.edSsid.setText(Utils.getStringSetting(requireContext(), Utils.PREF_SSID))
        binding.edPassword.setText(Utils.getStringSetting(requireContext(), Utils.PREF_PASSWORD))

        binding.btnConnect.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        binding.btnNewReport.setOnClickListener(this)
        binding.btnPastReport.setOnClickListener(this)
        binding.btnSendServer.setOnClickListener(this)

        (activity as? MainActivity)?.setBackButtonVisibility(View.VISIBLE)
        updateWifiUiState()
    }

    private fun updateWifiUiState() {
        val connected = (activity as? MainActivity)?.isMachineWifiConnected() == true
        isConnected = connected
        if (_binding != null) {
            if (connected) {
                binding.llProgressbar.visibility = View.GONE
                handler.removeCallbacks(runnable)
                binding.btnPastReport.isEnabled = true
                binding.btnNewReport.isEnabled = true
                binding.edSsid.isEnabled = true
                binding.edPassword.isEnabled = true
                binding.btnConnect.text = strDisconnect
            } else {
                binding.btnPastReport.isEnabled = false
                binding.btnNewReport.isEnabled = false
                binding.btnConnect.text = strConnect
            }
        }
    }

    private fun disconnectWifi() {
        handler.removeCallbacks(runnable)
        (activity as? MainActivity)?.disconnectMachineWifi()
        updateWifiUiState()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnConnect -> {
                (activity as? MainActivity)?.closeKeyboard(binding.edPassword)
                if (isConnected) {
                    disconnectWifi()
                    return
                }

                val networkSSID = binding.edSsid.text.toString().trim()
                val networkPass = binding.edPassword.text.toString().trim()

                if (networkSSID.isEmpty()) {
                    binding.edSsid.error = "Please enter ssid"
                    return
                }
                if (networkPass.isEmpty()) {
                    binding.edPassword.error = "Please enter password"
                    return
                }

                binding.llProgressbar.visibility = View.VISIBLE
                binding.edSsid.isEnabled = false
                binding.edPassword.isEnabled = false

                handler.postDelayed(runnable, 30000)

                (activity as? MainActivity)?.connectMachineWifi(
                    networkSSID,
                    networkPass,
                    onSuccess = {
                        Utils.setStringSetting(requireContext(), Utils.PREF_SSID, networkSSID)
                        Utils.setStringSetting(requireContext(), Utils.PREF_PASSWORD, networkPass)
                        updateWifiUiState()
                    },
                    onFailure = {
                        updateWifiUiState()
                    }
                )
            }
            R.id.btn_new_report -> {
                (activity as? MainActivity)?.replaceFragment(FragmentNewReport(), true, null, true)
            }
            R.id.btn_past_report -> {
                (activity as? MainActivity)?.replaceFragment(FragmentPastReport(), true, null, true)
            }
            R.id.btn_send_server -> {
                (activity as? MainActivity)?.replaceFragment(FragmentSendToServer(), true, null, true)
            }
            R.id.btn_cancel -> {
                if (binding.ivNoresult.isShown) {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.ivNoresult.visibility = View.GONE
                    binding.btnCancel.text = strCancel
                    disconnectWifi()
                    binding.btnConnect.performClick()
                } else {
                    binding.llProgressbar.visibility = View.GONE
                    handler.removeCallbacks(runnable)
                    binding.edSsid.isEnabled = true
                    binding.edPassword.isEnabled = true
                    disconnectWifi()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setShareButtonVisibility(View.INVISIBLE)
        updateWifiUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
