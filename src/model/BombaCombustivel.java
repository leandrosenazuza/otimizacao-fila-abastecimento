package model;

public class BombaCombustivel {
	private String tipoCombustivel;
	private Double volumeAbastecido;
	private Double valorCombustivel;
	private Double velocidadeAbastecimento;
	private Boolean emOperacao;
	private Integer numeroBomba;
	
	public BombaCombustivel(String tipoCombustivel, Double valorCombustivel,
			Double velocidadeAbastecimento, Boolean emOperacao, Integer numeroBomba) {
		super();
		this.tipoCombustivel = tipoCombustivel;
		this.valorCombustivel = valorCombustivel;
		this.velocidadeAbastecimento = velocidadeAbastecimento;
		this.volumeAbastecido = 0.0;
		this.emOperacao = emOperacao;
		this.numeroBomba = numeroBomba;
	}

	public String getTipoCombustivel() {
		return tipoCombustivel;
	}

	public void setTipoCombustivel(String tipoCombustivel) {
		this.tipoCombustivel = tipoCombustivel;
	}

	public Double getVolumeAbastecido() {
		return volumeAbastecido;
	}

	public void setVolumeAbastecido(Double volumeAbastecido) {
		this.volumeAbastecido = volumeAbastecido;
	}

	public Double getValorCombustivel() {
		return valorCombustivel;
	}

	public void setValorCombustivel(Double valorCombustivel) {
		this.valorCombustivel = valorCombustivel;
	}

	public Double getVelocidadeAbastecimento() {
		return velocidadeAbastecimento;
	}

	public void setVelocidadeAbastecimento(Double velocidadeAbastecimento) {
		this.velocidadeAbastecimento = velocidadeAbastecimento;
	}

	public Boolean getEmOperacao() {
		return emOperacao;
	}

	public void setEmOperacao(Boolean emOperacao) {
		this.emOperacao = emOperacao;
	}

	public Integer getNumeroBomba() {
		return numeroBomba;
	}

	public void setNumeroBomba(Integer numeroBomba) {
		this.numeroBomba = numeroBomba;
	}

	@Override
	public String toString() {
		return "BombaCombustivel [tipoCombustivel=" + tipoCombustivel + ", volumeAbastecido=" + volumeAbastecido
				+ ", valorCombustivel=" + valorCombustivel + ", velocidadeAbastecimento=" + velocidadeAbastecimento
				+ ", emOperacao=" + emOperacao + ", numeroBomba=" + numeroBomba + "]";
	}
	

}
