package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Wrumper extends Personaje {
    int dx = 0;
    private PersonajeEstado estado = PersonajeEstado.caminandoReversa;
    public Wrumper(){
        cargarText("Personajes/WrumperCorriendo.png",24);
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

    @Override
    public void render(SpriteBatch batch) {

        if(estado==PersonajeEstado.caminandoNormal){
            animacion=animC;
            timerAnimacion+=Gdx.graphics.getDeltaTime();
            TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
            batch.draw(region,sprite.getX(),sprite.getY());
        }


    }
}
