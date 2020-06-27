/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appexalmar.frame;

import Api.swing.frameword.abstractobject.D_TableModelo;
import Api.swing.frameword.bean.D_ControlsObject;
import Api.swing.frameword.bean.D_DataTableObject;
import Api.swing.frameword.bean.Enumerator;
import Api.swing.frameword.controles.D_Button;
import Api.swing.frameword.controles.D_CheckBox;
import Api.swing.frameword.controles.D_Label;
import Api.swing.frameword.controles.D_Panel;
import Api.swing.frameword.controles.D_ProgressBar;
import Api.swing.frameword.controles.D_ScrollPane;
import Api.swing.frameword.controles.D_Table;
import Api.swing.frameword.controles.D_TextArea;
import Api.swing.frameword.controles.D_TextField;
import Api.swing.frameword.frame.D_FrameLayout;
import Api.swing.frameword.frame.D_InternalFrameLayout;
import Api.swing.frameword.panel.D_PanelFooter;
import Api.swing.frameword.panel.D_PanelGroup;
import Api.swing.frameword.panel.D_PanelTable;
import appexalmar.bean.CorreoBeans;
import appexalmar.bean.DetalleReporteBeans;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.bean.ReporteCabeceraBeans;
import appexalmar.bean.Singletoon;
import appexalmar.bean.TableDetalleReporteBeans;
import appexalmar.bean.TableReporteBean;
import appexalmar.image.RutaImagen;
import appexalmar.model.CorreoModel;
import appexalmar.model.PersonaModel;
import appexalmar.model.ReporteModel;
import com.mysql.jdbc.Connection;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class ReporteInternal extends D_InternalFrameLayout {

    private D_InternalFrameLayout internalFrameLayout;
    private DefaultTableModel defaultTableModel;
//    private List<DetalleReporteBeans> listaDetalle = new ArrayList<>();
    private List<TableDetalleReporteBeans> listaTableDetalle = new ArrayList<>();
    D_Table tableDetalle;
    String[] tituloDetalle;
    PersonaModel personaModel = new PersonaModel();
    ReporteModel reporteModel = new ReporteModel();

    D_TextField txtDniDetalle = new D_TextField(D_TextField.Type.NUMERIC, 1, 10);
    D_TextField txtApelliNombresDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtCodSabDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtCelularDetalle = new D_TextField(D_TextField.Type.NUMERIC, 1, 10);
    D_TextField txtLocalidadDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtConfinadoDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtSedeDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtEpDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtAtenSegDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_TextField txtPrenVirDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
    D_CheckBox chksiDetalle = new D_CheckBox(1, 10, Color.WHITE);
    D_Table table;
    CorreoModel correoModel = new CorreoModel();
    private final JFileChooser fileChooser = new JFileChooser();
    private D_ProgressBar d_ProgressBar;
    D_Button btnCragraExcel;
    D_Button btnNuevo;
    D_TextField txtDniFilter;

    D_TextField txtConsultaDetalle;
    D_TextField txtDetalleDetalle;
    D_TextField txtAccionDetalle;
    D_TextField txtDiagnosticoDetalle;
    D_TextField txtMedicacionDetalle;
    D_TextField txtFrecuenciaDetalle;
    D_TextField txtDiasDetalle;
    D_TextField txtCantidadTotalDetalle;
    D_TextArea txtEnvioMensageCorreo;
    D_CheckBox chkEnvioMensage;
    D_TextField txtAsunto;

    public ReporteInternal(Object _frame, int _width, int _height, boolean _enableDestopPane) throws HeadlessException {
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
        this.EnableMenu(false, null, new D_Label("Registro de Llamadas", Color.white, true));

        List<D_ControlsObject> listaFiltros = new ArrayList<>();
        List<List<D_ControlsObject>> listaFamiliaFiltros = new ArrayList<>();
        D_ControlsObject filters = new D_ControlsObject();
        txtDniFilter = new D_TextField(D_TextField.Type.CHARACTER, 1, 5);
        txtDniFilter.setPreferredSize(new Dimension(230, 30));
        filters.setTypeControl(txtDniFilter);
        filters.setControlName(new D_Label("Buscar:", Color.BLACK, true));
        listaFiltros.add(filters);
        listaFamiliaFiltros.add(listaFiltros);

        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        btnNuevo = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("addsi.png")), "Nuevo");

        filters.setControlName(btnNuevo);
        listaFiltros.add(filters);

        listaFamiliaFiltros.add(listaFiltros);
        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        btnCragraExcel = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("excel.png")), "Descargar");

        filters.setControlName(btnCragraExcel);
        d_ProgressBar = new D_ProgressBar(1, 10, Color.RED);
        d_ProgressBar.setPreferredSize(new Dimension(70, 30));
        d_ProgressBar.setForeground(Color.WHITE);
        filters.setTypeControl(d_ProgressBar);
        listaFiltros.add(filters);

        listaFamiliaFiltros.add(listaFiltros);

        this.AddFiltersToFrameHeader(listaFamiliaFiltros, new Color(52, 202, 188), "");

        //Add Table
        MostrarTabla();
        D_PanelTable panelTable = new D_PanelTable(table, Color.BLUE);
        this.AddFrameToBody(panelTable);

        this.ShowFrame();

        btnCragraExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DescargarExcel();
            }
        });
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    D_InternalFrameLayout registroLlamadas = new D_InternalFrameLayout(internalFrameLayout, 800, 500, false);
                    //registroLlamadas.DisableCloseFrameButton();
                    registroLlamadas.BackgroundColor(new Color(52, 202, 188));
                    registroLlamadas.EnableMenu(false, null, new D_Label("Nuevo Registro de LLamadas", Color.white, true));

                    registroLlamadas.AddFiltersToFrameHeader(LLenarControles(registroLlamadas, "", "", "", "", "", "", "", "", "", "", ""), new Color(52, 202, 188), "");

                    tituloDetalle = new String[10];
                    tituloDetalle[0] = "";
                    tituloDetalle[1] = "";
                    tituloDetalle[2] = "CONSULTA";
                    tituloDetalle[3] = "DETALLE";
                    tituloDetalle[4] = "ACCION";
                    tituloDetalle[5] = "DIAGNOSTICO";
                    tituloDetalle[6] = "MEDICACIÓN";
                    tituloDetalle[7] = "FRECUENCIA";
                    tituloDetalle[8] = "DIAS";
                    tituloDetalle[9] = "CANTIDAD TOTAL";
                    tableDetalle = new D_Table(tituloDetalle);
                    AddEventoTableDetalle(registroLlamadas);
                    tableDetalle.AddColumWidth(AnchoColunaDetalle());
                    tableDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    tableDetalle.AddBackGroundColorTable(new Color(52, 202, 188));
                    tableDetalle.AddBackGroundColorTableHeader(new Color(52, 202, 188));
                    tableDetalle.AddColumnOrientation(AlingColunaDetalle());
                    D_PanelTable panelTableDetalle = new D_PanelTable(tableDetalle, Color.BLUE);
                    registroLlamadas.AddFrameToBody(panelTableDetalle);
                    List<Object> listaBotones = new ArrayList<>();
                    D_Button btnGrabar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("guardar_smal16px.png")));
                    D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("iconscancel-16px.png")));
                    listaBotones.add(btnGrabar);
                    listaBotones.add(btnCancelar);

                    registroLlamadas.AddFooterControls(listaBotones, 1);
                    btnCancelar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            registroLlamadas.CloseInternalFrame();
                        }
                    });
                    btnGrabar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(null, "Va a Grabar el Registro, Dese Continuar?", "Registro de Llamada", JOptionPane.OK_OPTION) == 0) {
                                TableReporteBean reporteBean = new TableReporteBean();
                                try {
                                    reporteBean.setNumero(reporteModel.ObtenerNumero());
                                    Date fechaActual = new Date();

                                    reporteBean.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(fechaActual));
                                    reporteBean.setHora(new SimpleDateFormat("HH:MM").format(fechaActual));
                                    reporteBean.setCelular(txtCelularDetalle.getText());
                                    reporteBean.setLocalidadDomicilio(txtLocalidadDetalle.getText());
                                    reporteBean.setConfinadoDonde(txtConfinadoDetalle.getText());
                                    reporteBean.setSede(txtSedeDetalle.getText());
                                    reporteBean.setEp(txtEpDetalle.getText());
                                    reporteBean.setTipoAtencionSeguimiento(txtAtenSegDetalle.getText());
                                    reporteBean.setTipoPrecencialVirtual(txtPrenVirDetalle.getText());
                                    String alerta;
                                    if (chksiDetalle.isSelected()) {
                                        alerta = "SI";
                                    } else {
                                        alerta = "NO";
                                    }
                                    reporteBean.setEmvSINO(alerta);
                                    reporteBean.setCodSap(txtCodSabDetalle.getText());
                                    reporteBean.setMedico("DRA MILAGROS VALENTIN");
                                    long idCabecera = reporteModel.GrabarReporte(reporteBean);
                                    if (listaTableDetalle.size() > 0) {
                                        for (int i = 0; i < listaTableDetalle.size(); i++) {
                                            TableDetalleReporteBeans detalleReporte = listaTableDetalle.get(i);
                                            reporteModel.GrabarDetalle(detalleReporte, (int) idCabecera);
                                        }

                                    }
                                    JOptionPane.showMessageDialog(null, "Se Creó el Registro Correctamente.");
                                    defaultTableModel = (DefaultTableModel) table.getModel();
                                    defaultTableModel.addRow(reporteModel.listaReporte((int) idCabecera));
                                    registroLlamadas.CloseInternalFrame();

                                } catch (SQLException ex) {
                                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    });
                    registroLlamadas.ShowFrame();

                    internalFrameLayout.addShowInternalFrame(internalFrameLayout.getDesktopPanePrincipal(), registroLlamadas);
                } catch (Exception ex) {
                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        txtDniFilter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                EventQueue.invokeLater(() -> {
                    defaultTableModel = (DefaultTableModel) table.getModel();
                    TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
                    table.setRowSorter(rowSorter);
                    rowSorter.setRowFilter(RowFilter.regexFilter(txtDniFilter.getText()));
                });
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public void DescargarExcel() {
        final Thread t;
        t = new Thread(() -> {
            try {
                //String RUTA = "C:\\Users\\ADMIN\\Documents\\milagros\\ReporteAtencionesExalmarw2.xlsx";
                int returnFile = fileChooser.showSaveDialog(btnCragraExcel);
                if (returnFile == JFileChooser.APPROVE_OPTION) {
                    txtDniFilter.setEnabled(false);
                    btnCragraExcel.setEnabled(false);
                    btnNuevo.setEnabled(false);
                    d_ProgressBar.setStringPainted(true);
                    File file = fileChooser.getSelectedFile();
                    //File file = new File(RUTA);
                    if (file.exists()) {
                        System.out.println("existe");
                    }

                    //FileInputStream stream = new FileInputStream(file.getAbsoluteFile());
                    XSSFWorkbook libro = new XSSFWorkbook();
                    XSSFCellStyle estilos = libro.createCellStyle();
                    XSSFFont font = libro.createFont();
                    font.setColor(HSSFColor.WHITE.index);
                    font.setBold(true);
                    estilos.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                    estilos.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    estilos.setFont(font);
                    estilos.setWrapText(true);
                    estilos.setAlignment(HorizontalAlignment.CENTER);
                    estilos.setVerticalAlignment(VerticalAlignment.CENTER);
                    XSSFSheet hoja = libro.createSheet();
                    hoja.setColumnWidth(0, 2000);
                    hoja.setColumnWidth(1, 3000);
                    hoja.setColumnWidth(2, 3000);
                    hoja.setColumnWidth(3, 3000);
                    hoja.setColumnWidth(4, 6000);
                    hoja.setColumnWidth(5, 3000);
                    hoja.setColumnWidth(6, 3000);
                    hoja.setColumnWidth(7, 9000);
                    hoja.setColumnWidth(8, 9000);
                    hoja.setColumnWidth(9, 9000);
                    hoja.setColumnWidth(10, 9000);
                    hoja.setColumnWidth(11, 9000);
                    hoja.setColumnWidth(12, 9800);
                    hoja.setColumnWidth(13, 9000);
                    hoja.setColumnWidth(14, 9000);
                    hoja.setColumnWidth(15, 9000);
                    hoja.setColumnWidth(16, 9000);
                    hoja.setColumnWidth(17, 9000);
                    hoja.setColumnWidth(18, 9000);
                    hoja.setColumnWidth(19, 3500);
                    hoja.setColumnWidth(20, 3500);
                    hoja.setColumnWidth(21, 9500);
                    hoja.setColumnWidth(22, 4000);
                    hoja.setColumnWidth(23, 4000);
                    hoja.setColumnWidth(24, 4000);
                    hoja.setColumnWidth(25, 4000);
                    hoja.setColumnWidth(26, 4000);
                    hoja.setColumnWidth(27, 8000);

                    String[] titulo = new String[28];
                    titulo[0] = "N°";
                    titulo[1] = "FECHA";
                    titulo[2] = "HORA";
                    titulo[3] = "CELULAR";
                    titulo[4] = "LOCALIDAD DE DOMICILIO";
                    titulo[5] = "EP";
                    titulo[6] = "DNI";
                    titulo[7] = "APELLIDOS Y NOMBRES";
                    titulo[8] = "CARGO";
                    titulo[9] = "CONFINADO / DONDE";
                    titulo[10] = "SEDE";
                    titulo[11] = "CONSULTA";
                    titulo[12] = "DETALLE";
                    titulo[13] = "ACCIÓN";
                    titulo[14] = "DIAGNOSTICO";
                    titulo[15] = "MEDICACIÓN";
                    titulo[16] = "FRECUENCIA";
                    titulo[17] = "DIAS";
                    titulo[18] = "CANTIDAD TOTAL";
                    titulo[19] = "TIPO (ATENCIÓN /SEGUIMIENTO DE CASOS)";
                    titulo[20] = "TIPO (PRESENCIAL / VIRTUAL)";
                    titulo[21] = "MEDICO TRATANTE";
                    titulo[22] = "OBSERVACIÓN DEL SEGUIMIENTO";
                    titulo[23] = "ALERTA ENVIADA (SI/NO)";
                    titulo[24] = "COD SAP";
                    titulo[25] = "FECHA INGRESO";
                    titulo[26] = "FECHA DE NACIMIENTO";
                    titulo[27] = "CATEGORIA";

                    XSSFRow fila;
                    XSSFCell columna;
                    fila = hoja.createRow(0);
                    for (int i = 0; i < titulo.length; i++) {

                        columna = fila.createCell(i);

                        columna.setCellStyle(estilos);
                        columna.setCellValue(titulo[i]);

                        CellRangeAddress region = new CellRangeAddress(0, 0, i, i);
                        RegionUtil.setBorderTop(BorderStyle.THIN, region, hoja);
                        RegionUtil.setBorderLeft(BorderStyle.THIN, region, hoja);
                        RegionUtil.setBorderRight(BorderStyle.THIN, region, hoja);
                        RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);
                    }

//                    try (FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile())) {
//                        libro.write(fos);
//                        libro.close();
//                    }
                    int contadorFila = 0;
                    try {

                        List<ReporteCabeceraBeans> listaReporte = reporteModel.listaReporte();
                        int flag = 0;
                        for (int i = 0; i < listaReporte.size(); i++) {
                            ReporteCabeceraBeans reporte = listaReporte.get(i);
                            int idCabecera = Integer.parseInt(((D_Button) reporte.getBtnEditar()).getHiddenValue());
                            List<DetalleReporteBeans> listaDetalle = reporteModel.listaDetalleReporte(idCabecera);
                            for (int j = 0; j < listaDetalle.size(); j++) {
                                DetalleReporteBeans detalle = listaDetalle.get(j);
                                contadorFila += 1;

                                fila = hoja.createRow(contadorFila);
                                if (flag == 0) {
                                    columna = fila.createCell(0);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getNumero());

                                    columna = fila.createCell(1);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getFecha());

                                    columna = fila.createCell(2);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getHora());

                                    columna = fila.createCell(3);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getCelular());

                                    columna = fila.createCell(4);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getLocalidadDomicilio());

                                    columna = fila.createCell(5);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getEp());

                                    columna = fila.createCell(6);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getDni());

                                    columna = fila.createCell(7);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getApe_nom());

                                    columna = fila.createCell(8);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getCargo());

                                    columna = fila.createCell(9);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getConfinadoDonde());

                                    columna = fila.createCell(10);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getSede());

                                    columna = fila.createCell(19);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getTipo_atencion());

                                    columna = fila.createCell(20);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getTipo_presencial());

                                    columna = fila.createCell(21);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getMedico());

                                    columna = fila.createCell(23);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getEmvSINO());

                                    columna = fila.createCell(24);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getCodsap());

                                    columna = fila.createCell(25);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getFecha_ingreso());

                                    columna = fila.createCell(26);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getFecha_naciomiento());

                                    columna = fila.createCell(27);
                                    columna.setCellType(CellType.STRING);
                                    columna.setCellValue(reporte.getCategoria());
                                }
                                columna = fila.createCell(11);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getConsulta());
                                CellRangeAddress region = new CellRangeAddress(contadorFila, contadorFila, 11, 11);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(12);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getDetalle());
                                region = new CellRangeAddress(contadorFila, contadorFila, 12, 12);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(13);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getAcción());
                                region = new CellRangeAddress(contadorFila, contadorFila, 13, 13);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(14);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getDiacnostico());
                                region = new CellRangeAddress(contadorFila, contadorFila, 14, 14);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(15);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getMedicación());
                                region = new CellRangeAddress(contadorFila, contadorFila, 15, 15);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(16);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getFrecuencia());
                                region = new CellRangeAddress(contadorFila, contadorFila, 16, 16);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(17);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getDias());
                                region = new CellRangeAddress(contadorFila, contadorFila, 17, 17);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                columna = fila.createCell(18);
                                columna.setCellType(CellType.STRING);
                                columna.setCellValue(detalle.getCanntidadTotal());
                                region = new CellRangeAddress(contadorFila, contadorFila, 18, 18);
                                RegionUtil.setBorderBottom(BorderStyle.THIN, region, hoja);

                                flag += 1;
                                if (flag == listaDetalle.size()) {
                                    flag = 0;
                                    region = new CellRangeAddress(contadorFila, contadorFila, 0, 27);
                                    RegionUtil.setBorderBottom(BorderStyle.MEDIUM, region, hoja);
                                }

                            }
                            d_ProgressBar.setString(String.valueOf(((i + 1) * 100) / listaReporte.size()).concat("%"));
                            d_ProgressBar.setValue(((i + 1) * 100) / listaReporte.size());
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        try (FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile() + ".xlsx")) {
                            libro.write(fos);
                            libro.close();
                        }
                        btnCragraExcel.setEnabled(true);
                        btnNuevo.setEnabled(true);
                        txtDniFilter.setEnabled(true);
                        d_ProgressBar.setString("0%");
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EncryptedDocumentException ex) {
                Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        t.start();

    }

    private void MostrarTabla() {
        String titleHeader[] = {"", "", "", "N°", "Fecha", "Hora", "Celular", "Localidad de  Domicilio", "EP", "DNI", "Ape/Nombre", "Cargo", "Confinado/Donde", "Sede", "Tipo/Atención/Seguimiento", "Tipo/Presencial", "medico", "Alerta", "Cod. Sap.", "Fecha Ing.", "Fecha Nac.", "Categoria"};

        List<Integer> listaDisable = new ArrayList<>();
        listaDisable.add(2);
        listaDisable.add(3);
        try {

            table = new D_Table(titleHeader, reporteModel.listaReporte());
            table.AddBackGroundColorTable(new Color(52, 202, 188));
            table.AddBackGroundColorTableHeader(new Color(52, 202, 188));
            table.EnableRowSorter(true);
            table.AddColumWidth(ModificarAnchoColumna());
            table.AddColumnOrientation(CentrarColumnasDeTabla());
            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int colum = table.getColumnModel().getColumnIndexAtX(e.getX());
                    int row = e.getY() / table.getRowHeight();

                    if (row < table.getRowCount() && row >= 0 && colum < table.getColumnCount() && colum >= 0) {
                        Object value = table.getValueAt(row, colum);
                        if (value instanceof D_Button) {
                            ((D_Button) value).doClick();
                            D_Button button = (D_Button) value;
                            if (button.getName().equals("E")) {
                                try {
                                    D_InternalFrameLayout registroLlamadas = new D_InternalFrameLayout(internalFrameLayout, 800, 500, false);
                                    //registroLlamadas.DisableCloseFrameButton();
                                    registroLlamadas.BackgroundColor(new Color(52, 202, 188));
                                    registroLlamadas.EnableMenu(false, null, new D_Label("Actualizar Registro de LLamadas", Color.white, true));
                                    Object codSap = table.getValueAt(row, 18);
                                    Object DNI;
                                    Object apeNom;
                                    if (((String) codSap).isEmpty()) {
                                        codSap = "0";
                                    }
                                    PersonalExalmarBeans personal = personaModel.ObtinePersona((String) codSap);
                                    if (personal != null) {
                                        DNI = personal.getDni();
                                        apeNom = personal.getApellidosNombres();
                                        codSap = personal.getCodsap();
                                    } else {
                                        DNI = "";
                                        apeNom = "";
                                        codSap = "";
                                    }

                                    Object celular = table.getValueAt(row, 6);
                                    Object localidad = table.getValueAt(row, 7);
                                    Object ep = table.getValueAt(row, 8);
                                    Object confinado = table.getValueAt(row, 12);
                                    Object sede = table.getValueAt(row, 13);
                                    Object tipoAtencion = table.getValueAt(row, 14);
                                    Object tipoPresencial = table.getValueAt(row, 15);
                                    Object alerta = table.getValueAt(row, 17);

                                    int id_cabecera_editar = Integer.parseInt(button.getHiddenValue());

                                    registroLlamadas.AddFiltersToFrameHeader(LLenarControles(registroLlamadas, (String) DNI, (String) apeNom, (String) codSap, (String) celular, (String) localidad, (String) confinado, (String) sede, (String) ep, (String) tipoAtencion, (String) tipoPresencial, (String) alerta), new Color(52, 202, 188), "");

                                    tituloDetalle = new String[10];
                                    tituloDetalle[0] = "";
                                    tituloDetalle[1] = "";
                                    tituloDetalle[2] = "CONSULTA";
                                    tituloDetalle[3] = "DETALLE";
                                    tituloDetalle[4] = "ACCION";
                                    tituloDetalle[5] = "DIAGNOSTICO";
                                    tituloDetalle[6] = "MEDICACIÓN";
                                    tituloDetalle[7] = "FRECUENCIA";
                                    tituloDetalle[8] = "DIAS";
                                    tituloDetalle[9] = "CANTIDAD TOTAL";
                                    listaTableDetalle = reporteModel.listaTableDetalleReporte(id_cabecera_editar);
                                    if (listaTableDetalle.size() > 0) {
                                        tableDetalle = new D_Table(tituloDetalle, listaTableDetalle);
                                    } else {
                                        tableDetalle = new D_Table(tituloDetalle);
                                    }
                                    AddEventoTableDetalle(registroLlamadas);

                                    tableDetalle.AddColumWidth(AnchoColunaDetalle());
                                    tableDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    tableDetalle.AddBackGroundColorTable(new Color(52, 202, 188));
                                    tableDetalle.AddBackGroundColorTableHeader(new Color(52, 202, 188));
                                    tableDetalle.AddColumnOrientation(AlingColunaDetalle());
                                    D_PanelTable panelTableDetalle = new D_PanelTable(tableDetalle, Color.BLUE);
                                    registroLlamadas.AddFrameToBody(panelTableDetalle);
                                    List<Object> listaBotones = new ArrayList<>();
                                    D_Button btnGrabar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("guardar_smal16px.png")));
                                    D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("iconscancel-16px.png")));
                                    listaBotones.add(btnGrabar);
                                    listaBotones.add(btnCancelar);

                                    registroLlamadas.AddFooterControls(listaBotones, 1);
                                    btnCancelar.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            registroLlamadas.CloseInternalFrame();
                                        }
                                    });
                                    btnGrabar.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (JOptionPane.showConfirmDialog(null, "Va Actualizar el Registro, Desea Continuar?", "Actualizar Registro", JOptionPane.PLAIN_MESSAGE) == 0) {
                                                TableReporteBean reporteBean = new TableReporteBean();
                                                try {
                                                    Object numero = table.getValueAt(row, 3);
                                                    reporteBean.setNumero((int) numero);
                                                    Date date = new Date();

                                                    reporteBean.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(date));
                                                    reporteBean.setHora(new SimpleDateFormat("HH:MM").format(date));
                                                    reporteBean.setCelular(txtCelularDetalle.getText());
                                                    reporteBean.setLocalidadDomicilio(txtLocalidadDetalle.getText());
                                                    reporteBean.setConfinadoDonde(txtConfinadoDetalle.getText());
                                                    reporteBean.setSede(txtSedeDetalle.getText());
                                                    reporteBean.setEp(txtEpDetalle.getText());
                                                    reporteBean.setTipoAtencionSeguimiento(txtAtenSegDetalle.getText());
                                                    reporteBean.setTipoPrecencialVirtual(txtPrenVirDetalle.getText());
                                                    String alerta;
                                                    if (chksiDetalle.isSelected()) {
                                                        alerta = "SI";
                                                    } else {
                                                        alerta = "NO";
                                                    }
                                                    reporteBean.setEmvSINO(alerta);
                                                    reporteBean.setCodSap(txtCodSabDetalle.getText());
                                                    reporteBean.setMedico("DRA MILAGROS VALENTIN");
                                                    reporteModel.ActualizarReporte(reporteBean, id_cabecera_editar);
                                                    reporteModel.EliminaDetalleReporte(id_cabecera_editar);
                                                    if (listaTableDetalle.size() > 0) {
                                                        for (int i = 0; i < listaTableDetalle.size(); i++) {
                                                            TableDetalleReporteBeans detalleReporte = listaTableDetalle.get(i);
                                                            reporteModel.GrabarDetalle(detalleReporte, id_cabecera_editar);
                                                        }
                                                    }
                                                    JOptionPane.showMessageDialog(null, "Se Actualizo el Registro Correctamente.");
                                                    defaultTableModel = (DefaultTableModel) table.getModel();
                                                    Object[] objetoActualizar = reporteModel.listaReporte(id_cabecera_editar);
                                                    for (int i = 0; i < objetoActualizar.length; i++) {
                                                        defaultTableModel.setValueAt(objetoActualizar[i], row, i);
                                                    }
                                                    registroLlamadas.CloseInternalFrame();
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                    });
                                    registroLlamadas.ShowFrame();

                                    internalFrameLayout.addShowInternalFrame(internalFrameLayout.getDesktopPanePrincipal(), registroLlamadas);
                                } catch (Exception ex) {
                                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (button.getName().equals("G")) {
                                try {
                                    D_InternalFrameLayout registroLlamadas = new D_InternalFrameLayout(internalFrameLayout, 800, 500, false);
                                    //registroLlamadas.DisableCloseFrameButton();
                                    registroLlamadas.BackgroundColor(new Color(52, 202, 188));
                                    registroLlamadas.EnableMenu(false, null, new D_Label("Registrar LLamada", Color.white, true));
                                    Object codSap = table.getValueAt(row, 18);
                                    Object DNI;
                                    Object apeNom;
                                    if (((String) codSap).isEmpty()) {
                                        codSap = "0";
                                    }
                                    PersonalExalmarBeans personal = personaModel.ObtinePersona((String) codSap);
                                    if (personal != null) {
                                        DNI = personal.getDni();
                                        apeNom = personal.getApellidosNombres();
                                        codSap = personal.getCodsap();
                                    } else {
                                        DNI = "";
                                        apeNom = "";
                                        codSap = "";
                                    }

                                    Object celular = table.getValueAt(row, 6);
                                    Object localidad = table.getValueAt(row, 7);
                                    Object ep = table.getValueAt(row, 8);
                                    Object confinado = table.getValueAt(row, 12);
                                    Object sede = table.getValueAt(row, 13);
                                    Object tipoAtencion = table.getValueAt(row, 14);
                                    Object tipoPresencial = table.getValueAt(row, 15);
                                    Object alerta = table.getValueAt(row, 17);
                                    int id_cabecera_editar = Integer.parseInt(button.getHiddenValue());
                                    registroLlamadas.AddFiltersToFrameHeader(LLenarControles(registroLlamadas, (String) DNI, (String) apeNom, (String) codSap, (String) celular, (String) localidad, (String) confinado, (String) sede, (String) ep, (String) tipoAtencion, (String) tipoPresencial, (String) alerta), new Color(52, 202, 188), "");

                                    tituloDetalle = new String[10];
                                    tituloDetalle[0] = "";
                                    tituloDetalle[1] = "";
                                    tituloDetalle[2] = "CONSULTA";
                                    tituloDetalle[3] = "DETALLE";
                                    tituloDetalle[4] = "ACCION";
                                    tituloDetalle[5] = "DIAGNOSTICO";
                                    tituloDetalle[6] = "MEDICACIÓN";
                                    tituloDetalle[7] = "FRECUENCIA";
                                    tituloDetalle[8] = "DIAS";
                                    tituloDetalle[9] = "CANTIDAD TOTAL";
                                    listaTableDetalle = reporteModel.listaTableDetalleReporte(id_cabecera_editar);
                                    if (listaTableDetalle.size() > 0) {
                                        tableDetalle = new D_Table(tituloDetalle, listaTableDetalle);
                                    } else {
                                        tableDetalle = new D_Table(tituloDetalle);
                                    }
                                    AddEventoTableDetalle(registroLlamadas);
                                    tableDetalle.AddColumWidth(AnchoColunaDetalle());
                                    tableDetalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                                    tableDetalle.AddBackGroundColorTable(new Color(52, 202, 188));
                                    tableDetalle.AddBackGroundColorTableHeader(new Color(52, 202, 188));
                                    tableDetalle.AddColumnOrientation(AlingColunaDetalle());
                                    D_PanelTable panelTableDetalle = new D_PanelTable(tableDetalle, Color.BLUE);
                                    registroLlamadas.AddFrameToBody(panelTableDetalle);
                                    List<Object> listaBotones = new ArrayList<>();
                                    D_Button btnGrabar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("guardar_smal16px.png")));
                                    D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("iconscancel-16px.png")));
                                    listaBotones.add(btnGrabar);
                                    listaBotones.add(btnCancelar);

                                    registroLlamadas.AddFooterControls(listaBotones, 1);
                                    btnCancelar.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            registroLlamadas.CloseInternalFrame();
                                        }
                                    });
                                    btnGrabar.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (JOptionPane.showConfirmDialog(null, "Va a Grabar el Registro, Desea continuar?", "Grabar Reporte", JOptionPane.OK_OPTION) == 0) {
                                                TableReporteBean reporteBean = new TableReporteBean();
                                                try {
                                                    reporteBean.setNumero(reporteModel.ObtenerNumero());
                                                    Date date = new Date();
                                                    reporteBean.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(date));
                                                    reporteBean.setHora(new SimpleDateFormat("HH:MM").format(date));
                                                    reporteBean.setCelular(txtCelularDetalle.getText());
                                                    reporteBean.setLocalidadDomicilio(txtLocalidadDetalle.getText());
                                                    reporteBean.setConfinadoDonde(txtConfinadoDetalle.getText());
                                                    reporteBean.setSede(txtSedeDetalle.getText());
                                                    reporteBean.setEp(txtEpDetalle.getText());
                                                    reporteBean.setTipoAtencionSeguimiento(txtAtenSegDetalle.getText());
                                                    reporteBean.setTipoPrecencialVirtual(txtPrenVirDetalle.getText());
                                                    String alerta;
                                                    if (chksiDetalle.isSelected()) {
                                                        alerta = "SI";
                                                    } else {
                                                        alerta = "NO";
                                                    }
                                                    reporteBean.setEmvSINO(alerta);
                                                    reporteBean.setCodSap(txtCodSabDetalle.getText());
                                                    reporteBean.setMedico("DRA MILAGROS VALENTIN");
                                                    long idCabecera = reporteModel.GrabarReporte(reporteBean);
                                                    if (listaTableDetalle.size() > 0) {
                                                        for (int i = 0; i < listaTableDetalle.size(); i++) {
                                                            TableDetalleReporteBeans detalleReporte = listaTableDetalle.get(i);
                                                            reporteModel.GrabarDetalle(detalleReporte, (int) idCabecera);
                                                        }

                                                    }
                                                    JOptionPane.showMessageDialog(null, "Se Registro Correctamente.");
                                                    defaultTableModel = (DefaultTableModel) table.getModel();
                                                    defaultTableModel.addRow(reporteModel.listaReporte((int) idCabecera));
                                                    registroLlamadas.CloseInternalFrame();
                                                } catch (SQLException ex) {
                                                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        }
                                    });
                                    registroLlamadas.ShowFrame();

                                    internalFrameLayout.addShowInternalFrame(internalFrameLayout.getDesktopPanePrincipal(), registroLlamadas);
                                } catch (Exception ex) {
                                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            if (button.getName().equals("I")) {
                                // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
                                D_InternalFrameLayout envioCorreoInternal = new D_InternalFrameLayout(internalFrameLayout, 400, 250, false);
                                try {
                                    envioCorreoInternal.BackgroundColor(new Color(52, 202, 188));
                                    envioCorreoInternal.EnableMenu(false, null, new D_Label("Envio de Correo", Color.WHITE, true));
                                    D_PanelGroup d_PanelGroup = new D_PanelGroup();
                                    txtEnvioMensageCorreo = new D_TextArea(false, 4, 1, Color.WHITE);
                                    chkEnvioMensage = new D_CheckBox(1, 10, Color.RED);
                                    chkEnvioMensage.setText("Mensaje Programado");
                                    chkEnvioMensage.setPreferredSize(new Dimension(200, 30));
                                    D_ScrollPane d_ScrollPane = new D_ScrollPane(1, 10, txtEnvioMensageCorreo);
                                    d_ScrollPane.setPreferredSize(new Dimension(200, 90));
                                    txtAsunto = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                                    txtAsunto.setPreferredSize(new Dimension(200, 30));
                                    CorreoBeans correo = correoModel.ObtieneCorreo(Singletoon.getInstance().usuario.getId_usuario());
                                    if (correo != null) {
                                        txtAsunto.setText(correo.getAsunto());
                                    }
                                    D_Button btnEnviarCorreo = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class.getResource("sendmessag.png")), "Enviar Correo");
                                    btnEnviarCorreo.setPreferredSize(new Dimension(150, 30));
                                    d_PanelGroup.AddComponet(0, 0, 1, 1, 0, new D_Label("Asunto:", Color.white, true));
                                    d_PanelGroup.AddComponet(1, 0, 1, 1, 0, txtAsunto);
                                    d_PanelGroup.AddComponet(0, 1, 1, 1, 0, new D_Label("Mensaje:", Color.white, true));
                                    d_PanelGroup.AddComponet(1, 1, 1, 1, 0, d_ScrollPane);
                                    d_PanelGroup.AddComponet(1, 2, 1, 1, 0, chkEnvioMensage);
                                    d_PanelGroup.AddComponet(1, 3, 1, 1, 0, btnEnviarCorreo);

                                    envioCorreoInternal.AddFrameToBody(d_PanelGroup);
                                    envioCorreoInternal.ShowFrame();
                                    btnEnviarCorreo.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            EventQueue.invokeLater(() -> {
                                                EnvioCorreo(Integer.parseInt(button.getHiddenValue()));
                                            });

                                        }
                                    });
                                    internalFrameLayout.addShowInternalFrame(internalFrameLayout.getDesktopPanePrincipal(), envioCorreoInternal);
                                } catch (Exception ex) {
                                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                //
                            }
                        }
                    }
                }

                @Override

                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            }
            );
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        } catch (SQLException ex) {
            Logger.getLogger(ReporteInternal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void EnvioCorreo(int IdCabecera) {
        final Thread t;
        t = new Thread(() -> {

            CorreoBeans correos = null;
            try {
                correos = correoModel.ObtieneCorreo(Singletoon.getInstance().usuario.getId_usuario());
            } catch (SQLException ex) {
                Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (correos == null) {
                JOptionPane.showConfirmDialog(null, "Ocurrio un Problema al enviar el Correo Asegure que este conectado a la Base de Datos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String username = correos.getCorreo_envio();  //Para la dirección nomcuenta@gmail.com
            String password = correos.getContraseña_envio();
            String recepcionCorreo = correos.getCorreo_recepcion();
            String asunto = txtAsunto.getText();
            String mensaje;
            if (chkEnvioMensage.isSelected()) {
                mensaje = correos.getMensaje_programado();
            } else {
                mensaje = txtEnvioMensageCorreo.getText().trim();
            }

            Properties props = System.getProperties();
            Date fecha = new Date();
            String fechaCadena = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
            String titulomensaje = "ATENCIÓN " + fechaCadena + " : DR(A) " + Singletoon.getInstance().usuario.getNombre().toUpperCase() + "";
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.user", username);
            props.put("mail.smtp.password", password);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);

            EventQueue.invokeLater(() -> {
                try {
                    //https://myaccount.google.com/security
                    int id_cabecera = IdCabecera;
                    ReporteCabeceraBeans reporte = reporteModel.ObtieneReporte(id_cabecera);
                    if (reporte != null) {
                        List<DetalleReporteBeans> listaDetalle = reporteModel.listaDetalleReporte(id_cabecera);
                        if (listaDetalle.size() > 0) {
                            MimeMessage message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(username));
                            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recepcionCorreo));
                            message.setSubject(asunto);
                            String tr = "";
                            String table = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1' /><head><body>"
                                    + " <p>" + mensaje + " </p><br/><br/>"
                                    + "<table cellspacing='0' cellpadding='0' dir='ltr' border='1' style='table-layout:fixed;font-size:10pt;font-family:Calibri;width:0px;border-collapse:collapse;border:none'>"
                                    + "<colgroup>"
                                    + "<col width='225'>"
                                    + "<col width='121'>"
                                    + "<col width='117'>"
                                    + "<col width='74'>"
                                    + "<col width='203'>"
                                    + "<col width='215'>"
                                    + "<col width='103'>"
                                    + "<col width='120'>"
                                    + "<col width='299'>"
                                    + "<col width='139'>"
                                    + "<col width='138'>"
                                    + "<col width='138'>"
                                    + "<col width='157'>"
                                    + "<col width='116'>"
                                    + "</colgroup>"
                                    + "<tbody>"
                                    + "<tr style='height:21px'>"
                                    + "<td style='border:1px solid rgb(0,0,0);overflow:hidden;padding:0px 3px;vertical-align:bottom;font-size:11pt;font-weight:bold;color:rgb(0,0,0)' rowspan='1' colspan='8'>" + titulomensaje + "</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(0,0,0) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:bottom'></td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(0,0,0) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:bottom'></td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(0,0,0) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:bottom'></td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(0,0,0) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:bottom'></td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(0,0,0) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:bottom'></td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(0,0,0) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top'></td>"
                                    + "</tr>"
                                    + "<tr style='height:21px'>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>APELLIDOS Y NOMBRES</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>CARGO</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>CONFINADO<br> / DONDE</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>SEDE</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>CONSULTA</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>DETALLE</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>ACCIÒN</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>DIAGNOSTICO</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>MEDICAMENTO</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>FRECUENCIA</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>DIAS</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>CANTIDAD TOTAL</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>TIPO<br>(ATENCIÒN / SEGUIMIENTO DE CASOS)</td>"
                                    + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;background-color:rgb(68,114,196);font-family:Arial;font-size:12pt;font-weight:bold;color:rgb(255,255,255);text-align:center'>TIPO (PRESENCIAL / VIRTUAL)</td>"
                                    + "</tr>";
                            String consulta = "";
                            String detalletitulo = "";
                            String accion = "";
                            String diagnostico = "";
                            for (int i = 0; i < listaDetalle.size(); i++) {
                                DetalleReporteBeans detalle = listaDetalle.get(i);
                                if (detalle.getConsulta().length() > 0) {
                                    consulta += detalle.getConsulta() + "<br/>";
                                }
                                if (detalle.getDetalle().length() > 0) {
                                    detalletitulo += detalle.getDetalle() + "<br/>";
                                }
                                if (detalle.getAcción().length() > 0) {
                                    accion += detalle.getAcción() + "<br/>";
                                }
                                if (detalle.getDiacnostico().length() > 0) {
                                    diagnostico += detalle.getDiacnostico() + "<br/>";
                                }

                            }
                            for (int i = 0; i < listaDetalle.size(); i++) {
                                DetalleReporteBeans detalle = listaDetalle.get(i);
                                if (i == 0) {
                                    tr += "<tr style='height:21px'>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial' rowspan='" + listaDetalle.size() + "' colspan='1'><div style='max-height:84px'>" + reporte.getApe_nom() + "</div></td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial' rowspan='" + listaDetalle.size() + "' colspan='1'><div style='max-height:84px'>" + reporte.getCargo() + "</div></td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial' rowspan='" + listaDetalle.size() + "' colspan='1'><div style='max-height:84px'>" + reporte.getConfinadoDonde() + "</div></td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial' rowspan='" + listaDetalle.size() + "' colspan='1'><div style='max-height:84px'>" + reporte.getSede() + "</div></td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center' rowspan='" + listaDetalle.size() + "'>" + consulta + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center' rowspan='" + listaDetalle.size() + "'>" + detalletitulo + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center' rowspan='" + listaDetalle.size() + "'>" + accion + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center' rowspan='" + listaDetalle.size() + "'>" + diagnostico + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getMedicación() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getFrecuencia() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getDias() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getCanntidadTotal() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center' rowspan='" + listaDetalle.size() + "' colspan='1'><div style='max-height:84px'>" + reporte.getTipo_atencion() + "</div></td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top' rowspan='" + listaDetalle.size() + "' colspan='1'><div style='max-height:84px'>" + reporte.getTipo_presencial() + "</div></td>"
                                            + "</tr>";
                                } else {
                                    tr += "</tr>"
                                            + "<tr style='height:21px'>"
                                            //                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getConsulta() + "</td>"
                                            //                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getDetalle() + "</td>"
                                            //                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getAcción() + "</td>"
                                            //                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getDiacnostico() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getMedicación() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getFrecuencia() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getDias() + "</td>"
                                            + "<td style='border-width:1px;border-style:solid;border-color:rgb(204,204,204) rgb(0,0,0) rgb(0,0,0) rgb(204,204,204);overflow:hidden;padding:0px 3px;vertical-align:top;font-family:Arial;color:rgb(0,0,0);text-align:center'>" + detalle.getCanntidadTotal() + "</td>"
                                            + "</tr>";
                                }
                            }
                            table += tr;
                            table += "</tbody></table></body></html>";

                            message.setContent(table, "text/html");
                            try (Transport transport = session.getTransport("smtp")) {
                                transport.connect(username, password);
                                if (!transport.isConnected()) {
                                    JOptionPane.showConfirmDialog(null, "Hubo un problema al Enviar el Correo asegurece que este conectado al Internet.", "Envio Correo", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                transport.sendMessage(message, message.getAllRecipients());
                                JOptionPane.showMessageDialog(null, "Se envio el correo Correctamente");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hubo un Problema al Obtener del Detalle del Reporte.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se Encontro el Reporte.");
                    }
                } catch (MessagingException me) {
                    JOptionPane.showMessageDialog(null, "Error: " + me.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(ReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        });
        t.start();
    }

    public List<int[]> AnchoColunaDetalle() {
        List<int[]> ListaAnchoColumnas = new ArrayList<>();
        int[] ancho = new int[2];
        ancho[0] = 0;
        ancho[1] = 50;
        ListaAnchoColumnas.add(ancho);
        ancho = new int[2];
        ancho[0] = 1;
        ancho[1] = 50;
        ListaAnchoColumnas.add(ancho);
        ancho = new int[2];
        ancho[0] = 2;
        ancho[1] = 150;
        ListaAnchoColumnas.add(ancho);

        return ListaAnchoColumnas;
    }

    public List<int[]> AlingColunaDetalle() {
        List<int[]> ListaAnchoColumnas = new ArrayList<>();
        int[] ancho = new int[2];
        ancho[0] = 0;
        ancho[1] = Enumerator.CENTER;
        ListaAnchoColumnas.add(ancho);
        ancho = new int[2];
        ancho[0] = 1;
        ancho[1] = Enumerator.CENTER;
        ListaAnchoColumnas.add(ancho);

        return ListaAnchoColumnas;
    }

    public void AddEventoTableDetalle(D_InternalFrameLayout internal) {
        tableDetalle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int colum = tableDetalle.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / tableDetalle.getRowHeight();

                if (row < tableDetalle.getRowCount() && row >= 0 && colum < tableDetalle.getColumnCount() && colum >= 0) {
                    Object value = tableDetalle.getValueAt(row, colum);
                    if (value instanceof D_Button) {
                        ((D_Button) value).doClick();
                        D_Button button = (D_Button) value;
                        if (button.getName().equals("M")) {
                            Object consulta = tableDetalle.getValueAt(row, 2);
                            Object detalle = tableDetalle.getValueAt(row, 3);
                            Object accion = tableDetalle.getValueAt(row, 4);
                            Object diagnostico = tableDetalle.getValueAt(row, 5);
                            Object medicacion = tableDetalle.getValueAt(row, 6);
                            Object frecuencia = tableDetalle.getValueAt(row, 7);
                            Object dias = tableDetalle.getValueAt(row, 8);
                            Object cantidad = tableDetalle.getValueAt(row, 9);
                            try {
                                D_InternalFrameLayout frameLayout = new D_InternalFrameLayout(internal, 500, 400, false);
                                frameLayout.BackgroundColor(new Color(52, 202, 188));
                                frameLayout.EnableMenu(false, null, new D_Label("Añadir Detalle", Color.white, true));

                                frameLayout.AddFrameToBody("", ListaControlesDetalle(consulta.toString(), detalle.toString(), accion.toString(), diagnostico.toString(), medicacion.toString(), frecuencia.toString(), dias.toString(), cantidad.toString()), new Color(52, 202, 188));

                                D_Button btnGrabar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "GRABAR");
                                D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "CANCELAR");
                                ArrayList<Object> listaBotonDetalle = new ArrayList<>();
                                listaBotonDetalle.add(btnGrabar);
                                listaBotonDetalle.add(btnCancelar);
                                D_PanelFooter panelButon = new D_PanelFooter(listaBotonDetalle, 1);
                                btnCancelar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frameLayout.CloseInternalFrame();
                                    }
                                });
                                btnGrabar.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        TableDetalleReporteBeans detalleReporteBeans = new TableDetalleReporteBeans();
                                        detalleReporteBeans
                                                .setBtnModificar(new D_Button(1, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                                                        .getResource("registro.png")), "M", ""));
                                        detalleReporteBeans
                                                .setBtnEliminar(new D_Button(1, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                                                        .getResource("borrar.png")), "E", ""));
                                        detalleReporteBeans.setConsulta(txtConsultaDetalle.getText());
                                        detalleReporteBeans.setDetalle(txtDetalleDetalle.getText());
                                        detalleReporteBeans.setAcción(txtAccionDetalle.getText());
                                        detalleReporteBeans.setDiacnostico(txtDiagnosticoDetalle.getText());
                                        detalleReporteBeans.setMedicación(txtMedicacionDetalle.getText());
                                        detalleReporteBeans.setFrecuencia(txtFrecuenciaDetalle.getText());
                                        detalleReporteBeans.setDias(txtDiasDetalle.getText());
                                        detalleReporteBeans.setCanntidadTotal(txtCantidadTotalDetalle.getText());
                                        listaTableDetalle.add(detalleReporteBeans);

                                        Object[] data = new Object[10];
                                        data[0] = detalleReporteBeans.getBtnModificar();
                                        data[1] = detalleReporteBeans.getBtnEliminar();
                                        data[2] = detalleReporteBeans.getConsulta();
                                        data[3] = detalleReporteBeans.getDetalle();
                                        data[4] = detalleReporteBeans.getAcción();
                                        data[5] = detalleReporteBeans.getDiacnostico();
                                        data[6] = detalleReporteBeans.getMedicación();
                                        data[7] = detalleReporteBeans.getFrecuencia();
                                        data[8] = detalleReporteBeans.getDias();
                                        data[9] = detalleReporteBeans.getCanntidadTotal();
                                        defaultTableModel = (DefaultTableModel) tableDetalle.getModel();
                                        defaultTableModel.addRow(data);
                                        frameLayout.CloseInternalFrame();
                                    }
                                });
                                frameLayout.AddFooterControls(panelButon);
                                frameLayout.ShowFrame();
                                internal.addShowInternalFrame(internal.getDesktopPanePrincipal(), frameLayout);

                            } catch (Exception ex) {
                                Logger.getLogger(ReporteInternal.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (button.getName().equals("E")) {
                            defaultTableModel = (DefaultTableModel) tableDetalle.getModel();
                            defaultTableModel.removeRow(row);
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private List<List<D_ControlsObject>> ListaControlesDetalle(String consulta, String detalle, String accion, String diagnostico, String medicacion, String frecuencia, String dias, String cantidad) {
        List<D_ControlsObject> listaFiltroDetalle = new ArrayList<>();
        List<List<D_ControlsObject>> listaGrupoDetalle = new ArrayList<>();

        D_ControlsObject filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Consulta:", Color.black, true));

        txtConsultaDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtConsultaDetalle.setPreferredSize(new Dimension(250, 30));
        txtConsultaDetalle.setText(consulta);
        filters.setTypeControl(txtConsultaDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Detalle:", Color.black, true));
        txtDetalleDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtDetalleDetalle.setPreferredSize(new Dimension(250, 30));
        txtDetalleDetalle.setText(detalle);
        filters.setTypeControl(txtDetalleDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Acción:", Color.black, true));
        txtAccionDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtAccionDetalle.setPreferredSize(new Dimension(250, 30));
        txtAccionDetalle.setText(accion);
        filters.setTypeControl(txtAccionDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Diagnostico:", Color.black, true));
        txtDiagnosticoDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtDiagnosticoDetalle.setPreferredSize(new Dimension(250, 30));
        txtDiagnosticoDetalle.setText(diagnostico);
        filters.setTypeControl(txtDiagnosticoDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Medicación:", Color.black, true));
        txtMedicacionDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtMedicacionDetalle.setPreferredSize(new Dimension(170, 30));
        txtMedicacionDetalle.setText(medicacion);
        filters.setTypeControl(txtMedicacionDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Frecuencia:", Color.black, true));
        txtFrecuenciaDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtFrecuenciaDetalle.setPreferredSize(new Dimension(150, 30));
        txtFrecuenciaDetalle.setText(frecuencia);
        filters.setTypeControl(txtFrecuenciaDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Dias:", Color.black, true));
        txtDiasDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtDiasDetalle.setPreferredSize(new Dimension(100, 30));
        txtDiasDetalle.setText(dias);
        filters.setTypeControl(txtDiasDetalle);
        listaFiltroDetalle.add(filters);

        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Cantidad Total:", Color.black, true));
        txtCantidadTotalDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
        txtCantidadTotalDetalle.setPreferredSize(new Dimension(100, 30));
        txtCantidadTotalDetalle.setText(cantidad);
        filters.setTypeControl(txtCantidadTotalDetalle);
        listaFiltroDetalle.add(filters);

        listaGrupoDetalle.add(listaFiltroDetalle);
        return listaGrupoDetalle;
    }

    private List<List<D_ControlsObject>> LLenarControles(D_InternalFrameLayout internal, String dni, String nombre, String codsap, String celular, String localidad, String confinado,
            String sede, String ep, String tipoAtencion, String tipoPresencial, String alerta) {
        List<D_ControlsObject> listaFiltros = new ArrayList<>();
        List<List<D_ControlsObject>> listaFamiliaFiltros = new ArrayList<>();

        D_ControlsObject filters = new D_ControlsObject();

        filters.setControlName(new D_Label("DNI:", Color.black, true));
        txtDniDetalle.setBorderColor(Color.WHITE);
        txtDniDetalle.setPreferredSize(new Dimension(100, 30));
        txtDniDetalle.setText(dni);
        D_Button btnGrabarPerosnal = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                .getResource("addsi.png")));
        D_Button btnBuscarPerosnal = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                .getResource("buscar_small_16px.png")));
        btnGrabarPerosnal.setPreferredSize(new Dimension(50, 30));
        btnBuscarPerosnal.setPreferredSize(new Dimension(50, 30));

        D_Panel d_Panel = new D_Panel(1, 10, new Color(100, 202, 188), Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        d_Panel.setLayout(new GridBagLayout());
        gbc.gridy = 0;
        gbc.gridx = 0;

        d_Panel.add(txtDniDetalle, gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 2, 0, 0);
        d_Panel.add(btnGrabarPerosnal, gbc);
        gbc.gridy = 0;
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 2, 0, 0);
        d_Panel.add(btnBuscarPerosnal, gbc);

        txtDniDetalle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        PersonalExalmarBeans exalmarBeans = new PersonalExalmarBeans();
                        exalmarBeans = personaModel.ObtinePersonaPorDni(txtDniDetalle.getText());
                        if (exalmarBeans == null) {
                            JOptionPane.showMessageDialog(null, "El Personal no se encuentra Registrado Ó es Tripulante.");
                        } else {
                            txtDniDetalle.setText(exalmarBeans.getDni());
                            txtApelliNombresDetalle.setText(exalmarBeans.getApellidosNombres());
                            txtCodSabDetalle.setText(exalmarBeans.getCodsap());

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ReporteInternal.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        btnBuscarPerosnal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    D_InternalFrameLayout personalLayout = new D_InternalFrameLayout(internal, 700, 300, false);
                    personalLayout.AddFrameBorder(new Color(52, 202, 188));
                    personalLayout.setBackground(new Color(52, 202, 188));
                    personalLayout.EnableMenu(false, null, new D_Label("Buscar de Personal", new Color(52, 202, 188), Color.WHITE));
                    List<List<D_ControlsObject>> listaGrupoControlPersonal = new ArrayList<>();
                    List<D_ControlsObject> listaControlPersonal = new ArrayList<>();
                    D_ControlsObject controlsObject = new D_ControlsObject();

                    D_TextField txtFiltroBusqueda = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);

                    controlsObject.setTypeControl(txtFiltroBusqueda);
                    controlsObject.setControlName(new D_Label("DNI", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    listaGrupoControlPersonal.add(listaControlPersonal);
                    personalLayout.AddFiltersToFrameHeader(listaGrupoControlPersonal, new Color(52, 202, 188), "");

                    List<PersonalExalmarBeans> listaPeronaBusqueda = personaModel.listaPersonal();
                    String[] titulosPersonal = {"Dni", "Nombre", "Cod Sap"};
                    D_Table tablePersonal = new D_Table(titulosPersonal);
                    tablePersonal.AddBackGroundColorTable(new Color(52, 202, 188));
                    tablePersonal.AddBackGroundColorTableHeader(new Color(52, 202, 188));
                    defaultTableModel = (DefaultTableModel) tablePersonal.getModel();
                    for (int i = 0; i < listaPeronaBusqueda.size(); i++) {
                        PersonalExalmarBeans beanPerso = listaPeronaBusqueda.get(i);
                        Object[] objects = new Object[3];
                        objects[0] = beanPerso.getDni();
                        objects[1] = beanPerso.getApellidosNombres();
                        objects[2] = beanPerso.getCodsap();
                        defaultTableModel.addRow(objects);
                    }
                    tablePersonal.setModel(defaultTableModel);
                    D_PanelTable d_PanelTable = new D_PanelTable(tablePersonal, new Color(52, 202, 188));

                    personalLayout.AddFrameToBody(d_PanelTable);
                    personalLayout.ShowFrame();
                    txtFiltroBusqueda.addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {
                            EventQueue.invokeLater(() -> {
                                defaultTableModel = (DefaultTableModel) tablePersonal.getModel();
                                TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(defaultTableModel);
                                tablePersonal.setRowSorter(rowSorter);
                                rowSorter.setRowFilter(RowFilter.regexFilter(txtFiltroBusqueda.getText().toUpperCase()));
                            });
                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                        }
                    });
                    tablePersonal.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int colum = tablePersonal.getColumnModel().getColumnIndexAtX(e.getX());
                            int row = e.getY() / tablePersonal.getRowHeight();

                            if (row < tablePersonal.getRowCount() && row >= 0 && colum < tablePersonal.getColumnCount() && colum >= 0) {
                                Object dni = tablePersonal.getValueAt(row, 0);
                                Object nombre = tablePersonal.getValueAt(row, 1);
                                Object codsap = tablePersonal.getValueAt(row, 2);
                                txtDniDetalle.setText((String) dni);
                                txtApelliNombresDetalle.setText((String) nombre);
                                txtCodSabDetalle.setText((String) codsap);
                                personalLayout.CloseInternalFrame();
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e
                        ) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e
                        ) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e
                        ) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e
                        ) {

                        }
                    }
                    );
                    internal.addShowInternalFrame(internal.getDesktopPanePrincipal(), personalLayout);

                } catch (Exception ex) {
                    Logger.getLogger(ReporteInternal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        btnGrabarPerosnal.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    D_InternalFrameLayout personalLayout = new D_InternalFrameLayout(internal, 700, 300, false);
                    personalLayout.AddFrameBorder(new Color(52, 202, 188));
                    personalLayout.setBackground(new Color(52, 202, 188));
                    personalLayout.EnableMenu(false, null, new D_Label("Registro de Personal", new Color(52, 202, 188), Color.WHITE));
                    List<List<D_ControlsObject>> listaGrupoControlPersonal = new ArrayList<>();
                    List<D_ControlsObject> listaControlPersonal = new ArrayList<>();
                    D_ControlsObject controlsObject = new D_ControlsObject();

                    D_TextField txtDni = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtApeNom = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtCargo = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtSede = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtCodsap = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtFechaIngreso = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtFechaNacimiento = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_TextField txtCategoria = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    D_CheckBox tipoPersonal = new D_CheckBox(1, 10, Color.GREEN);
                    tipoPersonal.setText("Tripulate");

                    controlsObject.setTypeControl(txtDni);
                    controlsObject.setControlName(new D_Label("DNI", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtApeNom);
                    controlsObject.setControlName(new D_Label("Nombre Y Apellidos", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtCargo);
                    controlsObject.setControlName(new D_Label("Cargo", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtSede);
                    controlsObject.setControlName(new D_Label("Sede", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtCodsap);
                    controlsObject.setControlName(new D_Label("Cod Sap", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    listaGrupoControlPersonal.add(listaControlPersonal);

                    listaControlPersonal = new ArrayList<>();
                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtFechaIngreso);
                    controlsObject.setControlName(new D_Label("Fecha Ingreso", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtFechaNacimiento);
                    controlsObject.setControlName(new D_Label("Fecha Nacimiento", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(txtCategoria);
                    controlsObject.setControlName(new D_Label("Categoria / Emb", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    controlsObject = new D_ControlsObject();
                    controlsObject.setTypeControl(tipoPersonal);
                    controlsObject.setControlName(new D_Label("", Color.WHITE, true));
                    listaControlPersonal.add(controlsObject);

                    listaGrupoControlPersonal.add(listaControlPersonal);

                    personalLayout.AddFrameToBody("", listaGrupoControlPersonal, new Color(52, 202, 188));
                    ArrayList<Object> controlFooter = new ArrayList<>();
                    D_Button btnGrabarPersonales = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                            .getResource("guardar_smal16px.png")), "Grabar");
                    D_Button btnCancelarPersonal = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                            .getResource("iconscancel-16px.png")), "Cancelar");
                    controlFooter.add(btnCancelarPersonal);
                    controlFooter.add(btnGrabarPersonales);
                    D_PanelFooter panelButon = new D_PanelFooter(controlFooter, 1);
                    personalLayout.AddFooterControls(panelButon);
                    btnGrabarPersonales.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (JOptionPane.showConfirmDialog(null, "Va a Registrar a ".concat(txtApeNom.getText()).concat(". Desea Continuar?"), "Registro Personal", JOptionPane.CANCEL_OPTION) == 0) {
                                PersonalExalmarBeans beans = new PersonalExalmarBeans();
                                beans.setDni(txtDni.getText());
                                beans.setApellidosNombres(txtApeNom.getText());
                                beans.setCargo(txtCargo.getText());
                                beans.setCodsap(txtCodsap.getText());
                                beans.setFechaIngreso(txtFechaIngreso.getText());
                                beans.setFechaNacimiento(txtFechaNacimiento.getText());
                                beans.setCatgoria(txtCategoria.getText());
                                if (tipoPersonal.isSelected()) {
                                    beans.setTipoPersonal("T");
                                } else {
                                    beans.setTipoPersonal("E");
                                }
                                try {
                                    personaModel.GrabarPersona(beans);
                                    JOptionPane.showMessageDialog(null, "Se Registro Correctamente el Personal.");
                                    txtDni.setText("");
                                    txtApeNom.setText("");
                                    txtCargo.setText("");
                                    txtCodsap.setText("");
                                    txtFechaIngreso.setText("");
                                    txtFechaNacimiento.setText("");
                                    txtCategoria.setText("");
                                    tipoPersonal.setSelected(false);

                                } catch (SQLException ex) {
                                    Logger.getLogger(ReporteInternal.class
                                            .getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                    });
                    personalLayout.ShowFrame();

                    internal.addShowInternalFrame(internal.getDesktopPanePrincipal(), personalLayout);

                } catch (Exception ex) {
                    Logger.getLogger(ReporteInternal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        filters.setTypeControl(d_Panel);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Apellidos Y Nombres:", Color.black, true));

        txtApelliNombresDetalle.setPreferredSize(
                new Dimension(160, 30));
        txtApelliNombresDetalle.setText(nombre);

        filters.setTypeControl(txtApelliNombresDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Cod. Sap.:", Color.black, true));

        txtCodSabDetalle.setPreferredSize(
                new Dimension(80, 30));
        txtCodSabDetalle.setText(codsap);

        filters.setTypeControl(txtCodSabDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Celular:", Color.black, true));

        txtCelularDetalle.setPreferredSize(
                new Dimension(100, 30));
        txtCelularDetalle.setText(celular);

        filters.setTypeControl(txtCelularDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Loc. y Domicilio:", Color.black, true));

        txtLocalidadDetalle.setPreferredSize(
                new Dimension(160, 30));
        txtLocalidadDetalle.setText(localidad);

        filters.setTypeControl(txtLocalidadDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Confinado / Donde:", Color.black, true));

        txtConfinadoDetalle.setPreferredSize(
                new Dimension(160, 30));
        txtConfinadoDetalle.setText(confinado);

        filters.setTypeControl(txtConfinadoDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();
        D_Button btnAddDetalle = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Añadir Detalle");

        btnAddDetalle.setPreferredSize(
                new Dimension(130, 30));
        filters.setControlName(btnAddDetalle);

        listaFiltros.add(filters);

        btnAddDetalle.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    D_InternalFrameLayout frameLayout = new D_InternalFrameLayout(internal, 500, 400, false);
                    frameLayout.BackgroundColor(new Color(52, 202, 188));
                    frameLayout.EnableMenu(false, null, new D_Label("Añadir Detalle", Color.white, true));
                    List<D_ControlsObject> listaFiltroDetalle = new ArrayList<>();
                    List<List<D_ControlsObject>> listaGrupoDetalle = new ArrayList<>();

                    D_ControlsObject filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Consulta:", Color.black, true));

                    txtConsultaDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtConsultaDetalle.setPreferredSize(new Dimension(250, 30));
                    filters.setTypeControl(txtConsultaDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Detalle:", Color.black, true));
                    txtDetalleDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtDetalleDetalle.setPreferredSize(new Dimension(250, 30));
                    filters.setTypeControl(txtDetalleDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Acción:", Color.black, true));
                    txtAccionDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtAccionDetalle.setPreferredSize(new Dimension(250, 30));
                    filters.setTypeControl(txtAccionDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Diagnostico:", Color.black, true));
                    txtDiagnosticoDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtDiagnosticoDetalle.setPreferredSize(new Dimension(250, 30));
                    filters.setTypeControl(txtDiagnosticoDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Medicación:", Color.black, true));
                    txtMedicacionDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtMedicacionDetalle.setPreferredSize(new Dimension(180, 30));
                    filters.setTypeControl(txtMedicacionDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Frecuencia:", Color.black, true));
                    txtFrecuenciaDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtFrecuenciaDetalle.setPreferredSize(new Dimension(180, 30));
                    filters.setTypeControl(txtFrecuenciaDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Dias:", Color.black, true));
                    txtDiasDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtDiasDetalle.setPreferredSize(new Dimension(130, 30));
                    filters.setTypeControl(txtDiasDetalle);
                    listaFiltroDetalle.add(filters);

                    filters = new D_ControlsObject();
                    filters.setControlName(new D_Label("Cantidad Total:", Color.black, true));
                    txtCantidadTotalDetalle = new D_TextField(D_TextField.Type.CHARACTER, 1, 10);
                    txtCantidadTotalDetalle.setPreferredSize(new Dimension(130, 30));
                    filters.setTypeControl(txtCantidadTotalDetalle);
                    listaFiltroDetalle.add(filters);

                    listaGrupoDetalle.add(listaFiltroDetalle);
                    frameLayout.AddFrameToBody("", listaGrupoDetalle, new Color(52, 202, 188));

                    D_Button btnGrabar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "GRABAR");
                    D_Button btnCancelar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "CANCELAR");
                    ArrayList<Object> listaBotonDetalle = new ArrayList<>();
                    listaBotonDetalle.add(btnGrabar);
                    listaBotonDetalle.add(btnCancelar);
                    D_PanelFooter panelButon = new D_PanelFooter(listaBotonDetalle, 1);
                    btnCancelar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frameLayout.CloseInternalFrame();
                        }
                    });
                    btnGrabar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            TableDetalleReporteBeans detalleReporteBeans = new TableDetalleReporteBeans();
                            detalleReporteBeans
                                    .setBtnModificar(new D_Button(1, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                                            .getResource("registro.png")), "M", ""));
                            detalleReporteBeans
                                    .setBtnEliminar(new D_Button(1, 5, D_Button.TypeButton.ROUNDED_CORNER, new ImageIcon(RutaImagen.class
                                            .getResource("borrar.png")), "E", ""));
                            detalleReporteBeans.setConsulta(txtConsultaDetalle.getText());
                            detalleReporteBeans.setDetalle(txtDetalleDetalle.getText());
                            detalleReporteBeans.setAcción(txtAccionDetalle.getText());
                            detalleReporteBeans.setDiacnostico(txtDiagnosticoDetalle.getText());
                            detalleReporteBeans.setMedicación(txtMedicacionDetalle.getText());
                            detalleReporteBeans.setFrecuencia(txtFrecuenciaDetalle.getText());
                            detalleReporteBeans.setDias(txtDiasDetalle.getText());
                            detalleReporteBeans.setCanntidadTotal(txtCantidadTotalDetalle.getText());
                            listaTableDetalle.add(detalleReporteBeans);

                            Object[] data = new Object[10];
                            data[0] = detalleReporteBeans.getBtnModificar();
                            data[1] = detalleReporteBeans.getBtnEliminar();
                            data[2] = detalleReporteBeans.getConsulta();
                            data[3] = detalleReporteBeans.getDetalle();
                            data[4] = detalleReporteBeans.getAcción();
                            data[5] = detalleReporteBeans.getDiacnostico();
                            data[6] = detalleReporteBeans.getMedicación();
                            data[7] = detalleReporteBeans.getFrecuencia();
                            data[8] = detalleReporteBeans.getDias();
                            data[9] = detalleReporteBeans.getCanntidadTotal();
                            defaultTableModel = (DefaultTableModel) tableDetalle.getModel();
                            defaultTableModel.addRow(data);
                            frameLayout.CloseInternalFrame();
                        }
                    });
                    frameLayout.AddFooterControls(panelButon);
                    frameLayout.ShowFrame();
                    internal.addShowInternalFrame(internal.getDesktopPanePrincipal(), frameLayout);

                } catch (Exception ex) {
                    Logger.getLogger(ReporteInternal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        );
        listaFamiliaFiltros.add(listaFiltros);

        listaFiltros = new ArrayList<>();

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Sede:", Color.black, true));

        txtSedeDetalle.setPreferredSize(
                new Dimension(160, 30));
        txtSedeDetalle.setText(sede);

        filters.setTypeControl(txtSedeDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("EP:", Color.black, true));

        txtEpDetalle.setPreferredSize(
                new Dimension(160, 30));
        txtEpDetalle.setText(ep);

        filters.setTypeControl(txtEpDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Tipo Atención/Seguimiento de Casos:", Color.black, true));

        txtAtenSegDetalle.setPreferredSize(
                new Dimension(130, 30));
        txtAtenSegDetalle.setText(tipoAtencion);

        filters.setTypeControl(txtAtenSegDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        filters.setControlName(
                new D_Label("Tipo Presencial/Virtual:", Color.black, true));

        txtPrenVirDetalle.setPreferredSize(
                new Dimension(130, 30));
        txtPrenVirDetalle.setText(tipoPresencial);

        filters.setTypeControl(txtPrenVirDetalle);

        listaFiltros.add(filters);

        filters = new D_ControlsObject();

        chksiDetalle.setPreferredSize(
                new Dimension(120, 30));
        chksiDetalle.setText(
                "Alerta Enviada");
        if (alerta.toUpperCase()
                .equals("SI")) {
            chksiDetalle.setSelected(true);
        } else {
            chksiDetalle.setSelected(false);
        }

        filters.setControlName(
                new D_Label("", Color.black, false));

        filters.setTypeControl(chksiDetalle);

        listaFiltros.add(filters);

        listaFamiliaFiltros.add(listaFiltros);

        return listaFamiliaFiltros;

    }

    private ArrayList<int[]> CentrarColumnasDeTabla() {
        ArrayList<int[]> centarColumnas = new ArrayList<>();
        int[] valorColumna = new int[2];
        valorColumna[0] = 0;
        valorColumna[1] = Enumerator.CENTER;
        centarColumnas.add(valorColumna);

        valorColumna = new int[2];
        valorColumna[0] = 1;
        valorColumna[1] = Enumerator.CENTER;
        centarColumnas.add(valorColumna);

        valorColumna = new int[2];
        valorColumna[0] = 2;
        valorColumna[1] = Enumerator.CENTER;
        centarColumnas.add(valorColumna);
        return centarColumnas;
    }

    private ArrayList<int[]> ModificarAnchoColumna() {
        ArrayList<int[]> anchoColumnas = new ArrayList<>();

        int[] anchoColumna = new int[2];
        anchoColumna[0] = 0;
        anchoColumna[1] = 32;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 1;
        anchoColumna[1] = 32;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 2;
        anchoColumna[1] = 32;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 3;
        anchoColumna[1] = 70;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 4;
        anchoColumna[1] = 70;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 5;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 5;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 6;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 7;
        anchoColumna[1] = 100;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 8;
        anchoColumna[1] = 100;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 9;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 10;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 11;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 12;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 13;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 14;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 15;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 16;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 17;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);

        anchoColumna = new int[2];
        anchoColumna[0] = 18;
        anchoColumna[1] = 150;
        anchoColumnas.add(anchoColumna);
        return anchoColumnas;
    }

}
