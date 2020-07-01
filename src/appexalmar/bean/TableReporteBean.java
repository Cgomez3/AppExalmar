/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.bean;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class TableReporteBean {

   
    private int numero;
    private String fecha;
    private String hora;
    private String celular;
    private String LocalidadDomicilio;   
    private String ep;
    private String dni;
    private String ape_nom;
    private String cargo;
     private String confinadoDonde;
    private String sede;
    private String tipoAtencionSeguimiento;
    private String tipoPrecencialVirtual;
    private String medico;
    private String emvSINO;
    private String codSap;
    
    private List<TableDetalleReporteBeans> listaDetalle;

    
     /**
     * @return the medico
     */
    public String getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(String medico) {
        this.medico = medico;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the LocalidadDomicilio
     */
    public String getLocalidadDomicilio() {
        return LocalidadDomicilio;
    }

    /**
     * @param LocalidadDomicilio the LocalidadDomicilio to set
     */
    public void setLocalidadDomicilio(String LocalidadDomicilio) {
        this.LocalidadDomicilio = LocalidadDomicilio;
    }

    /**
     * @return the confinadoDonde
     */
    public String getConfinadoDonde() {
        return confinadoDonde;
    }

    /**
     * @param confinadoDonde the confinadoDonde to set
     */
    public void setConfinadoDonde(String confinadoDonde) {
        this.confinadoDonde = confinadoDonde;
    }

    /**
     * @return the sede
     */
    public String getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(String sede) {
        this.sede = sede;
    }

    /**
     * @return the ep
     */
    public String getEp() {
        return ep;
    }

    /**
     * @param ep the ep to set
     */
    public void setEp(String ep) {
        this.ep = ep;
    }

    /**
     * @return the tipoAtencionSeguimiento
     */
    public String getTipoAtencionSeguimiento() {
        return tipoAtencionSeguimiento;
    }

    /**
     * @param tipoAtencionSeguimiento the tipoAtencionSeguimiento to set
     */
    public void setTipoAtencionSeguimiento(String tipoAtencionSeguimiento) {
        this.tipoAtencionSeguimiento = tipoAtencionSeguimiento;
    }

    /**
     * @return the tipoPrecencialVirtual
     */
    public String getTipoPrecencialVirtual() {
        return tipoPrecencialVirtual;
    }

    /**
     * @param tipoPrecencialVirtual the tipoPrecencialVirtual to set
     */
    public void setTipoPrecencialVirtual(String tipoPrecencialVirtual) {
        this.tipoPrecencialVirtual = tipoPrecencialVirtual;
    }

    /**
     * @return the emvSINO
     */
    public String getEmvSINO() {
        return emvSINO;
    }

    /**
     * @param emvSINO the emvSINO to set
     */
    public void setEmvSINO(String emvSINO) {
        this.emvSINO = emvSINO;
    }

    /**
     * @return the listaDetalle
     */
    public List<TableDetalleReporteBeans> getListaDetalle() {
        return listaDetalle;
    }

    /**
     * @param listaDetalle the listaDetalle to set
     */
    public void setListaDetalle(List<TableDetalleReporteBeans> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    /**
     * @return the codSap
     */
    public String getCodSap() {
        return codSap;
    }

    /**
     * @param codSap the codSap to set
     */
    public void setCodSap(String codSap) {
        this.codSap = codSap;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the ape_nom
     */
    public String getApe_nom() {
        return ape_nom;
    }

    /**
     * @param ape_nom the ape_nom to set
     */
    public void setApe_nom(String ape_nom) {
        this.ape_nom = ape_nom;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
