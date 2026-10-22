package com.org.lucidfusion.data.api

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface WeldReportApiService {

    @POST("api.php")
    suspend fun sendReportData(
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<String>
}
