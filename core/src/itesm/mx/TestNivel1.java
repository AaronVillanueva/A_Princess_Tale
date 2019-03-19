package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.Random;

public class TestNivel1 extends Pantalla implements Screen {


    private Elya testE;
    private LinkedList<Item> listaItems;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {
        inicializarShow();
        listaItems = new LinkedList<Item>();
        crearFondo("Nivel1/Nivel1Base.png");

        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();

    }

    @Override
    public void render(float delta) {
        generarItems();
        batch.setProjectionMatrix(camara.combined);
        for(int i = 0; i<listaItems.size();i++){
            Item currentItem = listaItems.get(i);
            if(currentItem.sprite.getY()>ALTO/2-200){
                currentItem.moverY(-1);
            }
        }
        batch.begin();
        batch.draw(textFondo, 0, 0);
        // dibujamos items (si existen)
        for(int i = 0; i<listaItems.size();i++){
            listaItems.get(i).render(batch);
        }

        testE.render(batch);
        batch.end();
    }

    private void generarItems() {
        int randomNum = MathUtils.random(0, 4000);
        if(randomNum ==1){
            int coordX = MathUtils.random(80, (int)ANCHO/2-80);
            int coordY = MathUtils.random(ALTO/2, ALTO);
            Estrella estrellita = new Estrella(coordX, coordY);
            listaItems.add(estrellita);
        }
        else if(randomNum ==300){
            int coordX = MathUtils.random(80, (int)ANCHO/2-80);
            int coordY = MathUtils.random(ALTO/2, ALTO);
            Cereza cereza = new Cereza(coordX, coordY);
            listaItems.add(cereza);
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


