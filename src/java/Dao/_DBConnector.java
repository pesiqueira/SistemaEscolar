/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public final class _DBConnector{
    static final String usuario = "root";
    static final String senha = "12345678";
    String urlDB = "jdbc:mysql://localhost/?user="+usuario+"&password="+senha+"&useSSL=false&allowPublicKeyRetrieval=true";
    static boolean dbCreated = false;
    
    
    public _DBConnector() throws SQLException, IOException{
        if(!dbCreated){
            this.loadDB();
            this.createUsersDB();  
            if(!isDisciplinasCriadas())
                createDisciplinas();
        }
        dbCreated = true;
    }
    
    public Connection connect() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver"); /* Aqui registra */
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(_DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Estabelecendo uma conexao...\n");
        Connection connection = DriverManager.getConnection(urlDB);
        return connection;
    }
    
    public void setDB(String dbName){
        this.urlDB = "jdbc:mysql://localhost/" + dbName + "?user="+usuario+"&password="+senha+"&useSSL=false&allowPublicKeyRetrieval=true";
    }
    
    public void disconnect() throws SQLException {   
        System.out.println("\nFinalizando uma conexao...");
//        connection.close();
    }
    
    
    public void loadDB() throws SQLException, IOException {
        Connection connection = connect();
        String sql1 = "CREATE SCHEMA IF NOT EXISTS `av1_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;";


        String sql2 = "USE `av1_db` ; ";

        String sql3 ="CREATE TABLE IF NOT EXISTS `av1_db`.`DISCIPLINA` ( "
        + "  `idDISCIPLINA` INT NOT NULL, "
        + "  `DISCIPLINA_NOME` VARCHAR(45) NULL, "
        + "  PRIMARY KEY (`idDISCIPLINA`)) "
        + "ENGINE = InnoDB; ";


        String sql4 = ""
        + "CREATE TABLE IF NOT EXISTS `av1_db`.`CURSO` ( "
        + "  `idCURSO` INT NOT NULL, "
        + "  `NOME_CURSO` VARCHAR(45) NULL, "
        + "  PRIMARY KEY (`idCURSO`)) "
        + "ENGINE = InnoDB; ";


        String sql5 = ""
        + "CREATE TABLE IF NOT EXISTS `av1_db`.`ALUNO` ( "
        + "  `MATRICULA` INT NOT NULL, "
        + "  `ALUNO_NOME` VARCHAR(45) NULL, "
        + "  `CURSO_idCURSO` INT NOT NULL, "
        + "  PRIMARY KEY (`MATRICULA`, `CURSO_idCURSO`), "
        + "  INDEX `fk_ALUNO_CURSO1_idx` (`CURSO_idCURSO` ASC) VISIBLE, "
        + "  CONSTRAINT `fk_ALUNO_CURSO1` "
        + "    FOREIGN KEY (`CURSO_idCURSO`) "
        + "    REFERENCES `av1_db`.`CURSO` (`idCURSO`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION); ";


        String sql6 = ""
        + "CREATE TABLE IF NOT EXISTS `av1_db`.`CURSO_has_ALUNO` ( "
        + "  `CURSO_idCURSO` INT NOT NULL, "
        + "  `ALUNO_MATRICULA` INT NOT NULL, "
        + "  PRIMARY KEY (`CURSO_idCURSO`, `ALUNO_MATRICULA`), "
        + "  INDEX `fk_CURSO_has_ALUNO_ALUNO1_idx` (`ALUNO_MATRICULA` ASC) VISIBLE, "
        + "  INDEX `fk_CURSO_has_ALUNO_CURSO_idx` (`CURSO_idCURSO` ASC) VISIBLE, "
        + "  CONSTRAINT `fk_CURSO_has_ALUNO_CURSO` "
        + "    FOREIGN KEY (`CURSO_idCURSO`) "
        + "    REFERENCES `av1_db`.`CURSO` (`idCURSO`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION, "
        + "  CONSTRAINT `fk_CURSO_has_ALUNO_ALUNO1` "
        + "    FOREIGN KEY (`ALUNO_MATRICULA`) "
        + "    REFERENCES `av1_db`.`ALUNO` (`MATRICULA`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION); ";


        String sql7 = ""
        + "CREATE TABLE IF NOT EXISTS `av1_db`.`ALUNO_has_DISCIPLINA` ( "
        + "  `ALUNO_MATRICULA` INT NOT NULL, "
        + "  `DISCIPLINA_idDISCIPLINA` INT NOT NULL, "
        + "  PRIMARY KEY (`ALUNO_MATRICULA`, `DISCIPLINA_idDISCIPLINA`), "
        + "  INDEX `fk_ALUNO_has_DISCIPLINA_DISCIPLINA1_idx` (`DISCIPLINA_idDISCIPLINA` ASC) VISIBLE, "
        + "  INDEX `fk_ALUNO_has_DISCIPLINA_ALUNO1_idx` (`ALUNO_MATRICULA` ASC) VISIBLE, "
        + "  CONSTRAINT `fk_ALUNO_has_DISCIPLINA_ALUNO1` "
        + "    FOREIGN KEY (`ALUNO_MATRICULA`) "
        + "    REFERENCES `av1_db`.`ALUNO` (`MATRICULA`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION, "
        + "  CONSTRAINT `fk_ALUNO_has_DISCIPLINA_DISCIPLINA1` "
        + "    FOREIGN KEY (`DISCIPLINA_idDISCIPLINA`) "
        + "    REFERENCES `av1_db`.`DISCIPLINA` (`idDISCIPLINA`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION); ";


        String sql8 = ""
        + "CREATE TABLE IF NOT EXISTS `av1_db`.`AVALIACAO` ( "
        + "  `idAVALIACAO` INT NOT NULL, "
        + "  `TRABALHO_ACADEMICO_AV1` DOUBLE NULL, "
        + "  `APS1` DOUBLE NULL, "
        + "  `TRABALHO_ACADEMICO_AV2` DOUBLE NULL, "
        + "  `APS2` DOUBLE NULL, "
        + "  `TRABALHO_ACADEMICO_AV3` DOUBLE NULL, "
        + "  `idMATRICULA` INT NOT NULL, "
        + "  `idDISCIPLINA` INT NOT NULL, "
        + "  PRIMARY KEY (`idAVALIACAO`), "
        + "  CONSTRAINT "
        + "    FOREIGN KEY (`idMATRICULA`) "
        + "    REFERENCES `av1_db`.`ALUNO` (`MATRICULA`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION, "
        + "  CONSTRAINT "
        + "    FOREIGN KEY (`idDISCIPLINA`) "
        + "    REFERENCES `av1_db`.`DISCIPLINA` (`idDISCIPLINA`) "
        + "    ON DELETE NO ACTION "
        + "    ON UPDATE NO ACTION, "
        + "  CONSTRAINT `avaliacao_chk_1` CHECK (((`APS1` >= 0.0) and (`APS1` <= 3.0))), "
        + "  CONSTRAINT `avaliacao_chk_2` CHECK (((`APS2` >= 0.0) and (`APS2` <= 2.0))), "
        + "  CONSTRAINT `avaliacao_chk_3` CHECK (((`TRABALHO_ACADEMICO_AV1` >= 0.0) and (`TRABALHO_ACADEMICO_AV1` <= 7.0))), "
        + "  CONSTRAINT `avaliacao_chk_4` CHECK (((`TRABALHO_ACADEMICO_AV2` >= 0.0) and (`TRABALHO_ACADEMICO_AV2` <= 8.0))), "
        + "  CONSTRAINT `avaliacao_chk_5` CHECK (((`TRABALHO_ACADEMICO_AV3` >= 0.0) and (`TRABALHO_ACADEMICO_AV3` <= 10.0))) "
        + ");";
        
        
        System.out.println("Criando Banco de Dados...");
        
        Statement stmnt = connection.createStatement();
        
        stmnt.executeUpdate(sql1);
        stmnt.executeUpdate(sql2);
        stmnt.executeUpdate(sql3);
        stmnt.executeUpdate(sql4);
        stmnt.executeUpdate(sql5);
        stmnt.executeUpdate(sql6);
        stmnt.executeUpdate(sql7);
        stmnt.executeUpdate(sql8);
        
//        File fileSql = new File("Av2_Create_Tables.sql");

        
        System.out.println("Banco de dados criado.");
    }
    
    public void createUsersDB() throws SQLException, IOException {
        String sql1 = "use av1_db; ";

        String sql2 = "create user if not exists 'dev'@'localhost' identified by 'senha';";

        String sql3 = ""
        + "grant update, "
        + "	delete, "
        + "    insert, "
        + "    select, "
        + "    execute, "
        + "    show view "
        + " on av1_db.* to 'dev'@'localhost';";

        String sql4 = ""
        + "create user if not exists 'dba'@'localhost' identified by 'senha';";

        String sql5 = ""
        + "grant create, "
        + "	alter, "
        + "    references, "
        + "    create view, "
        + "    create routine, "
        + "    alter routine, "
        + "    event, "
        + "    drop, "
        + "    index, "
        + "    trigger "
        + "on av1_db.* to 'dba'@'localhost';";

        String sql6 = ""
        + "flush privileges;";

        Connection connection = connect();
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate(sql1);
        stmnt.executeUpdate(sql2);
        stmnt.executeUpdate(sql3);
        stmnt.executeUpdate(sql4);
        stmnt.executeUpdate(sql5);
        stmnt.executeUpdate(sql6);
        
        System.out.println("Usuários criados.");        
    }
    
    public void createDisciplinas() throws SQLException{
        
        String sql1 = ""
        + "INSERT INTO av1_db.`DISCIPLINA` (`idDISCIPLINA`, `DISCIPLINA_NOME`) VALUES ('1', 'Algoritmos I'), ('2', 'Raciocínio lógico'), ('3', 'Algoritmos I'), ('4', 'Introdução a programação'), ('5', 'Algoritmos II'), ('6', 'Arquitetura de computadores'), ('7', 'Lógica matemática'), ('8', 'Programação estruturada'), ('9', 'Estatística'), ('10', 'Banco de dados I');"
        + "";

        String sql2 = ""
        + "INSERT INTO av1_db.`CURSO` (`idCURSO`, `NOME_CURSO`) VALUES( '1', 'ADS'),( '2', 'CC'),( '3', 'ENC');"
        + "";
        
        Connection connection = connect();
        Statement stmnt = connection.createStatement();
        
        stmnt.executeUpdate(sql1);
        stmnt.executeUpdate(sql2);
        
        System.out.println("Cursos e Disciplinas criados.");  
        
    }
    
    public boolean isDisciplinasCriadas() throws SQLException{
        String sql = "select * from av1_db.disciplina";
        int i=0;
        Connection connection = connect();
        Statement stmnt = connection.createStatement();
        
        ResultSet result = stmnt.executeQuery(sql);
        
        while(result.next()){
            System.out.print(i);
            i++;
        }
        
        return i>0;
        
    }
    
}
