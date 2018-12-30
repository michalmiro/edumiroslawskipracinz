package edu.miroslawski.pracinz.actions.loginInfoDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginUser implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LOGIN:");
        String login = scanner.nextLine();

        System.out.println("PASSWORD:");
        String password = scanner.nextLine();

        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("login_info_db");
        PreparedStatement statement = connection.prepareStatement("EXEC LOGIN_USER " +
                "@LOGIN = ? ," +
                "@PASSWORD = ?");

        statement.setString(1, login);
        statement.setString(2, password);

        ResultSet rs = statement.executeQuery();
        boolean result = false;
        while (rs.next()) {
            result = "Ok".equals(rs.getString(1));
        }
        connection.close();

        if (result) {
            System.out.println("Logowanie powiodło się");
        } else {
            System.out.println("Logowanie nie powiodło się");
        }
    }
}
