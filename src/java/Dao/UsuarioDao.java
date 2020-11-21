/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

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
public class UsuarioDao {
    /**
     * @return 
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws org.hsqldb.cmdline.SqlToolError
     */
    
    private _DBConnector database;
    private Connection connection;
    
    public UsuarioDao() {
        try {
            database = new _DBConnector();
            connection = database.connect();
        } catch (SQLException | IOException | SqlToolError ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Usuario> getUsuarios()throws SQLException{
        String query = "select * from usuario";
        
        database.setDB("av1_db");
        connection = database.connect();
        
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario;
        
        Statement stmnt = connection.createStatement();
        ResultSet result = stmnt.executeQuery(query);
        while(result.next()){
            usuario = new Usuario();
            usuario.setId(result.getInt("id"));
            usuario.setNome(result.getString("nome"));
            usuario.setLogin(result.getString("login"));
            usuario.setSenha(result.getString("senha"));
            usuario.setTipo(result.getInt("tipo"));
            usuarios.add(usuario);
        }
        connection.close();
        return usuarios;
    }
    
    public Usuario getUsuario(String login){
        String query = "select * from usuario where login=?";
        Usuario usuario = new Usuario();
        try{
            database.setDB("av1_db");
            connection = database.connect();
            PreparedStatement stmnt = connection.prepareStatement(query);
            stmnt.setString(1, login);

            ResultSet result = stmnt.executeQuery();
            while(result.next()){
                usuario.setId(result.getInt("id"));
                usuario.setNome(result.getString("nome"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setTipo(result.getInt("tipo"));
            }
            connection.close();
            return usuario;
        }catch (SQLException e){
            return usuario;
        }
    }
    
}
