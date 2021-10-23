package mx.brg.dibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Este archivo define los atributos para las clases de las propuestas de un donante
 */

data class Propuesta (
    @SerializedName("propuestaTitulo")
    val titulo: String,
    @SerializedName("propuestaDescripcion")
    val descripcion: String
        ): Serializable

data class PropuestaBD (
    @SerializedName("id")
    var id: Int,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("descripcion")
    val descripcion: String,
        ): Serializable

data class PropuestaId (
    @SerializedName("id")
    var id: Int
        ): Serializable