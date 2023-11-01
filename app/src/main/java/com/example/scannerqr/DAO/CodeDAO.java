package com.example.scannerqr.DAO;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.scannerqr.DTO.CodeDTO;

@Dao
public interface CodeDAO {
    @Insert
    void insertCode(CodeDTO dto);
}
