<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 13.11.2020
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/jsp/style/h.css">
</head>
<body>
<%@include file="header.jsp"%>

<div class="lg:w-2/3 w-full mx-auto overflow-auto">
    <table class="table-auto w-full text-left whitespace-no-wrap">
        <thead>
        <tr>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Id</th>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Title</th>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Description</th>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Time</th>
            <%
                if(session.getAttribute("status")!=null){
            %>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Remove</th>

            <%
                }
            %>
        </tr>
        </thead>
        <tbody>
        <c:set var="events" value='${requestScope["events"]}' />

        <c:forEach items="${events}" var="event">
            <tr>
                <td class="px-4 py-3">${event.id}</td>
                <td class="px-4 py-3">${event.title}</td>
                <td class="px-4 py-3">${event.description}</td>
                <td class="px-4 py-3">${event.time}</td>
                <%
                    if(session.getAttribute("status")!=null){
                %>
                <td class="px-4 py-3"><a id="r" href="<%=request.getContextPath()%>/event?act=remove&id=${event.id}">remove</a></td>
                <%
                    }
                %>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="flex flex-col text-center w-full mb-12">
    <%
        if(session.getAttribute("status")!=null){
    %>
    <h2 class="text-gray-900 text-lg mb-1 font-medium title-font">Adding</h2>
    <form method="get" action="<%=request.getContextPath()%>/event">
        <input type="text" value="id" name="id">
        <input type="text" value="Title" name="title">
        <input type="text" value="Description" name="description">
        <input type="text" value="Time" name="time">
        <input type="submit" value="add" name="act">
    </form>
    <h2 class="text-gray-900 text-lg mb-1 font-medium title-font">Change</h2>
    <form id="a" method="get" action="<%=request.getContextPath()%>/event">
        <p>Enter id of event which you want to change</p>
        <input type="text" value="id" name="id">
        <input type="text" value="Title" name="title">
        <input type="text" value="Description" name="description">
        <input type="text" value="Time" name="time">
        <input type="submit" value="change" name="act">
    </form>
    <%
        }
    %>
</div>
</body>
</html>
