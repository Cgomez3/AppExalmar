/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.interfaz;

import appexalmar.bean.DetalleReporteBeans;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.bean.ReporteCabeceraBeans;
import appexalmar.bean.TableDetalleReporteBeans;
import appexalmar.bean.TableReporteBean;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IReporte {
    List<ReporteCabeceraBeans> listaReporte() throws SQLException;
    Object[] listaReporte(int idCabecera) throws SQLException;
    PersonalExalmarBeans obtienePersonal(int codSap) throws SQLException;
    List<DetalleReporteBeans> listaDetalleReporte(int id_cabecera) throws SQLException;
    List<TableDetalleReporteBeans> listaTableDetalleReporte(int id_cabecera) throws SQLException;
    long GrabarReporte (TableReporteBean reporteBean) throws SQLException;
    void GrabarDetalle (TableDetalleReporteBeans detalleBean,int id_cabecera) throws SQLException;
    Integer ObtenerNumero()throws SQLException;    
    void ActualizarReporte(TableReporteBean reporteBean,int id_cabecera) throws SQLException;
    void EliminaDetalleReporte(int id_cabecera) throws SQLException;
}
