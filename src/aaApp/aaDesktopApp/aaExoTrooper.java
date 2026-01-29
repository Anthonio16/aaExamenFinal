package aaApp.aaDesktopApp;

import aaBusinessLogic.FactoryBL;
import aaBusinessLogic.Helpers.aaExobotBL;
import aaBusinessLogic.Helpers.aaAlumnoBL;
import aaBusinessLogic.aaEntities.aaExobot;
import aaBusinessLogic.aaEntities.aaTipoExobot;
import aaInfrastructure.CMD;
import aaInfrastructure.aaAppException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class aaExoTrooper extends JFrame {

    private final JTextField aaTxtCedula = new JTextField();
    private final JTextField aaTxtNombre = new JTextField();
    private final JComboBox<aaTipoExobot> aaCmbTipo = new JComboBox<>(aaTipoExobot.values());

    private final DefaultTableModel aaModel = new DefaultTableModel(
            new Object[]{"IdExobot", "TipoExobot", "Entreno", "No. Accion"}, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
    };

    private final JTable aaTable = new JTable(aaModel);
    private final List<aaExobot> aaAll = new ArrayList<>();

    private final aaExobotBL aaExobotBL;

    public aaExoTrooper() throws aaAppException {
        super("ExoTrooper");
        this.aaExobotBL = FactoryBL.aaExobotBL();
        aaShowComponents();
        aaAddTipoExobot("");
        aaLoadAlumno();
    }

public List<aaTipoExobot> aaAddTipoExobot(String fileNamePath) {
    // En el prototipo se agrega TipoExobot; aquí cargamos los disponibles.
    // fileNamePath se deja por tu diseño (puede usarse luego para cargar desde archivo).
    List<aaTipoExobot> tipos = new ArrayList<>();
    for (aaTipoExobot t : aaTipoExobot.values()) tipos.add(t);

    aaCmbTipo.setModel(new DefaultComboBoxModel<>(tipos.toArray(new aaTipoExobot[0])));
    return tipos;
}

public void aaBtnBuscarClick() {
    aaBuscar();
}

    private void aaShowComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // Top: Alumno(s)
        JPanel pAlumno = new JPanel(new BorderLayout());
        pAlumno.setBorder(BorderFactory.createTitledBorder("Alumno(s)"));
        JPanel grid = new JPanel(new GridLayout(2, 2, 8, 8));
        grid.add(new JLabel("Cédula"));
        grid.add(aaTxtCedula);
        grid.add(new JLabel("Nombres completos"));
        grid.add(aaTxtNombre);
        aaTxtCedula.setEditable(false);
        aaTxtNombre.setEditable(false);
        pAlumno.add(grid, BorderLayout.CENTER);

        // Controls
        JPanel pControls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pControls.add(new JLabel("TipoExobot"));
        pControls.add(aaCmbTipo);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");

        pControls.add(btnAgregar);
        pControls.add(btnBuscar);

        // Table
        JScrollPane sp = new JScrollPane(aaTable);

        // Bottom buttons
        JPanel pBottom = new JPanel(new GridLayout(1,2,10,10));
        JButton btnEntrenar = new JButton("Entrenar AcciónArma");
        JButton btnAccion = new JButton("Acción_TipoArma");
        pBottom.add(btnEntrenar);
        pBottom.add(btnAccion);

        // Layout center
        JPanel center = new JPanel(new BorderLayout(10,10));
        center.add(pControls, BorderLayout.NORTH);
        center.add(sp, BorderLayout.CENTER);
        center.add(pBottom, BorderLayout.SOUTH);

        root.add(pAlumno, BorderLayout.NORTH);
        root.add(center, BorderLayout.CENTER);
        setContentPane(root);

        // Actions
        btnAgregar.addActionListener(e -> aaAgregar());
        btnBuscar.addActionListener(e -> aaBtnBuscarClick());
        btnEntrenar.addActionListener(e -> aaEntrenar());
        btnAccion.addActionListener(e -> aaAccion());
    }

    private void aaLoadAlumno() throws aaAppException {
        aaAlumnoBL ubl = FactoryBL.aaAlumnoBL();
        aaTxtCedula.setText(ubl.aaGetAlumno().getAaCedula());
        aaTxtNombre.setText(ubl.aaGetAlumno().getAaNombre());
    }

    private void aaAgregar() {
        aaTipoExobot tipo = (aaTipoExobot) aaCmbTipo.getSelectedItem();
        aaExobot exo = aaExobotBL.aaCrearExobot(tipo);
        aaAll.add(exo);
        aaModel.addRow(new Object[]{exo.getAaIdExobot(), exo.getAaTipoExobot().name(), "NO", exo.getAaNoAccion()});
    }

    private void aaBuscar() {
        aaTipoExobot tipo = (aaTipoExobot) aaCmbTipo.getSelectedItem();
        // filtra (extra point)
        aaModel.setRowCount(0);
        for (aaExobot exo : aaAll) {
            if (exo.getAaTipoExobot() == tipo) {
                aaModel.addRow(new Object[]{exo.getAaIdExobot(), exo.getAaTipoExobot().name(), exo.isAaEntreno() ? "SI" : "NO", exo.getAaNoAccion()});
            }
        }
    }

    private aaExobot aaGetSelected() throws aaAppException {
        int row = aaTable.getSelectedRow();
        if (row < 0) throw new aaAppException("Seleccione una fila en la grilla.");
        int id = Integer.parseInt(aaTable.getValueAt(row, 0).toString());
        for (aaExobot exo : aaAll) if (exo.getAaIdExobot() == id) return exo;
        throw new aaAppException("Exobot no encontrado.");
    }

    private void aaRefreshRow(aaExobot exo) {
        // actualiza toda la tabla con el filtro actual si hay
        aaTipoExobot tipo = (aaTipoExobot) aaCmbTipo.getSelectedItem();
        boolean filtrado = true;
        aaModel.setRowCount(0);
        for (aaExobot x : aaAll) {
            if (!filtrado || x.getAaTipoExobot() == tipo) {
                aaModel.addRow(new Object[]{x.getAaIdExobot(), x.getAaTipoExobot().name(), x.isAaEntreno() ? "SI" : "NO", x.getAaNoAccion()});
            }
        }
    }

    private void aaEntrenar() {
        try {
            aaExobot exo = aaGetSelected();
            aaExobotBL.aaEntrenar(exo);
            aaRefreshRow(exo);
        } catch (Exception ex) {
            CMD.error(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aaAccion() {
        try {
            aaExobot exo = aaGetSelected();
            String recurso = aaExobotBL.aaAccionArma(exo);
            aaRefreshRow(exo);
            JOptionPane.showMessageDialog(this, "Recurso: " + recurso, "Acción", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            CMD.error(ex.getMessage());
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
