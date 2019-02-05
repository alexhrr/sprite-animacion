package colision;

import javax.swing.JComponent;

import javax.swing.JPanel;

import javax.swing.ImageIcon;

import java.awt.Graphics;

import javax.swing.JOptionPane;


public class Colision extends JComponent {

    static JPanel panel;

    static int columna = 3;

    static int fila = 195;

    static int numero = 1;

    static boolean x = true;
    

    Colision(JPanel panel) {
        this.panel = panel;

        setBounds(0, 0, panel.getWidth(), panel.getHeight());

    }

    public void paint(Graphics g) {
        ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("imagenes/" + numero + ".png")).getImage());

        g.drawImage(imagen.getImage(), columna, fila, 150, 150, null);

    }

    static Thread hilo = new Thread() {
        @Override
        public void run() {
            try {
                while (x) {
                    numero++;

                    if (numero == 4) {
                        numero = 1;
                    }

                    panel.repaint();

                    columna += 10;

                    if (columna >= 450) {
                        x = false;
                        JOptionPane.showMessageDialog(null, "OUCHHHHH");
                        System.exit(0);
                    }

                    hilo.sleep(110);
                }

            } catch (java.lang.InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    };

    public static void mover() {
        if (!hilo.isAlive()) {
            hilo.start();
        }
        columna = 3;
    }
}
