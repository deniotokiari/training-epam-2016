package com.example.mikhail_sianko.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

public interface IDbOperations {

    Cursor query(String sql, String... args);

    long insert(Class<?> table, ContentValues values);

    int bulkInsert(Class<?> table, List<ContentValues> values);

    int delete(Class<?> table, String sql, String... args);

    final class Imp {

        public static IDbOperations newInstance(final Context pContext, final String pName, final int version) {
            return new DbHelper(pContext, pName, version);
        }

    }

}
