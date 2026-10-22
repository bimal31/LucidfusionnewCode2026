package com.org.lucidfusion.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import kotlinx.coroutines.Dispatchers;
import com.org.lucidfusion.AppConstants;
import com.org.lucidfusion.MainActivity;
import com.org.lucidfusion.R;
import com.org.lucidfusion.Utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.org.lucidfusion.data.api.NetworkResult;
import com.org.lucidfusion.databinding.FragmentNewReportBinding;
import com.org.lucidfusion.dbhelper.DBConstant;
import com.org.lucidfusion.domain.usecase.SaveReportUseCase;
import com.org.lucidfusion.domain.usecase.UploadReportUseCase;
import com.org.lucidfusion.dtos.ReportDTO;
import dagger.hilt.android.AndroidEntryPoint;
import org.json.JSONException;
import org.json.JSONObject;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0018\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020\u0013H\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J$\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020\u001dH\u0016J\b\u0010+\u001a\u00020\u001dH\u0016J\u001a\u0010,\u001a\u00020\u001d2\u0006\u0010-\u001a\u00020#2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010.\u001a\u00020\u001dH\u0002J\u0010\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\tH\u0002J\b\u00101\u001a\u00020\u001dH\u0002J\u0010\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u0013H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u00a8\u00064"}, d2 = {"Lcom/org/lucidfusion/fragment/FragmentNewReport;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/org/lucidfusion/databinding/FragmentNewReportBinding;", "binding", "getBinding", "()Lcom/org/lucidfusion/databinding/FragmentNewReportBinding;", "isEditMode", "", "reportDTO", "Lcom/org/lucidfusion/dtos/ReportDTO;", "saveReportUseCase", "Lcom/org/lucidfusion/domain/usecase/SaveReportUseCase;", "getSaveReportUseCase", "()Lcom/org/lucidfusion/domain/usecase/SaveReportUseCase;", "setSaveReportUseCase", "(Lcom/org/lucidfusion/domain/usecase/SaveReportUseCase;)V", "strMinSec", "", "strSaveOffline", "strSendToServer", "uploadReportUseCase", "Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;", "getUploadReportUseCase", "()Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;", "setUploadReportUseCase", "(Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;)V", "fetchMachineData", "", "formatValue", "value", "unit", "handleSaveClick", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDestroyView", "onViewCreated", "view", "populateData", "setLanguageLabels", "isHindi", "shareReportData", "showAlertDialog", "message", "app_debug"})
public final class FragmentNewReport extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.org.lucidfusion.databinding.FragmentNewReportBinding _binding;
    @javax.inject.Inject()
    public com.org.lucidfusion.domain.usecase.SaveReportUseCase saveReportUseCase;
    @javax.inject.Inject()
    public com.org.lucidfusion.domain.usecase.UploadReportUseCase uploadReportUseCase;
    private boolean isEditMode = false;
    @org.jetbrains.annotations.NotNull()
    private com.org.lucidfusion.dtos.ReportDTO reportDTO;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String strSaveOffline = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String strSendToServer = "";
    @org.jetbrains.annotations.NotNull()
    private java.lang.String strMinSec = "";
    
    public FragmentNewReport() {
        super();
    }
    
    private final com.org.lucidfusion.databinding.FragmentNewReportBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.org.lucidfusion.domain.usecase.SaveReportUseCase getSaveReportUseCase() {
        return null;
    }
    
    public final void setSaveReportUseCase(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.SaveReportUseCase p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.org.lucidfusion.domain.usecase.UploadReportUseCase getUploadReportUseCase() {
        return null;
    }
    
    public final void setUploadReportUseCase(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.UploadReportUseCase p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void handleSaveClick() {
    }
    
    private final void shareReportData() {
    }
    
    private final void setLanguageLabels(boolean isHindi) {
    }
    
    private final void populateData() {
    }
    
    private final java.lang.String formatValue(java.lang.String value, java.lang.String unit) {
        return null;
    }
    
    private final void showAlertDialog(java.lang.String message) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void fetchMachineData() {
    }
}