package com.rocs.infirmary.desktop.data.dao.report.dashboard.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.report.dashboard.DashboardDao;
import com.rocs.infirmary.desktop.data.model.report.ailment.CommonAilmentsReport;
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
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @AfterEach
    public void tearDown() {
        connectionHelper.close();
    }

    @Test
    public void testGetCommonAilmentReport() throws SQLException {

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("AILMENT")).thenReturn("Headache");
        when(resultSet.getInt("OCCURRENCE_COUNT")).thenReturn(1);
        when(resultSet.getString("GRADE_LEVEL")).thenReturn("11");
        when(resultSet.getString("STRAND")).thenReturn("HUMSS");
        when(resultSet.getString("FIRST_NAME")).thenReturn("John");
        when(resultSet.getString("LAST_NAME")).thenReturn("Doe");
        when(resultSet.getInt("AGE")).thenReturn(18);


        DashboardDao dashboardDao = new DashboardDaoImpl();
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
}
