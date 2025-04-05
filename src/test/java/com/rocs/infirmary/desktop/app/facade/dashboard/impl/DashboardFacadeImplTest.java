package com.rocs.infirmary.desktop.app.facade.dashboard.impl;

import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DashboardFacadeImplTest {

    @Mock
    private DashboardFacadeImpl dashboard;

    private List<FrequentVisitReport> frequentVisitReportList;

    @BeforeEach
    public void setUp() {
        frequentVisitReportList = new ArrayList<>();
        FrequentVisitReport frequentVisitReport = new FrequentVisitReport();
        frequentVisitReport.setStudentId(1);
        frequentVisitReport.setFirstName("Test FirstName");
        frequentVisitReport.setLastName("Test LastName");
        frequentVisitReport.setGradeLevel("Test GradeLevel");
        frequentVisitReport.setVisitDate(new Date());
        frequentVisitReport.setSymptoms("Test Symptoms");
        frequentVisitReport.setVisitCount(5);
        frequentVisitReportList.add(frequentVisitReport);
    }

    @Test
    public void testGenerateFrequentVisitReport() {
        when(dashboard.generateFrequentVisitReport(any(Date.class), any(Date.class), anyString()))
                .thenReturn(frequentVisitReportList);

        List<FrequentVisitReport> result = dashboard.generateFrequentVisitReport(new Date(), new Date(), "Grade 11");

        assertNotNull(result);
        assertEquals(1, result.size());

        FrequentVisitReport report = result.get(0);
        assertEquals("Test FirstName", report.getFirstName());
        assertEquals("Test LastName", report.getLastName());
        assertEquals("Test GradeLevel", report.getGradeLevel());
        assertNotNull(report.getVisitDate());
        assertEquals("Test Symptoms", report.getSymptoms());
        assertEquals(5, report.getVisitCount());

        verify(dashboard, times(1)).generateFrequentVisitReport(any(Date.class), any(Date.class), anyString());
    }
}