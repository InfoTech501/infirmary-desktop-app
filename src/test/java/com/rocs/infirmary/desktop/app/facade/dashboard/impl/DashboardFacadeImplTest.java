package com.rocs.infirmary.desktop.app.facade.dashboard.impl;

import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
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

    private List<MedicationTrendReport> medicationTrendReportsList;

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

        medicationTrendReportsList = new ArrayList<>();
        MedicationTrendReport medicationTrendReport = new MedicationTrendReport();
        medicationTrendReport.setUsage(100);
        medicationTrendReport.setMedicineName("Test MedicineName");
        medicationTrendReport.setStocks(50);
        medicationTrendReportsList.add(medicationTrendReport);
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

    @Test
    public void testGenerateMedicationReport() {
        when(dashboard.generateMedicationReport(any(Date.class), any(Date.class)))
                .thenReturn(medicationTrendReportsList);

        List<MedicationTrendReport> result = dashboard.generateMedicationReport(new Date(), new Date());

        assertNotNull(result);
        assertEquals(1, result.size());

        MedicationTrendReport report = result.get(0);
        assertEquals(100, report.getUsage());
        assertEquals("Test MedicineName", report.getMedicineName());
        assertEquals(50, report.getStocks());

        verify(dashboard, times(1)).generateMedicationReport(any(Date.class), any(Date.class));

    }
}