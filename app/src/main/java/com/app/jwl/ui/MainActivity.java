package com.app.jwl.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.app.jwl.App;
import com.app.jwl.BaseActivity;
import com.app.jwl.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ItemMain> mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainList = loadData();
        rv.setLayoutManager(new GridLayoutManager(this,2));
    }

    private List<ItemMain> loadData() {
        mainList = new ArrayList<>();
        mainList.add(new ItemMain(R.mipmap.icon_base, JavaBaseActivity.class));
        return mainList;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: ");
        Log.d(TAG, "onClick: " + App.getDaoSession().getTBaseDao().loadAll().size());
    }


    class ItemMain {
        int imageR;
        Class<? extends BaseActivity> activity;

        public ItemMain(int imageR, Class<? extends BaseActivity> activity) {
            this.imageR = imageR;
            this.activity = activity;
        }

        public int getImageR() {
            return imageR;
        }

        public void setImageR(int imageR) {
            this.imageR = imageR;
        }

        public Class<? extends BaseActivity> getActivity() {
            return activity;
        }

        public void setActivity(Class<? extends BaseActivity> activity) {
            this.activity = activity;
        }
    }
}
