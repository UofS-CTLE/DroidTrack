package edu.scranton.ctleweb.droidtrack;

import java.util.List;

import edu.scranton.ctleweb.droidtrack.projtrack.AllProjectsContent;
import edu.scranton.ctleweb.droidtrack.projtrack.MyProjectsContent;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.GET;

/**
 * Created by sean on 1/9/18.
 */

interface ProjtrackService {
    @FormUrlEncoded
    @POST("get_auth_token/")
    Call<Login> authenticate(@Field("username") String username,
                             @Field("password") String password);
    @Headers("Content-Type: application/json")
    @GET("api/users/{id}/")
    Call<AllProjectsContent.UserItem> getUser(@Path("id") String id,
                                              @Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/projects/{id}/")
    Call<AllProjectsContent.ProjectItem> getProject(@Path("id") String id,
                                                    @Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/clients/{id}/")
    Call<AllProjectsContent.ClientItem> getClient(@Path("id") String id,
                                                  @Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/departments/{id}/")
    Call<AllProjectsContent.DepartmentItem> getDepartment(@Path("id") String id,
                                                          @Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/types/{id}/")
    Call<AllProjectsContent.TypeItem> getType(@Path("id") String id,
                                              @Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/semesters/{id}/")
    Call<AllProjectsContent.SemesterItem> getSemester(@Header("authenticate") String token);

    // List Functions
    @Headers("Content-Type: application/json")
    @GET("api/users/")
    Call<List<AllProjectsContent.UserItem>> getUsers(@Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/all_projects/")
    Call<List<AllProjectsContent.ProjectItem>> getAllProjects(@Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/my_projects/")
    Call<List<MyProjectsContent.ProjectItem>> getMyProjects(@Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/clients/")
    Call<List<AllProjectsContent.ClientItem>> getClients(@Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/departments/")
    Call<List<AllProjectsContent.DepartmentItem>> getDepartments(@Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/types/")
    Call<List<AllProjectsContent.TypeItem>> getTypes(@Header("authenticate") String token);
    @Headers("Content-Type: application/json")
    @GET("api/semesters/")
    Call<List<AllProjectsContent.SemesterItem>> getSemesters(@Header("authenticate") String token);
}
