package com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.impl;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.config.DatabaseConnection;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.BencanaDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.PenempatanDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.RelawanDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Penempatan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PenempatanDAOImpl implements PenempatanDAO {
    private final RelawanDAO relawanDAO = new RelawanDAOImpl();
    private final BencanaDAO bencanaDAO = new BencanaDAOImpl();

    @Override
    public void insert(String idRelawan, String idBencana) throws SQLException {
        String sql = "INSERT INTO penempatan (id_relawan, id_bencana) VALUES (?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idRelawan);
            ps.setString(2, idBencana);
            ps.executeUpdate();
        }
    }

    @Override
    public boolean exists(String idRelawan, String idBencana) throws SQLException {
        String sql = "SELECT 1 FROM penempatan WHERE id_relawan = ? AND id_bencana = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, idRelawan);
            ps.setString(2, idBencana);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
    public List<Penempatan> findAll() throws SQLException {
        List<Penempatan> list = new ArrayList<>();
        String sql = "SELECT id_relawan, id_bencana FROM penempatan ORDER BY id_penempatan";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Penempatan(relawanDAO.findById(rs.getString("id_relawan")),
                                        bencanaDAO.findById(rs.getString("id_bencana"))));
            }
        }
        return list;
    }
}