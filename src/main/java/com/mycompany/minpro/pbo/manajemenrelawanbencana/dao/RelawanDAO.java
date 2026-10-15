package com.mycompany.minpro.pbo.manajemenrelawanbencana.dao;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Relawan;
import java.sql.SQLException;
import java.util.List;

public interface RelawanDAO {
    void insert(Relawan relawan) throws SQLException;
    List<Relawan> findAll() throws SQLException;
    Relawan findById(String id) throws SQLException;
    void update(Relawan relawan) throws SQLException;
    void delete(String id) throws SQLException;
}