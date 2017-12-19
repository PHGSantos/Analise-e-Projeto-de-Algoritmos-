/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedro;

import Felipe.Dijkstra;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Pedro Gomes e Felipe Damasceno
 */
public class Main {
 
    /*
    Formato do Arquivo
        [1 Linha] qtd de vertices
        [2 Linha] qtd de arestas
        [3 Linha] source
        [4 Linha] sink
        [5+ Linhas] arestas
    */
    
    public static void main(String[] args) throws FileNotFoundException {
        int[][] grafo;
        int qtdNodos;
        int source;
        int sink;
        int fluxoMax;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("1 - Algoritmo Ford-Fulkerson");
        System.out.println("2 - Algoritmo de Dijkstra");
        System.out.println("\nEscolha uma das opções acima:");
        int input = scanner.nextInt();
        
        if(input == 1){
            Arquivo arquivo = new Arquivo("grafo.txt");
            grafo = arquivo.read();
        
            qtdNodos = arquivo.getQtdVertices();
            source = arquivo.getSource();
            sink = arquivo.getSink();
        
            FordFulkerson fordFulkerson = new FordFulkerson(qtdNodos, grafo);
            fluxoMax = fordFulkerson.run(source, sink);
        
            System.out.println("O fluxo máximo do grafo é: " + fluxoMax);
        }else if(input == 2){
            Dijkstra dijkstra = new Dijkstra();
            dijkstra.run();
            
        }else{
            System.out.println("Entrada Inválida");
        }
        
    }
    
    
}
