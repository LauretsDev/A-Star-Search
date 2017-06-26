package diego.aestrela;

public class Nodo {

	private Estado estadoAtual;
	private Nodo pai;
	private double custo; // custo até esse nodo
	private double custoH; // custo heuristico
	private double custoF; // custo f(n)

	public Nodo(Estado s) {
		estadoAtual = s;
		pai = null;
		custo = 0;
		custoH = 0;
		custoF = 0;
	}

	public Nodo(Nodo prev, Estado s, double c, double h) {
		pai = prev;
		estadoAtual = s;
		custo = c;
		custoH = h;
		custoF = custo + custoH;
	}

	public Estado getEstadoAtual() {
		return estadoAtual;
	}

	public Nodo getPai() {
		return pai;
	}

	public double getCusto() {
		return custo;
	}

	public double getCustoH() {
		return custoH;
	}

	public double getCustoF() {
		return custoF;
	}
}
