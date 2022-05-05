import java.io.Serializable;

public class Task implements Serializable {
    String taskName;
    String taskInfo;

    public Task(String taskName, String taskInfo) {
        this.taskName = taskName;
        this.taskInfo = taskInfo;
    }
    public void getData(){
        System.out.println(taskName + " " + taskInfo);
    }
}
