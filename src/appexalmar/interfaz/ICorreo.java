/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.interfaz;

import appexalmar.bean.CorreoBeans;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public interface ICorreo {
    void GrabarCorreo(CorreoBeans correo)throws SQLException;
     String  ObtieneCorreo (Integer idusuario)throws SQLException;
}
