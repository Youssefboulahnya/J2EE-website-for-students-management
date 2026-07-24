<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <title>Liste des Étudiants</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background: #fff; color: #222; }
        .top { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        a.btn { padding: 8px 16px; background: #3a7bd5; color: white; text-decoration: none; border-radius: 4px; font-size: 14px; }
        a.btn-danger { background: #d9534f; }
        a.btn-edit { background: #5cb85c; }
        table { width: 100%; border-collapse: collapse; font-size: 14px; }
        th, td { border: 1px solid #eee; padding: 12px; text-align: left; }
        th { background: #f5f5f5; }
        .flash { padding: 10px; margin-bottom: 20px; border-radius: 4px; }
        .success { background: #dff0d8; color: #3c763d; border: 1px solid #d6e9c6; }
        .error { background: #f2dede; color: #a94442; border: 1px solid #ebccd1; }
    </style>
</head>
<body>
    <div class="top">
        <h2>Liste des Étudiants</h2>
        <div style="display:flex; gap:10px;">
            <a href="${pageContext.request.contextPath}/ajouter" class="btn">+ Ajouter</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Déconnexion</a>
        </div>
    </div>

    <%-- Flash Messages using EL --%>
    <c:if test="${param.success == 'ajoute'}"><div class="flash success">Étudiant ajouté.</div></c:if>
    <c:if test="${param.success == 'modifie'}"><div class="flash success">Étudiant modifié.</div></c:if>
    <c:if test="${param.success == 'supprime'}"><div class="flash success">Étudiant supprimé.</div></c:if>

    <c:choose>
        <c:when test="${empty etudiants}">
            <p>Aucun étudiant enregistré.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>ID</th><th>Nom</th><th>Prénom</th><th>Email</th><th>Filière</th><th>Niveau</th><th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="e" items="${etudiants}">
                        <tr>
                            <td>${e.id}</td>
                            <td>${e.nom}</td>
                            <td>${e.prenom}</td>
                            <td>${e.email}</td>
                            <td>${e.filiere}</td>
                            <td>${e.niveau}</td>
                            <td style="display:flex; gap:8px;">
                                <a href="${pageContext.request.contextPath}/modifier?id=${e.id}" class="btn btn-edit">Modifier</a>
                                <a href="${pageContext.request.contextPath}/supprimer?id=${e.id}" class="btn btn-danger" onclick="return confirm('Supprimer ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>