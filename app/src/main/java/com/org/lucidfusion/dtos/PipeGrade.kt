package com.org.lucidfusion.dtos

data class PipeGrade(
    @JvmField var p_grade_id: Int = 0,
    @JvmField var p_grade_name: String? = null,
    @JvmField var p_grade_desc: String? = null
)
