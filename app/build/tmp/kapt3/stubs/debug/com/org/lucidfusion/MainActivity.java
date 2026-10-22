package com.org.lucidfusion;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.org.lucidfusion.databinding.ActivityMainBinding;
import com.org.lucidfusion.dbhelper.DBAdapter;
import com.org.lucidfusion.dbhelper.DBQueries;
import com.org.lucidfusion.dtos.MachineModel;
import com.org.lucidfusion.dtos.PipeGrade;
import com.org.lucidfusion.dtos.PipeSize;
import com.org.lucidfusion.dtos.PressureSize;
import com.org.lucidfusion.fragment.SplashFragment;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00ac\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\r\n\u0002\b\u0006\b\u0007\u0018\u0000 ^2\u00020\u0001:\u0001^B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\u0011J\b\u0010,\u001a\u00020\u0011H\u0002J\u000e\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020/J2\u00100\u001a\u00020*2\u0006\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u00020\u001d2\f\u00103\u001a\b\u0012\u0004\u0012\u00020*042\f\u00105\u001a\b\u0012\u0004\u0012\u00020*04J\u000e\u00106\u001a\u00020*2\u0006\u00107\u001a\u00020\u001dJ\u0006\u00108\u001a\u00020*J\u0006\u00109\u001a\u00020*J\b\u0010:\u001a\u0004\u0018\u00010\u0006J\u000e\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020\u001dJ\u0016\u0010=\u001a\u0012\u0012\u0004\u0012\u00020 0\u001fj\b\u0012\u0004\u0012\u00020 `>J\u0016\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\"0\u001fj\b\u0012\u0004\u0012\u00020\"`>J&\u0010@\u001a\u0012\u0012\u0004\u0012\u00020$0\u001fj\b\u0012\u0004\u0012\u00020$`>2\u0006\u0010A\u001a\u00020B2\u0006\u0010<\u001a\u00020\u001dJ\u0016\u0010C\u001a\u0012\u0012\u0004\u0012\u00020&0\u001fj\b\u0012\u0004\u0012\u00020&`>J\b\u0010D\u001a\u00020*H\u0002J\u0006\u0010E\u001a\u00020\u0011J\u0010\u0010F\u001a\u00020*2\u0006\u0010<\u001a\u00020\u001dH\u0002J\u0012\u0010G\u001a\u00020*2\b\u0010H\u001a\u0004\u0018\u00010IH\u0014J\b\u0010J\u001a\u00020*H\u0014J(\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\u00112\b\u0010O\u001a\u0004\u0018\u00010\u001d2\u0006\u0010P\u001a\u00020\u0011J\u0016\u0010Q\u001a\u0012\u0012\u0004\u0012\u00020$0\u001fj\b\u0012\u0004\u0012\u00020$`>J\u000e\u0010R\u001a\u00020*2\u0006\u0010S\u001a\u00020BJ\u0010\u0010T\u001a\u00020*2\b\u0010U\u001a\u0004\u0018\u00010\u0006J\u000e\u0010V\u001a\u00020*2\u0006\u0010S\u001a\u00020BJ\u0012\u0010W\u001a\u00020*2\b\u0010X\u001a\u0004\u0018\u00010YH\u0016J\b\u0010Z\u001a\u00020*H\u0002J\b\u0010[\u001a\u00020*H\u0002J\u000e\u0010\\\u001a\u00020*2\u0006\u0010S\u001a\u00020BJ\u000e\u0010]\u001a\u00020*2\u0006\u0010S\u001a\u00020BR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\'\u001a\u0004\u0018\u00010(X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006_"}, d2 = {"Lcom/org/lucidfusion/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/org/lucidfusion/databinding/ActivityMainBinding;", "boundNetwork", "Landroid/net/Network;", "dbQueries", "Lcom/org/lucidfusion/dbhelper/DBQueries;", "imm", "Landroid/view/inputmethod/InputMethodManager;", "injectedDbQueries", "getInjectedDbQueries", "()Lcom/org/lucidfusion/dbhelper/DBQueries;", "setInjectedDbQueries", "(Lcom/org/lucidfusion/dbhelper/DBQueries;)V", "<set-?>", "", "isHindiSupported", "()Z", "iv_share", "Landroid/widget/ImageView;", "getIv_share", "()Landroid/widget/ImageView;", "legacyWifiReceiver", "Landroid/content/BroadcastReceiver;", "locationPermissionRequest", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "machineModels", "Ljava/util/ArrayList;", "Lcom/org/lucidfusion/dtos/MachineModel;", "pipeGrades", "Lcom/org/lucidfusion/dtos/PipeGrade;", "pipeSizes", "Lcom/org/lucidfusion/dtos/PipeSize;", "pressureSizes", "Lcom/org/lucidfusion/dtos/PressureSize;", "wifiNetworkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "askRunTimePermission", "", "checkAppStatus", "checkPermission", "closeKeyboard", "view", "Landroid/view/View;", "connectMachineWifi", "ssid", "passphrase", "onSuccess", "Lkotlin/Function0;", "onFailure", "copyTextToClipBoard", "text", "disconnectMachineWifi", "finishActivity", "getBoundNetwork", "getDBData", "standard", "getMachineModels", "Lkotlin/collections/ArrayList;", "getPipeGrades", "getPipeSizes", "machineId", "", "getPressureSizes", "initApp", "isMachineWifiConnected", "loadDBDataAsync", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "replaceFragment", "fragment", "Landroidx/fragment/app/Fragment;", "shouldAnimate", "tag", "shouldAddToBackStack", "resetPipeSizes", "setBackButtonVisibility", "visibility", "setBoundNetwork", "network", "setShareButtonVisibility", "setTitle", "title", "", "setupEdgeToEdgeInsets", "setupPredictiveBackNavigation", "showHideBottomBar", "showHideTitleBar", "Companion", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.org.lucidfusion.databinding.ActivityMainBinding binding;
    @javax.inject.Inject()
    public com.org.lucidfusion.dbhelper.DBQueries injectedDbQueries;
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.Nullable()
    public com.org.lucidfusion.dbhelper.DBQueries dbQueries;
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.Nullable()
    public android.net.Network boundNetwork;
    @org.jetbrains.annotations.Nullable()
    private android.net.ConnectivityManager.NetworkCallback wifiNetworkCallback;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver legacyWifiReceiver;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.org.lucidfusion.dtos.MachineModel> machineModels;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.org.lucidfusion.dtos.PipeSize> pipeSizes;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.org.lucidfusion.dtos.PipeGrade> pipeGrades;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<com.org.lucidfusion.dtos.PressureSize> pressureSizes;
    private boolean isHindiSupported = false;
    @org.jetbrains.annotations.Nullable()
    private android.view.inputmethod.InputMethodManager imm;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> locationPermissionRequest = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "LUCID FUTION";
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.org.lucidfusion.dbhelper.DBQueries getInjectedDbQueries() {
        return null;
    }
    
    public final void setInjectedDbQueries(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.dbhelper.DBQueries p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.net.Network getBoundNetwork() {
        return null;
    }
    
    public final void setBoundNetwork(@org.jetbrains.annotations.Nullable()
    android.net.Network network) {
    }
    
    public final boolean isMachineWifiConnected() {
        return false;
    }
    
    public final void connectMachineWifi(@org.jetbrains.annotations.NotNull()
    java.lang.String ssid, @org.jetbrains.annotations.NotNull()
    java.lang.String passphrase, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFailure) {
    }
    
    public final void disconnectMachineWifi() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.ImageView getIv_share() {
        return null;
    }
    
    public final boolean isHindiSupported() {
        return false;
    }
    
    public final void finishActivity() {
    }
    
    public final void closeKeyboard(@org.jetbrains.annotations.NotNull()
    android.view.View view) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupPredictiveBackNavigation() {
    }
    
    private final void setupEdgeToEdgeInsets() {
    }
    
    private final boolean checkPermission() {
        return false;
    }
    
    private final void initApp() {
    }
    
    private final void loadDBDataAsync(java.lang.String standard) {
    }
    
    public final void showHideTitleBar(int visibility) {
    }
    
    public final void showHideBottomBar(int visibility) {
    }
    
    public final void setShareButtonVisibility(int visibility) {
    }
    
    @java.lang.Override()
    public void setTitle(@org.jetbrains.annotations.Nullable()
    java.lang.CharSequence title) {
    }
    
    public final void setBackButtonVisibility(int visibility) {
    }
    
    public final void replaceFragment(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.Fragment fragment, boolean shouldAnimate, @org.jetbrains.annotations.Nullable()
    java.lang.String tag, boolean shouldAddToBackStack) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.MachineModel> getMachineModels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PipeSize> getPipeSizes(int machineId, @org.jetbrains.annotations.NotNull()
    java.lang.String standard) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PipeSize> resetPipeSizes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PipeGrade> getPipeGrades() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PressureSize> getPressureSizes() {
        return null;
    }
    
    public final boolean checkAppStatus() {
        return false;
    }
    
    public final void getDBData(@org.jetbrains.annotations.NotNull()
    java.lang.String standard) {
    }
    
    public final void askRunTimePermission() {
    }
    
    public final void copyTextToClipBoard(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/org/lucidfusion/MainActivity$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}