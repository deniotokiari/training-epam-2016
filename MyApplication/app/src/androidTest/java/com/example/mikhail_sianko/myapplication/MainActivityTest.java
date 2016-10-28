package com.example.mikhail_sianko.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;

import com.example.mikhail_sianko.myapplication.db.DbHelper;
import com.example.mikhail_sianko.myapplication.db.IDbOperations;
import com.example.mikhail_sianko.myapplication.model.contract.User;
import com.example.mikhail_sianko.myapplication.ui.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    private Context mContext;

    @Override
    protected void setUp() throws Exception {
        mContext = getActivity();

        super.setUp();
    }

    public void testCRUD() {
        final ContentValues values = new ContentValues();

        values.put(User.ID, 1);
        values.put(User.TITLE, "User");
        values.put(User.DATE, System.currentTimeMillis());

        final IDbOperations operations = new DbHelper(mContext, "test.db", 1);

        final long id = operations.insert(User.class, values);

        assertTrue(id > 0);

        final Cursor cursor = operations.query("SELECT * FROM " + DbHelper.getTableName(User.class));

        assertNotNull(cursor);
        assertTrue(cursor.moveToFirst());

        assertEquals(cursor.getString(cursor.getColumnIndex(User.TITLE)), "User");

        cursor.close();

        assertTrue(cursor.isClosed());

        operations.delete(User.class, "WHERE " + User.ID + " = ?", String.valueOf(id));
    }

}
