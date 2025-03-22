package com.rocs.infirmary.desktop.app.facade.dashboard.impl;

import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardDao;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DashboardFacadeImplTest {

    @Mock
    private DashboardFacadeImpl dashboard;

    private List<CommonAilmentsReport> commonAilmentsReportList;
  
    @Mock
    private DashboardDao dashboardDao;

    private List<FrequentVisitReport> frequentVisitReportList;


    private List<MedicationTrendReport> medicationTrendReportsList;

    private List<LowStockReport> lowStockReportList;

    @BeforeEach
    public void setUp() {

        commonAilmentsReportList = new ArrayList<>();
        CommonAilmentsReport report = new CommonAilmentsReport();
        report.setAilment("Headache");
        report.setOccurrences(1);
        report.setGradeLevel("Grade 11");
        report.setStrand("HUMSS");

        commonAilmentsReportList.add(report);

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

        lowStockReportList = new ArrayList<>();
        LowStockReport lowStockReport = new LowStockReport();
        lowStockReport.setDescription("Test MedicineName");
        lowStockReport.setQuantityAvailable(20);
        lowStockReportList.add(lowStockReport);


    }

    @Test
    public void testGenerateCommonAilmentReport() {
        when(dashboard.generateCommonAilmentReport(any(Date.class), any(Date.class), anyString(), anyString()))
                .thenReturn(commonAilmentsReportList);

        List<CommonAilmentsReport> result = dashboard.generateCommonAilmentReport(new Date(), new Date(), "Grade 11", "HUMSS");

        assertNotNull(result);
        assertEquals(1, result.size());

        CommonAilmentsReport report = result.get(0);
        assertEquals("Headache", report.getAilment());
        assertEquals(1, report.getOccurrences());
        assertEquals("Grade 11", report.getGradeLevel());
        assertEquals("HUMSS", report.getStrand());

        verify(dashboard, times(1)).generateCommonAilmentReport(any(Date.class), any(Date.class), anyString(), anyString());
    }

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

    @Test
    public void testFindAllLowStockMedicine() {

        when(dashboardDao.getAllLowStockMedicine()).thenReturn(lowStockReportList);

        List<LowStockReport> result = dashboardDao.getAllLowStockMedicine();

        assertNotNull(result);
        assertEquals(1, result.size());

        LowStockReport report = result.get(0);
        assertEquals("Test MedicineName", report.getDescription());
        assertEquals(20, report.getQuantityAvailable());

        verify(dashboardDao, times(1)).getAllLowStockMedicine();
    }

}

