package itesm.mx;

import com.badlogic.gdx.math.MathUtils;

public class Keeper1 extends Wrumper {

    public Keeper1(){
        System.out.println("Se crea un Keeper");
        vidas = 5;
        cargarText("Personajes/Kepper1Running.png", 24, 1);
        setPos(Pantalla.ANCHO+300, Pantalla.ALTO/2-205);

    }
}//
