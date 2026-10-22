package com.org.lucidfusion.dbhelper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBConstant;", "", "Companion", "app_debug"})
public abstract interface DBConstant {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DB_NAME = "TejasjInd.db";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _ID = "id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TBL_MACHINE = "Machine";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _NAME = "MachineName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _DESC = "MachineDesc";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String MACHINE_STANDARD = "Standard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TBL_CALCDATA = "CalcData";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _MACHINE_ID = "MachineId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPESIZEID = "PipesizeId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPEGRADEID = "PipeGradeId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PRESSURREID = "PressurreId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _TEMPERATURE = "Temperature";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _JOINTINGPRESSURE = "JointingPressure";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _AVERAGETHICKNESS = "AverageThickness";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TBL_PIPEGRADE = "PipeGrade";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPEGRADENAME = "PipeGradeName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPEGRADDESC = "PipeGradDesc";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GRADE_STANDARD = "Standard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TBL_PIPESIZE = "PipeSize";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPESIZE_MACHINEID = "MachineId";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPESIZENAME = "PipeSizeName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PIPESIZEDESC = "PipeSizeDesc";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PIPESIZE_STANDARD = "Standard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TBL_PRESSURESIZE = "PressureSize";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _PRESSURESIZENAME = "PressureSizeName";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PRESSURE_STANDARD = "Standard";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TBL_REPORT = "Report";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _FILENAME = "filename";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _REPORT_DATA = "report_data";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String _IS_UPLOADED = "is_uploaded";
    @org.jetbrains.annotations.NotNull()
    public static final com.org.lucidfusion.dbhelper.DBConstant.Companion Companion = null;
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/org/lucidfusion/dbhelper/DBConstant$Companion;", "", "()V", "DB_NAME", "", "GRADE_STANDARD", "MACHINE_STANDARD", "PIPESIZE_STANDARD", "PRESSURE_STANDARD", "TBL_CALCDATA", "TBL_MACHINE", "TBL_PIPEGRADE", "TBL_PIPESIZE", "TBL_PRESSURESIZE", "TBL_REPORT", "_AVERAGETHICKNESS", "_DESC", "_FILENAME", "_ID", "_IS_UPLOADED", "_JOINTINGPRESSURE", "_MACHINE_ID", "_NAME", "_PIPEGRADDESC", "_PIPEGRADEID", "_PIPEGRADENAME", "_PIPESIZEDESC", "_PIPESIZEID", "_PIPESIZENAME", "_PIPESIZE_MACHINEID", "_PRESSURESIZENAME", "_PRESSURREID", "_REPORT_DATA", "_TEMPERATURE", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String DB_NAME = "TejasjInd.db";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _ID = "id";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TBL_MACHINE = "Machine";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _NAME = "MachineName";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _DESC = "MachineDesc";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String MACHINE_STANDARD = "Standard";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TBL_CALCDATA = "CalcData";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _MACHINE_ID = "MachineId";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPESIZEID = "PipesizeId";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPEGRADEID = "PipeGradeId";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PRESSURREID = "PressurreId";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _TEMPERATURE = "Temperature";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _JOINTINGPRESSURE = "JointingPressure";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _AVERAGETHICKNESS = "AverageThickness";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TBL_PIPEGRADE = "PipeGrade";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPEGRADENAME = "PipeGradeName";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPEGRADDESC = "PipeGradDesc";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String GRADE_STANDARD = "Standard";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TBL_PIPESIZE = "PipeSize";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPESIZE_MACHINEID = "MachineId";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPESIZENAME = "PipeSizeName";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PIPESIZEDESC = "PipeSizeDesc";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PIPESIZE_STANDARD = "Standard";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TBL_PRESSURESIZE = "PressureSize";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _PRESSURESIZENAME = "PressureSizeName";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String PRESSURE_STANDARD = "Standard";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TBL_REPORT = "Report";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _FILENAME = "filename";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _REPORT_DATA = "report_data";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String _IS_UPLOADED = "is_uploaded";
        
        private Companion() {
            super();
        }
    }
}