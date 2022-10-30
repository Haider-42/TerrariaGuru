/*****************************************************************************************
 * Authors:  Adam O., Kersh A. and Haider S.
 * Date:  6/8/2022
 * Description:  GUI-based project with sorting, adding, removing and updating a database.
 **************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.FileWriter;

/*******************************************************************
/CLASS: contains backround object and call to function method init
/*******************************************************************/
public class Main
{
   private static final long serialVersionUID =1L;
   public static void main(String[] args)
   {
   
      Background frame = new Background();
      frame.init();
   }

}

/*************************************************************************************************
/CLASS: contains all methods that create JPanel, buttons, images, and all functionality methods
/***********************************************************************************************/   
class Background extends JFrame implements ActionListener, MouseListener {
 
  //Fields
   private static final long serialVersionUID = 1L;
   JLabel terrlabel1;
   JLabel terrlabel2;
   JLabel terrlabel3;
   JLabel terrlabel4;
   JButton a;
   JButton b;
   JButton c;
   JButton d;
   JButton settings;
   JButton edit;
   JFrame frame;
   JPanel panel;
   JPanel bottompanel;
   JLabel weaponImage;
   JScrollPane scr;
   JList<String> listOfStrings;
   DefaultListModel<String> listModel;

   ArrayList<Terraria> weapons = readInData("terraria item guru.csv");


 //CONSTRUCTOR: Creates main JPanel for program to be built on, buttons, labels, and images
   public Background() {
      super("Terraria Item Guru");
   
      //panels
      panel = (JPanel)this.getContentPane();
      panel.setLayout(new BorderLayout());
      panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
      frame = new JFrame("Terraria Item Guru");
      frame.getContentPane().add(panel);
      panel.setBackground(new Color(65, 146, 75));
      panel.setDoubleBuffered(true);
      panel.setLayout(null);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
   
      bottompanel = new JPanel();
      bottompanel.setLayout(null);
      bottompanel.setBounds(0, 400, 610, 200);
      bottompanel.setBackground(new Color(139, 69, 19));
      bottompanel.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 10, new Color(194, 164, 132)));
      panel.add(bottompanel);
      
      //Image JLabel
      weaponImage = new JLabel();
      weaponImage.setBounds(235, 40, 350, 350);
      panel.add(weaponImage);
   
      //Buttons
      a = new JButton("Drop Rate");
      a.setBackground(new Color(139, 69, 19));
      a.setBounds(15, 70, 150, 35);
      a.setForeground(new Color(65, 146, 75));
      a.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(194, 164, 132)));
      a.setFocusPainted(false);
      a.setContentAreaFilled(false);
      a.setOpaque(true);
      a.addActionListener(this);
      panel.add(a);
   
      b = new JButton("Weapon Type");
      b.setBounds(15, 130, 150, 35);
      b.setBackground(new Color(139, 69, 19));
      b.setForeground(new Color(65, 146, 75));
      b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(194, 164, 132)));
      b.setFocusPainted(false);
      b.setContentAreaFilled(false);
      b.setOpaque(true);
      b.addActionListener(this);
      panel.add(b);
   
      c = new JButton("Enemy Dropped");
      c.setBounds(15, 190, 150, 35);
      c.setBackground(new Color(139, 69, 19));
      c.setForeground(new Color(65, 146, 75));
      c.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(194, 164, 132)));
      c.setContentAreaFilled(false);
      c.setFocusPainted(false);
      c.setOpaque(true);
      c.addActionListener(this);
      panel.add(c);
   
      d = new JButton("Name");
      d.setBounds(15, 250, 150, 35);
      d.setBackground(new Color(139, 69, 19));
      d.setForeground(new Color(65, 146, 75));
      d.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(194, 164, 132)));
      d.setFocusPainted(false);
      d.setContentAreaFilled(false);
      d.setOpaque(true);
      d.addActionListener(this);
      panel.add(d);
   
      settings = new JButton("Customize");
      settings.setBounds(15, 10, 120, 25);
      settings.setBorder(new RoundBtn(15));
      settings.setBackground(new Color(139, 69, 19));
      settings.setFocusPainted(false);
      settings.setForeground(new Color(0x03377C));
      settings.setContentAreaFilled(false);
      settings.setOpaque(true);
      settings.addActionListener(this);
      panel.add(settings);
   
      edit = new JButton("Edit");
      edit.setBounds(140, 10, 120, 25);
      edit.setBorder(new RoundBtn(15));
      edit.setBackground(new Color(139, 69, 19));
      edit.setFocusPainted(false);
      edit.setForeground(new Color(0x03377C));
      edit.setContentAreaFilled(false);
      edit.setOpaque(true);
      edit.addActionListener(this);
      panel.add(edit);
     
      //JList with Items
      listModel = new DefaultListModel<String>();
      for (Terraria t : weapons)
         listModel.addElement(t.iName);
      listOfStrings = new JList<String>(listModel);
      listOfStrings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      listOfStrings.setAlignmentX(Component.RIGHT_ALIGNMENT );
      listOfStrings.addMouseListener(this);
      scr = new JScrollPane(listOfStrings);
      listOfStrings.setLayoutOrientation(JList.VERTICAL);
      scr.setBounds(610, 5, 190, 570);
      panel.add(scr, BorderLayout.EAST);
   
      //Labels
      terrlabel1 = new JLabel("Name:  ");
      terrlabel1.setBounds(0, 10, 600, 25);
      terrlabel1.setBackground(new Color(25, 25, 25));
      terrlabel1.setForeground(Color.white);
      terrlabel1.setFont(new Font("Hack", Font.PLAIN, 16));
      bottompanel.add(terrlabel1);
   
   
      terrlabel2 = new JLabel("Enemy Dropped:  ");
      terrlabel2.setBounds(0, 50, 600, 25);
      terrlabel2.setBackground(new Color(25, 25, 25));
      terrlabel2.setForeground(Color.white);
      terrlabel2.setFont(new Font("Hack", Font.PLAIN, 16));
      bottompanel.add(terrlabel2);
   
   
      terrlabel3 = new JLabel("Drop Rate:  ");
      terrlabel3.setBounds(0, 90, 600, 26);
      terrlabel3.setBackground(new Color(25, 25, 25));
      terrlabel3.setForeground(Color.white);
      terrlabel3.setFont(new Font("Hack", Font.PLAIN, 16));
      bottompanel.add(terrlabel3);
   
      terrlabel4 = new JLabel("Weapon Type:  ");
      terrlabel4.setBounds(0, 130, 600, 25);
      terrlabel4.setBackground(new Color(25, 25, 25));
      terrlabel4.setForeground(Color.white);
      terrlabel4.setFont(new Font("Hack", Font.PLAIN, 16));
      bottompanel.add(terrlabel4);
     
      frame.pack();
      frame.setMinimumSize(new Dimension(800,610));
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
   
   }

//METHOD: Gives buttons functionality
   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();
      if (command.equals("a"))
         dropRate(weapons);
      else if (command.equals("b"))
         weaponType(weapons);
      else if (command.equals("c"))
         enemyDrop(weapons);
      else if (command.equals("d"))
         itemName(weapons);
   
   
      if (e.getSource() == a)
         dropRate(weapons);
      else if (e.getSource() == b) 
         weaponType(weapons);
      else if (e.getSource() == c) 
         enemyDrop(weapons);
      else if (e.getSource() == d)
         itemName(weapons);

      play("buttoneffect.wav");
      mutate();
      if (e.getSource() == settings) {
         Color init = Color.BLUE;
         Color coleur = JColorChooser.showDialog(this, "Choose a color", init);
         panel.setBackground(coleur);
      }
      if (e.getSource() == edit)
         edit();
   }


 //METHOD: Reads the ArrayList to add into the JList on RHS
   public void populate()
   {
      if(listModel != null)
         listModel.clear();
       
      for (Terraria t: weapons)
         listModel.addElement(t.iName);
   }


 //METHOD: Allows user to select a weapon to view stats
   public void mouseClicked(MouseEvent f) 
   {
      if (f.getClickCount() == 1) 
      {
         JList target = (JList)f.getSource();
         int ind = target.locationToIndex(f.getPoint());
         if (ind >= 0)
            selection(ind);      
      }
   }
   public void mouseEntered(MouseEvent f) 
   {  }  
   public void mouseExited(MouseEvent f) 
   {  }   
   public void mousePressed(MouseEvent f) 
   {  }   
   public void mouseReleased(MouseEvent f) 
   {  }


 //METHOD: Prints out image and data for selected item
   public void selection(int index)
   {
      Terraria selected = weapons.get(index);
      String nameOfFile = (selected.iName.replaceAll("\\s", "")+".png");
      image(nameOfFile);
      populateB(selected);
   }
 
//METHOD: Loads image and displays it in GUI
   public void image(String nameFile)
   {
      BufferedImage img = null;
      Image newImage = null;
      File f = new File("/home/runner/TerrariaItemGuru/Terraria Items/"+nameFile);
      try
      {      
         //Sorts whether it's an official Terraria Item or not
         if (f.exists())
            img = ImageIO.read(f);
         else
            img = ImageIO.read(new File("/home/runner/TerrariaItemGuru/Terraria Items/custom.png"));
            
         //Resizing the Image
         int w = img.getWidth();
         int h = img.getHeight();
         int mod = 0;
         int max = 350;
         
         if(h >= w)
         {
            while(max > 0)
            {
               max = max-h;
               if(max >= 0)
                  mod++;
            }
         }
         
         else
         {
            while(max > 0)
            {
               max = max-w;
               if(max>0)
                  mod++;
            }
         }
         
         //Sets the image in JLabel
         newImage = img.getScaledInstance((w*mod), (h*mod), Image.SCALE_DEFAULT);
         weaponImage.setIcon(new ImageIcon(newImage));
         weaponImage.setHorizontalAlignment(SwingConstants.CENTER);
         weaponImage.setVerticalAlignment(SwingConstants.CENTER);
      } 
      catch (IOException ex)
      {
         System.out.println("Couldn't Load Image");      
      }
   }

   //METHOD: populates the Bottom Panel with stats of the weapon
   public void populateB(Terraria s)
   {
      terrlabel1.setText("Name:  "+s.iName);
      terrlabel2.setText("Enemy Dropped:  "+s.enemy);
      terrlabel3.setText("Drop Rate:  "+s.drop+"%");
      terrlabel4.setText("Weapon Type:  "+s.type);
   }
   
//METHOD: allows the user to add/change/delete weapons
   public void edit()
   {
    try
    {
      Object[] possibilities = {"Add", "Update", "Delete"};
      String s = (String)JOptionPane.showInputDialog(null,
                              "Although this program is meant for Terraria, you can add, remove or update any weapon. \nWhat would you like to do? \nNOTE: IF YOU PUT DOWN AN UNOFFICIAL NAME AND NOT PUT AN IMAGE WITH THE SAME WEAPON NAME, \nAN ICON OF TERRARIA WILL BE DISPLAYED.",
                              "Terraria Item Guru",
                              JOptionPane.PLAIN_MESSAGE,
                              null,
                              possibilities,
                              "Add");
    
      // CRUD Methods  
      if (s.equals("Add"))
         add();            
      if (s.equals("Update"))
         update();
      if (s.equals("Delete"))
         delete();
      }

      catch(Exception ex)
      {
        JOptionPane.showMessageDialog (null,
                  "You have decided not to add/update/remove a weapon.");
      }
   }
   
//METHOD: allows the user to add a custom weapon   
   public void add()
   {
      //Fields for input
      JTextField name = new JTextField();
      JTextField enemy = new JTextField();
      JTextField rate = new JTextField();
      JTextField type = new JTextField();
      Object[] weapon = {"Name:", name, "Enemy Dropped:", enemy, "Drop Rate:", rate, "Weapon type:", type};
   
      int option = JOptionPane.showConfirmDialog(null, weapon, "Add a Weapon", JOptionPane.OK_CANCEL_OPTION);
      
      //Adds weapon created from input
      if (option == JOptionPane.OK_OPTION)
      {
         weapons.add(new Terraria(name.getText(), type.getText(), enemy.getText(), Double.parseDouble(rate.getText())));
        itemName(weapons);
         mutate();
      }
      
      //Doesn't add weapon
      else
         JOptionPane.showMessageDialog (null,
                  "You have decided not to add a weapon.");
   }
   
   
//METHOD: allows the user to change the identity of an existing weapon   
   public void update()
   {
      int max = weapons.size()-1;
      JTextField id = new JTextField();
      Terraria temp;
      
      //Try-Catch for Finding Item
      try
      {
      String iden = JOptionPane.showInputDialog("You want to update a weapon. \nPlease choose a number between 0 and "+max+".");
         int d = Integer.valueOf(iden);
         int choice = JOptionPane.showConfirmDialog(null, 
                                                ("You have chosen to update the weapon, "+weapons.get(d).iName+". \nAre you sure you want to update this weapon?"));
         
         //Creates a temporary object to replace the object 
         if (choice == 0)
         {
            //Fields for input
            JTextField name = new JTextField();
            JTextField enemy = new JTextField();
            JTextField rate = new JTextField();
            JTextField type = new JTextField();
            Object[] weapon = {"Name:", name, "Enemy Dropped:", enemy, "Drop Rate:", rate, "Weapon type:", type};

            int option = JOptionPane.showConfirmDialog(null, weapon, "Update the Existing Weapon", JOptionPane.OK_CANCEL_OPTION);
            
            //Creates new weapon, deletes old weapon
            if (option == JOptionPane.OK_OPTION)
            {
               weapons.remove(weapons.get(d)); 
               weapons.add(new Terraria(name.getText(), type.getText(), enemy.getText(), Double.parseDouble(rate.getText())));
               itemName(weapons);
               mutate();
            }
         }

         //No Changes
         else if (choice == 1)
         JOptionPane.showMessageDialog (null,
                  "You have decided not to update a weapon.");
      }
      
      catch (Exception ex)
      {
      JOptionPane.showMessageDialog (null,
                  "You have decided not to update a weapon.");
      }
   }
   
   
//METHOD: allows the user to delete a weapon   
   public void delete()
   {
      int max = weapons.size()-1;
      JTextField id = new JTextField();
      
      //Try-Catch for Finding Item
      try
      {
      String iden = JOptionPane.showInputDialog("You want to delete a weapon. \nPlease choose a number between 0 and "+max+".");
         int d = Integer.valueOf(iden);
         int choice = JOptionPane.showConfirmDialog(null, 
                                                ("You have chosen to delete the weapon, "+weapons.get(d).iName+". \nAre you sure you want to continue?"));
         //Deletes weapon
         if (choice == 0)
         {
            weapons.remove(weapons.get(d));
            itemName(weapons);
            mutate();
         }
         
         //Message stating that use doesn't want to remove a weapon
         else if (choice == 1)
         JOptionPane.showMessageDialog (null,
                  "You have decided not to delete a weapon.");
      }
      
      catch (Exception ex)
      {
      JOptionPane.showMessageDialog (null,
                  "You have decided not to delete a weapon.");
      }
   }

//METHOD: allows the user to add a custom weapon   
   public void mutate()
   {
      //Updates arraylist
      populate();
      
      //Makes permanent change in the csv file
      File csvFile = new File("terraria item guru.csv");
      try (PrintWriter csvWriter = new PrintWriter(new FileWriter(csvFile));)
      {
         for(Terraria t : weapons)
            csvWriter.println(t.iName+","+t.type+","+t.enemy+","+t.drop);
      }
      catch (Exception ex)
      {
         System.out.println("oops.");
      }
   }


 //METHOD: plays background music
   public void play(String song)
   {
      try{
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(song));
         Clip myClip = AudioSystem.getClip();
         myClip.open(audioInputStream);
         myClip.start();
      
      } catch(Exception ex){
         System.out.print("Caught error in play method" + ex);
      }
   }



 //METHOD: Merge sort (sorts data by name)
   public static void itemName(ArrayList<Terraria> weapons)
   {
      if (weapons.size() > 1)
      {
         ArrayList<Terraria> firstHalf = new ArrayList<Terraria> (weapons.size()/2);
         ArrayList<Terraria> secondHalf = new ArrayList<Terraria>(weapons.size() - weapons.size()/2);
      
         for (int i = 0; i < weapons.size(); i ++){
            if (i < weapons.size()/2)
               firstHalf.add(weapons.get(i));
            else
               secondHalf.add(weapons.get(i));
         }
      
         itemName(firstHalf);
         itemName(secondHalf);
      
         int l = 0, r = 0, i = 0;
      
         while (l < firstHalf.size() && r < secondHalf.size())
         {
            String firstName = firstHalf.get(l).iName;
            String secondName = secondHalf.get(r).iName;
            if (firstName.compareTo(secondName) > 0)
               weapons.set(i++, secondHalf.get(r++));
            else
               weapons.set(i++, firstHalf.get(l++));
         }
      
         while (l < firstHalf.size())
            weapons.set(i++, firstHalf.get(l++));
      
         while (r < secondHalf.size())
            weapons.set(i++, secondHalf.get(r++));
      
      }
   }

   //METHOD: Merge sort (sorts data by Enemy Dropped)
   public static void enemyDrop(ArrayList<Terraria> weapons)
   {
      if (weapons.size() > 1) {
         ArrayList<Terraria> firstHalf = new ArrayList<Terraria>(weapons.size() / 2);
         ArrayList<Terraria> secondHalf = new ArrayList<Terraria>(weapons.size() - weapons.size() / 2);
      
         for (int i = 0; i < weapons.size(); i++) {
            if (i < weapons.size() / 2)
               firstHalf.add(weapons.get(i));
            else
               secondHalf.add(weapons.get(i));
         }
      
         enemyDrop(firstHalf);
         enemyDrop(secondHalf);
      
         int l = 0, r = 0, i = 0;
      
         while (l < firstHalf.size() && r < secondHalf.size()) {
            String firstName = firstHalf.get(l).enemy;
            String secondName = secondHalf.get(r).enemy;
            if (firstName.compareTo(secondName) > 0)
               weapons.set(i++, secondHalf.get(r++));
            else
               weapons.set(i++, firstHalf.get(l++));
         }
      
         while (l < firstHalf.size())
            weapons.set(i++, firstHalf.get(l++));
      
         while (r < secondHalf.size())
            weapons.set(i++, secondHalf.get(r++));
      
      }
   }
    
   //METHOD: Merge sort (sorts data by Weapon Type)
   public static void weaponType(ArrayList<Terraria> weapons)
   {
      if (weapons.size() > 1)
      {
         ArrayList<Terraria> firstHalf = new ArrayList<Terraria> (weapons.size()/2);
         ArrayList<Terraria> secondHalf = new ArrayList<Terraria>(weapons.size() - weapons.size()/2);
      
         for (int i = 0; i < weapons.size(); i++)
         {
            if (i < weapons.size()/2)
               firstHalf.add(weapons.get(i));
            else
               secondHalf.add(weapons.get(i));
         }
      
         weaponType(firstHalf);
         weaponType(secondHalf);
      
         int l = 0, r = 0, i = 0;
      
         while (l < firstHalf.size() && r < secondHalf.size())
         {
            String firstName = firstHalf.get(l).type;
            String secondName = secondHalf.get(r).type;
            if (firstName.compareTo(secondName) > 0 )
               weapons.set(i++, secondHalf.get(r++));
            else
               weapons.set(i++, firstHalf.get(l++));
         }
         while (l < firstHalf.size())
            weapons.set(i++, firstHalf.get(l++));
      
         while (r < secondHalf.size())
            weapons.set(i++, secondHalf.get(r++));
      }
   }

   //METHOD: Merge sort (sorts data by Drop Rate)
   public static void dropRate(ArrayList<Terraria> weapons)
   {
      if (weapons.size() > 1)
      {
         ArrayList<Terraria> firstHalf = new ArrayList<Terraria> (weapons.size()/2);
         ArrayList<Terraria> secondHalf = new ArrayList<Terraria>(weapons.size() - weapons.size()/2);
      
         for (int i = 0; i < weapons.size(); i++)
         {
            if (i < weapons.size()/2)
               firstHalf.add(weapons.get(i));
            else
               secondHalf.add(weapons.get(i));
         }
      
         dropRate(firstHalf);
         dropRate(secondHalf);
      
         int l = 0, r = 0, i = 0;
      
         while (l < firstHalf.size() && r < secondHalf.size())
         {
            double firstName = firstHalf.get(l).drop;
            double secondName = secondHalf.get(r).drop;
            if (firstName >= secondName)
               weapons.set(i++, secondHalf.get(r++));
            else
               weapons.set(i++, firstHalf.get(l++));
         }
         while (l < firstHalf.size())
            weapons.set(i++, firstHalf.get(l++));
      
         while (r < secondHalf.size())
            weapons.set(i++, secondHalf.get(r++));
      }
   }

   public static void swap(ArrayList<Terraria> weapons, int li, int ri)
   {
      Terraria tempWeapon = weapons.get(li);
      weapons.set(li, weapons.get(ri));
      weapons.set(ri, tempWeapon);
   }

   public static ArrayList<Terraria> readInData(String fileN)
   {
      // instantiate an ArrayList for all record data
      ArrayList<Terraria> weapons = new ArrayList<Terraria>();
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(fileN));
         String line;
         while ((line = reader.readLine())!=null)
         {
            // split the line into an array (String.split())
            String[] data = line.split(",");
            weapons.add(new Terraria(data[0],data[1],data[2],Double.valueOf(data[3])));
         }
         reader.close();
      }
      catch (IOException iox)
      {
         System.out.println("Problem reading "+fileN);
      }
   
      return weapons;
   }

   public void init() {
      play("music.wav");
   }
} //end of class


/******************************************************************
/CLASS: creates a rounded border for the customize and edit buttons
/*****************************************************************/  
class RoundBtn implements Border
{
   private int r;
   RoundBtn(int r) {
      this.r = r;
   }
   public Insets getBorderInsets(Component c) {
      return new Insets(this.r+1, this.r+1, this.r+2, this.r);
   }
   public boolean isBorderOpaque() {
      return true;
   }
   public void paintBorder(Component c, Graphics g, int x, int y,
                               int width, int height) {
      g.drawRoundRect(x, y, width-1, height-1, r, r);
   }
} //end of class


/*******************************************
/CLASS: Constructs a weapon w/Terraria class
/*****************************************/ 
class Terraria
{
   String iName;
   String type;
   String enemy;
   double drop;


//CONSTRUCTOR: Uses input to store the object's fields
   public Terraria(String iName, String type, String enemy, double drop)
   {
      this.iName = iName;
      this.type = type;
      this.enemy = enemy;
      this.drop = drop;
   }

//OVERLOADED CONSTRUCTOR: Creates the object
   public Terraria (Terraria obj)
   {
      this(obj.iName, obj.type, obj.enemy, obj.drop);
   }
} //end of class and program
