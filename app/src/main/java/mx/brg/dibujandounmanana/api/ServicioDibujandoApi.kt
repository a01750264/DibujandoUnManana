package mx.brg.dibujandounmanana.api

import android.content.Context
import android.content.SharedPreferences
import mx.brg.dibujandounmanana.model.DonanteInfo
import mx.brg.dibujandounmanana.model.DonanteLogIn
import mx.brg.dibujandounmanana.model.DonanteToken
import mx.brg.dibujandounmanana.model.MiDonacion
import retrofit2.Call
import retrofit2.http.*

interface ServicioDibujandoApi
{
    val context : Context

    @Headers("Content-Type: application/json")
    @POST("donante/logIn")
    fun logIn(@Body body: DonanteLogIn): Call<DonanteToken>

    //@Headers("Authorization: Bearer")
    @GET("donante/verDonaciones")
    fun verDonaciones(@Header("Authorization") token: String): Call<List<MiDonacion>>

    @GET("donante/verInfoDonante")
    fun verInfoDonante(@Header("Authorization") token: String): Call<DonanteInfo>
}