package model;

public class Combustivel {
	private String tipoCombustivel;
	private Double precoCombustivel;
	private Double vazao;
	
	public Combustivel(String tipoCombustivel, Double precoCombustivel, Double vazao) {
		super();
		this.tipoCombustivel = tipoCombustivel;
		this.precoCombustivel = precoCombustivel;
		this.vazao = vazao;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public Double getPrecoCombustivel() {
		return precoCombustivel;
	}

	public void setPrecoCombustivel(Double precoCombustivel) {
		this.precoCombustivel = precoCombustivel;
	}

	public Double getVazao() {
		return vazao;
	}

	public void setVazao(Double vazao) {
		this.vazao = vazao;
	}
	
}
