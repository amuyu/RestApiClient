package com.lazycouple.restapiclient.ui.presenter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.lazycouple.restapiclient.data.DataManager;
import com.lazycouple.restapiclient.ui.contract.RestRequestContract;
import com.lazycouple.restapiclient.ui.data.CustomResponse;
import com.lazycouple.restapiclient.ui.data.Parameter;
import com.lazycouple.restapiclient.ui.viewModel.RestRequestViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.MockInjection;

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

    private Context context;
    private RestRequestContract.View mockView;
    private DataManager dataManager;
    private RestRequestViewModel viewModel;

    @Before
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
        this.context = InstrumentationRegistry.getTargetContext();
        this.mockView = Mockito.mock(RestRequestContract.View.class);
        this.dataManager = Mockito.mock(DataManager.class);
        this.viewModel = Mockito.mock(RestRequestViewModel.class);

        restRequestPresenter = new RestRequestPresenter(context, mockView, dataManager, viewModel);
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
//        verify(mockView, timeout(3000)).showResponse(any(CustomResponse.class));
    }


}
