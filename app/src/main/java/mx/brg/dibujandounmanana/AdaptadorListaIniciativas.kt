package mx.brg.dibujandounmanana

import android.app.ProgressDialog
import android.content.Intent
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

class AdaptadorListaIniciativas(var arrIniciativas: ArrayList<IniciativaBD>) :
    RecyclerView.Adapter<AdaptadorListaIniciativas.ListaIniciativasViewHolder>()
{
    var listener: RenglonListenerIniciativa? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaIniciativasViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_iniciativa, parent, false)
        return AdaptadorListaIniciativas.ListaIniciativasViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ListaIniciativasViewHolder, position: Int) {
        holder.set(arrIniciativas[position])
        val vista = holder.itemView
        val btnVerMas = vista.findViewById<Button>(R.id.btnVerMasIniciativa)
        btnVerMas.setOnClickListener {
            println("Click en ver más de ${arrIniciativas[position]}")
            listener?.clickEnRenglon(position)
            val intent = Intent(holder.itemView.context, IniciativaSeleccionada::class.java)
            holder.itemView.context.startActivity(intent)
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

    class ListaIniciativasViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvTituloIniciativa = vista.findViewById<TextView>(R.id.tvTituloIniciativa)
        private val ivIniciativa = vista.findViewById<ImageView>(R.id.ivIniciativa)

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