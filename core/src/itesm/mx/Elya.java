package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Elya extends Personaje {
    private int vidas = 3;
    private PersonajeEstado estado;
    private int poder=1;
    private boolean saltando = false;
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    public static final float VELOCIDAD_X = 2;     // Velocidad horizontal

    // SALTO del personaje
    private static final float V0 = 40;     // Velocidad inicial del salto
    private static final float G = 9.81f;
    private static final float G_2 = G/2;   // Gravedad
    private float yInicial;         // 'y' donde inicia el salto
    private float tiempoVuelo;       // Tiempo que estará en el aire
    private float tiempoSalto;      // Tiempo actual de vuelo

    public Elya() {

        estado=PersonajeEstado.caminandoNormal;
        cargarText("Personajes/ElyaRunning2.png", 24, 1);
        cargarText("Personajes/ElyaAtack.png", 22, 2);
        cargarText("Personajes/ElyaDead.png", 1, 3);

    }

    public void setSaltando(boolean saltando){
        this.saltando = saltando;
    }

    public boolean getSaltando(){
        return saltando;
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

    public void update(float delta){
            if(saltando){

            }

        }
    }


