package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Iniciativa (
    @SerializedName("nombreIniciativa")
    val nombre: String,
    @SerializedName("descripcionIniciativa")
    val descripcion: String,
    @SerializedName("participantesIniciativa")
    val maxPart: Int
        ): Serializable