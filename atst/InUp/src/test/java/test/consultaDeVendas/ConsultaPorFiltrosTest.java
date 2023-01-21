package test.consultaDeVendas;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.ConsultaDeVendasPO;
import test.BaseTest;

/** Classe de testes utilizando os filtros para a pagina de Consulta de Vendas */
public class ConsultaPorFiltrosTest extends BaseTest{

    //#region Regiao dos atributos
	private static ConsultaDeVendasPO consultaDeVendasPO;
	//#endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes(){
		consultaDeVendasPO = new ConsultaDeVendasPO(driver);
		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
	}

	@Before
	public void selecionarDatas(){
		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("1", "Jan", "2019");
		consultaDeVendasPO.selecionarDataFinal("22", "Abr", "2022");
	
		consultaDeVendasPO.selecionarFiliais("NORTE SHOPPING");
		consultaDeVendasPO.inputBoleta.click();
	}

	//#region Regiao dos testes.

	/** Teste para buscar as vendas pelo nome do cliente. */
	@Test
	public void T0001_deve_consultar_vendas_por_nome_do_cliente() {
		consultaDeVendasPO.selecionarNomeAutocomplete(consultaDeVendasPO.inputCliente, "Lucas Padilha");
		consultaDeVendasPO.pesquisarVendas();
		
		Assert.assertEquals("LUCAS PADILHA", consultaDeVendasPO.nomeClienteListagem.getText());
		consultaDeVendasPO.limparCampoInput(consultaDeVendasPO.inputCliente);

		consultaDeVendasPO.selecionarNomeAutocomplete(consultaDeVendasPO.inputCliente, "Karina Paim");
		consultaDeVendasPO.pesquisarVendas();
		
		Assert.assertEquals("KARINA PAIM", consultaDeVendasPO.nomeClienteListagem.getText());
		
		consultaDeVendasPO.limparCampoInput(consultaDeVendasPO.inputCliente);
	}

	/** Teste para buscar as vendas pelo numero da boleta. */
	@Test
	public void T0002_deve_consultar_vendas_por_boleta() {
		consultaDeVendasPO.buscarCodigoVenda("E01021");

		Assert.assertEquals("E01021", consultaDeVendasPO.numeroBoletaListagem.getText());
		
		consultaDeVendasPO.retirarSelecaoFiliais("NORTE SHOPPING");
		consultaDeVendasPO.selecionarFiliais("ESCRITORIO");

		consultaDeVendasPO.buscarCodigoVenda("026478");
		consultaDeVendasPO.aguardarTextoNoElemento(consultaDeVendasPO.numeroBoletaListagem, "026478");
		
		Assert.assertEquals("026478", consultaDeVendasPO.numeroBoletaListagem.getText());
		
		consultaDeVendasPO.limparCampoInput(consultaDeVendasPO.inputBoleta);
	}

	/** Teste para buscar as vendas pela filial. */
	@Test
	public void T0003_deve_consultar_vendas_por_filial() {
	   	consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("NORTE SHOPPING", consultaDeVendasPO.nomeFilialListagem.getText());

        consultaDeVendasPO.retirarSelecaoFiliais("NORTE SHOPPING");
		
		consultaDeVendasPO.selecionarFiliais("DEFEITO");
		consultaDeVendasPO.pesquisarVendas();
		consultaDeVendasPO.aguardarTextoNoElemento(consultaDeVendasPO.nomeFilialListagem, "DEFEITO");

		Assert.assertEquals("DEFEITO", consultaDeVendasPO.nomeFilialListagem.getText());

		consultaDeVendasPO.retirarSelecaoFiliais("DEFEITO");

		consultaDeVendasPO.selecionarFiliais("FASHION MALL");
		consultaDeVendasPO.pesquisarVendas();
		consultaDeVendasPO.aguardarTextoNoElemento(consultaDeVendasPO.nomeFilialListagem, "FASHION MALL");

		Assert.assertEquals("FASHION MALL", consultaDeVendasPO.nomeFilialListagem.getText());

		consultaDeVendasPO.retirarSelecaoFiliais("FASHION MALL");
	}	
	
	/** Teste para confirmar os dados da venda realizada pelo CashNet. */
	@Test
	public void T0004_deve_confirmar_dados_boleta() {
		consultaDeVendasPO.buscarCodigoVenda("905608");

		Assert.assertEquals("905608", consultaDeVendasPO.numeroBoletaListagem.getText());

		consultaDeVendasPO.botaoExpandirDetalhesVenda.click();

		Assert.assertEquals("Boleta: 905608", consultaDeVendasPO.codigoBoleta.getText());
		Assert.assertEquals("Cliente: WOLVERINE", consultaDeVendasPO.nomeCliente.getText());
		Assert.assertEquals("Valor da Venda: R$ 45,90", consultaDeVendasPO.valorDaVenda.getText());
	}

	/** Teste para confirmar os dados da venda realizada pelo CashNet no Resumo. */
	@Test
	public void T0005_deve_confirmar_dados_resumo() {
		consultaDeVendasPO.buscarCodigoVenda("905608");

		Assert.assertEquals("905608", consultaDeVendasPO.numeroBoletaListagem.getText());

		double valorTotal = Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		double valorDesconto = Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Desconto"));
		double valorPago = valorTotal - valorDesconto;

		Assert.assertEquals(Double.toString(valorPago), consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));
		
		Assert.assertEquals("45.90", consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		Assert.assertEquals("4.59", consultaDeVendasPO.obterValorResumoVenda("Desconto"));
		Assert.assertEquals("41.31", consultaDeVendasPO.obterValorResumoVenda("Dinheiro"));

		consultaDeVendasPO.buscarCodigoVenda("E01027");

		Assert.assertEquals("E01027", consultaDeVendasPO.numeroBoletaListagem.getText());

		double valorTotal2 = Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		double valorDesconto2 = Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Desconto"));
		double valorPago2 = valorTotal2 - valorDesconto2;

		Assert.assertEquals(Double.toString(valorPago2), consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));
		
		Assert.assertEquals("80.90", consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		Assert.assertEquals("5.59", consultaDeVendasPO.obterValorResumoVenda("Desconto"));
		Assert.assertEquals("75.31", consultaDeVendasPO.obterValorResumoVenda("Cartão"));
		Assert.assertEquals("25.00", consultaDeVendasPO.obterValorResumoVenda("Frete"));
	}

	/** Teste para pesquisar vendas excluídas. */
	//@Ignore ("IN-9616")
	@Test
	public void T0006_deve_pesquisar_vendas_excluidas() {
		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("1", "Jan", "2019");
		consultaDeVendasPO.selecionarDataFinal("22", "Abr", "2022");
	
		consultaDeVendasPO.selecionarFiliais("NORTE SHOPPING");
		consultaDeVendasPO.inputBoleta.click();

		consultaDeVendasPO.checkboxVendasExcluidas.click();
	
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Cancelado", consultaDeVendasPO.statusListagem.getText());

		consultaDeVendasPO.retirarSelecaoFiliais("NORTE SHOPPING");
		consultaDeVendasPO.selecionarFiliais("FASHION MALL");

		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Cancelado", consultaDeVendasPO.statusListagem.getText());

		consultaDeVendasPO.retirarSelecaoFiliais("FASHION MALL");
	}

    /** Deve consultar por tipo de venda. */
	@Test
	public void T0007_deve_conferir_tipo_de_venda_na_listagem() {
		List<WebElement> lista = consultaDeVendasPO.selectTipoDeVendas.findElements(By.tagName("option"));

		consultaDeVendasPO.selecionarTipoDeVenda(2);
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("NFC-e", lista.get(2).getText());
		Assert.assertEquals("NFC-e", consultaDeVendasPO.tipoDeVendaListagem.getText());
		
		consultaDeVendasPO.selecionarTipoDeVenda(3);
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Nota Manual", lista.get(3).getText());
		Assert.assertEquals("Nota Manual", consultaDeVendasPO.tipoDeVendaListagem.getText());

		consultaDeVendasPO.selecionarTipoDeVenda(4);
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Nota manual C/Nº", lista.get(4).getText());
		Assert.assertEquals("Nota Manual", consultaDeVendasPO.tipoDeVendaListagem.getText());

		consultaDeVendasPO.selecionarTipoDeVenda(7);
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Pedido de Venda", lista.get(7).getText());
		Assert.assertEquals("Pedidos", consultaDeVendasPO.tipoDeVendaListagem.getText());

		consultaDeVendasPO.selecionarFiliais("FASHION MALL");

		consultaDeVendasPO.selecionarTipoDeVenda(5);
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Nota manual S/Nº", lista.get(5).getText());
		Assert.assertEquals("Nota Manual", consultaDeVendasPO.tipoDeVendaListagem.getText());

		consultaDeVendasPO.selecionarFiliais("ESCRITORIO");

		consultaDeVendasPO.selecionarTipoDeVenda(9);
		consultaDeVendasPO.pesquisarVendas();

		Assert.assertEquals("Retiradas", lista.get(9).getText());
		Assert.assertEquals("Retirada", consultaDeVendasPO.tipoDeVendaListagem.getText());
	}

	/** Deve conferir total de vendas na listagem e no resumo de um determinado periodo. */
	@Test
	public void T0008_deve_conferir_total_de_vendas_na_listagem_com_o_resumo() {
		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.botaoCalendario.click();
    
		consultaDeVendasPO.selecionarDataInicial("6", "Jan", "2020");
		consultaDeVendasPO.selecionarDataFinal("7", "Jan", "2020");
	
		consultaDeVendasPO.selecionarFiliais("FASHION MALL");
		consultaDeVendasPO.pesquisarVendas();
		consultaDeVendasPO.aguardarVisibilidadeDoElemento(driver, 20, consultaDeVendasPO.numeroBoletaListagem);
		
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Total de Venda"), consultaDeVendasPO.somaDosValoresDaListagem());
	
		consultaDeVendasPO.retirarSelecaoFiliais("FASHION MALL");
	}

	/** Deve conferir valores no resumo de um determinado periodo. */
	@Test
	public void T0009_deve_conferir_valores_do_resumo_do_periodo() {
		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("23", "Ago", "2021");
		consultaDeVendasPO.selecionarDataFinal("23", "Ago", "2021");
	
		consultaDeVendasPO.selecionarFiliais("NORTE SHOPPING");

		consultaDeVendasPO.pesquisarVendas();

		Double valorRecebidoNorteShopping = Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Total de Venda")) - Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Desconto"));

		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Frete"), consultaDeVendasPO.somaDosValoresDoCard("Frete"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Desconto"), consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Dinheiro"), consultaDeVendasPO.somaDosValoresDoCard("Dinheiro"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Cartão"), consultaDeVendasPO.somaDosValoresDoCard("Cartão"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Total de Venda"), consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda"));
		Assert.assertEquals(String.valueOf(valorRecebidoNorteShopping), consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));
	}

	/** Deve conferir valores no resumo de um determinado periodo. */
	@Ignore ("IN-9117 Dados de cheque não possui e IN-9119 Dados de cartão zerado")
	@Test
	public void T0010_deve_conferir_valores_do_resumo_do_periodo() {
		consultaDeVendasPO.atualizarPagina(5);
		
		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("2", "Jun", "2020");
		consultaDeVendasPO.selecionarDataFinal("2", "Jun", "2020");
	
		consultaDeVendasPO.selecionarFiliais("LJ CAMPINAS");
		consultaDeVendasPO.pesquisarVendas();

		Double valorRecebidoCampinas = Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Total de Venda")) - Double.valueOf(consultaDeVendasPO.obterValorResumoVenda("Desconto"));

		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Frete"), consultaDeVendasPO.somaDosValoresDoCard("Frete"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Desconto"), consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Dinheiro"), consultaDeVendasPO.somaDosValoresDoCard("Dinheiro"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Cartão"), consultaDeVendasPO.somaDosValoresDoCard("Cartão"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Total de Venda"), consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda"));
		Assert.assertEquals(String.valueOf(valorRecebidoCampinas), consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));
	}

	/** Deve conferir valores no resumo de trocas. */
	@Test
	public void T0011_deve_conferir_valores_do_resumo_de_troca() {
		consultaDeVendasPO.retirarSelecaoFiliais("NORTE SHOPPING");
		consultaDeVendasPO.selecionarFiliais("FASHION MALL");
		
		consultaDeVendasPO.buscarCodigoVenda("005398");
		
		Assert.assertEquals(consultaDeVendasPO.somaDosValoresDaListagem(), consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		Assert.assertEquals("0.00", consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));

		consultaDeVendasPO.buscarCodigoVenda("005423");
		
		Assert.assertEquals(consultaDeVendasPO.somaDosValoresDaListagem(), consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		Assert.assertEquals("0.00", consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));
	}

	/** Teste para conferir valores de vendas excluídas de troca. */
	@Test
	public void T0012_deve_conferir_valores_resumo_vendas_excluidas_de_troca() {
		consultaDeVendasPO.retirarSelecaoFiliais("NORTE SHOPPING");
		consultaDeVendasPO.selecionarFiliais("FASHION MALL");

		consultaDeVendasPO.inputBoleta.click();

		consultaDeVendasPO.checkboxVendasExcluidas.click();
				
		consultaDeVendasPO.buscarCodigoVenda("005453");

		Assert.assertEquals("Cancelado", consultaDeVendasPO.statusListagem.getText());
		Assert.assertEquals(consultaDeVendasPO.somaDosValoresDaListagem(), consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		Assert.assertEquals("0.00", consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));

		consultaDeVendasPO.buscarCodigoVenda("005463");
		
		Assert.assertEquals("Cancelado", consultaDeVendasPO.statusListagem.getText());
		Assert.assertEquals(consultaDeVendasPO.somaDosValoresDaListagem(), consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
		Assert.assertEquals("0.00", consultaDeVendasPO.obterValorResumoVenda("Total Recebido"));
	}

	/** Teste para conferir valores de vendas excluídas pelo periodo. 
	 * @throws InterruptedException */
	@Test
	public void T0012_deve_conferir_valores_resumo_vendas_excluidas_periodo() throws InterruptedException {
		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.checkboxVendasExcluidas.click();

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("3", "Mai", "2021");
		consultaDeVendasPO.selecionarDataFinal("6", "Mai", "2021");
	
		consultaDeVendasPO.selecionarFiliais("NORTE SHOPPING");
		
		consultaDeVendasPO.pesquisarVendas();
		consultaDeVendasPO.aguardarVisibilidadeDoElemento(driver, 20, consultaDeVendasPO.numeroBoletaListagem);

		consultaDeVendasPO.selecionarItensPorPagina("10");

		Assert.assertEquals("Cancelado", consultaDeVendasPO.statusListagem.getText());

		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Frete"), consultaDeVendasPO.somaDosValoresDoCard("Frete"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Desconto"), consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Dinheiro"), consultaDeVendasPO.somaDosValoresDoCard("Dinheiro"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Cartão"), consultaDeVendasPO.somaDosValoresDoCard("Cartão"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Total de Venda"), consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda"));
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Total Recebido"), consultaDeVendasPO.somaDosValoresDoCard("Valor Recebido"));
		Assert.assertEquals(consultaDeVendasPO.somaDosValoresDaListagem(), consultaDeVendasPO.obterValorResumoVenda("Total de Venda"));
	}

	/** Teste para conferir valores de vendas do netshopper com troco pelo periodo. */
	@Test
	public void T0013_deve_conferir_valores_resumo_vendas_troco_netshopper() {
		consultaDeVendasPO.selecionarTipoDeVenda(7);
		consultaDeVendasPO.buscarCodigoVenda("E01021");

		Double valorComDescontos = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda")) - Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Double troco = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor Recebido")) - valorComDescontos;
	
		Assert.assertEquals(consultaDeVendasPO.obterValorTroco(), consultaDeVendasPO.formatarValorDecimal(troco));

		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("24", "Jan", "2022");
		consultaDeVendasPO.selecionarDataFinal("24", "Jan", "2022");
	
		consultaDeVendasPO.selecionarFiliais("NORTE SHOPPING");
		consultaDeVendasPO.pesquisarVendas();

		Double valorDaVendaComDescontos = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda")) - Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Double valorTroco = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor Recebido")) - valorDaVendaComDescontos;
	
		Assert.assertEquals(consultaDeVendasPO.obterValorTroco(), consultaDeVendasPO.formatarValorDecimal(valorTroco));
	}

	/** Teste para conferir valores de vendas com troco pelo periodo. */
	@Ignore ("IN-7541")
	@Test
	public void T0014_deve_conferir_valores_resumo_vendas_troco_cashnet() {
		consultaDeVendasPO.buscarCodigoVenda("905610");

		Double valorComDescontos = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda")) - Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Double troco = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor Recebido")) - valorComDescontos;
	
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Troco"), consultaDeVendasPO.formatarValorDecimal(troco));

		consultaDeVendasPO.limparCampoInput(consultaDeVendasPO.inputBoleta);

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("07", "Jan", "2020");
		consultaDeVendasPO.selecionarDataFinal("07", "Jan", "2020");
	
		consultaDeVendasPO.selecionarFiliais("FASHION MALL");

		consultaDeVendasPO.pesquisarVendas();

		Double valorDaVendaComDescontos = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor da Venda")) - Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Desconto"));
		Double valorTroco = Double.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Valor Recebido")) - valorDaVendaComDescontos;
	
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Troco"), consultaDeVendasPO.formatarValorDecimal(valorTroco));
	}

	/** Teste para conferir valores de desconto das vendas. */
	@Test
	public void T0015_deve_conferir_valores_resumo_vendas_desconto() {
		consultaDeVendasPO.atualizarPagina(5);

		consultaDeVendasPO.botaoCalendario.click();

		consultaDeVendasPO.selecionarDataInicial("16", "Jan", "2020");
		consultaDeVendasPO.selecionarDataFinal("16", "Jan", "2020");
	
		consultaDeVendasPO.selecionarFiliais("FASHION MALL");

		consultaDeVendasPO.pesquisarVendas();
		consultaDeVendasPO.pesquisarVendas();
		
		Assert.assertEquals(consultaDeVendasPO.obterValorResumoVenda("Desconto"), String.valueOf(consultaDeVendasPO.somaDosValoresDoCard("Desconto")));
	}
    //#endregion   
}
