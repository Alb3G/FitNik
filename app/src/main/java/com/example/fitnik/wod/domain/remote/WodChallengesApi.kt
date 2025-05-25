package com.example.fitnik.wod.domain.remote

import com.example.fitnik.wod.domain.model.WodResponse
import retrofit2.http.GET

interface WodChallengesApi {

    @GET("/wod")
    suspend fun getWod(): WodResponse

}