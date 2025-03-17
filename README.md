🎵 Song Stats

Song Stats is a Java-based music analysis tool that processes and analyzes song data stored in CSV files. It enables users to compare songs, analyze similarities, and manage music data efficiently.

🎮 Features

CSV Data Management: Read and write song data using CSV files.

Song Similarity Calculation: Compare songs based on their attributes.

User Data Tracking: Store and analyze user interactions with songs.

🔧 Technologies Used

Language: Java

Data Storage: CSV files

Core Components:

CSVReader.java - Reads song data from CSV files.

CSVWriter.java - Writes processed data to CSV files.

SimilarityCalculator.java - Computes similarity between songs.

Song.java - Represents song attributes.

User.java - Manages user-related song data.

🚀 How to Run

Clone the repository:

git clone https://github.com/yourusername/Song-Stats.git

Navigate to the project folder:

cd Song-Stats

Compile and run the program:

javac Main.java

Calculating similarity: java Main <input.csv> <output.csv> <-u>
Calculating standard deviation: java Main <input.csv> <output.csv> 

📌 Future Enhancements

Integration with streaming APIs to fetch real-time music data.

GUI implementation for better user interaction.

Advanced similarity algorithms to improve recommendations.

📬 Contributing

Interested in contributing? Feel free to fork the repository and submit a pull request!

📜 License

This project is open-source and available under the MIT License.

Enjoy analyzing music with Song Stats! 🎶📊
