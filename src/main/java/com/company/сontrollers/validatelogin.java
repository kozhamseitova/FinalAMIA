package com.company.сontrollers;

import com.company.models.User;
import com.company.util.UserDB;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/validatelogin")
public class validatelogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        JSONArray list = new JSONArray();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        JSONObject obj = new JSONObject();
        UserDB userDao = UserDB.getInstance();
        User user = userDao.getUserByEmail(email);
        HashMap<Long, String> hashMap = userDao.getAllUsers();
        String msg;
        if(user!=null){
            if(user.getPassword().equals(password)){
                String status = userDao.getStatusByEmail(email);
                HttpSession session = request.getSession(true);
                session.setAttribute("user",user);
                session.setAttribute("status", status);
                session.setMaxInactiveInterval(60);
                Cookie ck=new Cookie("email",email);
                response.addCookie(ck);
                msg="1";
                obj.put("msg", msg);
                list.add(obj);
                out.println(list.toJSONString());
                out.flush();
            }else{
                msg="2";
                obj.put("msg", msg);
                list.add(obj);
                out.println(list.toJSONString());
                out.flush();
            }
        }else{
            msg="3";
            obj.put("msg", msg);
            list.add(obj);
            out.println(list.toJSONString());
            out.flush();
        }
    }
}
