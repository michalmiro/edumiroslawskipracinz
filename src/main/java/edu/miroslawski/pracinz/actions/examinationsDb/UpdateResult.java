package edu.miroslawski.pracinz.actions.examinationsDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateResult implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("RESULT_ID:");
        String idResult = scanner.nextLine();

        System.out.println("OBSERVATION:");
        String observation = scanner.nextLine();

        System.out.println("CONCLUSION:");
        String conclusion = scanner.nextLine();

        System.out.println("RESULT:");
        String result = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("examinations_db");
        PreparedStatement statement = connection.prepareStatement("EXEC UPDATE_RESULT" +
                " @USER_ID = ?,@RESULT_ID = ?,@OBSERVATIONS = ?, @CONCLUSION = ?, @RESULTS = ?");

        statement.setString(1, userName);
        statement.setString(2, idResult);
        statement.setString(3, observation);
        statement.setString(4, conclusion);
        statement.setString(5, result);

        ResultSet rs = statement.executeQuery();
        boolean operationResult = false;
        while (rs.next()) {
            System.out.println(rs.getString(1));
            operationResult = "Ok".equals(rs.getString(1));
            if (operationResult) {
                System.out.println("Update zakończony sukcesem");
            } else {
                System.out.println("Update nie powiódł się");
            }
        }
        connection.close();


    }
}

