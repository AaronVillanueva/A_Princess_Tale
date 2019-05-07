package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


public class PantallaAjustes extends Pantalla implements Screen {

    public PantallaAjustes(Principal principal){this.principal=principal;}
    public Nube nube1,nube2,nube3;
    public Stage configuracion;
    public ImageButton btnMusicaEncendida;
    public ImageButton btnMusicaApagada;
    public ImageButton btnSonidoE;
    public ImageButton btnSonidoA;
    @Override
    public void show() {
        inicializarShow();
        nube1=new Nube(1,300,400,.8f);
        nube2=new Nube(2,600,500,.8f);
        nube3=new Nube(1,900,600,.8f);
        configuracion= new Stage(vista);

        crearFondo("Pantallas/Pantalla_Configuracion.png");
       crearRegresar();
        crearMusicaEncendida();
        crearMusicaApagada();
        crearEfectosEncendidos();
        crearEfectosApagados();
        Gdx.input.setInputProcessor(configuracion);
    }

    private void crearEfectosApagados() {
        //BotónEfectosEncendidos
        Texture texturaBtnSonidosA = new Texture("Botones/Btn_Configuracion/SoundOFF.png");
        TextureRegion textureRegionBtnSonidoA = new TextureRegion(texturaBtnSonidosA);
        TextureRegionDrawable textureRegionDrawableBtnSonidoA = new TextureRegionDrawable(textureRegionBtnSonidoA);
        btnSonidoA = new ImageButton(textureRegionDrawableBtnSonidoA);
        btnSonidoA.setPosition(560, 150);

        configuracion.addActor(btnSonidoA);

        // Acción botón Perder
        btnSonidoE.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.log("funciona","no");
                //Responder al evento del botón
                btnSonidoE.setVisible(true);
                btnSonidoA.setVisible(false);
            }
        });
    }

    private void crearEfectosEncendidos() {
        //BotónEfectosEncendidos
        Texture texturaBtnSonidosE = new Texture("Botones/Btn_Configuracion/SoundON.png");
        TextureRegion textureRegionBtnSonidoE = new TextureRegion(texturaBtnSonidosE);
        TextureRegionDrawable textureRegionDrawableBtnSonidoE = new TextureRegionDrawable(textureRegionBtnSonidoE);
         btnSonidoE = new ImageButton(textureRegionDrawableBtnSonidoE);
        btnSonidoE.setPosition(560, 150);

        configuracion.addActor(btnSonidoE);

        // Acción botón Perder
        btnSonidoE.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.log("funciona","no");
                //Responder al evento del botón
                btnSonidoE.setVisible(false);
                btnSonidoA.setVisible(true);
            }
        });
    }

    private void crearRegresar() {
        //BotónRegresar
        Texture texturaBtnRegresar = new Texture("Botones/Btn_Menu/Btn_Regresar.png");
        TextureRegion textureRegionBtnRegresar = new TextureRegion(texturaBtnRegresar);
        TextureRegionDrawable textureRegionDrawableBtnRegresar = new TextureRegionDrawable(textureRegionBtnRegresar);
        ImageButton btnRegresar = new ImageButton(textureRegionDrawableBtnRegresar);
        btnRegresar.setPosition(20, ALTO-160);

        configuracion.addActor(btnRegresar);

        // Acción botón Perder
        btnRegresar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new PantallaMenu(principal));
            }
        });
    }

    private void crearMusicaApagada() {
        //BotónMusica
        Texture texturaBtnMusicaApagada = new Texture("Botones/Btn_Configuracion/MusicOFF.png");
        TextureRegion textureRegionBtnMusicaApagada = new TextureRegion(texturaBtnMusicaApagada);
        TextureRegionDrawable textureRegionDrawableBtnMusicaApagada = new TextureRegionDrawable(textureRegionBtnMusicaApagada);
         btnMusicaApagada = new ImageButton(textureRegionDrawableBtnMusicaApagada);
         btnMusicaApagada.setPosition(560, 320);

        configuracion.addActor(btnMusicaApagada);

        // Acción botón Perder
        btnMusicaApagada.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                Gdx.app.log("funciona","no");
                btnMusicaApagada.setVisible(false);
                btnMusicaEncendida.setVisible(true);
                //btnMusicaApagada.setZIndex(9000);
                //btnMusicaEncendida.toFront();
                musica=true;

            }
        });
    }

    private void crearMusicaEncendida() {
        //BotónMusica
        Texture texturaBtnMusicaEncendida = new Texture("Botones/Btn_Configuracion/MusicON.png");
        TextureRegion textureRegionBtnMusicaEncendida = new TextureRegion(texturaBtnMusicaEncendida);
        TextureRegionDrawable textureRegionDrawableBtnMusicaEncendida = new TextureRegionDrawable(textureRegionBtnMusicaEncendida);
          btnMusicaEncendida = new ImageButton(textureRegionDrawableBtnMusicaEncendida);
        btnMusicaEncendida.setPosition(560, 320);


            configuracion.addActor(btnMusicaEncendida);
        // Acción botón Perder
        btnMusicaEncendida.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                //btnMusicaEncendida.setZIndex(9000);
                //btnMusicaApagada.toFront();
                btnMusicaEncendida.setVisible(false);
                btnMusicaApagada.setVisible(true);

               musica=false;

            }
        });
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

        //spriteRegresar.draw(batch);
        batch.end();
        //configuracion.act();
        configuracion.draw();





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
