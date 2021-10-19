package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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