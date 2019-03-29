package itesm.mx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Elya extends Personaje {
    private int vidas = 3;
    private PersonajeEstado estado = PersonajeEstado.caminandoNormal;
    private int poder = 1;

    public Elya(){
        cargarText("Personajes/ElyaRunning1.png",22,1);
        cargarText("Personajes/ElyaAtack.png",22,2);
        cargarText("Personajes/ElyaDead.png",1,3);

    }

    public void actualizarVidas(int dVidas){
        this.vidas+=dVidas;
    }

    public int getVidas(){
        return vidas;
    }

    public void setEstado(PersonajeEstado estado){
        this.estado = estado;
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

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getPoder(){
        return this.poder;
    }
}
