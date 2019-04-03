package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class Nivel1Real extends Pantalla{

    //Mapa
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;

    private Elya testE;
    private WrumperVolador testV;
    private LinkedList<Item> listaItems;
    private LinkedList<Wrumper> enemigos;
    private Stage stage;
    private float timerEnemigos;
    private float  diferenciaX =10f;// Es una constante para probar el scroll

    public Nivel1Real(Principal principal){
        this.principal=principal;}



    @Override
    public void show() {

        inicializarShow();
        timerEnemigos = 0;
        listaItems = new LinkedList<Item>();
        crearFondo("Nivel1/FondoNivel1.png");
        cargarMapa();

        enemigos = new LinkedList<Wrumper>();
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();
        testE.setPos(40, ALTO/2-210);

        testV=new WrumperVolador();

        stage = new Stage(vista);
        crearBotonDer();
        crearBotonIzq();
        Gdx.input.setInputProcessor(stage);



    }

    private void cargarMapa() {
        AssetManager manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("Nivel1/Nivel1A.tmx",TiledMap.class);
        manager.finishLoading(); //Bloquea la app
        mapa = manager.get("Nivel1/Nivel1A.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa);
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
        borrarPantalla(0f,0f,0f);

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
        for(Wrumper wrumper: enemigos){
            if(wrumper.estado==PersonajeEstado.muerto){
                enemigos.remove(wrumper);
                System.out.println("ded");
            }
        }
        verificarColisionEnemigos();


        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(textFondo, camara.position.x-ANCHO/2, 0);

        batch.end();

        rendererMapa.setView(camara);
        rendererMapa.render();

        // dibujamos items (si existen) y eliminamos los que ya hayan cumplido su ciclo
        batch.begin();
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
        stage.draw();


        actualizarPersonaje(diferenciaX);

    }

    private void actualizarPersonaje(float delta) {
        int mapaWidth = mapa.getProperties().get("width", Integer.class) * mapa.getProperties().get("tilewidth",Integer.class);
        if(testE.getX()+delta<mapaWidth-delta-testE.getWidth()){
            testE.moverX(delta);
        }



        actualizarCamara();
    }

    private void actualizarCamara() {
        float xCamara= testE.getX();
        int mapaWidth = mapa.getProperties().get("width", Integer.class) * mapa.getProperties().get("tilewidth",Integer.class);
        if(testE.getX()<ANCHO/2 ){
            xCamara = ANCHO/2;
        }
        else if(testE.getX() > mapaWidth - ANCHO/2){
            xCamara = mapaWidth - ANCHO/2;
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
