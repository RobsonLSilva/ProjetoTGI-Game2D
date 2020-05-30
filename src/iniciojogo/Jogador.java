package iniciojogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Jogador {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List<Tiro> tiros;
	private boolean isVisivel;

	public Jogador() {
		this.x = 100;
		this.y = 728 / 2;
		//isVisivel = true;

		tiros = new ArrayList<Tiro>();
		ImageIcon referencia = new ImageIcon("res/jogador04.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getHeight(null);
	}


	public void update() {
		//System.out.println(x+", "+y);
		x += dx;
		y += dy;

		if (this.x < -1) {
            x = -1;
        }

        if (this.x > 929) {
            x = 929;
        }

        if (this.y < -1) {
            y = -1;
        }

        if (this.y > 611) {
            y = 611	;
        }
	}

	public void tiroSimples() {
		this.tiros.add(new Tiro(x + largura, y + altura / 3));
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public void keyPressed(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_SPACE) {
			tiroSimples();
		}

		if (codigo == KeyEvent.VK_UP) {
			dy = -1;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 1;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = -1;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		
	}

	public void keyReleased(KeyEvent tecla) {

		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {

			dy = 0;

		}
		if (codigo == KeyEvent.VK_DOWN) {

			dy = 0;

		}

		if (codigo == KeyEvent.VK_LEFT) {

			dx = 0;

		}

		if (codigo == KeyEvent.VK_RIGHT) {

			dx = 0;

		}
	}

}