package itesm.mx;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//Pantalla genérica
public abstract class Pantalla {
    public static final int ANCHO = 1280;
    public static final int ALTO = 720;
    protected OrthographicCamera camara; //cámara
    protected Viewport vista;
    protected SpriteBatch batch;
    protected Texture textFondo;
    protected Principal principal;

    public void dispose(){
        batch.dispose();
        textFondo.dispose();
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
    }


    public ImageButton crearImgBtn(String path){
        Texture textBtn=new Texture(path);
        TextureRegionDrawable trdBtn=new TextureRegionDrawable(new TextureRegion(textBtn));
        ImageButton btn=new ImageButton(trdBtn);
        return btn;
    }

}
