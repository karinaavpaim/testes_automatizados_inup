package test.cashNet.motorDePromocao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import page.EditarItemPO;
import page.PinPO;
import page.cashNet.CashNetPO;
import test.BaseTest;

/** Classe para testar o recurso de motor de promocao para promoções cumulativas. */
public class CumulativaTest extends BaseTest{

    //#region Regiao dos atributos
	private static CashNetPO cashNetPO;
	private static PinPO pinPO;
	private static EditarItemPO editarItemPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static int indexCorOuTamanhoDoProduto = 1;
	private static double valorDescontoPromocao;
	private static double valorTotalItens;
	private static double valorTotalAPagar;
	private static double porcentagemDesconto = 20;
	private static double valorAPagar;
	//#endregion
    
    /** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		cashNetPO = new CashNetPO(driver);
		pinPO = new PinPO(driver);
		editarItemPO = new EditarItemPO(driver);

		cashNetPO.navegarParaCashNet();
	}

	/** Metodo a ser realizado antes de cada teste. */
	@Before
	public void deve_atualizar_pagina(){
		cashNetPO.atualizarPagina(5);

        cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
	}

	//#region Regiao dos testes
	
    /** Testar aplicacao de duas promocoes: uma para compras acima de R$ 2.000,00, ser aplicado 20% de desconto.
	 * E a outra a cada 20 itens incluídos ao carrinho é descontado R$ 1,00. */
	@Test
	public void T0001_devem_acumular_duas_promocoes() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		
		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("21");

		cashNetPO.adicionarProdutoAoCarrinho("COLCHÃO", indexCorOuTamanhoDoProduto);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());

		valorTotalItens = Double.valueOf(cashNetPO.obterValorResumoVenda("Total Itens"));
		valorTotalAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("Total"));
	
		valorDescontoPromocao = cashNetPO.valorASerDescontadoOuAcrescido(porcentagemDesconto, valorTotalItens - 1) + 1;
		
		valorAPagar = valorTotalItens - valorDescontoPromocao;
		
		Assert.assertEquals(Double.toString(valorAPagar), Double.toString(valorTotalAPagar));	
	}

    /** Testar aplicacao de tres promocoes: uma para um desconto de 10% de um cliente que faca parte do grupo de clientes.
	 * Outra a cada 20 itens incluídos ao carrinho é descontado R$ 1,00.
	 * E aplicar desconto de 5,00 a cada 5 brincos. */
	@Test
	public void T0002_deve_aplicar_tres_promocoes() {
		pinPO.inserirPin("grupoclientes");

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "VANIA GONCALVES DOS SANTOS");

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("50");

		cashNetPO.adicionarProdutoAoCarrinho("BRINCO ROSE ARGOLA", indexCorOuTamanhoDoProduto);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());

		valorTotalItens = Double.valueOf(cashNetPO.obterValorResumoVenda("Total Itens"));
		valorTotalAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("Total"));
	
		valorDescontoPromocao = cashNetPO.valorASerDescontadoOuAcrescido(10, valorTotalItens) + 1 + 5;
		valorAPagar = valorTotalItens - valorDescontoPromocao;
		
		Assert.assertEquals(Double.toString(valorAPagar), Double.toString(valorTotalAPagar));	
	}

    /** Testar aplicacao de promocao que a cada 20 produtos aplique R$ 1,00 de desconto. Inserindo 40 produtos, deve ser inserido R$2,00
	 * de desconto. */
	@Ignore("IN-9101")
	@Test
	public void T0003_deve_aplicar_promocao_a_cada_vinte_produtos_um_real_de_desconto_para_quarenta_produtos_dois_reais_de_desconto() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("40");

		cashNetPO.adicionarProdutoAoCarrinho("26068", indexCorOuTamanhoDoProduto);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("2.00", cashNetPO.obterValorResumoVenda("Promoção"));
				
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}

    /** Testar aplicacao de desconto de 10% em um item que esteja em promocao que pode ser ativado varias vezes. */
	@Test
	public void T0004_deve_aplicar_desconto_no_item_em_promocao_varias_vezes() {
		pinPO.inserirPin("28");

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("3");

		cashNetPO.adicionarProdutoAoCarrinho("29018", indexCorOuTamanhoDoProduto);
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("30", cashNetPO.obterPorcentagemPromocaoAplicada());

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}

    /** Testar aplicacao de desconto de 5% a partir de 2 itens qualquer, 10% a partir de 3 itens qualquer e  15% a partir de
	 * 4 itens qualquer. */
	@Test
	public void T0005_deve_aplicar_descontos_progressivos_conforme_aumentar_quantidade_de_itens() {
		pinPO.inserirPin("741");
		
		cashNetPO.adicionarProdutoAoCarrinho("BRINCO BAILARINA 3M", indexCorOuTamanhoDoProduto);

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());

		editarItemPO.editarQuantidade(2);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", cashNetPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(3);
		cashNetPO.aguardarTrocaDeAplicacaoDaPromocao();
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", cashNetPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(5);
		cashNetPO.aguardarTrocaDeAplicacaoDaPromocao();
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("15", cashNetPO.obterPorcentagemPromocaoAplicada());
	}

	/** Testar aplicacao de desconto de 5% a cada 2 itens qualquer, sendo aplicada varias vezes. */
	@Test
	public void T0006_deve_aplicar_descontos_progressivos_a_cada_dois_itens() {
		pinPO.inserirPin("14700");

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("2");
		cashNetPO.adicionarProdutoAoCarrinho("BRINCO BAILARINA 3M", indexCorOuTamanhoDoProduto);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", cashNetPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(4);
		cashNetPO.aguardarTrocaDeAplicacaoDaPromocao();

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", cashNetPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(6);
		cashNetPO.aguardarTrocaDeAplicacaoDaPromocao();
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("15", cashNetPO.obterPorcentagemPromocaoAplicada());
	}
	//#endregion
}
