package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Asistenciapa
import pe.edu.upeu.asistenciaupeujc.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestAsistenciapa {
    @GET("/asis/asistenciapa/list")
    suspend fun reportarAsistenciapa(@Header("Authorization") token:String): Response<List<Asistenciapa>>

    @GET("/asis/asistenciapa/buscar/{id}")
    suspend fun getAsistenciapaId(@Header("Authorization") token:String, @Query("id") id:Long): Response<Asistenciapa>

    @DELETE("/asis/asistenciapa/eliminar/{id}")
    suspend fun deleteAsistenciapa(@Header("Authorization") token:String, @Path("id") id:Long): Response<MsgGeneric>

    @PUT("/asis/asistenciapa/editar/{id}")
    suspend fun actualizarAsistenciapa(@Header("Authorization") token:String, @Path("id") id:Long, @Body asistenciapa: Asistenciapa): Response<Asistenciapa>

    @POST("/asis/asistenciapa/crear")
    suspend fun insertarAsistenciapa(@Header("Authorization") token:String, @Body asistenciapa: Asistenciapa): Response<Asistenciapa>
}