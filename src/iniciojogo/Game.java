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

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	BufferedImage backBuffer;
	int janelaW = 1024;
	int janelaH = 728;
	int aux = 0;

	// AQUI DECLARAMOS O NOSSO MENU COM:
	// 4 itens, coordenadas x e y = 100, a ativo = true
	// agora olhe esse método abaixo cenarios()

	Menu menuPrincipal = new Menu(4, 100, 100, true);
	ImageIcon fundo = new ImageIcon("src/res/menu01.png");

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
			
			//Sobre
			if (menuPrincipal.cenario == 1) {
				bbg.setColor(new Color(0, 0, 0));
				bbg.fillRect(0, 0, janelaW, janelaH);
				bbg.setColor(Color.WHITE);
				bbg.drawString("Projeto TGI - UniDrummond.", 400, 180);
				bbg.drawString("O jogo foi desenvolvido na linguagem java, no estilo nave, para a matéria de POO. ", 130, 220);
				bbg.drawString(" ", 130, 240);
				bbg.drawString("Coordenador do projeto: Profº Osvaldo Prosper - UniDrummond.", 130, 260);
				bbg.drawString(" ", 130, 280);
				bbg.drawString("Desenvolvedores:", 130, 300);
				bbg.drawString("Robson Luiz", 275, 320);
				bbg.drawString("Aline", 275, 340);
				bbg.drawString("Matheus", 275, 360);
			}
			
			//Ajuda
			if (menuPrincipal.cenario == 2) {
				bbg.setColor(new Color(0, 0, 0));
				bbg.fillRect(0, 0, janelaW, janelaH);
				bbg.setColor(Color.WHITE);
				bbg.drawString("Você escolheu Ajuda", 100, 200);
			}
			
			//Sair
			if (menuPrincipal.cenario == 3) {
				System.exit(0);
			}
		}
	}
	
	public void atualizar() {

	}

	public void desenharGraficos() {
		Graphics g = getGraphics(); // ISSO JÁ ESTAVA AQUI
		Graphics bbg = backBuffer.getGraphics();// ISSO TAMBÉM JÁ ESTAVA AQUI...
		bbg.drawImage(fundo.getImage(), 0, 0, this);

		menuPrincipal.desenharMenu();// isso desenhará nosso menu
		cenarios();// isso irá desenhar os cenários que escolhermos no menu
		// agora observe o método inicializar()

		// ==================================================================================
		g.drawImage(backBuffer, 0, 0, this);// OBS: ISSO DEVE FICAR SEMPRE NO FINAL!
	}

	public void inicializar() {

		setTitle("Viking");
		setSize(janelaW, janelaH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);

		backBuffer = new BufferedImage(janelaW, janelaH, BufferedImage.TYPE_INT_RGB);

		// AQUI ESTAMOS ADICIONANDO UM ESCUTADOR DE TECLAS
		addKeyListener(this);

		// aqui definimos o texto de cada item do nosso menu
		menuPrincipal.itens[0] = "Jogar";
		menuPrincipal.itens[1] = "Sobre";
		menuPrincipal.itens[2] = "Ajuda";
		menuPrincipal.itens[3] = "Sair";
		// aqui fazemos o método desenhaMenu() que fica lá em Menu.java
		// desenhar no nosso buffer
		// .. agora para finalizar observe o método de evento keyPressed() mais
		// abaixo...
		menuPrincipal.bbg = backBuffer.getGraphics();
	}
	
	

	public void run() {
		inicializar();
		while (true) {
			atualizar();
			desenharGraficos();
		}
	}

	public void keyPressed(KeyEvent e) {
		menuPrincipal.controlar(e);// esse controla o menu
		menuPrincipal.voltarAoMenu(e);// esse faz voltar para o menu quando pressionarmos "Esc"
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
}
