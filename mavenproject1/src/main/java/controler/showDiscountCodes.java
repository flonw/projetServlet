package controler;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.DAOException;
import modele.DAO;
import modele.DataSourceFactory;
import modele.DiscountEntity;
import java.io.PrintWriter;

@WebServlet(name = "showDiscountCodes", urlPatterns = {"/showDiscountCodes"})
public class showDiscountCodes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Créér le ExtendedDAO avec sa source de données
            DAO dao = new DAO(DataSourceFactory.getDataSource());

            List<DiscountEntity> DiscountList = dao.ListeDesDiscount();

            request.setAttribute("listeCodeDiscount", DiscountList);

            // On continue vers la page JSP sélectionnée
            request.getRequestDispatcher("Views/ViewDiscountCode.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch (action) {
            case "DELETE":
                System.out.println("DELETE");
                deleteDiscount(request, response);
                break;
            case "ADD":
                System.out.println("ADD");
                addDiscount(request, response);
            default:
                
        }

        /*if (request.getParameter("action") == "DELETE") {
            deleteDiscount(request, response);
            System.out.println("toto");
        }
        if (request.getParameter("action") == "ADD") {

            addDiscount(request, response);
        }
        System.out.println(request.getParameter("action"));*/
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void deleteDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String parametre = request.getParameter("code");
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        dao.deleteDiscount(parametre);

    }

    public void addDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String parametre1 = request.getParameter("code");
        double parametre2 = Double.parseDouble(request.getParameter("taux"));
        
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        dao.addDiscount(parametre1, parametre2);
        processRequest(request, response);
        
    }
}
