package itesm.mx;

import com.badlogic.gdx.Screen;

public class PantallaCreditos extends Pantalla implements Screen {
    public Nube nube1,nube2,nube3;

    public PantallaCreditos(Principal principal){this.principal=principal;}

    @Override
    public void show() {
        inicializarShow();
        nube1=new Nube(1,300,600,.8f);
        nube2=new Nube(1,650,550,.7f);
        nube3=new Nube(2,1200,450,.8f);
        crearFondo("Pantallas/Pantalla_Creditos.png");
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
