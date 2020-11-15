<%@ page import="java.util.List" %>
<%@ page import="com.company.models.Student" %><%--
  Created by IntelliJ IDEA.
  User: ismail
  Date: 11/13/20
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<div class="lg:w-2/3 w-full mx-auto overflow-auto">
    <table class="table-auto w-full text-left whitespace-no-wrap">
        <thead>
            <tr>
                <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Student Id</th>
                <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Firstname</th>
                <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Lastname</th>
                <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Enrol Year</th>
            </tr>
        </thead>
        <% for (Student item: students) {%>
        <tbody>
            <tr>
                <td class="px-4 py-3"><%=item.getId()%></td>
                <td class="px-4 py-3"><%=item.getFirst_name()%></td>
                <td class="px-4 py-3"><%=item.getLast_name()%></td>
                <td class="px-4 py-3"><%=item.getEnrol_date()%></td>
            </tr>
        </tbody>
        <%}%>
    </table>
</div>
</body>
</html>
