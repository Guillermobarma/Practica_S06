package com.practica_s06.ui.estado

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.practica_s06.R
import com.practica_s06.databinding.FragmentUpdateEstadoBinding
import com.practica_s06.model.Estado
import com.practica_s06.viewmodel.EstadoViewModel
import kotlin.math.cos

class UpdateEstadoFragment:Fragment() {

    private var _binding: FragmentUpdateEstadoBinding? = null
    private val binding get() = _binding!!
    private  lateinit var estadoViewModel: EstadoViewModel
    private val args by navArgs<UpdateEstadoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        estadoViewModel =
            ViewModelProvider(this).get(EstadoViewModel::class.java)

        _binding = FragmentUpdateEstadoBinding.inflate(inflater, container, false)

        binding.etNombre.setText(args.estado.nombre)
        binding.etCapital.setText(args.estado.capital)
        binding.etPoblacion.setText(args.estado.poblacion.toString())
        binding.etCostas.setText(args.estado.costas.toString())

        binding.btUpdateEstado.setOnClickListener { updateEstado() }
        binding.btDeleteEstado.setOnClickListener { deleteLugar() }

        return binding.root
    }

    private fun updateEstado() {
        val nombre=binding.etNombre.text.toString()
        if(nombre.isNotEmpty()){ //si no está vacío se agrega
            val capital=binding.etCapital.text.toString()
            val costas=binding.etCostas.text.toString().toInt()
            val poblacion=binding.etPoblacion.text.toString().toInt()

            val estado= Estado(args.estado.id,nombre,capital, poblacion, costas)

            estadoViewModel.saveEstado(estado)
            Toast.makeText(requireContext(),getString(R.string.msg_estado_updated),
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_estado)
        }
        else{
            Toast.makeText(requireContext(),getString(R.string.msg_data),
                Toast.LENGTH_SHORT).show()
        }
    }
    private fun deleteLugar() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(R.string.bt_delete_estado)
        builder.setMessage(getString(R.string.msg_seguro_borrado)+ " ${args.estado.nombre}?")
        builder.setNegativeButton(getString(R.string.msg_no)){_,_-> }
        builder.setPositiveButton(getString(R.string.msg_si)){_,_->
            estadoViewModel.deleteLugar(args.estado)
            Toast.makeText(requireContext(), getString(R.string.msg_estado_updated),Toast.LENGTH_SHORT)
            findNavController().navigate(R.id.action_updateEstadoFragment_to_nav_estado)
        }
        builder.create().show()
    }


}