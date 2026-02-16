package com.client;

import com.bean.Ticket;
import com.bean.Train;
import com.service.BookingService;
import com.service.UserService;

import java.util.List;
import java.util.Scanner;

public class ClientCode {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
         new ClientCode().start();
    }

    public void start(){
          while (true){
              System.out.println("\n-----Welcome to Train Booking System-----");
              if (!userService.isLoggedIn()){
                  System.out.println("1. Register User");
                  System.out.println("2. Login User");
                  System.out.println("3. Exit");

                  System.out.print("\nEnter your Choice : ");
                  int choice = scanner.nextInt();

                  switch (choice){
                      case 1 -> register();
                      case 2 -> login();
                      case 3 -> exitApp();
                      default -> System.out.println("Invalid choice!");
                  }
              }
              else {
                    showUserManu();
              }
          }
    }

    public void register(){
        System.out.print("Enter username : ");
        String username = scanner.next();
        System.out.print("Enter password : ");
        String password = scanner.next();
        System.out.print("Enter Full name : ");
        scanner.nextLine();
        String fullName = scanner.nextLine();
        System.out.print("Enter contact : ");
        String contact = scanner.next();

        userService.registerUser(username, password, fullName, contact);
    }

    public void login(){
        System.out.print("Enter username : ");
        String username = scanner.next();
        System.out.print("Enter password : ");
        String password = scanner.next();

        userService.loginUser(username, password);
    }

    private void showUserManu(){
        while (userService.isLoggedIn()){
            System.out.println("-----User Menu-----");
            System.out.println("1. Search Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. View My Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. View all Trains");
            System.out.println("6. Logout");

            System.out.print("\nEnter your Choice : ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1 -> searchTrain();
                case 2 -> bookTicket();
                case 3 -> viewMyTicket();
                case 4 -> cancelTicket();
                case 5 -> bookingService.listOfAllTrains();
                case 6 -> userService.logoutUser();
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    private void searchTrain(){
        System.out.println("Enter source station : ");
        String source = scanner.next();
        System.out.println("Enter destination : ");
        String destination = scanner.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if (trains.isEmpty()){
            System.out.println("No trains found between "+source+ " and "+destination);
            return;
        }
        System.out.println("Trains Found...");
        for (Train train : trains){
            System.out.println(train);
        }
        System.out.print("Do you want to book Ticket ? (yes/no) : ");
        String ch = scanner.next();
        if (ch.equalsIgnoreCase("yes")){
            System.out.print("Enter train ID to book : ");
            int trainID = scanner.nextInt();
            System.out.print("Enter number of seats to book : ");
            int seats = scanner.nextInt();

            Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(), trainID, seats);
            if (ticket != null){
                System.out.println("Booking Successful!");
                System.out.println(ticket);
            }
        }
        else {
            System.out.println("Returning to user menu...");
        }
    }

    private void bookTicket(){
        System.out.println("Enter source station : ");
        String source = scanner.next();
        System.out.println("Enter destination : ");
        String destination = scanner.next();

        List<Train> trains = bookingService.searchTrain(source,destination);
        if (trains.isEmpty()){
            System.out.println("No trains found between "+source+ " and "+destination);
            return;
        }
        System.out.println("Available Trains :");
        for (Train train : trains){
            System.out.println(train);
        }
        System.out.print("Enter train ID to book : ");
        int trainID = scanner.nextInt();
        System.out.print("Enter number of seats to book : ");
        int seats = scanner.nextInt();

        Ticket ticket = bookingService.bookTicket(userService.getCurrentUser(), trainID, seats);
        if (ticket != null){
            System.out.println("Booking Successful!");
            System.out.println(ticket);
        }
    }

    private void viewMyTicket(){
        List<Ticket> ticketByUser = bookingService.getTicketByUser(userService.getCurrentUser());
        if (ticketByUser.isEmpty()){
            System.out.println("No tickets booked yet...");
        }
        else {
            System.out.println("Your Tickets : ");
            for (Ticket ticket : ticketByUser){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket(){
        System.out.print("Enter Ticket ID to cancel : ");
        int ticketID = scanner.nextInt();
        bookingService.cancelTicket(ticketID, userService.getCurrentUser());
    }

    private void exitApp(){
        System.out.println("Thank You!");
        System.exit(0);
    }
}
