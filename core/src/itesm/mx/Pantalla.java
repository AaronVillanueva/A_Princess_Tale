package itesm.mx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Pantalla {
    public static final int ANCHO = 1280;
    public static final int ALTO = 720;
    protected OrthographicCamera camara; //cámara
    protected Viewport vista;
    protected SpriteBatch batch;
    protected Texture textFondo;
    protected Principal principal;

    public void dispose(){
        batch.dispose();
        textFondo.dispose();
    }
    public void inicializarCamaraBatch(){
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO/2, ALTO/2, 0);
        camara.update();
        //Vista
        vista = new StretchViewport(ANCHO, ALTO);
        batch = new SpriteBatch();
    }
}
