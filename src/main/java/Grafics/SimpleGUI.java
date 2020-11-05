package Grafics;

import DB.WorkDb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SimpleGUI extends JFrame {
    private JButton button = new JButton("Add");
    private  JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Nazva,Avtor,Klass:");
    private JButton radio1 = new JButton("Search");
    private JButton radio2 = new JButton("Delete");
    private JButton all = new JButton("Show all");
    private JRadioButton check = new JRadioButton("Search or delete by id");
    private JRadioButton fi = new JRadioButton("Search or Delete by Nazva");
    private JRadioButton num = new JRadioButton("Search or Delete by Avtor");
    private JRadioButton ope = new JRadioButton("Search or Delete by Klass");
    private final String username = "root";
    private final String password = "1234";
    private final String connectionurl = "jdbc:mysql://localhost:3306/mydb";
    private final WorkDb workDb;

    public SimpleGUI(WorkDb workDb) {
        super("Book DataBase");
        this.workDb = workDb;
        this.setBounds(300, 300, 450, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5, 2, 2, 2));
        container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        group.add(check);
        group.add(fi);
        group.add(num);
        group.add(ope);
       // container.add(radio1);

      //radio1.setSelected(true);
       // container.add(radio2);
        container.add(all);
        container.add(check);
        container.add(fi);
        container.add(num);
        container.add(ope);
        button.addActionListener(new ButtonEventListener());
        radio1.addActionListener(new ListenerSearch());
        radio2.addActionListener(new ListenerRemove());
        all.addActionListener(new ListenerAll());
        container.add(button);
        container.add(radio1);
        container.add(radio2);
        container.add(all);

    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message = "Add successful ";
            String[] array = input.getText().split(",");
            try {
                workDb.addBook(array);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Output",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    class ListenerSearch implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)  {
           String message = null;
                if (check.isSelected()) {
                    try {
                        message = String.join("\n", workDb.findBook(input.getText(), "id"));
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }
                }
                if (fi.isSelected()) {
                    try {
                        message = String.join("\n", workDb.findBook(input.getText(), "Nazva_predmeta"));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if (num.isSelected()) {
                    try {
                        message = String.join("\n", workDb.findBook(input.getText(), "Avtor"));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                if (ope.isSelected()) {
                    try {
                        message = String.join("\n", workDb.findBook(input.getText(), "Klas"));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                } else {
                    message = " choose one option for this move";
                }
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        }

    class ListenerRemove implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = "Delete Successful";
            if (check.isSelected()) {
                try {
                    workDb.deleteBook(input.getText(), "id");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (fi.isSelected()) {
                try {
                    workDb.deleteBook(input.getText(), "Nazva_predmeta");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            }
            if (num.isSelected()) {
                try {
                    workDb.deleteBook(input.getText(), "Avtor");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (ope.isSelected()) {
                try {
                    workDb.deleteBook(input.getText(), "Klas");
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            } else {
                message = "choose one option for this move";
            }
            JOptionPane.showMessageDialog(null, message,
                    "Output",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    class ListenerAll implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String message = null;
            try {
                message = String.join("\n", workDb.seeBook());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }

    }

}

