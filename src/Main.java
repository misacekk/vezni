import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        Path vezni = Path.of("data", "vezni.txt");

        for(String line : Files.readAllLines(vezni)){
            System.out.println(line);
        }

    }
}