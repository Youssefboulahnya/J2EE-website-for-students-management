package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Etudiant;
import dao.EtudiantDAO;
import java.io.IOException;

@WebServlet("/ajouter")
public class AjouterEtudiantServlet extends HttpServlet {

    private final EtudiantDAO dao = new EtudiantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/ajouterEtudiant.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setCharacterEncoding("UTF-8");

        String nom     = req.getParameter("nom");
        String prenom  = req.getParameter("prenom");
        String email   = req.getParameter("email");
        String filiere = req.getParameter("filiere");
        int    niveau;

        try {
            niveau = Integer.parseInt(req.getParameter("niveau"));
        } catch (NumberFormatException e) {
            req.setAttribute("erreur", "Le niveau doit être un nombre entier.");
            req.getRequestDispatcher("/WEB-INF/ajouterEtudiant.jsp").forward(req, resp);
            return;
        }

        if (nom == null || nom.isBlank() || prenom == null || prenom.isBlank()
                || email == null || email.isBlank()) {
            req.setAttribute("erreur", "Tous les champs obligatoires doivent être remplis.");
            req.getRequestDispatcher("/WEB-INF/ajouterEtudiant.jsp").forward(req, resp);
            return;
        }

        
        Etudiant e = new Etudiant(0, nom.trim(), prenom.trim(),
                                  email.trim(), filiere, niveau);
        dao.addEtudiant(e);

        resp.sendRedirect(req.getContextPath() + "/etudiants?success=ajoute");
    }
}
