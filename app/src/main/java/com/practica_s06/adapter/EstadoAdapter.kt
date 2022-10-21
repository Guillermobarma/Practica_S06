package com.practica_s06.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.practica_s06.databinding.EstadoFilaBinding
import com.practica_s06.model.Estado
import com.practica_s06.ui.estado.EstadoFragmentDirections


class EstadoAdapter : RecyclerView.Adapter<EstadoAdapter.EstadoViewHolder>(){

    private var listaEstados= emptyList<Estado>()

    inner class EstadoViewHolder(private val itemBinding: EstadoFilaBinding):
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(estado: Estado){
            itemBinding.tvNombre.text = estado.nombre
            itemBinding.tvCapital.text = estado.capital
            itemBinding.tvPoblacion.text =estado.poblacion.toString()
            itemBinding.tvCostas.text = estado.costas.toString()
            itemBinding.vistaFila.setOnClickListener{
                val accion = EstadoFragmentDirections
                    .actionNavEstadoToUpdateEstadoFragment(estado)
                itemView.findNavController().navigate(accion)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstadoViewHolder {
        //función para crear la card de cada lugar vista:fila
        val itemBinding =
            EstadoFilaBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return EstadoViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EstadoViewHolder, position: Int) {
        //obtengo el objeto por el que voy
        val estadoActual = listaEstados[position]
        //llamo a la función imprime la información
        holder.bind(estadoActual)
    }

    override fun getItemCount(): Int {
        return listaEstados.size
    }

    fun setData(estados:List<Estado>){
        this.listaEstados=estados
        //vuelve a cargar la lista de cards
        notifyDataSetChanged()
    }

}