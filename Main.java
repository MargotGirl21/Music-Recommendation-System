import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.err.println("Error: Incorrect number of arguments provided. Excepted 2 arguments: input.csv output.csv ");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];
        String similarityCue = (args.length == 3) ? args[2] : "";

        if (!inputFile.endsWith(".csv")) {
                System.err.println("Error: input file: " + inputFile + " does not have '.csv' extension!");
                return;
            }

        CSVWriter writer = new CSVWriter();
        CSVReader reader = new CSVReader();

        if (args.length == 2) {
            TreeMap<String,Song> songMap = reader.readCSVData(inputFile);
            writer.exportStats(outputFile, songMap);
        }
        else if (args.length == 3 && similarityCue.equals("-u")) {
            reader.readCSVData(inputFile);
            reader.processUsers();

            if (reader.getUserMap().size() < 2) {
                System.err.println("Error: at least two cooperative users are required for user similarity.");
                return;
            }

            TreeMap<String, TreeMap<String, Double>> similarityMap = SimilarityCalculator.calculateSimilarity(reader.getUserMap(), reader.getSongNames());
            writer.exportSimilarity(outputFile, similarityMap);

        }
        else {
            System.err.println("Error: unsupported argument " + args[2]);
            return;
        }
    }
 }
