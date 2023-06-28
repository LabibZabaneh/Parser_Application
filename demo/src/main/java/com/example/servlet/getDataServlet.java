package com.example.servlet;

import com.progressoft.interns.advanced.utility.ParsedDataUtilityImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class getDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        ArrayList<Object[]> data = (ArrayList<Object[]>) session.getAttribute("parsedData");
        ParsedDataUtilityImpl utility = new ParsedDataUtilityImpl();
        String temp = req.getParameter("columns");
        ArrayList<String> answer = utility.getColumnData(data, temp);
        req.setAttribute("colName", temp);
        req.setAttribute("result", answer);
        req.getRequestDispatcher("colResult.jsp").forward(req, resp);
    }
}
