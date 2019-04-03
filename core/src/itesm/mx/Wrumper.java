package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Wrumper extends Personaje {
    int dx = 3;
    int vidas = 2;

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
        if (elya.getVidas()==1){
            elya.setEstado(PersonajeEstado.muriendo);}

        elya.actualizarVidas(-1);
        int direc = 1;
        if(estado==PersonajeEstado.caminandoNormal){
            direc = direc *-1;
        }


        setPos(getX()+ 300*direc, getY());




       /* if(estado!=PersonajeEstado.muerto && estado!=PersonajeEstado.muriendo){
            if (elya.getVidas()==1){
            elya.setEstado(PersonajeEstado.muriendo);
            estado=PersonajeEstado.muerto;}
            elya.actualizarVidas(-1);
            if(estado== PersonajeEstado.caminandoReversa || estado==PersonajeEstado.caminandoNormal){
                setPos(sprite.getX()+8000, sprite.getY());
            }
        }

        else{
            estado=PersonajeEstado.muriendo;
            setPos(sprite.getX()-150, sprite.getY());
        }*/

    }

    public int getVidas(){
        return vidas;
    }

    public void actualizarVidas(int dVidas){
        vidas += dVidas;
    }




}
