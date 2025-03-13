package com.rocs.infirmary.desktop.application.app.facade.reports.common.ailments.report.impl;

import com.rocs.infirmary.desktop.application.data.dao.reports.common.ailments.report.impl.CommonAilmentsReportDaoImpl;
import com.rocs.infirmary.desktop.application.model.reports.CommonAilmentsReport;
import com.rocs.infirmary.desktop.application.data.dao.reports.common.ailments.report.CommonAilmentsReportDao;
import com.rocs.infirmary.desktop.application.app.facade.reports.common.ailments.report.CommonAilmentsReportFacade;

import java.util.Date;
import java.util.List;

/**
 * A class that implements the CommonAilmentsReportFacade
 * managing the CommonAilmentsReport object maintenance such as
 * common ailments, occurrences, affected people, grade level, and strand
 * within the given report period.
 * */
public class CommonAilmentsReportFacadeImpl implements CommonAilmentsReportFacade {

    /** The data access object for CommonAilmentsReport. */
    private final CommonAilmentsReportDao ailmentsReportDAO = new CommonAilmentsReportDaoImpl();

    @Override
    public List<CommonAilmentsReport> generateCommonAilmentReport(Date startDate, Date endDate, String gradeLevel, String section) {
        return this.ailmentsReportDAO.getCommonAilmentReport(startDate, endDate, gradeLevel, section);
    }
}