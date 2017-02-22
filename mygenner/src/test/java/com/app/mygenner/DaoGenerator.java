package com.app.mygenner;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.junit.Test;

/**
 * Created by neo2 on 2016/7/6.
 */
public class DaoGenerator {
    @Test
    public void daoGenerator() throws Exception {
        Schema schema = new Schema(170209, "greendao");
        //建表
        addSchma(schema);
        //D:\SVNchat\app\src\main\java-gen
        new org.greenrobot.greendao.generator.DaoGenerator()
                .generateAll(schema, "E:\\GitHub\\GithubPull\\JavaWeLife\\app\\src\\main\\java-gen");
    }

    private static void addSchma(Schema schema) {
        //联系人
        Entity base = schema.addEntity("TBase");
        base.addIdProperty().autoincrement();
        base.addStringProperty("title");
        base.addStringProperty("content");
    }
}
