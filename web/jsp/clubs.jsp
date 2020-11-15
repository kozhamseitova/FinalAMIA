<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 10.11.2020
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="lg:w-2/3 w-full mx-auto overflow-auto">
    <table class="table-auto w-full text-left whitespace-no-wrap">
        <thead>
        <tr>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Name</th>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Description</th>
            <th class="px-4 py-3 title-font tracking-wider font-medium text-gray-900 text-sm bg-gray-200">Image</th>
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
     <c:set var="clubs" value='${requestScope["clubs"]}' />

        <c:forEach items="${clubs}" var="club">
            <tr>
                <td class="px-4 py-3">${club.name}</td>
                <td class="px-4 py-3">${club.description}</td>
                <td class="px-4 py-3"><img src="${club.img}"></td>
                <%
                    if(session.getAttribute("status")!=null){
                %>
                <td class="px-4 py-3"><a id="r" href="<%=request.getContextPath()%>/club?act=remove&name=${club.name}">remove</a></td>
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
    <form method="get" action="<%=request.getContextPath()%>/club">
        <input type="text" value="Name" name="name">
        <input type="text" value="Image" name="img">
        <input type="text" value="Description" name="description">
        <input type="submit" value="add" name="act">
    </form>
    <h2 class="text-gray-900 text-lg mb-1 font-medium title-font">Change</h2>
    <form id="a" method="get" action="<%=request.getContextPath()%>/club">
        <p>Enter name of club which you want to change</p>
        <input type="text" value="Name" name="name">
        <input type="text" value="Image" name="img">
        <input type="text" value="Description" name="description">
        <input type="submit" value="change" name="act">
    </form>
    <%
        }
    %>
</div>
</body>
</html>
