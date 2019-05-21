package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PantallaHistoria extends Pantalla {
    float contadorTiempo = 0;
    private Stage stage;
    private Music musica;

    public PantallaHistoria(Principal principal){
        this.principal = principal;
    }

    @Override
    public void show() {
        inicializarShow();
        crearFondo("Historia/Historia1.png");
        stage = new Stage(vista);
        crearBoton();
        Gdx.input.setInputProcessor(stage);
        musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/historiaMusic.mp3"));
        musica.play();

    }
    public void crearBoton(){
        // Botón Nivel1
        Texture texturaBtn = new Texture("Botones/Btn_Historia/Btn_Saltar.png");
        TextureRegion textureRegionBtn = new TextureRegion(texturaBtn);
        TextureRegionDrawable textureRegionDrawableBtn = new TextureRegionDrawable(textureRegionBtn);
        ImageButton btn = new ImageButton(textureRegionDrawableBtn);
        btn.setPosition(ANCHO/2-120, ALTO/2+240);

        btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                musica.stop();
                principal.setScreen(new PantallaMenu(principal, true));
            }
        });

        stage.addActor(btn);


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
        stage.draw();

    }

    private void checarCambioPantalla() {
        if(contadorTiempo>=30){
            musica.stop();
            principal.setScreen(new PantallaMenu(principal, true));
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
        /*else if(contadorTiempo>=30 && contadorTiempo<35){
            crearFondo("Historia/Historia4.png");
        }
        else if(contadorTiempo>=35 && contadorTiempo<40){
            crearFondo("Historia/Historia5.png");
        }
        else if(contadorTiempo>=40){
            crearFondo("Historia/Historia6.png");

        } */

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
