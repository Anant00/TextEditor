import javax.swing.table.DefaultTableModel;



class AddCommand implements Command {
    private final String id;
    private final String content;
    private final DefaultTableModel tableModel;

    public AddCommand(DefaultTableModel tableModel, String id, String content) {
        this.tableModel = tableModel;
        this.id = id;
        this.content = content;
    }

    public void execute() {
        tableModel.addRow(new Object[]{id, content});
    }

    public void undo() {
        int lastRowIndex = tableModel.getRowCount() - 1;
        tableModel.removeRow(lastRowIndex);
    }
}