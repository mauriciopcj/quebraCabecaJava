/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * 
 * Alunos: Lucas Sales
 *         Maurício Pereira
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class QuebraCabeca extends JPanel{
	private int[][] matriz;					//matriz com numeros 
	private final int DIMENSAO;				//dimensao do grid
	private int totaljogadas;

	private JLabel[][] grid1;  				//exibe para usuario a ordem correta
	private JLabel[][] grid2;  				//exibe para usuario fora da ordem
	private int TAMANHO;					//largura/altura de cada label em pixels
	private final int XINICIAL = 10;		//localização do grid1
	private final int YINICIAL = 10;		//localização do grid1
	private final int ESPACAMENTO = 10; 	//espaço entre os dois grids
	private JLabel labelTotal;
	private JLabel labelResultado;
	private JLabel primeiroselecionado;		//referencia o primeiro label clicado
	private int totalselecionados;
	
	private String difficulty;				//armazena o nível de dificuldade escolhido
	private Random ran = new Random();		//utilizado para gerar número random
	private int x;							//armazena o número random gerado

	public QuebraCabeca(int dimensao) {
	
		this.DIMENSAO = dimensao;
		this.TAMANHO = 400 / dimensao;
		this.matriz = new int[dimensao][dimensao];
		this.setLayout(null);
		this.setSize(XINICIAL + ESPACAMENTO + 2 * DIMENSAO * TAMANHO, YINICIAL + DIMENSAO * TAMANHO + 80 );		//2*largura e altura do painel
		
	}

	public void iniciar() {
		totaljogadas = 0;		
		totalselecionados = 0;
		this.removeAll();	//REMOVER TODOS COMPONENTES VISUAIS
		this.grid1 = new JLabel[DIMENSAO][DIMENSAO];
		this.grid2 = new JLabel[DIMENSAO][DIMENSAO];
		
		labelTotal = new JLabel("Total Moves:\n " + totaljogadas); //label para mostrar o total de jogadas
		labelTotal.setSize(400, 50);
		labelTotal.setLocation(10, 420);
		labelTotal.setForeground(new Color(255,255,255));
		labelTotal.setBackground(new Color(51, 51, 51));
		labelTotal.setHorizontalAlignment(SwingConstants.CENTER);
		labelTotal.setFont(new Font("Showcard Gothic", Font.PLAIN, 14));
		labelTotal.setOpaque(true);
		this.add(labelTotal);
		
		labelResultado = new JLabel("Select two cells to change position"); //label para informar que o usurário deve selecionar duas peças para trocar
		labelResultado.setSize(400, 50);
		labelResultado.setLocation(420, 420);
		labelResultado.setForeground(new Color(255,255,255));
		labelResultado.setBackground(new Color(51, 51, 51));
		labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
		labelResultado.setFont(new Font("Showcard Gothic", Font.PLAIN, 14));
		labelResultado.setOpaque(true);
		this.add(labelResultado);
		
		this.repaint();		//redesenhar todos os componentes
		
		pegaDificuldade();		
		x = ran.nextInt(10)+1; //gera número random para complementar o endereço da imagem a ser utilizada no quebracabeça
		
		ordenarConteudo();
		desenharGridOrdenado();
		embaralharConteudo();
		desenharGridDesordenado();
		
	}
	
	public void iniciar(int a) {
		totaljogadas = 0;
		ordenarConteudo();
		embaralharConteudo();
		labelTotal = new JLabel("Total Moves:\n " + totaljogadas);
	}

	private void ordenarConteudo() {
		int contador = 1;
		for(int linha = 0; linha < DIMENSAO; linha++) {
			for(int coluna = 0; coluna < DIMENSAO; coluna++) {
				matriz[linha][coluna] = contador;
				contador++;
			}
		}
	}

	private void embaralharConteudo() {
		ArrayList<String>  numeros = new ArrayList<String>();	//Cria um array
		for(int linha = 0; linha < DIMENSAO; linha++) {			//Adiciona os números da matríz no array numeros
			for(int coluna = 0; coluna < DIMENSAO; coluna++) {
				numeros.add(""+matriz[linha][coluna]);
			}
		}
		Collections.shuffle(numeros);							//embaralha o array numeros
		int contador = 0;
		for(int linha = 0; linha < DIMENSAO; linha++) {			//adiciona os números embaralhados na matriz
			for(int coluna = 0; coluna < DIMENSAO; coluna++) {
				matriz[linha][coluna] = Integer.parseInt(numeros.get(contador));
				contador++;
			}
		}
	}
	
	private void pegaDificuldade() {
		if (DIMENSAO == 4) {
			difficulty = "easy";
		} else if (DIMENSAO == 5) {
			difficulty = "normal";
		} else if (DIMENSAO == 8){
			difficulty = "hard";
		}
	}
	
	private void desenharGridOrdenado() {
		//desenhar o grid1 a partir do ponto (xinicial,yinicial)
		for(int linha = 0; linha < DIMENSAO; linha++){
			for(int coluna = 0; coluna < DIMENSAO; coluna++){
				grid1[linha][coluna] = new JLabel("", 0);
				this.add(grid1[linha][coluna]);
				grid1[linha][coluna].setSize(TAMANHO, TAMANHO);
				grid1[linha][coluna].setLocation(XINICIAL+coluna*TAMANHO, YINICIAL+linha*TAMANHO);
				grid1[linha][coluna].setIcon(new ImageIcon(QuebraCabeca.class.getResource(
							"/img/"+ difficulty + "/" + x + "/" + matriz[linha][coluna] + ".jpg")));
			}
		}
	}
	
	private void desenharGridDesordenado() {
		//coordenadas do inicio do grid2
		int xinicial2 = XINICIAL + DIMENSAO*TAMANHO + ESPACAMENTO; 	//espacamento entre os grids
		int yinicial2 = YINICIAL;	
		
		for(int linha = 0; linha < DIMENSAO; linha++){
			for(int coluna = 0; coluna < DIMENSAO; coluna++){
				grid2[linha][coluna]=new JLabel("", 0);
				this.add(grid2[linha][coluna]);
				grid2[linha][coluna].setSize(TAMANHO, TAMANHO);
				grid2[linha][coluna].setLocation(xinicial2+coluna*TAMANHO, yinicial2+linha*TAMANHO);
				grid2[linha][coluna].setIcon(new ImageIcon(QuebraCabeca.class.getResource(
							"/img/"+ difficulty + "/" + x + "/" + matriz[linha][coluna] + ".jpg")));
				grid2[linha][coluna].addMouseListener(
						new  MouseAdapter(){
							private int lin1, col1, lin2, col2;
							public void mouseClicked(MouseEvent e){
								//obter o componente label que recebeu o click
								JLabel labelselecionado = (JLabel)e.getSource();
								labelselecionado.setBorder(new LineBorder(new Color(255,255,51),3,true));
								totalselecionados++;

								if(totalselecionados == 1) 	//PRIMEIRO LABEL SELECIONADO 
									primeiroselecionado = labelselecionado;
								else
									if(totalselecionados == 2 && primeiroselecionado != labelselecionado) { //SEGUNDO LABEL SELECIONADO
										col1 = (labelselecionado.getX() - xinicial2) / TAMANHO ;
										lin1 = (labelselecionado.getY() - yinicial2) / TAMANHO ;
										col2 = (primeiroselecionado.getX() - xinicial2) / TAMANHO;
										lin2 = (primeiroselecionado.getY() - yinicial2) / TAMANHO;
										try {
											jogar(lin1, col1, lin2, col2);
											totalselecionados = 0;
											int colaux = primeiroselecionado.getX();
											int linaux = primeiroselecionado.getY();
											primeiroselecionado.setBorder(null);
											primeiroselecionado.setLocation(labelselecionado.getX(), labelselecionado.getY());
											labelselecionado.setLocation(colaux, linaux);
											labelselecionado.setBorder(null);
										}
										catch (Exception a) {
											a.printStackTrace();
										}
										
									} else {
										primeiroselecionado.setBorder(null);
										totalselecionados = 0;
									}
								if(finalizado()) {
									labelResultado.setText("FINISHED! - Restart");
									labelResultado.setBackground(new Color(0, 153, 153));
									//remover os ouvintes MouseListener de cada label
									for(int linha=0; linha < DIMENSAO; linha++) {
										for(int coluna=0; coluna < DIMENSAO; coluna++) {
											for(MouseListener m : grid2[linha][coluna].getMouseListeners())
												grid2[linha][coluna].removeMouseListener(m);
										}
									}
								}
							}//mouseclicked
						});
			}
		}
	}
	
	public String toString() {
		String texto = "";
		texto += "\n----------Quebra-Cabeça:-----------\n    ";
		for(int x = 0; x < DIMENSAO; x++) texto += "   " + x;
		texto += "\n    ";
		for(int x = 0; x < DIMENSAO; x++) texto += "____"; 
		texto += "\n";

		//exibir a matriz
		String numeroformatado;
		for(int linha = 0; linha < DIMENSAO; linha++) {
			texto += linha + "  |";
			for(int coluna = 0; coluna < DIMENSAO; coluna++) {
				numeroformatado = matriz[linha][coluna] + "";
				if(matriz[linha][coluna] < 10) 
					numeroformatado = " " + numeroformatado;
				texto += "  " + numeroformatado;
			}
			texto += "\n";
		}

		texto += "    ";
		for(int x=0; x < DIMENSAO; x++) texto += "----";
		texto += "\nTotal Moves:" + this.getTotalJogadas() + "\n";

		return texto;
	}
	
	public boolean finalizado() {
		boolean result = true;
		int contador = 1;
		for(int linha = 0; linha < DIMENSAO; linha++) {
			for(int coluna = 0; coluna < DIMENSAO; coluna++) {
				if (matriz[linha][coluna] != contador) {
					result = false;
				}
				contador++;
			}
		}
		return result;
	}

	public void jogar(int linha1, int coluna1, int linha2, int coluna2) throws Exception {
		if(linha1 <= this.DIMENSAO-1 && linha1 >= 0 && coluna1 <= this.DIMENSAO-1 && coluna1 >= 0 && 
				linha2 <= this.DIMENSAO-1 && linha2 >= 0 && coluna2 <= this.DIMENSAO-1 && coluna2 >= 0) {
			totaljogadas++;
			labelTotal.setText("Total Moves: " + totaljogadas);
			int origem = matriz[linha1][coluna1];
			int destino = matriz[linha2][coluna2];
			matriz[linha2][coluna2] = origem;
			matriz[linha1][coluna1] = destino;
		}
		else {
			throw new Exception("valor das linhas ou colunas fora de alcance");
		}
	}

	public int getTotalJogadas() {
		return totaljogadas;
	}
	
	public int getNumero(int linha, int coluna) {
		return matriz[linha][coluna] ;
	}

	public int getDimensao() {
		return DIMENSAO;
	}
}