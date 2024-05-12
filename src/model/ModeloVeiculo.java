package model;

import java.util.ArrayList;

public class ModeloVeiculo {

    private String modelo;
    private ArrayList<ConsumoVeiculo> consumoVeiculo;
    private int capacidadeTanque;
    
	public ModeloVeiculo(String modelo, ArrayList<ConsumoVeiculo> consumoVeiculo, int capacidadeTanque) {
		super();
		this.modelo = modelo;
		this.consumoVeiculo = consumoVeiculo;
		this.capacidadeTanque = capacidadeTanque;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public ArrayList<ConsumoVeiculo> getConsumo() {
		return consumoVeiculo;
	}

	public void setConsumo(ArrayList<ConsumoVeiculo> consumoVeiculo) {
		this.consumoVeiculo = consumoVeiculo;
	}

	public int getCapacidadeTanque() {
		return capacidadeTanque;
	}

	public void setCapacidadeTanque(int capacidadeTanque) {
		this.capacidadeTanque = capacidadeTanque;
	}

	@Override
	public String toString() {
		return "ModeloVeiculo [modelo=" + modelo + ", consumoVeiculo=" + consumoVeiculo + ", capacidadeTanque="
				+ capacidadeTanque + "]";
	}
	
	
   
}


