package edu.miroslawski.pracinz.connectionString;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static String keyStoreLocation = "klucz/keystore.jks";

    private static String template = "jdbc:sqlserver://178.128.194.94:1433;" +
            "databaseName={{dbName}};" +
            "user=SA;password=mama#Rafala18;" +
            "integratedSecurity=false;" +
            "columnEncryptionSetting=Enabled;" +
            "keyStoreAuthentication=JavaKeyStorePassword;" +
            "keyStoreLocation={{keyStore}};" +
            "keyStoreSecret=mypassword";


    public static SQLServerConnection getConnectionStringForDb(String dbName) throws SQLException {
        String connectionUrl = template.replace("{{dbName}}", dbName)
                .replace("{{keyStore}}", keyStoreLocation);

        return (SQLServerConnection) DriverManager.getConnection(connectionUrl);
    }

}
