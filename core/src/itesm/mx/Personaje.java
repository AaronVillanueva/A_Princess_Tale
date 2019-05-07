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
    public float contadorMuerte,contadorMuerteOG;
    public PersonajeEstado estado=PersonajeEstado.quieto;

    //Generar una animacion en base a un path de imagen dado. Requiere el numero de frames y el tipo de animacion.
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

    //Crea una animacion a partir de una textura dividida
    private Animation crearAnimacion(TextureRegion[] text){
        Animation animac = new Animation(0.025f,text);
        animac.setPlayMode(Animation.PlayMode.LOOP);
        return animac;
    }

    public void render(SpriteBatch batch){
        timerAnimacion+=Gdx.graphics.getDeltaTime();
        //Recarga la animacion
        TextureRegion region=(TextureRegion) animacion.getKeyFrame(timerAnimacion);

        //Cambia animaciones segun estado
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
        //timer para animacion de muerte
        if(estado==PersonajeEstado.muriendo){
            contadorMuerte+=timerAnimacion;
            if(contadorMuerteOG==0){
                contadorMuerteOG=contadorMuerte;}
            //System.out.println("");
            //System.out.println(contadorMuerte);
            if((contadorMuerte-contadorMuerteOG)>5){
                estado=PersonajeEstado.muerto;
            }
        }


        batch.draw(region,sprite.getX(),sprite.getY());
    }

    public void moverX(float dx){
        if (estado!=PersonajeEstado.muriendo || estado!=PersonajeEstado.muerto){
            sprite.setPosition(sprite.getX()+dx,sprite.getY());
        }

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
        if(estado!=PersonajeEstado.muriendo && estado!=PersonajeEstado.muerto){
            if(estado!=PersonajeEstado.muerto){
                sprite.setY(y);
                sprite.setX(x);
            }
        }
    }

    public void setEstado(PersonajeEstado estado) {
        this.estado = estado;
    }

    public PersonajeEstado getEstado() {
        return estado;
    }

    public Sprite getSprite(){
        return sprite;
    }

}
