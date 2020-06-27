/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.bean;

/**
 *
 * @author ADMIN
 */
public class CorreoBeans {

    
    private Integer idCorreo;
    private String correo_envio;
    private String correo_recepcion;
    private String contraseña_envio;
    private String asunto;
    private String mensaje_programado;
    private Integer id_usuario;

    /**
     * @return the id_usuario
     */
    public Integer getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
    /**
     * @return the idCorreo
     */
    public Integer getIdCorreo() {
        return idCorreo;
    }

    /**
     * @param idCorreo the idCorreo to set
     */
    public void setIdCorreo(Integer idCorreo) {
        this.idCorreo = idCorreo;
    }

    /**
     * @return the correo_envio
     */
    public String getCorreo_envio() {
        return correo_envio;
    }

    /**
     * @param correo_envio the correo_envio to set
     */
    public void setCorreo_envio(String correo_envio) {
        this.correo_envio = correo_envio;
    }

    /**
     * @return the correo_recepcion
     */
    public String getCorreo_recepcion() {
        return correo_recepcion;
    }

    /**
     * @param correo_recepcion the correo_recepcion to set
     */
    public void setCorreo_recepcion(String correo_recepcion) {
        this.correo_recepcion = correo_recepcion;
    }

    /**
     * @return the contraseña_envio
     */
    public String getContraseña_envio() {
        return contraseña_envio;
    }

    /**
     * @param contraseña_envio the contraseña_envio to set
     */
    public void setContraseña_envio(String contraseña_envio) {
        this.contraseña_envio = contraseña_envio;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the mensaje_programado
     */
    public String getMensaje_programado() {
        return mensaje_programado;
    }

    /**
     * @param mensaje_programado the mensaje_programado to set
     */
    public void setMensaje_programado(String mensaje_programado) {
        this.mensaje_programado = mensaje_programado;
    }
    
    
}
