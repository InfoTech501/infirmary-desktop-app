package com.rocs.infirmary.desktop.data.dao.report.dashboard;

import com.rocs.infirmary.desktop.data.model.reports.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.reports.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.reports.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.reports.medication.MedicationTrendReport;

import java.util.Date;
import java.util.List;

public interface DashboardDao {
    List<LowStockReport> getAllLowStockMedicine();
    List<CommonAilmentsReport> getCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section);
    List<FrequentVisitReport> getFrequentVisitReports(String gradeLevel, Date startDate, Date endDate);
    List<MedicationTrendReport> getMedicationTrendReport(Date startDate, Date endDate);

}
