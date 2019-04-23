package itesm.mx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Flecha {
    private float velocidadX;
    private Texture textFlecha;
    private Sprite sprite;
    int direccion = 1;


    public Flecha(float x, float y, Elya elya, int direccion){
        textFlecha = new Texture("Arrow2.png");
        sprite = new Sprite(textFlecha);
        sprite.setPosition(x, y);
        sprite.setColor(1,1,1,1);
        velocidadX = Pantalla.ANCHO/3;
        this.direccion = direccion;

    }

    public void render(SpriteBatch batch){
        if(direccion<0){
            if(!sprite.isFlipX()){
                sprite.flip(true, false);
            }
        }
        sprite.draw(batch);


    }

    public void moverX(float dt){
        float dx = direccion*3;
        sprite.setX(sprite.getX()+dx);


    }

    public Sprite getSprite(){
        return sprite;
    }
}
