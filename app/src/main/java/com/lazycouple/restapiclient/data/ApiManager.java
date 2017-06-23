package com.lazycouple.restapiclient.data;

import com.amuyu.logger.Logger;
import com.lazycouple.restapiclient.network.api.RestApiService;
import com.lazycouple.restapiclient.network.api.response.RepositoryResponse;
import com.lazycouple.restapiclient.ui.data.User;
import com.lazycouple.restapiclient.util.Utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by noco on 2016-10-12.
 */
public class ApiManager {
    private final String TAG = ApiManager.class.getSimpleName();
    private RestApiService restApiService;

    public ApiManager(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    public Observable<User> getUser(String username) {
        return restApiService.getUser(username)
                .map(userResponse -> {
                    Logger.d("");
                    User user = new User();
                    user.login = userResponse.login;
                    user.id = userResponse.id;
                    user.url = userResponse.url;
                    user.email = userResponse.email;
                    return user;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<RepositoryResponse> getUsersRepositories(String username) {
        return restApiService.getUsersRepositories(username)
                .flatMap(repos-> Observable.from(repos))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Observable<Response<ResponseBody>> getUserResponse(String username) {
        return restApiService.getUserResponse(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Response<ResponseBody>> callApi(String url, Map<String,String> parameter, Map<String, String> headerMap) {
        Interceptor interceptor = null;
        if(headerMap != null) interceptor = new HeaderInterceptors(headerMap);
        RestApiService restApiService = getRetrofit(Utils.getBaseUrl(url), interceptor)
                .create(RestApiService.class);
        return restApiService.callApi(Utils.getPath(url), parameter)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response<ResponseBody>> callApiPost(String url, RequestBody body, Map<String, String> headerMap) {
        Interceptor interceptor = null;
        if(headerMap != null) interceptor = new HeaderInterceptors(headerMap);
        RestApiService restApiService = getRetrofit(Utils.getBaseUrl(url), interceptor)
                .create(RestApiService.class);
        return restApiService.callApiPost(Utils.getPath(url), body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    private Retrofit getRetrofit(String baseUrl) {
        return getRetrofit(baseUrl, null);
    }

    private Retrofit getRetrofit(String baseUrl, Interceptor interceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        if(interceptor != null) httpClient.addInterceptor(interceptor);

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public class HeaderInterceptors implements Interceptor {
        Map<String,String> map;

        public HeaderInterceptors(Map<String, String> map) {
            this.map = map;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Logger.d(map);
            Request.Builder builder = chain.request().newBuilder();
            Iterator<String> keys = map.keySet().iterator();
            while( keys.hasNext() ) {
                String key = keys.next();
                builder.addHeader(key, map.get(key));
            }

            return chain.proceed(builder.build());
        }
    }

}
