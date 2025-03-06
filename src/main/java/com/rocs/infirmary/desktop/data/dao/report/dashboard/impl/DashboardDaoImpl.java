package com.rocs.infirmary.desktop.data.dao.report.dashboard.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardDao;
import com.rocs.infirmary.desktop.data.model.person.Person;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DashboardDaoImpl implements DashboardDao {


    @Override
    public List<LowStockReport> getAllLowStockMedicine() {
        List<LowStockReport> lowStockItems = new ArrayList<>();
        String query = "SELECT description, quantity_available FROM inventory WHERE quantity_available < 20";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LowStockReport item = new LowStockReport(resultSet.getString("description"), resultSet.getInt("quantity_available"));
                lowStockItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStockItems;
    }

    @Override
    public List<CommonAilmentsReport> getCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section) {
        List<CommonAilmentsReport> reportList = new ArrayList<>();

        String sql = "SELECT a.description as AILMENT, COUNT(*) as occurrence_count, s.SECTION, s.GRADE_LEVEL, p.FIRST_NAME, p.LAST_NAME, p.AGE, s.STRAND " +
                "FROM MEDICAL_RECORD mr " +
                "JOIN AILMENTS a ON mr.AILMENT_ID = a.AILMENT_ID " +
                "JOIN STUDENT st ON mr.STUDENT_ID = st.ID " +
                "JOIN PERSON p ON st.PERSON_ID = p.ID " +
                "LEFT JOIN SECTION s ON st.SECTION_SECTION_ID = s.SECTION_ID " +
                "WHERE mr.VISIT_DATE BETWEEN ? AND ? " +
                "AND (s.GRADE_LEVEL = ? OR ? IS NULL) " +
                "AND (s.SECTION = ? OR ? IS NULL) " +
                "GROUP BY a.description, s.section, s.grade_level, p.FIRST_NAME, p.LAST_NAME, p.AGE, s.STRAND";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            statement.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));
            statement.setString(3, gradeLevel);
            statement.setString(4, gradeLevel);
            statement.setString(5, section);
            statement.setString(6, section);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CommonAilmentsReport report = new CommonAilmentsReport();
                report.setAilment(resultSet.getString("AILMENT"));
                report.setOccurrences(resultSet.getInt("OCCURRENCE_COUNT"));
                report.setGradeLevel(resultSet.getString("GRADE_LEVEL"));
                report.setStrand(resultSet.getString("STRAND"));

                Person person = new Person();
                person.setFirstName(resultSet.getString("FIRST_NAME"));
                person.setLastName(resultSet.getString("LAST_NAME"));
                person.setAge(resultSet.getInt("AGE"));


                List<Person> people = new ArrayList<>();
                people.add(person);
                report.setAffectedPeople(people);

                reportList.add(report);
            }
        } catch (SQLException e) {
            System.err.println("Error generating the common ailments report: " + e.getMessage());
        }

        return reportList;
    }


    @Override
    public List<FrequentVisitReport> getFrequentVisitReports(String gradeLevel, Date startDate, Date endDate) {
        List<FrequentVisitReport> reportsList = new ArrayList<>();

        String sql = "SELECT mr.student_id, p.first_name, p.last_name, s.grade_level, mr.visit_date, mr.symptoms, COUNT(*) AS visit_count\n" +
                "FROM medical_record mr\n" +
                "JOIN student st ON mr.student_id = st.id\n" +
                "JOIN section s ON st.section_section_id = s.section_id\n" +
                "JOIN person p ON st.person_id = p.id\n" +
                "WHERE s.grade_level = ? " +
                "AND mr.visit_date BETWEEN ? AND ?\n" +
                "GROUP BY mr.student_id, p.first_name, p.last_name, s.grade_level, mr.visit_date, mr.symptoms";

        try (Connection conn = ConnectionHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gradeLevel);
            stmt.setTimestamp(2, new java.sql.Timestamp(startDate.getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FrequentVisitReport report = new FrequentVisitReport();
                report.setStudentId(rs.getInt("student_id"));
                report.setFirstName(rs.getString("first_name"));
                report.setLastName(rs.getString("last_name"));
                report.setGradeLevel(rs.getString("grade_level"));
                report.setVisitDate(rs.getDate("visit_date"));
                report.setSymptoms(rs.getString("symptoms"));
                report.setVisitCount(rs.getInt("visit_count"));

                reportsList.add(report);
            }
        } catch (SQLException e) {
            System.out.println("An SQL Exception Occurred. " + e.getMessage());
        }
        return reportsList;
    }

    @Override
    public List<MedicationTrendReport> getMedicationTrendReport(Date startDate, Date endDate) {
        List<MedicationTrendReport> reportList = new ArrayList<>();

        String sql = "SELECT i.medicine_id, COUNT (*) AS usage, m.item_name, i.quantity_available " +
                "FROM medicine_administered ma " +
                "JOIN medicine m ON m.medicine_id = ma.medicine_id " +
                "JOIN inventory i ON i.medicine_id = ma.medicine_id " +
                "JOIN medical_record mr ON mr.id = ma.med_record_id " +
                "WHERE mr.visit_date BETWEEN ? AND ? " +
                "GROUP BY i.medicine_id, m.item_name, i.quantity_available";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setTimestamp(1, new java.sql.Timestamp(startDate.getTime()));
            stmt.setTimestamp(2, new java.sql.Timestamp(endDate.getTime()));

            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                MedicationTrendReport report = new MedicationTrendReport();
                report.setUsage(rs.getInt("usage"));
                report.setMedicineName(rs.getString("item_name"));
                report.setStocks(rs.getInt("quantity_available"));

                reportList.add(report);
            }

        } catch (SQLException e) {
            System.out.println("An SQL Exception Occurred. " + e.getMessage());
        }

        return reportList;
    }
}
