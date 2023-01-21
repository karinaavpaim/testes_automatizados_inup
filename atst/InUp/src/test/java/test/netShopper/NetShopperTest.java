package test.netShopper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe para testar elementos na tela do NetShopper. */
public class NetShopperTest extends BaseTest {

	// #region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanho = 0;
	private static String frete = "JUDITE";
	// #endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes() {
		netShopperPO = new NetShopperPO(driver);

		netShopperPO.navegarParaNetshopper();
	}

	/** Metodo a ser realizado antes de cada teste. */
	@Before
	public void deve_atualizar_pagina() {
		netShopperPO.atualizarPagina(3);
	}

	//#region Regiao dos testes
	
	/** Testar adicionar uma nova venda e excluir as mesmas. */
	@Test
	public void T0001_deve_adicionar_nova_aba_de_vendas_e_excluir() {
		netShopperPO.adicionarNovaAbaDeVenda();
		netShopperPO.adicionarNovaAbaDeVenda();

		netShopperPO.excluirAbaDeVenda(2);
		netShopperPO.excluirAbaDeVenda(1);
	}

	/** Testa a alteracao do frete. */
	@Test
	public void T0002_deve_alterar_o_frete() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, nomeVendedor);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
		netShopperPO.selecionarFrete(frete);

		Assert.assertEquals("JUDITE EXPRESS", netShopperPO.botaoEditarFrete.getText());

		netShopperPO.botaoEditarFrete.click();

		netShopperPO.selecionarFrete("CORREIOS");

		Assert.assertEquals("CORREIOS", netShopperPO.botaoEditarFrete.getText());
	}

	/** Procurar e selecionar determinado cliente. */
	@Test
	public void T0003_deve_procurar_cliente_e_selecionar() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Lucas Padilha");
		Assert.assertEquals("LUCAS PADILHA", netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Karina Paim");
		Assert.assertEquals("KARINA PAIM", netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Vania Goncalves dos Santos");
		Assert.assertEquals("VANIA GONCALVES DOS SANTOS", netShopperPO.inputClienteSelecionado.getAttribute("value"));
	}

	/** Testa o cancelamento do pedido. 
	 * @throws InterruptedException */
	@Test
	public void T0004_deve_cancelar_o_pedido() throws InterruptedException {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, nomeVendedor);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
		netShopperPO.selecionarFrete(frete);

		netShopperPO.botaoCancelarPedido.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();
		
		Thread.sleep(3000);

		Assert.assertEquals("0.00", netShopperPO.obterValorResumoVenda("A pagar"));
	}

	/** Testa a inclusao e exclusao de um produto. */
	@Test
	public void T0005_deve_testar_inclusao_e_exclusao_do_produto() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, nomeVendedor);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
		
		Assert.assertEquals("Produto adicionado\nCARTEIRA KARINA", netShopperPO.obterTextoMensagemFlutuante(driver, 5));

		netShopperPO.selecionarFrete(frete);

		Assert.assertEquals("CARTEIRA KARINA", netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Verifica o valor do produto inserido no carrinho, com o valor do resumo. */
	@Test
	public void T0006_verifica_valor_do_produto_no_carrinho_com_o_valor_no_resumo() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, nomeVendedor);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
		netShopperPO.selecionarFrete(frete);

		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);

		Assert.assertEquals(netShopperPO.obterValorProdutoAdicionado(nomeProduto), netShopperPO.obterValorResumoVenda("Total Itens"));
	}
	// #endregion
}
