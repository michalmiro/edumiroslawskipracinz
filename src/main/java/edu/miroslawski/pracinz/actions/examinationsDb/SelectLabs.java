package edu.miroslawski.pracinz.actions.examinationsDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectLabs implements DbAction {

    public void execute(String userName) throws SQLException {
        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("examinations_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_LABS @USER_ID = ?");
        statement.setString(1, userName);

        System.out.println("ID_LAB\tADRESS_STREET\tADRESS_BUILDING_NUMBER\tROOM\tFLOOR");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getString(2) + "\t" +
                            rs.getString(3) + "\t" +
                            rs.getString(4) + "\t" +
                            rs.getString(5)
            );
        }
        System.out.println("");

        connection.close();
    }
}
