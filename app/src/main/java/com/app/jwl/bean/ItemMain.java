package com.app.jwl.bean;

import com.app.jwl.ui.QuestionActivity;

/**
 * Author：YangBin
 * Time：2017/2/22.
 * Email：1250211588@qq.com
 * explain：
 */

public class ItemMain {
    int imageR;
    @QuestionActivity.TYPE_DBTABLE
    int typeDb;

    public ItemMain(int imageR, @QuestionActivity.TYPE_DBTABLE int typeDb) {
        this.imageR = imageR;
        this.typeDb = typeDb;
    }

    public int getImageR() {
        return imageR;
    }

    public void setImageR(int imageR) {
        this.imageR = imageR;
    }

    @QuestionActivity.TYPE_DBTABLE
    public int getTypeDb() {
        return typeDb;
    }


    public void setTypeDb(@QuestionActivity.TYPE_DBTABLE int typeDb) {
        this.typeDb = typeDb;
    }
}
