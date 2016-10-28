package com.example.mikhail_sianko.myapplication;

import android.app.Application;

import com.example.mikhail_sianko.myapplication.db.DbHelper;
import com.example.mikhail_sianko.myapplication.db.IDbOperations;
import com.example.mikhail_sianko.myapplication.model.contract.User;

import org.junit.Assert;
import org.junit.Test;

public class DbTest {

    @Test
    public void testCreateTableStatement() {
        final String sql = DbHelper.getTableCreateQuery(User.class);

        Assert.assertNotNull(sql);
        Assert.assertEquals(
                "CREATE TABLE IF NOT EXISTS USER (id INTEGER,title TEXT,date BIGINT);",
                sql
        );
    }

}
