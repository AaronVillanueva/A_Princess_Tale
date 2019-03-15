package itesm.mx;

public class Cereza extends Item {

    public Cereza(float x, float y) {
        super(x, y, "ITEMS/Cereza.png");
    }

    @Override
    public void generarEfecto(Personaje personaje) {
        // Aquí se modifica el atributo deseado del personaje
    }
}
