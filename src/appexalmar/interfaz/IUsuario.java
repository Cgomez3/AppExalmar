/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.interfaz;

import appexalmar.bean.UsuarioBeans;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public interface IUsuario {
     void GrabarUsuario (UsuarioBeans usuario)throws SQLException;
     UsuarioBeans  ObtieneUsuario (UsuarioBeans usuario)throws SQLException;
}
