package it.unibo.oop.lab.mvc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.BorderLayout;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller;

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the
     * frame, a JTextArea in the center and two buttons below it: "Print", and
     * "Show history". SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the controller
     * is asked to show the string contained in the text field on standard output.
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    private void mainContent() {
        final JPanel canvas = new JPanel(new BorderLayout());
        final JTextField chatbox = new JTextField();
        final JTextArea textarea = new JTextArea("Enter something");
        final JPanel panel = new JPanel();
        final JButton print = new JButton("Print");
        final JButton showHisotry = new JButton("show hisotry");
        final JButton clear = new JButton("clear");
        // config
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        textarea.setBackground(Color.lightGray);
        textarea.setLineWrap(true);

        // actionListeners
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.setNextString(textarea.getText());
                controller.printString();
                chatbox.setText(controller.getNextString());
                textarea.setText("");
            }

        });
        showHisotry.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final List<String> history = controller.getHistory();
                textarea.setText("");
                for (final var line : history) {
                    textarea.append(line + "\n");
                }
            }

        });

        clear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textarea.setText("");
            }
        });

        // final assemble
        panel.add(print);
        panel.add(showHisotry);
        panel.add(clear);
        canvas.add(chatbox, BorderLayout.NORTH);
        canvas.add(textarea, BorderLayout.CENTER);
        canvas.add(panel, BorderLayout.SOUTH);
        frame.setContentPane(canvas);

        // frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SimpleGUI() {
        this(new ControllerImpl());
    }

    /**
     * builds a new {@link SimpleGUI}.
     * 
     * @param control
     *                    the controller to handle events
     */
    public SimpleGUI(final Controller control) {

        /*
         * Make the frame half the resolution of the screen. This very method is enough
         * for a single screen setup. In case of multiple monitors, the primary is
         * selected.
         * 
         * In order to deal coherently with multimonitor setups, other facilities exist
         * (see the Java documentation about this issue). It is MUCH better than
         * manually specify the size of a window in pixel: it takes into account the
         * current resolution.
         */
        controller = control;
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 2);

        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this flag
         * makes the OS window manager take care of the default positioning on screen.
         * Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        mainContent();
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUI().display();
    }

}
