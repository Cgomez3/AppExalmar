/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.model;

import appexalmar.bean.Conexion;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.interfaz.IPersona;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PersonaModel implements IPersona {

    private Conexion conexion = new Conexion();
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    @Override
    public void GrabarPersona(PersonalExalmarBeans personal) throws SQLException {
        SQL = "INSERT INTO PERSONAL (CODSAP, APELLIDO_NOMBRES, DNI, CARGO,SEDE,FECHA_INGRESO,FECHA_NACIMIENTO,CATEGORIA,TIPO_PERSONAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setInt(1,Integer.parseInt(personal.getCodsap()));
        prepareStatement.setString(2, personal.getApellidosNombres());
        prepareStatement.setString(3, personal.getDni());
        prepareStatement.setString(4, personal.getCargo());
        prepareStatement.setString(5, personal.getSede());
        prepareStatement.setString(6, personal.getFechaIngreso());
        prepareStatement.setString(7, personal.getFechaNacimiento());
        prepareStatement.setString(8, personal.getCatgoria());
        prepareStatement.setString(9, personal.getTipoPersonal());
        int pt = prepareStatement.executeUpdate();
        System.out.println("rpta " + pt);
        conexion.CloseSql();
    }

    @Override
    public PersonalExalmarBeans ObtinePersona(String codsap) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM personal WHERE CODSAP= " + codsap;
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            personal.setCodsap(String.valueOf(resultSet.getInt("codsap")));
            personal.setApellidosNombres(resultSet.getString("apellido_nombres"));
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo"));
            personal.setSede(resultSet.getString("sede"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            personal.setCatgoria(resultSet.getString("categoria"));
            personal.setTipoPersonal(resultSet.getString("tipo_personal"));
            System.out.println("appexalmar.model.PersonaModel.ObtinePersona()"+personal.getTipoPersonal());

        }
        conexion.CloseSql();
        return personal;
    }
    @Override
    public PersonalExalmarBeans ObtinePersonaPorDni(String dni) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM personal WHERE DNI= " + dni;
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            personal.setCodsap(String.valueOf(resultSet.getInt("codsap")));
            personal.setApellidosNombres(resultSet.getString("apellido_nombres"));
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo"));
            personal.setSede(resultSet.getString("sede"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            personal.setCatgoria(resultSet.getString("categoria"));
            personal.setTipoPersonal(resultSet.getString("tipo_personal"));
            System.out.println("appexalmar.model.PersonaModel.ObtinePersona()"+personal.getTipoPersonal());

        }
        conexion.CloseSql();
        return personal;
    }

    @Override
    public List<PersonalExalmarBeans> listaPersonal() throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM personal";
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        List<PersonalExalmarBeans> listaPersonal = new ArrayList<>();
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            personal.setCodsap(String.valueOf(resultSet.getInt("codsap")).toUpperCase());
            personal.setApellidosNombres(resultSet.getString("apellido_nombres").toUpperCase());
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo").toUpperCase());
            personal.setSede(resultSet.getString("sede"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            personal.setCatgoria(resultSet.getString("categoria").toUpperCase());
            personal.setTipoPersonal(resultSet.getString("tipo_personal").toUpperCase());
            listaPersonal.add(personal);

        }
        conexion.CloseSql();
        return listaPersonal;
    }

}
