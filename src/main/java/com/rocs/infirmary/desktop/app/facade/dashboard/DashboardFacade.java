package com.rocs.infirmary.desktop.app.facade.dashboard;

import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;

import java.util.Date;
import java.util.List;

public interface DashboardFacade {
    List<LowStockReport> findAllLowStockMedicine();
    List<CommonAilmentsReport> generateCommonAilmentReport(Date startDate,Date endDate, String gradeLevel, String section);
    List<FrequentVisitReport> generateFrequentVisitReport(Date startDate, Date endDate, String gradeLevel);
    List<MedicationTrendReport> generateMedicationReport(Date startDate, Date endDate);

}
