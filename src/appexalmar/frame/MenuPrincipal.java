/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.frame;

import Api.swing.frameword.bean.D_ComboBoxObject;
import Api.swing.frameword.bean.D_ControlsObject;
import Api.swing.frameword.bean.D_Menu;
import Api.swing.frameword.bean.D_MenuItem;
import Api.swing.frameword.controles.D_Button;
import Api.swing.frameword.controles.D_CheckBox;
import Api.swing.frameword.controles.D_ComboBox;
import Api.swing.frameword.controles.D_GroupButton;
import Api.swing.frameword.controles.D_Label;
import Api.swing.frameword.controles.D_RadioButton;
import Api.swing.frameword.controles.D_ScrollPane;
import Api.swing.frameword.controles.D_TextArea;
import Api.swing.frameword.controles.D_TextField;
import Api.swing.frameword.frame.D_FrameLayout;
import appexalmar.bean.Singletoon;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ADMIN
 */
public class MenuPrincipal extends D_FrameLayout {

    public MenuPrincipal(int _width, int _height, boolean _enableDestopPane) throws HeadlessException, Exception {
        super(_width, _height, _enableDestopPane);
        EventQueue.invokeLater(() -> {
            try {
                Configuration();
            } catch (Exception ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void Configuration() throws Exception {
        this.BackgroundColor(new Color(52,202,188));

        this.EnableMenu(true, AddMenu(), new D_Label("Reporte Exalmar", Color.white, true));
        this.EnableformularyWatch();
        this.AddformularyVersion(Singletoon.version);
        this.AddformularyAutor(Singletoon.autor, Color.yellow);
        this.AddFooterControls(null, new Color(52,202,188), 1, Color.PINK);

//        List<D_ControlsObject> listaFiltros = new ArrayList<>();
//        List<List<D_ControlsObject>> listaFamiliaFiltros = new ArrayList<>();
//        D_ControlsObject filters = new D_ControlsObject();
//        filters.setControlName(new D_Label("Nombre:", Color.WHITE, false, D_Label.ALING.RIGHT, 14));
//        filters.setTypeControl(new D_TextField(D_TextField.Type.FLOATING, 0, 0));
//        listaFiltros.add(filters);
//        listaFamiliaFiltros.add(listaFiltros);
//
//        D_ComboBox comboBox = new D_ComboBox(D_ComboBox.TypeObject.ICON, 0, 0);
//        comboBox.addItemObjectList(AgregarItem());
//
//        filters = new D_ControlsObject();
//        filters.setControlName(new D_Label("Imagenes:", Color.WHITE, false, D_Label.ALING.RIGHT, 14));
//        filters.setTypeControl(comboBox);
//        listaFiltros.add(filters);
//        listaFamiliaFiltros.add(listaFiltros);
//
//        D_TextArea area1 = new D_TextArea(true, 2, 2, Color.WHITE);
//        D_ScrollPane d_ScrollPane = new D_ScrollPane(0, 0, area1);
//
//        filters = new D_ControlsObject();
//        filters.setControlName(new D_Label("Observaciones:", Color.WHITE, false, D_Label.ALING.RIGHT, 14));
//        filters.setTypeControl(d_ScrollPane);
//        listaFiltros.add(filters);
//
//        listaFamiliaFiltros.add(listaFiltros);
//
//        D_CheckBox M = new D_CheckBox();
//        M.setText("M");
//        D_RadioButton F = new D_RadioButton();
//        F.setText("F");
//        List<Object> listaBotones = new ArrayList<>();
//        listaBotones.add(M);
//        listaBotones.add(F);
//        //D_GroupButton panelRadioButon = new D_GroupButton(true, listaBotones, D_GroupButton.ORIENTATION.HORIZOTAL);
//        filters = new D_ControlsObject();
//        filters.setControlName(new D_Label("Sexo:", Color.BLUE, false, D_Label.ALING.RIGHT, 12));
//        filters.setTypeControl(M);
//        listaFiltros.add(filters);
//
//        listaFamiliaFiltros.add(listaFiltros);
//
//        listaFiltros = new ArrayList<>();
//        filters = new D_ControlsObject();
//        filters.setTypeControl(new D_Button(D_Button.TypeButton.SQUARE, new ImageIcon(MenuPrincipal.class.getResource("../image/buscar_small_16px.png")), Color.darkGray));
//        listaFiltros.add(filters);
//        listaFamiliaFiltros.add(listaFiltros);
//
//        this.AddFiltersToFrameHeader(listaFamiliaFiltros, Color.PINK, "");
    }

    private List<D_Menu> AddMenu() {
        List<D_Menu> listaMenu = new ArrayList<>();
        List<D_MenuItem> listaMenuItem = new ArrayList<>();

        D_Menu d_Menu = new D_Menu();
        d_Menu.setTitulo("Registros");

        D_MenuItem d_MenuItem = new D_MenuItem();
        d_MenuItem.setTitulo("Reporte");
        d_MenuItem.setInternalFrame(new ReporteInternal(this, 1000, 600, false));
        
        listaMenuItem.add(d_MenuItem);
        
        d_MenuItem = new D_MenuItem();
        d_MenuItem.setTitulo("Correo");
        d_MenuItem.setInternalFrame(new CorreoInternal(this, 400, 350, false));
        
        listaMenuItem.add(d_MenuItem);
        
        d_Menu.setMenuItemBeans(listaMenuItem);
        listaMenu.add(d_Menu);
        
        listaMenuItem= new ArrayList<>();
        d_Menu = new D_Menu();
        d_Menu.setTitulo("Carga Data");
        
        d_MenuItem = new D_MenuItem();
        d_MenuItem.setTitulo("Importar Personal");
        d_MenuItem.setInternalFrame(new ImportarPersonalInternal(this, 1000, 600, false));
        listaMenuItem.add(d_MenuItem);
        
        d_MenuItem = new D_MenuItem();
        d_MenuItem.setTitulo("Importar Reporte");
        d_MenuItem.setInternalFrame(new ImportarReporteInternal(this, 1000, 600, false));
        listaMenuItem.add(d_MenuItem);
        
        d_Menu.setMenuItemBeans(listaMenuItem);
        listaMenu.add(d_Menu);
        
        return listaMenu;
    }

    private List<D_ComboBoxObject> AgregarItem() {
        ImageIcon icon = new ImageIcon(MenuPrincipal.class.getResource("../image/buscar_small_16px.png"));
        ImageIcon icon2 = new ImageIcon(MenuPrincipal.class.getResource("../image/cargando_small_16px.png"));
        ImageIcon icon3 = new ImageIcon(MenuPrincipal.class.getResource("../image/guardar_smal16px.png"));
        ImageIcon icon4 = new ImageIcon(MenuPrincipal.class.getResource("../image/icon-login-16px.png"));
        ImageIcon icon5 = new ImageIcon(MenuPrincipal.class.getResource("../image/iconscancel-16px.png"));
        D_ComboBoxObject bean = new D_ComboBoxObject();
        bean.setId("1");
        bean.setDescripcion("dato");
        bean.setImageIcon(icon);

        List<D_ComboBoxObject> lista = new ArrayList<>();
        lista.add(bean);
        bean = new D_ComboBoxObject();
        bean.setId("1");
        bean.setDescripcion("dato 2");
        bean.setImageIcon(icon2);
        lista.add(bean);
        bean = new D_ComboBoxObject();
        bean.setId("1");
        bean.setDescripcion("dato 3");
        bean.setImageIcon(icon3);
        lista.add(bean);
        bean = new D_ComboBoxObject();
        bean.setId("1");
        bean.setDescripcion("dato 4");
        bean.setImageIcon(icon4);
        lista.add(bean);
        bean = new D_ComboBoxObject();
        bean.setId("1");
        bean.setDescripcion("dato 5");
        bean.setImageIcon(icon5);
        lista.add(bean);
//            bean = new D_ComboBoxObject();
//            bean.setId("1");
//            bean.setDescripcion("dato 6");
//            bean.setImageIcon(iconGrabar);
//            lista.add(bean);
//            bean = new D_ComboBoxObject();
//            bean.setId("1");
//            bean.setDescripcion("dato 7");
//            bean.setImageIcon(iconGrabar);
//            lista.add(bean);
//            bean = new D_ComboBoxObject();
//            bean.setId("1");
//            bean.setDescripcion("dato 8");
//            bean.setImageIcon(iconGrabar);
//            lista.add(bean);
//            bean = new D_ComboBoxObject();
//            bean.setId("1");
//            bean.setDescripcion("dato 9");
//            bean.setImageIcon(iconGrabar);
//            lista.add(bean);
//            bean = new D_ComboBoxObject();
//            bean.setId("1");
//            bean.setDescripcion("dato 10");
//            bean.setImageIcon(iconGrabar);
//            lista.add(bean);
        return lista;
    }

}
