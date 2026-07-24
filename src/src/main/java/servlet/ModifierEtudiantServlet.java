package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Etudiant;
import dao.EtudiantDAO;
import java.io.IOException;

@WebServlet("/modifier")
public class ModifierEtudiantServlet extends HttpServlet {

    private final EtudiantDAO dao = new EtudiantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/etudiants");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Etudiant e = dao.getEtudiantById(id);
            if (e == null) {
                resp.sendRedirect(req.getContextPath() + "/etudiants?error=introuvable");
                return;
            }
            req.setAttribute("etudiant", e);
            req.getRequestDispatcher("/WEB-INF/modifierEtudiant.jsp").forward(req, resp);
        } catch (NumberFormatException ex) {
            resp.sendRedirect(req.getContextPath() + "/etudiants");
        }
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

        int id;
        int niveau;

        try {
            id     = Integer.parseInt(req.getParameter("id"));
            niveau = Integer.parseInt(req.getParameter("niveau"));
        } catch (NumberFormatException e) {
            req.setAttribute("erreur", "Données invalides.");
            req.getRequestDispatcher("/WEB-INF/modifierEtudiant.jsp").forward(req, resp);
            return;
        }

        String nom     = req.getParameter("nom");
        String prenom  = req.getParameter("prenom");
        String email   = req.getParameter("email");
        String filiere = req.getParameter("filiere");

        if (nom == null || nom.isBlank() || prenom == null || prenom.isBlank()
                || email == null || email.isBlank()) {
            req.setAttribute("erreur", "Tous les champs obligatoires doivent être remplis.");
            req.setAttribute("etudiant", dao.getEtudiantById(id));
            req.getRequestDispatcher("/WEB-INF/modifierEtudiant.jsp").forward(req, resp);
            return;
        }

        Etudiant updated = new Etudiant(id, nom.trim(), prenom.trim(),
                                        email.trim(), filiere, niveau);
        dao.updateEtudiant(updated);

        resp.sendRedirect(req.getContextPath() + "/etudiants?success=modifie");
    }
}
