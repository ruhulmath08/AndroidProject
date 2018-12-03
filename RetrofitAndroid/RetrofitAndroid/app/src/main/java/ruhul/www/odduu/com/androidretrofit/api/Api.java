package ruhul.www.odduu.com.androidretrofit.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> createUser(
            //name should match with API parameter values
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
            );
}
