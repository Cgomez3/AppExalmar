/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.frame;

import Api.swing.frameword.abstractobject.D_TableModelo;
import Api.swing.frameword.bean.D_ControlsObject;
import Api.swing.frameword.bean.Enumerator;
import Api.swing.frameword.controles.D_Button;
import Api.swing.frameword.controles.D_CheckBox;
import Api.swing.frameword.controles.D_GroupButton;
import Api.swing.frameword.controles.D_Label;
import Api.swing.frameword.controles.D_ProgressBar;
import Api.swing.frameword.controles.D_ScrollPane;
import Api.swing.frameword.controles.D_Table;
import Api.swing.frameword.controles.D_TextArea;
import Api.swing.frameword.controles.D_TextField;
import Api.swing.frameword.frame.D_InternalFrameLayout;
import Api.swing.frameword.panel.D_PanelTable;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.image.RutaImagen;
import appexalmar.model.PersonaModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class CorreoInternal extends D_InternalFrameLayout {

    private D_InternalFrameLayout internalFrameLayout;
    private D_TableModelo defaultTableModel;
    private List<PersonalExalmarBeans> listaReporte = new ArrayList<>();
    private final JFileChooser fileChooser = new JFileChooser();
    private D_Table table = new D_Table();
    private String titleHeader[];
    private PersonaModel personaModel = new PersonaModel();
    private static D_ProgressBar progreso;
    private static D_Button btnCargar;
    private static D_Button btnArchivo;
    private static D_TextField txthoja;
    private static D_TextField txtArchivo;
    private static D_CheckBox chkExalmar;
    private static D_CheckBox chkTripulante;

    public CorreoInternal(Object _frame, int _width, int _height, boolean _enableDestopPane) throws HeadlessException {
        super(_frame, _width, _height, _enableDestopPane);
        EventQueue.invokeLater(() -> {
            try {
                Configuracion();
            } catch (Exception ex) {
                Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void Configuracion() throws Exception {
        internalFrameLayout = this;
        this.BackgroundColor(new Color(52, 202, 188));
        this.EnableMenu(false, null, new D_Label("Registro de Correos", Color.white, true));

        List<D_ControlsObject> listaFiltros = new ArrayList<>();
        List<List<D_ControlsObject>> listaFamiliaFiltros = new ArrayList<>();
        D_ControlsObject filters = new D_ControlsObject();

        D_TextField txtCorreoEnvio = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtCorreoEnvio.setPreferredSize(new Dimension(200, 30));
        D_TextField txtcontraseña = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        D_TextArea txtCorreoRecepcion = new D_TextArea(false, 3, 1, Color.WHITE);
        D_ScrollPane d_Scrollcorreo= new D_ScrollPane(1, 10, txtCorreoRecepcion);
        d_Scrollcorreo.setPreferredSize(new Dimension(200, 90));
        
        D_TextField txtAsunto = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtAsunto.setPreferredSize(new Dimension(200, 30));
         D_TextArea txtMensajeProgramado = new D_TextArea(false, 3, 1, Color.WHITE);
        D_ScrollPane d_ScrollMensaje= new D_ScrollPane(1, 10, txtMensajeProgramado);
        d_ScrollMensaje.setPreferredSize(new Dimension(200, 90));
        

        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("De:", Color.BLACK, true));
        filters.setTypeControl(txtCorreoEnvio);
        listaFiltros.add(filters);

        filters = new D_ControlsObject();
        filters.setTypeControl(txtcontraseña);
        filters.setControlName(new D_Label("Contraseña:", Color.BLACK, true));
        listaFiltros.add(filters);
        listaFamiliaFiltros.add(listaFiltros);

        filters = new D_ControlsObject();
        filters.setTypeControl(d_Scrollcorreo);
        filters.setControlName(new D_Label("Para:", Color.BLACK, true));
        listaFiltros.add(filters);

        filters = new D_ControlsObject();
        filters.setTypeControl(txtAsunto);
        filters.setControlName(new D_Label("Asunto:", Color.BLACK, true));
        listaFiltros.add(filters);

        filters = new D_ControlsObject();
        filters.setTypeControl(d_ScrollMensaje);
        filters.setControlName(new D_Label("Mensage:", Color.BLACK, true));
        listaFiltros.add(filters);

        listaFamiliaFiltros.add(listaFiltros);

        this.AddFrameToBody("Registro de Correos", listaFamiliaFiltros, new Color(52, 202, 188));
        List<Object> listaBotones = new ArrayList<>();
        D_Button btnGrabar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("guardar_smal16px.png")));
        D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("iconscancel-16px.png")));
        listaBotones.add(btnGrabar);
        listaBotones.add(btnCancelar);
        this.AddFooterControls(listaBotones, 1);
        this.ShowFrame();

    }

}
