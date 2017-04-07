package com.example.ntkhanh.mvpdaggerclean.ui.post;

import com.example.ntkhanh.mvpdaggerclean.ui.base.BasePresenter;
import com.example.ntkhanh.mvpdaggerclean.ui.base.BaseView;
import com.example.ntkhanh.mvpdaggerclean.data.model.Post;

import java.util.List;

/**
 * Created by ntkhanh on 3/26/17.
 */

public interface PostContract {

    interface View extends BaseView {

        void showProgressDialog();

        void hideProgressDialog();

        void showCompleted();

        void showError(String message);

        void showPost(List<Post> post);

    }

    interface Presenter extends BasePresenter {
        void loadPost(boolean forceUpdate);
    }

}
