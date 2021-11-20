package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;
import it.unibo.oop.lab.mvcio.Observer;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface. Suggestion: use a second JPanel with a second
     * BorderLayout, put the panel in the North of the main panel, put the text
     * field in the center of the new panel and put the button in the line_end of
     * the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the current
     * selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should use
     * the method showSaveDialog() to display the file chooser, and if the result is
     * equal to JFileChooser.APPROVE_OPTION the program should set as new file in
     * the Controller the file chosen. If CANCEL_OPTION is returned, then the
     * program should do nothing. Otherwise, a message dialog should be shown
     * telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to update
     * the UI: in this example the UI knows when should be updated, so try to keep
     * things separated.
     */
    private final JFrame frame = new JFrame("My second java graphical interface");
    private final Observer ob = new Controller();
    private final JFileChooser fileChooser = new JFileChooser();

    public SimpleGUIWithFileChooser() {

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 2);

        frame.setLocationByPlatform(true);
        mainContents();
    }

    private void mainContents() {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JTextArea textarea = new JTextArea();
        textarea.setLayout(new BoxLayout(textarea, BoxLayout.Y_AXIS));
        textarea.setLineWrap(true);
        final JButton save = new JButton("save");
        save.setLayout(new BoxLayout(save, BoxLayout.Y_AXIS));

        final JTextField textfield = new JTextField(ob.getPath());
        final JButton browse = new JButton("Browse");
        final JPanel panel = new JPanel(new BorderLayout());

        textfield.setLayout(new BoxLayout(textfield, BoxLayout.LINE_AXIS));
        textfield.setEditable(false);
        browse.setLayout(new BoxLayout(browse, BoxLayout.LINE_AXIS));

        panel.add(textfield, BorderLayout.WEST);
        panel.add(browse, BorderLayout.EAST);
        canvas.add(panel, BorderLayout.NORTH);
        canvas.add(textarea, BorderLayout.CENTER);
        canvas.add(save, BorderLayout.SOUTH);
        frame.setContentPane(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final int res = fileChooser.showSaveDialog(panel);
                switch (res) {
                case JFileChooser.APPROVE_OPTION:

                    ob.setCurrentFile(fileChooser.getSelectedFile().toString());
                    textfield.setText(fileChooser.getSelectedFile().toString());
                    // System.out.println(fileChooser.getSelectedFile().toString());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(fileChooser, "Something went wrong");
                }
            }
        });

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {

                try {
                    ob.writeFiles(textarea.getText());
                    System.out.println("Writing to files.....");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }

}
