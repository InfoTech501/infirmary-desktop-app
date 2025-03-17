package com.rocs.infirmary.desktop.data.dao.report.dashboard;

import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;

import java.util.Date;
import java.util.List;

public interface DashboardDao {
    /**
     * Retrieves a list of inventory items that are low in stock.
     * @return a list of LowStockItem objects that are low in stock.
     */
    List<LowStockReport> getAllLowStockMedicine();
    List<CommonAilmentsReport> getCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section);
    List<FrequentVisitReport> getFrequentVisitReports(String gradeLevel, Date startDate, Date endDate);
    List<MedicationTrendReport> getMedicationTrendReport(Date startDate, Date endDate);

}
