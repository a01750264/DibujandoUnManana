package mx.brg.dibujandounmanana.ui

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction
import mx.brg.dibujandounmanana.BuildConfig

/*
    En esta clase se encuentra la información que recibe PayPal para saber acerca del tipo de
    transacción.
 */
class AppPago : Application() {
    private val CLIENT_ID: String = "AQPTxSXoOQrO3ec69tWA0h5TJkngyQQUQIIKDTs1_Tvy_9PgZXP9TV82yPbmar8eCmEv5lyFHS-C5r5H"

    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = CLIENT_ID,
            environment = Environment.SANDBOX,
            returnUrl = "${BuildConfig.APPLICATION_ID}://paypalpay",
            currencyCode = CurrencyCode.MXN,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)
    }
}