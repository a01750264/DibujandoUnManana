package mx.brg.dibujandounmanana.api

import android.content.Context
import android.content.SharedPreferences
import mx.brg.dibujandounmanana.model.DonanteLogIn
import mx.brg.dibujandounmanana.model.DonanteToken
import mx.brg.dibujandounmanana.model.MiDonacion
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServicioDibujandoApi
{
    val context : Context

    @Headers("Content-Type: application/json")
    @POST("donante/logIn")
    fun logIn(@Body body: DonanteLogIn): Call<DonanteToken>

    @Headers("Authorization: Bearer")
    @GET("donante/verDonaciones")
    fun verDonaciones(): Call<List<MiDonacion>>

}