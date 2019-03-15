package itesm.mx;

public class Estrella extends Item{


    public Estrella(float x, float y) {
        super(x, y, "ITEMS/Star.png");
    }

    @Override
    public void generarEfecto(Personaje personaje) {
        // Aquí se modifica el atributo deseado del personaje
    }
}
