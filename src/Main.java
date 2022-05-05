import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static JFrame jFrame = getFrame();
    static String Goal = "";
    static List<Task> curTasks = new ArrayList<>();



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Login login = new Login();
        TaskPanel taskPanel = new TaskPanel();
        ReadData();
        //verstka--------------------
        JButton addButton = new JButton("add");
        JButton doneButton = new JButton("done");
        JTextField taskName = new JTextField();
        JTextArea taskInfo = new JTextArea();
        JScrollPane taskInfoSP = new JScrollPane(taskInfo);
        DefaultListModel defaultCurListModel = new DefaultListModel();
        JList curList = new JList(defaultCurListModel);
        JLabel taskNameLabel = new JLabel("Name");
        JLabel taskInfoLabel = new JLabel("Task");
        JLabel curListLabel = new JLabel("Current Tasks");

        for (int i = 0; i < curTasks.size(); i++)
            defaultCurListModel.addElement(curTasks.get(i).taskName);

        jFrame.add(addButton);
        jFrame.add(doneButton);
        jFrame.add(taskName);
        jFrame.add(taskInfoSP);
        jFrame.add(curList);
        jFrame.add(taskNameLabel);
        jFrame.add(taskInfoLabel);
        jFrame.add(curListLabel);


        taskInfo.setLineWrap(true);
        taskName.setBounds(80, 20, 200, 25);
        taskInfoSP.setBounds(80, 60, 300, 200);
        addButton.setBounds(310, 20, 68, 25);
        taskNameLabel.setBounds(30, 15, 50, 25);
        taskInfoLabel.setBounds(30, 57, 50, 25);
        curList.setBounds(425, 60, 200, 200);
        curListLabel.setBounds(425, 35, 100, 25);
        doneButton.setBounds(550, 30, 75, 25);
        //logics----------
        //addButton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!taskInfo.getText().equals("") && !taskName.getText().equals("")) {
                    Task task1 = new Task(taskName.getText(), taskInfo.getText());
                    curTasks.add(task1);
                    defaultCurListModel.addElement(taskName.getText());//при запуске из файла всё переносить в лист здесь
                    System.out.println(defaultCurListModel.get(0));
                    try {
                        WriteData();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        //curList
        curList.setLayoutOrientation(curList.VERTICAL_WRAP);
        curList.setSelectionMode(0);
        curList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    taskPanel.getIndexedTask(curTasks.get(curList.getSelectedIndex()));
                }
            }
        });
        //doneButton
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curTasks.remove(curList.getSelectedIndex());
                defaultCurListModel.remove(curList.getSelectedIndex());
                try {
                    WriteData();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    //getFrame creating Frame
    static JFrame getFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit Toolkit = java.awt.Toolkit.getDefaultToolkit();
        Dimension dimension = Toolkit.getScreenSize();
        jFrame.setBounds((dimension.width-1080)/2,(dimension.height-720)/2, 700, 370);
        jFrame.setTitle("FabricList");
        jFrame.setResizable(false);
        jFrame.setLayout(null);

        return jFrame;

    }

    static public void WriteData() throws IOException {
        FileOutputStream fos = new FileOutputStream("buffer.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        int curTasksSize = curTasks.size();
        oos.writeInt(curTasksSize);
        for (Task task1 : curTasks) oos.writeObject(task1);
        fos.close();
    }

    static public void ReadData() throws ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("buffer.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        int curTasksSize = ois.readInt();
        for (int i = 0; i < curTasksSize; i++) {
            Task task1 = (Task) ois.readObject();
            curTasks.add(task1);

        }


    }
}
