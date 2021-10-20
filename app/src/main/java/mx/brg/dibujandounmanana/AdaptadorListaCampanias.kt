package mx.brg.dibujandounmanana

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.brg.dibujandounmanana.model.CampaniaBD


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
        val btnVerMas = vista.findViewById<Button>(R.id.btnVerMasCampania)
        val btnDonar = vista.findViewById<Button>(R.id.btnDonarCapania)
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

        fun set(campania: CampaniaBD)
        {
            tvTituloCampania.setText(campania.nombre)
            val fileName = "${campania.nombre}${campania.maxPart}"
        }
    }

}