package edu.scranton.ctleweb.droidtrack;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sean on 1/9/18.
 */

interface ProjtrackService {
    @POST("get_auth_token/{username}/{password}")
    Call<Login> authenticate(@Path("username") String email, @Path("password") String password);
    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);
    @GET("projects/{id}")
    Call<Project> getProject(@Path("id") String id);
    @GET("clients/{id}")
    Call<Client> getClient(@Path("id") String id);
    @GET("departments/{id}")
    Call<Department> getDepartment(@Path("id") String id);
    @GET("types/{id}")
    Call<Type> getType(@Path("id") String id);
    @GET("semesters/{id}")
    Call<Semester> getSemester(@Path("id") String id);
}
