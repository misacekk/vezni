import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class VezenValidator {

    private Pattern patternJmeno = Pattern.compile("^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-záčďéěíňóřšťúůýž]+\\s+[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-záčďéěíňóřšťúůýž]+$");
    private Pattern patternTelefon = Pattern.compile("^\\+420 \\d{3} \\d{3} \\d{3}$");
    private Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private Pattern patternMestoUlice = Pattern.compile("^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-zA-Zá-žÁ-Ž\\s]*$");
    private Pattern patternCisloPopisne = Pattern.compile("^[1-9]\\d*$");
    private Pattern patternPsc = Pattern.compile("^\\d{3} \\d{2}$");

    public boolean jePlatnyRadek(String radek) {
        if (radek == null || radek.trim().isEmpty()) {
            System.out.println("Chyba: Prázdný řádek!");
            return false;
        }

        String[] udaje = radek.split(";", -1);

        if (udaje.length != 8) {
            System.out.println("Chyba: Špatný počet údajů (očakáváno 8, nalezeno " + udaje.length + ")");
            return false;
        }

        boolean jeVseOk = true;

        String jmeno = udaje[0].trim();
        String datum = udaje[1].trim();
        String telefon = udaje[2].trim();
        String email = udaje[3].trim();
        String mesto = udaje[4].trim();
        String ulice = udaje[5].trim();
        String cisloPopisne = udaje[6].trim();
        String psc = udaje[7].trim();

        if (!patternJmeno.matcher(jmeno).matches()) {
            System.out.println("Neplatné jméno a příjmení: " + jmeno);
            jeVseOk = false;
        }

        if (!datum.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$")) {
            System.out.println("Špatný formát data narození: " + datum);
            jeVseOk = false;
        } else {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
                LocalDate datumNarozeni = LocalDate.parse(datum, dtf);

                if (datumNarozeni.isAfter(LocalDate.now())) {
                    System.out.println("Datum narození nemůže být v budoucnosti: " + datum);
                    jeVseOk = false;
                }
            } catch (Exception e) {
                System.out.println("Neexistující datum v kalendáři: " + datum);
                jeVseOk = false;
            }
        }

        if (!patternTelefon.matcher(telefon).matches()) {
            System.out.println("Neplatný telefon: " + telefon);
            jeVseOk = false;
        }

        if (!patternEmail.matcher(email).matches()) {
            System.out.println("Neplatný e-mail: " + email);
            jeVseOk = false;
        }

        if (!patternMestoUlice.matcher(mesto).matches()) {
            System.out.println("Neplatné město: " + mesto);
            jeVseOk = false;
        }

        if (!patternMestoUlice.matcher(ulice).matches()) {
            System.out.println("Neplatná ulice: " + ulice);
            jeVseOk = false;
        }

        if (!patternCisloPopisne.matcher(cisloPopisne).matches()) {
            System.out.println("Neplatné číslo popisné: " + cisloPopisne);
            jeVseOk = false;
        }

        if (!patternPsc.matcher(psc).matches()) {
            System.out.println("Neplatné PSČ: " + psc);
            jeVseOk = false;
        }

        return jeVseOk;
    }
}