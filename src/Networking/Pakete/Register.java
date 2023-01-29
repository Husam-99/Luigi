package Networking.Pakete;



import com.esotericsoftware.kryo.Kryo;

public class Register {
    public static void register(Kryo k){
        k.register(AnzahlClients.class);
        k.register(Bescheid.class);
        k.register(ClientsZug.class);
        k.register(HostClient.class);
        k.register(SpielfigurAuswahl.class);
        k.register(AnzahlMitspieler.class);
        k.register(Rundenzahl.class);
        k.register(SpielerPosition.class);
        k.register(Sternzahl.class);
        k.register(Muenzenzahl.class);
        k.register(SternPosition.class);
        k.register(SternKaufen.class);
        k.register(Bewegung.class);
        k.register(Schritte.class);
        k.register(GegenstandInfo.class);
        k.register(Blocken.class);
        k.register(SammlerGegenstaende.class);
        k.register(SammlerPunkte.class);
        k.register(SquidGamePunkte.class);
        k.register(SquidGamePosition.class);
        k.register(PalettenFalle.class);




    }
}
