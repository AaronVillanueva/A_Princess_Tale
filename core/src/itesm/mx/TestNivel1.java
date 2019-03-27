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
import java.util.Queue;
import java.util.Random;

public class TestNivel1 extends Pantalla implements Screen {


    private Wrumper testW;
    private Elya testE;
    private WrumperVolador testV;
    private LinkedList<Item> listaItems;
    private LinkedList<Wrumper> enemigos;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {

        inicializarShow();
        listaItems = new LinkedList<Item>();
        crearFondo("Nivel1/Nivel1Base.png");
        enemigos = new LinkedList<Wrumper>();
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();
        testE.setPos(40, ALTO/2-210);
        testW=new Wrumper();
        testW.setPos(ANCHO-50, ALTO/2-210);
        testV=new WrumperVolador();
        enemigos.add(testW);

    }

    @Override
    public void render(float delta) {
        generarItems();
        batch.setProjectionMatrix(camara.combined);
        desplazarItem();
        for(Wrumper wrumper: enemigos){
            wrumper.rastrearPrincesa(testE);
        }

        verificarColisionEnemigos();


        batch.begin();
        batch.draw(textFondo, 0, 0);

        // dibujamos items (si existen) y eliminamos los que ya hayan cumplido su ciclo

        for(Item item: listaItems){
            item.render(batch);
            item.tiempoTranscurrido+=delta;
            if(item.tiempoTranscurrido>=15){
                listaItems.remove(item);
                System.out.println("Elimino aquí");
                break;
            }

        }

        testE.render(batch);
        for(Wrumper wrumper: enemigos){
            wrumper.render(batch);
        }
        testV.render(batch);
        batch.end();
    }

    private void desplazarItem() {
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

    public void verificarColisionEnemigos(){
        for(Wrumper wrumper: enemigos){
            if(wrumper.getX()>=testE.getX()-testE.getWidth()/2 && wrumper.getX()<= testE.getX()+ testE.getWidth()/2){
                wrumper.atacar(testE);
                System.out.println(testE.getVidas());
            }
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


