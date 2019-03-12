package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public abstract class Item{

    protected Sprite sprite;
    private Animation animacion; //cuanto tiempo pasa entre frames
    private float timerAnimacion;
    protected Personaje princesa;

    public Item(float x, float y, String path, Personaje princesa){
        Texture textura = new Texture(path);
        TextureRegion region = new TextureRegion(textura);
        sprite.setPosition(x,y);
        timerAnimacion = 0;
        this.princesa = princesa;
    }

    public void render(SpriteBatch batch){
        timerAnimacion += Gdx.graphics.getDeltaTime();
        TextureRegion region = (TextureRegion) animacion.getKeyFrame(timerAnimacion);
        batch.draw(region, sprite.getX(), sprite.getY());
    }

    public void moverX(float dx){
        sprite.setPosition(sprite.getX()+dx, sprite.getY());

    }

    public float getX() {
        return sprite.getX();
    }

    public void caer(){
        if(sprite.getY()>300){
            sprite.setPosition(sprite.getX(), sprite.getY()-1);
        }
    }

    public abstract void efectoItem();



}
