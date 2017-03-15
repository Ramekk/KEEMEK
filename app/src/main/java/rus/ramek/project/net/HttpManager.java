package rus.ramek.project.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rus.ramek.project.net.api.AssetApi;

/**
 * Created by Ramek on 12/9/2559.
 */
public class HttpManager {
    private static HttpManager INSTANCE;
    private final AssetApi service;

    public static HttpManager getInstance(){
        if(INSTANCE == null)
            INSTANCE = new HttpManager();
        return INSTANCE;
    }

    private HttpManager(){
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(AssetApi.class);
    }

    public AssetApi getService(){
        return this.service;
    }
}
