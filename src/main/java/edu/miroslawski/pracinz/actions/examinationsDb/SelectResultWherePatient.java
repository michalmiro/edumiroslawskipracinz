package edu.miroslawski.pracinz.actions.examinationsDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectResultWherePatient implements DbAction {

    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID_PATIENT:");
        String idPatient = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("examinations_db");
        PreparedStatement statement = connection.prepareStatement("EXEC SELECT_RESULT_WHERE_PATIENT" +
                " @USER_ID = ?,@PATIENT_ID = ?");

        statement.setString(1, userName);
        statement.setString(2,idPatient);

        System.out.println("ID_EXAMINATION\tDATE\tOBSERVATIONS\tCONCLUSION\tRESULT");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "\t" +
                            rs.getString(2) + "\t" +
                            rs.getString(3) + "\t" +
                            rs.getString(4) + "\t" +
                            rs.getString(5) + "\t"
            );
        }
    }
}
