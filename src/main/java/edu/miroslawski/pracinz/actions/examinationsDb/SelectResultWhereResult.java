package edu.miroslawski.pracinz.actions.examinationsDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectResultWhereResult implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID_RESULT:");
        String idResult = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("examinations_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_RESULT_WHERE_RESULT" +
                " @USER_ID = ?,@RESULT_ID = ?");

        statement.setString(1, userName);
        statement.setString(2, idResult);

        System.out.println("ID_RESULT\tOBSERVATION\tCONCLUSION\tRESULTS");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getString(2) + "\t" +
                            rs.getString(3) + "\t" +
                            rs.getString(4) + "\t"
            );
        }
    }
}
