package comp1110.exam;

import java.util.Random;

/**
 * COMP1110 Final Exam, Question 5
 *
 * 5 Marks
 *
 * This class represents a type of car, which is uniquely identified by a
 * combination of make, model, and year, for example: "Hyundai i30 2019".
 */
public class Q5Car {
    /**
     * The make (manufacturer) of this car e.g. "Toyota".
     */
    String make;

    /**
     * The model name of this car e.g. "Corolla".
     */
    String model;

    /**
     * The model year (version) of this car e.g. "2018".
     */
    int year;

    public Q5Car(String make, String model, int year) {
        if (make == null) {
            throw new IllegalArgumentException("make may not be null!");
        }
        this.make = make;
        if (model == null) {
            throw new IllegalArgumentException("model may not be null!");
        }
        this.model = model;
        this.year = year;
    }

    /**
     * @return a hash code value for this car
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (this.make != null && this.model != null) {
            return ((int) (Math.pow(this.make.charAt(0) + this.make.charAt(make.length() - 1), 3) +
                    (int) (Math.pow(this.model.charAt(this.model.length() - 1), 2)) +
                    this.year))
                    % 10000;
        } else if (this.make == null) {
            return ((int) (Math.pow(200, 3) +
                    (int) (Math.pow(this.model.charAt(this.model.length() - 1), 2)) +
                    this.year))
                    % 10000;
        } else if (this.model == null) {
            return ((int) (Math.pow(this.make.charAt(0) + this.make.charAt(make.length() - 1), 3) +
                    (int) (Math.pow(200, 2)) +
                    this.year))
                    % 10000;
        } else {
            return this.year;
        }
    }

    /**
     * @return true if this car is equal to the provided object
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if (object != null) {
            return this.hashCode() == object.hashCode();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return make + " " + model + " " + year;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}
