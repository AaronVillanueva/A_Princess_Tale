package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;

public class PantallaHistoria extends Pantalla {
    float contadorTiempo = 0;

    public PantallaHistoria(Principal principal){
        this.principal = principal;
    }

    @Override
    public void show() {
        inicializarShow();
        crearFondo("Historia/Historia1.png");

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        contadorTiempo += delta;
        checarImagenFondo();
        checarCambioPantalla();
        batch.begin();
        batch.draw(textFondo, 0,0);
        batch.end();

    }

    private void checarCambioPantalla() {
        if(contadorTiempo>45){
            principal.setScreen(new PantallaMenu(principal));
        }
    }

    public void checarImagenFondo(){
        if(contadorTiempo>=10 && contadorTiempo<15){
            crearFondo("Historia/Historia2.1.png");
        }
        else if(contadorTiempo>=15 && contadorTiempo<20){
            crearFondo("Historia/Historia2.2.png");
        }
        else if(contadorTiempo>=20 && contadorTiempo<30){
            crearFondo("Historia/Historia3.png");
        }
        else if(contadorTiempo>=30 && contadorTiempo<35){
            crearFondo("Historia/Historia4.png");
        }
        else if(contadorTiempo>=35 && contadorTiempo<40){
            crearFondo("Historia/Historia5.png");
        }
        else if(contadorTiempo>=40){
            crearFondo("Historia/Historia6.png");
        }

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
