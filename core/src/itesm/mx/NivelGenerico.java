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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.LinkedList;

public class NivelGenerico extends Pantalla implements Screen {

    private boolean pausa=false;
    private Elya testE;
    private LinkedList<Item> listaItems;
    private float tiempoParaVoladores=1000;
    private float tiempoParaWrumpers=5;
    private LinkedList<Wrumper> enemigos;
    private LinkedList<Volador> voladores;
    public LinkedList<BolaRaven> bolasRaven;
    private Stage stage;
    public Stage escenaPerdio;
    public Stage escenaPausa;
    public Stage escenaGano;
    private float timerEnemigos;
    private float timerVoladores;
    private float  diferenciaX = 0f;// Es una constante para probar el scroll
    private float timerPoder = 0;
    private LinkedList<Flecha> flechas;
    int flechasActivas = 0;
    float timerGanar = 0;
    boolean gano = false;
    private Sprite spriteGanaste;
    private Sprite spritePerdiste;
    private Sprite spritePausa;
    private boolean perdio = false;
    private LinkedList<Sprite> vidas;
    int tiempoTranscurridoSeg = 0;
    float auxiliarTiempo = 0;
    float timerAtaqueRaven = 0;
    private Texto texto;
    private Raven bossNivel4;
    private Keeper1 bossNivel1;
    private Keeper2 bossNivel2;
    private Keeper3 bossNivel3;
    public Nube nube1,nube2;
    public String rutaFondo;
    float timerAnimAtaque = 0;
    public int nivel = 0;

    public NivelGenerico(Principal principal) {
        this.principal = principal;
        rutaFondo="Nivel4/Nivel4.png";
    }

    public NivelGenerico(Principal principal, int nivel) {
        this.principal = principal;
        switch (nivel) {
            case 1:
                rutaFondo = "Nivel1/Nivel1.png";
                tiempoParaWrumpers = 10;
                tiempoParaVoladores = 1000;
                break;
            case 2:
                rutaFondo = "Nivel2/Nivel2.png";
                tiempoParaWrumpers = 7;
                tiempoParaVoladores = 1000;
                break;
            case 3:
                rutaFondo = "Nivel3/Nivel3.png";
                tiempoParaWrumpers = 7;
                tiempoParaVoladores = 12;
                break;
            case 4:
                rutaFondo = "Nivel4/Nivel4.png";
                tiempoParaWrumpers=5;
                tiempoParaVoladores = 10;
                break;
        }
        System.out.println("ini"+rutaFondo);
        this.nivel = nivel;
    }
    @Override
    public void show() {
        System.out.println(rutaFondo);
        nube1=new Nube(2,250,40,.8f);
        nube2=new Nube(2,700,600,.8f);
        nube1.activarRandom(false);
        nube2.activarRandom(false);
        inicializarShow();
        vidas = new LinkedList<Sprite>();
        timerEnemigos = 0;
        listaItems = new LinkedList<Item>();
        crearFondo(rutaFondo);
        enemigos = new LinkedList<Wrumper>();
        voladores = new LinkedList<Volador>();
        flechas = new LinkedList<Flecha>();
        bolasRaven = new LinkedList<BolaRaven>();
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());
        testE=new Elya();
        testE.setPos(40, ALTO/2-220);
        if(nivel == 1){
            testE.setPos(40, ALTO/2-205);
        }
        stage = new Stage(vista);
        escenaPerdio=new Stage(vista);
        escenaPausa=new Stage(vista);
        escenaGano=new Stage(vista);
        crearBotonDer();
        crearBotonIzq();
        crearBotonAtacar();
        crearGanaste();
        crearPerdiste();
        crearPausa();
        crearBotonPausa();
        configurarEscenaGano();
        configurarEscenaPerdio();
        configurarEscenaPausa();
        inicializarVidas();
        texto = new Texto();
        bossNivel4 = new Raven();
        bossNivel1 = new Keeper1();
        bossNivel2 = new Keeper2();
        bossNivel3 = new Keeper3();
        Gdx.input.setInputProcessor(stage);
    }

    private void configurarEscenaGano() {
        crearBtnSiguienteNivelGanar();
        crearBtnVolverAJugarGanar();
        crearBtnMenuPGanar();
    }

    private void crearBtnMenuPGanar() {
        //BotónMenu
        Texture texturaBtnMenuP = new Texture("Botones/Btn_GanoPerdio/Btn_MenuP.png");
        TextureRegion textureRegionBtnMenuP = new TextureRegion(texturaBtnMenuP);
        TextureRegionDrawable textureRegionDrawableBtnMenuP = new TextureRegionDrawable(textureRegionBtnMenuP);
        ImageButton btnMenuP = new ImageButton(textureRegionDrawableBtnMenuP);
        btnMenuP.setPosition(460, 180);

        escenaGano.addActor(btnMenuP);

        // Acción botón Perder
        btnMenuP.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                principal.setScreen(new PantallaMenu(principal));

            }
        });
    }

    private void crearBtnVolverAJugarGanar() {
        //BotónVolverJugar
        Texture texturaBtnVolverJugar = new Texture("Botones/Btn_GanoPerdio/Btn_VolverAJugar.png");
        TextureRegion textureRegionBtnVolverJugar = new TextureRegion(texturaBtnVolverJugar);
        TextureRegionDrawable textureRegionDrawableBtnVolverJugar = new TextureRegionDrawable(textureRegionBtnVolverJugar);
        ImageButton btnVolver = new ImageButton(textureRegionDrawableBtnVolverJugar);
        btnVolver.setPosition(460, 300);

        escenaGano.addActor(btnVolver);

        // Acción botón Perder
        btnVolver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                principal.setScreen(new NivelGenerico(principal,nivel));

            }
        });
    }

    private void crearBtnSiguienteNivelGanar() {
        //BotónSiguienteNivel
        Texture texturaBtnSiguienteNivel = new Texture("Botones/Btn_GanoPerdio/Btn_SigNivel.png");
        TextureRegion textureRegionBtnSiguienteNivel = new TextureRegion(texturaBtnSiguienteNivel);
        TextureRegionDrawable textureRegionDrawableBtnSiguienteNivel = new TextureRegionDrawable(textureRegionBtnSiguienteNivel);
        ImageButton btnSiguienteNivel = new ImageButton(textureRegionDrawableBtnSiguienteNivel);
        btnSiguienteNivel.setPosition(460, 420);

        escenaGano.addActor(btnSiguienteNivel);

        // Acción botón Perder
        btnSiguienteNivel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                principal.setScreen(new NivelGenerico(principal,nivel+1));


            }
        });
    }

    private void configurarEscenaPausa() {
        crearBtnMenuPausa();
        crearBtnReiniciarPausa();
        crearBtnContinuarPausa();
    }

    private void crearBtnContinuarPausa() {
        //BotónContinuar
        Texture texturaBtnContinuar = new Texture("Botones/Btn_GanoPerdio/Btn_Continuar.png");
        TextureRegion textureRegionBtnContinuar = new TextureRegion(texturaBtnContinuar);
        TextureRegionDrawable textureRegionDrawableBtnContinuar = new TextureRegionDrawable(textureRegionBtnContinuar);
        ImageButton btnContinuar = new ImageButton(textureRegionDrawableBtnContinuar);
        btnContinuar.setPosition(460, 440);

        escenaPausa.addActor(btnContinuar);

        // Acción botón Perder
        btnContinuar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                pausa=false;

            }
        });
    }

    private void crearBtnReiniciarPausa() {
        //BotónVolverMenu
        Texture texturaBtnReiniciar = new Texture("Botones/Btn_GanoPerdio/Btn_Reiniciar.png");
        TextureRegion textureRegionBtnReiniciar = new TextureRegion(texturaBtnReiniciar);
        TextureRegionDrawable textureRegionDrawableBtnReiniciar = new TextureRegionDrawable(textureRegionBtnReiniciar);
        ImageButton btnReiniciar = new ImageButton(textureRegionDrawableBtnReiniciar);
        btnReiniciar.setPosition(460, 200);

        escenaPausa.addActor(btnReiniciar);

        // Acción botón Perder
        btnReiniciar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                principal.setScreen(new NivelGenerico(principal,nivel));

            }
        });
    }

    private void crearBtnMenuPausa() {
        Texture texturaBtnMenu = new Texture("Botones/Btn_GanoPerdio/Btn_MenuP.png");
        TextureRegion textureRegionBtnMenu = new TextureRegion(texturaBtnMenu);
        TextureRegionDrawable textureRegionDrawableBtnMenu = new TextureRegionDrawable(textureRegionBtnMenu);
        ImageButton btnMenu = new ImageButton(textureRegionDrawableBtnMenu);
        btnMenu.setPosition(460, 320);

        escenaPausa.addActor(btnMenu);

        // Acción botón Perder
        btnMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                principal.setScreen(new PantallaMenu(principal));

            }
        });
    }

    private void crearBotonPausa() {
        //BotónPausa
        Texture texturaBtnPausa= new Texture("Botones/Btn_Nivel1/Btn_Pausa.png");
        TextureRegion textureRegionBtnPausa = new TextureRegion(texturaBtnPausa);
        TextureRegionDrawable textureRegionDrawableBtnPausa = new TextureRegionDrawable(textureRegionBtnPausa);
        ImageButton btnVolverMenu = new ImageButton(textureRegionDrawableBtnPausa);
        btnVolverMenu.setPosition(1150, 550);

        stage.addActor(btnVolverMenu);

        // Acción botón Perder
        btnVolverMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                pausa=true;

            }
        });
    }

    private void configurarEscenaPerdio() {
        //spritePerdiste.draw(batch);
        crearBotonVolverAJugarPerdio();
        crearBtnMenuPerdio();


    }

    private void crearBtnMenuPerdio() {
        //BotónVolverMenu
        Texture texturaBtnVolverMenu = new Texture("Botones/Btn_GanoPerdio/Btn_MenuP.png");
        TextureRegion textureRegionBtnVolverMenu = new TextureRegion(texturaBtnVolverMenu);
        TextureRegionDrawable textureRegionDrawableBtnVolverMenu = new TextureRegionDrawable(textureRegionBtnVolverMenu);
        ImageButton btnVolverMenu = new ImageButton(textureRegionDrawableBtnVolverMenu);
        btnVolverMenu.setPosition(460, 200);

        escenaPerdio.addActor(btnVolverMenu);

        // Acción botón Perder
        btnVolverMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                principal.setScreen(new PantallaMenu(principal));

            }
        });
    }

    private void crearBotonVolverAJugarPerdio() {
        //BotónVolverAJUgar
        Texture texturaBtnVolverJugar = new Texture("Botones/Btn_GanoPerdio/Btn_VolverAJugar.png");
        TextureRegion textureRegionBtnVolverAJugar = new TextureRegion(texturaBtnVolverJugar);
        TextureRegionDrawable textureRegionDrawableBtnVolverJugar = new TextureRegionDrawable(textureRegionBtnVolverAJugar);
        ImageButton btnVolverJugar = new ImageButton(textureRegionDrawableBtnVolverJugar);
        btnVolverJugar.setPosition(460, 320);

        escenaPerdio.addActor(btnVolverJugar);

        // Acción botón Perder
        btnVolverJugar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón

                principal.setScreen(new NivelGenerico(principal, nivel));

            }
        });
    }


    private void inicializarVidas() {
        for(int i = 0; i<3;i++){
            Texture texturaVida = new Texture("Botones/Btn_Nivel1/Vida.png");
            Sprite sprite = new Sprite(texturaVida);
            if(i==0){
                sprite.setPosition(25, ALTO-100);
            }
            else if(i==1){
                sprite.setPosition(150, ALTO-100);
            }
            else{
                sprite.setPosition(275, ALTO-100);
            }

            vidas.add(sprite);
        }
    }

    private void crearGanaste() {
        Texture texturaGanaste = new Texture("GanoPerdio/Gano.png");
        spriteGanaste = new Sprite(texturaGanaste);
        spriteGanaste.setPosition(0, 0);

    }

    private void crearPerdiste(){
        Texture texturaPerdiste = new Texture("GanoPerdio/Perdio.png");
        spritePerdiste = new Sprite(texturaPerdiste);
        spritePerdiste.setPosition(0, 0);




    }

    private void crearBotonIzq() {
        // Botón derecha
        Texture texturaBtnIzq = new Texture("Botones/Btn_Nivel1/Btn_Izq.png");
        TextureRegion textureRegionBtnIzq = new TextureRegion(texturaBtnIzq);
        TextureRegionDrawable textureRegionDrawableBtnIzq = new TextureRegionDrawable(textureRegionBtnIzq);
        ImageButton btnIzq = new ImageButton(textureRegionDrawableBtnIzq);
        btnIzq.setPosition(ANCHO/2-600, ALTO/2-355);

        stage.addActor(btnIzq);

        // Acción botón Izquierda
        btnIzq.addListener(new ClickListener(){
                               public boolean touchDown(InputEvent event,float x,float y,int pointer, int button){
                                   super.touchDown(event,x,y,pointer,button);
                                   if(testE.getEstado()!=PersonajeEstado.muriendo && testE.getEstado()!=PersonajeEstado.muerto) {
                                       if(testE.getX()<ANCHO-70){
                                           testE.setEstado(PersonajeEstado.caminandoReversa);
                                       }
                                   }

                                   return true;}
                               public void touchUp(InputEvent event,float x,float y,int pointer,int button){
                                   //super.touchUp(event,x,y,pointer,button);
                                   if(testE.getEstado()!=PersonajeEstado.muriendo && testE.getEstado()!=PersonajeEstado.muerto) {
                                       if(testE.getX()<ANCHO-70){
                                           testE.setEstado(PersonajeEstado.quieto); }
                                   }
                               }
                           }
        );

    }

    private void crearBotonAtacar(){
        // Botón derecha
        Texture texturaBtnAtac = new Texture("Botones/Btn_Nivel1/Btn_Ataque.png");
        TextureRegion textureRegionBtnAtac = new TextureRegion(texturaBtnAtac);
        TextureRegionDrawable textureRegionDrawableBtnAtac = new TextureRegionDrawable(textureRegionBtnAtac);
        ImageButton btnAtac = new ImageButton(textureRegionDrawableBtnAtac);
        btnAtac.setPosition(ANCHO/2+400, ALTO/2-345);

        stage.addActor(btnAtac);

        // Acción botón Atacar
        btnAtac.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //Responder al evento del botón
                if(testE.derecha==true){
                    testE.setEstado(PersonajeEstado.atacando);
                }

                else{
                    testE.setEstado(PersonajeEstado.atacandoReversa);
                }
                timerAnimAtaque = 0;
                int direc = 1;
                if(testE.getEstado()==PersonajeEstado.caminandoReversa || testE.derecha==false){
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
        btnDer.setPosition(ANCHO/2-450, ALTO/2-355);

        stage.addActor(btnDer);

        // Acción botón derecha para movimiento
        btnDer.addListener(new ClickListener(){

                               public boolean touchDown(InputEvent event,float x,float y,int pointer, int button){
                                   super.touchDown(event,x,y,pointer,button);
                                   if(testE.getEstado()!=PersonajeEstado.muriendo && testE.getEstado()!=PersonajeEstado.muerto) {
                                       if(testE.getX()<ANCHO-70){
                                           testE.setEstado(PersonajeEstado.caminandoNormal);
                                       }
                                   }

                                   return true;
                               }
                               public void touchUp(InputEvent event,float x,float y,int pointer,int button){
                                   //super.touchUp(event,x,y,pointer,button);
                                   if(testE.getEstado()!=PersonajeEstado.muriendo && testE.getEstado()!=PersonajeEstado.muerto) {
                                       if(testE.getX()<ANCHO-70){
                                           testE.setEstado(PersonajeEstado.quieto);
                                       }
                                   }
                               }

                           }
        );
        //btnDer.addListener()

    }



    @Override
    public void render(float delta) {
        if(!pausa) {
            if(testE.getEstado()==PersonajeEstado.atacando || testE.getEstado()==PersonajeEstado.atacandoReversa){
                timerAnimAtaque += delta;
            }

            if(timerAnimAtaque>=0.5){
                testE.setEstado(PersonajeEstado.quieto);
                timerAnimAtaque = 0;
            }


            Gdx.input.setInputProcessor(stage);
            borrarPantalla(0f, 0f, 0f);
            checarPerdio();

            //mover a la princesa cuando se encuentra en un estado caminando
            if(testE.getEstado()==PersonajeEstado.caminandoNormal){
                testE.moverX(testE.velocidad);
            }
            if(testE.getEstado()==PersonajeEstado.caminandoReversa){
                testE.moverX(-testE.velocidad);
            }


            auxiliarTiempo += delta;
            if (auxiliarTiempo >= 1 && gano != true && perdio != true) {
                tiempoTranscurridoSeg += 1;
                auxiliarTiempo = 0;
            }
            timerGanar += delta;
            actualizarTimerAtaqueRaven(delta);
            verificarColisionBolasRaven();
            actualizarBolasRaven();
            actualizarFlechas(delta);
            verificarVidasEnemigos();
            borrarFlechas();
            if (testE.getPoder() > 1) {
                System.out.println("poder: " + testE.getPoder());
                timerPoder += delta;
                if (timerPoder > 10) {
                    testE.setPoder(1);
                    timerPoder = 0;

                }
            }


            timerEnemigos += delta;
            timerVoladores += delta;
            if (timerEnemigos >= tiempoParaWrumpers) {
                generarWrumpers();
                timerEnemigos = 0;
            }
            if(timerVoladores>=tiempoParaVoladores){
                generarVoladores();
                timerVoladores=0;
            }

            procesoAtaqueRaven();

            generarItems();
            batch.setProjectionMatrix(camara.combined);
            desplazarItem();

            for (Wrumper wrumper : enemigos) {
                wrumper.rastrearPrincesa(testE);
            }
            for(Volador volador: voladores){
                volador.rastrearPrincesa(testE);
            }
            procesoBoss();
            for (int i = enemigos.size() - 1; i >= 0; i--) {
                Wrumper wrumper = enemigos.get(i);
                if (wrumper.estado == PersonajeEstado.muerto) {
                    enemigos.remove(i);
                    System.out.println("ded");
                }
            }

            for (int i = voladores.size() - 1; i >= 0; i--) {
                Volador volador = voladores.get(i);
                if (volador.estado == PersonajeEstado.muerto) {
                    voladores.remove(i);
                    System.out.println("ded");
                }
            }
            verificarColisionEnemigos();
            verificarColisionItems();
            verificarColisionFlechas();


            batch.begin();
            batch.draw(cielo, 0, 0);
            if(nivel == 2){
            nube1.draw(batch, 5);
            nube2.draw(batch, 2);}
            else if(nivel ==3){
                nube1.draw(batch, 5);
            }

            batch.draw(textFondo, 0, 0);

            // dibujamos items (si existen) y eliminamos los que ya hayan cumplido su ciclo

            for (Item item : listaItems) {
                item.render(batch);
                item.tiempoTranscurrido += delta;
                if (item.tiempoTranscurrido >= 15) {
                    listaItems.remove(item);
                    System.out.println("Elimino aquí");
                    break;
                }

            }
            dibujarBolasRaven();
            dibujarVidas();

            testE.render(batch);
            dibujarBoss();

            for (Wrumper wrumper : enemigos) {
                wrumper.render(batch);
            }

            for(Volador volador: voladores){
                volador.render(batch);
            }

            for (int i = flechas.size() - 1; i >= 0; i--) {
                flechas.get(i).render(batch);
            }

            // Dibuja el tiempo
            texto.mostrarTexto(batch, "Tiempo: " + tiempoTranscurridoSeg, ANCHO - 200, ALTO - 35);

            batch.end();
            stage.draw();
            actualizarPersonaje(diferenciaX);
            batch.begin();
            if (timerGanar > 120 && perdio == false) {
                gano = true;
                spriteGanaste.draw(batch);
            } else if (perdio == true) {
                spritePerdiste.draw(batch);

                //escenaPerdio.draw();
            }
            batch.end();
            if (perdio == true) {
                //spritePerdiste.draw(batch);

                escenaPerdio.draw();
                Gdx.input.setInputProcessor(escenaPerdio);
            }
            if(timerGanar > 120 && perdio == false){
                Gdx.input.setInputProcessor(escenaGano);
              escenaGano.draw();

            }
        }
        else{
            batch.begin();
            spritePausa.draw(batch);
            batch.end();
            crearPausa();
            Gdx.input.setInputProcessor(escenaPausa);
            escenaPausa.draw();


        }


    }

    private void actualizarTimerAtaqueRaven(float delta) {
        if(nivel ==4){
            if(bossNivel4 !=null && tiempoTranscurridoSeg>=90){
                timerAtaqueRaven +=delta;
            }
        }
    }

    private void procesoAtaqueRaven() {
        if(timerAtaqueRaven>=5){
            bolasRaven.add(new BolaRaven(bossNivel4));
            timerAtaqueRaven = 0;
        }
    }

    private void dibujarBolasRaven() {
        for(BolaRaven bola: bolasRaven){
            bola.render(batch);
        }
    }

    private void actualizarBolasRaven() {
        for(BolaRaven bola: bolasRaven){
            bola.rastrearPrincesa(bossNivel4, testE);
        }
    }

    private void verificarColisionBolasRaven() {
        for(int i = bolasRaven.size()-1;i>=0;i--){
            BolaRaven bola = bolasRaven.get(i);
            if(bola.getX()>=testE.getX()-testE.getWidth()/2 && bola.getX()<= testE.getX()+ testE.getWidth()/2){
                testE.actualizarVidas(-1);
                bolasRaven.remove(i);
                break;
            }
        }
    }

    private void generarVoladores() {
        Volador volador = new Volador();
        volador.setPos(volador.getX(), volador.getY()-17);
        enemigos.add(volador);

    }

    private void crearPausa() {
        Texture texturaPausa = new Texture("Pantallas/Pantalla_Pausa.png");
        spritePausa = new Sprite(texturaPausa);
        spritePausa.setPosition(0, 0);
    }


    private void procesoBoss() {
        switch(nivel){
            case 1:
                if(tiempoTranscurridoSeg>=90 && bossNivel1 != null){
                    bossNivel1.rastrearPrincesa(testE);
                }
                break;
            case 2:
                if(tiempoTranscurridoSeg>=90 && bossNivel2 != null){
                    bossNivel2.rastrearPrincesa(testE);
                }
                break;
            case 3:
                if(tiempoTranscurridoSeg>=90 && bossNivel3 != null){
                    bossNivel3.rastrearPrincesa(testE);
                }
                break;
            case 4:
                if(tiempoTranscurridoSeg>=90 && bossNivel4 != null){
                    bossNivel4.encontrarPosicion(testE);
                }
                break;

        }

    }

    private void dibujarBoss() {
        switch(nivel){
            case 1:
                if(tiempoTranscurridoSeg>=90&&bossNivel1 !=null){
                    bossNivel1.render(batch);
                }
                break;
            case 2:
                if(tiempoTranscurridoSeg>=90&&bossNivel2 !=null){
                    bossNivel2.render(batch);
                }
                break;
            case 3:
                if(tiempoTranscurridoSeg>=90&&bossNivel3 !=null){
                    bossNivel3.render(batch);
                }
                break;
            case 4:
                if(tiempoTranscurridoSeg>=90&& bossNivel4 !=null){
                    bossNivel4.render(batch);
                }
                break;
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

        for(int i = voladores.size()-1;i>=0;i--){
            Volador volador = voladores.get(i);
            if(volador.getVidas()<=0){
                voladores.remove(i);
                break;
            }
        }

        switch(nivel){
            case 1:
                if(bossNivel1!=null){
                    if(bossNivel1.getVidas()<=0){
                        bossNivel1= null;
                        System.out.println("me hice null");
                    }
                }
                break;
            case 2:
                if(bossNivel2!=null){
                    if(bossNivel2.getVidas()<=0){
                        bossNivel2= null;
                        System.out.println("me hice null");
                    }
                }
                break;
            case 3:
                if(bossNivel3!=null){
                    if(bossNivel3.getVidas()<=0){
                        bossNivel3= null;
                        System.out.println("me hice null");
                    }
                }

                break;
            case 4:
                if(bossNivel4 !=null){
                    if(bossNivel4.getVidas()<=0){
                        bossNivel4 = null;
                        bolasRaven = new LinkedList<BolaRaven>();
                        System.out.println("me hice null");
                    }
                }
                break;
        }




    }

    private void actualizarFlechas(float delta) {

        for(int i = flechas.size()-1;i>=0;i--){

            flechas.get(i).moverX(delta);
        }
    }

    private void verificarColisionFlechas() {
        boolean yaDesaparecio = false;
        for(int i = flechas.size()-1; i>=0; i--){
            Flecha flecha = flechas.get(i);
            for(int j = enemigos.size()-1; j>=0; j--){
                Wrumper wrumper = enemigos.get(j);
                if(flecha.getSprite().getX()>=wrumper.getX()-wrumper.getWidth()/2&&flecha.getSprite().getX()<=wrumper.getX()+wrumper.getWidth()/2){
                    wrumper.actualizarVidas(testE.getPoder()*-1);
                    flechas.remove(i);
                    flechasActivas--;
                    yaDesaparecio=true;
                    break;
                }
            }
            if(yaDesaparecio){
                break;
            }

            for(int j = voladores.size()-1; j>=0; j--){
                Volador volador = voladores.get(j);
                if(flecha.getSprite().getX()>=volador.getX()-volador.getWidth()/2&&flecha.getSprite().getX()<=volador.getX()+volador.getWidth()/2){
                    volador.actualizarVidas(testE.getPoder()*-1);
                    flechas.remove(i);
                    flechasActivas--;
                    yaDesaparecio = true;
                    break;
                }
            }
            if(yaDesaparecio){
                break;
            }

            switch(nivel){
                case 1:
                    if(bossNivel1 !=null){
                        if(flecha.getSprite().getX()>= bossNivel1.getX()- bossNivel1.getWidth()/2&&flecha.getSprite().getX()<= bossNivel1.getX()+ bossNivel1.getWidth()/2){
                            bossNivel1.actualizarVidas(testE.getPoder()*-1);
                            flechas.remove(i);
                            flechasActivas--;
                            yaDesaparecio = true;
                            break;
                        }
                    }
                    break;
                case 2:
                    if(bossNivel2 !=null){
                        if(flecha.getSprite().getX()>= bossNivel2.getX()- bossNivel4.getWidth()/2&&flecha.getSprite().getX()<= bossNivel2.getX()+ bossNivel2.getWidth()/2){
                            bossNivel2.actualizarVidas(testE.getPoder()*-1);
                            flechas.remove(i);
                            flechasActivas--;
                            yaDesaparecio = true;
                            break;
                        }
                    }
                    break;
                case 3:
                    if(bossNivel3 !=null){
                        if(flecha.getSprite().getX()>= bossNivel3.getX()- bossNivel3.getWidth()/2&&flecha.getSprite().getX()<= bossNivel3.getX()+ bossNivel3.getWidth()/2){
                            bossNivel3.actualizarVidas(testE.getPoder()*-1);
                            flechas.remove(i);
                            flechasActivas--;
                            yaDesaparecio = true;
                            break;
                        }
                    }
                    break;
                case 4:
                    if(bossNivel4 !=null){
                        if(flecha.getSprite().getX()>= bossNivel4.getX()- bossNivel4.getWidth()/2&&flecha.getSprite().getX()<= bossNivel4.getX()+ bossNivel4.getWidth()/2){
                            bossNivel4.actualizarVidas(testE.getPoder()*-1);
                            flechas.remove(i);
                            flechasActivas--;
                            yaDesaparecio = true;
                            break;
                        }
                    }
                    break;

            }

            if(yaDesaparecio){
                break;
            }
        }
    }

    private void verificarColisionItems() {
        for(int i = listaItems.size()-1; i>= 0; i--){
            Item item = listaItems.get(i);
            if(testE.getX()<item.getX()+item.getSprite().getWidth()/2 && testE.getX()>item.getX()-item.getSprite().getWidth()/2 && item.getY()<testE.getY()+testE.getHeight() && item.getY()>testE.getY()-testE.getHeight()){
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
        Wrumper w = new Wrumper();
        w.setPos(w.getX(), w.getY()-17);
        if(nivel ==1){
            w.setPos(w.getX(), w.getY()+18);
        }
        enemigos.add(w);
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
        if(bossNivel4 !=null){
            if(bossNivel4.getX()>=testE.getX()-testE.getWidth()/2 && bossNivel4.getX()<= testE.getX()+ testE.getWidth()/2&& bossNivel4 !=null){
                bossNivel4.atacar(testE);
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

            if (keycode== Input.Keys.BACK){
                principal.setScreen(new PantallaMenu(principal));

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