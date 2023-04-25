import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaGUIFrame extends JFrame {

    JPanel mainPnl;
    JPanel controlPnl;
    JPanel radioPnl;
    JPanel checkPnl;
    JPanel displayPnl;
    JPanel comboPnl;
    JPanel buttonPnl;

    JComboBox sizeCombo;

    JCheckBox pepperoni;
    JCheckBox sausage;
    JCheckBox eyes;
    JCheckBox brains;
    JCheckBox spinnach;
    JCheckBox ham;

    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepDish;

    JButton quitBtn;
    JButton orderBtn;
    JButton clearBtn;

    JScrollPane scrollPane;

    JTextArea displayArea;
    ButtonGroup group;

    public PizzaGUIFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 3));

        createDisplayArea();
        createComboPanel();
        createCheckPanel();
        createRadioPanel();
        createButtonPanel();


       controlPnl.add(comboPnl);
       controlPnl.add(checkPnl);
       controlPnl.add(radioPnl);

        mainPnl.add(controlPnl, BorderLayout.NORTH);
        mainPnl.add(displayPnl, BorderLayout.CENTER);
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);

        add(mainPnl);
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createDisplayArea()
    {
        displayPnl = new JPanel();
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        scrollPane = new JScrollPane(displayArea);
        displayPnl.add(scrollPane);
    }
    private void createComboPanel()
    {
        comboPnl = new JPanel();
        comboPnl.setBorder(new TitledBorder(new EtchedBorder(),"Pizza Size"));

        sizeCombo = new JComboBox();
        sizeCombo.addItem("Small");
        sizeCombo.addItem("Medium");
        sizeCombo.addItem("Large");
        sizeCombo.addItem("Super");
        comboPnl.add(sizeCombo);
    }

    private void createCheckPanel()
    {
        checkPnl = new JPanel();
        checkPnl.setBorder(new TitledBorder(new EtchedBorder(),"Select Toppings"));

        pepperoni = new JCheckBox("Pepperoni");
        sausage = new JCheckBox("Sausage");
        eyes = new JCheckBox("Eyes");
        brains = new JCheckBox("Brains");
        spinnach = new JCheckBox("Spinnach");
        ham = new JCheckBox("Ham");

        checkPnl.setLayout(new BoxLayout(checkPnl, BoxLayout.PAGE_AXIS));
        checkPnl.add(pepperoni);
        checkPnl.add(sausage);
        checkPnl.add(eyes);
        checkPnl.add(brains);
        checkPnl.add(spinnach);
        checkPnl.add(ham);
    }

    private void createRadioPanel()
    {
        radioPnl = new JPanel();
        radioPnl.setBorder(new TitledBorder(new EtchedBorder(),"Select Crust Type"));
        thin = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deepDish = new JRadioButton("Deep Dish");

        radioPnl.add(thin);
        radioPnl.add(regular);
        radioPnl.add(deepDish);

        group = new ButtonGroup();
        group.add(thin);
        group.add(regular);
        group.add(deepDish);
    }

    private void createButtonPanel()
    {
        buttonPnl = new JPanel();
        buttonPnl.setBorder(new TitledBorder(new EtchedBorder(),"Actions"));

        clearBtn = new JButton("Clear");
        orderBtn = new JButton("Place Order");
        quitBtn = new JButton("Quit");

        buttonPnl.add(clearBtn);
        buttonPnl.add(orderBtn);
        buttonPnl.add(quitBtn);

        clearBtn.addActionListener((ActionEvent ae) -> clearData());
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        orderBtn.addActionListener((ActionEvent ae) -> printOrder());


    }

    private void printOrder() {
        String crustType = "Thin";
        if (thin.isSelected())
        {
            crustType = "Thin";
        }
        if (regular.isSelected())
        {
            crustType = "Regular";
        }
        if (deepDish.isSelected())
        {
            crustType = "Deep Dish";
        }

        String pizzaSize = "Small";
        double sizePrice = 0;
        pizzaSize = sizeCombo.getSelectedItem().toString();
        if (pizzaSize.equals("Small"))
        {
            sizePrice = 8;
        }
        if (pizzaSize.equals("Medium"))
        {
            sizePrice = 12;
        }
        if (pizzaSize.equals("Large"))
        {
            sizePrice = 16;
        }
        if (pizzaSize.equals("Super"))
        {
            sizePrice = 20;
        }

        String toppings = "";
        double toppingsPrice = 0;

        if (pepperoni.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Pepperoni \n";
        }
        if (sausage.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Sausage \n";
        }
        if (eyes.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Eyes \n";
        }
        if (pepperoni.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Eyes \n";
        }
        if (brains.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Brains \n";
        }
        if (spinnach.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Spinnach \n";
        }
        if (ham.isSelected())
        {
            toppingsPrice += 1;
            toppings += "Ham \n";
        }

        double tax=(.07*(toppingsPrice + sizePrice));

        String receipt =
                "=========================================\n" +
                        pizzaSize + " " + crustType + " Pizza \t\t\t $"  + sizePrice + "\n\n"
                        + "Toppings: \t\t\t\t $" + toppingsPrice + "\n" + toppings + "\n"
                        + "Sub-total: \t\t\t\t $" + (toppingsPrice + sizePrice) + "\n\n" + "Tax: \t\t\t\t $" + Math.round(tax*100.00)/100.00 + "\n\n" +
                        "-----------------------------------------------------\n" +
                        "Total: \t\t\t\t $" + Math.round((toppingsPrice + sizePrice+tax)*100.00)/100.00 + "\n" +
                        "=========================================";



        displayArea.setText(receipt);

    }
    private void clearData() {

        group.clearSelection();
        pepperoni.setSelected(false);

        pepperoni.setSelected(false);
        sausage.setSelected(false);
        eyes.setSelected(false);
        brains.setSelected(false);
        spinnach.setSelected(false);
        ham.setSelected(false);


        displayArea.setText("");
    }

}