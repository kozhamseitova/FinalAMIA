package com.company.сontrollers;

import com.company.models.Student;
import com.company.util.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB db = new DB();
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        int enrol_year = -1;
        try {
            enrol_year = Integer.parseInt(request.getParameter("enrol_year"));
        } catch (Exception e){
            System.out.println("Enrol year was not entered");
        }
        int group_id = Integer.parseInt(request.getParameter("group"));
        int major_id = Integer.parseInt(request.getParameter("major"));
        String show = request.getParameter("show");
        List<Student> studentsByMajor = new ArrayList<>();
        if (major_id!=-1){
            studentsByMajor.addAll(db.getAllStudentsByMajor(major_id));
        } else {
            studentsByMajor.addAll(db.getAllStudents());
        }
        List<Student> studentsByGroup = new ArrayList<>();
        if (group_id!=-1){
            studentsByGroup.addAll(db.getAllStudentsByGroup(group_id));
        } else {
            studentsByGroup.addAll(db.getAllStudents());
        }
        List<Student> intersectionOfMajorAndGroup = studentsByMajor
                .stream()
                .filter(os->studentsByGroup.stream().anyMatch(ns -> os.getId() == ns.getId())).collect(Collectors.toList());
        List<Student> studentsSortedByYear = new ArrayList<>();
        if (enrol_year!=-1){
            int finalEnrol_year = enrol_year;
            studentsSortedByYear.addAll(intersectionOfMajorAndGroup.stream().filter(student -> student.getEnrol_date()== finalEnrol_year).collect(Collectors.toList()));
        } else {
            studentsSortedByYear.addAll(intersectionOfMajorAndGroup);
        }

        List<Student> studentsSortedByFirstname = new ArrayList<>();
        if (!firstname.equals("")){
            studentsSortedByFirstname.addAll(studentsSortedByYear.stream().filter(student -> student.getFirst_name().equals(firstname)).collect(Collectors.toList()));
        } else {
            studentsSortedByFirstname.addAll(studentsSortedByYear);
        }

        List<Student> studentsSortedByLastname = new ArrayList<>();
        if (!lastname.equals("")){
            studentsSortedByLastname.addAll(studentsSortedByFirstname.stream().filter(student -> student.getLast_name().equals(lastname)).collect(Collectors.toList()));
        } else {
            studentsSortedByLastname.addAll(studentsSortedByFirstname);
        }

        List<Student> showStudents = new ArrayList<>();
        if (show.equals("one")){
            showStudents.add(studentsSortedByLastname.get(0));
        } else {
            showStudents.addAll(studentsSortedByLastname);
        }

        request.setAttribute("students", showStudents);
        request.getRequestDispatcher("jsp/result.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DB db = new DB();
        request.setAttribute("groups", db.getAllGroups());
        request.setAttribute("majors", db.getAllMajors());
        request.getRequestDispatcher("jsp/all-students.jsp").forward(request,response);
    }
}
