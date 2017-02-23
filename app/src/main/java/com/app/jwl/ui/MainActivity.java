package com.app.jwl.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.app.jwl.BaseActivity;
import com.app.jwl.R;
import com.app.jwl.adapter.RvMainAdapter;
import com.app.jwl.bean.ItemMain;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.jwl.App.getDaoSession;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.rv)
    RecyclerView rv;

    List<ItemMain> mainList;
    RvMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainList = loadData();
        adapter = new RvMainAdapter(mainList);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setAdapter(adapter);
        adapter.openLoadAnimation();
        rv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ItemMain item = (ItemMain) baseQuickAdapter.getItem(i);
                switch (view.getId()) {
                    case R.id.item_parent:
                        Log.d(TAG, "onClick: " + getDaoSession().getTBaseDao().loadAll().size());
                        QuestionActivity.navigate(MainActivity.this, item.getTypeDb());
                        break;
                }
            }
        });
    }

    private List<ItemMain> loadData() {
        mainList = new ArrayList<>();
        mainList.add(new ItemMain(R.mipmap.icon_base, QuestionActivity.DB_TBase));
        mainList.add(new ItemMain(R.mipmap.icon_database, QuestionActivity.DB_TDatabase));
        mainList.add(new ItemMain(R.mipmap.icon_design, QuestionActivity.DB_TDesign));

        mainList.add(new ItemMain(R.mipmap.icon_ee, QuestionActivity.DB_TEe));
        mainList.add(new ItemMain(R.mipmap.icon_frame, QuestionActivity.DB_TFrame));
        mainList.add(new ItemMain(R.mipmap.icon_progress, QuestionActivity.DB_TProgress));

        mainList.add(new ItemMain(R.mipmap.icon_up, QuestionActivity.DB_TUp));
        mainList.add(new ItemMain(R.mipmap.icon_web, QuestionActivity.DB_TWeb));
        return mainList;
    }
}
