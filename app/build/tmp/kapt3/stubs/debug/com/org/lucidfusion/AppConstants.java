package com.org.lucidfusion;

import android.util.SparseArray;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0006j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004`\u00078\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040)8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00040)8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00040)8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040)8\u0006X\u0087\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/org/lucidfusion/AppConstants;", "", "()V", "API_URL", "", "KEYS", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "KEY_ACHIVED_DRAG_PRESS", "KEY_ACHI_PRESS_JOINT", "KEY_BEAD_UP_TIME", "KEY_CHANGEOVER_TIME", "KEY_COMMENT", "KEY_COMPANY_NAME", "KEY_COOLING_TIME", "KEY_DATE", "KEY_DATE_TIME", "KEY_FN", "KEY_HOT_PLATE_TEMP", "KEY_HOUR", "KEY_LOGIC_WELDER", "KEY_MACHINE_ID", "KEY_MATERIAL_GRADE", "KEY_MIN", "KEY_MONTH", "KEY_PIPE_DIA", "KEY_PIPE_LENGTH", "KEY_PIPE_PRESSURE_RATING", "KEY_PROJECT_NAME", "KEY_SDR", "KEY_SEC", "KEY_SET_DRAG_PRESS", "KEY_SET_JOINT_PRESS", "KEY_SET_PRESS_AT_JOINT", "KEY_SITE_PLACE", "KEY_SOAKING_TIME", "KEY_WALL_THICKNESS", "KEY_WELDER_NAME", "KEY_WELDNO", "KEY_YEAR", "LOGIC_WELDER", "Landroid/util/SparseArray;", "MATERIAL_GRADE", "PIPE_LENGTH", "PIPE_PRESSURE_RATING", "app_debug"})
public final class AppConstants {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String API_URL = "https://weldreports.com/api.php?request=addreportdata&PLCNumber=%1$s&FileName=%2$s";
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final java.util.HashMap<java.lang.String, java.lang.String> KEYS = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_FN = "FN";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_MACHINE_ID = "Machine ID";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_LOGIC_WELDER = "Logic Welder";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_WELDNO = "Weld No";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DATE = "date";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_MONTH = "month";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_YEAR = "year";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_HOUR = "hour";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_MIN = "min";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SEC = "sec";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PIPE_DIA = "Pipe Dia";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_MATERIAL_GRADE = "Material Grade";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PIPE_PRESSURE_RATING = "Pipe pressure rating";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_WALL_THICKNESS = "Wall thickness";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SET_DRAG_PRESS = "Set drag press";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ACHIVED_DRAG_PRESS = "Achived drag press";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SET_JOINT_PRESS = "Set joint press";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SET_PRESS_AT_JOINT = "Set press @joint";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ACHI_PRESS_JOINT = "Achi. press joint";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_HOT_PLATE_TEMP = "Hot plate temp";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_BEAD_UP_TIME = "Bead up time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SOAKING_TIME = "Soaking time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_CHANGEOVER_TIME = "Changeover time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_COOLING_TIME = "Cooling time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_COMPANY_NAME = "company_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_DATE_TIME = "date_time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_WELDER_NAME = "Welder_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PROJECT_NAME = "project_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SITE_PLACE = "site_place";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SDR = "sdr";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PIPE_LENGTH = "pipe_length";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_COMMENT = "comment";
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final android.util.SparseArray<java.lang.String> MATERIAL_GRADE = null;
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final android.util.SparseArray<java.lang.String> LOGIC_WELDER = null;
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final android.util.SparseArray<java.lang.String> PIPE_PRESSURE_RATING = null;
    @kotlin.jvm.JvmField()
    @org.jetbrains.annotations.NotNull()
    public static final android.util.SparseArray<java.lang.String> PIPE_LENGTH = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.AppConstants INSTANCE = null;
    
    private AppConstants() {
        super();
    }
}