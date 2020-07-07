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
import Api.swing.frameword.controles.D_GroupButton;
import Api.swing.frameword.controles.D_Label;
import Api.swing.frameword.controles.D_ProgressBar;
import Api.swing.frameword.controles.D_RadioButton;
import Api.swing.frameword.controles.D_ScrollPane;
import Api.swing.frameword.controles.D_Table;
import Api.swing.frameword.controles.D_TextArea;
import Api.swing.frameword.controles.D_TextField;
import Api.swing.frameword.frame.D_InternalFrameLayout;
import Api.swing.frameword.panel.D_PanelTable;
import appexalmar.bean.PersonalExalmarBeans;
import appexalmar.bean.ReporteCabeceraBeans;
import appexalmar.model.PersonaModel;
import appexalmar.model.ReporteModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ADMIN
 */
public class ImportarPersonalInternal extends D_InternalFrameLayout {

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

    public ImportarPersonalInternal(Object _frame, int _width, int _height, boolean _enableDestopPane) throws HeadlessException {
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

        chkExalmar = new D_CheckBox();
        chkExalmar.setText("Exalmar");
        chkExalmar.setSelected(true);
        chkTripulante = new D_CheckBox();
        chkTripulante.setText("Tripulantes");
        List<Object> listaBotones = new ArrayList<>();
        listaBotones.add(chkExalmar);
        listaBotones.add(chkTripulante);
        D_GroupButton panelRadioButon = new D_GroupButton(true, listaBotones, D_GroupButton.ORIENTATION.HORIZOTAL, 1, 5, Color.WHITE, Color.BLACK);

        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        filters.setControlName(new D_Label("Tipo:", Color.BLACK, true));
        filters.setTypeControl(panelRadioButon);
        listaFiltros.add(filters);

        listaFamiliaFiltros.add(listaFiltros);

        listaFiltros = new ArrayList<>();
        filters = new D_ControlsObject();
        txthoja = new D_TextField(D_TextField.Type.NUMERIC, 1, 5);
        txthoja.requestFocus();
        txthoja.setPreferredSize(new Dimension(50, 30));
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
        btnArchivo = new D_Button(1,10,D_Button.TypeButton.ROUNDED_CORNER, "Archivo", Color.BLACK);
        filters = new D_ControlsObject();
        filters.setControlName(btnArchivo);
        listaFiltros.add(filters);

        listaFamiliaFiltros.add(listaFiltros);
        listaFiltros = new ArrayList<>();
        btnCargar = new D_Button(1,10,D_Button.TypeButton.ROUNDED_CORNER, "Cargar", Color.BLACK);
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
                chkExalmar.setEnabled(false);
                chkTripulante.setEnabled(false);
                Grabar();
            }
        }
        );
        chkExalmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TerminarCaga();
            }
        });
        chkTripulante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TerminarCaga();
            }

        });

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
                chkExalmar.setEnabled(false);
                chkTripulante.setEnabled(false);
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
                        XSSFSheet hoja = libro.getSheetAt(Integer.parseInt(txthoja.getText()));
                        XSSFRow fila;
                        XSSFCell columna;
//                            Iterator<Row> iterator = hoja.iterator();
                        for (int i = 0; i < hoja.getLastRowNum(); i++) {
                            fila = hoja.getRow(i);
                            if (fila != null) {
                                if (i == 0) {
                                    if (chkExalmar.isSelected()) {
                                        titleHeader = new String[9];
                                        titleHeader[0] = fila.getCell(0).getStringCellValue();
                                        titleHeader[1] = fila.getCell(1).getStringCellValue();
                                        titleHeader[2] = fila.getCell(2).getStringCellValue();
                                        titleHeader[3] = fila.getCell(3).getStringCellValue();
                                        titleHeader[4] = fila.getCell(4).getStringCellValue();
                                        titleHeader[5] = fila.getCell(5).getStringCellValue();
                                        titleHeader[6] = fila.getCell(6).getStringCellValue();
                                        titleHeader[7] = fila.getCell(7).getStringCellValue();
                                        titleHeader[8] = "CARGA";
                                    }
                                    if (chkTripulante.isSelected()) {
                                        titleHeader = new String[7];
                                        titleHeader[0] = fila.getCell(1).getStringCellValue();
                                        titleHeader[1] = fila.getCell(2).getStringCellValue();
                                        titleHeader[2] = fila.getCell(3).getStringCellValue();
                                        titleHeader[3] = fila.getCell(4).getStringCellValue();
                                        titleHeader[4] = fila.getCell(5).getStringCellValue();
                                        titleHeader[5] = fila.getCell(6).getStringCellValue();
                                        titleHeader[6] = "CARGA";
                                    }

                                } else {
                                    if (chkExalmar.isSelected()) {
                                        PersonalExalmarBeans personalExalmar = new PersonalExalmarBeans();

                                        columna = fila.getCell(0);
                                        if (columna != null) {
                                            if (!ObtenerValorExcel(columna).isEmpty()) {
                                                personalExalmar.setDni(ObtenerValorExcel(columna));

                                                columna = fila.getCell(1);
                                                personalExalmar.setApellidosNombres(ObtenerValorExcel(columna));

                                                columna = fila.getCell(2);
                                                personalExalmar.setCargo(ObtenerValorExcel(columna));

                                                columna = fila.getCell(3);
                                                personalExalmar.setSede(ObtenerValorExcel(columna));

                                                columna = fila.getCell(4);
                                                personalExalmar.setCodsap(ObtenerValorExcel(columna));

                                                columna = fila.getCell(5);
                                                Date fini = columna.getDateCellValue();
                                                if (fini != null) {
                                                    personalExalmar.setFechaIngreso(new SimpleDateFormat("yyyy-MM-dd").format(fini));
                                                }
                                                columna = fila.getCell(6);
                                                Date finac = columna.getDateCellValue();
                                                if (finac != null) {
                                                    personalExalmar.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").format(finac));
                                                }

                                                columna = fila.getCell(7);
                                                personalExalmar.setCatgoria(ObtenerValorExcel(columna));
                                                personalExalmar.setTipoPersonal("E");
                                                listaReporte.add(personalExalmar);
                                            }
                                        }
                                    }
                                    if (chkTripulante.isSelected()) {
                                        PersonalExalmarBeans personalExalmar = new PersonalExalmarBeans();

                                        columna = fila.getCell(1);
                                        if (columna != null) {
                                            if (!ObtenerValorExcel(columna).isEmpty()) {

                                                personalExalmar.setApellidosNombres(ObtenerValorExcel(columna));

                                                columna = fila.getCell(2);
                                                personalExalmar.setCargo(ObtenerValorExcel(columna));

                                                columna = fila.getCell(3);
                                                personalExalmar.setCodsap(String.valueOf(Double.valueOf(ObtenerValorExcel(columna)).intValue()));

                                                columna = fila.getCell(4);
                                                Date fini = columna.getDateCellValue();
                                                if (fini != null) {
                                                    personalExalmar.setFechaIngreso(new SimpleDateFormat("yyyy-MM-dd").format(fini));
                                                }
                                                columna = fila.getCell(5);
                                                Date finac = columna.getDateCellValue();
                                                if (finac != null) {
                                                    personalExalmar.setFechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").format(finac));
                                                }

                                                columna = fila.getCell(6);
                                                personalExalmar.setCatgoria(ObtenerValorExcel(columna));
                                                personalExalmar.setTipoPersonal("T");
                                                listaReporte.add(personalExalmar);
                                            }
                                        }
                                    }
                                }

                            }
                            progreso.setString(String.valueOf(((i + 1) * 100) / hoja.getLastRowNum()).concat("%"));
                            progreso.setValue(((i + 1) * 100) / hoja.getLastRowNum());
                            try {
                                Thread.sleep(5);
                            } catch (InterruptedException ex) {
                                JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                            }

                        }
                        defaultTableModel = new D_TableModelo(titleHeader, listaReporte);
                        table.setModel(defaultTableModel);
                        progreso.setString("");
                        btnArchivo.setEnabled(true);
                        btnCargar.setEnabled(true);
                        txthoja.setEnabled(true);
                        chkExalmar.setEnabled(true);
                        chkTripulante.setEnabled(true);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                    Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                    TerminarCaga();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                    Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                    TerminarCaga();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                    Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                    TerminarCaga();
                }
            }

        });
        t.start();
    }
    
    private String ObtenerValorExcel(XSSFCell columna){
      String Valor="";
       switch(columna.getCellTypeEnum()){
         case BLANK:
             Valor="";
             break;
         case ERROR:
             Valor=columna.getErrorCellString();
            break;
         case STRING:
             Valor=columna.getStringCellValue();
             break;
         case NUMERIC:
             Valor=String.valueOf(columna.getNumericCellValue());
             break;
         case _NONE:
             Valor="";
             break;
         case BOOLEAN:
             Valor=String.valueOf(columna.getBooleanCellValue());
         case FORMULA:
             Valor=columna.getCellFormula();
          break;
          default:
              Valor="";
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
                            PersonalExalmarBeans beans = personaModel.ObtinePersona(listaReporte.get(i).getCodsap().trim());
                            if (beans == null) {
                                personaModel.GrabarPersona(listaReporte.get(i));
                                contadorGra += 1;
                            } else {
                                contadorExis = contadorExis + 1;
                            }

                            progreso.setString(String.valueOf(((i + 1) * 100) / listaReporte.size()).concat("%"));
                            progreso.setValue(((i + 1) * 100) / listaReporte.size());
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        String mensage = "Se cargaron ".concat(String.valueOf(contadorGra)).concat(" Registros y ").concat(String.valueOf(contadorExis)).concat(" ya existen en la base de Datos");
                        JOptionPane.showMessageDialog(null, mensage);
                        TerminarCaga();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
                        Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
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
            JOptionPane.showMessageDialog(null, "Error:"+ ex.getMessage());
            Logger.getLogger(ImportarPersonalInternal.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setModel(defaultTableModel);
        txtArchivo.setText("");
        txthoja.setText("");
        btnArchivo.setEnabled(true);
        btnCargar.setEnabled(true);
        txthoja.setEnabled(true);
        chkExalmar.setEnabled(true);
        chkTripulante.setEnabled(true);
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
