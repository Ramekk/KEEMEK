package rus.ramek.project.net.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rus.ramek.project.apimodel.LoginResponseModel;
import rus.ramek.project.net.URL;

/**
 * Created by Ramek on 12/9/2559.
 */
public interface AssetApi {
    @FormUrlEncoded
    @POST(URL.LOGIN)
    Call<LoginResponseModel> login(@Field("username") String username,@Field("password") String password);
}
