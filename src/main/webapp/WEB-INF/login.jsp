<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <title>Connexion</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            background: #f0f0f0; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
            height: 100vh; 
            margin: 0; 
        }
        .box { 
            background: #fff; 
            padding: 32px 40px; 
            border: 1px solid #ccc; 
            border-radius: 6px; 
            width: 320px; 
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        h2 { margin: 0 0 24px; font-size: 20px; text-align: center; color: #333; }
        label { display: block; margin-bottom: 6px; font-size: 14px; color: #666; }
        input { 
            width: 100%; 
            padding: 10px; 
            margin-bottom: 16px; 
            border: 1px solid #ccc; 
            border-radius: 4px; 
            box-sizing: border-box; 
            font-size: 14px; 
        }
        button { 
            width: 100%; 
            padding: 10px; 
            background: #3a7bd5; 
            color: white; 
            border: none; 
            border-radius: 4px; 
            font-size: 14px; 
            cursor: pointer; 
            transition: background 0.3s;
        }
        button:hover { background: #2f6abf; }
        .error { 
            color: #a94442; 
            background-color: #f2dede; 
            border: 1px solid #ebccd1; 
            padding: 10px; 
            border-radius: 4px; 
            font-size: 13px; 
            margin-bottom: 14px; 
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="box">
        <h2>Gestion Scolaire</h2>

        <%-- JSTL replacement for if(request.getAttribute("error") != null) --%>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="username">Utilisateur</label>
            <input type="text" id="username" name="username" placeholder="Admin" required />

            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="password" placeholder="••••••••" required />

            <button type="submit">Se connecter</button>
        </form>
    </div>
</body>
</html>