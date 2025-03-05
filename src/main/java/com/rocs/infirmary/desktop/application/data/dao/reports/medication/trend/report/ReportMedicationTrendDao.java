package com.rocs.infirmary.desktop.application.data.dao.reports.medication.trend.report;

import com.rocs.infirmary.desktop.application.model.reports.MedicationTrendReport;

import java.util.Date;
import java.util.List;

/**
 * An interface that manages the generated ReportMedicationTrend object that maintains
 * medication usage, medicine name, and medication stocks
 * within the given report period.
 * */
public interface ReportMedicationTrendDao {

    /**
     * Returns the generated report of MedicationTrendReports object within the given report period
     *  @param startDate  The start date of the report period.
     *  @param endDate    The end date of the report period.
     *  @return list of MedicationTrendReports object such as medication usage, medicine name and medication stocks.
     * */
    List<MedicationTrendReport> getMedicationTrendReport(Date startDate, Date endDate);
}
