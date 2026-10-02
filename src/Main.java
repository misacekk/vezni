import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Vezen> platniVezni = new ArrayList<>();
        VezenValidator validator = new VezenValidator();
        String cestaKeSouboru = "vezni_testovaci_data.txt";

        System.out.println("=== načítání a validace vězňů ===");

        try (BufferedReader br = new BufferedReader(new FileReader(cestaKeSouboru))) {
            String radek;
            int cisloRadku = 1;

            while ((radek = br.readLine()) != null) {
                System.out.println("[Řádek " + cisloRadku + "] " + radek);

                if (validator.jePlatnyRadek(radek)) {
                    System.out.println("VÝSLEDEK: VALIDNÍ");

                    String[] udaje = radek.split(";");
                    Vezen vezen = new Vezen(
                            udaje[0].trim(),
                            udaje[1].trim(),
                            udaje[2].trim(),
                            udaje[3].trim(),
                            udaje[4].trim(),
                            udaje[5].trim(),
                            udaje[6].trim(),
                            udaje[7].trim()
                    );
                    platniVezni.add(vezen);
                } else {
                    System.out.println("=> VÝSLEDEK: NEVALIDNÍ");
                }
                cisloRadku++;
            }

        } catch (IOException e) {
            System.out.println("Chyba při čtení souboru: " + e.getMessage());
        }

        System.out.println("=================================");
        System.out.println("SEZNAM PLATNÝCH VĚZŇŮ (Příjmení + Rok narození):");
        System.out.println("=================================");
        for (Vezen v : platniVezni) {
            System.out.println("Příjmení: " + v.getPrijmeni() + " | Rok narození: " + v.getRokNarozeni());
        }
    }
}