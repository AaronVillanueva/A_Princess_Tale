package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WrumperVolador extends Personaje {
    private PersonajeEstado estado = PersonajeEstado.caminandoReversa;
    int dx = 3;
    public WrumperVolador(){
        cargarText("Personajes/WrumperVolando.png",24,1);
        setPos(1000,600);
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
