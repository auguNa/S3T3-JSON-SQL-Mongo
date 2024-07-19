package menu;

import PersistenceInterface.FloristRepository;
import florist.Florist;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Scanner;

public class MainMenu {


    @Autowired
    private FloristRepository floristRepository;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Florist");
            System.out.println("2. Stock Management");
            System.out.println("3. Sales Management");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("Enter florist name:");
                String name = scanner.nextLine();
                if (floristRepository.existsByName(name)) {
                    System.out.println("Florist already exists: " + name);
                } else {
                    Florist florist = new Florist(name);
                    floristRepository.save(florist);
                    System.out.println("Florist '" + name + "' created ");
                }
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }
}






