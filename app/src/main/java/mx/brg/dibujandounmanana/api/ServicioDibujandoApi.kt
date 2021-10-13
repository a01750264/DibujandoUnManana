package mx.brg.dibujandounmanana.api

import mx.brg.dibujandounmanana.model.DonanteLogIn
import mx.brg.dibujandounmanana.model.DonanteToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServicioDibujandoApi
{
    @Headers("Content-Type: application/json")
    @POST("donante/logIn")
    fun logIn(@Body body: DonanteLogIn): Call<DonanteToken>
}