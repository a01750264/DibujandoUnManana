package mx.brg.dibujandounmanana.api

import android.content.Context
import android.content.SharedPreferences
import mx.brg.dibujandounmanana.model.*
import retrofit2.Call
import retrofit2.http.*

interface ServicioDibujandoApi
{
    val context : Context

    @Headers("Content-Type: application/json")
    @POST("donante/logIn")
    fun logIn(@Body body: DonanteLogIn): Call<DonanteToken>

    @Headers("Content-Type: application/json")
    @POST("administrador/logIn")
    fun logInAdmin(@Body body: AdminLogIn): Call<AdminToken>

    @POST("iniciativa/crearIniciativa")
    fun crearIniciativa(@Header("Authorization") token: String,
                        @Body body: Iniciativa): Call<Map<String, String>>

    @POST("donativo/crearDonativo")
    fun crearCampania(@Header("Authorization") token: String,
                      @Body body: Campania): Call<Map<String, String>>

    @POST("donativo/verDonativo")
    fun verCampania(@Body body: CampaniaId): Call<CampaniaBD>

    @POST("iniciativa/verIniciativa")
    fun verIniciativa(@Body body: IniciativaId): Call<IniciativaBD>

    @POST("donante/donarDonativo")
    fun donarCampania(@Header("Authorization") token: String,
                      @Body body: DonanteDonacionCampania): Call<Map<String, String>>

    @POST("donante/donar")
    fun donarIniciativa(@Header("Authorization") token: String,
                        @Body body: DonanteDonacionIniciativa): Call<Map<String, String>>

    //@Headers("Authorization: Bearer")
    @GET("donante/verDonaciones")
    fun verDonaciones(@Header("Authorization") token: String): Call<List<MiDonacion>>

    @GET("donante/verInfoDonante")
    fun verInfoDonante(@Header("Authorization") token: String): Call<DonanteInfo>

    @GET("donativo/verDonativos")
    fun verCampanias(): Call<List<CampaniaBD>>

    @GET("iniciativa/verIniciativas")
    fun verIniciativas(): Call<List<IniciativaBD>>
}