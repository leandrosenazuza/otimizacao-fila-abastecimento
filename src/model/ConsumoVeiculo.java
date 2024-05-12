package model;

public class ConsumoVeiculo {
    private String tipoCombustivel;
    private double consumo;

    public ConsumoVeiculo(String tipoCombustivel, double consumo) {
        this.tipoCombustivel = tipoCombustivel;
        this.consumo = consumo;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

	@Override
	public String toString() {
		return "ConsumoVeiculo [tipoCombustivel=" + tipoCombustivel + ", consumo=" + consumo + "]";
	}

    
}

