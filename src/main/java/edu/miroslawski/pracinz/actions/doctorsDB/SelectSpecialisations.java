package edu.miroslawski.pracinz.actions.doctorsDB;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSpecialisations implements DbAction {
    public void execute(String userName) throws SQLException {
        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("doctors_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_SPECIALISATIONS @USER_ID = ?");
        statement.setString(1, userName);

        System.out.println("ID_SPECIALISATION\tSPECIALISATION_NAME");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getString(2) + "\t"
            );
        }
        System.out.println("");

        connection.close();
    }
}
