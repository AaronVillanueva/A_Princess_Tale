package itesm.mx;
//
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;

public class PantallaAyuda extends Pantalla implements Screen {

    public PantallaAyuda(Principal principal){this.principal=principal;}

    @Override
    public void show() {
        inicializarShow();
        crearFondo("Pantallas/PantallaAyuda.png");
        crearRegreso();
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(textFondo, 0, 0);
        spriteRegresar.draw(batch);
        batch.end();
        checarRegreso();
    }

    private void checarRegreso() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            principal.setScreen(new PantallaMenu(principal));
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
}
