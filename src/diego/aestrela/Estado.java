package diego.aestrela;

import java.util.ArrayList;

public interface Estado {
	
	// se atingiu o objetivo
	boolean isObjetivo();

	// gerar sucessores do estado atual
	ArrayList<Estado> gerarSucessores();

	// retornar custo de ir para este estado, sempre 1
	double retornarCusto();

	// imprimir o estado atual
	public void imprimirEstado();

	// comparar estados
	public boolean equals(Estado s);
	
	// quantos quadrados estao fora do lugar no estado inicial
	public int getForaDoLugar();
}
