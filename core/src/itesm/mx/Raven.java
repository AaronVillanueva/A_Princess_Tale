package itesm.mx;

public class Raven extends Wrumper {
    public Raven(){
        vidas = 8;
        cargarText("Personajes/RavenRunning.png", 24, 1);
        setPos(Pantalla.ANCHO+300, Pantalla.ALTO/2-205);
    }

    public void encontrarPosicion(Elya elya){
        if(estado!=PersonajeEstado.muriendo){
            if(this.getX() >= elya.getX() + 300){
                dx = -3;
                estado = PersonajeEstado.caminandoReversa;
                this.moverX(dx);
            }
            else if(elya.getX()-300>this.getX()){
                dx = 3;
                estado = PersonajeEstado.caminandoNormal;
                this.moverX(dx);
            }
            }
        }

        public void atacar(NivelGenerico nivel){
            nivel.bolasRaven.add(new BolaRaven(this));

        }

    }



