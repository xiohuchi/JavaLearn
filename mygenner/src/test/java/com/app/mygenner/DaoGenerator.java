package com.app.mygenner;

import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
import org.junit.Test;

/**
 * Created by neo2 on 2016/7/6.
 */
public class DaoGenerator {
    @Test
    public void daoGenerator() throws Exception {
        Schema schema = new Schema(170222, "greendao");
        //建表
        addSchma(schema);
        //D:\SVNchat\app\src\main\java-gen
        new org.greenrobot.greendao.generator.DaoGenerator()
                .generateAll(schema, "E:\\GitHub\\GithubPull\\JavaLearn\\app\\src\\main\\java-gen");
    }

    private static void addSchma(Schema schema) {
        Entity base = schema.addEntity("TBase");
        base.addIdProperty().autoincrement();
        base.addStringProperty("title");
        base.addStringProperty("content");

        Entity database = schema.addEntity("TDatabase");
        database.addIdProperty().autoincrement();
        database.addStringProperty("title");
        database.addStringProperty("content");

        Entity design = schema.addEntity("TDesign");
        design.addIdProperty().autoincrement();
        design.addStringProperty("title");
        design.addStringProperty("content");

        Entity ee = schema.addEntity("TEe");
        ee.addIdProperty().autoincrement();
        ee.addStringProperty("title");
        ee.addStringProperty("content");

        Entity frame = schema.addEntity("TFrame");
        frame.addIdProperty().autoincrement();
        frame.addStringProperty("title");
        frame.addStringProperty("content");

        Entity progress = schema.addEntity("TProgress");
        progress.addIdProperty().autoincrement();
        progress.addStringProperty("title");
        progress.addStringProperty("content");

        Entity up = schema.addEntity("TUp");
        up.addIdProperty().autoincrement();
        up.addStringProperty("title");
        up.addStringProperty("content");

        Entity web = schema.addEntity("TWeb");
        web.addIdProperty().autoincrement();
        web.addStringProperty("title");
        web.addStringProperty("content");
    }
}
