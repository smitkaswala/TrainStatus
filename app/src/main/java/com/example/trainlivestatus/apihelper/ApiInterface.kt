package com.example.trainlivestatus.apihelper

import com.example.trainlivestatus.model.*
import com.example.trainlivestatus.test.TestModel
import com.example.trainlivestatus.trainavaimodel.SeatAvailabilityModel
import com.example.trainlivestatus.trainavaimodel.TopCalModel
import com.example.trainlivestatus.utils.WsClients
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("station_list?")
    fun FindStations(
        @Field(WsClients.app_version) app_version: String?,
        @Field(WsClients.api_key) api_key: String?
    ): Call<JsonArray?>?


    @GET("content/trainstation")
    fun newSearchStations(
        @Query(WsClients.anchor) anchor: Boolean?,
        @Query(WsClients.searchFor) searchFor: String?,
        @Query(WsClients.value) value: String?
    ): Call<List<SearchStationsItem>>


    @GET("/api/platform/trainbooking/tatwnstns")
    fun RouteStationCall(
        @Query(WsClients.fromStnCode) fromStnCode: String?,
        @Query(WsClients.destStnCode) destination: String?,
        @Query(WsClients.doj) doj: String?,
        @Query(WsClients.token) token: String?,
        @Query(WsClients.quota) quota: String?,
        @Query(WsClients.locale) locale: String?,
        @Query(WsClients.androidid) androidid: String?,
        @Query(WsClients.appVersion) appVersion: String?,
        @Query(WsClients.email) email: String?
    ): Call<RouteStationModel?>?

    @GET("api/trains/schedulewithintermediatestn")
    fun getTrainDetails(@Query(WsClients.trainNo) trainNo: String?): Call<InterstnModel?>

    @GET("api/trains/livestatusall")
    fun trainlivestatus(
        @Query(WsClients.trainno) trainNo: String?,
        @Query(WsClients.doj) doj: String?
    ): Call<LiveStatusModel?>

    @GET("api/trains/{trainno}/monthlyavailability")
    fun getTrainCalender(
        @Path(WsClients.trainno) trainNo: String?,
        @Query(WsClients.source) source: String?,
        @Query(WsClients.destination) destination: String?,
        @Query(WsClients.doj) doj: String?,
        @Query(WsClients.travelclasses) travelClasses: String?,
        @Query(WsClients.quota) quota: String?,
        @Query(WsClients.email) email: String?
    ): Call<List<SeatAvailabilityModel?>?>

    @GET("api/trains/latest")
    fun topcalanderdata(
        @Query(WsClients.source) source: String?,
        @Query(WsClients.destination) destination: String?,
        @Query(WsClients.doj) doj: String?,
        @Query(WsClients.travelClass) travelClass: String?,
        @Query(WsClients.email) email: String?,
        @Query(WsClients.passengerTrains) passengerTrains: Boolean?,
        @Query(WsClients.locale) locale: String?,
        @Query(WsClients.showEcClass) showEcClass: Boolean?
    ): Call<TopCalModel?>?

    @Headers("Authorization: Token dab50e0656db2e9383bc9269d35615a6e93aef15")
    @POST("railapi/getlivestation")
    fun livestation1(
        @Body action: JsonObject?
    ): Call<JsonObject?>?

    @Headers(
        "Authorization: Token dab50e0656db2e9383bc9269d35615a6e93aef15",
        "Content-Type: application/x-www-form-urlencoded",
        "Accept-Path: true",
        "User-Agent: Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36"
    )
    @POST("/crisns/AppServAnd")
    fun livestation2(@Body action: JsonObject?): Call<JsonObject?>?

    @Headers("Authorization: Token dab50e0656db2e9383bc9269d35615a6e93aef15")
    @POST("railapi/getlivestation")
    fun finalstation(@Body action: JsonObject?): Call<JsonObject?>?

    @GET("action/content/")
    suspend fun nameorcode(
        @Query("nameOrCode") nameOrCode: CharSequence?,
        @Query("searchFor") searchFor: String
    ): List<NameOrCodeModelItem>

    @GET("action/content/")
    fun trainby(
        @Query("nameOrCode") nameOrCode: CharSequence?,
        @Query("searchFor") searchFor: String
    ): Call<List<NameOrCodeModelItem>>


    @GET("ixi-api/local/cities")
    fun test(): Call<TestModel>


    /*@Multipart
    @Headers("Authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImMxMGM5MGJhNGMzNjYzNTE2ZTA3MDdkMGU5YTg5NDgxMDYyODUxNTgiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vY2FyaG9tZS10ZXN0LTMyOTgwOSIsImF1ZCI6ImNhcmhvbWUtdGVzdC0zMjk4MDkiLCJhdXRoX3RpbWUiOjE2NDM3Nzc4MjcsInVzZXJfaWQiOiJWeVl0RThiRXdiYUdaUFNXc1k1cWppamNDbmQyIiwic3ViIjoiVnlZdEU4YkV3YmFHWlBTV3NZNXFqaWpjQ25kMiIsImlhdCI6MTY0Mzc4MzM0OSwiZXhwIjoxNjQzNzg2OTQ5LCJlbWFpbCI6ImRldmVsb3BlcjEuY3ViZXp5QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiZmlyZWJhc2UiOnsiaWRlbnRpdGllcyI6eyJlbWFpbCI6WyJkZXZlbG9wZXIxLmN1YmV6eUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.dDu5gQeV_QAVPj55hZAVhVlexdUecd9aFHMVZS3BZx_n83CUlBH45EVT4vjMLqy4ZOKkN7bXmUBiKOrTQz_YObYtWnhoCQdX6Ne52CCJF5joVxBNOdt1bOQW06-ruKWajPgS8MgbZdaptSnZskAQbyeOoxGsVj8wqZX7sPrRzskvuPHsgDhM8Bd_v2vy98NFJ1JbaHOIQ0K6oI86fp1I5cSa4Lv0sZ18bucQVrNfH0WWAiC37I3fQe3mGPmoMRyIoI2kgO_esnbkpIBLuzNYBnAvKw2hlCwz7RhSJCR-c52rRZWVoHRMS71RB2FiJLlyJvvGD1HIcQAi6UWKNQ3Nuw")
    @POST("person/attachments")
    fun test(@Query("attachType") attach: String?,@Part("attachment") name: RequestBody?): Call<JsonObject?>?*/

}