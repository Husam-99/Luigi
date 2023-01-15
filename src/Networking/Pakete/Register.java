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

    }
}
