package Networking.Pakete;



import com.esotericsoftware.kryo.Kryo;

public class Register {
    public static void register(Kryo k){
        k.register(SpielerAuskuenfte.class);
        k.register(GeklickteKarte.class);


    }
}
