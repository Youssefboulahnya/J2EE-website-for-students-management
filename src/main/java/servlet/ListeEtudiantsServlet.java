package servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Etudiant;
import dao.EtudiantDAO;
import java.io.IOException;
import java.util.List;

@WebServlet("/etudiants")
public class ListeEtudiantsServlet extends HttpServlet {

    private final EtudiantDAO dao = new EtudiantDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("admin") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<Etudiant> liste = dao.getAllEtudiants();
        req.setAttribute("etudiants", liste);
        req.getRequestDispatcher("/WEB-INF/listeEtudiant.jsp").forward(req, resp);
    }
}
