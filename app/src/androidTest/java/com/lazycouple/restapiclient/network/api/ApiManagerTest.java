package com.lazycouple.restapiclient.network.api;

import com.lazycouple.restapiclient.consts.BasicConsts;
import com.lazycouple.restapiclient.data.ApiManager;
import com.lazycouple.restapiclient.ui.data.User;

import org.junit.Before;
import org.junit.Test;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.observers.TestSubscriber;

import static org.hamcrest.core.Is.is;

/**
 * Created by noco on 2016-10-24.
 */
public class ApiManagerTest {

    private ApiManager apiManager;

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BasicConsts.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RestApiService restApiService = retrofit.create(RestApiService.class);
        apiManager = new ApiManager(restApiService);
    }

    @Test
    public void testGetUser() throws Exception {
        TestSubscriber<User> testSubscriber = TestSubscriber.create();
        apiManager.getUser("amuyu")
                .subscribe(testSubscriber);
        testSubscriber.assertNoErrors();

    }
}