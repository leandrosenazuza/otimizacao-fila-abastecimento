package model;

public class Veiculo {
    private ModeloVeiculo modeloVeiculo;
    private String placa;

	public Veiculo(ModeloVeiculo modeloVeiculo, String placa) {
		super();
		this.modeloVeiculo = modeloVeiculo;
		this.placa = placa;
	}
	public ModeloVeiculo getModeloVeiculo() {
		return modeloVeiculo;
	}
	public void setModeloVeiculo(ModeloVeiculo modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

   
      
}
