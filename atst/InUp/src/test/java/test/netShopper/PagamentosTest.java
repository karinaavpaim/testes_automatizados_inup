package test.netShopper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import page.DinheiroPO;
import page.netShopper.LinkDeCartaoPO;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe de testes para realizar pagamentos. */
public class PagamentosTest extends BaseTest {

	// #region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static DinheiroPO dinheiroPO;
	private static LinkDeCartaoPO linkDeCartaoPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanho = 0;
	private static String frete = "CORREIOS";
	private static String valorAPagar;
	private static double valorTroco;
	private static double valorPago;
	// #endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes() {
		netShopperPO = new NetShopperPO(driver);
		dinheiroPO = new DinheiroPO(driver);
		linkDeCartaoPO = new LinkDeCartaoPO(driver);
		
		netShopperPO.navegarParaNetshopper();
	}

	/** Metodo para ser realizado antes de cada teste, selecionando um cliente e inserindo um produto ao carrinho. */
	@Before
	public void realiza_a_primeira_acao_a_cada_teste() {
		netShopperPO.atualizarPagina(5);

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, nomeVendedor);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
		netShopperPO.selecionarFrete(frete);
		
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 3, netShopperPO.conteudoMensagemFlutuante);
	}

	// #region Regiao dos testes
	
	/** Testa pagamento em dinheiro. 
	 * @throws InterruptedException */
	@Test
	public void T0001_deve_realizar_pagamento_em_dinheiro() throws InterruptedException {
		valorAPagar = netShopperPO.obterValorResumoVenda("A pagar");

		dinheiroPO.realizarPagamentoEmDinheiro(valorAPagar);

		netShopperPO.botaoFinalizarPedido.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		Thread.sleep(2000);
		Assert.assertEquals("Venda realizada com sucesso!", netShopperPO.mensagemVendaRealizadaComSucesso.getText());
		netShopperPO.botaoFechar.click();
	}

	/** Testa pagamento com link de cartao. */
	//@Test
	public void T0002_deve_realizar_pagamento_com_link_de_cartao() {
		valorAPagar = netShopperPO.obterValorResumoVenda("A pagar");

		linkDeCartaoPO.realizarPagamentoLinkDeCartao(valorAPagar);

		netShopperPO.botaoFinalizarPedido.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		netShopperPO.aguardarVisibilidadeDoElemento(driver, 10, netShopperPO.botaoCopiarLinkDePagamento);

		Assert.assertEquals("Venda realizada com sucesso!", netShopperPO.mensagemVendaRealizadaComSucesso.getText());
		netShopperPO.botaoFechar.click();
	}

	/** Testa o pagamento em parte com dinheiro e outra parte com link de cartao. */
	//@Test
	public void T0003_deve_realizar_pagamento_com_link_de_cartao_e_dinheiro() {
		dinheiroPO.realizarPagamentoEmDinheiro("1000");

		valorAPagar = netShopperPO.obterValorResumoVenda("A pagar");

		linkDeCartaoPO.realizarPagamentoLinkDeCartao(valorAPagar);

		netShopperPO.botaoFinalizarPedido.click();		
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		netShopperPO.aguardarVisibilidadeDoElemento(driver, 10, netShopperPO.botaoCopiarLinkDePagamento);
		
		Assert.assertEquals("Venda realizada com sucesso!", netShopperPO.mensagemVendaRealizadaComSucesso.getText());
		netShopperPO.botaoFechar.click();
	}

	/** Testa a finalizacao da venda sem preenchimento do valor para pagamento. */
	@Test
	public void T0004_deve_testar_realizar_pagamento_sem_preencher_o_valor() {
		netShopperPO.botaoFinalizarPedido.click();

		Assert.assertEquals("Ops!\nO valor à pagar não foi totalmente preenchido.", netShopperPO.obterTextoMensagemFlutuante(driver, 3));
	}

	/** Testa a conferencia de valores com troco para dinheiro. */
	@Test
	public void T0005_deve_testar_preenchendo_um_valor_acima_da_venda_e_confirmar_troco_dinheiro() {
		double valorAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("A pagar"));

		dinheiroPO.realizarPagamentoEmDinheiro(String.valueOf(valorAPagar) + "00");

		valorTroco = Double.valueOf(netShopperPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(netShopperPO.obterValorResumoVenda("Dinheiro"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}

	/** Testa a conferencia de valores com troco para cartao. */
	@Test
	public void T0006_deve_testar_preenchendo_um_valor_acima_da_venda_e_confirmar_troco_cartao() {
		double valorAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("A pagar"));

		linkDeCartaoPO.realizarPagamentoLinkDeCartao(String.valueOf(valorAPagar) + "00");

		valorTroco = Double.valueOf(netShopperPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(netShopperPO.obterValorResumoVenda("Cartão"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}

	/** Testa a conferencia de valores com troco para pagamento com cartao e dinheiro. */
	@Test
	public void T0007_deve_testar_preenchendo_um_valor_para_cartao_e_para_dinheiro_com_troco() {
		double valorAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("A pagar"));

		linkDeCartaoPO.realizarPagamentoLinkDeCartao("100");

		dinheiroPO.realizarPagamentoEmDinheiro(String.valueOf(valorAPagar) + "00");

		valorTroco = Double.valueOf(netShopperPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(netShopperPO.obterValorResumoVenda("Cartão")) + Double.valueOf(netShopperPO.obterValorResumoVenda("Dinheiro"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}
	//#endregion
}
