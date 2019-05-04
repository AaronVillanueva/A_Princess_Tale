package itesm.mx;

import com.badlogic.gdx.math.MathUtils;

public class Volador extends Wrumper {
    public Volador(){
        cargarText("Personajes/WrumperVolando.png", 24, 1);
        vidas = 3;
        int posRandom = MathUtils.random(1);
        if(posRandom==1){
            setPos(Pantalla.ANCHO,Pantalla.ALTO/2-190);
        }
        else{
            setPos(0, Pantalla.ALTO/2-190);
        }
    }
}
