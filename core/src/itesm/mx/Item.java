package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public abstract class Item{
    public Sprite sprite;


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
        System.out.println("Sí llegué aquí: " + sprite.getX() + ", " + sprite.getY());
        sprite.draw(batch);
    }

    public abstract void generarEfecto(Personaje personaje);
}
