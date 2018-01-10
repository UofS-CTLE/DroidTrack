package edu.scranton.ctleweb.droidtrack;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sean on 1/9/18.
 */

interface ProjtrackService {
    @POST("get_auth_token")
    Call<Login> authenticate(@Body String body);
    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") String id);
    @GET("api/projects/{id}")
    Call<Project> getProject(@Path("id") String id);
    @GET("api/clients/{id}")
    Call<Client> getClient(@Path("id") String id);
    @GET("api/departments/{id}")
    Call<Department> getDepartment(@Path("id") String id);
    @GET("api/types/{id}")
    Call<Type> getType(@Path("id") String id);
    @GET("api/semesters/{id}")
    Call<Semester> getSemester(@Path("id") String id);
}
