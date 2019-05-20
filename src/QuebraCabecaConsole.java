/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * 
 * Aplicação console do Quebra-Cabeca
 * 
 */


import java.util.Scanner;



public class QuebraCabecaConsole {
	public static void main(String[] args) {
		
		QuebraCabeca jogo = new QuebraCabeca(5);
		jogo.iniciar(1);
		System.out.println(jogo);
		Scanner teclado = new Scanner(System.in);
		int linhaorigem, colunaorigem, linhadestino, colunadestino;
		while(!jogo.finalizado()) {
			System.out.print("Digite a linha (origem):");
			linhaorigem = teclado.nextInt();
			System.out.print("Digite a coluna (origem): ");
			colunaorigem = teclado.nextInt();
			System.out.print("Digite a linha (destino):");
			linhadestino = teclado.nextInt();
			System.out.print("Digite a coluna (destino): ");
			colunadestino = teclado.nextInt();
			
			try {
				jogo.jogar(linhaorigem,colunaorigem,linhadestino,colunadestino);
				System.out.println(jogo);
				
			} catch (Exception e) {
				System.out.println("\n"+e.getMessage()+"\n");
			}
			
			
		}
		System.out.println("\n GAME OVER !!");
		teclado.close();
	}

}
