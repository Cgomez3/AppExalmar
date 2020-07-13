/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.model;

import appexalmar.bean.Conexion;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.interfaz.IPersona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        prepareStatement.setString(1, personal.getCodsap());
        prepareStatement.setString(2, personal.getApellidosNombres());
        prepareStatement.setString(3, personal.getDni());
        prepareStatement.setString(4, personal.getCargo());
        prepareStatement.setString(5, personal.getSede());
        if (personal.getFechaIngreso().isEmpty()) {
            prepareStatement.setString(6, null);
        } else {
            prepareStatement.setString(6, personal.getFechaIngreso());
        }
        if (personal.getFechaNacimiento().isEmpty()) {
            prepareStatement.setString(7, null);
        } else {
            prepareStatement.setString(7, personal.getFechaNacimiento());
        }
        prepareStatement.setString(8, personal.getCatgoria());
        prepareStatement.setString(9, personal.getTipoPersonal());
        int pt = prepareStatement.executeUpdate();
        System.out.println("rpta " + pt);
        conexion.CloseSql();
    }

    @Override
    public PersonalExalmarBeans ObtinePersona(String codsap) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
         System.out.println("codsap"+codsap);
        String consulta = "SELECT * FROM personal WHERE CODSAP= '" + codsap + "'";
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            if (resultSet.getString("codsap") == null) {
                personal.setCodsap("");
            } else {
                personal.setCodsap(String.valueOf(resultSet.getString("codsap")));
            }
            personal.setApellidosNombres(resultSet.getString("apellido_nombres"));
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo"));
            personal.setSede(resultSet.getString("sede"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            if (fini != null) {
                personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            }
            if (finac != null) {
                personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            }
            personal.setCatgoria(resultSet.getString("categoria"));
            personal.setTipoPersonal(resultSet.getString("tipo_personal"));
            System.out.println("appexalmar.model.PersonaModel.ObtinePersona()" + personal.getTipoPersonal());

        }
        conexion.CloseSql();
        return personal;
    }

    @Override
    public PersonalExalmarBeans ObtinePersonaPorDni(String dni) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if (dni.isEmpty()) {
            dni = "0";
        }
        String consulta = "SELECT * FROM personal WHERE DNI= '" + dni + "'";
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            if (resultSet.getString("codsap") == null) {
                personal.setCodsap("");
            } else {
                personal.setCodsap(String.valueOf(resultSet.getString("codsap")));
            }
            personal.setApellidosNombres(resultSet.getString("apellido_nombres"));
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo"));
            personal.setSede(resultSet.getString("sede"));
            System.out.println("aaa" + resultSet.getDate("fecha_ingreso"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            if (fini != null) {
                personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            }
            if (finac != null) {
                personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            }
            personal.setCatgoria(resultSet.getString("categoria"));
            personal.setTipoPersonal(resultSet.getString("tipo_personal"));
            System.out.println("appexalmar.model.PersonaModel.ObtinePersona()" + personal.getTipoPersonal());

        }
        conexion.CloseSql();
        return personal;
    }

    @Override
    public PersonalExalmarBeans ObtinePersonaPorNombre(String nombre) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT  * FROM personal WHERE apellido_nombres= '" + nombre.trim() + "'";
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            if (resultSet.getString("codsap") == null) {
                personal.setCodsap("");
            } else {
                personal.setCodsap(String.valueOf(resultSet.getString("codsap")));
            }
            personal.setApellidosNombres(resultSet.getString("apellido_nombres"));
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo"));
            personal.setSede(resultSet.getString("sede"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            if (fini != null) {
                personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            }
            if (finac != null) {
                personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            }
            personal.setCatgoria(resultSet.getString("categoria"));
            personal.setTipoPersonal(resultSet.getString("tipo_personal"));
            System.out.println("appexalmar.model.PersonaModel.ObtinePersona()" + personal.getTipoPersonal());

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
            if (resultSet.getString("codsap") == null) {
                personal.setCodsap("");
            } else {
                personal.setCodsap(String.valueOf(resultSet.getString("codsap")));
            }
            personal.setApellidosNombres(resultSet.getString("apellido_nombres").toUpperCase());
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo").toUpperCase());
            personal.setSede(resultSet.getString("sede"));
            java.util.Date fini = resultSet.getDate("fecha_ingreso");
            java.util.Date finac = resultSet.getDate("fecha_nacimiento");
            if (fini != null) {
                personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            }
            if (finac != null) {
                personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            }
            if (resultSet.getString("categoria") != null) {
                personal.setCatgoria(resultSet.getString("categoria").toUpperCase());
            }
            if (resultSet.getString("tipo_personal") != null) {
                personal.setTipoPersonal(resultSet.getString("tipo_personal").toUpperCase());
            }

            listaPersonal.add(personal);

        }
        conexion.CloseSql();
        return listaPersonal;
    }

    @Override
    public void ActualizaPersonaPorDni(PersonalExalmarBeans personal) throws SQLException,ParseException {
        SQL = "UPDATE PERSONAL SET CODSAP=? , APELLIDO_NOMBRES= ?, CARGO= ?,SEDE= ?,FECHA_INGRESO= ?,FECHA_NACIMIENTO= ?,CATEGORIA=?,TIPO_PERSONAL=? WHERE DNI= ?";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, personal.getCodsap());
        prepareStatement.setString(2, personal.getApellidosNombres());
        prepareStatement.setString(3, personal.getCargo());
        prepareStatement.setString(4, personal.getSede());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechai;
        Date fechan;
        
            prepareStatement.setString(5, null);
            prepareStatement.setString(6, null);
            if (personal.getFechaIngreso() != null) {
                fechai = (Date) format.parse(personal.getFechaIngreso());
                prepareStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(fechai));
            }
            if (personal.getFechaNacimiento() != null) {
                fechan = (Date) format.parse(personal.getFechaNacimiento());

                prepareStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(fechan));
            }
            prepareStatement.setString(7, personal.getCatgoria());
            prepareStatement.setString(8, personal.getTipoPersonal());
            prepareStatement.setString(9, personal.getDni());
            int pt = prepareStatement.executeUpdate();
            System.out.println("rpta " + pt);
            conexion.CloseSql();
        
    }

    @Override
    public void ActualizaPersonaCodsap(PersonalExalmarBeans personal) throws SQLException,ParseException {
        SQL = "UPDATE PERSONAL SET APELLIDO_NOMBRES= ?, DNI=?, CARGO= ?,SEDE= ?,FECHA_INGRESO= ?,FECHA_NACIMIENTO= ?,CATEGORIA=?,TIPO_PERSONAL=? WHERE CODSAP= ?";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, personal.getApellidosNombres());
        prepareStatement.setString(2, personal.getDni());
        prepareStatement.setString(3, personal.getCargo());
        prepareStatement.setString(4, personal.getSede());
//        new SimpleDateFormat("dd-MM-yyyy").format(fini)
        System.out.println("personal.getFechaIngreso()" + personal.getFechaIngreso());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date fechai;
        Date fechan;
        
            prepareStatement.setString(5, null);
            prepareStatement.setString(6, null);
            if (personal.getFechaIngreso() != null) {
                fechai = (Date) format.parse(personal.getFechaIngreso());
                prepareStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(fechai));
            }
            if (personal.getFechaNacimiento() != null) {
                fechan = (Date) format.parse(personal.getFechaNacimiento());
                prepareStatement.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(fechan));
            }
            prepareStatement.setString(7, personal.getCatgoria());
            prepareStatement.setString(8, personal.getTipoPersonal());
            System.out.println("personal.getCodsap()" + personal.getCodsap());
            prepareStatement.setString(9, personal.getCodsap());
            int pt = prepareStatement.executeUpdate();
            System.out.println("rpta " + pt);
            conexion.CloseSql();
        
    }

    @Override
    public void ActualizaPersonaNombre(PersonalExalmarBeans personal) throws SQLException,ParseException {
        SQL = "UPDATE PERSONAL SET  DNI=?, CARGO= ?,SEDE= ?,FECHA_INGRESO= ?,FECHA_NACIMIENTO= ?,CATEGORIA=?,TIPO_PERSONAL=? WHERE APELLIDO_NOMBRES= ?";
        prepareStatement = conexion.getConection().prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setString(1, personal.getDni());
        prepareStatement.setString(2, personal.getCargo());
        prepareStatement.setString(3, personal.getSede());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
            prepareStatement.setString(4, null);
            prepareStatement.setString(5, null);
            if (personal.getFechaIngreso() != null) {
                Date fechai = (Date) format.parse(personal.getFechaIngreso());
                prepareStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(fechai));
            }
            if (personal.getFechaNacimiento() != null) {
                Date fechan = (Date) format.parse(personal.getFechaNacimiento());
                prepareStatement.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(fechan));
            }

            prepareStatement.setString(6, personal.getCatgoria());
            prepareStatement.setString(7, personal.getTipoPersonal());
            prepareStatement.setString(8, personal.getApellidosNombres());
            int pt = prepareStatement.executeUpdate();
            System.out.println("rpta " + pt);
            conexion.CloseSql();
        
    }

}
