package it.unibo.oop.lab.advanced;

public class WriteMatchStdout implements DrawNumberView {

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
        System.out.println("Number is incorrect");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void result(final DrawResult res) {
        System.out.println(res.getDescription());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void limitsReached() {
        System.out.println("limitsReached");

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void displayError(final String message) {
        System.out.println("Error:" + message);

    }

}
