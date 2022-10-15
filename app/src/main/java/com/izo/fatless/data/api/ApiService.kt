package com.izo.fatless.data.api

import com.izo.fatless.data.request.RequestPredict

import com.izo.fatless.data.response.PredictResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("predict")
    fun getPredict(
        @Body requestPredict: RequestPredict
    ): Call<PredictResponse>
}