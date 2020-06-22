/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.bean;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class PersonalExalmarBeans {

    private String dni;
    private String apellidosNombres;
    private String cargo;
    private String sede;
    private String codsap;
    private String fechaIngreso;
    private String fechaNacimiento;
    private String catgoria;
    private String tipoPersonal;

    /**
     * @return the apellidosNombres
     */
    public String getApellidosNombres() {
        return apellidosNombres;
    }

    /**
     * @param apellidosNombres the apellidosNombres to set
     */
    public void setApellidosNombres(String apellidosNombres) {
        this.apellidosNombres = apellidosNombres;
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
     * @return the catgoria
     */
    public String getCatgoria() {
        return catgoria;
    }

    /**
     * @param catgoria the catgoria to set
     */
    public void setCatgoria(String catgoria) {
        this.catgoria = catgoria;
    }

    /**
     * @return the tipoPersonal
     */
    public String getTipoPersonal() {
        return tipoPersonal;
    }

    /**
     * @param tipoPersonal the tipoPersonal to set
     */
    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
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
     * @return the fechaIngreso
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
