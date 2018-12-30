package edu.miroslawski.pracinz.actions.examinationsDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertResult implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("OBSERVATION:");
        String observation = scanner.nextLine();

        System.out.println("CONCLUSION:");
        String conclusion = scanner.nextLine();

        System.out.println("RESULT:");
        String result = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("examinations_db");
        PreparedStatement statement = connection.prepareStatement("EXEC INSERT_RESULT" +
                " @USER_ID = ?,@OBSERVATION = ?, @CONCLUSION = ?, @RESULT = ?");

        statement.setString(1, userName);
        statement.setString(2, observation);
        statement.setString(3, conclusion);
        statement.setString(4, result);

        ResultSet rs = statement.executeQuery();
        boolean operationResult = false;
        while (rs.next()) {
            operationResult = "Ok".equals(rs.getString(1));
        }
        connection.close();

        if (operationResult) {
            System.out.println("Insert zakończony sukcesem");
        } else {
            System.out.println("Insert nie powiódł się");
        }

    }
}
