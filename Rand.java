import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class Rand {
    private static SecureRandom rand;

    static {new Rand();}

    public Rand() {
        try {
            rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException nsaEx) {
            nsaEx.printStackTrace();
            byte[] seed = SecureRandom.getSeed(128);
            rand = new SecureRandom(seed);
        } catch (NoSuchProviderException nspEx) {
            nspEx.printStackTrace();
            byte[] seed = SecureRandom.getSeed(128);
            rand = new SecureRandom(seed);
        }
    }

    public Rand(byte[] seed) {
        rand = new SecureRandom(seed);
    }

    public static int randInt(int max) {
        return rand.nextInt(max);
    }

    public static int randInt(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    // Simulate Gaussian distribution using Box-Muller transform
    public static double randGauss(double mean, double stddev) {
        double u1 = rand.nextDouble();
        double u2 = rand.nextDouble();
        double r = Math.sqrt(-2 * Math.log(u1));
        double theta = 2 * Math.PI * u2;
        return mean + stddev * r * Math.cos(theta);
    }

    public static void main(String[] args) {
        Rand rand = new Rand();
        for (int i = 0; i < 20; i++) {
            System.out.println(rand.randInt(5));
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.println(rand.randInt(1, 6));
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.println(rand.randGauss(100, 10));
        }
    }
}
