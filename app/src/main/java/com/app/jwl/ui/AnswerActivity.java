package com.app.jwl.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.jwl.BaseToolBarActivity;
import com.app.jwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnswerActivity extends BaseToolBarActivity {
    private static final String KEY_TITLE = "com.app.jwl.ui.AnswerActivity.KEY_TITLE";
    private static final String KEY_CONTENT = "com.app.jwl.ui.AnswerActivity.KEY_CONTENT";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.activity_answer)
    ScrollView activityAnswer;


    public static void navigate(Activity activity, String title, String content) {
        Intent intent = new Intent(activity, AnswerActivity.class);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_CONTENT, content);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_answer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        getTitleActionBar().setTitle("详细");
        tvTitle.setText(getIntent().getStringExtra(KEY_TITLE));
        tvContent.setText(getIntent().getStringExtra(KEY_CONTENT));
    }
}
