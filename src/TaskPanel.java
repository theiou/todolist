import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JFrame {
    JTextField taskName = new JTextField();
    JTextArea taskInfo = new JTextArea();
    public TaskPanel() {
        setLayout(null);
        setVisible(false);
        setBounds(600, 200, 400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        add(taskName);
        add(taskInfo);

        taskName.setBounds(85, 20, 200, 25);
        taskInfo.setBounds(35, 50, 300, 175);
        taskInfo.setEditable(false);
        taskName.setEditable(false);

    }
    public void getIndexedTask(Task task){
        setVisible(true);
        taskName.setText(task.taskName);
        taskInfo.setText(task.taskInfo);
    }
}
