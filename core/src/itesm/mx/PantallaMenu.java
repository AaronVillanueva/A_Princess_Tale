package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PantallaMenu extends Pantalla {

    private Stage stage;
    Nube nube1,nube2;

    public PantallaMenu(Principal principal){
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
        crearFondo("Pantallas/Pantalla_Menu.png");
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
        Gdx.input.setInputProcessor(stage);
    }

    private void crearBotones() {

        // Botón Jugar
        Texture texturaBtnJugar = new Texture("Botones/Btn_Menu/Btn_Jugar.png");
        TextureRegion textureRegionBtnJugar = new TextureRegion(texturaBtnJugar);
        TextureRegionDrawable textureRegionDrawableBtnJugar = new TextureRegionDrawable(textureRegionBtnJugar);
        ImageButton btnJugar = new ImageButton(textureRegionDrawableBtnJugar);
        btnJugar.setPosition(ANCHO/2-250, ALTO/2-50);

        // Botón controles
        Texture texturaBtnControles = new Texture("Botones/Btn_Menu/Btn_Controles.png");
        TextureRegion textureRegionBtnControles = new TextureRegion(texturaBtnControles);
        TextureRegionDrawable textureRegionDrawableBtnControles = new TextureRegionDrawable(textureRegionBtnControles);
        ImageButton btnControles = new ImageButton(textureRegionDrawableBtnControles);
        btnControles.setPosition(ANCHO/2+170, ALTO/2-170);


        // Botón Configuración
        Texture texturaBtnConf = new Texture("Botones/Btn_Menu/Btn_Configuracion.png");
        TextureRegion textureRegionBtnConf = new TextureRegion(texturaBtnConf);
        TextureRegionDrawable textureRegionDrawableBtnConf = new TextureRegionDrawable(textureRegionBtnConf);
        ImageButton btnConf = new ImageButton(textureRegionDrawableBtnConf);
        btnConf.setPosition(ANCHO/2-280, ALTO/2-170);

        // Botón Créditos
        Texture texturaBtnCred = new Texture("Botones/Btn_Menu/Btn_Creditos.png");
        TextureRegion textureRegionBtnCred = new TextureRegion(texturaBtnCred);
        TextureRegionDrawable textureRegionDrawableBtnCred = new TextureRegionDrawable(textureRegionBtnCred);
        ImageButton btnCred = new ImageButton(textureRegionDrawableBtnCred);
        btnCred.setPosition(ANCHO/2-300+btnConf.getWidth()+33, ALTO/2-170);



        stage.addActor(btnJugar);
        stage.addActor(btnControles);
        stage.addActor(btnConf);
        stage.addActor(btnCred);

        // Agregar Listeners

        btnJugar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new TestNivel4(principal));
            }
        });

        btnControles.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new PantallaControles(principal));
            }
        });

        btnConf.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new PantallaAjustes(principal));
            }
        });

        btnCred.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new PantallaCreditos(principal));
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
            /*Vector3 v3 = new Vector3(screenX, screenY,0);
            camara.unproject(v3);
            //Verifica botón jugar

            if(v3.y<=ALTO-100 && v3.y>=ALTO-290 && v3.x<=860 && v3.x>=400){
                //Quiere jugar
                principal.setScreen( new TestNivel1(principal));
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
            if(v3.y<=ALTO-315 && v3.y>=ALTO-450 && v3.x<=870 && v3.x>=745){

                principal.setScreen( new PantallaAyuda(principal));
            }
            if(v3.y<=ALTO-460 && v3.y>=ALTO-545 && v3.x<=755 && v3.x>=515){
                principal.setScreen( new PantallaCreditos(principal));
            }


                */

            return false;
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
