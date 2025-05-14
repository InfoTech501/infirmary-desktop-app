package com.rocs.infirmary.desktop.app.facade.dashboard.impl;

import com.rocs.infirmary.desktop.app.facade.dashboard.DashboardFacade;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardReports;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.impl.DashboardReportsImpl;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * The DashboardFacadeImpl class is an implementation of the DashboardFacade interface.
 * It provides methods for managing reports and notification.
 */
public class DashboardFacadeImpl implements DashboardFacade {

    /** The data access object for Dashboard. */
    private final DashboardReports dashboard = new DashboardReportsImpl();
    private static final Logger logger = LoggerFactory.getLogger(DashboardFacadeImpl.class);

    @Override
    public List<LowStockReport> findAllLowStockMedicine() {
        logger.info("Entering findAllLowStockMedicine");
        List<LowStockReport> lowStockItems = dashboard.getAllLowStockMedicine();
        logger.info("Exiting findAllLowStockMedicine with {} items found.", lowStockItems.size());
        return lowStockItems;
    }

    @Override
    public List<CommonAilmentsReport> generateCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section) {
        logger.info("Entering generateCommonAilmentReport with startDate: {}, endDate: {}, gradeLevel: {}, section: {}", startDate, endDate, gradeLevel, section);
        List<CommonAilmentsReport> report = this.dashboard.getCommonAilmentReport(startDate, endDate, gradeLevel, section);
        logger.warn(report.isEmpty() ?  "CommonAilment Report is Empty" : " Common Ailment Generated Successfully");
        logger.info("Exiting generateCommonAilmentReport with {} records found.", report.size());
        return report;
    }

    @Override
    public List<FrequentVisitReport> generateFrequentVisitReport(Date startDate, Date endDate, String gradeLevel) {
        logger.info("Entering generateFrequentVisitReport with startDate: {}, endDate: {}, gradeLevel: {}", startDate, endDate, gradeLevel);
        List<FrequentVisitReport> frequentVisitReportList = this.dashboard.getFrequentVisitReports(gradeLevel, startDate, endDate);
        logger.warn(frequentVisitReportList.isEmpty() ? "Frequent Visit Report is Empty" : " Frequent Visit Report");
        logger.info("Exiting generateFrequentVisitReport with {} records found.", frequentVisitReportList.size());
        return frequentVisitReportList;
    }

    @Override
    public List<MedicationTrendReport> generateMedicationReport(Date startDate, Date endDate) {
        logger.info("Entering generateMedicationReport with startDate: {}, endDate: {}", startDate, endDate);
        List<MedicationTrendReport> medicationTrendReportList = dashboard.getMedicationTrendReport(startDate, endDate);
        logger.warn(medicationTrendReportList.isEmpty() ? "MedicationTrend Report is Empty  " : " MedicationTrend Report Generated Successfully");
        logger.info("Exiting generateMedicationReport with {} records found.", medicationTrendReportList.size());
        return medicationTrendReportList;
    }
}
