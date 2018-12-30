package edu.miroslawski.pracinz.actions.loginInfoDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectUsers implements DbAction {

    public void execute(String userName) throws SQLException {
        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("login_info_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_USERS @USER_ID = ?");
        statement.setString(1, userName);

        System.out.println("LOGIN\tPASSWORD\tSALT\tTOKEN\tTOKEN_EXPIRATION_DATE");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(2) + "\t" +
                            rs.getString(3) + "\t" +
                            rs.getString(4) + "\t" +
                            rs.getString(5) + "\t" +
                            rs.getString(6)
            );
        }
        System.out.println("");

        connection.close();
    }

}
