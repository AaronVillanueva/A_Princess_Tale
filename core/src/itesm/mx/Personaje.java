package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

public class Personaje {
    private Animation animacion; //cuanto tiempo pasa entre frames
    private Animation animC,animQ,animA;
    private Sprite sprite;
    private float timerAnimacion;
    private PersonajeEstado estado;


    //Test
    public Personaje(){
        cargarText("Wrumper/WrumperCorriendo/c_1.png");
        sprite.setPosition(10,10);
    }

    public void cargarText(String path){
        int frames=1;
        Texture textura=new Texture(path);
        TextureRegion region=new TextureRegion(textura);
        //Para cargar cualquier numero de frames
        TextureRegion[][] texturaPersonaje = region.split(textura.getWidth()/frames,textura.getHeight());

        //Darle la fila de la animación correspondiente
        crearAnimacion(texturaPersonaje[0],animQ);
        crearAnimacion(texturaPersonaje[0],animC);
        crearAnimacion(texturaPersonaje[0],animA);

        sprite = new Sprite(texturaPersonaje[0][0]);
        sprite.setPosition(0,64);
    }

    private void crearAnimacion(TextureRegion[] text,Animation animac){
        animac = new Animation(0.15f,text);
        animac.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void render(SpriteBatch batch){
        estado=PersonajeEstado.caminando;
        switch (estado){
            case quieto:
                animacion=animQ;
                break;
            case caminando:
                animacion=animC;
                break;
            case atacando:
                animacion=animA;
                break;
        }

        timerAnimacion+=Gdx.graphics.getDeltaTime();
        TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
        batch.draw(sprite,sprite.getX(),sprite.getY());
    }
    public void moverX(float dx){
        sprite.setPosition(sprite.getX()+dx,sprite.getY());
    }
    public void moverY(float dy){
        sprite.setPosition(sprite.getX(),sprite.getY()+dy);
    }
    public void mover(float dx, float dy){
        sprite.setPosition(sprite.getX()+dx,sprite.getY()+dy);
    }

    public float getX(){
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }
    public float getHeight(){
        return sprite.getHeight();
    }
    public float getWidth() {
        return sprite.getWidth();
    }

}