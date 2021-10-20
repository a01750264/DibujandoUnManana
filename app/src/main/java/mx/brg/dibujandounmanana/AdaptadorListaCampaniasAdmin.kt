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
import mx.brg.dibujandounmanana.model.CampaniaBD
import java.io.File

class AdaptadorListaCampaniasAdmin(var arrCampanias: ArrayList<CampaniaBD>) :
    RecyclerView.Adapter<AdaptadorListaCampaniasAdmin.ListaCampaniasAdminViewHolder>() {
    var listener: RenglonListenerCampaniasAdmin? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaCampaniasAdminViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.renglon_campania_admin, parent, false)
        return ListaCampaniasAdminViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ListaCampaniasAdminViewHolder, position: Int) {
        holder.set(arrCampanias[position])
        val vista = holder.itemView
        val btnBorrar = vista.findViewById<Button>(R.id.btnBorrarCampania)

        btnBorrar.setOnClickListener {
            println("Borrar campania ${arrCampanias[position]}")
        }

    }

    override fun getItemCount(): Int {
        return arrCampanias.size
    }

    fun actualizar(lista: List<CampaniaBD>?) {
        arrCampanias.clear()
        if (lista != null) {
            arrCampanias.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class ListaCampaniasAdminViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvTituloCampania = vista.findViewById<TextView>(R.id.tvTituloCampaniaAdmin)
        private val ivCampania = vista.findViewById<ImageView>(R.id.ivCampaniaAdmin)

        fun set(campania: CampaniaBD) {
            val progressDialog = ProgressDialog(itemView.context)
            progressDialog.setMessage("Cargando ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            tvTituloCampania.text = campania.nombre
            val fileName = campania.nombre
            println(fileName)
            val storageRef = FirebaseStorage.getInstance().reference.child("campanias/$fileName")
            val file = File.createTempFile("tempImage", "jpg")
            storageRef.getFile(file).addOnSuccessListener {

                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }

                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                ivCampania.setImageBitmap(bitmap)

            }.addOnFailureListener {
                if (progressDialog.isShowing) {
                    progressDialog.dismiss()
                }

                Toast.makeText(itemView.context, "No se recuperó la imagen", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
