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
import Api.swing.frameword.controles.D_Table;
import Api.swing.frameword.controles.D_TextField;
import Api.swing.frameword.frame.D_InternalFrameLayout;
import Api.swing.frameword.panel.D_PanelTable;
import appexalmar.bean.DetalleReporteBeans;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.bean.ReporteCabeceraBeans;
import appexalmar.bean.TableDetalleReporteBeans;
import appexalmar.bean.TableReporteBean;
import appexalmar.model.PersonaModel;
import appexalmar.model.ReporteModel;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class ImportarReporteInternal extends D_InternalFrameLayout {
    
    private D_InternalFrameLayout internalFrameLayout;
    private D_TableModelo defaultTableModel;
    private List<TableReporteBean> listaReporte = new ArrayList<>();
    private List<TableDetalleReporteBeans> listaDetalle = new ArrayList<>();
    private final JFileChooser fileChooser = new JFileChooser();
    private D_Table table = new D_Table();
    private String titleHeader[];
    private ReporteModel reporteModel = new ReporteModel();
    private PersonaModel personalModel = new PersonaModel();
    private static D_ProgressBar progreso;
    private static D_Button btnCargar;
    private static D_Button btnArchivo;
    private static D_TextField txthoja;
    private static D_TextField txtArchivo;
    
    public ImportarReporteInternal(Object _frame, int _width, int _height, boolean _enableDestopPane) throws HeadlessException {
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
        
        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        txthoja = new D_TextField(D_TextField.Type.NUMERIC, 1, 5);
        txthoja.requestFocus();
        txthoja.setPreferredSize(new Dimension(50, 30));
        txthoja.setText(String.valueOf(1));
        filters.setTypeControl(txthoja);
        filters.setControlName(new D_Label("N° Hoja:", Color.BLACK, true));
        listaFiltros.add(filters);
        listaFamiliaFiltros.add(listaFiltros);
        
        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        txtArchivo = new D_TextField(D_TextField.Type.NUMERIC, 1, 5);
        txtArchivo.setEnabled(false);
        txtArchivo.setPreferredSize(new Dimension(300, 30));
        filters.setTypeControl(txtArchivo);
        filters.setControlName(new D_Label("File:", Color.BLACK, true));
        listaFiltros.add(filters);
        
        listaFamiliaFiltros.add(listaFiltros);
        
        listaFiltros = new ArrayList<>();
        btnArchivo = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Archivo", Color.BLACK);
        filters = new D_ControlsObject();
        filters.setControlName(btnArchivo);
        listaFiltros.add(filters);
        
        listaFamiliaFiltros.add(listaFiltros);
        listaFiltros = new ArrayList<>();
        btnCargar = new D_Button(1, 10, D_Button.TypeButton.ROUNDED_CORNER, "Cargar", Color.BLACK);
        filters = new D_ControlsObject();
        filters.setControlName(btnCargar);
        listaFiltros.add(filters);
        
        listaFamiliaFiltros.add(listaFiltros);
        
        listaFiltros = new ArrayList<>();
        progreso = new D_ProgressBar(1, 5);
        progreso.setBorderColor(Color.BLACK);
        progreso.setPreferredSize(new Dimension(80, 30));
        filters = new D_ControlsObject();
        filters.setTypeControl(progreso);
        listaFiltros.add(filters);
        
        listaFamiliaFiltros.add(listaFiltros);
        
        this.AddFiltersToFrameHeader(listaFamiliaFiltros, new Color(52, 202, 188), "");

        //Add Table
        table.AddBackGroundColorTable(new Color(52, 202, 188));
        table.AddBackGroundColorTableHeader(new Color(52, 202, 188));
        table.EnableRowSorter(true);
        table.AddColumWidth(ModificarAnchoColumna());
        table.AddColumnOrientation(CentrarColumnasDeTabla());
        
        D_PanelTable panelTable = new D_PanelTable(table, Color.BLUE);
        
        this.AddFrameToBody(panelTable);
        
        this.ShowFrame();
        
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnArchivo.setEnabled(false);
                btnCargar.setEnabled(false);
                txthoja.setEnabled(false);
                Grabar();
            }
        }
        );
        
        btnArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                CargarExcel();
            }
            
        }
        );
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
        return centarColumnas;
    }
    
    private void CargarExcel() {
        final Thread t;
        t = new Thread(() -> {
            progreso.setStringPainted(true);
            int returnFile = fileChooser.showOpenDialog(btnArchivo);
            if (returnFile == JFileChooser.APPROVE_OPTION) {
                btnArchivo.setEnabled(false);
                btnCargar.setEnabled(false);
                txthoja.setEnabled(false);
                File file = fileChooser.getSelectedFile();
                txtArchivo.setText(file.getAbsoluteFile().getAbsolutePath());
                try {
                    if (txtArchivo.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Selecciona el archivo de carga");
                        TerminarCaga();
                        txtArchivo.requestFocus();
                    } else if (txthoja.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingresar N° de Hoja");
                        TerminarCaga();
                        txthoja.requestFocus();
                    } else {
                        listaReporte = new ArrayList<>();
                        FileInputStream stream = new FileInputStream(file.getAbsoluteFile());
                        XSSFWorkbook libro = new XSSFWorkbook(stream);
                        XSSFSheet hoja = libro.getSheetAt(Integer.parseInt(txthoja.getText()) - 1);
                        XSSFRow fila;
                        XSSFCell columna;
                        XSSFRow fila_temp;
                        XSSFCell columna_temp;
                        XSSFRow fila_temp2;
                        XSSFCell columna_temp2;
                        TableReporteBean reporteCabecera = new TableReporteBean();
                        TableDetalleReporteBeans detalleReporteBeans = new TableDetalleReporteBeans();
                        int flagExisteCabecera = 0;
//                            Iterator<Row> iterator = hoja.iterator();
                        int contador = 0;
                        int flag = 0;
                        int filas = 0;
                        for (int i = 0; i < hoja.getLastRowNum(); i++) {
                            fila = hoja.getRow(i);
                            columna = fila.getCell(0);
                            if (i == 0) {
                                titleHeader = new String[16];
                                titleHeader[0] = fila.getCell(0).getStringCellValue();
                                titleHeader[1] = fila.getCell(1).getStringCellValue();
                                titleHeader[2] = fila.getCell(2).getStringCellValue();
                                titleHeader[3] = fila.getCell(3).getStringCellValue();
                                titleHeader[4] = fila.getCell(4).getStringCellValue();
                                titleHeader[5] = fila.getCell(5).getStringCellValue();
                                titleHeader[6] = fila.getCell(6).getStringCellValue();
                                titleHeader[7] = fila.getCell(7).getStringCellValue();
                                titleHeader[8] = fila.getCell(8).getStringCellValue();
                                titleHeader[9] = fila.getCell(9).getStringCellValue();
                                titleHeader[10] = fila.getCell(10).getStringCellValue();
                                titleHeader[11] = fila.getCell(19).getStringCellValue();
                                titleHeader[12] = fila.getCell(20).getStringCellValue();
                                titleHeader[13] = fila.getCell(21).getStringCellValue();
                                titleHeader[14] = fila.getCell(23).getStringCellValue();
                                titleHeader[15] = fila.getCell(24).getStringCellValue();
                                
                            } else {
                                
                                if (!ObtenerValorExcel(columna).isEmpty()) {
                                    flag = 0;
                                    filas = 1;
                                    System.out.println(">> " + ObtenerValorExcel(columna));
                                    boolean termina = true;
                                    int count = i + 1;
                                    
                                    while (termina) {
                                        fila_temp = hoja.getRow(count);
                                        columna_temp = fila_temp.getCell(17);
                                        fila_temp2 = hoja.getRow(count);
                                        columna_temp2 = fila_temp2.getCell(0);
//                                        System.out.println("col>>" + columna_temp2.getNumericCellValue());
//                                        if (columna_temp == null) {
//                                            System.out.println("appexalmar.frame.ImportarReporteInternal.CargarExcel()");
//                                        } else {
                                        if (columna_temp2.getNumericCellValue() > 0 || ObtenerValorExcel(columna_temp).isEmpty()) {
                                            termina = false;
                                        } else {
                                            filas += 1;
                                        }
//                                        }
                                        count += 1;
                                    }
                                    contador += 1;
                                    
                                    reporteCabecera.setNumero(contador);
                                    columna = fila.getCell(1);
                                    Date fini = columna.getDateCellValue();
                                    if (fini != null) {
                                        reporteCabecera.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(fini));
                                    } else {
                                        reporteCabecera.setFecha("");
                                    }
                                    
                                    columna = fila.getCell(2);
                                    if (DateUtil.isCellDateFormatted(columna)) {
                                        reporteCabecera.setHora(new SimpleDateFormat("HH:mm").format(DateUtil.getJavaDate(columna.getNumericCellValue())));
                                    } else {
                                        reporteCabecera.setHora("");
                                    }
                                    columna = fila.getCell(3);
                                    reporteCabecera.setCelular(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(4);
                                    reporteCabecera.setLocalidadDomicilio(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(5);
                                    reporteCabecera.setEp(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(6);
                                    reporteCabecera.setDni(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(7);
                                    reporteCabecera.setApe_nom(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(8);
                                    reporteCabecera.setCargo(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(9);
                                    reporteCabecera.setConfinadoDonde(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(10);
                                    reporteCabecera.setSede(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(19);
                                    reporteCabecera.setTipoAtencionSeguimiento(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(20);
                                    reporteCabecera.setTipoPrecencialVirtual(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(21);
                                    reporteCabecera.setMedico(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(23);
                                    reporteCabecera.setEmvSINO(ObtenerValorExcel(columna));
                                    
                                    columna = fila.getCell(24);
                                    reporteCabecera.setCodSap(ObtenerValorExcel(columna));
                                }
                                
                                detalleReporteBeans = new TableDetalleReporteBeans();
                                columna = fila.getCell(11);
                                detalleReporteBeans.setConsulta(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(12);
                                detalleReporteBeans.setDetalle(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(13);
                                detalleReporteBeans.setAcción(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(14);
                                
                                detalleReporteBeans.setDiacnostico(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(15);
                                detalleReporteBeans.setMedicación(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(16);
                                detalleReporteBeans.setFrecuencia(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(17);
                                detalleReporteBeans.setDias(ObtenerValorExcel(columna));
                                
                                columna = fila.getCell(18);
                                detalleReporteBeans.setCanntidadTotal(ObtenerValorExcel(columna));
                                
                                int validaData = 0;
                                if (!detalleReporteBeans.getConsulta().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getDetalle().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getAcción().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getDiacnostico().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getMedicación().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getFrecuencia().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getDias().isEmpty()) {
                                    validaData = 1;
                                } else if (!detalleReporteBeans.getCanntidadTotal().isEmpty()) {
                                    validaData = 1;
                                }
                                if (validaData > 0) {
                                    listaDetalle.add(detalleReporteBeans);
                                }
                                flag += 1;
                                System.out.println("filas" + filas);
                                System.out.println("flag" + flag);
                                if (filas == flag) {
                                    reporteCabecera.setListaDetalle(listaDetalle);
                                    listaReporte.add(reporteCabecera);
                                    listaDetalle = new ArrayList<>();
                                    reporteCabecera = new TableReporteBean();
                                }

//                                if (i == (filas + i)) {
//                                    listaReporte.add(reporteCabecera);
//                                    listaDetalle = new ArrayList<>();
//                                }
//                                if (!ObtenerValorExcel(columna).isEmpty()) {
//                                    reporteCabecera.setListaDetalle(listaDetalle);
//
//                                }
                            }
                            progreso.setString(String.valueOf(((i + 1) * 100) / hoja.getLastRowNum()).concat("%"));
                            progreso.setValue(((i + 1) * 100) / hoja.getLastRowNum());
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                                JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                                Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
//                        for (int i = 0; i < hoja.getLastRowNum(); i++) {
//                            fila = hoja.getRow(i);
////                            if (fila != null) {
//                            if (i == 0) {
//                                titleHeader = new String[13];
//                                titleHeader[0] = fila.getCell(0).getStringCellValue();
//                                titleHeader[1] = fila.getCell(1).getStringCellValue();
//                                titleHeader[2] = fila.getCell(2).getStringCellValue();
//                                titleHeader[3] = fila.getCell(3).getStringCellValue();
//                                titleHeader[4] = fila.getCell(4).getStringCellValue();
//                                titleHeader[5] = fila.getCell(9).getStringCellValue();
//                                titleHeader[6] = fila.getCell(10).getStringCellValue();
//                                titleHeader[7] = fila.getCell(5).getStringCellValue();
//                                titleHeader[8] = fila.getCell(19).getStringCellValue();
//                                titleHeader[9] = fila.getCell(20).getStringCellValue();
//                                titleHeader[10] = fila.getCell(21).getStringCellValue();
//                                titleHeader[11] = fila.getCell(23).getStringCellValue();
//                                titleHeader[12] = fila.getCell(24).getStringCellValue();
//
//                            } else {
//
//                                columna = fila.getCell(0);
//
////                                    if (columna != null) {
//                                if (!ObtenerValorExcel(columna).isEmpty()) {
//                                    boolean contador_x = true;
//                                    int cont = 0;
//                                    int i_x = i;
//                                    while (contador_x) {
//                                        cont += 1;
//                                        i_x += 1;
//                                        if (hoja.getRow(i_x) != null) {
//                                            if (hoja.getRow(i_x).getCell(0) != null) {
//                                                if (!ObtenerValorExcel(hoja.getRow(i_x).getCell(0)).isEmpty()) {
//                                                    contador_x = false;
//                                                }
//                                            }
//                                        }
//                                        System.out.println("grupo de >> " + cont);
//                                    }
//                                    System.out.println("grupo de >> " + cont);
//                                    System.out.println("1 >> " + i);
//                                    System.out.println("nro >> " + columna.getNumericCellValue());
//                                    flagExisteCabecera = 1;
//                                    if (listaDetalle.size() > 0) {
//                                        personalExalmar.setListaDetalle(listaDetalle);
//                                        listaReporte.add(personalExalmar);
//                                    }
//                                    personalExalmar = new TableReporteBean();
//                                    detalleReporteBeans = new TableDetalleReporteBeans();
//                                    listaDetalle = new ArrayList<>();
//
//                                    personalExalmar.setNumero(contador += 1);
//
//                                    columna = fila.getCell(1);
//                                    Date fini = columna.getDateCellValue();
//                                    if (fini != null) {
//                                        personalExalmar.setFecha(new SimpleDateFormat("yyyy-MM-dd").format(fini));
//                                    } else {
//                                        personalExalmar.setFecha("");
//                                    }
//
//                                    columna = fila.getCell(2);
//                                    if (DateUtil.isCellDateFormatted(columna)) {
//                                        personalExalmar.setHora(new SimpleDateFormat("HH:mm").format(DateUtil.getJavaDate(columna.getNumericCellValue())));
//                                    } else {
//                                        personalExalmar.setHora("");
//                                    }
//                                    columna = fila.getCell(3);
//                                    personalExalmar.setCelular(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(4);
//                                    personalExalmar.setLocalidadDomicilio(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(9);
//                                    personalExalmar.setConfinadoDonde(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(10);
//                                    personalExalmar.setSede(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(5);
//                                    personalExalmar.setEp(ObtenerValorExcel(columna));
//
//                                    //Detalle
//                                    columna = fila.getCell(11);
//                                    detalleReporteBeans.setConsulta(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(12);
//                                    detalleReporteBeans.setDetalle(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(13);
//                                    detalleReporteBeans.setAcción(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(14);
//
//                                    detalleReporteBeans.setDiacnostico(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(15);
//                                    detalleReporteBeans.setMedicación(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(16);
//                                    detalleReporteBeans.setFrecuencia(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(17);
//                                    detalleReporteBeans.setDias(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(18);
//                                    detalleReporteBeans.setCanntidadTotal(ObtenerValorExcel(columna));
//                                    //fin detalle
//
//                                    columna = fila.getCell(19);
//                                    personalExalmar.setTipoAtencionSeguimiento(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(20);
//                                    personalExalmar.setTipoPrecencialVirtual(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(21);
//                                    personalExalmar.setMedico(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(23);
//                                    personalExalmar.setEmvSINO(ObtenerValorExcel(columna));
//
//                                    columna = fila.getCell(24);
//                                    personalExalmar.setCodSap(ObtenerValorExcel(columna));
//
//                                    listaDetalle.add(detalleReporteBeans);
//
//                                } else {
//                                    //Detalle
//                                    if (flagExisteCabecera == 1) {
//
//                                        detalleReporteBeans = new TableDetalleReporteBeans();
//                                        columna = fila.getCell(11);
//                                        detalleReporteBeans.setConsulta(ObtenerValorExcel(columna));
//
//                                        columna = fila.getCell(12);
//                                        detalleReporteBeans.setDetalle(ObtenerValorExcel(columna));
//
//                                        columna = fila.getCell(13);
//                                        if (columna != null) {
//                                            detalleReporteBeans.setAcción(ObtenerValorExcel(columna));
//                                        } else {
//                                            detalleReporteBeans.setAcción("");
//                                        }
//                                        columna = fila.getCell(14);
//                                        detalleReporteBeans.setDiacnostico(ObtenerValorExcel(columna));
//
//                                        columna = fila.getCell(15);
//                                        detalleReporteBeans.setMedicación(ObtenerValorExcel(columna));
//
//                                        columna = fila.getCell(16);
//                                        detalleReporteBeans.setFrecuencia(ObtenerValorExcel(columna));
//
//                                        columna = fila.getCell(17);
//                                        detalleReporteBeans.setDias(ObtenerValorExcel(columna));
//
//                                        columna = fila.getCell(18);
//                                        detalleReporteBeans.setCanntidadTotal(ObtenerValorExcel(columna));
//                                        //fin detalle
//                                        int validaData = 0;
//                                        if (!detalleReporteBeans.getConsulta().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getDetalle().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getAcción().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getDiacnostico().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getMedicación().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getFrecuencia().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getDias().isEmpty()) {
//                                            validaData = 1;
//                                        } else if (!detalleReporteBeans.getCanntidadTotal().isEmpty()) {
//                                            validaData = 1;
//                                        }
//                                        if (validaData > 0) {
//                                            listaDetalle.add(detalleReporteBeans);
//                                        }
//                                    }
//                                }
////                                    }
//                            }
//
////                            }
//                            progreso.setString(String.valueOf(((i + 1) * 100) / hoja.getLastRowNum()).concat("%"));
//                            progreso.setValue(((i + 1) * 100) / hoja.getLastRowNum());
//                            try {
//                                Thread.sleep(5);
//                            } catch (InterruptedException ex) {
//                                Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        }
                        defaultTableModel = new D_TableModelo(titleHeader, listaReporte);
                        table.setModel(defaultTableModel);
                        progreso.setString("");
                        btnArchivo.setEnabled(true);
                        btnCargar.setEnabled(true);
                        txthoja.setEnabled(true);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                    progreso.setString("");
                    btnArchivo.setEnabled(true);
                    btnCargar.setEnabled(true);
                    txthoja.setEnabled(true);
                    Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                    progreso.setString("");
                    btnArchivo.setEnabled(true);
                    btnCargar.setEnabled(true);
                    txthoja.setEnabled(true);
                    Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                    progreso.setString("");
                    btnArchivo.setEnabled(true);
                    btnCargar.setEnabled(true);
                    txthoja.setEnabled(true);
                    Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        t.start();
    }
    
    private String ObtenerValorExcel(XSSFCell columna) {
        String Valor = "";
        if (columna == null) {
            return Valor;
        }
        switch (columna.getCellTypeEnum()) {
            case BLANK:
                Valor = "";
                break;
            case ERROR:
                Valor = columna.getErrorCellString();
                break;
            case STRING:
                Valor = columna.getStringCellValue();
                break;
            case NUMERIC:
                Valor = String.valueOf((long)columna.getNumericCellValue());
                break;
            case _NONE:
                Valor = "";
                break;
            case BOOLEAN:
                Valor = String.valueOf(columna.getBooleanCellValue());
            case FORMULA:
                Valor = columna.getCellFormula();
                break;
            
            default:
                Valor = "";
                break;
            
        }
        
        return Valor;
    }
    
    private void Grabar() {
        final Thread t;
        t = new Thread(() -> {
            progreso.setStringPainted(true);
            if (listaReporte.size() > 0) {
                if (JOptionPane.showConfirmDialog(null, "Esta Seguro de Grabar", "Grabado", JOptionPane.DEFAULT_OPTION) == 0) {
                    try {
                        int contadorGra = 0;
                        int contadorExis = 0;
                        for (int i = 0; i < listaReporte.size(); i++) {
                            TableReporteBean beans = listaReporte.get(i);
                            System.out.println("numero >> " + beans.getNumero());
                            long idGenerado = reporteModel.GrabarReporte(beans);
                            System.out.println("idGenerado>>" + idGenerado);
                            PersonalExalmarBeans exalmarBeans = personalModel.ObtinePersona(beans.getCodSap().trim());
                            if (exalmarBeans == null) {
                                PersonalExalmarBeans exalmarBeans2 = personalModel.ObtinePersonaPorDni(beans.getDni().trim());
                                if (exalmarBeans2 == null) {
                                    PersonalExalmarBeans exalmarBeans3 = personalModel.ObtinePersonaPorNombre(beans.getApe_nom().trim());
                                    if (exalmarBeans3 == null) {
                                        exalmarBeans3 = new PersonalExalmarBeans();
                                        exalmarBeans3.setApellidosNombres(beans.getApe_nom());
                                        exalmarBeans3.setDni(beans.getDni());
                                        exalmarBeans3.setCargo(beans.getCargo());
                                        exalmarBeans3.setTipoPersonal("T");
                                        personalModel.GrabarPersona(exalmarBeans3);
                                    } else {
                                        if (exalmarBeans3.getTipoPersonal().equals("T")) {
                                            exalmarBeans3.setDni(beans.getDni());
                                            exalmarBeans3.setCargo(beans.getCargo());
                                            exalmarBeans3.setTipoPersonal("T");
                                            personalModel.ActualizaPersonaNombre(exalmarBeans3);
                                        }
                                    }
                                }
                            } else {
                                if (exalmarBeans.getTipoPersonal().equals("T")) {
                                    exalmarBeans.setDni(beans.getDni());
                                    exalmarBeans.setTipoPersonal("T");
                                    personalModel.ActualizaPersonaCodsap(exalmarBeans);
                                }                                
                            }
                            for (int j = 0; j < listaReporte.get(i).getListaDetalle().size(); j++) {
                                TableDetalleReporteBeans detalle = listaReporte.get(i).getListaDetalle().get(j);
                                reporteModel.GrabarDetalle(detalle, (int) idGenerado);
                                System.out.println("dias " + detalle.getDias());
                            }
//                            if((((i + 1) * 100) / listaReporte.size()) == 3){
//                             return;
//                            }
                            progreso.setString(String.valueOf(((i + 1) * 100) / listaReporte.size()).concat("%"));
                            progreso.setValue(((i + 1) * 100) / listaReporte.size());
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                        String mensage = "Se cargaron ".concat(String.valueOf(listaReporte.size())).concat(" Registros.");
                        JOptionPane.showMessageDialog(null, mensage);
                        TerminarCaga();
                        
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                        Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                        Logger.getLogger(ImportarReporteInternal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "No existen Datos para Grabar");
                TerminarCaga();
            }
        });
        t.start();
    }
    
    private void TerminarCaga() {
        try {
            titleHeader = new String[0];
            listaReporte = new ArrayList<>();
            defaultTableModel = new D_TableModelo(titleHeader);
        } catch (Exception ex) {
            Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setModel(defaultTableModel);
        txtArchivo.setText("");
        txthoja.setText("");
        btnArchivo.setEnabled(true);
        btnCargar.setEnabled(true);
        txthoja.setEnabled(true);
        progreso.setString("");
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
        anchoColumna[1] = 70;
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
        anchoColumna[1] = 100;
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
        
        return anchoColumnas;
    }
}
