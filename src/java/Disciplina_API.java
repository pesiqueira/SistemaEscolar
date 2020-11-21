/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.DisciplinaDao;
import Model.Avaliacao;
import Model.Disciplina;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author plino
 */
@WebServlet(urlPatterns = {"/disciplina"})
public class Disciplina_API extends HttpServlet {
    DisciplinaDao disciplinaDao = new DisciplinaDao();
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
        List<Disciplina> alunos;
        PrintWriter out = response.getWriter();
        String idDisciplina = request.getParameter("idDisciplina");
        Gson g = new Gson();
        if(idDisciplina==null){
            try {
                alunos = disciplinaDao.getDisciplinas();
                out.println(g.toJson(alunos));
            } catch (SQLException ex) {
                Logger.getLogger(Login_API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
            Disciplina aluno = disciplinaDao.getDisciplina(idDisciplina);
            out.println(g.toJson(aluno));
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
        Disciplina disciplina = new Gson().fromJson(request.getReader(), Disciplina.class);
        PrintWriter out = response.getWriter();
        
        int resDao = disciplinaDao.createDisciplina(disciplina);
        
        if(resDao>0){
            out.print(200);
        }else{
            out.print(405);
        }
        
        
    }
    /**
     * Handles the HTTP <code>PUT</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Disciplina disciplina = new Gson().fromJson(request.getReader(), Disciplina.class);
        PrintWriter out = response.getWriter();
        
        int resDao = disciplinaDao.updateDisciplina(disciplina);
        
        if(resDao>0){
            out.print(200);
        }else{
            out.print(405);
        }
    }
    
    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String idDisciplina = request.getParameter("idDisciplina");
        if(idDisciplina!=null){
            int resDao = disciplinaDao.deleteDisciplina(idDisciplina);
            if(resDao>0){
                out.print(200);
            }else{
                out.print(405);
            }
        }
    }
}
