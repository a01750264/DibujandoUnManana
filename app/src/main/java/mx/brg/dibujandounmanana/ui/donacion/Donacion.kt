package mx.brg.dibujandounmanana.ui.donacion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton
import kotlinx.android.synthetic.main.activity_donacion.*
import mx.brg.dibujandounmanana.MainActivity
import mx.brg.dibujandounmanana.R
import mx.brg.dibujandounmanana.admin.LoginAdminActivity
import mx.brg.dibujandounmanana.api.ServicioDibujandoApi
import mx.brg.dibujandounmanana.model.DonanteDonacionCampania
import mx.brg.dibujandounmanana.model.DonanteDonacionIniciativa
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Donacion : AppCompatActivity() {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.64:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val servicioDibujandoApi by lazy {
        retrofit.create(ServicioDibujandoApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donacion)
        val payPalButton = findViewById<PayPalButton>(R.id.payPalButton)
        payPalButton.setup(
            createOrder = CreateOrder { createOrderActions ->
                val montoADonarInt = findViewById<EditText>(R.id.etMonto).text.toString().toInt()
                var montoADonarStr = montoADonarInt.toString()
                if (montoADonarStr == "0") {
                    montoADonarStr = "1.00"
                } else {
                    montoADonarStr += ".00"
                }
                val order = Order(
                    intent = OrderIntent.CAPTURE,
                    appContext = AppContext(
                        userAction = UserAction.PAY_NOW
                    ),
                    purchaseUnitList = listOf(
                        PurchaseUnit(
                            amount = Amount(
                                currencyCode = CurrencyCode.MXN,
                                value = montoADonarStr
                            )
                        )
                    )
                )
                createOrderActions.create(order)
            },
            onApprove = OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    Log.i("CaptureOrder","CaptureOrderResult: $captureOrderResult")
                    println("CaptureOrderResult: $captureOrderResult")
                    val preferencias = getSharedPreferences("campaniaSeleccionada", Context.MODE_PRIVATE)
                    val preferenciasUsuario = getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE)
                    val token = preferenciasUsuario.getString("token","none")
                    if(preferencias.getInt("id",-1) == -1)
                    {
                        val preferencias2 = getSharedPreferences("iniciativaSeleccionada", Context.MODE_PRIVATE)
                        if(preferencias2.getInt("id",-1) == -1)
                        {
                            val referencia = "Donativo único"
                            Toast.makeText(this@Donacion.applicationContext,"Donación recibida",Toast.LENGTH_SHORT).show()
                        } else {
                            val referencia = preferencias2.getInt("id", -1)
                            val monto = findViewById<EditText>(R.id.etMonto).text.toString().toFloat()
                            val call = servicioDibujandoApi.donarIniciativa("Bearer $token", DonanteDonacionIniciativa(referencia, monto))
                            call.enqueue(object: Callback<Map<String, String>> {
                                override fun onResponse(
                                    call: Call<Map<String, String>>,
                                    response: Response<Map<String, String>>
                                ) {
                                    if (response.isSuccessful)
                                    {
                                        if (response.code() == 200)
                                        {
                                            Toast.makeText(this@Donacion.applicationContext,"Donación recibida",Toast.LENGTH_SHORT).show()
                                        }
                                    } else {
                                        println("Error: ${response.body()}")
                                    }
                                }
                                override fun onFailure(
                                    call: Call<Map<String, String>>,
                                    t: Throwable
                                ) {
                                    println("Error: ${t.localizedMessage}")
                                }
                            })
                        }
                    } else {
                        val referencia = preferencias.getInt("id", -1)
                        val monto = findViewById<EditText>(R.id.etMonto).text.toString().toFloat()
                        val call = servicioDibujandoApi.donarCampania("Bearer $token", DonanteDonacionCampania(referencia,monto))
                        call.enqueue(object: Callback<Map<String,String>> {
                            override fun onResponse(
                                call: Call<Map<String, String>>,
                                response: Response<Map<String, String>>
                            ) {
                                if (response.isSuccessful)
                                {
                                    if (response.code() == 200)
                                    {
                                        Toast.makeText(this@Donacion.applicationContext,"Donación recibida",Toast.LENGTH_SHORT).show()
                                    }
                                } else
                                {
                                    println("Error: ${response.body()}")
                                }
                            }

                            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                                println("Error: ${t.localizedMessage}")
                            }

                        })
                    }
                }
            },
            onError = OnError { errorInfo ->
                Log.d("On Error", "Error: ${errorInfo}")
            }
        )

    }




}

