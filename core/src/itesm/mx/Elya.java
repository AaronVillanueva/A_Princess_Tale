package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Elya extends Personaje {
    private int vidas = 3;
    private PersonajeEstado estado;
    private int poder=1;


    public Elya() {

        estado=PersonajeEstado.caminandoNormal;
        cargarText("Personajes/ElyaRunning2.png", 24, 1);
        cargarText("Personajes/ElyaAtack.png", 22, 2);
        cargarText("Personajes/ElyaDead.png", 1, 3);

    }

    public void actualizarVidas(int dVidas) {
        this.vidas += dVidas;
    }

    public int getVidas() {
        return vidas;
    }
    public void setPoder(int poder){
        this.poder=poder;
    }
    public int getPoder(){
        return poder;
    }

}

