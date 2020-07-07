/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.model;

import Api.swing.frameword.controles.D_Button;
import appexalmar.bean.Conexion;
import appexalmar.bean.DetalleReporteBeans;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.bean.ReporteCabeceraBeans;
import appexalmar.bean.TableDetalleReporteBeans;
import appexalmar.bean.TableReporteBean;
import appexalmar.image.RutaImagen;
import appexalmar.interfaz.IReporte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class ReporteModel implements IReporte {

    Conexion conexion = new Conexion();
    PreparedStatement prepareStatement;
    Statement statement;
    ResultSet resultSet;
    String QUERY = "";
    PersonaModel personaModel = new PersonaModel();

    @Override
    public List<ReporteCabeceraBeans> listaReporte() throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("SELECT * FROM reporte_cabecera");
        List<ReporteCabeceraBeans> listaReportes = new ArrayList<>();
        ReporteCabeceraBeans reporteBeans;
        while (resultSet.next()) {
            reporteBeans = new ReporteCabeceraBeans();
            D_Button btnEditar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("refresh.png")), "E", "");
            btnEditar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            D_Button btnAgregar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("addsi.png")), "G", "");
            btnAgregar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            D_Button btnEnviar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("email.png")), "I", "");
            btnEnviar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            reporteBeans.setBtnEditar(btnEditar);
            reporteBeans.setBtnGrabar(btnAgregar);
            reporteBeans.setBtnEnviar(btnEnviar);
            reporteBeans.setNumero(resultSet.getInt("numero"));
            java.util.Date fecha = resultSet.getDate("fecha");
            if (fecha != null) {
                reporteBeans.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
            } else {
                reporteBeans.setFecha("");
            }

            reporteBeans.setHora(resultSet.getString("hora"));
            reporteBeans.setCelular(resultSet.getString("celular"));
            reporteBeans.setLocalidadDomicilio(resultSet.getString("localidad_domicilio"));
            reporteBeans.setEp(resultSet.getString("ep"));
            reporteBeans.setDni(resultSet.getString("dni"));
            String codsap = resultSet.getString("cod_sap");
            if (codsap.isEmpty()) {
                codsap = "0";
            }
            PersonalExalmarBeans personal = personaModel.ObtinePersona(codsap);
            if (personal != null) {
                reporteBeans.setApe_nom(personal.getApellidosNombres());
                reporteBeans.setDni(personal.getDni());
                reporteBeans.setCargo(personal.getCargo());
                reporteBeans.setCodsap(personal.getCodsap());
                reporteBeans.setFecha_ingreso(personal.getFechaIngreso());
                reporteBeans.setFecha_naciomiento(personal.getFechaNacimiento());
                reporteBeans.setCategoria(personal.getCatgoria());
            } else {
                personal = personaModel.ObtinePersonaPorDni(reporteBeans.getDni());
                if (personal != null) {
                    reporteBeans.setApe_nom(personal.getApellidosNombres());
                    reporteBeans.setDni(personal.getDni());
                    reporteBeans.setCargo(personal.getCargo());
                    reporteBeans.setCodsap(personal.getCodsap());
                    reporteBeans.setFecha_ingreso(personal.getFechaIngreso());
                    reporteBeans.setFecha_naciomiento(personal.getFechaNacimiento());
                    reporteBeans.setCategoria(personal.getCatgoria());
                }
            }
            reporteBeans.setConfinadoDonde(resultSet.getString("confinado_donde"));
            reporteBeans.setSede(resultSet.getString("sede"));
            reporteBeans.setTipo_atencion(resultSet.getString("tipo_atencion_seguimiento"));
            reporteBeans.setTipo_presencial(resultSet.getString("tipo_presencial_virtual"));
            reporteBeans.setMedico(resultSet.getString("medico"));
            reporteBeans.setEmvSINO(resultSet.getString("alerta"));

            listaReportes.add(reporteBeans);
        }
        conexion.CloseSql();
        return listaReportes;
    }

    @Override
    public ReporteCabeceraBeans ObtieneReporte(int idcabecera) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("SELECT * FROM reporte_cabecera WHERE id_cabecera=" + idcabecera);
        ReporteCabeceraBeans reporteBeans = null;
        while (resultSet.next()) {
            reporteBeans = new ReporteCabeceraBeans();
            D_Button btnEditar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("refresh.png")), "E", "");
            btnEditar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            D_Button btnAgregar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("addsi.png")), "G", "");
            btnAgregar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            D_Button btnEnviar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("email.png")), "I", "");
            btnEnviar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            reporteBeans.setBtnEditar(btnEditar);
            reporteBeans.setBtnGrabar(btnAgregar);
            reporteBeans.setBtnEnviar(btnEnviar);
            reporteBeans.setNumero(resultSet.getInt("numero"));
            java.util.Date fecha = resultSet.getDate("fecha");
            reporteBeans.setFecha(new SimpleDateFormat("dd-MM-yyyy").format(fecha));
            reporteBeans.setHora(resultSet.getString("hora"));
            reporteBeans.setCelular(resultSet.getString("celular"));
            reporteBeans.setLocalidadDomicilio(resultSet.getString("localidad_domicilio"));
            reporteBeans.setEp(resultSet.getString("ep"));
            reporteBeans.setDni(resultSet.getString("dni"));
            String codsap = resultSet.getString("cod_sap");
            if (codsap.isEmpty()) {
                codsap = "0";
            }
            PersonalExalmarBeans personal = personaModel.ObtinePersona(codsap);
            if (personal != null) {
                reporteBeans.setDni(personal.getDni());
                reporteBeans.setApe_nom(personal.getApellidosNombres());
                reporteBeans.setCargo(personal.getCargo());
                reporteBeans.setCodsap(personal.getCodsap());
                reporteBeans.setFecha_ingreso(personal.getFechaIngreso());
                reporteBeans.setFecha_naciomiento(personal.getFechaNacimiento());
                reporteBeans.setCategoria(personal.getCatgoria());

            } else {
                personal = personaModel.ObtinePersonaPorDni(reporteBeans.getDni());
                reporteBeans.setDni(personal.getDni());
                reporteBeans.setApe_nom(personal.getApellidosNombres());
                reporteBeans.setCargo(personal.getCargo());
                reporteBeans.setCodsap(personal.getCodsap());
                reporteBeans.setFecha_ingreso(personal.getFechaIngreso());
                reporteBeans.setFecha_naciomiento(personal.getFechaNacimiento());
                reporteBeans.setCategoria(personal.getCatgoria());
            }
            reporteBeans.setConfinadoDonde(resultSet.getString("confinado_donde"));
            reporteBeans.setSede(resultSet.getString("sede"));
            reporteBeans.setTipo_atencion(resultSet.getString("tipo_atencion_seguimiento"));
            reporteBeans.setTipo_presencial(resultSet.getString("tipo_presencial_virtual"));
            reporteBeans.setMedico(resultSet.getString("medico"));
            reporteBeans.setEmvSINO(resultSet.getString("alerta"));

        }
        conexion.CloseSql();
        return reporteBeans;
    }

    @Override
    public Object[] listaReporte(int idCabecera) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery("SELECT * FROM reporte_cabecera where  id_cabecera=" + idCabecera);
        Object[] objects = new Object[22];
        while (resultSet.next()) {
            D_Button btnEditar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("refresh.png")), "E", "");
            btnEditar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            D_Button btnAgregar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("addsi.png")), "G", "");
            btnAgregar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            D_Button btnEnviar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("email.png")), "I", "");
            btnEnviar.setHiddenValue(String.valueOf(resultSet.getInt("id_cabecera")));
            objects[0] = btnAgregar;
            objects[1] = btnEditar;
            objects[2] = btnEnviar;
            objects[3] = resultSet.getInt("numero");
            java.util.Date fecha = resultSet.getDate("fecha");
            objects[4] = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
            objects[5] = resultSet.getString("hora");
            objects[6] = resultSet.getString("celular");
            objects[7] = resultSet.getString("localidad_domicilio");
            objects[8] = resultSet.getString("ep");
            String codsap = resultSet.getString("cod_sap");
            if (codsap.isEmpty()) {
                codsap = "";
            }
            PersonalExalmarBeans personal = personaModel.ObtinePersona(codsap);
            if (personal != null) {
                objects[9] = personal.getDni();
                objects[10] = personal.getApellidosNombres();
                objects[11] = personal.getCargo();
                objects[18] = personal.getCodsap();
                objects[19] = personal.getFechaIngreso();
                objects[20] = personal.getFechaNacimiento();
                objects[21] = personal.getCatgoria();
            } else {
                personal = personaModel.ObtinePersonaPorDni(resultSet.getString("dni"));
                if (personal != null) {
                    objects[9] = personal.getDni();
                    objects[10] = personal.getApellidosNombres();
                    objects[11] = personal.getCargo();
                    objects[18] = personal.getCodsap();
                    objects[19] = personal.getFechaIngreso();
                    objects[20] = personal.getFechaNacimiento();
                    objects[21] = personal.getCatgoria();
                } else {
                    objects[9] = "";
                    objects[10] = "";
                    objects[11] = "";
                    objects[18] = "";
                    objects[19] = "";
                    objects[20] = "";
                    objects[21] = "";
                }
            }
            objects[12] = resultSet.getString("confinado_donde");
            objects[13] = resultSet.getString("sede");
            objects[14] = resultSet.getString("tipo_atencion_seguimiento");
            objects[15] = resultSet.getString("tipo_presencial_virtual");
            objects[16] = resultSet.getString("medico");
            objects[17] = resultSet.getString("alerta");

        }
        conexion.CloseSql();
        return objects;
    }

    @Override
    public PersonalExalmarBeans obtienePersonal(int codSap) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM personal WHERE CODSAP= " + codSap;
        resultSet = statement.executeQuery(consulta);
        PersonalExalmarBeans personal = null;
        while (resultSet.next()) {
            personal = new PersonalExalmarBeans();
            personal.setCodsap(String.valueOf(resultSet.getInt("codsap")));
            personal.setApellidosNombres(resultSet.getString("apellido_nombres"));
            personal.setDni(resultSet.getString("dni"));
            personal.setCargo(resultSet.getString("cargo"));
            personal.setSede(resultSet.getString("sede"));
            Date fini = resultSet.getDate("fecha_ingreso");
            Date finac = resultSet.getDate("fecha_nacimiento");
            personal.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").format(fini));
            personal.setFechaNacimiento(new SimpleDateFormat("dd-MM-yyyy").format(finac));
            personal.setCatgoria(resultSet.getString("categoria"));
            personal.setTipoPersonal(resultSet.getString("tipo_personal"));

        }

        return personal;
    }

    @Override
    public List<DetalleReporteBeans> listaDetalleReporte(int id_cabecera) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM reporte_detalle WHERE ID_CABECERA= " + id_cabecera;
        resultSet = statement.executeQuery(consulta);
        DetalleReporteBeans detalle = null;
        List<DetalleReporteBeans> listaDetalleReporte = new ArrayList<>();
        while (resultSet.next()) {
            detalle = new DetalleReporteBeans();
            detalle.setIdDetalle(resultSet.getInt("id_detalle"));
            detalle.setIdCabecera(resultSet.getInt("id_cabecera"));
            detalle.setConsulta(resultSet.getString("consulta"));
            detalle.setDetalle(resultSet.getString("detalle"));
            detalle.setAcción(resultSet.getString("accion"));
            detalle.setDiacnostico(resultSet.getString("diacnostico"));
            detalle.setMedicación(resultSet.getString("medicacion"));
            detalle.setFrecuencia(resultSet.getString("frecuencia"));
            detalle.setDias(resultSet.getString("dias"));
            detalle.setCanntidadTotal(resultSet.getString("cantidad_total"));
            listaDetalleReporte.add(detalle);
        }

        return listaDetalleReporte;
    }

    @Override
    public List<TableDetalleReporteBeans> listaTableDetalleReporte(int id_cabecera) throws SQLException {
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String consulta = "SELECT * FROM reporte_detalle WHERE ID_CABECERA= " + id_cabecera;
        resultSet = statement.executeQuery(consulta);
        TableDetalleReporteBeans detalle = null;
        List<TableDetalleReporteBeans> listaDetalleReporte = new ArrayList<>();
        while (resultSet.next()) {
            detalle = new TableDetalleReporteBeans();
            D_Button btnEditar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("registro.png")), "M", "");
            btnEditar.setHiddenValue(String.valueOf(resultSet.getInt("id_detalle")));
            D_Button btnEliminar = new D_Button(0, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("borrar.png")), "E", "");
            btnEliminar.setHiddenValue(String.valueOf(resultSet.getInt("id_detalle")));

            detalle.setBtnModificar(btnEditar);
            detalle.setBtnEliminar(btnEliminar);
            detalle.setConsulta(resultSet.getString("consulta"));
            detalle.setDetalle(resultSet.getString("detalle"));
            detalle.setAcción(resultSet.getString("accion"));
            detalle.setDiacnostico(resultSet.getString("diacnostico"));
            detalle.setMedicación(resultSet.getString("medicacion"));
            detalle.setFrecuencia(resultSet.getString("frecuencia"));
            detalle.setDias(resultSet.getString("dias"));
            detalle.setCanntidadTotal(resultSet.getString("cantidad_total"));
            listaDetalleReporte.add(detalle);
        }

        return listaDetalleReporte;
    }

    @Override
    public long GrabarReporte(TableReporteBean reporteBean) throws SQLException {
        QUERY = "INSERT INTO REPORTE_CABECERA (NUMERO, FECHA, HORA, CELULAR,LOCALIDAD_DOMICILIO,CONFINADO_DONDE,SEDE,EP,TIPO_ATENCION_SEGUIMIENTO,TIPO_PRESENCIAL_VIRTUAL,ALERTA,COD_SAP,MEDICO,DNI) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prepareStatement = conexion.getConection().prepareStatement(QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setInt(1, reporteBean.getNumero());
        if (reporteBean.getFecha().isEmpty()) {
            prepareStatement.setString(2, null);
        } else {
            prepareStatement.setString(2, reporteBean.getFecha());
        }
        prepareStatement.setString(3, reporteBean.getHora());
        prepareStatement.setString(4, reporteBean.getCelular());
        prepareStatement.setString(5, reporteBean.getLocalidadDomicilio());
        prepareStatement.setString(6, reporteBean.getConfinadoDonde());
        prepareStatement.setString(7, reporteBean.getSede());
        prepareStatement.setString(8, reporteBean.getEp());
        prepareStatement.setString(9, reporteBean.getTipoAtencionSeguimiento());
        prepareStatement.setString(10, reporteBean.getTipoPrecencialVirtual());
        prepareStatement.setString(11, reporteBean.getEmvSINO());
        prepareStatement.setString(12, reporteBean.getCodSap());
        prepareStatement.setString(13, reporteBean.getMedico());
        System.out.println("reporteBean.getDni()"+reporteBean.getDni());
        prepareStatement.setString(14, reporteBean.getDni());
        int pt = prepareStatement.executeUpdate();
        if (pt == 0) {
            throw new SQLException("No se puedo Grabar.");
        }
        resultSet = prepareStatement.getGeneratedKeys();
        long idGenerodo = 0;
        if (resultSet.next()) {
            idGenerodo = resultSet.getInt(1);
        }
        conexion.CloseSql();
        return idGenerodo;
    }

    @Override
    public void GrabarDetalle(TableDetalleReporteBeans detalleBean, int id_cabecera) throws SQLException {
        QUERY = "INSERT INTO REPORTE_DETALLE (ID_CABECERA, CONSULTA, DETALLE, ACCION,DIACNOSTICO,MEDICACION,FRECUENCIA,DIAS,CANTIDAD_TOTAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        prepareStatement = conexion.getConection().prepareStatement(QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setInt(1, id_cabecera);
        prepareStatement.setString(2, detalleBean.getConsulta());
        prepareStatement.setString(3, detalleBean.getDetalle());
        prepareStatement.setString(4, detalleBean.getAcción());
        prepareStatement.setString(5, detalleBean.getDiacnostico());
        prepareStatement.setString(6, detalleBean.getMedicación());
        prepareStatement.setString(7, detalleBean.getFrecuencia());
        prepareStatement.setString(8, detalleBean.getDias());
        prepareStatement.setString(9, detalleBean.getCanntidadTotal());
        prepareStatement.executeUpdate();
        conexion.CloseSql();
    }

    @Override
    public Integer ObtenerNumero() throws SQLException {
        QUERY = "SELECT max(numero)+1 FROM bd_exalmar.reporte_cabecera";
        statement = conexion.getConection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(QUERY);
        Integer Numero = 0;
        while (resultSet.next()) {
            Numero = resultSet.getInt(1);
        }
        conexion.CloseSql();
        return Numero;
    }

    @Override
    public void ActualizarReporte(TableReporteBean reporteBean, int id_cabecera) throws SQLException {
        QUERY = "UPDATE  REPORTE_CABECERA SET NUMERO=?, FECHA=?, HORA=?, CELULAR=?,LOCALIDAD_DOMICILIO=?,CONFINADO_DONDE=?,SEDE=?,EP=?,TIPO_ATENCION_SEGUIMIENTO=?,TIPO_PRESENCIAL_VIRTUAL=?,ALERTA=?,COD_SAP=?,MEDICO=?,DNI=? WHERE ID_CABECERA=?";
        prepareStatement = conexion.getConection().prepareStatement(QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
        prepareStatement.setInt(1, reporteBean.getNumero());
        prepareStatement.setString(2, reporteBean.getFecha());
        prepareStatement.setString(3, reporteBean.getHora());
        prepareStatement.setString(4, reporteBean.getCelular());
        prepareStatement.setString(5, reporteBean.getLocalidadDomicilio());
        prepareStatement.setString(6, reporteBean.getConfinadoDonde());
        prepareStatement.setString(7, reporteBean.getSede());
        prepareStatement.setString(8, reporteBean.getEp());
        prepareStatement.setString(9, reporteBean.getTipoAtencionSeguimiento());
        prepareStatement.setString(10, reporteBean.getTipoPrecencialVirtual());
        prepareStatement.setString(11, reporteBean.getEmvSINO());
        prepareStatement.setString(12, reporteBean.getCodSap());
        prepareStatement.setString(13, reporteBean.getMedico());
        prepareStatement.setString(14, reporteBean.getDni());
        prepareStatement.setInt(15, id_cabecera);
        prepareStatement.executeUpdate();
        conexion.CloseSql();
    }

    @Override
    public void EliminaDetalleReporte(int id_cabecera) throws SQLException {
        QUERY = "DELETE  FROM REPORTE_DETALLE WHERE ID_CABECERA = ?";
        prepareStatement = conexion.getConection().prepareStatement(QUERY);
        prepareStatement.setInt(1, id_cabecera);
        prepareStatement.executeUpdate();
        conexion.CloseSql();
    }

}
