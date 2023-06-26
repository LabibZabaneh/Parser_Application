package com.example.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

import static com.example.database.DatabaseUtility.createTable;

public class initServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            createTable();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
