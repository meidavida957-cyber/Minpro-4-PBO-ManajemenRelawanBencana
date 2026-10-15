package com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.impl;

import com.mycompany.minpro.pbo.manajemenrelawanbencana.config.DatabaseConnection;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.dao.RelawanDAO;
import com.mycompany.minpro.pbo.manajemenrelawanbencana.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelawanDAOImpl implements RelawanDAO {

    @Override
    public void insert(Relawan r) throws SQLException {
        String sql = "INSERT INTO relawan (id, nama, alamat, no_hp, keahlian, jenis, spesialisasi, jenis_logistik) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getId());
            ps.setString(2, r.getNama());
            ps.setString(3, r.getAlamat());
            ps.setString(4, r.getNoHp());
            ps.setString(5, r.getKeahlian());
            ps.setString(6, kodeJenis(r));
            ps.setString(7, r instanceof RelawanMedis ? ((RelawanMedis) r).getSpesialisasi() : null);
            ps.setString(8, r instanceof RelawanLogistik ? ((RelawanLogistik) r).getJenisLogistik() : null);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Relawan> findAll() throws SQLException {
        List<Relawan> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM relawan ORDER BY id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    @Override
    public Relawan findById(String id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM relawan WHERE id = ?")) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    @Override
    public void update(Relawan r) throws SQLException {
        String sql = "UPDATE relawan SET nama = ?, alamat = ?, no_hp = ?, keahlian = ?, "
                   + "spesialisasi = ?, jenis_logistik = ? WHERE id = ?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getNama());
            ps.setString(2, r.getAlamat());
            ps.setString(3, r.getNoHp());
            ps.setString(4, r.getKeahlian());
            ps.setString(5, r instanceof RelawanMedis ? ((RelawanMedis) r).getSpesialisasi() : null);
            ps.setString(6, r instanceof RelawanLogistik ? ((RelawanLogistik) r).getJenisLogistik() : null);
            ps.setString(7, r.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM relawan WHERE id = ?")) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    private String kodeJenis(Relawan r) {
        if (r instanceof RelawanMedis) return "MEDIS";
        if (r instanceof RelawanLogistik) return "LOGISTIK";
        return "UMUM";
    }

    /** Mengubah satu baris tabel menjadi objek subclass Relawan yang sesuai. */
    private Relawan mapRow(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String nama = rs.getString("nama");
        String alamat = rs.getString("alamat");
        String noHp = rs.getString("no_hp");
        String keahlian = rs.getString("keahlian");
        switch (rs.getString("jenis")) {
            case "MEDIS":
                return new RelawanMedis(id, nama, alamat, noHp, keahlian, rs.getString("spesialisasi"));
            case "LOGISTIK":
                return new RelawanLogistik(id, nama, alamat, noHp, keahlian, rs.getString("jenis_logistik"));
            default:
                return new RelawanUmum(id, nama, alamat, noHp, keahlian);
        }
    }
}