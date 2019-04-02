package itesm.mx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Flecha {
    private float velocidadX;
    private Texture textFlecha;
    private Sprite sprite;

    public Flecha(float x, float y, Elya elya){
        textFlecha = new Texture("Arrow.png");
        sprite = new Sprite(textFlecha);
        sprite.setPosition(x, y);


    }
}
