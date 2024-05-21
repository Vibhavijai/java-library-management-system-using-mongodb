import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import com.mongodb.client.model.Updates;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCursor;
public class Main {
    JLabel l0, l1, l2, l3, l4, l5, l6;
    JTextField tf1, tf2, tf3, tf4, tf5;
    JButton btn1;
    public String copies,usr;

    Main(String u) {
       usr=u;
        JFrame f1 = new JFrame("LIBRARY");
        JButton b1 = new JButton("Add_Book");
        b1.setBounds(250, 150, 100, 30);
        f1.add(b1);
        JButton b2 = new JButton("Take_Book");
        b2.setBounds(250, 300, 100, 30);
        f1.add(b2);
        //f1.getContentPane().setBackground(Color.CYAN);
        f1.setSize(500, 500);
        ImageIcon background_ima = new ImageIcon("C://Users//vibha//Take.jpg");
        Image img1 = background_ima.getImage();
        Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
        background_ima = new ImageIcon(temp_img);
        JLabel background1 = new JLabel("",background_ima,JLabel.CENTER);
        background1.setBounds(0,0,1920,1080);
        f1.add(background1);
        f1.setLayout(null);
        f1.setVisible(true);

        JFrame f2 = new JFrame("ADDING BOOKS");

        JFrame f3 = new JFrame("TAKING BOOK");

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                l1 = new JLabel("BOOK NAME");
                l1.setBounds(50, 100, 120, 50);
                f2.add(l1);

                tf1 = new JTextField();
                tf1.setBounds(190, 110, 100, 20);
                f2.add(tf1);

                l2 = new JLabel("AUTHOR NAME");
                l2.setBounds(50, 140, 120, 50);
                f2.add(l2);


                tf2 = new JTextField();
                tf2.setBounds(190, 150, 100, 20);
                f2.add(tf2);

                l3 = new JLabel("PRICE");
                l3.setBounds(50, 180, 120, 50);
                f2.add(l3);


                tf3 = new JTextField();
                tf3.setBounds(190, 190, 100, 20);
                f2.add(tf3);

                l4 = new JLabel("COPIES");
                l4.setBounds(50, 220, 120, 50);
                f2.add(l4);


                tf4 = new JTextField();
                tf4.setBounds(190, 230, 100, 20);
                f2.add(tf4);

                l5 = new JLabel("PAGES");
                l5.setBounds(50, 260, 120, 50);
                f2.add(l5);


                tf5 = new JTextField();
                tf5.setBounds(190, 270, 100, 20);
                f2.add(tf5);

                l6 = new JLabel("JOURNAL");
                l6.setBounds(50, 300, 120, 50);
                f2.add(l6);
                String j[] = {"FICTION", "NON FICTION", "NOVEL", "SCIENCE", "MYSTERY", "THRILLER"};
                JComboBox<String> c = new JComboBox<String>(j);
                c.setBounds(190, 300, 120, 30);
                f2.add(c);

                JTextField tf6 = new JTextField();
                tf6.setBounds(50, 300, 120, 50);
                f2.add(tf6);


                f2.setVisible(true);
                l0 = new JLabel("LIBRARY MANAGEMENT SYSTEM");
                l0.setBounds(350, 50, 250, 50);
                f2.add(l0);


                btn1 = new JButton("ADD BOOK");
                btn1.setBounds(450, 450, 190, 20);
                f2.add(btn1);

                btn1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String bname = tf1.getText();
                        String aname = tf2.getText();
                        String journal = tf3.getText();
                        String price = tf3.getText();
                        copies = tf4.getText();
                        String pages = tf5.getText();
System.out.println(usr);
                        //1
                        try {
                            if (usr.equalsIgnoreCase("admin")) {
                                MongoCollection<Document> collection = database.getCollection("books");
                                Document doc = new Document("bnme", bname)
                                        .append("anme", aname)
                                        .append("pric", price)
                                        .append("cpy", copies)
                                        .append("pgs", pages)
                                        .append("jrnl", journal);
                                collection.insertOne(doc);
                                JOptionPane.showMessageDialog(f2, "Book added successfully...");
                            }
                            else
                                JOptionPane.showMessageDialog(f2, "You cannot add the book t...");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                });

                // f2.getContentPane().setBackground(Color.CYAN);
                f2.setSize(900, 900);
                ImageIcon background_imag = new ImageIcon("C://Users//vibha//add.jpg");
                Image img1 = background_imag.getImage();
                Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
                background_imag = new ImageIcon(temp_img);
                JLabel background2 = new JLabel("",background_imag,JLabel.CENTER);
                background2.setBounds(0,0,1920,1080);
                f2.add(background2);
                f2.setLayout(null);
                f2.setVisible(true);
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                JFrame f3 = new JFrame("TAKING BOOK");
                JLabel ll1 = new JLabel("BOOK NAME");
                JTextField t1 = new JTextField();
                t1.setBounds(200, 100, 120, 50);
                f3.add(t1);
                JButton bb1 = new JButton("Take Book");
                bb1.setBounds(100, 160, 120, 50);
                f3.add(bb1);
                ll1.setBounds(50, 100, 120, 50);
                f3.add(ll1);
                bb1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ee) { //2
                        try {
                            String book = t1.getText();

                            MongoCollection<Document> collection = database.getCollection("books");
                            MongoCursor<Document> cursor = collection.find().iterator();

                            while (cursor.hasNext()) {
                                Document document = cursor.next();
                                String bname = document.get("bnme").toString();
                                Integer copies = Integer.valueOf(document.get("cpy").toString());
                                if (bname.equalsIgnoreCase(book) && copies > 0) {

                                    collection.updateOne(
                                            Filters.eq("bnme", bname),
                                            Updates.set("cpy", copies - 1));

                                    JOptionPane.showMessageDialog(f3, "You can take the book and return within 15 days...");
                                    return;
                                }

                            else if (bname.equalsIgnoreCase(book) && copies <= 0) {
                                JOptionPane.showMessageDialog(f3, "Sorry :(( Running Out of Copies");
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(f3, "Sorry book is not available");


                    }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                f3.setSize(500, 500);
                ImageIcon background_imag = new ImageIcon("C://Users//vibha//add.jpg");
                Image img1 = background_imag.getImage();
                Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
                background_imag = new ImageIcon(temp_img);
                JLabel background2 = new JLabel("",background_imag,JLabel.CENTER);
                background2.setBounds(0,0,1920,1080);
                f3.add(background2);
                f3.setLayout(null);
                f3.setVisible(true);
            }
        });
        f1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f3.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    static MongoDatabase database;

    public static void main(String[] args) {

        JButton btnNewButton;
        JFrame fr = new JFrame("LOGIN PAGE");
        // fr.getContentPane().add(new JPanelWithBackground("login.jpeg"));
        //3
        try {
            String url = "mongodb+srv://21i365:Vibha_03@cluster0.gqvkohn.mongodb.net/?retryWrites=true&w=majority";
            String database_name = "dbms";

            ConnectionString connectionString = new ConnectionString(url);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(database_name);
            System.out.println("Connectivity Success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lb = new JLabel("LOGIN");
        lb.setBounds(100, 50, 100, 30);
        lb.setFont(new Font("Arial", Font.PLAIN, 30));
        fr.add(lb);

        JLabel lb1 = new JLabel("UserName");
        lb1.setBounds(50, 150, 100, 15);
        lb1.setFont(new Font("Arial", Font.BOLD, 15));
        fr.add(lb1);

        JTextField tt1 = new JTextField();
        tt1.setBounds(170, 150, 100, 20);
        fr.add(tt1);

        JLabel lb2 = new JLabel("Password");
        lb2.setBounds(50, 300, 100, 30);
        lb2.setFont(new Font("Arial", Font.BOLD, 15));
        fr.add(lb2);

        JPasswordField tt2 = new JPasswordField();
        tt2.setBounds(170, 300, 100, 30);
        fr.add(tt2);

        btnNewButton = new JButton("Login");
        btnNewButton.setBounds(100, 350, 100, 35);
        fr.add(btnNewButton);

        //4
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = tt1.getText();
                String password = String.valueOf(tt2.getPassword());
                String user = null;
                String pwd = null;
                try {
                    System.out.println("logging in");
                    MongoCollection<Document> collection = database.getCollection("login");
                    MongoCursor<Document> cursor = collection.find().iterator();

                    while (cursor.hasNext()) {
                        Document doc = cursor.next();
                        user = (String) doc.get("username");
                        pwd = doc.get("pwd").toString();
                        System.out.println(user + " " + pwd);

                        if (pwd.equalsIgnoreCase(password) && user.equalsIgnoreCase(userName)) {
                            JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                            new Main(userName);
                            return;
                        } else if (user.equalsIgnoreCase(userName) && pwd.equalsIgnoreCase(password) == false) {
                            JOptionPane.showMessageDialog(btnNewButton, "Password is Incorrect");
                            return;
                        }

                    }
                    JOptionPane.showMessageDialog(btnNewButton, "Username is new... You can sign in..!!");
                    //   try {
                    MongoCollection<Document> collectio = database.getCollection("login");
                    Document doc = new Document("username", userName)
                            .append("pwd", password);
                    collectio.insertOne(doc);
                    //  } catch (Exception ex) {
                    //   ex.printStackTrace();
                    //  }
                }
                catch (Exception a) {
                    System.out.println("Login error");
                }

            }
        });

        fr.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        fr.setSize(500, 500);
        ImageIcon background_image = new ImageIcon("C://Users//vibha//login.jpg");
        Image img1 = background_image.getImage();
        Image temp_img = img1.getScaledInstance(1920,1080,Image.SCALE_SMOOTH);
        background_image = new ImageIcon(temp_img);
        JLabel background1 = new JLabel("",background_image,JLabel.CENTER);
        background1.setBounds(0,0,1920,1080);
        fr.add(background1);
        fr.setLayout(null);
        fr.setVisible(true);
    }

}