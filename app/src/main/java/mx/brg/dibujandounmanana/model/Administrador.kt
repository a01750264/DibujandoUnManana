package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Esta archivo define los atributos de las clases login del administrador y de su json web token de sesión
 */

data class AdminLogIn (
    @SerializedName("adminEmail")
    val email: String,
    @SerializedName("adminPass")
    val password: String
): Serializable

data class AdminToken (
    @SerializedName("token")
    val token: String
        ): Serializable