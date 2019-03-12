package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class TestNivel1 extends Pantalla implements Screen {

    private Texture textPrincesa;
    private Sprite spritePrincesa;
    private Texture textStar;
    private Sprite spriteEstrella;
    private Texture textCereza;
    private Sprite spriteCereza;

    public TestNivel1(Principal principal){this.principal=principal;}
    @Override
    public void show() {
        inicializarShow();
        crearFondo("Pantallas/Nivel1.PNG");
        textCereza = new Texture("ITEMS/Cereza.png");
        spriteCereza = new Sprite(textCereza);
        textStar = new Texture("ITEMS/Star.png");
        spriteEstrella = new Sprite(textStar);
        textPrincesa = new Texture("Pantallas/Princesa.png");
        spritePrincesa = new Sprite(textPrincesa);
        spriteEstrella.setSize(100,75);
        spriteEstrella.setPosition(600, ALTO/2-115);
        spriteCereza.setSize(100, 75);
        spriteCereza.setPosition(200, ALTO/2-115);
        spritePrincesa.setSize(150, 200);
        spritePrincesa.setPosition(30, ALTO/2-170);
        Gdx.input.setInputProcessor(new ProcesadorEntradaJuego());

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(textFondo, 0, 0);

        spritePrincesa.draw(batch);
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
            if(keycode==Input.Keys.RIGHT && spritePrincesa.getX()<ANCHO){
                spritePrincesa.setPosition(spritePrincesa.getX()+10, spritePrincesa.getY());
            }

            else if(keycode==Input.Keys.LEFT && spritePrincesa.getX()>0){
                spritePrincesa.setPosition(spritePrincesa.getX()-10, spritePrincesa.getY());
            }
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

            Vector3 v3 = new Vector3(screenX, screenY,0);
            camara.unproject(v3);
            if(v3.x > 0 && v3.x < 150 && v3.y< ALTO && v3.y>ALTO-150){
                principal.setScreen(new PantallaMenu(principal));
            }
            if(v3.x>ANCHO/2 && spritePrincesa.getX()<ANCHO-100){
                spritePrincesa.setPosition(spritePrincesa.getX()+10, spritePrincesa.getY());
            }

            else if(v3.x<ANCHO/2 && spritePrincesa.getX()>15){
                spritePrincesa.setPosition(spritePrincesa.getX()-10, spritePrincesa.getY());
            }

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


