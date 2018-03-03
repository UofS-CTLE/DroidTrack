package edu.scranton.ctleweb.droidtrack

import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent
import edu.scranton.ctleweb.droidtrack.projtrack.Content
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
                @Header("authenticate") token: String): Call<Content.UserItem>

    @Headers("Content-Type: application/json")
    @GET("api/projects/{id}/")
    fun getProject(@Path("id") id: String,
                   @Header("authenticate") token: String): Call<AllProjectsContent.ProjectItem>

    @Headers("Content-Type: application/json")
    @GET("api/clients/{id}/")
    fun getClient(@Path("id") id: String,
                  @Header("authenticate") token: String): Call<Content.ClientItem>

    @Headers("Content-Type: application/json")
    @GET("api/departments/{id}/")
    fun getDepartment(@Path("id") id: String,
                      @Header("authenticate") token: String): Call<Content.DepartmentItem>

    @Headers("Content-Type: application/json")
    @GET("api/types/{id}/")
    fun getType(@Path("id") id: String,
                @Header("authenticate") token: String): Call<Content.TypeItem>

    @Headers("Content-Type: application/json")
    @GET("api/semesters/{id}/")
    fun getSemester(@Header("authenticate") token: String): Call<Content.SemesterItem>

    // List Functions
    @Headers("Content-Type: application/json")
    @GET("api/users/")
    fun getUsers(@Header("authenticate") token: String): Call<List<Content.UserItem>>

    @Headers("Content-Type: application/json")
    @GET("api/projects/")
    fun getAllProjects(@Header("authenticate") token: String): Call<List<AllProjectsContent.ProjectItem>>

    @Headers("Content-Type: application/json")
    @GET("api/projects?my_projects=true")
    fun getMyProjects(@Header("authenticate") token: String): Call<List<MyProjectsContent.ProjectItem>>

    @Headers("Content-Type: application/json")
    @GET("api/clients/")
    fun getClients(@Header("authenticate") token: String): Call<List<Content.ClientItem>>

    @Headers("Content-Type: application/json")
    @GET("api/departments/")
    fun getDepartments(@Header("authenticate") token: String): Call<List<Content.DepartmentItem>>

    @Headers("Content-Type: application/json")
    @GET("api/types/")
    fun getTypes(@Header("authenticate") token: String): Call<List<Content.TypeItem>>

    @Headers("Content-Type: application/json")
    @GET("api/semesters/")
    fun getSemesters(@Header("authenticate") token: String): Call<List<Content.SemesterItem>>

    @Headers("Content-Type: application/json")
    @GET("api/current_semester/")
    fun getCurrentSemester(@Header("authenticate") token: String): Call<List<Content.CurrentSemesterItem>>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("api/projects/")
    fun addProject(@Header("authenticate") token: String,
                   @Field("title") title: String,
                   @Field("description") description: String,
                   @Field("date") date: String,
                   @Field("type") type: Int,
                   @Field("walk_in") walk_in: Boolean,
                   @Field("client") client: Int,
                   @Field("users") users: List<Content.UserItem>,
                   @Field("semester") semester: Int,
                   @Field("hours") hours: String,
                   @Field("completed") completed: Boolean): Call<Void>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("api/clients/")
    fun addClient(@Header("authenticate") token: String,
                  @Field("first_name") first_name: String,
                  @Field("last_name") last_name: String,
                  @Field("email") email: String,
                  @Field("department") department: Int): Call<Void>
}
