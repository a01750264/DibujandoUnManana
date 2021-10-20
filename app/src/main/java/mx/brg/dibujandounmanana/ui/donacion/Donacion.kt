package mx.brg.dibujandounmanana.ui.donacion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
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

class Donacion : AppCompatActivity() {



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
                }
            },
            onError = OnError { errorInfo ->
                Log.d("On Error", "Error: ${errorInfo}")
            }
        )

    }




}

