/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author ADMIN
 */
public class ReporteCabeceraBeans {

  
    private JButton btnGrabar;
    private JButton btnEditar;    
    private JButton btnEnviar;    
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
    private String tipo_atencion;
    private String tipo_presencial;    
    private String medico;
    private String emvSINO;
    private String codsap;
    private String fecha_ingreso;
    private String fecha_naciomiento;
    private String categoria;

      /**
     * @return the btnEnviar
     */
    public JButton getBtnEnviar() {
        return btnEnviar;
    }

    /**
     * @param btnEnviar the btnEnviar to set
     */
    public void setBtnEnviar(JButton btnEnviar) {
        this.btnEnviar = btnEnviar;
    }

    
    /**
     * @return the btnEditar
     */
    public JButton getBtnEditar() {
        return btnEditar;
    }

    /**
     * @param btnEditar the btnEditar to set
     */
    public void setBtnEditar(JButton btnEditar) {
        this.btnEditar = btnEditar;
    }

    /**
     * @return the btnGrabar
     */
    public JButton getBtnGrabar() {
        return btnGrabar;
    }

    /**
     * @param btnGrabar the btnGrabar to set
     */
    public void setBtnGrabar(JButton btnGrabar) {
        this.btnGrabar = btnGrabar;
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
     * @return the tipo_atencion
     */
    public String getTipo_atencion() {
        return tipo_atencion;
    }

    /**
     * @param tipo_atencion the tipo_atencion to set
     */
    public void setTipo_atencion(String tipo_atencion) {
        this.tipo_atencion = tipo_atencion;
    }

    /**
     * @return the tipo_presencial
     */
    public String getTipo_presencial() {
        return tipo_presencial;
    }

    /**
     * @param tipo_presencial the tipo_presencial to set
     */
    public void setTipo_presencial(String tipo_presencial) {
        this.tipo_presencial = tipo_presencial;
    }

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
     * @return the codsap
     */
    public String getCodsap() {
        return codsap;
    }

    /**
     * @param codsap the codsap to set
     */
    public void setCodsap(String codsap) {
        this.codsap = codsap;
    }

    /**
     * @return the fecha_ingreso
     */
    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    /**
     * @param fecha_ingreso the fecha_ingreso to set
     */
    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    /**
     * @return the fecha_naciomiento
     */
    public String getFecha_naciomiento() {
        return fecha_naciomiento;
    }

    /**
     * @param fecha_naciomiento the fecha_naciomiento to set
     */
    public void setFecha_naciomiento(String fecha_naciomiento) {
        this.fecha_naciomiento = fecha_naciomiento;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
