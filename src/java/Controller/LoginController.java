package Controller;

import Dao.UsuarioDao;
import Model.Usuario;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author plino
 */
public class LoginController {
    
    private final String usuario;
    private final String senha;
    final String invalid = "Usuario ou senha invalidos";
    
    public LoginController(String usr, String pwd){
        this.usuario = usr;
        this.senha = pwd;
    }
    
    public String validarUsuario(){
        UsuarioDao usrDao = new UsuarioDao();
        Usuario usuarioObj = usrDao.getUsuario(usuario);
        if(usuarioObj.isValid()){
            if(usuarioObj.getSenha().equals(senha)){
                return "valido";
            }
        }
        return invalid;
    }
    
}
