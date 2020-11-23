 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.AlunosDao;
import Dao.AvaliacaoDao;
import Model.Aluno;
import Model.Avaliacao;
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
@WebServlet(urlPatterns = {"/avaliacao"})
public class Avaliacao_API extends HttpServlet {
    AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
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
        List<Avaliacao> alunos;
        PrintWriter out = response.getWriter();
        String matricula = request.getParameter("matricula");
        Gson g = new Gson();
        if(matricula==null){
            try {
                alunos = avaliacaoDao.getAvaliacoes();
                out.println(g.toJson(alunos));
            } catch (SQLException ex) {
                Logger.getLogger(Login_API.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
            List<Avaliacao> aluno = avaliacaoDao.getAvaliacaoByMatricula(matricula);
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
        Avaliacao avaliacao = new Gson().fromJson(request.getReader(), Avaliacao.class);
        PrintWriter out = response.getWriter();
        
        int resDao = avaliacaoDao.createAvaliacao(avaliacao);
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
        Avaliacao avaliacao = new Gson().fromJson(request.getReader(), Avaliacao.class);
        PrintWriter out = response.getWriter();
        
        int resDao = avaliacaoDao.updateAvalicao(avaliacao);
        
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
        String idAvaliacao = request.getParameter("idAvaliacao");
        if(idAvaliacao!=null){
            int resDao = avaliacaoDao.deleteAvaliacao(idAvaliacao);
            if(resDao>0){
                out.print(200);
            }else{
                out.print(405);
            }
        }
    }
}
