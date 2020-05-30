package iniciojogo;



import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	
	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 1;

	private static int contador = 0;
	
	public Inimigo(int x, int y) {

        this.x = x;
        this.y = y;

        ImageIcon referencia;
        
        if (contador++ % 3 == 0) {

            referencia = new ImageIcon("res/inimigo1.gif");

        } else{

            referencia = new ImageIcon("res/inimigo3.gif");
        }

        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

        isVisivel = true;

    }

	public void update() {
		if (this.x < 0) {
            this.x = LARGURA;
        } else {
            this.x -= VELOCIDADE;
        }
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
}