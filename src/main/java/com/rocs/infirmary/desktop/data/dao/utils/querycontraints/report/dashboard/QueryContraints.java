package com.rocs.infirmary.desktop.data.dao.utils.querycontraints.report.dashboard;

public class QueryContraints {

    private final String getAllLowStockMedicineQuery = "SELECT description, quantity_available FROM inventory WHERE quantity_available < 20";;

    private final String getCommonAilmentReportQuery = "SELECT a.description as AILMENT, COUNT(*) as occurrence_count, s.SECTION, s.GRADE_LEVEL, p.FIRST_NAME, p.LAST_NAME, p.AGE, s.STRAND " +
            "FROM MEDICAL_RECORD mr " +
            "JOIN AILMENTS a ON mr.AILMENT_ID = a.AILMENT_ID " +
            "JOIN STUDENT st ON mr.STUDENT_ID = st.ID " +
            "JOIN PERSON p ON st.PERSON_ID = p.ID " +
            "LEFT JOIN SECTION s ON st.SECTION_SECTION_ID = s.SECTION_ID " +
            "WHERE mr.VISIT_DATE BETWEEN ? AND ? " +
            "AND (s.GRADE_LEVEL = ? OR ? IS NULL) " +
            "AND (s.SECTION = ? OR ? IS NULL) " +
            "GROUP BY a.description, s.section, s.grade_level, p.FIRST_NAME, p.LAST_NAME, p.AGE, s.STRAND";

    private final String getFrequentVisitReportsQuery = "SELECT mr.student_id, p.first_name, p.last_name, s.grade_level, mr.visit_date, mr.symptoms, COUNT(*) AS visit_count\n" +
            "FROM medical_record mr\n" +
            "JOIN student st ON mr.student_id = st.id\n" +
            "JOIN section s ON st.section_section_id = s.section_id\n" +
            "JOIN person p ON st.person_id = p.id\n" +
            "WHERE s.grade_level = ? " +
            "AND mr.visit_date BETWEEN ? AND ?\n" +
            "GROUP BY mr.student_id, p.first_name, p.last_name, s.grade_level, mr.visit_date, mr.symptoms";

    private final  String getMedicationTrendReport = "SELECT i.medicine_id, COUNT (*) AS usage, m.item_name, i.quantity_available " +
            "FROM medicine_administered ma " +
            "JOIN medicine m ON m.medicine_id = ma.medicine_id " +
            "JOIN inventory i ON i.medicine_id = ma.medicine_id " +
            "JOIN medical_record mr ON mr.id = ma.med_record_id " +
            "WHERE mr.visit_date BETWEEN ? AND ? " +
            "GROUP BY i.medicine_id, m.item_name, i.quantity_available";

    public String getGetAllLowStockMedicineQuery() {
        return getAllLowStockMedicineQuery;
    }

    public String getGetCommonAilmentReportQuery() {
        return getCommonAilmentReportQuery;
    }

    public String getGetFrequentVisitReportsQuery() {
        return getFrequentVisitReportsQuery;
    }

    public String getGetMedicationTrendReport() {
        return getMedicationTrendReport;
    }
}
