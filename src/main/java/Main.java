import DB.WorkDb;
import Grafics.SimpleGUI;

public class Main {
    public static void main(String[] args) {
        WorkDb workDb = new WorkDb();
            SimpleGUI app = new SimpleGUI(workDb);
            app.setVisible(true);

    }
}

