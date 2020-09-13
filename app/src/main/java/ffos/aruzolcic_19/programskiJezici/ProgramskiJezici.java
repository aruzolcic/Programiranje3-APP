package ffos.aruzolcic_19.programskiJezici;

public class ProgramskiJezici {


    private int sifra;
    private String naziv;
    private String tvorac;
    private String programskaParadigma;
    private String opis;

    public ProgramskiJezici(int sifra, String naziv, String tvorac, String programskaParadigma, String opis) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.tvorac = tvorac;
        this.programskaParadigma = programskaParadigma;
        this.opis = opis;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTvorac() {
        return tvorac;
    }

    public void setTvorac(String tvorac) {
        this.tvorac = tvorac;
    }

    public String getProgramskaParadigma() {
        return programskaParadigma;
    }

    public void setProgramskaParadigma(String programskaParadigma) {
        this.programskaParadigma = programskaParadigma;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
