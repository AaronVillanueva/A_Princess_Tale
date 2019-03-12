package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

public class Personaje {
    private Animation animacion; //cuanto tiempo pasa entre frames
    private Sprite sprite;
    private float timerAnimacion;

    //Test
    public Personaje(){
        cargarText("Wrumper/WrumperCorriendo/wrumper_00000.png");
    }

    public void cargarText(String path){
        int frames=1;
        Texture textura=new Texture(path);
        TextureRegion region=new TextureRegion(textura);
        //Para cargar cualquier numero de frames
        TextureRegion[][] texturaPersonaje = region.split(textura.getWidth()/frames,textura.getHeight());
        //animacion = new Animation(0.15f,texturaPersonaje[0][3],texturaPersonaje[0][2],texturaPersonaje[0][1]);
        //animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        // Quieto
        sprite = new Sprite(texturaPersonaje[0][0]);
        sprite.setPosition(0,64);
    }

    public void render(SpriteBatch batch){
        //timerAnimacion+=Gdx.graphics.getDeltaTime();
        //TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
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

    public float getX() {
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }

}
