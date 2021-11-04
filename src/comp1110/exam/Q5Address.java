package comp1110.exam;

import java.util.Random;

/**
 * COMP1110 Final Exam, Question 5
 *
 * 5 Marks
 *
 * This class represents a street address within the Australian Capital Territory,
 * for example "108 North Road 2601".
 */
public class Q5Address {
    /**
     * The street number of the address (must be greater than zero) e.g. 108.
     */
    int streetNumber;

    /**
     * The street name is a string of ASCII characters e.g. "North Road"
     */
    String streetName;

    /**
     * The postcode is a number from 0 to 9999.
     */
    int postCode;

    public Q5Address(int streetNumber, String streetName, int postCode) {
        if (streetName == null) {
            throw new IllegalArgumentException("Street name may not be null!");
        }
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postCode = postCode;
    }

    /**
     * @return a hash code value for this object.
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (this.streetName != null) {
            return ((int) (Math.pow(this.streetName.charAt(0) + this.streetName.charAt(streetName.length() - 1), 3) +
                    (int) (Math.pow(this.streetNumber, 2)) +
                    this.postCode))
                    % 10000;
        } else {
            return ((int) (Math.pow(200, 3) +
                    (int) (Math.pow(this.streetNumber, 2)) +
                    this.postCode))
                    % 10000;
        }
    }

    /**
     * @return true if this address is equal to the provided object
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
        return streetNumber + " " + streetName + " " + postCode;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}
