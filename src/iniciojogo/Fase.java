package iniciojogo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase<jogador> extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Image fundo;
	private Jogador jogador;
	private Timer timer;
	private List<Inimigo> inimigo;
	private List<Estrelas> estrelas;

	private boolean emJogo;
	private boolean ganhou;

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());

		ImageIcon referencia = new ImageIcon("src/res/fundo01.png");
		fundo = referencia.getImage();
		jogador = new Jogador();

		emJogo = true;
		ganhou = false;
		inicializaInimigos();
		inicializaEstrelas();

		timer = new Timer(5, this);
		timer.start();

	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[30];
		inimigo = new ArrayList<Inimigo>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 6000 + 1024);
			int y = (int) (Math.random() * 610 + 30);
			inimigo.add(new Inimigo(x, y));

		}
	}

	public void inicializaEstrelas() {
		int coordenadas[] = new int[30];
		estrelas = new ArrayList<Estrelas>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 1024 + 0);
			int y = (int) (Math.random() * 728 + 0);
			estrelas.add(new Estrelas(x, y));
		}
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, this);

		if (emJogo) {

			graficos.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);
			List<Tiro> tiros = jogador.getTiros();

			for (int p = 0; p < estrelas.size(); p++) {
				Estrelas q = estrelas.get(p);
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}

			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int o = 0; o < inimigo.size(); o++) {
				Inimigo in = (Inimigo) inimigo.get(o);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

			graficos.setColor(Color.WHITE);
			graficos.drawString("INIMIGOS: " + inimigo.size(), 5, 15);

			if (inimigo.size() <= 0) {

				ganhou = true;

			}
		} else if (emJogo == false && ganhou == false) {

			ImageIcon fimJogo = new ImageIcon("src/res/fimdejogo2.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, this);

		}
		
		if (ganhou == true) {
			
			emJogo = false;

			ImageIcon ganhou = new ImageIcon("src/res/vitoria1.png");
			graficos.drawImage(ganhou.getImage(), 0, 0, this);

		}

		g.dispose();

		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador.update();
		for (int p = 0; p < estrelas.size(); p++) {
			Estrelas on = estrelas.get(p);
			if (on.isVisivel()) {
				on.update();
			} else {
				estrelas.remove(p);

			}

		}

		List<Tiro> tiros = jogador.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();

			} else {
				tiros.remove(i);
			}
		}

		for (int o = 0; o < inimigo.size(); o++) {
			Inimigo in = inimigo.get(o);
			if (in.isVisivel()) {
				in.update();
			} else {
				inimigo.remove(o);

			}

			checarColisoes();
			repaint();

		}
	}

	public void checarColisoes() {
		Rectangle formaNave = jogador.getBounds();
		Rectangle formainimigo;
		Rectangle formatiro;

		for (int i = 0; i < inimigo.size(); i++) {
			Inimigo tempinimigo = inimigo.get(i);
			formainimigo = tempinimigo.getBounds();
			if (formaNave.intersects(formainimigo)) {
				jogador.setVisivel(false);
				tempinimigo.setVisivel(false);
				emJogo = false;

			}

		}
		List<Tiro> tiros = jogador.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formatiro = tempTiro.getBounds();
			for (int o = 0; o < inimigo.size(); o++) {
				Inimigo tempinimigo = inimigo.get(o);
				formainimigo = tempinimigo.getBounds();
				if (formatiro.intersects(formainimigo)) {
					tempinimigo.setVisivel(false);
					tempTiro.setVisivel(false);
				}

			}

		}
	}

	public List<Inimigo> getinimigo() {
		return inimigo;
	}

	public void setinimigo(List<Inimigo> inimigo) {
		this.inimigo = inimigo;
	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				if (emJogo == false || ganhou == true) {
					
				    ganhou = false;
					emJogo = true;
					jogador = new Jogador();
					inicializaInimigos();
					inicializaEstrelas();
				}

			}
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				
				System.exit(0);
			}

			jogador.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			jogador.keyReleased(e);
		}

	}
}
