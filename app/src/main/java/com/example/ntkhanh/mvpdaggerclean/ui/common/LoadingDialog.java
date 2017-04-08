package com.example.ntkhanh.mvpdaggerclean.ui.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.Window;

import com.example.ntkhanh.mvpdaggerclean.R;

/**
 * Created by ntkhanh on 4/8/17.
 */

public class LoadingDialog extends Dialog {
    private static final String TAG = LoadingDialog.class.getSimpleName();

    private boolean isShowing = false;

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.loading_dialog_view);
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public void dismiss() {
        isShowing = false;
        super.dismiss();
    }

    @Override
    public void show() {
        if (isShowing) {
            return;
        }
        isShowing = true;
        super.show();
    }
}
