/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.Aluno;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Usuario;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


 *
 * @author John
 */
public class AlunosDao {
    /**
     * @return 
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    
    private _DBConnector database;
    private Connection connection;
    
    public AlunosDao() {
        try {
            database = new _DBConnector();
            connection = database.connect();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Aluno> getAlunos()throws SQLException{
        String query = "select * from aluno";
        
        database.setDB("av1_db");
        connection = database.connect();
        
        List<Aluno> alunos = new ArrayList<>();
        Aluno aluno;
        
        Statement stmnt = connection.createStatement();
        ResultSet result = stmnt.executeQuery(query);
        while(result.next()){
            aluno = new Aluno();
            aluno.setAlunoNome(result.getString("ALUNO_NOME"));
            aluno.setMatricula(result.getString("MATRICULA"));
            aluno.setIdCurso(result.getString("CURSO_idCURSO"));
            aluno.setIdUsuario(result.getString("idUSUARIO"));
            alunos.add(aluno);
        }
        connection.close();
        return alunos;
    }
    
    public Aluno getAluno(String matricula){
        String query = "select * from aluno where MATRICULA=?";
        Aluno aluno = new Aluno();
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, matricula);

            ResultSet result = stmnt.executeQuery();
            while(result.next()){
                aluno.setAlunoNome(result.getString("ALUNO_NOME"));
                aluno.setMatricula(result.getString("MATRICULA"));
                aluno.setIdCurso(result.getString("CURSO_idCURSO"));
                aluno.setIdUsuario(result.getString("idUSUARIO"));
            }
            connection.close();
            return aluno;
        }catch (SQLException e){
            return aluno;
        }
    }
    
    public int createAluno(String matricula, String nome, String idCurso, String idUsuario){
        String query = "INSERT INTO `ALUNO` (`MATRICULA`, `ALUNO_NOME`, `CURSO_idCURSO`, `idusuario`) VALUES(?, ?, ?,?);";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, matricula);
            stmnt.setString(2, nome);
            stmnt.setString(3, idCurso);
            stmnt.setString(4, idUsuario);
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
    public int updateAluno(String matricula, String nome, String idCurso){
        String query = "UPDATE aluno SET MATRICULA = ?, ALUNO_NOME = ?, CURSO_idCurso=?  WHERE MATRICULA = ? ";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, matricula);
            stmnt.setString(2, nome);
            stmnt.setString(3, idCurso);
            
            stmnt.setString(4, matricula);
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
    public int deleteAluno(String matricula){
        String query = "DELETE FROM `ALUNO` WHERE MATRICULA = ?;";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, matricula);
            
            int result = stmnt.executeUpdate(query);
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
}
