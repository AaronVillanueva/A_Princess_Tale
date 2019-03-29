package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Wrumper extends Personaje {
    int dx = 3;
    boolean hacerFlip = true;

    public Wrumper(){
        cargarText("Personajes/WrumperCorriendo.png",24,1);
        cargarText("Personajes/WrumperAtack.png",24,2);
        cargarText("Personajes/WrumperDead.png",1,3);
        setPos(MathUtils.random(80, (int)Pantalla.ANCHO/2-80), Pantalla.ALTO/2-205);
    }
    
    public void rastrearPrincesa(Elya elya){
        if(estado!=PersonajeEstado.muriendo){
            if(elya.getX()<this.getX()){
                dx = -3;
                estado = PersonajeEstado.caminandoReversa;
            }
            else if(elya.getX()>this.getX()){
                dx = 3;
                estado = PersonajeEstado.caminandoNormal;
            }
            this.moverX(dx);}
    }

    public void atacar(Elya elya){
        elya.setEstado(PersonajeEstado.muriendo);
        estado=PersonajeEstado.muriendo;
        elya.actualizarVidas(-1);
        if(estado == PersonajeEstado.caminandoReversa){
            estado=PersonajeEstado.muriendo;
            setPos(sprite.getX()+150, sprite.getY());
        }

        else{
            estado=PersonajeEstado.muriendo;
            setPos(sprite.getX()-150, sprite.getY());
        }

    }




}
