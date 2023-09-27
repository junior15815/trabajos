package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Asistenciapa
import pe.edu.upeu.asistenciaupeujc.repository.AsistenciapaRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciapaFormViewModel @Inject constructor(
    private val asistRepo: AsistenciapaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getAsistenciapa(idX: Long): LiveData<Asistenciapa> {
        return asistRepo.buscarAsistenciapaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addAsistenciapa(asistenciapa: Asistenciapa){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", asistenciapa.toString())
            asistRepo.insertarAsistenciapa(asistenciapa)
        }
    }
    fun editAsistenciapa(asistenciapa: Asistenciapa){
        viewModelScope.launch(Dispatchers.IO){
            try {
                asistRepo.modificarRemoteAsistenciapa(asistenciapa)
            }catch (e:Exception){
                Log.i("ERRRRREDI", "${e.message}")
            }
        }
    }
}