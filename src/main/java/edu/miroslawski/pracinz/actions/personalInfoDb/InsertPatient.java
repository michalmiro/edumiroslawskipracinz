package edu.miroslawski.pracinz.actions.personalInfoDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertPatient implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("IMIĘ:");
        String name = scanner.nextLine();

        System.out.println("NAZWISKO:");
        String surname = scanner.nextLine();

        System.out.println("PESEL:");
        String pesel = scanner.nextLine();

        System.out.println("EMAIL:");
        String email = scanner.nextLine();

        System.out.println("TELEFON:");
        String phoneNumber = scanner.nextLine();

        System.out.println("KOD POCZTOWY:");
        String areaCode = scanner.nextLine();

        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("personal_info_db");
        PreparedStatement insertStatement = connection.prepareStatement("EXEC INSERT_PATIENT " +
                "@USER_ID = ? ," +
                "@NAME = ? ," +
                "@SURNAME = ? ," +
                "@PESEL = ? ," +
                "@EMAIL = ? ," +
                "@PHONE = ? ," +
                "@AREA_CODE = ?");
        insertStatement.setString(1, userName);
        insertStatement.setString(2, name);
        insertStatement.setString(3, surname);
        insertStatement.setString(4, pesel);
        insertStatement.setString(5, email);
        insertStatement.setString(6, phoneNumber);
        insertStatement.setString(7, areaCode);

       // insertStatement.execute();
        ResultSet rs = insertStatement.executeQuery();
        boolean result = false;
        while (rs.next()) {
            result = "Ok".equals(rs.getString(1));
        }
        connection.close();

        if (result) {
            System.out.println("Dodano dane pacjenta");
        } else {
            System.out.println("Dodawanie pacjenta nie powiodło się");
        }




        connection.close();
    }
}
