<%--
  Created by IntelliJ IDEA.
  User: Abu
  Date: 15.11.2020
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/jsp/style/header.css">
</head>
<body>
    <div class="header">
        <div class="inner_header">
            <div class="logo_container">
                <h1>MAIA<span>University</span></h1>
            </div>

            <ul class="navigation">
                <a href="<%=request.getContextPath()%>/jsp/main.jsp"><li>Home</li></a>
                <a href="<%=request.getContextPath()%>/event?act=l"><li>Events</li></a>
                <a href="<%=request.getContextPath()%>/search"><li>Search students</li></a>
                <a href="<%=request.getContextPath()%>/new?act=l"><li>New</li></a>
                <a href="<%=request.getContextPath()%>/club?act=l"><li>Clubs</li></a>
                <%
                    if(session.getAttribute("user")!=null){
                %>
                <a href="<%=request.getContextPath()%>/logOut"><li>Log out</li></a>
                <%
                    }
                %>
                <%
                    if(session.getAttribute("user")==null){
                %>
                <a href="<%=request.getContextPath()%>/jsp/signInUp.jsp"><li>Sign In</li></a>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
