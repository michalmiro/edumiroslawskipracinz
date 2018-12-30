package edu.miroslawski.pracinz.actions.doctorsDB;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectDutyHours implements DbAction {
    public void execute(String userName) throws SQLException {
        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("doctors_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_DUTY_HOURS @USER_ID = ?");
        statement.setString(1, userName);

        System.out.println("ID_DUTY_HOURS\tDAY_NAME\tBEGGINING_HOUR\tFINISHING_HOUR\tOFFICE");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getString(2) + "\t" +
                            rs.getString(3) + "\t" +
                            rs.getString(4) + "\t" +
                            rs.getString(5) + "\t"
            );
        }
        System.out.println("");

        connection.close();
    }
}
