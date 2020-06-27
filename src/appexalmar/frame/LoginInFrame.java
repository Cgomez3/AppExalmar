/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.frame;

import Api.swing.frameword.bean.D_ControlsObject;
import Api.swing.frameword.controles.D_Button;
import Api.swing.frameword.controles.D_Label;
import Api.swing.frameword.controles.D_PasswordField;
import Api.swing.frameword.controles.D_TextField;
import Api.swing.frameword.frame.D_FrameLayout;
import Api.swing.frameword.panel.D_PanelGroup;
import Api.swing.frameword.panel.D_PanelGroupItem;
import appexalmar.bean.Singletoon;
import appexalmar.bean.UsuarioBeans;
import appexalmar.image.RutaImagen;
import appexalmar.model.UsuaroModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class LoginInFrame extends D_FrameLayout {

    D_FrameLayout d_FrameLayout;
    UsuaroModel usuaroModel = new UsuaroModel();

    public LoginInFrame(int _width, int _height, boolean _enableDestopPane) throws HeadlessException, Exception {
        super(_width, _height, _enableDestopPane);
        d_FrameLayout = this;
        Configuration();
    }

    private void Configuration() throws Exception {
        this.BackgroundColor(new Color(52, 202, 188));
        this.EnableMenu(false, null, new D_Label("Ingresar a Reporte de LLamadas", Color.white, false));

        D_TextField txtUsuarioLog = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtUsuarioLog.setPreferredSize(new Dimension(170, 30));
        D_PasswordField txtpass = new D_PasswordField(1, 10);
        D_Button btnIngresar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Ingresar");
        D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Cancelar");
        D_Button btnRegistrar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("addsi.png")));
        btnRegistrar.setPreferredSize(new Dimension(50, 30));

        D_PanelGroup formGroup = new D_PanelGroup(new Color(52, 202, 188));
        formGroup.setBackground(Color.GREEN);
        D_PanelGroupItem groupItem = new D_PanelGroupItem();
        groupItem.setBackground(new Color(52, 202, 188));

        groupItem.AddTitle(new D_Label("INGRESAR", Color.BLACK, true), null, 1);

        Object[] columnas = {new D_Label("Usuario:", Color.BLACK, false), txtUsuarioLog};
        Object[] columnas1 = {new D_Label("Clave:", Color.BLACK, false), txtpass};
        List<Object[]> filas = new ArrayList<>();
        filas.add(columnas);
        filas.add(columnas1);
        groupItem.VerticalAlignedContainerBody(filas);
//
        List<Object> filasControles = new ArrayList<>();
        filasControles.add(btnIngresar);
        filasControles.add(btnCancelar);
        filasControles.add(btnRegistrar);

        groupItem.AddFooter(filasControles, 0);

        formGroup.AddComponet(0, 0, 1, 1, 0, groupItem);
        this.AddFrameToBody(formGroup);
        this.AddformularyAutor(Singletoon.autor, Color.yellow);
        this.AddFooterControls(null, null, 1, null);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d_FrameLayout.CloseFrame();
            }
        });

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioBeans usuarioFilter = new UsuarioBeans();
                usuarioFilter.setUsuario(txtUsuarioLog.getText());
                usuarioFilter.setClave(txtpass.getText());
                try {
                    UsuarioBeans usuarioBeans = usuaroModel.ObtieneUsuario(usuarioFilter);
                    if (usuarioBeans != null) {
                        Singletoon.getInstance().usuario = usuarioBeans;
                        MenuPrincipal menuPrincipal = new MenuPrincipal(1200, 700, true);
                        menuPrincipal.ShowFrame();
                        d_FrameLayout.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario y Clave incorrecto!!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginInFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LoginInFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    D_FrameLayout frameRegistro = new D_FrameLayout(350, 300, false);
                    frameRegistro.BackgroundColor(Color.GRAY);
                    //frameRegistro.EnableMenu(false, null, new D_Label("Registrar Usuario", Color.white, false));

                    D_PanelGroup formGroup = new D_PanelGroup(Color.RED);
                    D_PanelGroupItem groupItem = new D_PanelGroupItem();
                    groupItem.setPreferredSize(new Dimension(300, 200));
                    groupItem.setBackground(new Color(52, 202, 188));
                    D_TextField txtNombreReg = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtNombreReg.setPreferredSize(new Dimension(180, 30));
                    D_TextField txtUsuarioReg = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtClaveReg = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);

                    groupItem.AddTitle(new D_Label("Registro de Usuario", Color.WHITE, true), null, 1);
                    Object[] columnas = {new D_Label("Nombres:", Color.WHITE, false), txtNombreReg};
                    Object[] columnas1 = {new D_Label("Usuario:", Color.WHITE, false), txtUsuarioReg};
                    Object[] columnas2 = {new D_Label("Clave:", Color.WHITE, false), txtClaveReg};
                    List<Object[]> filas = new ArrayList<>();
                    filas.add(columnas);
                    filas.add(columnas1);
                    filas.add(columnas2);
                    groupItem.VerticalAlignedContainerBody(filas);

                    D_Button btnGrabarReg = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Grabar");
                    D_Button btnCancelarReg = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Cancelar");
                    List<Object> filasControles = new ArrayList<>();
                    filasControles.add(btnGrabarReg);
                    filasControles.add(btnCancelarReg);

                    groupItem.AddFooter(filasControles, 0);

                    formGroup.AddComponet(0, 0, 1, 1, 0, groupItem);
                    frameRegistro.AddFrameToBody(formGroup);
                    frameRegistro.AddformularyAutor(Singletoon.autor, Color.yellow);
                    frameRegistro.AddFooterControls(null, null, 1, null);
                    frameRegistro.ShowFrame();
                    btnGrabarReg.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(null, "Va registrar el Usuario " + txtUsuarioReg.getText() + ". Desea Continuar?", "Registro", JOptionPane.OK_CANCEL_OPTION) == 0) {
                                try {
                                    UsuarioBeans usuarioBeans = new UsuarioBeans();
                                    usuarioBeans.setNombre(txtNombreReg.getText());
                                    usuarioBeans.setUsuario(txtUsuarioReg.getText());
                                    usuarioBeans.setClave(txtClaveReg.getText());
                                    usuaroModel.GrabarUsuario(usuarioBeans);
                                    JOptionPane.showMessageDialog(null, "Usuario Registrado");
                                    frameRegistro.dispose();
                                } catch (SQLException ex) {
                                    Logger.getLogger(LoginInFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                    btnCancelarReg.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frameRegistro.dispose();
                        }
                    });
                } catch (Exception ex) {
                    Logger.getLogger(LoginInFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

}
