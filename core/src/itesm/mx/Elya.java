package itesm.mx;

public class Elya extends Personaje {
    private int vidas = 3;
    public Elya(){
        cargarText("Personajes/ElyaRunning1.png",22,1);
        cargarText("Personajes/ElyaAtack.png",22,2);

    }

    public void actualizarVidas(int dVidas){
        this.vidas+=dVidas;
    }

    public int getVidas(){
        return vidas;
    }



}
