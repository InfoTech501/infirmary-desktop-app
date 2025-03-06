package com.rocs.infirmary.desktop.data.model.reports.visit;

import com.rocs.infirmary.desktop.data.model.reports.Report;

import java.util.Date;

public class FrequentVisitReport extends Report {
    private int visitCount;

    public int getVisitCount() { return visitCount; }

    public void setVisitCount(int visitCount) { this.visitCount = visitCount; }

}
