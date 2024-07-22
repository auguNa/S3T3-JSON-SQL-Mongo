package service;

import model.Florist;

public class FloristService {
    private static FloristService instance;
    private Florist florist;

    private FloristService() {}

    public static FloristService getInstance() {
        if (instance == null) {
            instance = new FloristService();
        }
        return instance;
    }

    public void createFlorist(String name) {
        this.florist = new Florist(name);
    }

    public Florist getFlorist() {
        return florist;
    }
}

