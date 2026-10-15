package com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.impl;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.config.DatabaseConnection;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.BencanaDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.Bencana;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BencanaDAOImpl implements BencanaDAO {

    @Override
    public void insert(Bencana b) throws SQLException {
        String sql = "INSERT INTO bencana (id_bencana, nama_bencana, lokasi, jenis_bencana, status) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getIdBencana());
            ps.setString(2, b.getNamaBencana());
            ps.setString(3, b.getLokasi());
            ps.setString(4, b.getJenisBencana());
            ps.setString(5, b.getStatus());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Bencana> findAll() throws SQLException {
        List<Bencana> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM bencana ORDER BY id_bencana");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    @Override
    public Bencana findById(String id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM bencana WHERE id_bencana = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    @Override
    public void update(Bencana b) throws SQLException {
        String sql = "UPDATE bencana SET nama_bencana = ?, lokasi = ?, jenis_bencana = ?, status = ? "
                   + "WHERE id_bencana = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getNamaBencana());
            ps.setString(2, b.getLokasi());
            ps.setString(3, b.getJenisBencana());
            ps.setString(4, b.getStatus());
            ps.setString(5, b.getIdBencana());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM bencana WHERE id_bencana = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    private Bencana mapRow(ResultSet rs) throws SQLException {
        return new Bencana(rs.getString("id_bencana"), rs.getString("nama_bencana"),
                rs.getString("lokasi"), rs.getString("jenis_bencana"), rs.getString("status"));
    }
}