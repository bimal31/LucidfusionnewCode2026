package com.org.lucidfusion.dtos

data class CalcData(
    @JvmField var calc_data_id: Int = 0,
    @JvmField var machine_id: Int = 0,
    @JvmField var pipe_size_id: Int = 0,
    @JvmField var pipe_grade_id: Int = 0,
    @JvmField var pressure_id: Int = 0,
    @JvmField var temprature: String? = null,
    @JvmField var jointing_pressure: String? = null
)
