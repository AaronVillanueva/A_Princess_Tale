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
        textFlecha = new Texture("Arrow.png");
        sprite = new Sprite(textFlecha);
        sprite.setPosition(x, y);
        sprite.setColor(1,1,1,1);
        velocidadX = Pantalla.ANCHO/3;
        this.direccion = direccion;

    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
        System.out.println("Supuestamente dibujo aquí");

    }

    public void moverX(float dt){
        float dx = direccion*3;
        sprite.setX(sprite.getX()+dx);

    }

    public Sprite getSprite(){
        return sprite;
    }
}
