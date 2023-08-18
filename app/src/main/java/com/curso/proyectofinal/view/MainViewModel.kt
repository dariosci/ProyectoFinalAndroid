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
        if ((cadena1==""||cadena2=="")){ //SI ALGUNA DE LAS CADENAS ESTÁN VACIAS DEVUELVE UNA CADENA VACIA
            actualizarResultado("")
        }else { //SI LAS DOS CADENAS TIENEN TEXTO LAS COMPARA Y DEVUELVE EL RESULTADO COMO CADENA
            if (cadena1 == cadena2) {
                actualizarResultado("iguales")
            } else {
                actualizarResultado("diferentes")
            }
        }
    }

    private fun actualizarResultado (resultado: String){ //DEVUELVE EL RESULTADO AL MAINACTIVITY
        viewModelScope.launch {
                _modelo.value = Modelo(resultado) }

    }

}

