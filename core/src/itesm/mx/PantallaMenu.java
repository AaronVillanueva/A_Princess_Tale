package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import java.awt.Image;
import java.util.Vector;

public class PantallaMenu extends Pantalla implements Screen {

    private int posX = 0;
    private Stage escenaMenu;
    private ImageButton btnJugar;

    public PantallaMenu(Principal principal){this.principal=principal;}

    @Override
    public void show() {
        //inicializa camara,camara update,batch y vista
        inicializarShow();
        crearFondo("Pantallas/PantallaMenu.PNG");
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    //método temporal dispuesto a mejoras y corrección de bug transparencia
    private void inicializarBotones() {
        escenaMenu=new Stage(vista);
        btnJugar=crearImgBtn("Botones/BotonJugar.png");
        btnJugar.setPosition(300,200);
        escenaMenu.addActor(btnJugar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    private class ProcesadorEntrada implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 v3 = new Vector3(screenX, screenY,0);
            camara.unproject(v3);
            //Verifica botón jugar

            if(v3.y<=ALTO-100 && v3.y>=ALTO-290 && v3.x<=860 && v3.x>=400){
                //Quiere jugar
                principal.setScreen( new PantallaCargando(principal));
            }
            if(v3.y<=ALTO-315 && v3.y>=ALTO-450 && v3.x<=570 && v3.x>=415){

                principal.setScreen( new PantallaAjustes(principal));
            }
            if(v3.y<=ALTO-315 && v3.y>=ALTO-450 && v3.x<=730 && v3.x>=575){

                principal.setScreen( new PantallaControles(principal));
            }
            if(v3.y<=ALTO-315 && v3.y>=ALTO-450 && v3.x<=870 && v3.x>=745){

                principal.setScreen( new PantallaAyuda(principal));
            }



            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

    //@Override
    //public void dispose() {}
}
