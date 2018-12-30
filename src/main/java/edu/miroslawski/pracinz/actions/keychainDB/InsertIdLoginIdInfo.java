package edu.miroslawski.pracinz.actions.keychainDB;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertIdLoginIdInfo implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LOGIN_ID:");
        String idLogin = scanner.nextLine();

        System.out.println("INFO_ID:");
        String idInfo = scanner.nextLine();

        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("keychain_db");
        PreparedStatement statement = connection.prepareStatement("EXEC INSERT_ID_LOGIN_ID_INFO" +
                " @USER_ID = ?,@LOGIN_ID=?,@INFO_ID=?");

        statement.setString(1, userName);
        statement.setString(2, idLogin);
        statement.setString(3, idInfo);

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
