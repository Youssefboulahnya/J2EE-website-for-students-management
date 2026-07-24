<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8"/>
  <title>Ajouter un Étudiant</title>
  <style>
    body { font-family: Arial, sans-serif; margin: 40px; }
    .form-group { margin-bottom: 16px; }
    label { display: block; margin-bottom: 6px; }
    input, select { width: 320px; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
    button { padding: 9px 20px; background: #3a7bd5; color: white; border: none; border-radius: 4px; cursor: pointer; }
    .error { color: red; margin-bottom: 16px; }
  </style>
</head>
<body>
  <h2>Ajouter un Étudiant</h2>

  <c:if test="${not empty erreur}">
    <p class="error">${erreur}</p>
  </c:if>

  <form method="post" action="${pageContext.request.contextPath}/ajouter">
    <div class="form-group">
      <label>Nom</label>
      <input type="text" name="nom" required/>
    </div>
    <div class="form-group">
      <label>Prénom</label>
      <input type="text" name="prenom" required/>
    </div>
    <div class="form-group">
      <label>Email</label>
      <input type="email" name="email" required/>
    </div>
    <div class="form-group">
      <label>Filière</label>
      <select name="filiere" required>
        <option value="" disabled selected>Choisir</option>
        <c:forEach var="f" items="Informatique,Mathématiques,Physique,Génie Civil,Génie Électrique,Commerce,Droit,Médecine">
            <option value="${f}">${f}</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label>Niveau</label>
      <select name="niveau" required>
        <option value="" disabled selected>Choisir</option>
        <c:forEach var="n" begin="1" end="5">
            <option value="${n}">${n}</option>
        </c:forEach>
      </select>
    </div>
    <button type="submit">Ajouter</button>
    <a href="${pageContext.request.contextPath}/etudiants" style="margin-left:10px;">Annuler</a>
  </form>
</body>
</html>