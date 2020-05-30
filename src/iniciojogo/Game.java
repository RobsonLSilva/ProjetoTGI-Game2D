package iniciojogo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	BufferedImage backBuffer;
	int janelaW = 1024;
	int janelaH = 728;
	int aux = 0;

	Menu menuPrincipal = new Menu(4, 100, 100, true);
	ImageIcon fundo = new ImageIcon("res/menu01.png");

	@SuppressWarnings("rawtypes")
	public void cenarios() {

		Graphics bbg = backBuffer.getGraphics();
		bbg.setFont(new Font("Arial", Font.BOLD, 14));

		if (aux == 0) {

			if (menuPrincipal.cenario == 0) {

				dispose();

				JFrame frame2 = new JFrame();

				frame2.add(new Fase());
				frame2.setTitle("Viking");
				frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
				frame2.setSize(janelaW, janelaH);
				frame2.setLocationRelativeTo(null);
				frame2.setResizable(false);
				frame2.setVisible(true);
				aux = 1;

			}

			// Sobre
			if (menuPrincipal.cenario == 1) {
				
				ImageIcon logo = new ImageIcon("res/logo.png");
				bbg.setFont(new Font("Arial", Font.BOLD, 24));
				bbg.setColor(new Color(0, 0, 0));
				bbg.fillRect(0, 0, janelaW, janelaH);
				bbg.setColor(Color.WHITE);
				bbg.drawString("Projeto TGI - UniDrummond.", 320, 130);
				bbg.setFont(new Font("Arial", Font.BOLD, 18));
				bbg.drawString("O jogo foi desenvolvido na linguagem java, no estilo nave, para a matéria de POO. ", 105, 220);
				bbg.drawString("Coordenador do projeto: ", 105, 260);
				bbg.setColor(Color.CYAN);
				bbg.drawString("Prof. Osvaldo Prosper - UniDrummond.", 360, 260);
				bbg.setColor(Color.WHITE);
				bbg.drawString("Desenvolvedores: ", 105, 300);
				bbg.setColor(Color.CYAN);
				bbg.drawString("Robson Luiz", 295, 300);
				bbg.drawString("Aline Magalhães", 295, 325);
				bbg.drawString("Matheus Gonzaga", 295, 350);
				bbg.drawImage(logo.getImage(), 400, 400, this);
				bbg.setColor(Color.WHITE);
				bbg.drawString("Retornar: Esc", 5, 720);
			}

			// Ajuda
			if (menuPrincipal.cenario == 2) {
				ImageIcon setas = new ImageIcon("res/setas.png");
				ImageIcon espaco = new ImageIcon("res/barraEspaco.png");
				ImageIcon esc = new ImageIcon("res/esc.png");
				bbg.setFont(new Font("Arial", Font.BOLD, 18));
				bbg.setColor(new Color(0, 0, 0));
				bbg.fillRect(0, 0, janelaW, janelaH);
				bbg.setColor(Color.WHITE);
				bbg.drawString("Para mover nosso herois utilize as teclas: ", 50, 100);
				bbg.drawString("Para atirar utilize a tecla: ", 600, 100);
				bbg.drawString("Para sair a qualquer momento do jogo, precione a tecla: ", 230, 380);
				bbg.drawImage(esc.getImage(), 480, 420, this);
				bbg.drawImage(espaco.getImage(), 610, 150, this);
				bbg.drawImage(setas.getImage(), 150, 120, this);
				
			}

			// Sair
			if (menuPrincipal.cenario == 3) {
				System.exit(0);
			}
		}
	}

	public void desenharGraficos() {

		Graphics g = getGraphics();
		Graphics bbg = backBuffer.getGraphics();
		bbg.drawImage(fundo.getImage(), 0, 0, this);

		menuPrincipal.desenharMenu();
		cenarios();

		g.drawImage(backBuffer, 0, 0, this);
	}

	public void inicializar() {

		Som.play();

		setTitle("Viking");
		setSize(janelaW, janelaH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);

		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);

		addKeyListener(this);

		menuPrincipal.itens[0] = "Jogar";
		menuPrincipal.itens[1] = "Sobre";
		menuPrincipal.itens[2] = "Ajuda";
		menuPrincipal.itens[3] = "Sair";

		menuPrincipal.bbg = backBuffer.getGraphics();

	}

	public void run() {
		inicializar();
		while (true) {
			desenharGraficos();
		}
	}

	public void keyPressed(KeyEvent e) {

		menuPrincipal.controlar(e);
		menuPrincipal.voltarAoMenu(e);

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}
