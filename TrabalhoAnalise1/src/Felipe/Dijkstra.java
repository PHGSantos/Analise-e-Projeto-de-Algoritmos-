package Felipe;

import java.io.FileNotFoundException;

import Felipe.Arquivo;
import Felipe.Grafo;

public class Dijkstra {
	public void run() throws FileNotFoundException {
		Arquivo arquivo = new Arquivo("dijkstra");
		arquivo.read();
		Grafo grafo = new Grafo(arquivo.getGrafo(), arquivo.getQtdVertices());
		grafo.run();
	}
}
