package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DonanteToken (
    @SerializedName("token")
    val token: String
    ): Serializable

data class DonanteLogIn(
    @SerializedName("donanteData")
    val donanteData: String,
    @SerializedName("donantePassword")
    val donantePassword: String
): Serializable

data class DonanteInfo(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellidoP")
    val apellidoP: String,
    @SerializedName("apellidoM")
    val apellidoM: String,
    @SerializedName("fecha_registro")
    val fecha: String
): Serializable