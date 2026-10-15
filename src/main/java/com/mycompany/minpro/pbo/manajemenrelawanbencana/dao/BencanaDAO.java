package com.mycompany.minpro.pbo.manajemenrelawanbencana.dao;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Bencana;
import java.sql.SQLException;
import java.util.List;

public interface BencanaDAO {
    void insert(Bencana bencana) throws SQLException;
    List<Bencana> findAll() throws SQLException;
    Bencana findById(String id) throws SQLException;
    void update(Bencana bencana) throws SQLException;
    void delete(String id) throws SQLException;
}