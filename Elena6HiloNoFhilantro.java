package elena6hilonofhilantro;

import static java.lang.Thread.sleep;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Elena6HiloNoFhilantro {

    public static void main(String[] args) throws InterruptedException {

        Caja caja = new Caja();

        Ingresos in = new Ingresos("Ingresos", caja);
        Extraccion ex = new Extraccion("Extraccion", caja);

        in.start();
        ex.start();
        in.join();
        ex.join();
        sleep(Duration.ofSeconds(3));
        System.out.println("Dinero de la empresa = " + caja.getCaja());

    }
}

class Extraccion extends Thread {

    Caja caja;

    public Extraccion(String nombre, Caja caja) {
        super(nombre);
        this.caja = caja;
    }

    public synchronized void run() {
        for (int i = 0; i <= 5; i++) {
				synchronized(caja)
				{
            int money = caja.getCaja();
            try {
                sleep((long) (Math.random() * 10));
            } catch (InterruptedException ex) {
                Logger.getLogger(Extraccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            caja.setCaja(money - 1);
            System.out.println("Se extrajo dinero en la empreza ya van : " + i + " extracciones");
                                }
        }
        System.out.println("El dinero tras extraccion es " + caja.getCaja());
    }
}

class Ingresos extends Thread {

    Caja caja;

    public Ingresos(String nombre, Caja caja) {
        super(nombre);
        this.caja = caja;
    }

    public synchronized void run() {
        for (int i = 0; i <= 10; i++) {
				synchronized(caja)
				{
            int money = caja.getCaja();
            try {
                sleep((long) (Math.random() * 10));
            } catch (InterruptedException ex) {
                Logger.getLogger(Ingresos.class.getName()).log(Level.SEVERE, null, ex);
            }
            caja.setCaja(money + 1);
            System.out.println("Se ingreso dinero en la empreza ya van : " + i + " ingresos");
                                }
        }
        System.out.println("El dinero tras ingreso es " + caja.getCaja());

    }
}

class Caja extends Thread {

    protected int cajaGlobal = 0;

    public int getCaja() {
        return cajaGlobal;
    }

    public void setCaja(int variado) {
        cajaGlobal = variado;
    }
}
