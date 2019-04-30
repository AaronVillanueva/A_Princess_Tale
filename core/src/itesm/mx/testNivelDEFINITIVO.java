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

public class testNivelDEFINITIVO extends Pantalla implements Screen {


    private Elya testE;
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
    private Sprite spritePerdiste;
    private boolean perdio = false;
    private LinkedList<Sprite> vidas;
    int tiempoTranscurridoSeg = 0;
    float auxiliarTiempo = 0;
    private Texto texto;
    private Keeper1 bossNivel;
    String rutaFondo;



    public testNivelDEFINITIVO(Principal principal,int nivel){this.principal=principal;
        switch (nivel){
            case 1:
                rutaFondo="Nivel1/FondoNivel1.png";
            case 2:
                rutaFondo="Nivel2/FondoNivel1.png";
            case 3:
                rutaFondo="Nivel3/FondoNivel1.png";
            case 4:
                rutaFondo="Nivel4/FondoNivel1.png";
        }


    }
    @Override
    public void show() {

        inicializarShow();
        vidas = new LinkedList<Sprite>();
        timerEnemigos = 0;
        listaItems = new LinkedList<Item>();
        crearFondo(rutaFondo);
        enemigos = new LinkedList<Wrumper>();
        flechas = new LinkedList<Flecha>();
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();
        testE.setPos(40, ALTO/2-210);
        stage = new Stage(vista);
        crearBotonDer();
        crearBotonIzq();
        crearBotonAtacar();
        crearGanaste();
        crearPerdiste();
        inicializarVidas();
        texto = new Texto();
        bossNivel = new Keeper1();
        Gdx.input.setInputProcessor(stage);

    }

    private void inicializarVidas() {
        for(int i = 0; i<3;i++){
            Texture texturaVida = new Texture("Botones/Btn_Nivel1/Vida.png");
            Sprite sprite = new Sprite(texturaVida);
            if(i==0){
                sprite.setPosition(100, ALTO-150);
            }
            else if(i==1){
                sprite.setPosition(200, ALTO-150);
            }
            else{
                sprite.setPosition(300, ALTO-150);
            }

            vidas.add(sprite);
        }
    }

    private void crearGanaste() {
        Texture texturaGanaste = new Texture("GanoPerdio/Gano_CONBOTONES.png");
        spriteGanaste = new Sprite(texturaGanaste);
        spriteGanaste.setPosition(0, 0);

    }

    private void crearPerdiste(){
        Texture texturaPerdiste = new Texture("GanoPerdio/Perdio_CONBOTONES.png");//Chacando
        spritePerdiste = new Sprite(texturaPerdiste);
        spritePerdiste.setPosition(0, 0);

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
                    if(testE.getX()>0){
                        testE.getSprite().setPosition(testE.getX()-20, testE.getY());
                    }

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
                    if(testE.getX()<ANCHO-70){
                        testE.getSprite().setPosition(testE.getX() + 20, testE.getY());
                    }
                    testE.setEstado(PersonajeEstado.caminandoNormal);
                }

            }
        });

    }



    @Override
    public void render(float delta) {
        borrarPantalla(0f,0f,0f);
        checarPerdio();
        auxiliarTiempo += delta;
        if(auxiliarTiempo>=1 && gano!= true && perdio != true){
            tiempoTranscurridoSeg+=1;
            auxiliarTiempo = 0;
        }
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
        procesoBoss();
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
        batch.draw(cielo,0,0);
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

        dibujarVidas();

        testE.render(batch);
        dibujarBoss();

        for(Wrumper wrumper: enemigos){
            wrumper.render(batch);
        }
        for(int i = flechas.size()-1;i>=0;i--){
            flechas.get(i).render(batch);
        }

        // Dibuja el tiempo
        texto.mostrarTexto(batch, "Tiempo: " + tiempoTranscurridoSeg, ANCHO-200, ALTO-35);

        batch.end();
        stage.draw();
        actualizarPersonaje(diferenciaX);
        batch.begin();
        if(timerGanar>120 && perdio == false){
            gano = true;
            spriteGanaste.draw(batch);
        }

        else if(perdio == true){

            spritePerdiste.draw(batch);

        }
        batch.end();

    }

    private void procesoBoss() {
        if(tiempoTranscurridoSeg>=90 && bossNivel != null){
            bossNivel.rastrearPrincesa(testE);
        }
    }

    private void dibujarBoss() {
        if(tiempoTranscurridoSeg>=90&&bossNivel !=null){
            bossNivel.render(batch);
        }
    }

    private void dibujarVidas() {
        for(int i=0; i<testE.getVidas();i++){
            vidas.get(i).draw(batch);
        }
    }

    private void checarPerdio() {
        if(testE.getVidas()==0 && gano== false){
            perdio = true;
        }
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
        if(bossNivel!=null){
            if(bossNivel.getVidas()<=0){
                bossNivel= null;
                System.out.println("me hice null");
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
            if(bossNivel!=null){
                if(flecha.getSprite().getX()>=bossNivel.getX()-bossNivel.getWidth()/2&&flecha.getSprite().getX()<=bossNivel.getX()+bossNivel.getWidth()/2){
                    bossNivel.actualizarVidas(testE.getPoder()*-1);
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
        if(bossNivel!=null){
            if(bossNivel.getX()>=testE.getX()-testE.getWidth()/2 && bossNivel.getX()<= testE.getX()+ testE.getWidth()/2&&bossNivel!=null){
                bossNivel.atacar(testE);
                System.out.println(testE.getVidas());
            }}

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
