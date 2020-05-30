package iniciojogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class Menu {
	public int cenario = -1; 
						
	public int itemSelecionado = 0; 
								
	public String itens[]; 
	public Graphics bbg; 
					
	boolean ativo; 
					
	int x; 
	int y; 
	int tamanhoDaFonte = 20;
	int distanciaEntreItens = 15;
	Font fonte = new Font("Arial", Font.BOLD, tamanhoDaFonte);
	Color corSelecionado = new Color(255, 0, 0); 
	Color corNaoSelecionado = new Color(255,255,255); 
	
	public Menu(int numeroDeItens, int x, int y, boolean ativo) {
		itens = new String[numeroDeItens];
		this.x = x;
		this.y = y;
		this.ativo = ativo;
	}

	
	public void controlar(KeyEvent e) {
		if (ativo) {
			controlarMenu(e);
		}
	}

	
	public void voltarAoMenu(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			cenario = -1; 
			ativo = true; 
		}
	}

	
	private void controlarMenu(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			itemSelecionado -= 1;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			itemSelecionado += 1;
		}
		
		if (itemSelecionado >= itens.length) {
			itemSelecionado = 0;
		}
		if (itemSelecionado < 0) {
			itemSelecionado = itens.length - 1;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			cenario = itemSelecionado;
			ativo = false;
		}
	}

	
	public void desenharMenu() {
		bbg.setFont(fonte);
		for (int i = 0; i < itens.length; i++) {
			if (itemSelecionado == i) {
				bbg.setColor(corSelecionado);
				bbg.drawString(itens[i], x, y + (i * (tamanhoDaFonte + distanciaEntreItens)));
			} else {
				bbg.setColor(corNaoSelecionado);
				bbg.drawString(itens[i], x, y + (i * (tamanhoDaFonte + distanciaEntreItens)));
			}
		}
	}

}
