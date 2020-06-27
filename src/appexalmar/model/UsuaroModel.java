/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.model;

import appexalmar.bean.Conexion;
import appexalmar.bean.UsuarioBeans;
import appexalmar.interfaz.IUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class UsuaroModel implements IUsuario {

    private Conexion conexion = new Conexion();
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    @Override
    public void GrabarUsuario(UsuarioBeans usuario) throws SQLException {
        SQL = "INSERT INTO USUARIOS (NOMBRES, USUARIO, CLAVE) VALUES (?, ?, ?)";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, usuario.getNombre());
        prepareStatement.setString(2, usuario.getUsuario());
        prepareStatement.setString(3, usuario.getClave());
        int pt = prepareStatement.executeUpdate();
        conexion.CloseSql();
    }

    @Override
    public UsuarioBeans ObtieneUsuario(UsuarioBeans usuario) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT NOMBRES, USUARIO, CLAVE , IDUSUARIOS FROM USUARIOS WHERE USUARIO= '" + usuario.getUsuario() + "' AND CLAVE= '" + usuario.getClave()+"'";
        resultSet = statement.executeQuery(consulta);
        UsuarioBeans usuarioloc = null;
        while (resultSet.next()) {
            usuarioloc = new UsuarioBeans();
            usuarioloc.setNombre(resultSet.getString("NOMBRES"));
            usuarioloc.setUsuario(resultSet.getString("USUARIO"));
            usuarioloc.setClave(resultSet.getString("CLAVE"));
            usuarioloc.setId_usuario(resultSet.getInt("IDUSUARIOS"));

        }
        conexion.CloseSql();
        return usuarioloc;
    }
}
