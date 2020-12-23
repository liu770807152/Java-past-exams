package comp1110.exam;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q5CarTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(2000);

    public static final int HASH_ITERATIONS = 100;

    static Q5Car[] cars = new Q5Car[200 * HASH_ITERATIONS];

    @Test
    public void testHashCodeDeterministic() {
        checkNotObjectHashCode();
        for (Q5Car car : cars) {
            int hash = car.hashCode();
            for (int i = 0; i < 10; i++) {
                int hash2 = car.hashCode();
                assertTrue("car " + car + "  returned different values for hashCode(): " + hash + ", " + hash2, hash == hash2);
            }
        }
    }

    @Test
    public void testAllFields() {
        checkNotObjectHashCode();
        Q5Car car1 = new Q5Car("Toyota", "Corolla", 2018);
        Q5Car car2 = new Q5Car("Toyota", "Corolla", 2011);
        testDifferent(car1, car2);
        testDifferent(car1, car2);
        car2 = new Q5Car("Corolla", "Toyota", 2018);
        testDifferent(car1, car2);
        testDifferent(car1, car2);
        car2 = new Q5Car("Ford", "Corolla", 2018);
        testDifferent(car1, car2);
        testDifferent(car1, car2);
        car2 = new Q5Car("Toyota", "Camry", 2018);
        testDifferent(car1, car2);
        testDifferent(car1, car2);
        car2 = new Q5Car("Toyota", "Hilux", 2018);
        testDifferent(car1, car2);
        testDifferent(car1, car2);
    }

    @Test
    public void testEquals() {
        Q5Car car1 = new Q5Car("Toyota", "Corolla", 2018);
        assertTrue("car1.equals(car1) returned false", car1.equals(car1));
        Q5Car car2 = new Q5Car("Toyota", "Corolla", 2011);
        assertFalse("cars " + car1 + " and " + car2 + " are not equal, but car1.equals(car2) returned true", car1.equals(car2));
        car2.make = "Ford";
        car2.year = 2018;
        assertFalse("cars " + car1 + " and " + car2 + " are not equal, but car1.equals(car2) returned true", car1.equals(car2));
        car2.make = "Toyota";
        car2.model = "Hilux";
        assertFalse("cars " + car1 + " and " + car2 + " are not equal, but car1.equals(car2) returned true", car1.equals(car2));
        car2.model = null;
        assertFalse("cars " + car1 + " and " + car2 + " are not equal, but car1.equals(car2) returned true", car1.equals(car2));
        car1.model = null;
        assertTrue("cars " + car1 + " and " + car2 + " are equal, but car1.equals(car2) returned false", car1.equals(car2));
        car2.model = "Corolla";
        assertFalse("cars " + car1 + " and " + car2 + " are not equal, but car1.equals(car2) returned true", car1.equals(car2));
        car1.model = "Corolla";
        assertTrue("cars " + car1 + " and " + car2 + " are equal, but car1.equals(car2) returned false", car1.equals(car2));
    }

    @Test
    public void testUniformA() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=10-1, prob=0.999) ~= 27.88
        testUniformity(10, 27.88);
    }

    @Test
    public void testUniformB() {
        checkNotObjectHashCode();
        // chiSquared critical value (DOF=50-1, prob=0.999) ~= 85.35
        testUniformity(50, 85.35);
    }

    private void testUniformity(int buckets, double chiSqCriticalValue) {
        Random r = new Random();
        int[] count = new int[buckets];
        int samples = buckets * HASH_ITERATIONS;
        for (int i = 0; i < samples; i++) {
            Q5Car car = cars[r.nextInt(cars.length)];
            int h = Math.abs(car.hashCode()) % buckets;
            count[h]++;
        }

        double chiSq = chiSquaredUniform(samples, count);
        assertTrue("Distribution of hash function doesn't appear to be uniform over " + buckets + " buckets (chi squared value of " + chiSq + ").\nExpected " + samples / buckets + " elements per bucket, but got " + Arrays.toString(count), chiSq < chiSqCriticalValue);
    }

    private void checkNotObjectHashCode() {
        Random r = new Random();
        int range = 39;
        Consumer<Function<Q5Car, Integer>> checkHash = (Function<Q5Car, Integer> hashFunction) -> {
            Set<Q5Car>[] myBuckets = new Set[range];
            Set<Q5Car>[] defaultBuckets = new Set[range];
            for (int i = 0; i < range; i++) {
                myBuckets[i] = new HashSet<>();
                defaultBuckets[i] = new HashSet<>();
            }
            for (int i = 0; i < 98 * HASH_ITERATIONS; i++) {
                Q5Car car = cars[r.nextInt(cars.length)];
                int m = Math.abs(car.hashCode()) % range;
                myBuckets[m].add(car);
                int n = Math.abs(hashFunction.apply(car)) % range;
                defaultBuckets[n].add(car);
            }
            for (Set<Q5Car> myBucket : myBuckets) {
                for (Set<Q5Car> defaultBucket : defaultBuckets) {
                    assertFalse("It looks like you're using Object.hashCode() or Objects.hash()!", myBucket.equals(defaultBucket));
                }
            }
        };

        checkHash.accept((Q5Car car) -> car.passThroughHash());
        checkHash.accept((Q5Car car) -> Objects.hash(car.make, car.model, car.year));
        checkHash.accept((Q5Car car) -> Objects.hash(car.make, car.year, car.model));
        checkHash.accept((Q5Car car) -> Objects.hash(car.model, car.year, car.make));
        checkHash.accept((Q5Car car) -> Objects.hash(car.model, car.make, car.year));
        checkHash.accept((Q5Car car) -> Objects.hash(car.year, car.make, car.model));
        checkHash.accept((Q5Car car) -> Objects.hash(car.year, car.model, car.make));

    }

    private void testDifferent(Q5Car car1, Q5Car car2) {
        int hash1 = car1.hashCode();
        int hash2 = car2.hashCode();
        assertTrue("cars " + car1 + " and " + car2 + " returned same hashCode(): " + hash1 + ", " + hash2, hash1 != hash2);
    }

    @BeforeClass
    public static void generateCars() {
        Random r = new Random(11);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Q5Car(generateRandomName(r), generateRandomName(r), r.nextInt(50) + 1970);
        }
    }

    static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};

    private static String generateRandomName(Random r) {
        char[] randomName = new char[r.nextInt(6) + 3];
        for (int c = 0; c < randomName.length; c++) {
            if (c == 0) {
                randomName[c] = (char) (r.nextInt(90 - 65) + 'A');
            } else if (c % 2 == 1) {
                randomName[c] = VOWELS[r.nextInt(VOWELS.length)];
            } else {
                randomName[c] = (char) (r.nextInt(123 - 97) + 'a');
            }
        }
        return String.valueOf(randomName);
    }

    private static double chiSquaredUniform(int samples, int[] counts) {
        double uniformProb = 1.0 / counts.length;
        double total = 0;
        for (int count : counts) {
            double mi = ((double) samples) * uniformProb;
            total += ((double) count - mi) * ((double) count - mi) / mi;
        }
        return total;
    }
}
