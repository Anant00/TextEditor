import javax.swing.table.DefaultTableModel;

class DeleteCommand implements Command {
    private final String id;
    private final String content;
    private final int rowIndex;
    private final DefaultTableModel tableModel;

    public DeleteCommand(DefaultTableModel tableModel, int rowIndex) {
        this.tableModel = tableModel;
        this.rowIndex = rowIndex;
        this.id = (String) tableModel.getValueAt(rowIndex, 0);
        this.content = (String) tableModel.getValueAt(rowIndex, 1);
    }

    public void execute() {
        tableModel.removeRow(rowIndex);
    }

    public void undo() {
        tableModel.insertRow(rowIndex, new Object[]{id, content});
    }
}