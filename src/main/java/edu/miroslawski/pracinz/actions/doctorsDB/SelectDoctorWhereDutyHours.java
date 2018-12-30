package edu.miroslawski.pracinz.actions.doctorsDB;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectDoctorWhereDutyHours implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID_DUTY_HOURS:");
        String idDutyHours = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("doctors_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_DOCTOR_WHERE_DUTY_HOURS" +
                " @USER_ID = ?,@SPECIALISATION_ID = ?");

        statement.setString(1, userName);
        statement.setString(2, idDutyHours);

        System.out.println("ID_DOCTOR\tNAME\tSECOND_NAME");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getString(2) + "\t" +
                            rs.getString(3) + "\t"
            );
        }
    }
}