package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.LinkedList;
import java.util.Random;

public class TestNivel1 extends Pantalla implements Screen {

    private Estrella testEstrella;
    private Personaje test;
    private LinkedList<Item> listaItems;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {
        inicializarShow();
        listaItems = new LinkedList<Item>();
        crearFondo("Pantallas/Nivel1.PNG");

        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        test=new Personaje();
        testEstrella = new Estrella(ANCHO/2, ALTO);
        listaItems.add(testEstrella);
    }

    @Override
    public void render(float delta) {

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

        test.render(batch);
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


