package com.app.jwl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.app.jwl.util.Tools;

/**
 * Created by neo2 on 2016/7/13.
 */
public abstract class BaseToolBarActivity extends BaseActivity {
    private LinearLayout llv_root_view;
    private ActionBar actionBar;

    public ActionBar getTitleActionBar() {
        return actionBar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_main);
        llv_root_view = (LinearLayout) findViewById(R.id.llv_root_view);

        initToolbar();
        Tools.systemBarLolipop(this);
        if (getLayoutId() != 0)
            llv_root_view.addView(LayoutInflater.from(this).inflate(getLayoutId(), null), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    protected abstract int getLayoutId();

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
