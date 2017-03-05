package com.application.subitoit.githubstargazers.managers;


import com.application.subitoit.githubstargazers.BuildConfig;
import com.application.subitoit.githubstargazers.services.StargazerService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
 import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static RetrofitManager instance;
    private StargazerService service;
    private final String TAG = "RetrofitManager";

    //dadoz/SelectCardViewPrototype
    // API URL https://api.github.com/repos/{owner}/{repo}/stargazers
    //Object -> avatar and username

    private RetrofitManager() {
        initRetrofit();
    }

    /**
     *
     * @return
     */
    public static RetrofitManager getInstance() {
        return instance == null ? instance = new RetrofitManager() : instance;
    }

    /**
     *
     * @return
     */
    public StargazerService getService() {
        return service;
    }

    /**
     *
     */
    private void initRetrofit() {
        try {
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .addInterceptor(logging)
//                    .build();

            service = new Retrofit.Builder()
                    .baseUrl(BuildConfig.GITHUB_ENDPOINT)
//                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build()
                    .create(StargazerService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public Gson getGson() {
        return new GsonBuilder()
//                .registerTypeAdapter(new TypeToken<ArrayList<Stargazer>>() {}.getType(), new CustomDeserializer())
                .create();
    }

//    private class CustomDeserializer implements JsonDeserializer<Object> {
//        @Override
//        public Object deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
//                throws JsonParseException
//        {
//            Log.i(TAG, je.toString());
//            return new Gson().fromJson(je.getAsJsonObject().get("data"), type);
//
//        }
//    }

}
