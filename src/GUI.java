import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUI {

    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private final TextEditorController controller;

    public GUI(TextEditorController controller) {

        this.controller = controller;

        initialize();
    }

    public void spin() {
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Content"});
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String[] inputData = showDialog();
            if (inputData != null) {
                controller.execute(new AddCommand(tableModel, inputData[0], inputData[1]));
            }
        });
        panel.add(addButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                controller.execute(new DeleteCommand(tableModel, selectedRow));
            }
        });
        panel.add(deleteButton);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> controller.undo());
        panel.add(undoButton);

        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> controller.redo());
        panel.add(redoButton);

    }

    private String[] showDialog() {
        JTextField idField = new JTextField(10);
        JTextField contentField = new JTextField(10);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(2, 2));
        dialogPanel.add(new JLabel("ID:"));
        dialogPanel.add(idField);
        dialogPanel.add(new JLabel("Content:"));
        dialogPanel.add(contentField);

        int result = JOptionPane.showConfirmDialog(null, dialogPanel,
                "Add Item", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return new String[]{idField.getText(), contentField.getText()};
        }

        return null;
    }
}
