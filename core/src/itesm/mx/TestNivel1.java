package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
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

public class TestNivel1 extends Pantalla implements Screen {


    private Elya testE;
    private WrumperVolador testV;
    private LinkedList<Item> listaItems;
    private LinkedList<Wrumper> enemigos;
    private Stage stage;
    private float timerEnemigos;
    private float  diferenciaX = 0f;// Es una constante para probar el scroll
    private float timerPoder = 0;
    private LinkedList<Flecha> flechas;
    int flechasActivas = 0;
    float timerGanar = 0;
    boolean gano = false;
    private Sprite spriteGanaste;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {

        inicializarShow();
        timerEnemigos = 0;
        listaItems = new LinkedList<Item>();
        crearFondo("Nivel1/FondoNivel1.png");
        enemigos = new LinkedList<Wrumper>();
        flechas = new LinkedList<Flecha>();
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();
        testE.setPos(40, ALTO/2-210);
        testV=new WrumperVolador();
        stage = new Stage(vista);
        crearBotonDer();
        crearBotonIzq();
        crearBotonAtacar();
        crearGanaste();
        Gdx.input.setInputProcessor(stage);



    }

    private void crearGanaste() {
        Texture texturaGanaste = new Texture("GanoPerdio/Gano_CONBOTONES.png");
        spriteGanaste = new Sprite(texturaGanaste);
        spriteGanaste.setPosition(0, 0);

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

                if(testE.getEstado()!=PersonajeEstado.muriendo && testE.getEstado()!=PersonajeEstado.muerto) {
                    testE.getSprite().setPosition(testE.getX()-20, testE.getY());
                    testE.setEstado(PersonajeEstado.caminandoReversa);}
            }
        });

    }


    private void crearBotonAtacar(){
        // Botón derecha
        Texture texturaBtnAtac = new Texture("Botones/Btn_Nivel1/Btn_Ataque.png");
        TextureRegion textureRegionBtnAtac = new TextureRegion(texturaBtnAtac);
        TextureRegionDrawable textureRegionDrawableBtnAtac = new TextureRegionDrawable(textureRegionBtnAtac);
        ImageButton btnAtac = new ImageButton(textureRegionDrawableBtnAtac);
        btnAtac.setPosition(ANCHO/2-600, ALTO/2-345);

        stage.addActor(btnAtac);

        // Acción botón Atacar
        btnAtac.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                int direc = 1;
                if(testE.getEstado()==PersonajeEstado.caminandoReversa){
                    direc = -1;
                }
                if(flechasActivas<=4 && testE.getEstado()!=PersonajeEstado.muerto && testE.getEstado()!=PersonajeEstado.muriendo){
                    flechas.add(new Flecha(testE.getX()+17.5f, testE.getY()+50, testE, direc));
                    flechasActivas++;
                }


            }
        });

    }

    private void crearBotonDer() {
        // Botón derecha

        Texture texturaBtnDer = new Texture("Botones/Btn_Nivel1/Btn_Der.png");
        TextureRegion textureRegionBtnDer = new TextureRegion(texturaBtnDer);
        TextureRegionDrawable textureRegionDrawableBtnDer = new TextureRegionDrawable(textureRegionBtnDer);
        ImageButton btnDer = new ImageButton(textureRegionDrawableBtnDer);
        btnDer.setPosition(ANCHO/2-260, ALTO/2-355);

        stage.addActor(btnDer);

        // Acción botón derecha
        btnDer.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                if(testE.getEstado()!=PersonajeEstado.muriendo && testE.getEstado()!=PersonajeEstado.muerto) {
                    testE.getSprite().setPosition(testE.getX() + 20, testE.getY());
                    testE.setEstado(PersonajeEstado.caminandoNormal);
                }

            }
        });

    }



    @Override
    public void render(float delta) {
        borrarPantalla(0f,0f,0f);
        timerGanar+=delta;
        actualizarFlechas(delta);
        verificarVidasEnemigos();
        borrarFlechas();
        if(testE.getPoder()>1){
            System.out.println("poder: " + testE.getPoder());
            timerPoder += delta;
            if(timerPoder>10){
                testE.setPoder(1);
                timerPoder = 0;

            }
        }
        timerEnemigos+=delta;
        if(timerEnemigos>=10){
            generarWrumpers();
            timerEnemigos = 0;
        }

        generarItems();
        batch.setProjectionMatrix(camara.combined);
        desplazarItem();

        for(Wrumper wrumper: enemigos){
            wrumper.rastrearPrincesa(testE);
        }
        for(int i = enemigos.size()-1; i>=0; i--){
            Wrumper wrumper = enemigos.get(i);
            if(wrumper.estado==PersonajeEstado.muerto){
                enemigos.remove(i);
                System.out.println("ded");
            }
        }
        verificarColisionEnemigos();
        verificarColisionItems();
        verificarColisionFlechas();



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
        for(int i = flechas.size()-1;i>=0;i--){
            flechas.get(i).render(batch);
        }
        batch.end();
        stage.draw();
        actualizarPersonaje(diferenciaX);
        batch.begin();
        if(timerGanar>20){
            spriteGanaste.draw(batch);
        }
        batch.end();

    }



    private void borrarFlechas() {
        for(int i = flechas.size()-1;i>=0;i--){
            Flecha flecha = flechas.get(i);
            if(flecha.getSprite().getX()>Pantalla.ANCHO||flecha.getSprite().getX()<0){
                flechasActivas--;
                flechas.remove(i);
                break;
            }
        }
    }

    private void verificarVidasEnemigos(){
        for(int i = enemigos.size()-1;i>=0;i--){
            Wrumper wrumper = enemigos.get(i);
            if(wrumper.getVidas()<=0){
                enemigos.remove(i);
                break;
            }
        }
    }

    private void actualizarFlechas(float delta) {

        for(int i = flechas.size()-1;i>=0;i--){

            flechas.get(i).moverX(delta);
        }
    }

    private void verificarColisionFlechas() {
        for(int i = flechas.size()-1; i>=0; i--){
            Flecha flecha = flechas.get(i);
            for(int j = enemigos.size()-1; j>=0; j--){
                Wrumper wrumper = enemigos.get(j);
                if(flecha.getSprite().getX()>=wrumper.getX()-wrumper.getWidth()/2&&flecha.getSprite().getX()<=wrumper.getX()+wrumper.getWidth()/2){
                    wrumper.actualizarVidas(testE.getPoder()*-1);
                    flechas.remove(i);
                    flechasActivas--;
                    break;
                }
            }
        }
    }

    private void verificarColisionItems() {
        for(int i = listaItems.size()-1; i>= 0; i--){
            Item item = listaItems.get(i);
            if(testE.getX()<item.getX()+item.getSprite().getWidth()/2 && testE.getX()>item.getX()-item.getSprite().getWidth()/2 && item.getY()<testE.getY()+testE.getHeight()/2 && item.getY()>testE.getY()-testE.getHeight()/2){
                listaItems.remove(i);
                item.generarEfecto(testE);
                if(item.getClass().equals(Estrella.class)){
                    timerPoder = 0;
                }
            }
        }
    }

    private void actualizarPersonaje(float delta) {
        testE.moverX(delta);
        actualizarCamara();
    }

    private void actualizarCamara() {
        float xCamara= testE.getX();
        if(testE.getX()<ANCHO/2) {

            xCamara=ANCHO/2;
        }
        else if(testE.getX()+diferenciaX>ANCHO){
            xCamara=ANCHO;
        }
        camara.position.x=xCamara;
        camara.update();
    }

    private void generarWrumpers() {
        enemigos.add(new Wrumper());
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
        }
        else if(randomNum ==300){
            int coordX = MathUtils.random(80, (int)ANCHO/2-80);
            int coordY = MathUtils.random(ALTO/2, ALTO);
            Cereza cereza = new Cereza(coordX, coordY);
            listaItems.add(cereza);

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
    // Borra la pantalla con el color RGB (r,g,b)
    protected void borrarPantalla(float r, float g, float b) {
        Gdx.gl.glClearColor(r,g,b,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

    public class ProcesadorEntradaJuego implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {

                    if (keycode== Input.Keys.RIGHT){
                        return true;
                    }
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


