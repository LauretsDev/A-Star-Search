package diego.aestrela;

import java.util.ArrayList;
import java.util.Arrays;

public class EstadoTabuleiro implements Estado {

	private final int TAMANHO_TABULEIRO = 9;
	private final int[] OBJETIVO = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 0 };
	
	private int foraDoLugar = 0;
	private int distanciaManhattan = 0;
	private int[] tabuleiroAtual;

	
	public EstadoTabuleiro(int[] tabuleiro) {
		tabuleiroAtual = tabuleiro;
		calcularForaDoLugar();
		calcularDistanciaManhattan();
	}

	@Override
	public double retornarCusto() {
		return 1;
	}

	private void calcularForaDoLugar() {
		for (int i = 0; i < tabuleiroAtual.length; i++) {
			if (tabuleiroAtual[i] != OBJETIVO[i]) {
				foraDoLugar++;
			}
		}
	}

	private void calcularDistanciaManhattan() {
		int index = -1;

		//nao da pra usar posicao zero entao alocar todos 1 para direita
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				index++;

				//subtrair 1 para pegar onde o valor é para estar
				int val = (tabuleiroAtual[index] - 1);

				if (val != -1) {
					//modulo do valor pela dimensao horizontal
					int horiz = val % 3;
					//dividir valor pela dimensao vertical
					int vert = val / 3;

					distanciaManhattan += Math.abs(vert - (y)) + Math.abs(horiz - (x));
				}
				
			}
		}
	}

	private int acharBranco() {
		int indexBranco = -1;

		for (int i = 0; i < TAMANHO_TABULEIRO; i++)
		{
			if (tabuleiroAtual[i] == 0)
				indexBranco = i;
		}
		return indexBranco;
	}

	@Override
	public int getForaDoLugar() {
		return foraDoLugar;
	}

	public int getDistanciaManhattan() {
		return distanciaManhattan;
	}

	private int[] copiarTabuleiro(int[] state) {
		int[] ret = new int[TAMANHO_TABULEIRO];
		for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
			ret[i] = state[i];
		}
		return ret;
	}

	@Override
	public ArrayList<Estado> gerarSucessores()
	{
		ArrayList<Estado> sucessores = new ArrayList<Estado>();
		int branco = acharBranco();

		/*  0 1 2
		 *  3 4 5
		 *  6 7 8
		 */
		
		// esquerda do branco
		if (branco != 0 && branco != 3 && branco != 6)
		{
			swapAndStore(branco - 1, branco, sucessores);
		}

		// em cima do branco
		if (branco != 6 && branco != 7 && branco != 8)
		{
			swapAndStore(branco + 3, branco, sucessores);
		}

		// de baixo do branco
		if (branco != 0 && branco != 1 && branco != 2)
		{
			swapAndStore(branco - 3, branco, sucessores);
		}
		// direita do branco
		if (branco != 2 && branco != 5 && branco != 8)
		{
			swapAndStore(branco + 1, branco, sucessores);
		}

		return sucessores;
	}

	private void swapAndStore(int d1, int d2, ArrayList<Estado> s)
	{
		int[] cpy = copiarTabuleiro(tabuleiroAtual);
		int temp = cpy[d1];
		cpy[d1] = tabuleiroAtual[d2];
		cpy[d2] = temp;
		s.add((new EstadoTabuleiro(cpy)));
	}

	@Override
	public boolean isObjetivo() {
		if (Arrays.equals(tabuleiroAtual, OBJETIVO)) {
			return true;
		}
		return false;
	}

	@Override
	public void imprimirEstado() {
		System.out.println(tabuleiroAtual[0] + " | " + tabuleiroAtual[1] + " | "
				+ tabuleiroAtual[2]);
		System.out.println("---------");
		System.out.println(tabuleiroAtual[3] + " | " + tabuleiroAtual[4] + " | "
				+ tabuleiroAtual[5]);
		System.out.println("---------");
		System.out.println(tabuleiroAtual[6] + " | " + tabuleiroAtual[7] + " | "
				+ tabuleiroAtual[8]);

	}

	@Override
	public boolean equals(Estado s) {
		if (Arrays.equals(tabuleiroAtual, ((EstadoTabuleiro) s).getTabuleiroAtual())) {
			return true;
		}
		else
			return false;
	}

	public int[] getTabuleiroAtual() {
		return tabuleiroAtual;
	}

}
