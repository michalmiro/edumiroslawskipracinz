package edu.miroslawski.pracinz.actions.doctorsDB;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectDoctorWhereSpecialisation implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID_SPECIALISATION:");
        String idSpecialisation = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("doctors_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_DOCTOR_WHERE_SPECIALISATION" +
                " @USER_ID = ?,@SPECIALISATION_ID = ?");

        statement.setString(1, userName);
        statement.setString(2,idSpecialisation);

        System.out.println("ID_DOCTOR\tNAME\tSECOND_NAME\tSPECIALISATION");
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
