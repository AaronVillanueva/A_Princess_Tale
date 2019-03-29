package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Elya extends Personaje {
    private int vidas = 3;
    private PersonajeEstado estado = PersonajeEstado.caminandoNormal;

    public Elya() {
        cargarText("Personajes/ElyaRunning1.png", 22, 1);
        cargarText("Personajes/ElyaAtack.png", 22, 2);
        cargarText("Personajes/ElyaDead.png", 1, 3);

    }

    public void actualizarVidas(int dVidas) {
        this.vidas += dVidas;
    }

    public int getVidas() {
        return vidas;
    }

}

