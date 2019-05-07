package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Pantalla_Niveles extends Pantalla {

    private Stage stage;
    Nube nube1,nube2;

    public Pantalla_Niveles(Principal principal){
        this.principal=principal;
    }

    @Override
    public void show() {
        nube1=new Nube(1);
        nube2=new Nube(2,500,500,.6f);
        //inicializa camara,camara update,batch y vista
        inicializarShow();

        stage = new Stage(vista);
        crearBotones();
        crearFondo("Pantallas/Pantalla_SeleccionaNivel.png");
        Gdx.input.setInputProcessor(stage);
    }

    private void crearBotones() {
        // Botón Nivel1
        Texture texturaBtnNivel1 = new Texture("Botones/Btn_Niveles/Btn_Nivel1.png");
        TextureRegion textureRegionBtnNivel1 = new TextureRegion(texturaBtnNivel1);
        TextureRegionDrawable textureRegionDrawableBtnNivel1 = new TextureRegionDrawable(textureRegionBtnNivel1);
        ImageButton btnNivel1 = new ImageButton(textureRegionDrawableBtnNivel1);
        btnNivel1.setPosition(ANCHO/2-120, ALTO/2+70);

        stage.addActor(btnNivel1);

        btnNivel1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new NivelGenerico(principal, 1));
            }
        });

        // Botón Nivel2
        Texture texturaBtnNivel2 = new Texture("Botones/Btn_Niveles/Btn_Nivel2.png");
        TextureRegion textureRegionBtnNivel2 = new TextureRegion(texturaBtnNivel2);
        TextureRegionDrawable textureRegionDrawableBtnNivel2 = new TextureRegionDrawable(textureRegionBtnNivel2);
        ImageButton btnNivel2 = new ImageButton(textureRegionDrawableBtnNivel2);
        btnNivel2.setPosition(ANCHO/2-120, ALTO/2-30);

        stage.addActor(btnNivel2);

        btnNivel2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new NivelGenerico(principal, 2));
            }
        });

        // Botón Nivel3
        Texture texturaBtnNivel3 = new Texture("Botones/Btn_Niveles/Btn_Nivel3.png");
        TextureRegion textureRegionBtnNivel3 = new TextureRegion(texturaBtnNivel3);
        TextureRegionDrawable textureRegionDrawableBtnNivel3 = new TextureRegionDrawable(textureRegionBtnNivel3);
        ImageButton btnNivel3 = new ImageButton(textureRegionDrawableBtnNivel3);
        btnNivel3.setPosition(ANCHO/2-120, ALTO/2-130);

        stage.addActor(btnNivel3);

        btnNivel3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new NivelGenerico(principal, 3));
            }
        });

        // Botón Nivel4
        Texture texturaBtnNivel4 = new Texture("Botones/Btn_Niveles/Btn_Nivel4.png");
        TextureRegion textureRegionBtnNivel4 = new TextureRegion(texturaBtnNivel4);
        TextureRegionDrawable textureRegionDrawableBtnNivel4 = new TextureRegionDrawable(textureRegionBtnNivel4);
        ImageButton btnNivel4 = new ImageButton(textureRegionDrawableBtnNivel4);
        btnNivel4.setPosition(ANCHO/2-120, ALTO/2-230);

        stage.addActor(btnNivel4);

        btnNivel4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new NivelGenerico(principal, 4));
            }
        });



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);

        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(cielo,0,0);

        nube1.draw(batch,5);
        nube2.draw(batch,10);

        batch.draw(textFondo, 0, 0);


        stage.act(Gdx.graphics.getDeltaTime());
        batch.end();
        stage.draw();
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
