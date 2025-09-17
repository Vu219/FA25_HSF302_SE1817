package org.example;

import java.sql.Connection;
import java.sql.DriverManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String connectionString = "jdbc:sqlserver://localhost:1433;"
                + "database=HouseholdStore;"
                + "user=sa;"
                + "password=12345;"
                + "encrypt=true;"
                + "trustServerCertificate=true;";

        try {
            Connection conn = DriverManager.getConnection(connectionString);
            System.out.println("Connection established");
            conn.close();
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }

    }
}