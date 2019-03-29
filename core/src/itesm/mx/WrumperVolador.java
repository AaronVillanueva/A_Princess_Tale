package itesm.mx;

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
}
