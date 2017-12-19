package Felipe;

public class Grafo {
	private int maxVertices;

	private boolean vizinhos[];
	private int adjMat[][];
	private int nVertices;
	private int nArvore;
	private DistPai caminhos[];
	private int verticeAtual;
	private int inicioParaAtual;
	private final int INFINITO = 1000000;


	public Grafo(int adjMat[][], int max){
		maxVertices = max;
		vizinhos = new boolean[maxVertices];
		this.adjMat = adjMat;
		nVertices = maxVertices;
		nArvore = 0;
		
		caminhos = new DistPai[maxVertices];
	}
	
	public void run(){
		int inicioArvore = 0;
		vizinhos[inicioArvore] = true;
		nArvore = 1;
		for (int j=0; j<nVertices; j++){
			int distTemp = adjMat[inicioArvore][j];
			caminhos[j] = new DistPai (distTemp, inicioArvore);
			
		}
		while(nArvore < nVertices){
			int indiceMin = getMin();
			int minDist = caminhos[indiceMin].getDistancia();
			
			if(minDist != INFINITO){
				verticeAtual = indiceMin;
				inicioParaAtual = caminhos[indiceMin].getDistancia();
			}
			vizinhos[verticeAtual] = true;
			nArvore++;
			relaxamento();
		}
		
		displayCaminhos();
		nArvore = 0;
		for (int i = 0; i < nVertices; i++){
			vizinhos[i] = false;
		}
		
	}
	
	private int getMin(){
		int minDist = INFINITO;
		int indiceMin = 0;
		for (int i = 0; i < nVertices; i++){
			if (!vizinhos[i] && caminhos[i].getDistancia() < minDist){
				minDist = caminhos[i].getDistancia();
				indiceMin = i;
			}
		}
		return  indiceMin;
		
	}
	
	public void relaxamento(){
		int coluna = 1;
		while (coluna < nVertices){
			if (vizinhos[coluna]){
				coluna++;
				continue;
			}
			int distAtual = adjMat[verticeAtual][coluna];
			int distInicio = inicioParaAtual + distAtual;
			int distCaminho = caminhos[coluna].getDistancia();
			
			if (distInicio < distCaminho)
			{
				caminhos[coluna].setDistancia(distInicio);
				caminhos[coluna].setPai(verticeAtual);
			}
			coluna++;
		}
	}
	
	public void displayCaminhos(){
		for (int j=0; j<maxVertices; j++){
			System.out.print(j + "=");
			if(caminhos[j].getDistancia() == INFINITO){
				System.out.print("inf ");
				
			}
			else{
				System.out.print(caminhos[j].getDistancia());
				int pai = caminhos[j].getPai();
				System.out.print("[" + pai + "] ");
			}
		}
		System.out.println("");
	}
}