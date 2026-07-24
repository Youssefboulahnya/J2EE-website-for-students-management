<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <title>Modifier l'Étudiant</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .form-group { margin-bottom: 16px; }
        label { display: block; margin-bottom: 6px; }
        input, select { width: 320px; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        button { padding: 9px 20px; background: #f0ad4e; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h2>Modifier l'Étudiant</h2>

    <c:if test="${not empty erreur}">
        <p style="color:red;">${erreur}</p>
    </c:if>

    <c:choose>
        <c:when test="${empty etudiant}">
            <p>Étudiant introuvable. <a href="${pageContext.request.contextPath}/etudiants">Retour</a></p>
        </c:when>
        <c:otherwise>
            <form method="post" action="${pageContext.request.contextPath}/modifier">
                <input type="hidden" name="id" value="${etudiant.id}"/>

                <div class="form-group">
                    <label>Nom</label>
                    <input type="text" name="nom" value="${etudiant.nom}" required/>
                </div>
                <div class="form-group">
                    <label>Prénom</label>
                    <input type="text" name="prenom" value="${etudiant.prenom}" required/>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" value="${etudiant.email}" required/>
                </div>

                <div class="form-group">
                    <label>Filière</label>
                    <select name="filiere" required>
                        <c:forEach var="f" items="Informatique,Mathématiques,Physique,Génie Civil,Génie Électrique,Commerce,Droit,Médecine">
                            <option value="${f}" ${f == etudiant.filiere ? 'selected' : ''}>${f}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-group">
                    <label>Niveau</label>
                    <select name="niveau" required>
                        <c:forEach var="n" begin="1" end="5">
                            <option value="${n}" ${n == etudiant.niveau ? 'selected' : ''}>${n}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit">Enregistrer</button>
                <a href="${pageContext.request.contextPath}/etudiants" style="margin-left:10px;">Annuler</a>
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>