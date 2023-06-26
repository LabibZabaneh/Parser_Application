package com.example.servlet;
import com.progressoft.interns.advanced.exception.ParserException;
import com.progressoft.interns.advanced.parser.CsvParserImpl;
import com.progressoft.interns.advanced.parser.JsonParserImpl;
import com.progressoft.interns.advanced.parser.Parser;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@MultipartConfig
public class ParserServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private HttpServletRequest req = null;
    private HttpServletResponse resp = null;
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        this.resp.setContentType("text/html");
        String applicationPath = this.req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()){
            fileSaveDir.mkdirs();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
        }
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        ArrayList<Object[]> parsedData = getParsedData(fileName, fileSaveDir, fileExtension);
        HttpSession session = this.req.getSession();
        session.setAttribute("parsedData", parsedData);
        session.setAttribute("fileName", fileName);
        this.req.setAttribute("parsedData", parsedData);
        this.req.getRequestDispatcher("data.jsp").forward(this.req, this.resp);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

    private ArrayList<Object[]> getParsedData(String fileName, File fileSaveDir, String fileExtension) throws ServletException, IOException {
        ArrayList<Object[]> parsedData = null;
        try {
            if (fileExtension.equals("json")){
                Parser<ArrayList<Object[]>> parser = new JsonParserImpl();
                parsedData = parser.parse(fileSaveDir.getAbsolutePath() + File.separator + fileName);
            } else {
                Parser<ArrayList<Object[]>> parser = new CsvParserImpl();
                parsedData = parser.parse(fileSaveDir.getAbsolutePath() + File.separator + fileName);
            }
        } catch (ParserException p){
            req.setAttribute("error", p.getMessage());
            req.getRequestDispatcher("parseError.jsp").forward(req, resp);
        }
        return parsedData;
    }
}

