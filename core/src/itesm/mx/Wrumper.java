package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Wrumper extends Personaje {
    int dx = 3;
    boolean hacerFlip = true;

    private PersonajeEstado estado = PersonajeEstado.caminandoReversa;
    public Wrumper(){
        cargarText("Personajes/WrumperCorriendo.png",24,1);
        cargarText("Personajes/WrumperAtack.png",24,2);
        cargarText("Personajes/WrumperDead.png",1,3);
        setPos(MathUtils.random(80, (int)Pantalla.ANCHO/2-80), Pantalla.ALTO/2-205);
    }
    
    public void rastrearPrincesa(Elya elya){

        if(elya.getX()<this.getX()){

            dx = -3;
            estado = PersonajeEstado.caminandoReversa;

        }
        
        else if(elya.getX()>this.getX()){

            dx = 3;
            estado = PersonajeEstado.caminandoNormal;

        }
        
        this.moverX(dx);
        
    }

    public void atacar(Elya elya){
        elya.actualizarVidas(-1);
        if(estado == PersonajeEstado.caminandoReversa){
            setPos(sprite.getX()+150, sprite.getY());
        }

        else{
            setPos(sprite.getX()-150, sprite.getY());
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        animacion=animC;
        timerAnimacion+=Gdx.graphics.getDeltaTime();
        TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
        if(estado==PersonajeEstado.caminandoReversa ){
           if(!region.isFlipX()){
               region.flip(true, false);
           }

           else{

           }
        }

        else if(estado ==PersonajeEstado.caminandoNormal){
            if(region.isFlipX()){
                region.flip(true, false);
            }
        }
        batch.draw(region,sprite.getX(),sprite.getY());
    }


}
