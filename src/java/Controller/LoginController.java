package Controller;

import Dao.AlunosDao;
import Dao.UsuarioDao;
import Model.Aluno;
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
    Usuario usuarioObj;
    
    public LoginController(String usr, String pwd){
        this.usuario = usr;
        this.senha = pwd;
    }
    
    public String validarUsuario(){
        if("admin".equals(usuario) && "admin".equals(senha))
            return "valido";
        UsuarioDao usrDao = new UsuarioDao();
        usuarioObj = usrDao.getUsuario(usuario);
        if(usuarioObj.isValid()){
            if(usuarioObj.getSenha().equals(senha)){
                return "valido";
            }
        }else{
            AlunosDao alunosDao = new AlunosDao();
            Aluno aluno = alunosDao.getAluno(usuario);
            if(aluno.getMatricula()!=null)
                return "valido";
        }
        return invalid;
    }
    
    public String getUserType(){
        if("admin".equals(usuario) && "admin".equals(senha))
            return "professor";
        UsuarioDao usrDao = new UsuarioDao();
        usuarioObj = usrDao.getUsuario(usuario);
        if(usuarioObj.getTipo()==1){
            return "professor";
        }
        return "aluno";
    }
    
}
