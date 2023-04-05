import java.util.Stack;

class TextEditorController {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public TextEditorController() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void execute(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
}
