package com.lazycouple.restapiclient.ui.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.lazycouple.restapiclient.data.DataManager;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.data.Parameter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by noco on 2016-10-24.
 */
@RunWith(AndroidJUnit4.class)
public class RestRequestPresenterTest {

    private RestRequestPresenter restRequestPresenter;


    private RestRequestContract.View mockView;
    private DataManager dataManager;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
        this.mockView = Mockito.mock(RestRequestContract.View.class);
        this.dataManager = Mockito.mock(DataManager.class);


        restRequestPresenter = new RestRequestPresenter(mockView, dataManager);
    }

    @Test
    public void testLoadList() {
        ResponseBody body = ResponseBody.create(MediaType.parse("text/plain"), "test");
        Response response = Response.success(body);

        String url = "http://api.github.com/users/amuyu";
        Map<String, String> map = new HashMap<>();
        when(dataManager.callApi(url, map))
                .thenReturn(Observable.just(response));


        restRequestPresenter.requestRestApi(url, Collections.<Parameter>emptyList());
        verify(mockView, timeout(3000)).showResponse(any(CustomResponse.class));
    }


}
