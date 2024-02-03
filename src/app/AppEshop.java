package app;

import app.controller.EshopMainController;

public class AppEshop {
    private final EshopMainController startApp = new EshopMainController();

    public void run() {
        startApp.eshopProcessing();
    }

    public static void main(String[] args) {
        new AppEshop().run();
    }
}
