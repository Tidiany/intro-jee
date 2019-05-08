/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Modele;
import model.Role;
import model.Utilisateur;
import services.MarqueFacadeLocal;
import services.ModeleFacadeLocal;
import services.RoleFacadeLocal;
import services.Upload;
import services.UtilisateurFacadeLocal;

/**
 *
 * @author Tidiany
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2Mo au max pour un fichier
        maxFileSize = 1024 * 1024 * 10, //
        maxRequestSize = 1024 * 1024 * 50 //
)
public class UserServlet extends HttpServlet {

    @EJB
    private UtilisateurFacadeLocal userEJB;
    @EJB
    private RoleFacadeLocal roleEJB;
    @EJB
    private MarqueFacadeLocal marqueEJB;
    @EJB
    private ModeleFacadeLocal modeleEJB;

    private static final String chemin = "C://Users/Tidiany/Documents/NetBeansProjects/intro-jee/web/image/";

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        switch (action) {
            case "list":
                request.setAttribute("roles", roleEJB.findAll());
                request.setAttribute("users", userEJB.findAll());
                request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                break;
            case "location":
                request.setAttribute("marques", marqueEJB.findAll());
                request.getRequestDispatcher("/WEB-INF/location.jsp").forward(request, response);
                break;
            case "home":
//                request.setAttribute("user", request.getSession().getAttribute("user"));
                request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                break;
            case "getModelesByMarque":
                List<Modele> modeles = modeleEJB.getModelesByMarque(Integer.parseInt(request.getParameter("idMarque")));
                request.setAttribute("modeles", modeles);
//                System.out.println("Modeles: ");
//                modeles.stream().map(Modele::getLibelle).forEach(System.out::println);
//                modeles.stream().map(m->{
//                    return "<option value='"+m.getId()+"'>"+m.getLibelle()+"</option>";
//                }).forEach(System.out::println);
                String resultat = modeles.stream().map(m->{
                    return "<option value='"+m.getId()+"'>"+m.getLibelle()+"</option>";
                }).reduce("", (a, b)-> a+b);
//                System.out.println(resultat);
                response.getWriter().println(resultat);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }
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
        String action = request.getParameter("form");
        switch (action) {
            case "auth":
                String login = request.getParameter("login").trim();
                String password = request.getParameter("password").trim();
                if (login.equals("") || password.equals("")) {
                    request.setAttribute("message", "Veillez saisir le login et le mot de passe...");
                    request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                    return;
                }
                Utilisateur u = userEJB.getUserByLoginAndPassword(login, password);
                if (u != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", u);
                    request.setAttribute("userName", u.getPrenom() + " " + u.getNom().toUpperCase());
                    request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Login ou mot de passe incorrect...");
                    request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
                }
                break;
            case "inscription":
                try {
                    Part p = request.getPart("photo");
                    String fileName = "noprofil.png";
                    if (p != null) {
                        fileName = chemin + p.getSubmittedFileName();
                        InputStream stream = p.getInputStream();
                        System.out.println("Chemin==========================>>>>>>>>> " + fileName);
                        Upload.saveFile(stream, fileName);
                    }
                    String nom = request.getParameter("nom");
                    String prenom = request.getParameter("prenom");
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setChanged(0);
                    utilisateur.setNom(nom);
                    utilisateur.setPrenom(prenom);
                    utilisateur.setPassword("passer");
                    utilisateur.setRole(roleEJB.find(Integer.parseInt(request.getParameter("role"))));
                    utilisateur.setPhoto(p != null ? (!p.getSubmittedFileName().equals("") ? p.getSubmittedFileName() : fileName) : fileName);
                    int id = userEJB.getLastInsertId() + 1;
                    utilisateur.setLogin(prenom.substring(0, 1) + nom + id);
                    userEJB.create(utilisateur);
                    request.setAttribute("message", "Utilisateur enregistrée...");

                } catch (Exception e) {
                    request.setAttribute("message", e.getMessage());
                    e.printStackTrace();
                }

                request.setAttribute("roles", roleEJB.findAll());
                request.setAttribute("users", userEJB.findAll());
                request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                break;
            case "edit":
                try {
                    Part p = request.getPart("photo");
                    String fileName = "noprofil.png";
                    if (p != null) {
                        fileName = chemin + p.getSubmittedFileName();
                        InputStream stream = p.getInputStream();
                        System.out.println("Chemin modifier==========================>>>>>>>>> " + fileName);
                        Upload.saveFile(stream, fileName);
                    }
                    String nom = request.getParameter("nom");
                    String prenom = request.getParameter("prenom");
                    Utilisateur utilisateur = userEJB.find(Integer.parseInt(request.getParameter("idUser")));
                    utilisateur.setChanged(1);
                    utilisateur.setNom(nom);
                    utilisateur.setPrenom(prenom);
                    utilisateur.setRole(roleEJB.find(Integer.parseInt(request.getParameter("role"))));
//                    String tof = p != null ? p.getSubmittedFileName() : fileName;
                    if (p != null) {
                        if (!(p.getSubmittedFileName().equals(utilisateur.getPhoto()) || p.getSubmittedFileName().equals(""))) {
                            utilisateur.setPhoto(p.getSubmittedFileName());
                        }
                    }
                    userEJB.edit(utilisateur);
                    request.setAttribute("message", "Utilisateur modifié avec succée...");
                } catch (Exception e) {
                    request.setAttribute("message", e.getMessage());
                    e.printStackTrace();
                }
                request.setAttribute("roles", roleEJB.findAll());
                request.setAttribute("users", userEJB.findAll());
                request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                break;
            case "delete":
                Utilisateur utilisateur = userEJB.find(Integer.parseInt(request.getParameter("idUser")));
                userEJB.remove(utilisateur);
                request.setAttribute("roles", roleEJB.findAll());
                request.setAttribute("users", userEJB.findAll());
                request.getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
