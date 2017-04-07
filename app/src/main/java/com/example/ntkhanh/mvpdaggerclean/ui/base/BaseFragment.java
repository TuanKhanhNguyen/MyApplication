package com.example.ntkhanh.mvpdaggerclean.ui.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

/**
 * Created by ntkhanh on 4/2/17.
 */

public class BaseFragment extends Fragment {

    public void showProgressDialog() {

        if (isActive() && getActivity() != null) {

        }
    }

    public void hideProgressDialog() {
        if (isActive() && getActivity() != null) {

        }
    }

    public boolean isActive() {
        return isAdded();
    }
}
