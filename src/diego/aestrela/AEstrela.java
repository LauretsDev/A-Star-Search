package diego.aestrela;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AEstrela {
	public static void buscar(int[] tabuleiro) {
		
		Nodo raiz = new Nodo(new EstadoTabuleiro(tabuleiro));
		Queue<Nodo> q = new LinkedList<Nodo>();
		q.add(raiz);

		int counterIteracao = 1;

		while (!q.isEmpty())  {
			Nodo tempNodo = (Nodo) q.poll();

			if (!tempNodo.getEstadoAtual().isObjetivo()) {
				ArrayList<Estado> tempSucessores = tempNodo.getEstadoAtual().gerarSucessores(); // gerar sucessores imediatos
				ArrayList<Nodo> nodosSucessores = new ArrayList<Nodo>();

				for (int i = 0; i < tempSucessores.size(); i++) {
					Nodo nodoVisitado;
					nodoVisitado = new Nodo(tempNodo, tempSucessores.get(i), tempNodo.getCusto() + tempSucessores.get(i).retornarCusto(), ((EstadoTabuleiro) tempSucessores.get(i)).getDistanciaManhattan());
					if (!checarNodoJaVisitado(nodoVisitado)) {
						nodosSucessores.add(nodoVisitado);
					}
				}
				
				if (nodosSucessores.size() == 0) {
					continue;
				}

				Nodo lowestNode = nodosSucessores.get(0);

				// checar nodo com menor valor f(n)
				for (int i = 0; i < nodosSucessores.size(); i++) {
					if (lowestNode.getCustoF() > nodosSucessores.get(i).getCustoF()) {
						lowestNode = nodosSucessores.get(i);
					}
				}

				int lowestValue = (int) lowestNode.getCustoF();

				// adicionar todos os nodos que tem esse mesmo menor valor
				for (int i = 0; i < nodosSucessores.size(); i++) {
					if (nodosSucessores.get(i).getCustoF() == lowestValue) {
						q.add(nodosSucessores.get(i));
					}
				}

				counterIteracao++;
			}
			else {
				Stack<Nodo> solucao = new Stack<Nodo>();
				solucao.push(tempNodo);
				tempNodo = tempNodo.getPai();

				while (tempNodo.getPai() != null) {
					solucao.push(tempNodo);
					tempNodo = tempNodo.getPai();
				}
				solucao.push(tempNodo);

				int tamanhoSolucao = solucao.size();

				for (int i = 0; i < tamanhoSolucao; i++) {
					tempNodo = solucao.pop();
					tempNodo.getEstadoAtual().imprimirEstado();
					System.out.println();
					System.out.println();
				}
				
				System.out.println("Número de quadrados fora do lugar (h1): "+ raiz.getEstadoAtual().getForaDoLugar());
				System.out.println("Número de passos até o objetivo (h2): " + tempNodo.getCusto());
				System.out.println("Número de nodos visitados: "+ counterIteracao);
				
				System.exit(0);
			}
		}

		System.out.println("Não conseguiu encontrar uma solução");
	}

	private static boolean checarNodoJaVisitado(Nodo n) {
		boolean visitado = false;
		Nodo nodoCheck = n;

		while (n.getPai() != null && !visitado) {
			if (n.getPai().getEstadoAtual().equals(nodoCheck.getEstadoAtual())) {
				visitado = true;
			}
			n = n.getPai();
		}

		return visitado;
	}

}
