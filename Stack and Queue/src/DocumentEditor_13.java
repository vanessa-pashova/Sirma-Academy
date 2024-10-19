import java.util.Scanner;
import java.util.Stack;

public class DocumentEditor_13 {
    private Stack<String> undoStack = new Stack<>();
    private Stack<String> redoStack = new Stack<>();
    private StringBuilder currentText = new StringBuilder();

    public void addText(String text) {
        undoStack.push(currentText.toString());
        currentText.append(text);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString());
            currentText = new StringBuilder(undoStack.pop());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString());
            currentText = new StringBuilder(redoStack.pop());
        }
    }

    public String getText() {
        return currentText.toString();
    }

    public static void main(String[] args) {
        DocumentEditor_13 editor = new DocumentEditor_13();
        Scanner scanner = new Scanner(System.in);
        String command;

        while (!(command = scanner.nextLine()).equals("exit")) {
            if (command.startsWith("add")) {
                String text = command.substring(4);
                editor.addText(text);
            }

            else if (command.equals("undo"))
                editor.undo();

            else if (command.equals("redo"))
                editor.redo();

            System.out.println(editor.getText());
        }
    }
}
