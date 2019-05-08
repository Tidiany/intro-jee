<%-- 
    Document   : index
    Created on : 22 avr. 2019, 13:09:29
    Author     : Tidiany
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authentification</title>
        <link href="css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="connection">
            <h1 id="header">Authentification</h1>
            <form action="" method="post">
                <div class="form-group">
                    <label for="login">Login</label>
                    <input id="login" type="text" name="login" class="form-control"/>
                    <input id="action" type="hidden" name="form" value="auth" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input id="password" type="password" name="password" class="form-control"/>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary">Connection</button>
                </div>
            </form>
            <span class="msg msg-error">${message}</span>
        </div>
        <script src="js/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="js/bootstrap/bootstrap.js" type="text/javascript"></script>
    </body>
</html>
