package itesm.mx;

import com.badlogic.gdx.Screen;

public class PantallaCreditos extends Pantalla implements Screen {

    public PantallaCreditos(Principal principal){this.principal=principal;}

    @Override
    public void show() {
        inicializarShow();
        inicializarNube1(1600,600,.8f);
        crearFondo("Pantallas/Pantalla_CreditosSinNubes.png");
    }
//
    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(textFondo, 0, 0);
        movernube(batch,5,1600);
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
