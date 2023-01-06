package com.emonics.covidtracker.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface DataApi {
    @GET("/v1/us/daily.json")
    suspend fun getData() : Response<DataList>
}