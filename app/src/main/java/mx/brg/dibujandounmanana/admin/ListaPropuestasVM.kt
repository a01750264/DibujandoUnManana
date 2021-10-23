package mx.brg.dibujandounmanana.admin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.model.PropuestaBD
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Este ViewModel trae la información de las Propuestas desde la base de datos utilizando retrofit.
Esto para mostrarlas al administrador
 */

class ListaPropuestasVM : ViewModel() {
    val arrPropuestas = MutableLiveData<List<PropuestaBD>>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

    fun descargarPropuestas(token: String)
    {
        val call = servicioDibujandoApi.verPropuestas("Bearer $token")
        call.enqueue(object: Callback<List<PropuestaBD>> {
            override fun onResponse(call: Call<List<PropuestaBD>>, response: Response<List<PropuestaBD>>) {
                if (response.isSuccessful)
                {
                    if (response.code() == 200)
                    {
                        println(response.body())
                        arrPropuestas.value = response.body()
                    } else {
                        println("Error")
                    }
                } else {
                    if (response.code()==404)
                    {
                        println("No existen propuestas")
                    } else if (response.code() == 500)
                    {
                        println("Servidor caído")
                    } else {
                        println(response.errorBody())
                    }
                }
            }
            override fun onFailure(call: Call<List<PropuestaBD>>, t: Throwable) {
                println("Error, descargando datos ${t.localizedMessage}")
            }
        })
    }
}