import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.io.BufferedWriter;
import java.util.TreeMap;

public class CSVWriter {
    public void exportStats(String filename, TreeMap<String,Song> songMap) {
        File file = new File(filename);
        File parentDirectory = file.getParentFile();

        if (!filename.endsWith(".csv")) {
            System.err.print("Error: Output file: " + filename + " does not have '.csv' extension!");
            return;
        }
        if (parentDirectory != null && !parentDirectory.exists()) {
            System.err.println("Error: Output file directory: " + filename + " not found!");
            return;
        }

        List<Song> sortedSongs = new ArrayList<>(songMap.values());
        Collections.sort(sortedSongs);
    
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("song,number of ratings,mean,standard deviation\n");

            for (Song song: sortedSongs) {
                writer.write(song.toString());
            }
        }
            catch (IOException e) {
                System.err.println("Error: Exporting to file was not successful - " + e.getMessage());    
        }
    }

    public void exportSimilarity(String filename, TreeMap<String,TreeMap<String, Double>> similarityMap) {
        File file = new File(filename);
        File parentDirectory = file.getParentFile();

        if (!filename.endsWith(".csv")) {
            System.err.print("Error: Output file: " + filename + " does not have '.csv' extension!");
            return;
        }
        if (parentDirectory != null && !parentDirectory.exists()) {
            System.err.println("Error: Output file directory: " + filename + " not found!");
            return;
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("name1,name2,similarity\n");

            for (Map.Entry<String, TreeMap<String,Double>> entry: similarityMap.entrySet()) {
                String user1 = entry.getKey();

                for (Map.Entry<String, Double> innerEntry: entry.getValue().entrySet()) {
                    String user2 = innerEntry.getKey();
                    Double similarityScore = innerEntry.getValue();


                if (similarityScore == null ) {
                    writer.write(user1 + "," + user2 + ",NaN\n");
                }
                else {
                    writer.write(user1 + "," + user2 + "," + similarityScore + "\n");

                }
 
                }
            }
        }
            
            catch (IOException e) {
                System.err.println("Error: Exporting to file was not successful - " + e.getMessage());    
        }
    }
}
