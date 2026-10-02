import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VezenValidator {

    private static final String REGEX_JMENO = "^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-záčďéěíňóřšťúůýž]+\\s[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-záčďéěíňóřšťúůýž]+$";
    private static final String REGEX_TELEFON = "^\\+420\\s\\d{3}\\s\\d{3}\\s\\d{3}$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_MESTO_ULICE = "^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-zA-ZáčďéěíňóřšťúůýžÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ\\s]*$";
    private static final String REGEX_CISLO_POPISNE = "^[1-9]\\d*$";
    private static final String REGEX_PSC = "^\\d{3}\\s\\d{2}$";

    public static List<String> zkontrolujRadek(String radek) {
        List<String> chyby = new ArrayList<>();

        String[] prvky = radek.split(";", -1);

        if (prvky.length != 8) {
            chyby.add("špatný počet údajů na řádku");
            return chyby;
        }

        String jmenoPrijmeni = prvky[0].trim();
        String datumStr = prvky[1].trim();
        String telefon = prvky[2].trim();
        String email = prvky[3].trim();
        String mesto = prvky[4].trim();
        String ulice = prvky[5].trim();
        String cisloPopisneStr = prvky[6].trim();
        String psc = prvky[7].trim();

        if (!jmenoPrijmeni.matches(REGEX_JMENO)) {
            chyby.add("neplatné jméno a příjmení");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate d = LocalDate.parse(datumStr, formatter);

            if (!d.format(formatter).equals(datumStr)) {
                chyby.add("neplatné datum narození");
            }
        } catch (Exception e) {
            chyby.add("neplatné datum narození");
        }

        if (!telefon.matches(REGEX_TELEFON)) {
            chyby.add("neplatný telefon");
        }

        if (!email.matches(REGEX_EMAIL)) {
            chyby.add("neplatný e-mail");
        }

        if (!mesto.matches(REGEX_MESTO_ULICE)) {
            chyby.add("neplatné město");
        }

        if (!ulice.matches(REGEX_MESTO_ULICE)) {
            chyby.add("neplatná ulice");
        }

        if (!cisloPopisneStr.matches(REGEX_CISLO_POPISNE)) {
            chyby.add("neplatné číslo popisné");
        }

        if (!psc.matches(REGEX_PSC)) {
            chyby.add("neplatné PSČ");
        }

        return chyby;
    }

    public static Vezen vytvorVezne(String radek) {
        String[] prvky = radek.split(";");
        String[] jmenoAPrijmeni = prvky[0].trim().split(" ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        LocalDate datum = LocalDate.parse(prvky[1].trim(), formatter);
        int cisloPopisne = Integer.parseInt(prvky[6].trim());

        return new Vezen(
                jmenoAPrijmeni[0],
                jmenoAPrijmeni[1],
                datum,
                prvky[2].trim(),
                prvky[3].trim(),
                prvky[4].trim(),
                prvky[5].trim(),
                cisloPopisne,
                prvky[7].trim()
        );
    }
}