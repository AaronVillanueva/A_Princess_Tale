package itesm.mx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Pantalla {
    public static final int ANCHO = 1280;
    public static final int ALTO = 720;
    protected OrthographicCamera camara; //cámara
    protected Viewport vista;
    protected SpriteBatch batch;
    private int posX = 0;
    protected Texture textFondo;

}
