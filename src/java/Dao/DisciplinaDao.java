/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.Disciplina;
import java.io.IOException;
import java.sql.SQLException;
import org.hsqldb.cmdline.SqlToolError;
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
public class DisciplinaDao {
    /**
     * @return 
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws org.hsqldb.cmdline.SqlToolError
     */
    
    private _DBConnector database;
    private Connection connection;
    
    public DisciplinaDao() {
        try {
            database = new _DBConnector();
            connection = database.connect();
        } catch (SQLException | IOException | SqlToolError ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Disciplina> getDisciplinas()throws SQLException{
        String query = "select * from disciplina";
        
        database.setDB("av1_db");
        connection = database.connect();
        
        List<Disciplina> disciplinas = new ArrayList<>();
        Disciplina disciplina;
        
        Statement stmnt = connection.createStatement();
        ResultSet result = stmnt.executeQuery(query);
        while(result.next()){
            disciplina = new Disciplina();
            disciplina.setIdDisciplina(result.getString("idDISCIPLINA"));
            disciplina.setNomeDisciplina(result.getString("DISCIPLINA_NOME"));
            
            disciplinas.add(disciplina);
        }
        connection.close();
        return disciplinas;
    }
    
    public Disciplina getDisciplina(String idDisciplina){
        String query = "select * from DISCIPLINA where idDISCIPLINA=?";
        Disciplina disciplina = new Disciplina();
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, idDisciplina);

            ResultSet result = stmnt.executeQuery();
            while(result.next()){
                disciplina = new Disciplina();
                disciplina.setNomeDisciplina(result.getString("DISCIPLINA_NOME"));
                disciplina.setIdDisciplina(result.getString("idDISCIPLINA"));
            }
            connection.close();
            return disciplina;
        }catch (SQLException e){
            return disciplina;
        }
    }
    
    public int createDisciplina(Disciplina disciplina){
        String query = "INSERT INTO av1_db.DISCIPLINA (idDISCIPLINA, DISCIPLINA_NOME) VALUES (?, ?);";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            
            stmnt.setString(1, disciplina.getIdDisciplina());
            stmnt.setString(2, disciplina.getNomeDisciplina());
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
    
    public int updateDisciplina(Disciplina disciplina){
        String query = "UPDATE av1_db.DISCIPLINA SET idDISCIPLINA=?, DISCIPLINA_NOME=? WHERE idDISCIPLINA=?;";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            
            stmnt.setString(1, disciplina.getIdDisciplina());
            stmnt.setString(2, disciplina.getNomeDisciplina());
            
            stmnt.setString(3, disciplina.getIdDisciplina());
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
    public int deleteDisciplina(String idDisciplina){
        String query = "DELETE FROM av1_db.DISCIPLINA WHERE idDISCIPLINA = ?;";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, idDisciplina);
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
}
