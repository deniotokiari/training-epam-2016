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

    private IDbOperations mOperations;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testCRUD() {
        final ContentValues values = new ContentValues();

        values.put(User.ID, 1);
        values.put(User.TITLE, "User");
        values.put(User.DATE, System.currentTimeMillis());

        final long id = mOperations.insert(User.class, values);

        assertTrue(id != 0);

        final Cursor cursor = mOperations.query("SELECT * FROM " + DbHelper.getTableName(User.class));

        assertNotNull(cursor);
        assertTrue(cursor.moveToFirst());

        assertEquals(cursor.getString(cursor.getColumnIndex(User.TITLE)), "User");

        cursor.close();

        assertTrue(cursor.isClosed());

        final int count = mOperations.delete(User.class, User.ID + " = ?", String.valueOf(id));

        assertEquals(count, 1);
    }

    @Override
    protected void setUp() throws Exception {
        final Context context = getActivity();

        context.deleteDatabase("test");

        mOperations = IDbOperations.Imp.newInstance(context, "test", 1);

        super.setUp();
    }

}
