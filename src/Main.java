import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String soubor = "vezni_testovaci_data.txt";
        List<Vezen> platniVezni = new ArrayList<>();

        System.out.println("=== VALIDACE ÚDAJŮ O VĚZŇÍCH ===");

        try (BufferedReader br = new BufferedReader(new FileReader(soubor, StandardCharsets.UTF_8))) {
            String radek;
            int cisloRadku = 1;

            while ((radek = br.readLine()) != null) {
                if (radek.trim().isEmpty()) {
                    cisloRadku++;
                    continue;
                }

                List<String> chyby = VezenValidator.zkontrolujRadek(radek);

                if (chyby.isEmpty()) {
                    System.out.println("Řádek " + cisloRadku + ": VALIDNÍ");
                    Vezen v = VezenValidator.vytvorVezne(radek);
                    platniVezni.add(v);
                } else {
                    System.out.println("Řádek " + cisloRadku + ": NEVALIDNÍ -> " + String.join(", ", chyby));
                }

                cisloRadku++;
            }
        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }

        System.out.println("=== SEZNAM PLATNĚ ZADANÝCH VĚZŇŮ ===");
        for (Vezen v : platniVezni) {
            System.out.println("Příjmení: " + v.getPrijmeni() + " | Rok narození: " + v.getRokNarozeni());
        }
    }
}