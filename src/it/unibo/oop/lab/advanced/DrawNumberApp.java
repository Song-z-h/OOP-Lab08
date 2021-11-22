package it.unibo.oop.lab.advanced;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param configfile
     *                       the file name which contains configuration data
     * @param views
     *                       all the GUI needed to start the program
     */
    public DrawNumberApp(final String configfile, final DrawNumberView... views) {

        final Configuration.Builder configurationbuilder = new Configuration.Builder();
        try (Scanner sc = new Scanner(ClassLoader.getSystemResourceAsStream(configfile))) {
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
            final Configuration config = configurationbuilder.build();

            this.model = new DrawNumberImpl(config.getMin(), config.getMax(), config.getAttempts());
            this.views = List.of(Arrays.copyOf(views, views.length));

            for (final var view : views) {
                view.setObserver(this);
                view.start();
            }
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final var view : views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final var view : views) {
                view.numberIncorrect();
            }
        } catch (AttemptsLimitReachedException e) {
            for (final var view : views) {
                view.limitsReached();
            }
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
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp("config.yml",
                new DrawNumberViewImpl(),
                new DrawNumberViewImpl(),
                new WriteMatchLog("output.log.deleteme"),
                new WriteMatchStdout());

    }
}
