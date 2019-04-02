package itesm.mx;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Personaje {
    protected Animation animacion; //cuanto tiempo pasa entre frames
    protected Animation animC,animQ,animA,animD;
    protected Sprite sprite;
    protected float timerAnimacion;
    public float contadorMuerte;
    public PersonajeEstado estado=PersonajeEstado.caminandoNormal;

    //Tipo 0 = quieto, Tipo 1 = Caminando, Tipo 2= Atacando, Tipo 3= Muerte
    public void cargarText(String path,int frames,int tipo){
        Texture textura=new Texture(path);
        TextureRegion region=new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = region.split(textura.getWidth()/frames,textura.getHeight());
        switch (tipo){
            case 0:
                animQ=crearAnimacion(texturaPersonaje[0]);
                break;
            case 1:
                animC=crearAnimacion(texturaPersonaje[0]);
                break;
            case 2:
                animA=crearAnimacion(texturaPersonaje[0]);
                break;
            case 3:
                animD=crearAnimacion(texturaPersonaje[0]);
                break;
        }
        animacion=animC;

        sprite = new Sprite(texturaPersonaje[0][0]);
        sprite.setPosition(0,64);
    }

    private Animation crearAnimacion(TextureRegion[] text){
        Animation animac = new Animation(0.025f,text);
        animac.setPlayMode(Animation.PlayMode.LOOP);
        return animac;
    }

    public void render(SpriteBatch batch){
        //estado=PersonajeEstado.caminandoReversa;
        //animacion=animC;
        timerAnimacion+=Gdx.graphics.getDeltaTime();

        TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);
        switch (estado){
            case caminandoNormal:
                animacion=animC;
                if(region.isFlipX()){
                    region.flip(true, false);
                }
                break;
            case caminandoReversa:
                animacion=animC;
                if(!region.isFlipX()){
                    region.flip(true, false);
                }
                break;
            case atacando:
                animacion=animA;
                break;
            case quieto:
                animacion=animQ;
                break;
            case muriendo:
                contadorMuerte=0;
                animacion=animD;
                break;
        }
        if(estado==PersonajeEstado.muriendo){
            contadorMuerte=timerAnimacion;
        }
        if(contadorMuerte>5){
            estado=PersonajeEstado.muerto;
        }

        batch.draw(region,sprite.getX(),sprite.getY());
    }

    public void moverX(float dx){

        sprite.setPosition(sprite.getX()+dx,sprite.getY());
    }
    public void moverY(float dy){
        sprite.setPosition(sprite.getX(),sprite.getY()+dy);
    }
    public void mover(float dx, float dy){
        sprite.setPosition(sprite.getX()+dx,sprite.getY()+dy);
    }
    public float getX(){
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }
    public float getHeight(){
        return sprite.getHeight();
    }
    public float getWidth() {
        return sprite.getWidth();
    }
    public void setPos(float x,float y){
        sprite.setY(y);
        sprite.setX(x);
    }

    public void setEstado(PersonajeEstado estado) {
        this.estado = estado;
    }

    public Sprite getSprite(){
        return sprite;
    }

}
