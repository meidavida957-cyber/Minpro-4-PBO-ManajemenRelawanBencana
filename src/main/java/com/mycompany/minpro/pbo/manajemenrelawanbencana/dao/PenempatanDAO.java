package com.mycompany.minpro.pbo.manajemenrelawanbencana.dao;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Penempatan;
import java.sql.SQLException;
import java.util.List;

public interface PenempatanDAO {
    void insert(String idRelawan, String idBencana) throws SQLException;
    boolean exists(String idRelawan, String idBencana) throws SQLException;
    List<Penempatan> findAll() throws SQLException;
}