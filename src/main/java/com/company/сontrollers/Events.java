package com.company.сontrollers;


import com.company.models.Club;
import com.company.models.ClubDB;
import com.company.models.Event;
import com.company.models.EventDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Events")
public class Events extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventDB eventDB = EventDB.getInstance();
        PrintWriter writer = response.getWriter();

        String act = request.getParameter("act");
        switch (act){
            case "add":
                long id = Long.parseLong(request.getParameter("id"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String time = request.getParameter("time");

                Event event = new Event();
                event.setId(id);
                event.setTitle(title);
                event.setDescription(description);
                event.setTime(time);


                if(eventDB.addEvents(event)){
                    List<Event> events = eventDB.getAllEvents();
                    request.setAttribute("events", events);
                    request.getRequestDispatcher("jsp/events.jsp").forward(request,response);
                }else{
                    writer.append("This id is already exists");
                }

                break;
            case "change":
                long id1 = Long.parseLong(request.getParameter("id"));
                String title1 = request.getParameter("title");
                String description1 = request.getParameter("description");
                String time1 = request.getParameter("time");

                eventDB.update(id1, title1, description1, time1);
                List<Event> events = eventDB.getAllEvents();
                request.setAttribute("events", events);
                request.getRequestDispatcher("jsp/events.jsp").forward(request,response);

                break;
            case "remove":
                long id2 = Long.parseLong(request.getParameter("id"));
                eventDB.remove(id2);
                List<Event> events1 = eventDB.getAllEvents();
                request.setAttribute("events", events1);
                request.getRequestDispatcher("jsp/events.jsp").forward(request, response);
                break;
            case "l":
                List<Event> events2 = eventDB.getAllEvents();
                request.setAttribute("events", events2);
                request.getRequestDispatcher("jsp/events.jsp").forward(request,response);
                break;
        }
    }
}

