package itesm.mx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class Nube {
    Sprite sprite;
    public Nube(){
        asignarTipo(1);
        setPosition(Pantalla.ANCHO,Pantalla.ALTO/2.0f);
    }
    public Nube(int tipo){
        asignarTipo(tipo);
        setPosition(Pantalla.ANCHO+10,(Pantalla.ALTO)/2);

    }
    public Nube(int tipo,float x,float y){
        asignarTipo(tipo);
        setPosition(x,y);
    }
    public Nube(int tipo,float x,float y,float transparencia) {
        asignarTipo(tipo);
        setPosition(x,y);
        setAlpha(transparencia);
    }

    public void setPosition(float x,float y){
        sprite.setPosition(x,y);
    }
    public void asignarTipo(int tipo){
        String string="Pantallas/Nube"+tipo+".png";
        String string2="Pantallas/Nube1.png";
        //System.out.println(string);
        Texture textNube=new Texture(string);
        TextureRegion regionNube=new TextureRegion(textNube);
        sprite=new Sprite(regionNube);
    }
    public void setAlpha(float a){
        sprite.setAlpha(a);
    }
    public void mover(float velocidad){
        sprite.setX(sprite.getX()-velocidad);
        if(sprite.getX()+sprite.getWidth()<0){
            sprite.setX(Pantalla.ANCHO+10);
            Random random=new Random();
            sprite.setY(sprite.getY()+(random.nextInt(20)-10));
            if(sprite.getY()<Pantalla.ALTO/2){
                sprite.setY(Pantalla.ALTO *(2.0f/3.0f));
            }
        }

    }
    //Despues de begin en render
    public void draw(SpriteBatch batch,float velocidad){
        sprite.draw(batch);
        mover(velocidad);
    }
}
