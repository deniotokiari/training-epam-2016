package com.example.mikhail_sianko.myapplication.model.contract;

import com.example.mikhail_sianko.myapplication.db.annotations.Table;
import com.example.mikhail_sianko.myapplication.db.annotations.type.dbInteger;
import com.example.mikhail_sianko.myapplication.db.annotations.type.dbLong;
import com.example.mikhail_sianko.myapplication.db.annotations.type.dbString;

@Table(name = "USER")
public final class User {

    @dbInteger
    public static final String ID = "id";

    @dbString
    public static final String TITLE = "title";

    @dbLong
    public static final String DATE = "date";

}
