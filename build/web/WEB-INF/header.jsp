<%-- 
    Document   : home
    Created on : 22 avr. 2019, 15:02:47
    Author     : Tidiany
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Utilisateurs</title>
        <link href="css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="menu">
            <nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand" href="user?action=home">Intro JEE</a>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <!--                        <li class="nav-item active">
                                                    <a class="nav-link" href="#"> <span class="sr-only">(current)</span></a>
                                                </li>-->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Administration
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropdown01">
                                <a class="dropdown-item" href="user?action=list">Utilisateur</a>
                                <a class="dropdown-item" href="role?action=list">Role</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="user?action=location">Location</a>
                        </li>
                        <!--                        <li class="nav-item">
                                                    <a class="nav-link" href="#">Link</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link disabled" href="#">Disabled</a>
                                                </li>-->
                    </ul>
                    <form class="form-inline mt-2 mt-md-0">
                        <input class="form-control mr-sm-2" type="text" placeholder="Rechercher...">
                        <button class="btn btn-primary my-2 my-sm-0" type="submit">Rechercher</button>
                    </form>
                </div>
            </nav>
        </div>
        <div id="container">
            <%--<%@include file="jspf/default.jspf" %>--%>