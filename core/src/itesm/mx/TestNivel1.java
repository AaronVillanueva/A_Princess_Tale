package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.LinkedList;

public class TestNivel1 extends Pantalla implements Screen {


    private Elya testE;
    private LinkedList<Item> listaItems;
    private LinkedList<Wrumper> wrumpers;
    private LinkedList<WrumperVolador> wrumperVoladores;
    private Stage stage;
    private float timerEnemigos;
    private LinkedList<Item> itemsActivos;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {

        inicializarShow();
        timerEnemigos = 0;
        listaItems = new LinkedList<Item>();
        itemsActivos = new LinkedList<Item>();
        crearFondo("Nivel1/Nivel1Base.png");
        wrumpers = new LinkedList<Wrumper>();
        wrumperVoladores = new LinkedList<WrumperVolador>();
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();
        testE.setPos(40, ALTO/2-210);



        stage = new Stage(vista);
        crearBotonDer();
        crearBotonIzq();
        Gdx.input.setInputProcessor(stage);

    }

    private void crearBotonIzq() {
        // Botón derecha
        Texture texturaBtnIzq = new Texture("Botones/Btn_Nivel1/Btn_Izq.png");
        TextureRegion textureRegionBtnIzq = new TextureRegion(texturaBtnIzq);
        TextureRegionDrawable textureRegionDrawableBtnIzq = new TextureRegionDrawable(textureRegionBtnIzq);
        ImageButton btnIzq = new ImageButton(textureRegionDrawableBtnIzq);
        btnIzq.setPosition(ANCHO/2-370, ALTO/2-355);

        stage.addActor(btnIzq);

        // Acción botón Izquierda
        btnIzq.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                testE.getSprite().setPosition(testE.getX()-20, testE.getY());
                testE.setEstado(PersonajeEstado.caminandoReversa);
            }
        });

    }

    private void crearBotonDer() {
        // Botón derecha
        Texture texturaBtnDer = new Texture("Botones/Btn_Nivel1/Btn_Der.png");
        TextureRegion textureRegionBtnDer = new TextureRegion(texturaBtnDer);
        TextureRegionDrawable textureRegionDrawableBtnDer = new TextureRegionDrawable(textureRegionBtnDer);
        ImageButton btnDer = new ImageButton(textureRegionDrawableBtnDer);
        btnDer.setPosition(ANCHO/2-155, ALTO/2-355);

        stage.addActor(btnDer);

        // Acción botón derecha
        btnDer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                testE.getSprite().setPosition(testE.getX()+20, testE.getY());
                testE.setEstado(PersonajeEstado.caminandoNormal);
            }
        });

    }



    @Override
    public void render(float delta) {
        timerEnemigos+=delta;
        if(timerEnemigos>=10){
            generarWrumpers();
            timerEnemigos = 0;
        }

        generarItems();
        batch.setProjectionMatrix(camara.combined);
        desplazarItem();
        for(Wrumper wrumper: wrumpers){
            wrumper.rastrearPrincesa(testE);
        }

        verificarColisionEnemigos();
        verificarColisionItems();

        batch.begin();
        batch.draw(textFondo, 0, 0);

        // dibujamos items (si existen) y eliminamos los que ya hayan cumplido su ciclo

        for(Item item: listaItems){
            item.render(batch);

        }

        testE.render(batch);
        for(Wrumper wrumper: wrumpers){
            wrumper.render(batch);
        }
        batch.end();
        stage.draw();
    }

    private void verificarColisionItems() {
        for(Item item: listaItems){
            if(testE.getX()<item.getX()+item.getSprite().getWidth()/2 && testE.getX()>item.getX()-item.getSprite().getWidth()/2 && item.getY()<testE.getY()+testE.getHeight()/2 && item.getY()>testE.getY()-testE.getHeight()/2){
                listaItems.remove(item);
                item.generarEfecto(testE);
            }

        }
    }

    private void generarWrumpers() {
        wrumpers.add(new Wrumper());
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
        for(Wrumper wrumper: wrumpers){
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


