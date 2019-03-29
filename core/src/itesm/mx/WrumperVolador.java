package itesm.mx;

public class WrumperVolador extends Personaje {
    public WrumperVolador(){
        cargarText("Personajes/WrumperVolando.png",24,1);
        cargarText("Personajes/WrumperFlyingAtack.png",24,2);
        cargarText("Personajes/WrumperAladoDead.png",1,3);
        setPos(1000,600);
    }
}
