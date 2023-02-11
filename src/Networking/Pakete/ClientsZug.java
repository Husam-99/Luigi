package Networking.Pakete;

public class ClientsZug {
    // trägt die Infos für den Client, wenn am Zug ist.
    public boolean istDran;
    public int zustand = -1; // entspricht die Zustände die im SpielPanel liegen.
    public int minispielIndex = -1;
    public boolean ersteRunde = false;
    public boolean dispose = false;
}
