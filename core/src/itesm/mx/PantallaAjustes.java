package itesm.mx;

import com.badlogic.gdx.Screen;

public class PantallaAjustes extends Pantalla implements Screen {

    public PantallaAjustes(Principal principal){this.principal=principal;}

    @Override
    public void show() {
        inicializarShow();
        crearFondo("Pantallas/Pantalla_Configuracion.png");
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
}
