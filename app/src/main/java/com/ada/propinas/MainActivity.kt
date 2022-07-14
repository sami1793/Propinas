package com.ada.propinas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ada.propinas.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonCalcular.setOnClickListener { calcularPropina() }
    }

    fun calcularPropina() {
        val stringEnCampoTexto = binding.costoDeServicio.text.toString()
        //Por si se ingresa un valor nulo no falle
        val costo = stringEnCampoTexto.toDoubleOrNull()
        if (costo == null) {
            binding.resultadoPropina.text=""
            return
        }
        val selectedID = binding.opcionesPropina.checkedRadioButtonId

        val porcentajePropina = when (selectedID) {
            R.id.opcion_quince_porciento -> 0.15
            R.id.opcion_dieciocho_porciento -> 0.18
            else -> 0.20
        }
        var propina = costo * porcentajePropina

        //PARTE DEL SWITCH
        var redondear = binding.switchRedondeo.isChecked
        if (redondear) {
            propina = kotlin.math.ceil(propina)
        }
        val propinaFormateda = NumberFormat.getCurrencyInstance().format(propina)

        binding.resultadoPropina.text = getString(R.string.monto_propina, propinaFormateda)
    }
}