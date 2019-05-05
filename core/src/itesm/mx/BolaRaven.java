package itesm.mx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BolaRaven{
    private Sprite sprite;
    private Texture texture;
    public BolaRaven(Raven r){
        texture = new Texture("FireBall.png");
        sprite = new Sprite(texture);
        sprite.setPosition(r.getX(), r.getY()+70);


    }
    public void rastrearPrincesa(Raven r, Elya e){
        if(r.getX()>e.getX()){
            sprite.setX(sprite.getX()-1);
        }

        else{
            sprite.setX(sprite.getX()+1);
        }
    }

    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    public float getX(){
        return sprite.getX();
    }
}
