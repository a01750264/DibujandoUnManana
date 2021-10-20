package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Campania (
    @SerializedName("nombreIniciativa")
    val nombre: String,
    @SerializedName("descripcionIniciativa")
    val descripcion: String,
    @SerializedName("participantesIniciativa")
    val maxPart: Int
        ): Serializable

data class CampaniaBD (
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("maxParticipantes")
    val maxPart: Int
): Serializable