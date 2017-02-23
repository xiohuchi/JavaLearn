package com.app.jwl.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.jwl.App;
import com.app.jwl.BaseToolBarActivity;
import com.app.jwl.R;
import com.app.jwl.adapter.RvItemQuestionAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends BaseToolBarActivity {
    public static final int DB_TBase = 0X20001;
    public static final int DB_TDatabase = 0X20002;
    public static final int DB_TDesign = 0X20003;
    public static final int DB_TEe = 0X20004;
    public static final int DB_TFrame = 0X20005;
    public static final int DB_TProgress = 0X20006;
    public static final int DB_TUp = 0X20007;
    public static final int DB_TWeb = 0X20008;
    private static final String KEY_TYPE_DBTABLE = "com.app.jwl.ui.QuestionActivity.KEY_TYPE_DBTABLE";

    @IntDef({
            DB_TBase,
            DB_TDatabase,
            DB_TDesign,
            DB_TEe,
            DB_TFrame,
            DB_TProgress,
            DB_TUp,
            DB_TWeb,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE_DBTABLE {
    }

    public static void navigate(Activity activity, @TYPE_DBTABLE int typeDb) {
        Intent intent = new Intent(activity, QuestionActivity.class);
        intent.putExtra(KEY_TYPE_DBTABLE, typeDb);
        activity.startActivity(intent);
    }

    @BindView(R.id.rv)
    RecyclerView rv;

    RvItemQuestionAdapter adapter;
    List list;

    @Override

    protected int getLayoutId() {
        return R.layout.activity_java_base;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        String title = "";
        switch (getIntent().getIntExtra(KEY_TYPE_DBTABLE, 0)) {
            case DB_TBase:
                title = "java基础";
                list = App.getDaoSession().getTBaseDao().loadAll();
                break;
            case DB_TDatabase:
                title = "数据库";
                list = App.getDaoSession().getTDatabaseDao().loadAll();
                break;
            case DB_TDesign:
                title = "设计模式";
                list = App.getDaoSession().getTDesignDao().loadAll();
                break;
            case DB_TEe:
                title = "javaEE";
                list = App.getDaoSession().getTEeDao().loadAll();
                break;
            case DB_TFrame:
                title = "流行框架";
                list = App.getDaoSession().getTFrameDao().loadAll();
                break;
            case DB_TProgress:
                title = "java进阶";
                list = App.getDaoSession().getTProgressDao().loadAll();
                break;
            case DB_TUp:
                title = "算法编程";
                list = App.getDaoSession().getTUpDao().loadAll();
                break;
            case DB_TWeb:
                title = "javaWeb";
                list = App.getDaoSession().getTWebDao().loadAll();
                break;
            default:
                list = new ArrayList();
        }
        getTitleActionBar().setTitle(title);
        adapter = new RvItemQuestionAdapter(list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        adapter.openLoadAnimation();
        rv.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void SimpleOnItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (view.getId()) {
                    case R.id.item_parent:
                        showAnswer(i, baseQuickAdapter.getData().get(i));
                        break;
                }
            }
        });
    }

    private void showAnswer(int position, Object o) {
        String title = null, content = null;
        try {
            Field f1 = null;
            Field f2 = null;
            try {
                f1 = o.getClass().getDeclaredField("title");//获取类的属性x,无视权限（获取方法类似）
                f2 = o.getClass().getDeclaredField("content");//获取类的属性x,无视权限（获取方法类似）
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            f1.setAccessible(true);//设置属性可编辑
            f2.setAccessible(true);//设置属性可编辑
            title = position + 1 + "." + f1.get(o).toString();
            content = f2.get(o).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        AnswerActivity.navigate(QuestionActivity.this, title, content);
    }
}
