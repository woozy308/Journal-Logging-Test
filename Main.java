import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Ticket machine logger //

        Scanner keyboard = new Scanner(System.in);
        boolean inUse = true;
        Message user = new Message();
        int selection;

        user.begin();
        user.menu();

        while (inUse) {

            selection = keyboard.nextInt();

            if (selection == 1) { // Writing a new ticket //
                try {

                    Random rand = new Random();
                    int ticketNum;
                    ticketNum = rand.nextInt(1000);

                    File ticket = new File("TICKET #" + ticketNum + ".txt");
                    if (ticket.createNewFile()) {
                        System.out.println("Ticket successfully opened: " + ticket.getName());
                    }
                    else {
                        System.out.println("Error: We apologize, but no tickets can be printed at this time.");
                        inUse = false; // emergency end to the machine //
                    }

                    try {
                        FileWriter myWriter = new FileWriter(ticket.getName());

                        myWriter.write("");
                        System.out.println("What is the reason for this ticket?");
                        myWriter.write("Reason for ticket: ");
                        myWriter.write(keyboard.next());
                        myWriter.write("");

                        System.out.println("Please elaborate on the reasoning.");
                        myWriter.write("Additional elaboration: ");
                        myWriter.write(keyboard.next());
                        myWriter.write("");

                        System.out.println("Number of faults/errors?");
                        myWriter.write("Fault/Error count: ");
                        myWriter.write(keyboard.next());
                        myWriter.write("");

                        System.out.println("Who is writing this?");
                        myWriter.write("Ticket logger: ");
                        myWriter.write(keyboard.next());
                        myWriter.write("");

                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }

            else if (selection == 2) { // Ticket reading/opening //

                int selectedTicket;
                System.out.println("Which ticket would you like to open? Please insert the three digits now.");
                selectedTicket = keyboard.nextInt();

                try {
                    File ticketRead = new File("TICKET #" + selectedTicket + ".txt");
                    Scanner myReader = new Scanner(ticketRead);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        System.out.println(data);
                        System.out.println();
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("The selected ticket, 'TICKET # " + selectedTicket + "', does not exist.");
                    e.printStackTrace();
                }

            }

            else if (selection == 3) { // Ticket Deletion //

                int ticketToDelete;
                System.out.println("Which ticket would you like to delete? Please insert the three digits now.");
                ticketToDelete = keyboard.nextInt();

                File deleteTicket = new File("TICKET #" + ticketToDelete + ".txt");
                if (deleteTicket.delete()) {
                    System.out.println("Result: Success! TICKET #" + ticketToDelete + " has been deleted.");
                }
                else {
                    System.out.println("Result: Failure! TICKET #" + ticketToDelete + " has NOT been deleted.");
                    System.out.println("Please try again!");
                }

            }

            else if (selection == 4) { // Vault key //
                int attemptKey;
                System.out.println("Please insert the management vault key now.");
                attemptKey = keyboard.nextInt();
                if (user.vaultKey(attemptKey)) {
                    try {

                        System.out.println("Success! Vault opening now...");
                        boolean vaultOpen = true;
                        System.out.println("There are two files in the vault, which would you like to open?");

                        while (vaultOpen) {
                            System.out.println("THE FILES:");
                            System.out.println("1 - The Epsilon Files");
                            System.out.println("2 - REPORT: Management Incidents");
                            System.out.println("3 - Exit the vault.");

                            int vaultChoice;
                            vaultChoice = keyboard.nextInt();

                            switch (vaultChoice) {
                                case 1:
                                    System.out.println("Opening File 1 - 'The Epsilon Files'");
                                    File ticketRead = new File("EPSILON.txt");
                                    Scanner myReader = new Scanner(ticketRead);
                                    while (myReader.hasNextLine()) {
                                        String data = myReader.nextLine();
                                        System.out.println(data);
                                        System.out.println();
                                    }

                                    System.out.println("End of the document, closing the file now...");
                                    myReader.close();
                                    break;

                                case 2:
                                    System.out.println("Opening File 2 - 'REPORT: Management Incidents'");
                                    File ticketRead1 = new File("REPORT.txt");
                                    Scanner myReader1 = new Scanner(ticketRead1);
                                    while (myReader1.hasNextLine()) {
                                        String data = myReader1.nextLine();
                                        System.out.println(data);
                                        System.out.println();
                                    }

                                    System.out.println("End of the document, closing the file now...");
                                    myReader1.close();
                                    break;

                                case 3:
                                    System.out.println("Exiting the vault...");
                                    vaultOpen = false;
                                    break;

                                default:
                                    System.out.println("ERROR: Incorrect file selected!");
                                    System.out.println("Please insert the correct file digit.");
                                    vaultChoice = keyboard.nextInt();
                                    break;
                            }

                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("error- uh oh");
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("The input vault key was incorrect.");
                    System.out.println("Returning to main menu now...");

                }
            }

            else if (selection == 5) { // end usage, does NOT enter the message loop //
                inUse = false; // turns off //
            }

            System.out.println("What else can I do for you?");
            user.menu();
        }

        user.end();

    }
}
