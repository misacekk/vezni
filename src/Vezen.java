import java.time.LocalDate;

public class Vezen {
    private String jmeno;
    private String prijmeni;
    private LocalDate datumNarozeni;
    private String telefon;
    private String email;
    private String mesto;
    private String ulice;
    private int cisloPopisne;
    private String psc;

    public Vezen(String jmeno, String prijmeni, LocalDate datumNarozeni, String telefon,
                 String email, String mesto, String ulice, int cisloPopisne, String psc) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.datumNarozeni = datumNarozeni;
        this.telefon = telefon;
        this.email = email;
        this.mesto = mesto;
        this.ulice = ulice;
        this.cisloPopisne = cisloPopisne;
        this.psc = psc;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public LocalDate getDatumNarozeni() {
        return datumNarozeni;
    }

    public int getRokNarozeni() {
        return datumNarozeni.getYear();
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public String getMesto() {
        return mesto;
    }

    public String getUlice() {
        return ulice;
    }

    public int getCisloPopisne() {
        return cisloPopisne;
    }

    public String getPsc() {
        return psc;
    }
}