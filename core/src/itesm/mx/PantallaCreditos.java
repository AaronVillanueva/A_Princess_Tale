package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;

public class PantallaCreditos extends Pantalla implements Screen {
    public Nube nube1,nube2,nube3;
    public boolean playMusica;

    public PantallaCreditos(Principal principal, boolean playMusica){
        this.principal=principal;
        this.playMusica = playMusica;}

    @Override
    public void show() {
        inicializarShow();
        nube1=new Nube(1,300,600,.8f);
        nube2=new Nube(1,650,550,.7f);
        nube3=new Nube(2,1200,450,.8f);
        crearFondo("Pantallas/Pantalla_Creditos.png");
        crearRegreso();
    }
//
    @Override
    public void render(float delta) {

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(cielo,0,0);
        nube1.draw(batch,5);
        nube2.draw(batch,7);
        nube3.draw(batch,4);
        batch.draw(textFondo, 0, 0);
        spriteRegresar.draw(batch);
        batch.end();
        checarRegreso();
    }

    private void checarRegreso() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            principal.setScreen(new PantallaMenu(principal, playMusica));
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
