package com.rocs.infirmary.desktop.application.data.dao.reports.frequent.visit.report;

import com.rocs.infirmary.desktop.application.model.reports.FrequentVisitReport;

import java.util.Date;
import java.util.List;

/**
 * * This returns the FrequentVisitReport List with the given report period.
 * * @param startDate -  The start of the report date period.
 * * @param endDate - The end of the report date period.
 * * @param gradeLevel - The grade level of the students with frequent visit.
 * */

public interface FrequentVisitReportDao {
    List<FrequentVisitReport> getFrequentVisitReports(String gradeLevel, Date startDate, Date endDate);
}
