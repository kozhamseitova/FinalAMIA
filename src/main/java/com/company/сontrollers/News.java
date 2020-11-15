package com.company.сontrollers;

import com.company.models.New;
import com.company.util.NewDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "News")
public class News extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewDB newDB = NewDB.getInstance();
        PrintWriter writer = response.getWriter();

        String act = request.getParameter("act");
        switch (act){
            case "add":
                long id = Long.parseLong(request.getParameter("id"));
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                String time = request.getParameter("time");

                New new1 = new New();
                new1.setId(id);
                new1.setTitle(title);
                new1.setDescription(description);
                new1.setTime(time);


                if(newDB.addNews(new1)){
                    List<New> news = newDB.getAllNews();
                    request.setAttribute("news1", news);
                    request.getRequestDispatcher("jsp/news.jsp").forward(request,response);
                }else{
                    writer.append("This id is already exists");
                }

                break;
            case "change":
                long id1 = Long.parseLong(request.getParameter("id"));
                String title1 = request.getParameter("title");
                String description1 = request.getParameter("description");
                String time1 = request.getParameter("time");

                newDB.update(id1, title1, description1, time1);
                List<New> news3 = newDB.getAllNews();
                request.setAttribute("news1", news3);
                request.getRequestDispatcher("jsp/news.jsp").forward(request,response);

                break;
            case "remove":
                long id2 = Long.parseLong(request.getParameter("id"));
                newDB.remove(id2);
                List<New> news1 = newDB.getAllNews();
                request.setAttribute("news1", news1);
                request.getRequestDispatcher("jsp/news.jsp").forward(request, response);
                break;
            case "l":
                List<New> news2 = newDB.getAllNews();
                request.setAttribute("news1", news2);
                request.getRequestDispatcher("jsp/news.jsp").forward(request,response);
                break;
        }
    }
}

