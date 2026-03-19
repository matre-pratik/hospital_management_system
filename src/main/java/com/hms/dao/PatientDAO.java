package com.hms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hms.model.Patient;
import com.hms.util.DBConnection;

public class PatientDAO {

//	STAFF
    public boolean addPatient(Patient p) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO patients (name,age,gender,phone,address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getAddress());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM patients ORDER BY id ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getTimestamp("created_at")
                );
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Patient> searchPatients(String keyword) {

        List<Patient> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql =
            "SELECT * FROM patients WHERE name LIKE ? OR phone LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Patient p = new Patient();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getInt("age"));
                p.setPhone(rs.getString("phone"));
                p.setGender(rs.getString("gender"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public int countTotalPatients() {

        int count = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM patients";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}