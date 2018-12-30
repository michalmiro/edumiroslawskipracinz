package edu.miroslawski.pracinz.actions.loginInfoDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertUser implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LOGIN:");
        String login = scanner.nextLine();

        System.out.println("PASSWORD:");
        String password = scanner.nextLine();

        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("login_info_db");
        PreparedStatement insertStatement = connection.prepareStatement("EXEC INSERT_USER " +
                "@LOGIN = ? ," +
                "@PASSWORD = ?");

        insertStatement.setString(1, login);
        insertStatement.setString(2, password);

        insertStatement.execute();

        ResultSet rs = insertStatement.executeQuery();
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

        connection.close();
    }
}
