package com.app.jwl.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.app.jwl.BaseToolBarActivity;
import com.app.jwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JavaBaseActivity extends BaseToolBarActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_java_base;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
