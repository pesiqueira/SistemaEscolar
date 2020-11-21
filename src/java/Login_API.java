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
        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios;
        PrintWriter out = response.getWriter();
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        Gson g = new Gson();
        if(login==null){
            try {
                usuarios = usuarioDao.getUsuarios();
                out.println(g.toJson(usuarios));
            } catch (SQLException ex) {
                Logger.getLogger(Login_API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            LoginController controller = new LoginController(login,senha);
            String usuarioValido = controller.validarUsuario();
            out.println(usuarioValido);
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
        
    }
}
