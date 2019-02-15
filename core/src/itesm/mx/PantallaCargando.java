package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PantallaCargando extends Pantalla implements Screen{
    private int posX = 0;

    //Tiempo
    private float contadorTiempo =0;


    public PantallaCargando(Principal principal) {
        this.principal=principal;
    }

    @Override
    public void show() {
        inicializarCamaraBatch();
        textFondo = new Texture("Pantallas/PantallaCargando.PNG");
    }

    @Override
    public void render(float delta) {
//
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        contadorTiempo+=delta;
        if(contadorTiempo>=2){
            principal.setScreen(new PantallaMenu(principal));
        }
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
    //PantallaCargando ya hace dispose de su batch y textFondo
    //@Override
    //public void dispose() { }
}
