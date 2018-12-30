package edu.miroslawski.pracinz.actions.examinationsDb;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import edu.miroslawski.pracinz.connectionString.ConnectionManager;
import edu.miroslawski.pracinz.interfaces.DbAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertExamination implements DbAction {
    public void execute(String userName) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DATA W FORMACIE YYYY-MM-DD:");
        String date = scanner.nextLine();

        System.out.println("PATIENT_ID:");
        String idPatient = scanner.nextLine();

        System.out.println("STATUS:");
        String status = scanner.nextLine();

        System.out.println("EXAMINATION_TYPE_ID:");
        String idExaminatioType = scanner.nextLine();

        System.out.println("DOCTOR_ID:");
        String idDoctor = scanner.nextLine();

        System.out.println("RESULTS_ID:");
        String idResult = scanner.nextLine();


        SQLServerConnection connection = ConnectionManager.getConnectionStringForDb("examinations_db");
        PreparedStatement statement = connection.prepareStatement("EXEC INSERT_EXAMINATION" +
                " @USER_ID = ?,@DATE = ?, @PATIENT_ID = ?, " +
                "@STATUS = ?,@EXAMINATION_TYPE_ID=?,@DOCTOR_ID=?,@RESULTS_ID=?");

        statement.setString(1, userName);
        statement.setString(2, date);
        statement.setString(3, idPatient);
        statement.setString(4, status);
        statement.setString(5, idExaminatioType);
        statement.setString(6, idDoctor);
        statement.setString(7, idResult);

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
