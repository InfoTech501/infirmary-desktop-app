package com.rocs.infirmary.desktop.app.facade.dashboard.impl;

import com.rocs.infirmary.desktop.app.facade.dashboard.DashboardFacade;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardDao;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.impl.DashboardDaoImpl;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;

import java.util.Date;
import java.util.List;

public class DashboardFacadeImpl implements DashboardFacade {
    private final DashboardDao dashboard = new DashboardDaoImpl();

    /**
     * It provides the functionality to check for low stock items and send notifications.
     */
    @Override
    public List<LowStockReport> findAllLowStockMedicine() {
        List<LowStockReport> lowStockItems = dashboard.getAllLowStockMedicine();
        return lowStockItems;
    }

    @Override
    public List<CommonAilmentsReport> generateCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section) {
        return this.dashboard.getCommonAilmentReport(startDate, endDate, gradeLevel, section);
    }

    @Override
    public List<FrequentVisitReport> generateFrequentVisitReport(Date startDate, Date endDate, String gradeLevel) {
        List<FrequentVisitReport> frequentVisitReportList = this.dashboard.getFrequentVisitReports(gradeLevel, startDate, endDate);
        return frequentVisitReportList;
    }

    @Override
    public List<MedicationTrendReport> generateMedicationReport(Date startDate, Date endDate) {
        List<MedicationTrendReport> medicationTrendReportList = dashboard.getMedicationTrendReport(startDate, endDate);
        return medicationTrendReportList;
    }
}
