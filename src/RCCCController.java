public class RCCCController {
    private final RCCCView view;

    /**
     * Start running the app.
     * @param args this app ignores any command-line arguments
     */
    public static void main(String[] args) {
        RCCCController controller = new RCCCController();
        controller.run();
    }

    /**
     * Initialise the controller class.
     */
    public RCCCController() {
        view = new RCCCView();
    }

    /**
     * Start the app, including starting the GUI.
     */
    public void run() {
        view.run();
    }
}
