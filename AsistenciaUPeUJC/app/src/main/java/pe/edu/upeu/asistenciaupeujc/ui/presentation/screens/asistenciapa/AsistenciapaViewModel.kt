package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Asistenciapa
import pe.edu.upeu.asistenciaupeujc.modelo.AsistenciapaConActividad
import pe.edu.upeu.asistenciaupeujc.repository.AsistenciapaRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciapaViewModel @Inject constructor(
    private val asistRepo: AsistenciapaRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<AsistenciapaConActividad>> by lazy {
        asistRepo.reportarAsistenciapas()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addAsistenciapa() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteAsistenciapa(toDelete: AsistenciapaConActividad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            asistRepo.deleteAsistenciapa(toDelete);
        }
    }

}