package it.unibo.oop.lab.mvc;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ControllerImpl implements Controller {

    private final List<String> texts = new LinkedList<>();

    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     */
    @Override
    public void setNextString() throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            final String text = sc.nextLine();
            if (text != null) {
                texts.add(text);
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        return ((LinkedList<String>) texts).getLast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return List.copyOf(texts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printString() {
        if (texts.isEmpty()) {
            throw new IllegalStateException("The current String is unset");
        } else {
            System.out.println(getNextString());
        }

    }

}
