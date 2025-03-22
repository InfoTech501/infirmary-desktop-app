package com.rocs.infirmary.desktop.app.facade.dashboard.impl;

import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
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

    @BeforeEach
    public void setUp() {
        commonAilmentsReportList = new ArrayList<>();
        CommonAilmentsReport report = new CommonAilmentsReport();
        report.setAilment("Headache");
        report.setOccurrences(1);
        report.setGradeLevel("Grade 11");
        report.setStrand("HUMSS");

        commonAilmentsReportList.add(report);
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
