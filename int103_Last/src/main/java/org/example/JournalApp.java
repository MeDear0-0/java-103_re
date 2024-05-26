package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class JournalApp {
    private static JournalManager manager = new JournalManager();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {
        while (true) {
            System.out.println("Personal Journal Application");
            System.out.println("1. Write an entry");
            System.out.println("2. View entries");
            System.out.println("3. Delete an entry");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    writeEntry();
                    break;
                case 2:
                    viewEntries();
                    break;
                case 3:
                    deleteEntry();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void writeEntry() {
        System.out.print("Enter your journal entry: ");
        String text = scanner.nextLine();
        JournalEntry entry = new JournalEntry(LocalDateTime.now(), text);
        manager.writeEntry(entry);
        System.out.println("Entry saved.");
    }

    private static void viewEntries() {
        List<JournalEntry> entries = manager.readEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (JournalEntry entry : entries) {
                System.out.println(entry);
            }
        }
    }

    private static void deleteEntry() {
        List<JournalEntry> entries = manager.readEntries();
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
            return;
        }

        System.out.println("Select an entry to delete:");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i));
        }

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (choice < 1 || choice > entries.size()) {
            System.out.println("Invalid choice.");
        } else {
            LocalDateTime timestampToDelete = entries.get(choice - 1).getTimestamp();
            manager.deleteEntry(timestampToDelete);
            System.out.println("Entry deleted.");
        }
    }
}
