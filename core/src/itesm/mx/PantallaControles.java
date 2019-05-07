package itesm.mx;

import com.badlogic.gdx.Screen;

public class PantallaControles extends Pantalla implements Screen {

    public Nube nube1,nube2,nube3;

    public PantallaControles(Principal principal){this.principal=principal;}
    @Override
    public void show() {
        nube1=new Nube(1,300,400,.8f);
        nube2=new Nube(2,600,500,.8f);
        nube3=new Nube(1,900,600,.8f);
        inicializarShow();
        crearFondo("Pantallas/Pantalla_Controles.png");
        crearRegreso();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(cielo,0,0);
        nube1.draw(batch,6);
        nube2.draw(batch,3);
        nube3.draw(batch,4);
        batch.draw(textFondo, 0, 0);
        spriteRegresar.draw(batch);
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
