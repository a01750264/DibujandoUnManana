package mx.brg.dibujandounmanana.ui.donacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton
import kotlinx.android.synthetic.main.activity_donacion.*
import mx.brg.dibujandounmanana.R

class Donacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donacion)
        val payPalButton = findViewById<PayPalButton>(R.id.payPalButton)
        payPalButton.setup(
            createOrder = CreateOrder { createOrderActions ->
                val order = Order(
                    intent = OrderIntent.CAPTURE,
                    appContext = AppContext(
                        userAction = UserAction.PAY_NOW
                    ),
                    purchaseUnitList = listOf(
                        PurchaseUnit(
                            amount = Amount(
                                currencyCode = CurrencyCode.MXN,
                                value = "10.00"
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
            }
        )
    }


}

