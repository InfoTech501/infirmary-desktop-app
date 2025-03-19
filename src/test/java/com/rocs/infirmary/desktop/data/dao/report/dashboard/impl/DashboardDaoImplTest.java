package com.rocs.infirmary.desktop.data.dao.report.dashboard.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
<<<<<<< HEAD
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardReports;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
import com.rocs.infirmary.desktop.data.model.report.lowstock.LowStockReport;
import com.rocs.infirmary.desktop.data.model.report.medication.MedicationTrendReport;
=======
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardDao;
>>>>>>> d7d2944 (IA-4 Create Medicine Inventory)
import com.rocs.infirmary.desktop.data.model.report.visit.FrequentVisitReport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DashboardDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private static MockedStatic<ConnectionHelper> connectionHelper;

    @BeforeEach
    public void setUp() throws SQLException {

        connectionHelper = Mockito.mockStatic(ConnectionHelper.class);
        connectionHelper.when(ConnectionHelper::getConnection).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @AfterEach
    public void tearDown() {
        connectionHelper.close();
    }

    @Test
    public void testGetFrequentVisitReports() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        DashboardDao dashboardDao = new DashboardDaoImpl();
        List<FrequentVisitReport> frequentVisitReports = dashboardDao.getFrequentVisitReports("Grade 11", new Date(), new Date());

        assertNotNull(frequentVisitReports);
        assertNotNull(frequentVisitReports.get(0));

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(eq(1), eq("Grade 11"));
        verify(preparedStatement, times(1)).setTimestamp(eq(2), any(Timestamp.class));
        verify(preparedStatement, times(1)).setTimestamp(eq(3), any(Timestamp.class));
        verify(preparedStatement, times(1)).executeQuery();


    }
<<<<<<< HEAD

    @Test
    public void testGetMedicationTrendReport() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);

        when(resultSet.getInt("usage")).thenReturn(100);
        when(resultSet.getString("item_name")).thenReturn("Test Medicine Name");
        when(resultSet.getInt("quantity")).thenReturn(50);

        DashboardReports dashboardDao = new DashboardReportsImpl();
        List<MedicationTrendReport> medicationTrendReports = dashboardDao.getMedicationTrendReport(new Date(), new Date());

        assertNotNull(medicationTrendReports);
        assertNotNull(medicationTrendReports.get(0));

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setTimestamp(eq(1), any(Timestamp.class));
        verify(preparedStatement, times(1)).setTimestamp(eq(2), any(Timestamp.class));
        verify(preparedStatement, times(1)).executeQuery();



    }

    @Test
    public void testGetAllLowStockMedicine() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("item_name")).thenReturn("Test MedicineName");
        when(resultSet.getInt("quantity")).thenReturn(20);

        DashboardReports dashboardDao = new DashboardReportsImpl();
        List<LowStockReport> lowStockReports = dashboardDao.getAllLowStockMedicine();

        assertNotNull(lowStockReports);
        assertEquals(1, lowStockReports.size());

        LowStockReport report = lowStockReports.get(0);
        assertEquals("Test MedicineName", report.getDescription());
        assertEquals(20, report.getQuantityAvailable());

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testGetCommonAilmentReport() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("AILMENT")).thenReturn("Severe Headache");
        when(resultSet.getInt("OCCURRENCE_COUNT")).thenReturn(10);
        when(resultSet.getString("GRADE_LEVEL")).thenReturn("11");
        when(resultSet.getString("STRAND")).thenReturn("HUMSS");
        when(resultSet.getString("FIRST_NAME")).thenReturn("John");
        when(resultSet.getString("LAST_NAME")).thenReturn("Doe");
        when(resultSet.getInt("AGE")).thenReturn(18);


        DashboardReports dashboardDao = new DashboardReportsImpl();
        List<CommonAilmentsReport> reportList = dashboardDao.getCommonAilmentReport(new Date(), new Date(), "11", "HUMSS");


        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setTimestamp(eq(1), any(Timestamp.class));
        verify(preparedStatement, times(1)).setTimestamp(eq(2), any(Timestamp.class));
        verify(preparedStatement, times(1)).setString(eq(3), eq("11"));
        verify(preparedStatement, times(1)).setString(eq(4), eq("11"));
        verify(preparedStatement, times(1)).setString(eq(5), eq("HUMSS"));
        verify(preparedStatement, times(1)).setString(eq(6), eq("HUMSS"));
        verify(preparedStatement, times(1)).executeQuery();

        assertNotNull(reportList);
        assertNotNull(reportList.get(0));

        CommonAilmentsReport report = reportList.get(0);
        assertNotNull(report.getAffectedPeople());
        assertNotNull(report.getAffectedPeople().get(0));

    }
=======
>>>>>>> d7d2944 (IA-4 Create Medicine Inventory)
}