package itesm.mx;

import com.badlogic.gdx.math.MathUtils;

public class Keeper3 extends Keeper1 {
    public Keeper3() {
        vidas = 7;
        cargarText("Personajes/Keeper3Running.png", 24,1);
        setPos(Pantalla.ANCHO+300, Pantalla.ALTO/2-205);
    }

    public void teletransporte(){
        setPos(MathUtils.random(0, Pantalla.ANCHO), getY());
    }
}
