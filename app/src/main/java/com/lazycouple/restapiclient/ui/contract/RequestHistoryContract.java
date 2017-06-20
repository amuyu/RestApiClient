package com.lazycouple.restapiclient.ui.contract;

import com.lazycouple.restapiclient.ui.presenter.BasePresenter;
import com.lazycouple.restapiclient.ui.viewModel.RequestHistoryViewModel;

/**
 * Created by noco on 2016-10-27.
 */
public interface RequestHistoryContract {

    interface View {
        void showList();
        void showRestRequset(String id);
    }

    interface Presenter extends BasePresenter {
        void init();
        void loadList();
        RequestHistoryViewModel getViewModel();
        void onClickedItem(int position);
        void clearItems();
    }

}
