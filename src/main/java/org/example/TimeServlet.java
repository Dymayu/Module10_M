package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value="/time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       TimeUtils timeUtils = new TimeUtils();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.print("<h1>Time:</h1>");

        String timezoneReq = req.getParameter("timezone");
        if(timezoneReq == null){
            String formattedDateTime = timeUtils.timeZoneHandler("UTC");
            out.write("<p>${dateTime}</p>".replace("${dateTime}", formattedDateTime));
        }else {
            if(timezoneReq.contains("UTC ")) {
                String timezoneReqPlus = timezoneReq.replace("UTC ", "UTC+");
                String formattedDateTime = timeUtils.timeZoneHandler(timezoneReqPlus);
                out.write("<p>${dateTime}</p>".replace("${dateTime}", formattedDateTime));
                out.close();
            }else {
                String formattedDateTime = timeUtils.timeZoneHandler(timezoneReq);
                out.write("<p>${dateTime}</p>".replace("${dateTime}", formattedDateTime));
                out.close();
            }

        }
    }

}
