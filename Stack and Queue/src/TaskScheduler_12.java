import java.util.Comparator;
import java.util.PriorityQueue;

class Task {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}

class TaskScheduler {
    private PriorityQueue<Task> queue;

    public TaskScheduler() {
        queue = new PriorityQueue<>(Comparator.comparingInt(task -> -task.priority));
    }

    public void addTask(String name, int priority) {
        queue.offer(new Task(name, priority));
    }

    public Task getNextTask() {
        return queue.poll();
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();

        scheduler.addTask("Task1", 3);
        scheduler.addTask("Task2", 1);
        scheduler.addTask("Task3", 5);

        System.out.println("Next task: " + scheduler.getNextTask());
        System.out.println("Next task: " + scheduler.getNextTask());
        System.out.println("Next task: " + scheduler.getNextTask());
    }
}
