package com.app.jwl.adapter;

import com.app.jwl.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Author：YangBin
 * Time：2017/2/22.
 * Email：1250211588@qq.com
 * explain：data.getClass().getField("y").get(data);
 */

public class RvItemQuestionAdapter extends BaseQuickAdapter<Object> {
    public RvItemQuestionAdapter(List<Object> data) {
        super(R.layout.row_question, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Object o) {
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
            holder.setText(R.id.tv_title, holder.getLayoutPosition() + 1 + "." + f1.get(o).toString());
            holder.setText(R.id.tv_content, f2.get(o).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        holder.addOnClickListener(R.id.item_parent);
    }
}
