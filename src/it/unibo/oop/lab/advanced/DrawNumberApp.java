package it.unibo.oop.lab.advanced;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final DrawNumberView view;

    /**
     * 
     */
    public DrawNumberApp() {

        final Configuration.Builder configurationbuilder = new Configuration.Builder();
        try (Scanner sc = new Scanner(ClassLoader.getSystemResourceAsStream("config.yml"))) {
            while (sc.hasNextLine()) {
                final String[] lines = sc.nextLine().trim().split(":");
                try (Scanner num = new Scanner(lines[1])) {
                    if (lines[0].contains("min")) {
                        configurationbuilder.setMin(num.nextInt());
                    } else if (lines[0].contains("max")) {
                        configurationbuilder.setMax(num.nextInt());
                    } else if (lines[0].contains("attemp")) {
                        configurationbuilder.setAttempts(num.nextInt());
                    } else {
                        System.out.println("couldn't get configuration files");
                    }
                }

            }

        }
        final Configuration config = configurationbuilder.build();

        this.model = new DrawNumberImpl(config.getMin(), config.getMax(), config.getAttempts());
        this.view = new DrawNumberViewImpl();
        this.view.setObserver(this);
        this.view.start();
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.view.result(result);
        } catch (IllegalArgumentException e) {
            this.view.numberIncorrect();
        } catch (AttemptsLimitReachedException e) {
            view.limitsReached();
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *                 ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();

    }
}
