package itesm.mx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaMenu extends Pantalla implements Screen {

    private int posX = 0;

    public PantallaMenu(Principal principal){this.principal=principal;}

    @Override
    public void show() {
        inicializarCamaraBatch();
        textFondo = new Texture("Pantallas/PantallaMenu.PNG");
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(textFondo, 0, 0);
        batch.end();
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

    //@Override
    //public void dispose() {}
}
