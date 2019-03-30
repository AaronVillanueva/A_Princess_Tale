package itesm.mx;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class Nivel1Real extends Pantalla{

    // MAPA
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;

    // HUD
    private OrthographicCamera camaraHUD; //hEADS UP DISPLAY
    private Viewport vistaHUD;
    private Stage escenaHUD;
    private float dx = 0;

    public Nivel1Real(Principal principal){
        this.principal=principal;
    }

    @Override
    public void show() {
        cargarMapa();
        construirHUD();
    }

    private void cargarMapa() {
        AssetManager manager = new AssetManager(); //esto va a estar en el juego
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        manager.load("Nivel1A.tmx",TiledMap.class);
        manager.finishLoading(); //Bloquea la app
        mapa = manager.get("Nivel1A.tmx");
        rendererMapa = new OrthogonalTiledMapRenderer(mapa);
    }

    private void construirHUD() {
        camaraHUD = new OrthographicCamera(ANCHO,ALTO);
        camaraHUD.position.set(ANCHO/2,ALTO/2,0);
        camaraHUD.update();
        vistaHUD = new StretchViewport(ANCHO,ALTO,camaraHUD);

        // Aquí me imagino va la parte del botón que desplaza al personaje


        //Agregar escenas
        escenaHUD = new Stage(vistaHUD);
        Gdx.input.setInputProcessor(escenaHUD);

    }

    @Override
    public void render(float delta) {
        //Actualizamos
        actualizarCamara ();

        borrarPantalla();
        batch.setProjectionMatrix(camara.combined);
        rendererMapa.setView(camara);
        rendererMapa.render();

        batch.begin();
        batch.end();

        //HUD
        batch.setProjectionMatrix(camaraHUD.combined);
        escenaHUD.draw();

    }

    private void actualizarCamara() {
    }

    public void borrarPantalla(){

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

    // Personaje



}
