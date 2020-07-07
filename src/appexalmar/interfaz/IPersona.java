/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.interfaz;

import appexalmar.bean.PersonalExalmarBeans;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IPersona {
    void GrabarPersona (PersonalExalmarBeans personal)throws SQLException;
    PersonalExalmarBeans ObtinePersona (String codsap)throws SQLException;
    List<PersonalExalmarBeans> listaPersonal() throws SQLException;
    PersonalExalmarBeans ObtinePersonaPorDni(String dni) throws SQLException;
    PersonalExalmarBeans ObtinePersonaPorNombre(String nombre) throws SQLException;
    void ActualizaPersonaPorDni(PersonalExalmarBeans personal) throws SQLException ;
    void ActualizaPersonaCodsap(PersonalExalmarBeans personal) throws SQLException;
    void ActualizaPersonaNombre(PersonalExalmarBeans personal) throws SQLException;
}
