package com.curso.proyectofinal.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curso.proyectofinal.model.Modelo
import kotlinx.coroutines.launch
class MainViewModel : ViewModel() {

    private var _modelo = MutableLiveData<Modelo>()
    val modelo: LiveData<Modelo> get() = _modelo

//    init {
//        _modelo.value = modelo
//    }

    fun comparar(cadena1 : String, cadena2 : String) {

        if ( cadena1 == cadena2) {
            actualizarResultado("iguales")
        } else {
            actualizarResultado("diferentes")
        }
        //actualizarResultado()


        //Log.i("Comparar","Boton pulsado")
    }

    private fun actualizarResultado (resultado: String){

        viewModelScope.launch {
                _modelo.value = Modelo(resultado) }

    }

}

