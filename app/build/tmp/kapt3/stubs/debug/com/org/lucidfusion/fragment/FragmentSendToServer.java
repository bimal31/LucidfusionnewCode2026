package com.org.lucidfusion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.org.lucidfusion.AppConstants;
import com.org.lucidfusion.MainActivity;
import com.org.lucidfusion.R;
import com.org.lucidfusion.Utils;
import com.org.lucidfusion.data.api.NetworkResult;
import com.org.lucidfusion.databinding.FragmentSendToServerBinding;
import com.org.lucidfusion.domain.usecase.GetPastReportsUseCase;
import com.org.lucidfusion.domain.usecase.UploadReportUseCase;
import com.org.lucidfusion.dtos.ReportDTO;
import dagger.hilt.android.AndroidEntryPoint;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u001aH\u0016J\b\u0010$\u001a\u00020\u001aH\u0016J\u001a\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006("}, d2 = {"Lcom/org/lucidfusion/fragment/FragmentSendToServer;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/org/lucidfusion/databinding/FragmentSendToServerBinding;", "binding", "getBinding", "()Lcom/org/lucidfusion/databinding/FragmentSendToServerBinding;", "getPastReportsUseCase", "Lcom/org/lucidfusion/domain/usecase/GetPastReportsUseCase;", "getGetPastReportsUseCase", "()Lcom/org/lucidfusion/domain/usecase/GetPastReportsUseCase;", "setGetPastReportsUseCase", "(Lcom/org/lucidfusion/domain/usecase/GetPastReportsUseCase;)V", "pastReports", "Ljava/util/ArrayList;", "Lcom/org/lucidfusion/dtos/ReportDTO;", "title", "", "uploadReportUseCase", "Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;", "getUploadReportUseCase", "()Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;", "setUploadReportUseCase", "(Lcom/org/lucidfusion/domain/usecase/UploadReportUseCase;)V", "loadData", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "PastDataListAdapter", "app_debug"})
public final class FragmentSendToServer extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.org.lucidfusion.databinding.FragmentSendToServerBinding _binding;
    @javax.inject.Inject()
    public com.org.lucidfusion.domain.usecase.GetPastReportsUseCase getPastReportsUseCase;
    @javax.inject.Inject()
    public com.org.lucidfusion.domain.usecase.UploadReportUseCase uploadReportUseCase;
    @org.jetbrains.annotations.NotNull()
    private final java.util.ArrayList<com.org.lucidfusion.dtos.ReportDTO> pastReports = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String title = "";
    
    public FragmentSendToServer() {
        super();
    }
    
    private final com.org.lucidfusion.databinding.FragmentSendToServerBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.org.lucidfusion.domain.usecase.GetPastReportsUseCase getGetPastReportsUseCase() {
        return null;
    }
    
    public final void setGetPastReportsUseCase(@org.jetbrains.annotations.NotNull()
    com.org.lucidfusion.domain.usecase.GetPastReportsUseCase p0) {
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
    
    private final void loadData() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\"\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/org/lucidfusion/fragment/FragmentSendToServer$PastDataListAdapter;", "Landroid/widget/BaseAdapter;", "(Lcom/org/lucidfusion/fragment/FragmentSendToServer;)V", "sdf1", "Ljava/text/SimpleDateFormat;", "sdf2", "getCount", "", "getItem", "Lorg/json/JSONObject;", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "app_debug"})
    final class PastDataListAdapter extends android.widget.BaseAdapter {
        @org.jetbrains.annotations.NotNull()
        private final java.text.SimpleDateFormat sdf1 = null;
        @org.jetbrains.annotations.NotNull()
        private final java.text.SimpleDateFormat sdf2 = null;
        
        public PastDataListAdapter() {
            super();
        }
        
        @java.lang.Override()
        public long getItemId(int position) {
            return 0L;
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.Nullable()
        public org.json.JSONObject getItem(int position) {
            return null;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
        android.view.View convertView, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            return null;
        }
    }
}