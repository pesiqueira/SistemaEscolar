/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import Model.Avaliacao;
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
public class AvaliacaoDao {
    /**
     * @return 
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    
    private _DBConnector database;
    private Connection connection;
    
    public AvaliacaoDao() {
        try {
            database = new _DBConnector();
            connection = database.connect();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Avaliacao> getAvaliacoes()throws SQLException{
        String query = "select * from avaliacao";
        
        database.setDB("av1_db");
        connection = database.connect();
        
        List<Avaliacao> avaliacoes = new ArrayList<>();
        Avaliacao avaliacao;
        
        Statement stmnt = connection.createStatement();
        ResultSet result = stmnt.executeQuery(query);
        while(result.next()){
            avaliacao = new Avaliacao();
            avaliacao.setIdAvaliacao(result.getString("idAVALIACAO"));
            avaliacao.setMateria(result.getString("idDISCIPLINA"));
            avaliacao.setMatricula(result.getString("idMATRICULA"));
            avaliacao.setAv1(result.getFloat("TRABALHO_ACADEMICO_AV1"));
            avaliacao.setAv2(result.getFloat("TRABALHO_ACADEMICO_AV2"));
            avaliacao.setAv3(result.getFloat("TRABALHO_ACADEMICO_AV3"));
            avaliacao.setAps1(result.getFloat("APS1"));
            avaliacao.setAps2(result.getFloat("APS2"));
            avaliacoes.add(avaliacao);
        }
        connection.close();
        return avaliacoes;
    }
    
    public List<Avaliacao> getAvaliacaoByMatricula(String matricula){
        String query = "select * from avaliacao where idMATRICULA=?";
        Avaliacao avaliacao = new Avaliacao();
        List<Avaliacao> avaliacoes = new ArrayList<>();
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, matricula);

            ResultSet result = stmnt.executeQuery();
            while(result.next()){
                avaliacao = new Avaliacao();
                avaliacao.setIdAvaliacao(result.getString("idAVALIACAO"));
                avaliacao.setIdDisciplina(result.getString("idDISCIPLINA"));
                avaliacao.setMatricula(result.getString("idMATRICULA"));
                avaliacao.setAv1(result.getFloat("TRABALHO_ACADEMICO_AV1"));
                avaliacao.setAv2(result.getFloat("TRABALHO_ACADEMICO_AV2"));
                avaliacao.setAv3(result.getFloat("TRABALHO_ACADEMICO_AV3"));
                avaliacao.setAps1(result.getFloat("APS1"));
                avaliacao.setAps2(result.getFloat("APS2"));
                avaliacoes.add(avaliacao);
            }
            connection.close();
            return avaliacoes;
        }catch (SQLException e){
            return avaliacoes;
        }
    }
    
    public int createAvaliacao(Avaliacao avaliacao){
        String query = "INSERT INTO av1_db.avaliacao (idAVALIACAO, idMATRICULA, idDISCIPLINA, TRABALHO_ACADEMICO_AV1, APS1, TRABALHO_ACADEMICO_AV2, APS2, TRABALHO_ACADEMICO_AV3) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, avaliacao.getIdAvaliacao());
            stmnt.setString(2, avaliacao.getMatricula());
            stmnt.setString(3, avaliacao.getIdDisciplina());
            stmnt.setFloat(4, avaliacao.getAv1());
            stmnt.setFloat(5, avaliacao.getAps1());
            stmnt.setFloat(6, avaliacao.getAv2());
            stmnt.setFloat(7, avaliacao.getAps2());
            stmnt.setFloat(8, avaliacao.getAv3());
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
    
    public int updateAvalicao(Avaliacao avaliacao){
        String query = "UPDATE av1_db.avaliacao SET idAVALIACAO= ?, idMATRICULA=?, idDISCIPLINA=?, TRABALHO_ACADEMICO_AV1=?, APS1=?, TRABALHO_ACADEMICO_AV2=?, APS2=?, TRABALHO_ACADEMICO_AV3=? WHERE idAVALIACAO=?;";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            
            stmnt.setString(1, avaliacao.getIdAvaliacao());
            stmnt.setString(2, avaliacao.getMatricula());
            stmnt.setString(3, avaliacao.getIdDisciplina());
            stmnt.setFloat(4, avaliacao.getAv1());
            stmnt.setFloat(5, avaliacao.getAps1());
            stmnt.setFloat(6, avaliacao.getAv2());
            stmnt.setFloat(7, avaliacao.getAps2());
            stmnt.setFloat(8, avaliacao.getAv3());
            
            stmnt.setString(9, avaliacao.getIdAvaliacao());
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
    public int deleteAvaliacao(String idAvaliacao){
        String query = "DELETE FROM av1_db.AVALIACAO WHERE idAVALIACAO = ?;";
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, idAvaliacao);
            
            int result = stmnt.executeUpdate();
            connection.close();
            return result;
        }catch (SQLException e){
            return 0;
        }
    }
    
}
