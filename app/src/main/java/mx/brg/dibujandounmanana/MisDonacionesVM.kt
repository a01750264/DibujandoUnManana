package mx.brg.dibujandounmanana

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.model.MiDonacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MisDonacionesVM : ViewModel() {
    val arrMisDonaciones = MutableLiveData<List<MiDonacion>>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }


    fun descargarDatosMisDonaciones(token: String) {
        val call = servicioDibujandoApi.verDonaciones("Bearer $token")
        println("Ya se creó Call")
        call.enqueue(object: Callback<List<MiDonacion>> {
            override fun onResponse(call: Call<List<MiDonacion>>, response: Response<List<MiDonacion>>) {
                println("xdddddd")
                if (response.isSuccessful){
                    println("response successful")
                    if (response.code() == 200) {
                        println(response.body())
                        arrMisDonaciones.value = response.body()
                    } else {
                        println("Error: ${response.errorBody()}")
                    }
                } else {
                    if (response.code() == 500) {
                        println("servidor caído")
                        println(response.body())
                    } else if (response.code() == 404) {
                        println("no hay donaciones")
                    } else if (response.code() == 403) {
                        println("no inició sesión")
                    } else {
                        println("ya no sé kpdo xd")
                    }
                }
            }

            override fun onFailure(call: Call<List<MiDonacion>>, t: Throwable) {
                println("Error: ${t.localizedMessage}")
            }
        })
    }
}