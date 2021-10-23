package mx.brg.dibujandounmanana

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.model.IniciativaBD
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Este ViewModel trae la información de las Iniciativas desde la base de datos utilizando retrofit.
Esto para mostrarlas al administrador
 */

class ListaIniciativasVM : ViewModel() {
    val arrIniciativas = MutableLiveData<List<IniciativaBD>>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

    fun descargarIniciativas()
    {
        val call = servicioDibujandoApi.verIniciativas()
        call.enqueue(object: Callback<List<IniciativaBD>> {
            override fun onResponse(call: Call<List<IniciativaBD>>, response: Response<List<IniciativaBD>>) {
                if (response.isSuccessful)
                {
                    if (response.code() == 200)
                    {
                        println(response.body())
                        arrIniciativas.value = response.body()
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
            override fun onFailure(call: Call<List<IniciativaBD>>, t: Throwable) {
                println("Error, descargando datos ${t.localizedMessage}")
            }
        })
    }
}