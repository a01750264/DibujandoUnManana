package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Campania (
    @SerializedName("nombreDonativo")
    val nombre: String,
    @SerializedName("descripcionDonativo")
    val descripcion: String,
        ): Serializable

data class CampaniaBD (
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
): Serializable