package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Este archivo define los atributos de todas las clases de las iniciativas
 */

data class Iniciativa (
    @SerializedName("nombreIniciativa")
    val nombre: String,
    @SerializedName("descripcionIniciativa")
    val descripcion: String,
    @SerializedName("participantesIniciativa")
    val maxPart: Int
        ): Serializable

data class IniciativaBD (
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("maxParticipantes")
    val maxPart: Int,
    @SerializedName("id")
    val id: Int
): Serializable

data class IniciativaId (
    @SerializedName("iniciativaId")
    val id: Int
        ): Serializable