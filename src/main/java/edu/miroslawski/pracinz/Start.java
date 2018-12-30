package edu.miroslawski.pracinz;

import edu.miroslawski.pracinz.actions.doctorsDB.SelectDoctorWhereSpecialisation;
import edu.miroslawski.pracinz.actions.doctorsDB.SelectDoctors;
import edu.miroslawski.pracinz.actions.doctorsDB.SelectDutyHours;
import edu.miroslawski.pracinz.actions.doctorsDB.SelectSpecialisations;
import edu.miroslawski.pracinz.actions.examinationsDb.*;
import edu.miroslawski.pracinz.actions.keychainDB.InsertIdLoginIdInfo;
import edu.miroslawski.pracinz.actions.keychainDB.SelectIdInfoWhereIdLogin;
import edu.miroslawski.pracinz.actions.loginInfoDb.InsertUser;
import edu.miroslawski.pracinz.actions.loginInfoDb.LoginUser;
import edu.miroslawski.pracinz.actions.loginInfoDb.SelectUsers;
import edu.miroslawski.pracinz.actions.loginInfoDb.UpdateUserPassword;
import edu.miroslawski.pracinz.interfaces.DbAction;
import edu.miroslawski.pracinz.actions.personalInfoDb.InsertPatient;
import edu.miroslawski.pracinz.actions.personalInfoDb.SelectPatients;

import java.sql.SQLException;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) throws SQLException {
        String input;
        Scanner scanner = new Scanner(System.in);

        System.out.println("NAZWA UŻYTKOWNIKA:");
        String userName = scanner.nextLine();

        do {
            System.out.println("Podaj komendę:");
            System.out.println("\t---------------PATIENT OPERATIONS-------------------------");
            System.out.println("\t readPatients - pokaż pacjentów");
            System.out.println("\t insertPatient - dodaj pacjenta");
            System.out.println("\t");
            System.out.println("\t---------------USER OPERATIONS----------------------------");
            System.out.println("\t readUsers - pokaż użytkowników");
            System.out.println("\t insertUser - dodaj użytkownika");
            System.out.println("\t loginUser - logowanie użytkownika");
            System.out.println("\t updateUserPassword - zmień hasło użytkownika");
            System.out.println("\t");
            System.out.println("\t---------------KEYCHAIN OPERATIONS------------------------");
            System.out.println("\t insertIdLoginIdInfo - powiąż id_login z id_info");
            System.out.println("\t selectIdInfoWhereIdUser - pokaż id_info dla danego id_login");
            System.out.println("\t");
            System.out.println("\t---------------EXAMINATION OPERATIONS---------------------");
            System.out.println("\t selectLabs - pokaż laboratoria");
            System.out.println("\t selectExamination - pokaż liste umówionych lub odbytych badań");
            System.out.println("\t selectExaminationTypes - pokaż listę rodzajów badań");
            System.out.println("\t insertExamination - dodaj badanie");
            System.out.println("\t selectExaminationWherePatient - wyszukaj badania konkretnego pacjenta");
            System.out.println("\t selectResultWhereResult - wyszukaj wynik konkretnego badania");
            System.out.println("\t selectResultWherePatient - wyszukaj wyniki danego pacjenta");
            System.out.println("\t insertResult - dodaj nowe wyniki badań");
            System.out.println("\t updateResult - zmień treść wyników danego badania");
            System.out.println("\t");
            System.out.println("\t---------------DOCTOR OPERATIONS--------------------------");
            System.out.println("\t selectDoctors - pokaż wszystkich lekarzy");
            System.out.println("\t selectSpecialisations - pokaż specjalizacje");
            System.out.println("\t selectDutyHours - pokaż godziny przyjęć");
            System.out.println("\t selectDoctorWhereSpecialisation - wyszukaj " +
                    "wszystkich lekarzy o określonej specjalizacji");
            System.out.println("\t q - wyjście");
            input = scanner.next();

            DbAction action = null;
            if ("readPatients".equals(input)) {
                action = new SelectPatients();
            } else if ("insertPatient".equals(input)) {
                action = new InsertPatient();
            } else if ("q".equals(input)) {
                System.out.println("Do widzenia!");
            } else if ("readUsers".equals(input)) {
                action = new SelectUsers();
            } else if ("insertUser".equals(input)) {
                action = new InsertUser();
            } else if ("loginUser".equals(input)) {
                action = new LoginUser();
            }else if("updateUserPassword".equals(input)) {
                action = new UpdateUserPassword();
            }else if("insertIdLoginIdInfo".equals(input)) {
                action = new InsertIdLoginIdInfo();
            }else if("selectIdInfoWhereIdUser".equals(input)){
                action = new SelectIdInfoWhereIdLogin();
            } else if ("selectLabs".equals(input)) {
                action = new SelectLabs();
            } else if ("selectExamination".equals(input)) {
                action = new SelectExamination();
            }else if ("selectExaminationTypes".equals(input)){
                action = new SelectExaminationTypes();
            }else if("insertExamination".equals(input)){
                action = new InsertExamination();
            } else if ("selectExaminationWherePatient".equals(input)) {
                action = new SelectExaminationWherePatient();
            } else if ("selectResultWhereResult".equals(input)) {
                action = new SelectResultWhereResult();
            } else if ("selectResultWherePatient".equals(input)) {
                action = new SelectResultWherePatient();
            }else if("insertResult".equals(input)){
                action = new InsertResult();
            }else if("updateResult".equals(input)){
                action = new UpdateResult();
            } else if ("selectDoctors".equals(input)) {
                action = new SelectDoctors();
            } else if ("selectSpecialisations".equals(input)) {
                action = new SelectSpecialisations();
            } else if ("selectDutyHours".equals(input)) {
                action = new SelectDutyHours();
            } else if ("selectDoctorWhereSpecialisation".equals(input)) {
                action = new SelectDoctorWhereSpecialisation();
            } else {
                System.out.println("Niepoprawna komenda");
            }

            if (action != null) {
                action.execute(userName);
            }
        } while (!"q".equals(input));
        scanner.close();
    }
}
