package Project3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RickRoll {
    public static void main(String[] args) {
        try {
            // Yeh URL ek ASCII Rick Astley art text file ka hai 😏
            URL url = new URL("https://raw.githubusercontent.com/denilsonsa/prettyping/master/ansi-colors-demo.txt");
            // ↑ tu chahe to apna custom Rickroll ASCII text bhi host kar sakta hai GitHub pe

            // Connection open karte h
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Response read karte h line by line
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(20); // Thoda delay, animation feel ke liye
            }

            reader.close();
            conn.disconnect();

            System.out.println("\n🎵 Never gonna give you up, never gonna let you down... 🎶");
            System.out.println("Rickrolled via Java 😎");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
