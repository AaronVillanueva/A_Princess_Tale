package itesm.mx;

public class Estrella extends Item{

    public Estrella(float x, float y, Personaje princesa) {
        super(x, y, "Star.png", princesa);
        sprite.setPosition(x, y);
    }

    @Override
    public void efectoItem() {
        // Aquí duplicamos el poder de la princesa por n segundos

    }
}
