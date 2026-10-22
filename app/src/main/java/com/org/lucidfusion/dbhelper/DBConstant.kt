package com.org.lucidfusion.dbhelper

interface DBConstant {
    companion object {
        const val DB_NAME = "TejasjInd.db"
        const val _ID = "id"

        // TBL_MACHINE
        const val TBL_MACHINE = "Machine"
        const val _NAME = "MachineName"
        const val _DESC = "MachineDesc"
        const val MACHINE_STANDARD = "Standard"

        // TBL_CALCDATA
        const val TBL_CALCDATA = "CalcData"
        const val _MACHINE_ID = "MachineId"
        const val _PIPESIZEID = "PipesizeId"
        const val _PIPEGRADEID = "PipeGradeId"
        const val _PRESSURREID = "PressurreId"
        const val _TEMPERATURE = "Temperature"
        const val _JOINTINGPRESSURE = "JointingPressure"
        const val _AVERAGETHICKNESS = "AverageThickness"

        // TBL_PIPEGRADE
        const val TBL_PIPEGRADE = "PipeGrade"
        const val _PIPEGRADENAME = "PipeGradeName"
        const val _PIPEGRADDESC = "PipeGradDesc"
        const val GRADE_STANDARD = "Standard"

        // TBL_PIPESIZE
        const val TBL_PIPESIZE = "PipeSize"
        const val _PIPESIZE_MACHINEID = "MachineId"
        const val _PIPESIZENAME = "PipeSizeName"
        const val _PIPESIZEDESC = "PipeSizeDesc"
        const val PIPESIZE_STANDARD = "Standard"

        // TBL_PRESSURESIZE
        const val TBL_PRESSURESIZE = "PressureSize"
        const val _PRESSURESIZENAME = "PressureSizeName"
        const val PRESSURE_STANDARD = "Standard"

        // TBL_REPORT
        const val TBL_REPORT = "Report"
        const val _FILENAME = "filename"
        const val _REPORT_DATA = "report_data"
        const val _IS_UPLOADED = "is_uploaded"
    }
}
