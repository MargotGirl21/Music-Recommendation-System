import java.util.ArrayList;
import java.util.List;

public class Song implements Comparable<Song> {
    private String songName;
    private List<Integer> ratings;
    List<Double> normalizedRating = new ArrayList<>();
    double mean;
    double stdDev;
    double zScore;

    public Song(String songName) {
        this.songName = songName;
        this.ratings = new ArrayList<>();
    }

    public void setSong(String songName) {
        this.songName = songName;
    }

    public String getSong() {
        return songName;
    }

    public int getRatingCount() {
        return ratings.size();
    }

    public void addRatings(int rating) {
        this.ratings.add(rating);
    }

    public double getMean() {
        double sum = 0;
        if (ratings.isEmpty()) {
            return 0.0;
        }
        for (double r: ratings) {
            sum += r;
        }
        mean = sum/ratings.size();
        return mean;
    }

    public double getStandardDeviation() {
        int n = ratings.size();

        if (n < 2) {
            return Double.NaN;
        }
        double varianceSum = 0;

        for (double r: ratings) {
            varianceSum += Math.pow(r - mean, 2);      
        }
        double variance = varianceSum/(n);
        stdDev = Math.sqrt(variance);
        return stdDev;
    }

    public double getZScore() {
        double zScore = 0;
        for (int r: ratings) {
            zScore = (r - getMean()) / getStandardDeviation();      
        }
        return zScore;
    }

    @Override
    public String toString() {
        return songName + "," + getRatingCount() + "," + getMean() + "," + getStandardDeviation() + "\n";
    }

    @Override
    public int compareTo(Song other) {
        return this.songName.compareToIgnoreCase(other.songName);
    }

 }