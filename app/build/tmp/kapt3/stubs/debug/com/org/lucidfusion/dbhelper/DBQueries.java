package com.org.lucidfusion.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.org.lucidfusion.ApplicationClass;
import com.org.lucidfusion.MainActivity;
import com.org.lucidfusion.dtos.MachineModel;
import com.org.lucidfusion.dtos.PipeGrade;
import com.org.lucidfusion.dtos.PipeSize;
import com.org.lucidfusion.dtos.PressureSize;
import com.org.lucidfusion.dtos.ReportDTO;
import org.json.JSONObject;
import java.util.Collections;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JB\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t`\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fJ\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0011j\b\u0012\u0004\u0012\u00020\u0016`\u00132\u0006\u0010\u0017\u001a\u00020\tJ&\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0011j\b\u0012\u0004\u0012\u00020\u0019`\u00132\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\tJ\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u0011j\b\u0012\u0004\u0012\u00020\u001b`\u00132\u0006\u0010\u0017\u001a\u00020\tJ\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\tJ\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u001d0\u0011j\b\u0012\u0004\u0012\u00020\u001d`\u0013J\u0016\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020&2\u0006\u0010\u001e\u001a\u00020\tJ5\u0010\'\u001a\u00020!2\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020$2\b\u0010(\u001a\u0004\u0018\u00010\t2\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010*\u00a2\u0006\u0002\u0010+R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006,"}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBQueries;", "Lcom/org/lucidfusion/dbhelper/DBConstant;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getCalcData", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "machine_id", "", "pipe_size_id", "pipe_grade_id", "pressure_id", "getMachineModels", "Ljava/util/ArrayList;", "Lcom/org/lucidfusion/dtos/MachineModel;", "Lkotlin/collections/ArrayList;", "machine_standard", "getPipeGrades", "Lcom/org/lucidfusion/dtos/PipeGrade;", "standard", "getPipeSizeForMachine", "Lcom/org/lucidfusion/dtos/PipeSize;", "getPressureSize", "Lcom/org/lucidfusion/dtos/PressureSize;", "getReport", "Lcom/org/lucidfusion/dtos/ReportDTO;", "fn", "getReports", "insert", "", "table", "contentValues", "Landroid/content/ContentValues;", "isReportExist", "", "update", "whereClause", "whereArgs", "", "(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)J", "app_debug"})
public final class DBQueries implements com.org.lucidfusion.dbhelper.DBConstant {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public DBQueries(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final boolean isReportExist(@org.jetbrains.annotations.NotNull()
    java.lang.String fn) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.MachineModel> getMachineModels(@org.jetbrains.annotations.NotNull()
    java.lang.String machine_standard) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PipeSize> getPipeSizeForMachine(int machine_id, @org.jetbrains.annotations.NotNull()
    java.lang.String standard) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PipeGrade> getPipeGrades(@org.jetbrains.annotations.NotNull()
    java.lang.String standard) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.PressureSize> getPressureSize(@org.jetbrains.annotations.NotNull()
    java.lang.String standard) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.String, java.lang.String> getCalcData(int machine_id, int pipe_size_id, int pipe_grade_id, int pressure_id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<com.org.lucidfusion.dtos.ReportDTO> getReports() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.org.lucidfusion.dtos.ReportDTO getReport(@org.jetbrains.annotations.NotNull()
    java.lang.String fn) {
        return null;
    }
    
    public final long insert(@org.jetbrains.annotations.NotNull()
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    android.content.ContentValues contentValues) {
        return 0L;
    }
    
    public final long update(@org.jetbrains.annotations.NotNull()
    java.lang.String table, @org.jetbrains.annotations.NotNull()
    android.content.ContentValues contentValues, @org.jetbrains.annotations.Nullable()
    java.lang.String whereClause, @org.jetbrains.annotations.Nullable()
    java.lang.String[] whereArgs) {
        return 0L;
    }
}