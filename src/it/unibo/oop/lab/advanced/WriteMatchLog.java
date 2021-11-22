package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class WriteMatchLog implements DrawNumberView {

    private final PrintStream ps;

    public WriteMatchLog(final PrintStream ps) {
        this.ps = ps;
    }

    public WriteMatchLog(final String path) throws FileNotFoundException {
        this.ps = new PrintStream(new FileOutputStream(new File(path)));
    }

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {

    }

    @Override
    public void start() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void numberIncorrect() {
        ps.println("Number is incorrect");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        ps.println(res.getDescription());

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void limitsReached() {
        ps.println("limitsReached");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
       ps.println("Error:" + message);
    }

}
