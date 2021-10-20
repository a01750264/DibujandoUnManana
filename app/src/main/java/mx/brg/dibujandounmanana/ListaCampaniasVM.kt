package mx.brg.dibujandounmanana

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.model.CampaniaBD
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaCampaniasVM : ViewModel() {
    val arrCampanias = MutableLiveData<List<CampaniaBD>>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

    fun descargarCampanias()
    {
        val call = servicioDibujandoApi.verCampanias()
        call.enqueue(object: Callback<List<CampaniaBD>> {
            override fun onResponse(call: Call<List<CampaniaBD>>, response: Response<List<CampaniaBD>>) {
                if (response.isSuccessful)
                {
                    if (response.code() == 200)
                    {
                        println(response.body())
                        arrCampanias.value = response.body()
                    } else {
                        println("Error")
                    }
                } else {
                    if (response.code()==404)
                    {
                        println("No existen campañas")
                    } else if (response.code() == 500)
                    {
                        println("Servidor caído")
                    } else {
                        println(response.errorBody())
                    }
                }
            }
            override fun onFailure(call: Call<List<CampaniaBD>>, t: Throwable) {
                println("Error, descargando datos ${t.localizedMessage}")
            }
        })
    }
}