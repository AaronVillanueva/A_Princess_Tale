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
    protected Animation animacion; //cuanto tiempo pasa entre frames
    protected Animation animC,animQ,animA;
    protected Sprite sprite;
    protected float timerAnimacion;
    private PersonajeEstado estado;


    public void cargarText(String path,int frames,int tipo){
        Texture textura=new Texture(path);
        TextureRegion region=new TextureRegion(textura);
        //Para cargar cualquier numero de frames
        TextureRegion[][] texturaPersonaje = region.split(textura.getWidth()/frames,textura.getHeight());
        //Darle la fila de la animación correspondiente
        //crearAnimacion(texturaPersonaje[0],animQ);
        switch (tipo){
            case 1:
                animC=crearAnimacion(texturaPersonaje[0]);
                break;
            case 2:
                animA=crearAnimacion(texturaPersonaje[0]);
                break;
            case 0:
                animQ=crearAnimacion(texturaPersonaje[0]);
                break;
        }


        sprite = new Sprite(texturaPersonaje[0][1]);
        sprite.setPosition(0,64);
    }

    private Animation crearAnimacion(TextureRegion[] text){
        Animation animac = new Animation(0.025f,text);
        animac.setPlayMode(Animation.PlayMode.LOOP);
        return animac;
    }

    public void render(SpriteBatch batch){
        estado=PersonajeEstado.caminandoNormal;
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

    public Sprite getSprite(){
        return sprite;
    }

}
