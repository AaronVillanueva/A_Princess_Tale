package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Wrumper extends Personaje {
    int dx = 5;
    boolean hacerFlip = true;

    private PersonajeEstado estado = PersonajeEstado.caminandoReversa;
    public Wrumper(){
        cargarText("Personajes/WrumperCorriendo.png",24,1);
        setPos(200,200);

    }
    
    public void rastrearPrincesa(Elya elya){

        if(elya.getX()<this.getX()){

            dx = -5;
            estado = PersonajeEstado.caminandoReversa;

        }
        
        else if(elya.getX()>this.getX()){

            dx = 5;
            estado = PersonajeEstado.caminandoNormal;

        }
        
        this.moverX(dx);
        
    }

    public void atacar(Elya elya){
        elya.actualizarVidas(-1);
        setPos(sprite.getX()+150, sprite.getY());

    }

    @Override
    public void render(SpriteBatch batch) {


        animacion=animC;
        timerAnimacion+=Gdx.graphics.getDeltaTime();
        TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
        if(estado==PersonajeEstado.caminandoReversa ){
            region.flip(true, false);
        }
        batch.draw(region,sprite.getX(),sprite.getY());
    }


}
