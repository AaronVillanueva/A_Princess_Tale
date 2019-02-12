package itesm.mx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaCargando implements Screen{

    public static final int ANCHO = 1280;
    public static final int ALTO = 720;
    private final Principal principal;
    private OrthographicCamera camara; //cámara
    private Viewport vista;
    private SpriteBatch batch;
    public PantallaCargando(Principal principal) {
        this.principal=principal;
    }

    @Override
    public void show() {
//
    }

    @Override
    public void render(float delta) {
//
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
