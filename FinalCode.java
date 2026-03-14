package P1;

import java.io.File;
import java.util.Scanner;

public class FinalCode {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String studentName = "";
        boolean loggedIn = false;

        while (true) {
            System.out.println("\n1. Student Login");
            System.out.println("2. Start Exam");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Enter Student Name: ");
                    studentName = sc.nextLine().trim();

                    if (studentName.isEmpty()) {
                        System.out.println("Name cannot be empty");
                    } else {
                        loggedIn = true;
                        System.out.println("Login successful");
                    }
                    break;

                case "2":
                    if (!loggedIn) {
                        System.out.println("Please login first");
                        break;
                    }
                    startExam(sc, studentName);
                    break;

                case "3":
                    System.out.println("Thank you");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    
    private static void startExam(Scanner sc, String studentName) {

        int score = 0;
        int questionCount = 0;


        File file = new File("C:/hack/sample.txt");

        if (!file.exists()) {
            System.out.println("Question file not found");
            return;
        }

        try (Scanner fileSc = new Scanner(file)) {

            while (fileSc.hasNextLine()) {

                String line = fileSc.nextLine().trim();
                if(line.isEmpty())
                	continue;
                String[] q = line.split("\\|",-1);

                if (q.length < 6)
                    continue;
                for (int i = 0; i < q.length; i++) {
                    q[i] = q[i].trim();
                }

                questionCount++;
                System.out.println("\nQ" + questionCount + ": " + q[0]);
                System.out.println("A) " + q[1] + "  B) " + q[2]);
                System.out.println("C) " + q[3] + "  D) " + q[4]);
                System.out.print("Answer (A/B/C/D): ");

                String ans = sc.nextLine().trim().toUpperCase();

                if (ans.equals(q[5].trim().toUpperCase())) {
                    System.out.println("Correct");
                    score += 4;
                } else {
                    System.out.println("Wrong");
                    score -= 1;
                }
            }

            System.out.println("\nStudent Name : " + studentName);
            System.out.println("Total Questions : " + questionCount);
            System.out.println("Marks : " + score + " / " + (questionCount * 4));

        } catch (Exception e) {
            System.out.println("Error reading question file");
        }
    }
}
