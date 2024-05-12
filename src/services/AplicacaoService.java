package services;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import model.*;

/*
 * Inicio da Aplicação e chamada dos serviços inerentes a lógica do desafio.
 * 
 * */

public class AplicacaoService {
	
	List<ModeloVeiculo> listaModeloVeiculosCarregados = new ArrayList<ModeloVeiculo>();	
	List<Veiculo> listaVeiculosCarregados = new ArrayList<Veiculo>();
	List<BombaCombustivel> listaBombasCombustivel = new ArrayList<BombaCombustivel>();

	
	ArquivoService arquivoService = new ArquivoService();
	AbastecimentoService abastecimentoService = new AbastecimentoService();
	CombustivelService combustivelService = new CombustivelService();
	BombaService bombaService = new BombaService();
	VeiculoService veiculoService = new VeiculoService();
	
	public void inicioAplicacao() {
		
		// Iniciando as Bombas funcionais
		listaBombasCombustivel = bombaService.iniciarBombasOperação();

		PostoCombustivel postoCombustivel = new PostoCombustivel(listaBombasCombustivel);
		
		// Aqui são executadas todas as rotinas para ler os arquivos e carregar os objetos para cumprir a rotina.
		listaVeiculosCarregados = veiculoService.retornarVeiculos();
		
		// Carregado a lista de veículos, será realizado o abastecimento
		abastecimentoService.operacao(listaVeiculosCarregados, postoCombustivel.getListaBombasPosto());

	}
	


}
