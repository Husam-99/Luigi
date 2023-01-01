package Networking.Pakete;



import com.esotericsoftware.kryo.Kryo;

public class Register {
    public static void register(Kryo k){
        k.register(AnzahlClients.class);
        k.register(BescheidSagen.class);
        k.register(ClientsZug.class);
        k.register(HostClient.class);


    }
}
