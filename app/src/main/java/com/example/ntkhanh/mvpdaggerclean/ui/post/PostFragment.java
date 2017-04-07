package com.example.ntkhanh.mvpdaggerclean.ui.post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ntkhanh.mvpdaggerclean.ui.base.BaseFragment;
import com.example.ntkhanh.mvpdaggerclean.ui.base.BasePresenter;
import com.example.ntkhanh.mvpdaggerclean.R;
import com.example.ntkhanh.mvpdaggerclean.data.model.Post;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ntkhanh on 3/26/17.
 */

public class PostFragment extends BaseFragment implements PostContract.View {

    private static final String TAG = PostFragment.class.getSimpleName();

    private PostContract.Presenter mPresenter;

    private RecyclerView mRecyclerView;

    private PostAdapter mPostAdapter;

    public PostFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPostAdapter = new PostAdapter(new ArrayList<Post>(0));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_post, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_list_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mPostAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, ">>>onResume()");
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public static PostFragment newInstance() {
        return new PostFragment();
    }

    @Override
    public void showCompleted() {
        //Show completed message Toast
        Toast.makeText(getActivity().getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError(String message) {
        //Show error message Toast
        Toast.makeText(getActivity().getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showPost(@NonNull List<Post> postList) {
        mPostAdapter.replaceData(postList);
    }


    @Override
    public void setPresenter(@NonNull BasePresenter presenter) {
        this.mPresenter = checkNotNull((PostContract.Presenter) presenter);
    }

}
