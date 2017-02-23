package com.app.jwl.adapter;

import com.app.jwl.R;
import com.app.jwl.bean.ItemMain;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author：YangBin
 * Time：2017/2/22.
 * Email：1250211588@qq.com
 * explain：data.getClass().getField("y").get(data);
 */

public class RvMainAdapter extends BaseQuickAdapter<ItemMain> {
    public RvMainAdapter(List<ItemMain> data) {
        super(R.layout.row_mainitem, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ItemMain item) {
        holder.setImageResource(R.id.iv_image, item.getImageR());
        holder.addOnClickListener(R.id.item_parent);
    }
}
