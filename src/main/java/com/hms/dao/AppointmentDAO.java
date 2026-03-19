package com.hms.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hms.model.Appointment;
import com.hms.util.DBConnection;

public class AppointmentDAO {

//	ADMIN
	public List<Appointment> getAllAppointments() {
		
		List<Appointment> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnection.getConnection();
			String sql = "SELECT * FROM appointments ORDER BY appointment_date DESC";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Appointment a = new Appointment(rs.getInt("id"), rs.getInt("patient_id"), rs.getInt("doctor_id"),
						rs.getDate("appointment_date"), rs.getTime("appointment_time"), rs.getString("status"),
						rs.getInt("created_by"));
				
				list.add(a);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int countTotalAppointments() {

		int count = 0;

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT COUNT(*) FROM appointments";
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

	public int countTotalCompletedAppointments() {

		int count = 0;

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT COUNT(*) FROM appointments WHERE status='COMPLETED'";

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

	public int countTotalCancelledAppointments() {

		int count = 0;

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT COUNT(*) FROM appointments WHERE status='CANCELLED'";

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
	
	public int countPendingAppointments() {

	    int count = 0;

	    try {
	        Connection conn = DBConnection.getConnection();

	        String sql = "SELECT COUNT(*) FROM appointments WHERE status='BOOKED'";

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
	
//	STAFF
	public boolean addAppointment(Appointment a) {
		
		try {
			Connection conn = DBConnection.getConnection();
			
			String sql = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time, status, created_by) VALUES (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, a.getPatientId());
			ps.setInt(2, a.getDoctorId());
			ps.setDate(3, a.getAppointmentDate());
			ps.setTime(4, a.getAppointmentTime());
			ps.setString(5, "BOOKED");
			ps.setInt(6, a.getCreatedBy());
			
			return ps.executeUpdate() > 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
//	DOCTOR

	public List<Appointment> getAppointmentsByDoctor(int doctorId) {

		List<Appointment> list = new ArrayList<>();

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT a.*, p.name AS patient_name FROM appointments a JOIN patients p ON a.patient_id = p.id WHERE a.doctor_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Appointment a = new Appointment();

				a.setId(rs.getInt("id"));
				a.setPatientId(rs.getInt("patient_id"));
				a.setDoctorId(rs.getInt("doctor_id"));
				a.setAppointmentDate(rs.getDate("appointment_date"));
				a.setAppointmentTime(rs.getTime("appointment_time"));
				a.setStatus(rs.getString("status"));
				a.setCreatedBy(rs.getInt("created_by"));

				a.setPatientName(rs.getString("patient_name"));

				list.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public boolean updateStatus(int id, String status, int doctorId) {

		boolean result = false;

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "UPDATE appointments SET status=? WHERE id=? AND doctor_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, id);
			ps.setInt(3, doctorId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int countPendingByDoctor(int doctorId) {

		int count = 0;

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND status='BOOKED'";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public int countCompletedByDoctor(int doctorId) {

		int count = 0;

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND status='COMPLETED'";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
	
	public int countCancelByDoctor(int doctorId) {
		
		int count = 0;
		
		try {
			Connection conn = DBConnection.getConnection();
			
			String sql = "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND status='CANCELLED'";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);
			
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