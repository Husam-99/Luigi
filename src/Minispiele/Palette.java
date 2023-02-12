package Minispiele;



public class Palette {
    public int weltX;
    public int weltY;
    public Palette naechsteRechts;
    public Palette naechsteLinks;
    public int paletteNummer;
    boolean hatFalle;

    public Palette(int x, int y, int p, boolean f) {
        this.weltX = x;
        this.weltY = y;
        this.paletteNummer = p;
        this.hatFalle = f;
    }
}
