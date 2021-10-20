package mx.brg.dibujandounmanana

import android.app.ProgressDialog
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.storage.FirebaseStorage
import mx.brg.dibujandounmanana.model.CampaniaBD
import java.io.File


class AdaptadorListaCampanias (var arrCampanias: ArrayList<CampaniaBD>) :
    RecyclerView.Adapter<AdaptadorListaCampanias.ListaCampaniasViewHolder>()
{
    var listener: RenglonListenerCampanias? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaCampaniasViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.renglon_campania, parent, false)
        return ListaCampaniasViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ListaCampaniasViewHolder, position: Int) {
        holder.set(arrCampanias[position])
        val vista = holder.itemView
        val btnVerMas = vista.findViewById<Button>(R.id.btnVerMasIniciativa)
        val btnDonar = vista.findViewById<Button>(R.id.btnDonarIniciativa)
        btnVerMas.setOnClickListener {
            println("Click en ver más de ${arrCampanias[position]}")
            listener?.clickEnRenglon(position)
        }

        btnDonar.setOnClickListener {
            println("Click en donar de ${arrCampanias[position]}")
            listener?.clickEnRenglon(position)
        }

    }

    override fun getItemCount(): Int {
        return arrCampanias.size
    }

    fun actualizar(lista: List<CampaniaBD>?)
    {
        arrCampanias.clear()
        if (lista != null)
        {
            arrCampanias.addAll(lista)
        }
        notifyDataSetChanged()
    }

    class ListaCampaniasViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        private val tvTituloCampania = vista.findViewById<TextView>(R.id.tvTituloCampania)
        private val ivCampania = vista.findViewById<ImageView>(R.id.ivCampania)

        fun set(campania: CampaniaBD)
        {
            val progressDialog = ProgressDialog(itemView.context)
            progressDialog.setMessage("Cargando ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            tvTituloCampania.setText(campania.nombre)
            val fileName = campania.nombre
            println(fileName)
            val storageRef = FirebaseStorage.getInstance().reference.child("campanias/$fileName")
            val file = File.createTempFile("tempImage", "jpg")
            storageRef.getFile(file).addOnSuccessListener {

                if (progressDialog.isShowing)
                {
                    progressDialog.dismiss()
                }

                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                ivCampania.setImageBitmap(bitmap)

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