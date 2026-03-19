package com.hms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hms.model.User;
import com.hms.util.DBConnection;

public class UserDAO {

    public User login(String email, String password) {

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public boolean addUser(User user) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (name,email,password,role) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    public int countTotalDoctors() {

        int count = 0;

        try {
            Connection conn = DBConnection.getConnection();

            String sql =
            "SELECT COUNT(*) FROM users WHERE role='DOCTOR'";

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
    
    public boolean emailExists(String email) {

        boolean exists = false;

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT id FROM users WHERE email=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    
    public List<User> getUsersByRole(String role) {

        List<User> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE role = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, role);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User u = new User();

                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setRole(rs.getString("role"));

                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}