package Felipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Pedro Gomes e Felipe Damasceno
 */
public class Arquivo {
    
    private Scanner scanner;
    private int qtdVertices;
    private int qtdArestas;
    private final int INFINITO = 1000000;
 
    private int[][] grafo;
    
    public Arquivo(String fileName) throws FileNotFoundException{
        this.scanner = new Scanner(new File(fileName));
    }
    
    public void start(int n){
        
        this.grafo = new int[n][n];
        
        for (int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                this.grafo[i][j] = INFINITO;
            }
        }

    }
    
    public int[][] read(){
       
        this.qtdVertices = Integer.parseInt(scanner.nextLine());
        this.qtdArestas = Integer.parseInt(scanner.nextLine());
        
        start(qtdVertices);
        
        for(int i = 0; i < qtdArestas; i++){
            
            String linha = scanner.nextLine();
            
            String split[] = linha.split(" ");
            
            int verticeInicio = Integer.parseInt(split[0]);
            int verticeDestino = Integer.parseInt(split[1]);
            int capacidade = Integer.parseInt(split[2]);
            
            grafo[verticeInicio][verticeDestino] = capacidade;
        }
        scanner.close();
        return grafo;
    }
    
    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getQtdVertices() {
        return qtdVertices;
    }

    public void setQtdVertices(int qtdVertices) {
        this.qtdVertices = qtdVertices;
    }

    public int getQtdArestas() {
        return qtdArestas;
    }

    public void setQtdArestas(int qtdArestas) {
        this.qtdArestas = qtdArestas;
    }

    public int[][] getGrafo() {
        return grafo;
    }

    public void setGrafo(int[][] grafo) {
        this.grafo = grafo;
    }
    

    
}
