package iCook.View.Operations;

import iCook.Controller.ServiceDispatcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * User interface for iCook's "home" page (Displayed when a user successfully logs in).
 * A user can chose to go to the inventory page, recipes page, or logout from this screen.
 *
 * @author Team 2
 * @version 04/27/2021
 */
public class HomeUI extends JPanel implements ActionListener {
    //User Home Page with two buttons
    //Search and My Inventory
    private BufferedImage img;
    private JPanel homepanel;
    private ServiceDispatcher serviceDispatcher;

    public HomeUI() {
        serviceDispatcher = new ServiceDispatcher();
        this.setLayout(new BorderLayout());
        homepanel = new JPanel(new GridBagLayout());

        Border emptyBorder = BorderFactory.createEmptyBorder();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255,255,255));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 10, 10);

        //iCook Logo
        try{
            img = ImageIO.read(new File("iCook_Logo(125).png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //Frame attributes/components
        ImageIcon logo = new ImageIcon(img);
        JLabel pic = new JLabel();
        pic.setIcon(logo);

        JLabel welcome = new JLabel("Hi " + serviceDispatcher.getUserName() + "!"); //Welcome label
        welcome.setFont(new Font("Century Gothic", Font.PLAIN, 60)); //Size, Color, etc
        welcome.setForeground(new Color(51,51,51));

        constraints.gridx = 4;
        constraints.gridy = 0;
        homepanel.add(welcome, constraints);

        constraints.gridx = 5;
        constraints.gridy = 0;
        homepanel.add(pic, constraints);

        // Buttons
        JButton manageBtn = new JButton("Manage");
        manageBtn.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        manageBtn.setPreferredSize(new Dimension(144,35));
        manageBtn.setForeground(new Color(255,255,255));
        manageBtn.setBackground(new Color(28, 31, 46));
        manageBtn.setFocusPainted(false);
        manageBtn.setBorder(emptyBorder);

        manageBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                manageBtn.setForeground(new Color(255,255,255));
                manageBtn.setBackground(new Color(68, 166, 154));
            }
            public void mouseExited(MouseEvent e){
                manageBtn.setForeground(new Color(255,255,255));
                manageBtn.setBackground(new Color(28, 31, 46));
            }
        });

        /*
        Admin button will be displayed for certain users
         */
        if (serviceDispatcher.isUserAdmin()) {
            constraints.gridx = 4;
            constraints.gridy = 1;
            manageBtn.setVisible(true);
            homepanel.add(buttonPanel.add(manageBtn), constraints);
        }

        JButton myInventory = new JButton("Inventory");
        myInventory.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        myInventory.setPreferredSize(new Dimension(144,35));
        myInventory.setForeground(new Color(255,255,255));
        myInventory.setBackground(new Color(28, 31, 46));
        myInventory.setFocusPainted(false);
        myInventory.setBorder(emptyBorder);

        myInventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                myInventory.setForeground(new Color(255,255,255));
                myInventory.setBackground(new Color(68, 166, 154));
            }
            public void mouseExited(MouseEvent e){
                myInventory.setForeground(new Color(255,255,255));
                myInventory.setBackground(new Color(28, 31, 46));
            }
        });

        constraints.gridx = 4;
        constraints.gridy = 2;
        homepanel.add(buttonPanel.add(myInventory), constraints);

        JButton recipes = new JButton("Recipes");
        recipes.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        recipes.setPreferredSize(new Dimension(144,35));
        recipes.setForeground(new Color(255,255,255));
        recipes.setBackground(new Color(28, 31, 46));
        recipes.setFocusPainted(false);
        recipes.setBorder(emptyBorder);

        recipes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                recipes.setForeground(new Color(255,255,255));
                recipes.setBackground(new Color(68, 166, 154));
            }
            public void mouseExited(MouseEvent e){
                recipes.setForeground(new Color(255,255,255));
                recipes.setBackground(new Color(28, 31, 46));
            }
        });

        constraints.gridx = 4;
        constraints.gridy = 3;
        homepanel.add(buttonPanel.add(recipes), constraints);

        JButton logout = new JButton("Logout");
        logout.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        logout.setPreferredSize(new Dimension(144,35));
        logout.setForeground(new Color(255,255,255));
        logout.setBackground(new Color(28, 31, 46));
        logout.setFocusPainted(false);
        logout.setBorder(emptyBorder);

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setForeground(new Color(255,255,255));
                logout.setBackground(new Color(248, 68, 149));
            }
            public void mouseExited(MouseEvent e){
                logout.setForeground(new Color(255,255,255));
                logout.setBackground(new Color(28, 31, 46));
            }
        });

        constraints.gridx = 4;
        constraints.gridy = 4;
        homepanel.add(buttonPanel.add(logout), constraints);

        manageBtn.addActionListener(this);
        recipes.addActionListener(this);
        myInventory.addActionListener(this);
        logout.addActionListener(this);

        homepanel.setBackground(new Color(255,255,255));
        this.add(homepanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String btn = e.getActionCommand();

        //User goes to view all recipes if search is clicked, otherwise they view their inventory
        //and add, update, or delete ingredients

        if (btn.equals("Logout")) {
            // log the user out of their account && take them back to WelcomeUI
            serviceDispatcher.logUserOut();
            serviceDispatcher.gotoWelcome(); //WelcomeUI with JPanel & JFrame as parameters
        }
        // take the user to RecipeUI
        else if (btn.equals("Recipes")) {
            serviceDispatcher.gotoViewRecipes();
        }
        else if (btn.equals("Manage")) {
            serviceDispatcher.gotoManageRecipesUI();
        }
        // take the user to InventoryUI
        else if (btn.equals("Inventory")){
            serviceDispatcher.gotoInventory();
        }
    }


} // end of HomeUI
