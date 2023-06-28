package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.database.DatabaseUtility.*;

public class HistoryTableServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ResultSet result = getTable("history");
            ArrayList<ArrayList<String>> table = new ArrayList<>();
            while (result.next()){
                ArrayList<String> temp = new ArrayList<>();
                String fileName = result.getString("fileName");
                String columnName = result.getString("columnName");
                String sum = result.getString("sum");
                String avg = result.getString("average");
                if (sum.equals("-1.0")) {
                    sum = "--";
                } if (avg.equals("-1.0")){
                    avg = "--";
                }
                temp.add(fileName);
                temp.add(columnName);
                temp.add(sum);
                temp.add(avg);
                table.add(temp);
                System.out.println(table.size());
            }
            System.out.println("final: " + table.size());
            req.setAttribute("table", table);
            req.getRequestDispatcher("history.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
