package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DonanteToken (
    @SerializedName("token")
    val token: String
    ): Serializable

class DonanteLogIn(
    donanteData: String,
    donantePassword: String
)