/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar;

import appexalmar.frame.LoginInFrame;
import appexalmar.frame.MenuPrincipal;
import java.awt.EventQueue;

/**
 *
 * @author ADMIN
 */
public class AppExalmar {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                LoginInFrame frame=new LoginInFrame(350,215, false);
                frame.ShowFrame();
                System.out.println("appexalmar.AppExalmar.main()");
                
                
            } catch (Exception e) {
                System.out.println("Error: "+ e.getMessage());
            }
        });

    }

}
