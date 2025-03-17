import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashSet;

public class User implements Comparable<User> {
    private final String username;
    private final TreeMap<String, Double> ratings;
    private final TreeMap<String, Double> normalizedRatings;
    double mean;
    double stdDev;

    public User(String username) {
        this.username = username; 
        this.ratings = new TreeMap<>();
        this.normalizedRatings = new TreeMap<>();
    }

    public String getUserName() {
        return username;
    }

    public void addRatings(String songName, double rating) {
        ratings.put(songName, rating);

    }

    public TreeMap<String, Double> getRatings() {
        return ratings;
    }

    public int getUniqueRatings() {
        return new HashSet<>(ratings.values()).size();
    }


    public double getMean() {
        double sum = 0;
        if (ratings.isEmpty()) {
            return 0.0;
        }
        
        for (double r: ratings.values()){
            sum += r;
        }
        mean = sum/ratings.size();
        return mean;
    }


    @Override
    public int compareTo(User other) {
        return this.username.compareToIgnoreCase(other.username);
    }

    public double getStandardDeviation() {
        int n = ratings.size();

        if (n < 2) {
            return 0.0;
        }
        double varianceSum = 0;
        double mean = getMean();

        for (double r: ratings.values()) {
            varianceSum += Math.pow(r - mean, 2);      
        }
        double variance = varianceSum/(n);
        stdDev = Math.sqrt(variance);
        return stdDev;
    }


     public void normalizeRating() {
        mean = getMean();
        stdDev = getStandardDeviation();

        if (ratings.size() < 2) return;

        if (stdDev == 0) return;

        for (Map.Entry<String, Double> entry: ratings.entrySet()) {
            normalizedRatings.put(entry.getKey(), (entry.getValue() - mean)/stdDev);

        }
    }

    public TreeMap<String, Double> getNormalizedRatings() {
        return normalizedRatings;
    }

    public ArrayList<Double> getNormalizedRatingsAsList(ArrayList<String> songNames) {
        ArrayList<Double> ratingsList = new ArrayList<>(Collections.nCopies(songNames.size(),Double.NaN));

        for (int i = 0; i < songNames.size(); i++) {
            String songName = songNames.get(i);
            if (ratings.containsKey(songName)) {
                ratingsList.set(i, normalizedRatings.get(songName));
            }
        }
        return ratingsList;

        }
        
    }
    


