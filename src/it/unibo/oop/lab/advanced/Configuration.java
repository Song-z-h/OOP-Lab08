package it.unibo.oop.lab.advanced;

public final class Configuration {
    private final int min;
    private final int max;
    private final int attempts;

    public Configuration(final int min, final int max, final int attempts) {
        super();
        this.min = min;
        this.max = max;
        this.attempts = attempts;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getAttempts() {
        return attempts;
    }
    
    

    @Override
    public String toString() {
        return "Configuration [min=" + min + ", max=" + max + ", attempts=" + attempts + "]";
    }



    public static class Builder {
        // default values
        private static final int MIN = 0;
        private static final int MAX = 100;
        private static final int ATTEMPTS = 10;

        private int min = MIN;
        private int max = MAX;
        private int attempts = ATTEMPTS;

        private boolean consumed;

        /**
         * 
         * @param min
         *                minimum number
         * @return build
         */
        public Builder setMin(final int min) {
            this.min = min;
            return this;
        }

        /**
         * set max number.
         * 
         * @param max
         *                maximum number
         * @return build
         */
        public Builder setMax(final int max) {
            this.max = max;
            return this;
        }

        /**
         * set setAttempts.
         * 
         * @param attempts
         *                     attemptions to try the game
         * @return build
         */
        public Builder setAttempts(final int attempts) {
            this.attempts = attempts;
            return this;
        }

        /**
         * A function to build all necessary data.
         * 
         * @return the configuration data
         */
        public Configuration build() {
            if (consumed) {
                throw new IllegalStateException();
            } else {
                consumed = true;
                return new Configuration(min, max, attempts);
            }

        }
    }

}
