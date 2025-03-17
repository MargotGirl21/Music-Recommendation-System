
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class CSVReader {
    private TreeMap<String, Song> songMap = new TreeMap<>();
    private TreeMap<String, User> userMap = new TreeMap<>();
    private ArrayList<String> songNames = new ArrayList<>();


    public TreeMap<String, Song> readCSVData (String file) {
        File filePath = new File(file);

       if (!filePath.exists()) {
        System.err.println("Error: Output file directory: " + filePath + " not found!");
        System.exit(1);
    }

       if (filePath.length() == 0) {
        System.err.println("Error: Input file " + file + " is an empty file!");
        System.exit(1);
    }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        String delimeter = ",";

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                System.err.println("Error: File contains an empty line!");
                System.exit(1);
        }

        String[] data = line.split(delimeter);

        if (data.length != 3) {
            System.err.println("Error: Entry must have exactly 3 fields: Song, Username, Rating!");
            System.exit(1);
        }

        String songName = data[0].trim();
        String userName = data[1].trim();
        String ratingString = data[2].trim();


        if (!ratingString.matches("\\d+")){
            System.err.print("Error: rating for " + userName + " is not a valid integer value!");
            System.exit(1);
        }

        int ratingNum = Integer.parseInt(ratingString);

        try {
            if (!songNames.contains(songName)) {
                songNames.add(songName);
            }
            userMap.putIfAbsent(userName, new User(userName));
            userMap.get(userName).addRatings(songName, ratingNum);
            
            

            if (ratingNum < 1 || ratingNum > 5) {
                System.err.println("Error: Rating for " + userName + " is out of the range of 1-5!");
                System.exit(1);
                } 

            }
            catch (NumberFormatException e) {
                System.err.println("Error: Rating for " + userName + " is not an integer value!" );
                System.exit(1);
                }


            songMap.putIfAbsent(songName, new Song(songName));
            songMap.get(songName).addRatings(ratingNum);
        
                }
            }

            catch (IOException e) {
                System.err.print("Error reading CSV File" + e.getMessage());
                System.exit(1);
            }

        return songMap;
    }


 
    public void processUsers() {
        userMap.values().removeIf(user -> user.getUniqueRatings() < 2);

        for (User user: userMap.values()) {
            user.normalizeRating();
        }
    }

   
    public TreeMap<String, User> getUserMap() {
        return userMap;
    }

  
    public ArrayList<String> getSongNames() {
        return songNames;
    }


}







