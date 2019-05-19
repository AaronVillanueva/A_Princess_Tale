package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;

class PantallaFinal extends Pantalla {
    float contadorTiempo = 0;
    public PantallaFinal(Principal principal) {
        this.principal = principal;
    }

    @Override
    public void show() {
        inicializarShow();
        crearFondo("Historia/WinHistoria.png");


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        contadorTiempo +=delta;
        if(contadorTiempo>=10){
            principal.setScreen(new PantallaMenu(principal, true));
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

    @Override
    public void dispose() {

    }
}
