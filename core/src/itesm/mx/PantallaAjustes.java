package itesm.mx;

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
    @Override
    public void show() {
        nube1=new Nube(1,300,400,.8f);
        nube2=new Nube(2,600,500,.8f);
        nube3=new Nube(1,900,600,.8f);
        //configuracion= new Stage();
        inicializarShow();
        crearFondo("Pantallas/Pantalla_Configuracion.png");
        crearRegreso();
        //crearMusicaEncendida();
        //crearMusicaApagada();
    }

    private void crearMusicaApagada() {
        //BotónMusica
        Texture texturaBtnMusicaApagada = new Texture("Botones/Btn_Configuaracion/MusicOFF.png");
        TextureRegion textureRegionBtnMusicaApagada = new TextureRegion(texturaBtnMusicaApagada);
        TextureRegionDrawable textureRegionDrawableBtnMusicaApagada = new TextureRegionDrawable(textureRegionBtnMusicaApagada);
        ImageButton btnMusicaApagada = new ImageButton(textureRegionDrawableBtnMusicaApagada);
        btnMusicaApagada.setPosition(460, 320);

        configuracion.addActor(btnMusicaApagada);

        // Acción botón Perder
        btnMusicaApagada.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                musica=true;

            }
        });
    }

    private void crearMusicaEncendida() {
        //BotónMusica
        Texture texturaBtnMusicaEncendida = new Texture("Botones/Btn_Configuaracion/MusicON.png");
        TextureRegion textureRegionBtnMusicaEncendida = new TextureRegion(texturaBtnMusicaEncendida);
        TextureRegionDrawable textureRegionDrawableBtnMusicaEncendida = new TextureRegionDrawable(textureRegionBtnMusicaEncendida);
        ImageButton btnMusicaEncendida = new ImageButton(textureRegionDrawableBtnMusicaEncendida);
        btnMusicaEncendida.setPosition(460, 320);



        // Acción botón Perder
        btnMusicaEncendida.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

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
        //configuracion.draw();
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
