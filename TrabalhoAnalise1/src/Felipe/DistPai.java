package Felipe;

public class DistPai {
	private int distancia;
	private int pai;
	
	public DistPai(int distancia, int pai){
		this.setDistancia(distancia);
		this.setPai(pai);
		
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getPai() {
		return pai;
	}

	public void setPai(int pai) {
		this.pai = pai;
	}
	
	
}
