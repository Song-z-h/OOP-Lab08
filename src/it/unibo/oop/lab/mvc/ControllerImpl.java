package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private final List<String> texts = new LinkedList<>();

    /**
     * {@inheritDoc}
     * 
     * @throws IOException
     */
    @Override
    public void setNextString(final String text) {
        texts.add(Objects.requireNonNull(text, "this should not be null"));
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
