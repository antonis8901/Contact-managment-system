package objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String address;
    private String category;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getCategory() {
        return category;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCategory(String category) {
        this.category = category;
    }




    public Contact() {
    }
    public Contact(String firstName, String lastName, String phoneNumber, String email, String address, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.category = category;

    }


    public static Contact createContact() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        String firstName = scan.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scan.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scan.nextLine();
        System.out.print("Enter Email: ");
        String email = scan.nextLine();
        System.out.print("Enter Address: ");
        String address = scan.nextLine();
        System.out.print("Enter objects.Category: ");
        String category = scan.nextLine();
        return new Contact(firstName, lastName, phoneNumber, email, address, category);
    }
    public static void createOne(Path f) {
        try{
            Files.createFile(f);
            System.out.println("File created");
        } catch (IOException e)
        {
            System.err.println("Error! Cannot create file");
        }
        finally {
            System.out.println("Operation ended");
        }

    }
    public static Contact exportFromFile() {
        String filepath = " ";
        Path path = Paths.get(filepath);
        Contact.createOne(path);
        Contact contact = new Contact();
        try (BufferedReader br = Files.newBufferedReader(path)){
            //String out;
            //while ((out = br.readLine()) != null)
            contact.setFirstName(br.readLine());
            contact.setLastName(br.readLine());
            contact.setPhoneNumber(br.readLine());
            contact.setEmail(br.readLine());
            contact.setAddress(br.readLine());
            contact.setCategory(br.readLine());
        }
        catch (Throwable x){
            System.err.println("Error! Cannot read file");
        }
        finally {
            System.out.println("Operation ended");
        }
        return contact;
    }
    public static void exportToDatabase(Contact contact) {
        try
        {
            String url = " ";
            Connection c = DriverManager.getConnection(url);
            try (c)
            {
                c.setAutoCommit(false);
                try (PreparedStatement s = c.prepareStatement("INSERT INTO Contact (firstName, lastName, phoneNumber, email, address, category)" +
                        " VALUES (?, ?, ?, ?, ?, ?);"))
                {
                    s.setString(1, contact.getFirstName());
                    s.setString(2, contact.getLastName());
                    s.setString(3, contact.getPhoneNumber());
                    s.setString(4, contact.getEmail());
                    s.setString(5, contact.getAddress());
                    s.setString(6, contact.getCategory());
                    s.executeUpdate();//executeUpdate because it does not return
                    c.commit();
                }
            }
            catch (Exception ix)
            {
                c.rollback();
                throw ix;
            }
        }
        catch (Exception x)
        {
            System.err.format("%s", x.getMessage());
        }
        finally {
            System.out.println("Operation completed");
        }
    }
    public static List<Contact> importFromDatabase() {
        List<Contact>  contacts = new ArrayList<>();
        String url = " ";
        try (Connection c = DriverManager.getConnection(url);
             PreparedStatement s = c.prepareStatement("SELECT * FROM Contact");
             ResultSet r = s.executeQuery()) {//executeQuery because it returns something

            while (r.next()) {
                Contact contact = new Contact();
                contact.setFirstName(r.getString("firstName"));
                contact.setLastName(r.getString("lastName"));
                contact.setPhoneNumber(r.getString("phoneNumber"));
                contact.setEmail(r.getString("email"));
                contact.setAddress(r.getString("address"));
                contact.setCategory(r.getString("category"));
                contacts.add(contact);
            }

        } catch (Exception x) {
            System.err.println(x.getMessage());
        }

        return contacts;
    }
    public static void importToFile(List<Contact> contacts) {
        String file = " ";
        Path filepath = Paths.get(file);
        try (BufferedWriter bw = Files.newBufferedWriter(filepath)) {
            for (Contact contact : contacts) {
                bw.write(contact.getFirstName());
                bw.newLine();
                bw.write(contact.getLastName());
                bw.newLine();
                bw.write(contact.getPhoneNumber());
                bw.newLine();
                bw.write(contact.getEmail());
                bw.newLine();
                bw.write(contact.getAddress());
                bw.newLine();
                bw.write(contact.getCategory());
                bw.write("\n");
                bw.newLine();
            }
        }
        catch (Throwable x){
            System.err.println("Error! Cannot pass to file");
        }
        finally {
            System.out.println("Operation completed");
        }
    }


    public static void deleteContact(Contact contact) {


        try
        {
            String url = " ";
            Connection c = DriverManager.getConnection(url);
            try (c)
            {
                c.setAutoCommit(false);
                try (PreparedStatement s = c.prepareStatement("DELETE FROM Contact WHERE firstName = ?;"))
                {
                    s.setString(1, contact.getFirstName());
                    s.executeUpdate();
                    c.commit();
                }
            }
            catch (Exception ix)
            {
                c.rollback();
                throw ix;
            }
        }
        catch (Exception x)
        {
            System.err.format("%s", x.getMessage());
        }
    }
    public static String showAllPersonsRecords() {
        StringBuilder sb = new StringBuilder();
        String url = " ";//Database path
        try (Connection c = DriverManager.getConnection(url)) {//drivemanager opens connection to db,stores it in c
            try (Statement s = c.createStatement()) {//object used to send sqlite commands to the Database
                try (ResultSet r = s.executeQuery("SELECT* FROM Contact")) {//executes statement
                    while (r.next())
                        sb.append(String.format(
                                "ID: %d | %s %s | %s | %s | %s | %s%n",
                                r.getInt("id"),
                                r.getString("firstName"),
                                r.getString("lastName"),
                                r.getString("phoneNumber"),
                                r.getString("email"),
                                r.getString("address"),
                                r.getString("category")
                        ));
                }
            }
        }
        catch (Exception x) {
            return ("Error!");

        }
        return sb.toString();
    }
    public static String searchByName(String nameSearch) {
        StringBuilder result = new StringBuilder();

        try
        {
            String url = " ";
            Connection c = DriverManager.getConnection(url);
            try (c)
            {
                c.setAutoCommit(false);
                try (PreparedStatement s = c.prepareStatement("SELECT * FROM Contact WHERE firstName = ?;"))
                {
                    s.setString(1, nameSearch);
                    try (ResultSet r = s.executeQuery()) {
                        if (r.next()) {
                            result.append(String.format(
                                    "%d | %s | %s | %s | %s | %s | %s",
                                    //System.out.println("objects.Contact found:"),
                                    r.getInt("id"),
                                    r.getString("firstName"),
                                    r.getString("lastName"),
                                    r.getString("phoneNumber"),
                                    r.getString("email"),
                                    r.getString("address"),
                                    r.getString("category")
                            ));
                        } else {
                            result.append("No contact found with first name: ").append(nameSearch);
                        }
                    }
                }
            }
            catch (Exception x)
            {
                result.append("Error!").append(x.getMessage());
            }
        }
        catch (Exception x)
        {
            System.err.format("%s", x.getMessage());
        }
        return result.toString();
    }
    public static String searchByPhoneNumber(String phoneSearch) {
        StringBuilder result = new StringBuilder();

        try
        {
            String url = " ";
            Connection c = DriverManager.getConnection(url);
            try (c)
            {
                c.setAutoCommit(false);
                try (PreparedStatement s = c.prepareStatement("SELECT * FROM Contact WHERE phoneNumber = ?;"))
                {
                    s.setString(1, phoneSearch);
                    try (ResultSet r = s.executeQuery()) {
                        if (r.next()) {
                            result.append(String.format(
                                    "%d | %s | %s | %s | %s | %s | %s",
                                    r.getInt("id"),
                                    r.getString("firstName"),
                                    r.getString("lastName"),
                                    r.getString("phoneNumber"),
                                    r.getString("email"),
                                    r.getString("address"),
                                    r.getString("category")
                            ));
                        } else {
                            result.append("No contact found with first name: ").append(phoneSearch);
                        }
                    }
                }
            }
            catch (Exception x)
            {
                result.append("Error!").append(x.getMessage());
            }
        }
        catch (Exception x)
        {
            System.err.format("%s", x.getMessage());
        }
        return result.toString();
    }
    public static String searchByCategory(String categorySearch) {
        StringBuilder result = new StringBuilder();
        boolean found = false;

        try {
            String url = " ";
            try (Connection c = DriverManager.getConnection(url);
                 PreparedStatement s = c.prepareStatement("SELECT * FROM Contact WHERE category = ?")) {
                s.setString(1, categorySearch);

                try (ResultSet r = s.executeQuery()) {
                    while (r.next()) {
                        found = true;
                        result.append(String.format(
                                "%d | %s | %s | %s | %s | %s | %s%n",
                                r.getInt("id"),
                                r.getString("firstName"),
                                r.getString("lastName"),
                                r.getString("phoneNumber"),
                                r.getString("email"),
                                r.getString("address"),
                                r.getString("category")
                        ));
                    }
                }
            }

            if (!found) {
                result.append("No contacts found with category: ")
                        .append(categorySearch);
            }

        } catch (Exception x) {
            result.append("Error: ").append(x.getMessage());
        }

        return result.toString();
    }

    public static void addContact(Contact contact) {
        try
        {
            String url = " ";
            Connection c = DriverManager.getConnection(url);
            try (c)
            {
                c.setAutoCommit(false);
                try (PreparedStatement s = c.prepareStatement("INSERT INTO Contact (firstName, lastName, phoneNumber, email, address, category) " +
                        "VALUES (?, ?, ?, ?, ?, ?);"))
                {
                    s.setString(1, contact.getFirstName());
                    s.setString(2, contact.getLastName());
                    s.setString(3, contact.getPhoneNumber());
                    s.setString(4, contact.getEmail());
                    s.setString(5, contact.getAddress());
                    s.setString(6, contact.getCategory());
                    s.executeUpdate();
                    c.commit();
                }
            }
            catch (Exception ix)
            {
                c.rollback();
                throw ix;
            }
        }
        catch (Exception x)
        {
            System.err.format("%s", x.getMessage());
        }
        finally {
            System.out.println("Operation completed");
        }
    }
    public static void modifyContact(String oldName, String newName){

        try
        {
            String url = " ";
            Connection c = DriverManager.getConnection(url);
            try (c)
            {
                c.setAutoCommit(false);
                try (PreparedStatement s = c.prepareStatement("UPDATE Contact SET firstName = ? WHERE firstName = ?;"))
                {
                    s.setString(1, newName);
                    s.setString(2, oldName);
                    s.executeUpdate();
                    c.commit();
                    System.out.println("objects.Contact updated");
                }
            }
            catch (Exception ix)
            {
                c.rollback();
                throw ix;
            }
        }
        catch (Exception x)
        {
            System.err.format("%s", x.getMessage());
        }
    }


}
