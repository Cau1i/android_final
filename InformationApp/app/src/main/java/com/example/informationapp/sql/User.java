package com.example.informationapp.sql;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices ={@Index(value ="account", unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String account;
    public String password;
}

