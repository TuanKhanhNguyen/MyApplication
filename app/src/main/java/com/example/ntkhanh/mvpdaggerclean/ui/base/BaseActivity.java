package com.example.ntkhanh.mvpdaggerclean.ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ntkhanh.mvpdaggerclean.ui.common.LoadingDialog;

/**
 * Created by ntkhanh on 4/8/17.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();

    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mLoadingDialog = new LoadingDialog(this);
    }

    public void showLoadingDialog() {
        showLoadingDialog(false, null);
    }

    public void showLoadingDialog(boolean cancelable, final DialogInterface.OnDismissListener dismissedListener) {
        if (!mLoadingDialog.isShowing() && !isFinishing()) {
            mLoadingDialog.setCancelable(cancelable);
            mLoadingDialog.setOnDismissListener(dismissedListener);
            mLoadingDialog.show();

        }
    }

    public void hideLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing() && !isDestroyed()) {
            mLoadingDialog.setOnDismissListener(null);
            mLoadingDialog.dismiss();
        }
    }

}
