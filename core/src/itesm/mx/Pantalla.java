package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//Pantalla genérica
public abstract class Pantalla implements Screen {
    public static final int ANCHO = 1280;
    public static final int ALTO = 720;
    public OrthographicCamera camara; //cámara
    protected Viewport vista;
    protected SpriteBatch batch;
    protected Texture textFondo;
    protected Principal principal;
    public Sprite nube;

    public void dispose(){
        batch.dispose();
        textFondo.dispose();
    }
    //Debe ser colocado en show
    public void inicializarNube1(float x,float y, float t){
        Texture textNube=new Texture("Pantallas/Nube1.png");
        TextureRegion regionNube=new TextureRegion(textNube);
        nube=new Sprite(regionNube);
        nube.setPosition(x,y);
        nube.setAlpha(t);
    }
    //Debe ser colocado despues de batch.begin() y siempre y cuando inicializar nube exista
    public void movernube(SpriteBatch batch,float vel,float x){
        nube.setX(nube.getX()-vel);
        if(nube.getX()+nube.getWidth()<0){
            nube.setX(x);
        }
        nube.draw(batch);
    }

    //Este metodo recibe una string (InternalPath) para agregar el fondo a la Pantalla
    //Las convenciones al día 15 de Febrero son "Pantalla/Pantalla####.png"
    public void crearFondo(String fondo){

        textFondo = new Texture(fondo);
    }

    //Este metodo inicializa la camara, batch y la vista
    public void inicializarShow(){
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO/2, ALTO/2, 0);
        camara.update();
        //Vista
        vista = new StretchViewport(ANCHO, ALTO,camara);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(new Pantalla.ProcesadorEntrada());
    }


    public ImageButton crearImgBtn(String path){
        Texture textBtn=new Texture(path);
        TextureRegionDrawable trdBtn=new TextureRegionDrawable(new TextureRegion(textBtn));
        ImageButton btn=new ImageButton(trdBtn);
        return btn;
    }

    //metodo super raro de prueba ree
    public void ree(){
        System.out.println("reeeee1");
    }

    //La clase pantallacargando tiene por defecto el boton atras implementado para PantallaMenu
    //cualquier otra pantalla que tenga un boton adicional/menos hace override a esta clase
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

            if(v3.y<=ALTO-20 && v3.y>=ALTO-160 && v3.x<=195 && v3.x>=20){
                //Quiere jugar
                principal.setScreen( new PantallaMenu(principal));
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
}
