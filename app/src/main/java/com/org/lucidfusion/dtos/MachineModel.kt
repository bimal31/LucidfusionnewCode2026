package com.org.lucidfusion.dtos

data class MachineModel(
    @JvmField var machine_id: Int = 0,
    @JvmField var machine_name: String? = null,
    @JvmField var machine_desc: String? = null,
    @JvmField var sort_value: Int = 0
)
