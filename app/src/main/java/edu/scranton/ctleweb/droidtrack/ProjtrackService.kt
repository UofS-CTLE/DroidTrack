package edu.scranton.ctleweb.droidtrack

import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent
import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent
import retrofit2.Call
import retrofit2.http.*

internal interface ProjtrackService {
    @FormUrlEncoded
    @POST("get_auth_token/")
    fun authenticate(@Field("username") username: String?,
                     @Field("password") password: String?): Call<Login>

    @Headers("Content-Type: application/json")
    @GET("api/users/{id}/")
    fun getUser(@Path("id") id: String,
                @Header("authenticate") token: String): Call<AllProjectsContent.UserItem>

    @Headers("Content-Type: application/json")
    @GET("api/projects/{id}/")
    fun getProject(@Path("id") id: String,
                   @Header("authenticate") token: String): Call<AllProjectsContent.ProjectItem>

    @Headers("Content-Type: application/json")
    @GET("api/clients/{id}/")
    fun getClient(@Path("id") id: String,
                  @Header("authenticate") token: String): Call<AllProjectsContent.ClientItem>

    @Headers("Content-Type: application/json")
    @GET("api/departments/{id}/")
    fun getDepartment(@Path("id") id: String,
                      @Header("authenticate") token: String): Call<AllProjectsContent.DepartmentItem>

    @Headers("Content-Type: application/json")
    @GET("api/types/{id}/")
    fun getType(@Path("id") id: String,
                @Header("authenticate") token: String): Call<AllProjectsContent.TypeItem>

    @Headers("Content-Type: application/json")
    @GET("api/semesters/{id}/")
    fun getSemester(@Header("authenticate") token: String): Call<AllProjectsContent.SemesterItem>

    // List Functions
    @Headers("Content-Type: application/json")
    @GET("api/users/")
    fun getUsers(@Header("authenticate") token: String): Call<List<AllProjectsContent.UserItem>>

    @Headers("Content-Type: application/json")
    @GET("api/projects/")
    fun getAllProjects(@Header("authenticate") token: String): Call<List<AllProjectsContent.ProjectItem>>

    @Headers("Content-Type: application/json")
    @GET("api/projects?my_projects=true")
    fun getMyProjects(@Header("authenticate") token: String): Call<List<MyProjectsContent.ProjectItem>>

    @Headers("Content-Type: application/json")
    @GET("api/clients/")
    fun getClients(@Header("authenticate") token: String): Call<List<AllProjectsContent.ClientItem>>

    @Headers("Content-Type: application/json")
    @GET("api/departments/")
    fun getDepartments(@Header("authenticate") token: String): Call<List<AllProjectsContent.DepartmentItem>>

    @Headers("Content-Type: application/json")
    @GET("api/types/")
    fun getTypes(@Header("authenticate") token: String): Call<List<AllProjectsContent.TypeItem>>

    @Headers("Content-Type: application/json")
    @GET("api/semesters/")
    fun getSemesters(@Header("authenticate") token: String): Call<List<AllProjectsContent.SemesterItem>>
}
