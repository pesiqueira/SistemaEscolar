/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.UsuarioDao;
import Model.Usuario;
import Controller.LoginController;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author plino
 */
@WebServlet(urlPatterns = {"/login"})
public class Login_API extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        if(login==null){
            out.println("Invalid Login or password");
        }else{
            LoginController controller = new LoginController(login,senha);
            String usuarioValido = controller.validarUsuario();
            if("valido".equals(usuarioValido)){
                HttpSession sessao = request.getSession();
                String userType = controller.getUserType();
                Cookie cookie = new Cookie("login",login);
                sessao.setAttribute("usuarioValido", usuarioValido);
                sessao.setAttribute("usuarioTipo", userType);
                response.addCookie(cookie);
                response.sendRedirect(userType+"/index.jsp");
            }else{
                out.println("Invalid Login or password");
            }
        }
    }
}
