package com.example.informationapp.sql;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM users")
    List<User> selectAll();

    @Query("SELECT id,account FROM users")
    List<User> selectAccount();

    @Query("SELECT id,password FROM users WHERE account=:account")
    List<User> selectPassword(String account);
}
