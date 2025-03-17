# Music Recommendation System – CSV Data & Euclidean Distance Calculation

Overview

This project processes a CSV file containing user song ratings, normalizes the ratings, and calculates the Euclidean distance between users to determine similarity in music preferences. It utilizes:

CSV reading and parsing for structured data processing
Data normalization to ensure fair comparisons between users
Euclidean distance metric to measure similarity between users' song preferences
TreeMaps (Java’s TreeMap<K, V>) for efficient data storage and retrieval


How It Works
1. Reading the CSV File
The program reads a CSV file containing user IDs, song titles, and corresponding ratings. A CSV file format might look like this:

UserID, Song, Rating  
101, "Bohemian Rhapsody", 4.5  
102, "Bohemian Rhapsody", 3.8  
101, "Stairway to Heaven", 5.0  
102, "Stairway to Heaven", 4.2  

The data is stored using Java's built-in CSV reading methods (e.g., BufferedReader or Scanner) and mapped into a TreeMap<String, TreeMap<String, Double>> structure:

Outer TreeMap (User ID as Key): Efficiently organizes users in ascending order.
Inner TreeMap (Song Title as Key, Rating as Value): Stores ratings per user in a structured format.


2. Normalizing the Ratings
To ensure fair comparisons between users with different rating tendencies, the ratings are normalized by:

Finding the mean rating per user
Adjusting each rating by subtracting the user’s mean rating
Resulting values represent a user's relative preference rather than absolute scores


3. Calculating Euclidean Distance
To compare user preferences, we compute the Euclidean distance between users:

Only songs rated by both users are considered
The squared difference of ratings for each shared song is summed
The square root of this sum gives the final distance score, where:
Lower values indicate more similar taste
Higher values indicate less similarity
​
 
4. Applications & Future Improvements
Music Recommendations: Identify users with similar preferences and suggest new songs.
Scalability: Expand to larger datasets with more efficient storage techniques.
Alternative Similarity Measures: Implement Pearson correlation for improved accuracy.


Getting Started
Requirements
Java (JDK 8 or higher)
A CSV file formatted as UserID, Song, Rating
