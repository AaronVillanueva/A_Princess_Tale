package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import java.awt.Image;

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
        inicializarBotones();
    }

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
        //escenaMenu.draw();
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
