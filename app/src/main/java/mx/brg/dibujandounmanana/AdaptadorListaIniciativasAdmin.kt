package mx.brg.dibujandounmanana

import android.app.ProgressDialog
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import mx.brg.dibujandounmanana.model.IniciativaBD
import java.io.File

/*
Este adaptador se encarga de dar valor al recycler view que contiene las Iniciativas dadas de alta
en la base de datos para mostrarlas al administrador, también trae la imagen de éstas a través de
Firebase Storage
 */

class AdaptadorListaIniciativasAdmin(var arrIniciativas: ArrayList<IniciativaBD>) :
    RecyclerView.Adapter<AdaptadorListaIniciativasAdmin.ListaIniciativasAdminViewHolder>()
{
    var listener: RenglonListenerIniciativaAdmin? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaIniciativasAdminViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_iniciativa_admin, parent, false)
        return AdaptadorListaIniciativasAdmin.ListaIniciativasAdminViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ListaIniciativasAdminViewHolder, position: Int) {
        holder.set(arrIniciativas[position])
        val vista = holder.itemView
        val btnBorrar = vista.findViewById<Button>(R.id.btnBorrarIniciativa)
        btnBorrar.setOnClickListener {
            println("Borrar iniciativa ${arrIniciativas[position]}")
            listener?.clickEnRenglon(position)
        }

    }

    override fun getItemCount(): Int {
        return arrIniciativas.size
    }

    fun actualizar(lista: List<IniciativaBD>?)
    {
        arrIniciativas.clear()
        if (lista != null)
        {
            arrIniciativas.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class ListaIniciativasAdminViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvTituloIniciativa = vista.findViewById<TextView>(R.id.tvTituloIniciativaAdmin)
        private val ivIniciativa = vista.findViewById<ImageView>(R.id.ivIniciativaAdmin)

        fun set(iniciativa: IniciativaBD)
        {
            val progressDialog = ProgressDialog(itemView.context)
            progressDialog.setMessage("Cargando ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            tvTituloIniciativa.setText(iniciativa.nombre)
            val fileName = "${iniciativa.nombre}${iniciativa.maxPart}"
            println(fileName)
            val storageRef = FirebaseStorage.getInstance().reference.child("iniciativas/$fileName")
            val file = File.createTempFile("tempImage", "jpg")
            storageRef.getFile(file).addOnSuccessListener {

                if (progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }

                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                ivIniciativa.setImageBitmap(bitmap)

            }.addOnFailureListener{
                if (progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }

                Toast.makeText(itemView.context, "No se recuperó la imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

}