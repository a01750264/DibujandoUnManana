package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Esta clase define los atributos de una donación en la pantalla del perfil
 */

data class MiDonacion (
    @SerializedName("title")
    val titulo: String,
    @SerializedName("quantity")
    val cantidad: Int,
    @SerializedName("date")
    val fecha: String
        ): Serializable