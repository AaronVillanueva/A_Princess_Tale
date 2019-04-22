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
    private Nube nube1,nube2,nube3;

    public PantallaCargando(Principal principal) {
        this.principal=principal;
    }

    @Override
    public void show() {
        nube1=new Nube(2,1300,500,.75f);
        nube2=new Nube(1,700,600,.7f);
        nube3=new Nube(2,300,400,.8f);
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO/2, ALTO/2, 0);
        camara.update();
        //Vista
        vista = new StretchViewport(ANCHO, ALTO);
        batch = new SpriteBatch();
        crearFondo("Pantallas/Pantalla_CargandoSinElya.PNG");
//
    }

    @Override
    public void render(float delta) {
//
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(cielo,0,0);
        nube1.draw(batch,4);
        nube2.draw(batch,3);
        nube3.draw(batch,5);
        batch.draw(textFondo, 0, 0);
        batch.end();

        //prueba tiempo
        contadorTiempo += delta;
        if(contadorTiempo>=2){
            //Contó 2 segundos
            principal.setScreen(new PantallaMenu(principal));
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

    @Override
    public void dispose() {

    }
}
