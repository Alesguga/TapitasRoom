package net.azarquiel.tapitasroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.tapitasroom.R
import net.azarquiel.tapitasroom.model.TapaWithEstablecimiento

/**
 * Created by pacopulido 
 */
class AdapterTapa(val context: Context,
                  val layout: Int
                    ) : RecyclerView.Adapter<AdapterTapa.ViewHolder>() {

    private var dataList: List<TapaWithEstablecimiento> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setTapas(tapas: List<TapaWithEstablecimiento>) {
        this.dataList = tapas
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: TapaWithEstablecimiento){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivrowtapa = itemView.findViewById(R.id.ivrowtapa) as ImageView
            val tvnombrerowtapa = itemView.findViewById(R.id.tvnombrerowtapa) as TextView
            val tvestablecimientorowtapa = itemView.findViewById(R.id.tvestablecimientorowtapa) as TextView
            val tvdescripcionrowtapa = itemView.findViewById(R.id.tvdescripcionrowtapa) as TextView

            tvnombrerowtapa.text = dataItem.tapa.nombre
            tvestablecimientorowtapa.text = "${dataItem.establecimiento.nombre}"
            tvdescripcionrowtapa.text = dataItem.tapa.descripcion

            // foto de internet a traves de Picasso
            Picasso.get().load("http://82.223.108.85/storage/${dataItem.tapa.url_imagen}").into(ivrowtapa)

            itemView.tag = dataItem

        }

    }
}