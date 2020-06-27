/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.model;

import appexalmar.bean.Conexion;
import appexalmar.bean.CorreoBeans;
import appexalmar.interfaz.ICorreo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class CorreoModel implements ICorreo {

    private Conexion conexion = new Conexion();
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    @Override
    public void GrabarCorreo(CorreoBeans correo) throws SQLException {
        SQL = "INSERT INTO CORREO (CORREO_ENVIO, CORREO_RECEPCION, CONTRASENIA_ENVIO,ASUNTO,MENSAJE_PROGRAMADO,ID_USUARIO) VALUES (?, ?, ?, ?, ?, ?)";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, correo.getCorreo_envio());
        prepareStatement.setString(2, correo.getCorreo_recepcion());
        prepareStatement.setString(3, correo.getContraseña_envio());
        prepareStatement.setString(4, correo.getAsunto());
        prepareStatement.setString(5, correo.getMensaje_programado());
        prepareStatement.setInt(6, correo.getId_usuario());
        int pt = prepareStatement.executeUpdate();
        conexion.CloseSql();
    }

    @Override
    public String ObtieneCadenaCorreo(Integer idusuario) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM CORREO WHERE ID_USUARIO= " + idusuario;
        resultSet = statement.executeQuery(consulta);
        String correos = null;
        while (resultSet.next()) {
            correos=(resultSet.getString("CORREO_RECEPCION"));
        }
        conexion.CloseSql();
        return correos;
    }
    
    @Override
    public CorreoBeans ObtieneCorreo(Integer idusuario) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM CORREO WHERE ID_USUARIO= " + idusuario;
        resultSet = statement.executeQuery(consulta);
        CorreoBeans correo = null;
        while (resultSet.next()) {
            correo= new CorreoBeans();
            correo.setCorreo_envio(resultSet.getString("CORREO_ENVIO"));
            correo.setCorreo_recepcion(resultSet.getString("CORREO_RECEPCION"));
            correo.setContraseña_envio(resultSet.getString("CONTRASENIA_ENVIO"));
            correo.setAsunto(resultSet.getString("ASUNTO"));
            correo.setMensaje_programado(resultSet.getString("MENSAJE_PROGRAMADO"));
            correo.setId_usuario(resultSet.getInt("ID_USUARIO"));
        }
        conexion.CloseSql();
        return correo;
    }

    @Override
    public void UpdateCorreo(CorreoBeans correo) throws SQLException {
        SQL = "UPDATE CORREO SET CORREO_ENVIO= ?, CORREO_RECEPCION= ?, CONTRASENIA_ENVIO= ?,ASUNTO= ?,MENSAJE_PROGRAMADO= ? WHERE ID_USUARIO= ?";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, correo.getCorreo_envio());
        prepareStatement.setString(2, correo.getCorreo_recepcion());
        prepareStatement.setString(3, correo.getContraseña_envio());
        prepareStatement.setString(4, correo.getAsunto());
        prepareStatement.setString(5, correo.getMensaje_programado());
        prepareStatement.setInt(6, correo.getId_usuario());
        prepareStatement.executeUpdate();
        conexion.CloseSql();
    }

}
