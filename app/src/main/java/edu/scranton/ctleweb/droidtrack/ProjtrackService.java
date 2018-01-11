package edu.scranton.ctleweb.droidtrack;

import java.util.List;

import edu.scranton.ctleweb.droidtrack.projtrack.ProjectContent;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by sean on 1/9/18.
 */

interface ProjtrackService {
    @FormUrlEncoded
    @POST("get_auth_token/")
    Call<Login> authenticate(@Field("username") String username, @Field("password") String password);
    @FormUrlEncoded
    @POST("api/users/")
    Call<ProjectContent.UserItem> getUser(@Field("id") String id, @Header("authenticate") String token);
    @FormUrlEncoded
    @POST("api/projects/")
    Call<ProjectContent.ProjectItem> getProject(@Field("id") String id, @Header("authenticate") String token);
    @FormUrlEncoded
    @POST("api/clients/")
    Call<ProjectContent.ClientItem> getClient(@Field("id") String id, @Header("authenticate") String token);
    @FormUrlEncoded
    @POST("api/departments/")
    Call<ProjectContent.DepartmentItem> getDepartment(@Field("id") String id, @Header("authenticate") String token);
    @FormUrlEncoded
    @POST("api/types/")
    Call<ProjectContent.TypeItem> getType(@Field("id") String id, @Header("authenticate") String token);
    @FormUrlEncoded
    @POST("api/semesters/")
    Call<ProjectContent.SemesterItem> getSemester(@Header("authenticate") String token);

    // List Functions
    @POST("api/users/")
    Call<List<ProjectContent.UserItem>> getUsers(@Header("authenticate") String token);
    @POST("api/projects/")
    Call<List<ProjectContent.ProjectItem>> getProjects(@Header("authenticate") String token);
    @POST("api/clients/")
    Call<List<ProjectContent.ClientItem>> getClients(@Header("authenticate") String token);
    @POST("api/departments/")
    Call<List<ProjectContent.DepartmentItem>> getDepartments(@Header("authenticate") String token);
    @POST("api/types/")
    Call<List<ProjectContent.TypeItem>> getTypes(@Header("authenticate") String token);
    @POST("api/semesters/")
    Call<List<ProjectContent.SemesterItem>> getSemesters(@Field("id") String id, @Header("authenticate") String token);
}
