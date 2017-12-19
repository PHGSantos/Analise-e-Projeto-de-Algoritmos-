package Pedro;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Trabalho da Disciplina: Análise e Projeto de Algoritmos
 * @author Pedro Gomes e Felipe Damasceno
 */
public class FordFulkerson {

    /**
     * V : QUANTIDADE DE VERTICES
     * A : ARESTAS DE UM VERTICE
     * E : TOTAL DE ARESTAS (V * A)
     */
    private int[] pai;
    private Queue<Integer> fila;
    private int qtdVertices;
    private boolean[] visitado;
    // Foi utilizado uma matriz de para o grafo, pois não foi necessário
    // executar uma varredura completa do vetor (fora para inicializá-lo), 
    // nos demais momentos foram efetuados apenas acessos já se conhecia 
    // de antemão sabiamos a possição do elemento, dessa forma, as operações 
    //com o vetor possuiriam tempo de O(1).
    private int grafo[][]; 
    private int [][] grafoResidual;
    
    public FordFulkerson(int qtdVertices, int grafo[][]) { //O(V²)
        this.pai = new int[qtdVertices+1];
        this.fila = new LinkedList<>();
        this.qtdVertices = qtdVertices;
        this.visitado = new boolean[qtdVertices+1];
        this.grafo = grafo;
        
        this.grafoResidual = new int[qtdVertices+1][qtdVertices+1]; //O(1)
        
        for(int verticeInicio = 0; verticeInicio < qtdVertices; verticeInicio++){ //O(V²)
            for(int verticeDestino = 0; verticeDestino < qtdVertices; verticeDestino++){ //O(V)
                grafoResidual[verticeInicio][verticeDestino] = grafo[verticeInicio][verticeDestino];
            }
        }
    }

    
    /*
        Complexidade Total do Método: O(V + E) para o pior caso, 
        em que todas as arestas são exploradas pelo algoritmo.
    
        Obs: as operações add e remove em LinkedList possuem complexidade O(1)
        porque sua implementação pressupõe que sejam realizadas tais operações 
        sempre ao final e ao inicio da lista, respectivamente.
    */
    public boolean buscaEmLargura(int inicio, int fim, int grafo[][]){
        boolean caminhoEncontrado = false; // O(1)
        int destino, elemento; // O(1)
        
        for (int vertice = 0; vertice < this.qtdVertices; vertice++){ // O(V)
            this.pai[vertice] = -1;//O(1)
            this.visitado[vertice] = false;//O(1)
        }
        
        fila.add(inicio);// O(1)
        pai[inicio] = -1;// O(1)
        visitado[inicio] = true;// O(1)
        
        while(!fila.isEmpty()){// O(E)
            elemento = fila.remove();//O(1)
            destino = 0;//O(1)
            while(destino <= qtdVertices){// O(A)
                if(grafo[elemento][destino] > 0 && !visitado[destino]){
                    pai[destino] = elemento;//O(1)
                    fila.add(destino);//O(1)
                    visitado[destino] = true;//O(1)
                }
                destino++;
            }
        }
        
        if(visitado[fim] == true){
            caminhoEncontrado =true;
        }
        
        return caminhoEncontrado;
    }
    
    /*
        Este método executa o algoritmo Ford-Fulkerson.
        
        Seja F o fluxo máximo, Apesar de um caminho de aumento fazer sempre 
        o fluxo aumentar, existe sempre a possibilidade dele aumentar muito 
        pouco. Por exemplo, caso os números sejam inteiros, poderia aumentar
        apenas 1 de cada vez, pelo que a complexidade final do algoritmo 
        é a quantidade de fluxo máximo vezes a complexidade da busca em largura,
        dessa forma a complexidade final ficou de O((E+V)*F).
    */
    public int run(int inicio, int fim){
        int u,v;//O(1)
        int fluxoMax = 0; //O(1)
        int fluxoCaminho; //O(1)
        
        
        while(buscaEmLargura(inicio,fim,grafoResidual)){ //O((E+V)*F)
            fluxoCaminho = Integer.MAX_VALUE;
            for(v = fim; v != inicio; v = pai[v]){//O(V)
                u = pai[v];
                fluxoCaminho = Math.min(fluxoCaminho, grafoResidual[u][v]);
            }
            for(v = fim; v!=inicio ; v =pai[v]){ // O(V)
                u = pai[v];
                grafoResidual[u][v] -= fluxoCaminho;
                grafoResidual[v][u] += fluxoCaminho;
            }
            fluxoMax += fluxoCaminho;
        }
        
        return fluxoMax;
        
    }
    
    public int[] getPai() {
        return pai;
    }

    public void setPai(int[] pai) {
        this.pai = pai;
    }

    public Queue<Integer> getFila() {
        return fila;
    }

    public void setFila(Queue<Integer> fila) {
        this.fila = fila;
    }

    public int getQtdVertices() {
        return qtdVertices;
    }

    public void setQtdVertices(int qtdVertices) {
        this.qtdVertices = qtdVertices;
    }

    public boolean[] getVisitado() {
        return visitado;
    }

    public void setVisitado(boolean[] visitado) {
        this.visitado = visitado;
    }
    
    
    
}
