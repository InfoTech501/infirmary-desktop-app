package com.rocs.infirmary.desktop.app.facade.dashboard;

import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;

import java.util.Date;
import java.util.List;

/**
 * The DashboardFacade interface defines methods for managing reports and notification.
 */
public interface DashboardFacade {

    List<LowStockReport> findAllLowStockMedicine();

    /**
     * Retrieves common ailments report from the database using the start date, end date, grade level, and section of student.
     *
     * @param startDate  The start date of the report period.
     * @param endDate    The end date of the report period.
     * @param gradeLevel The grade level to filter the report and can be null.
     * @param section    The section to filter the report and can be null.
     * @return list of CommonAilmentsReport object such as common ailments, occurrences, affected people, grade level, and strand.
     */
    List<CommonAilmentsReport> generateCommonAilmentReport(Date startDate,Date endDate, String gradeLevel, String section);

    List<FrequentVisitReport> generateFrequentVisitReport(Date startDate, Date endDate, String gradeLevel);

    /**
     * Retrieves medication trend report from the database using the start date, end date.
     *
     * @param startDate  The start date of the report period.
     * @param endDate    The end date of the report period.
     * @return list of MedicationTrendReports object such as medication usage, medicine name and medication stocks.
     */
    List<MedicationTrendReport> generateMedicationReport(Date startDate, Date endDate);

}
