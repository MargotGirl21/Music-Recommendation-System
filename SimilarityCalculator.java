import org.apache;
import org.apache.commons.math4.legacy.ml.distance.EuclideanDistance;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class SimilarityCalculator {

    public static TreeMap<String, TreeMap<String,Double>> calculateSimilarity(TreeMap<String,User> userMap, ArrayList<String> songNames) {
        TreeMap<String, TreeMap<String, Double>> similarityMap = new TreeMap<>();
        EuclideanDistance distance = new EuclideanDistance();

        List<String> userList = new ArrayList<>(userMap.keySet());
        
        for (int i = 0; i < userList.size(); i++) {
            for (int j = i + 1; j < userList.size(); j++) {
                String user1 = userList.get(i);
                String user2 = userList.get(j);
                
                ArrayList<Double> ratings1 = userMap.get(user1).getNormalizedRatingsAsList(songNames);
                ArrayList<Double> ratings2 = userMap.get(user2).getNormalizedRatingsAsList(songNames);
                boolean hasValidRatings = false;

                List<Double> filteredRatings1 = new ArrayList<>();
                List<Double> filteredRatings2 = new ArrayList<>();


                for (int loop = 0; loop < ratings1.size(); loop++) { 
                    double value1 = ratings1.get(loop);
                    double value2 = ratings2.get(loop);

                    if (!Double.isNaN(value1) && !Double.isNaN(value2)) {
                        filteredRatings1.add(value1);
                        filteredRatings2.add(value2);
                        hasValidRatings = true;
                    }

                    else if (Double.isNaN(value1) && Double.isNaN(value2)) {
                        similarityMap.computeIfAbsent(user1, k -> new TreeMap<>()).put(user2, null);

                    }
                }

                if (!hasValidRatings) {
                    similarityMap.computeIfAbsent(user1, k -> new TreeMap<>()).put(user2, Double.NaN);
                    continue;
                }
               

               
                double[] user1Doubles = filteredRatings1.stream().mapToDouble(Double::doubleValue).toArray();
                double[] user2Doubles = filteredRatings2.stream().mapToDouble(Double::doubleValue).toArray();


                double euDistance = distance.compute(user1Doubles, user2Doubles);

                similarityMap.computeIfAbsent(user1, k -> new TreeMap<>()).put(user2, euDistance);

            }
        }
        return similarityMap;

    }
    
}
