package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.LinkedList;
import java.util.Random;

public class TestNivel1 extends Pantalla implements Screen {

    private Stage stage;
    private Elya testE;
    private LinkedList<Item> listaItems;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {
        inicializarShow();
        stage = new Stage(vista);
        listaItems = new LinkedList<Item>();
        crearFondo("Nivel1/Nivel1Base.png");
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        Gdx.input.setInputProcessor(stage);
        testE=new Elya();
        testE.sprite.setY(ALTO/2-210);
        crearBotones();

    }

    private void crearBotones() {
        // Botón Derecha
        Texture texturaBtnDerecha = new Texture("Botones/Btn_Nivel1/Btn_Der.png");
        TextureRegion textureRegionBtnDerecha = new TextureRegion(texturaBtnDerecha);
        TextureRegionDrawable textureRegionDrawableBtnDerecha = new TextureRegionDrawable(textureRegionBtnDerecha);
        ImageButton btnDerecha = new ImageButton(textureRegionDrawableBtnDerecha);
        btnDerecha.setPosition(ANCHO/2-158, ALTO/2-355);

        stage.addActor(btnDerecha);

        // Botón Izquierda
        Texture texturaBtnIzquierda = new Texture("Botones/Btn_Nivel1/Btn_Izq.png");
        TextureRegion textureRegionBtnIzquierda = new TextureRegion(texturaBtnIzquierda);
        TextureRegionDrawable textureRegionDrawableBtnIzquierda = new TextureRegionDrawable(textureRegionBtnIzquierda);
        ImageButton btnIzquierda = new ImageButton(textureRegionDrawableBtnIzquierda);
        btnIzquierda.setPosition(ANCHO/2-375, ALTO/2-355);

        stage.addActor(btnIzquierda);

        // Listeners
        btnDerecha.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                testE.moverX(10);
            }
        });

        btnIzquierda.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                testE.moverX(-10);
            }
        });



    }

    @Override
    public void render(float delta) {
        generarItems();
        batch.setProjectionMatrix(camara.combined);
        desplazarItems();
        batch.begin();
        batch.draw(textFondo, 0, 0);
        // dibujamos items (si existen) y eliminamos los que ya hayan cumplido su ciclo
        ciclarItems(delta);
        testE.render(batch);
        batch.end();
        stage.draw();
    }

    private void ciclarItems(float delta) {
        for(Item item: listaItems){
            item.render(batch);
            item.tiempoTranscurrido+=delta;
            if(item.tiempoTranscurrido>=15){
                listaItems.remove(item);
                System.out.println("Elimino aquí");
                break;
            }

        }
    }

    private void desplazarItems() {
        for(int i = 0; i<listaItems.size();i++){
            Item currentItem = listaItems.get(i);

            if(currentItem.sprite.getY()>ALTO/2-200){
                currentItem.moverY(-1);
            }

        }
    }

    private void generarItems() {
        int randomNum = MathUtils.random(0, 1000);
        if(randomNum ==1){
            int coordX = MathUtils.random(80, (int)ANCHO/2-80);
            int coordY = MathUtils.random(ALTO/2, ALTO);
            Estrella estrellita = new Estrella(coordX, coordY);
            listaItems.add(estrellita);
            estrellita.tiempoTranscurrido = 0;
        }
        else if(randomNum ==300){
            int coordX = MathUtils.random(80, (int)ANCHO/2-80);
            int coordY = MathUtils.random(ALTO/2, ALTO);
            Cereza cereza = new Cereza(coordX, coordY);
            listaItems.add(cereza);
            cereza.tiempoTranscurrido=0;
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

    private class ProcesadorEntradaJuego implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {

            return true;
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


