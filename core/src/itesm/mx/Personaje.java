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
    public Animation animC,animQ,animA;
    public Sprite sprite;
    private float timerAnimacion;
    private PersonajeEstado estado;


    public void cargarText(String path,int frames){
        Texture textura=new Texture(path);
        TextureRegion region=new TextureRegion(textura);
        //Para cargar cualquier numero de frames
        TextureRegion[][] texturaPersonaje = region.split(textura.getWidth()/frames,textura.getHeight());
        //Darle la fila de la animación correspondiente
        //crearAnimacion(texturaPersonaje[0],animQ);
        animC = new Animation(0.15f,texturaPersonaje[0]);
        animC.setPlayMode(Animation.PlayMode.LOOP);

        //crearAnimacion(texturaPersonaje[0],animA);
        sprite = new Sprite(texturaPersonaje[0][1]);
        sprite.setPosition(0,64);
    }

    private void crearAnimacion(TextureRegion[] text,Animation animac){

        animac = new Animation(0.15f,text);
        animac.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void render(SpriteBatch batch){
        estado=PersonajeEstado.caminando;
        animacion=animC;

        timerAnimacion+=Gdx.graphics.getDeltaTime();
        TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
        batch.draw(region,sprite.getX(),sprite.getY());
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
    public void setPos(float x,float y){
        sprite.setY(y);
        sprite.setX(x);
    }
}