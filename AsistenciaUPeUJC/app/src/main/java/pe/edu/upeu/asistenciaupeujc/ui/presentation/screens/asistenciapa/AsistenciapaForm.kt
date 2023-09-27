package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.asistenciapa

import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Asistenciapa
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.actividad.AsistenciapaFormViewModel


@Composable
fun AsistenciapaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: AsistenciapaFormViewModel = hiltViewModel()
) {
    val asistenciapaD: Asistenciapa
    if (text!="0"){
        asistenciapaD = Gson().fromJson(text, Asistenciapa::class.java)
    }else{
        asistenciapaD= Asistenciapa(0,"","", "","","","","","","","", 0, "")
    }

    formulario(asistenciapaD.id!!,
        darkMode,
        navController,
        asistenciapaD,
        viewModel
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               asistenciapa: Asistenciapa,
               viewModel: AsistenciapaFormViewModel){

    Log.i("VERRR", "d: "+asistenciapa?.id!!)
    val person= Asistenciapa(0,"","", "","","","","","","","", 0, "")
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isLoading by viewModel.isLoading.observeAsState(false)
    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        context)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                Log.e("LATLONX", "Lat: ${lo.latitude} Lon: ${lo.longitude}")
                person.latituda=lo.latitude.toString()
                person.longituda=lo.longitude.toString()
            }
        }
    }
    scope.launch{
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        Log.e("LATLON", "Lat: ${person.latituda} Lon: ${person.longituda}")
        delay(1500L)
        if (fusedLocationClient != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback);
            fusedLocationClient = null;
        }

    }

    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                /*   NameTextField(easyForms = easyForm, text =actividad?.nombreActividad!!,"Nomb. Actividad:", MyFormKeys.NAME )*/
                var listE = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )

                ComboBox(easyForm = easyForm, "offlinex:", asistenciapa?.offlinex!!, listE)
                NameTextField(easyForms = easyForm, text =asistenciapa?.cui!!,"Nomb. Cui:", MyFormKeys.CUI )
                NameTextField(easyForms = easyForm, text =asistenciapa?.tipoCui!!,"Nomb. tipoCui:", MyFormKeys.TIPOCUI )
                NameTextField(easyForms = easyForm, text =asistenciapa?.cui!!,"cui:", MyFormKeys.MATERENTRE )
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = asistenciapa?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")

                TimePickerCustom(easyForm = easyForm, label = "hora_reg", texts = asistenciapa?.horaReg!!, MyFormKeys.HORAREG, "HH:mm:ss")

                NameTextField(easyForms = easyForm, text =asistenciapa?.actividadId!!.toString(),"actividadID:", MyFormKeys.ACTIVIDADID )




                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.offlinex=splitCadena((lista.get(0) as EasyFormsResult.GenericStateResult<String>).value)
                        person.cui=(lista.get(1) as EasyFormsResult.StringResult).value
                        person.tipoCui=(lista.get(2) as EasyFormsResult.StringResult).value
                        person.entsal=(lista.get(3) as EasyFormsResult.StringResult).value
                        person.fecha=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.horaReg=(lista.get(5) as EasyFormsResult.GenericStateResult<String>).value
                        person.calificacion=(lista.get(6) as EasyFormsResult.GenericStateResult<String>).value
                        person.actividadId = (lista.get(7) as EasyFormsResult.StringResult).value.toLong()


                        if (id==0.toLong()){

                            Log.i("AGREGAR", "OF:"+ person.offlinex)
                            Log.i("AGREGARID", "OF:"+ person.actividadId)

                            viewModel.addAsistenciapa(person)

                            navController.navigate(Destinations.AsistenciapaUI.route)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editAsistenciapa(person)
                            navController.navigate(Destinations.AsistenciapaUI.route)
                        }

                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.AsistenciapaUI.route)
                    }
                }
            }
        }
    }

}


fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}