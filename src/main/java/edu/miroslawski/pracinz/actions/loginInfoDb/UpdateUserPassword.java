package edu.miroslawski.pracinz.actions.loginInfoDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateUserPassword implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LOGIN:");
        String login = scanner.nextLine();

        System.out.println("PASSWORD:");
        String password = scanner.nextLine();

        System.out.println("NEW PASSWORD:");
        String newPassword = scanner.nextLine();

        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("login_info_db");
        PreparedStatement statement = connection.prepareStatement("EXEC UPDATE_USER_PASSWORD " +
                "@LOGIN = ? ," +
                "@PASSWORD = ?"+
                "@NEW_PASSWORD");


        statement.setString(1, login);
        statement.setString(2, password);
        statement.setString(3,newPassword);

        ResultSet rs = statement.executeQuery();
        boolean result = false;
        while (rs.next()) {
            result = "Ok".equals(rs.getString(1));
        }
        connection.close();

        if (result) {
            System.out.println("Zmieniono hasło");
        } else {
            System.out.println("Logowanie nie powiodło się, hasło nie zostało zmienione");
        }

    }
}
