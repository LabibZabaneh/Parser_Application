package com.example.servlet;

import com.progressoft.interns.advanced.utility.ParsedDataUtilityImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.database.DatabaseUtility.*;

public class opDataServlet extends HttpServlet {

    private String currentColumn = null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        ArrayList<Object[]> data = (ArrayList<Object[]>) session.getAttribute("parsedData");
        ParsedDataUtilityImpl utility = new ParsedDataUtilityImpl();
        String temp = req.getParameter("columns");
        currentColumn = temp;
        BigDecimal answer = null;

        try {
            if (req.getParameter("operation").equals("avg")){
                answer = utility.getAverageOfColumn(data, temp);
            } else if (req.getParameter("operation").equals("sum")){
                answer = utility.getSummationOfColumn(data, temp);
            }
        } catch (Exception e){
            req.setAttribute("colName", temp);
            req.setAttribute("function", "Get"+req.getParameter("operation"));
            req.getRequestDispatcher("utilityError.jsp").forward(req, resp);
        }
        try {
            String fileName = (String) session.getAttribute("fileName");
            if (ifRecordExists(fileName, currentColumn)){
                updateQuery(fileName, currentColumn, answer.doubleValue(), req.getParameter("operation").equals("avg"));
            }
            else {
                if (req.getParameter("operation").equals("avg")){
                    applyQuery(fileName,  currentColumn, answer.doubleValue(), -1.0);
                } else if (req.getParameter("operation").equals("sum")) {
                    applyQuery(fileName, currentColumn, -1.0, answer.doubleValue());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("added operation");
        req.setAttribute("result", answer);
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
