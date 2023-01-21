package test.cashNet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import page.cashNet.CashNetPO;
import test.BaseTest;

/** Classe para testar elementos na tela do CashNet. */
public class CashNetTest extends BaseTest{

    //#region Regiao dos atributos
	private static CashNetPO cashNetPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanhoDoProduto = 0;
	//#endregion
	
	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		cashNetPO = new CashNetPO(driver);

		cashNetPO.navegarParaCashNet();
	}

	/** Metodo a ser realizado antes de cada teste. */
	@Before
	public void deve_atualizar_pagina(){
		cashNetPO.atualizarPagina(3);
	}

	//#region Regiao dos testes
	
	/** Testar adicionar uma nova venda e excluir as mesmas. */
	@Test
	public void T0001_deve_adicionar_nova_aba_de_vendas_e_excluir() {
		cashNetPO.adicionarNovaAbaDeVenda();
		cashNetPO.adicionarNovaAbaDeVenda();

        cashNetPO.excluirAbaDeVenda(2);
		cashNetPO.excluirAbaDeVenda(1);
	}

    /**Procurar e selecionar determinado cliente. */
	@Test
	public void T0002_deve_procurar_cliente_e_selecionar() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "Lucas Padilha");
		Assert.assertEquals("LUCAS PADILHA", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "Karina Paim");
		Assert.assertEquals("KARINA PAIM", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "Vania Goncalves dos Santos");
		Assert.assertEquals("VANIA GONCALVES DOS SANTOS", cashNetPO.inputClienteSelecionado.getAttribute("value"));
	}

	/** Testa o cancelamento a venda. 
	 * @throws InterruptedException */
	@Test
	public void T0003_deve_cancelar_a_venda() throws InterruptedException {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);

		cashNetPO.fecharMensagensFlutuantes();
		cashNetPO.botaoCancelarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		Thread.sleep(3000);

		Assert.assertEquals("0.00", cashNetPO.obterValorResumoVenda("A pagar"));
	}

    /** Testa a inclusao e exclusao de um produto. */
	@Test
	public void T0004_deve_testar_inclusao_e_exclusao_do_produto() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		
		Assert.assertEquals("Produto adicionado\nCARTEIRA KARINA", cashNetPO.obterTextoMensagemFlutuante(driver, 3));
		Assert.assertEquals("CARTEIRA KARINA", cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Verifica o valor do produto inserido no carrinho, com o valor do resumo. */
	@Test
	public void T0005_verifica_valor_do_produto_no_carrinho_com_o_valor_no_resumo() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertEquals(cashNetPO.obterValorProdutoAdicionado(nomeProduto), cashNetPO.obterValorResumoVenda("Total Itens"));
	}
	//#endregion
}
