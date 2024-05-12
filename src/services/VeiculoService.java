package services;

import java.util.List;

import model.Veiculo;

public class VeiculoService {
	ArquivoService arquivoService = new ArquivoService();

	public List<Veiculo> retornarVeiculos() {
		return arquivoService.retornarVeiculos();
	}


}
