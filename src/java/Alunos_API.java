/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.AlunosDao;
import Model.Aluno;
import com.google.gson.Gson;
import java.io.BufferedReader;
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
@WebServlet(urlPatterns = {"/aluno"})
public class Alunos_API extends HttpServlet {
    AlunosDao alunosDao = new AlunosDao();
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
        List<Aluno> alunos;
        PrintWriter out = response.getWriter();
        String matricula = request.getParameter("matricula");
        Gson g = new Gson();
        if(matricula==null){
            try {
                alunos = alunosDao.getAlunos();
                out.println(g.toJson(alunos));
            } catch (SQLException ex) {
                Logger.getLogger(Login_API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
            Aluno aluno = alunosDao.getAluno(matricula);
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
        Aluno aluno = new Gson().fromJson(request.getReader(), Aluno.class);
        PrintWriter out = response.getWriter();
        
        int resDao = alunosDao.createAluno(aluno.getMatricula(), aluno.getAlunoNome(), aluno.getIdCurso());
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
        Aluno aluno = new Gson().fromJson(request.getReader(), Aluno.class);
        PrintWriter out = response.getWriter();
        
        int resDao = alunosDao.updateAluno(aluno.getMatricula(), aluno.getAlunoNome(), aluno.getIdCurso());
        
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
        String matricula = request.getParameter("matricula");
        if(matricula!=null){
            int resDao = alunosDao.deleteAluno(matricula);
            if(resDao>0){
                out.print(200);
            }else{
                out.print(405);
            }
        }
    }
}
