package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public abstract class Item{
    public Sprite sprite;
    public float tiempoTranscurrido;



    public Item(float x, float y, String camino){
        Texture textura = new Texture(camino);
        TextureRegion region = new TextureRegion(textura);
        sprite = new Sprite(region);
        sprite.setPosition(x,y);
        sprite.setSize(100,60);

    }

    public void moverY(int dy){

        sprite.setPosition(sprite.getX(), sprite.getY()+dy);
    }

    public void moverX(int dx){
        sprite.setPosition(sprite.getX()+dx, sprite.getY());
    }

    public void render(SpriteBatch batch){
        //batch.draw(sprite, sprite.getX(), sprite.getY());
        sprite.draw(batch);
    }

    public float getX(){
        return sprite.getX();
    }

    public float getY(){
        return sprite.getY();
    }

    public abstract void generarEfecto(Elya elya);

    public Sprite getSprite() {
        return sprite;
    }
}
