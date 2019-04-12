package itesm.mx;

public class Cereza extends Item {

    public Cereza(float x, float y) {
        super(x, y, "ITEMS/Cereza.png");
    }

    @Override
    public void generarEfecto(Elya elya) {
        // Aquí se modifica el atributo deseado del personaje
        if(elya.getVidas()<3 && elya.getVidas()>0){
            elya.actualizarVidas(1);
        }

    }
}
