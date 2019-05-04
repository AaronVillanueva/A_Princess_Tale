package itesm.mx;

public class Keeper2 extends Keeper1 {
    public Keeper2() {
        vidas = 7;
        cargarText("Personajes/Keeper2Running.png", 24,1);
        setPos(Pantalla.ANCHO+300, Pantalla.ALTO/2-205);
    }
}
