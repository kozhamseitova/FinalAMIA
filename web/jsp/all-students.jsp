<%@ page import="com.company.models.Major" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.models.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/jsp/style/h.css">

</head>
<body>
<%@include file="header.jsp"%>
<%
    List<Major> majors = (List<Major>) request.getAttribute("majors");
    List<Group> groups = (List<Group>) request.getAttribute("groups");
%>
<form class="register-form" method="post" action="<%=request.getContextPath()%>/search">
    <input type="text" placeholder="firstname" name="firstname"/>
    <input type="text" placeholder="lastname" name="lastname"/>
    <input type="text" placeholder="enrol year" name="enrol_year" pattern="\d*" maxlength="4">
    <select id="majors" name="major">
        <option value="-1">Do not set Major</option>
        <% for (Major item: majors) {%>
            <option value="<%=item.getId()%>"><%=item.getName()%></option>
        <%}%>
    </select>
    <select id="groups" name="group">
        <option value="-1">Do not set Group</option>
        <% for (Group item: groups) {%>
            <option value="<%=item.getId()%>"><%=item.getName()%></option>
        <%}%>
    </select>
    <label>Show all</label>
    <input type="radio" id="show all" name="show" value="all" checked>
    <label>Show one</label>
    <input type="radio" id="show one" name="show" value="one">
    <button>Search</button>
</form>
</body>
</html>
