package com.example.mikhail_sianko.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.mikhail_sianko.myapplication.db.DbHelper;
import com.example.mikhail_sianko.myapplication.db.IDbOperations;
import com.example.mikhail_sianko.myapplication.model.contract.User;

public class CustomContentProvider extends ContentProvider {

    public static final String AUTH = "content://com.example.mikhail_sianko.myapplication.provider.CustomContentProvider";

    private IDbOperations mOperations;

    @Override
    public boolean onCreate() {
        mOperations = IDbOperations.Imp.newInstance(getContext(), "db", 1);

        return true;
    }

    private Class<?> getTable(final Uri pUri) {
        return User.class;
    }

    @Nullable
    @Override
    public Cursor query(final Uri pUri, final String[] pStrings, final String pS, final String[] pStrings1, final String pS1) {
        return mOperations.query("select * from "+DbHelper.getTableName(getTable(pUri)), pStrings1);
    }

    @Nullable
    @Override
    public String getType(final Uri pUri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(final Uri pUri, final ContentValues pContentValues) {
        long id = mOperations.insert(getTable(pUri), pContentValues);
        final Uri uri = Uri.parse(AUTH + "/" + DbHelper.getTableName(User.class) + "/" + id);
        getContext().getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(final Uri pUri, final String pS, final String[] pStrings) {
        return mOperations.delete(getTable(pUri), pS, pStrings);
    }

    @Override
    public int bulkInsert(final Uri uri, final ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(final Uri pUri, final ContentValues pContentValues, final String pS, final String[] pStrings) {
        return 0;
    }
}
