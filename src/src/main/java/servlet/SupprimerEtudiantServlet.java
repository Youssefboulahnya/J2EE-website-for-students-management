package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.EtudiantDAO;
import java.io.IOException;

@WebServlet("/supprimer")
public class SupprimerEtudiantServlet extends HttpServlet {

    private final EtudiantDAO dao = new EtudiantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String idParam = req.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                dao.deleteEtudiant(id);
                resp.sendRedirect(req.getContextPath() + "/etudiants?success=supprime");
            } catch (NumberFormatException e) {
                resp.sendRedirect(req.getContextPath() + "/etudiants");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/etudiants");
        }
    }
}
