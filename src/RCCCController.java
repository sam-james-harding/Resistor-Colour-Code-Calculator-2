import javax.swing.*;

public class RCCCController {
    private final RCCCView view;

    public static void main(String[] args) {
        RCCCController controller = new RCCCController();
        controller.run();
    }

    public RCCCController() {
        view = new RCCCView();
    }

    public void run() {
        view.run();
    }
}
