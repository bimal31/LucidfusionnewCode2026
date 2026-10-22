package com.org.lucidfusion.dtos

data class PipeSize(
    @JvmField var pipe_size_id: Int = 0,
    @JvmField var machine_id: Int = 0,
    @JvmField var pipe_size_name: String? = null,
    @JvmField var pipe_size_desc: String? = null
)
