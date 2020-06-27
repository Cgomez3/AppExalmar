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
public class Singletoon {
    private static Singletoon instanciaUnica;
    public static UsuarioBeans usuario=null;
    public static String version="1.0.0";
    public static String autor="Cristhian gomez";
    private Singletoon() {}

    private synchronized static void createInstance() {
        if (instanciaUnica == null) { 
            instanciaUnica = new Singletoon();
        }
    }
 
    public static Singletoon getInstance() {
        createInstance();

        return instanciaUnica;
    }
}
