package com.example.hotelmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

public class ApplicationDatabase {
    public static Connection connectdb()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/hmdb", "root", "admin");
            return connect;
        }
        catch (Exception e) {
            System.out.println("LOI ERROR DATABASE: "+e.getMessage());
        }
        return null;
    }
}
