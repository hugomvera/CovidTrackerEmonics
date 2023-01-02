package com.emonics.covidtracker

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface DataApi {
    @GET("/v1/us/daily.json")
    suspend fun getData() : Response<DataList>
}