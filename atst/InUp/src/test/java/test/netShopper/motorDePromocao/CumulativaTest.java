package test.netShopper.motorDePromocao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import page.EditarItemPO;
import page.PinPO;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe para testar o recurso de motor de promocao para promoções cumulativas. */
public class CumulativaTest extends BaseTest{

    //#region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static PinPO pinPO;
	private static EditarItemPO editarItemPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
   	private static int indexCorOuTamanhoDoProduto = 1;
    private static String frete = "JUDITE";
	private static double valorDescontoPromocao;
	private static double valorTotal;
	private static double valorTotalAPagar;
	private static double porcentagemDesconto = 20;
	private static double valorAPagar;
	private static double valorLiquido;
	//#endregion
	
	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		netShopperPO = new NetShopperPO(driver);
		pinPO = new PinPO(driver);
		editarItemPO = new EditarItemPO(driver);

		netShopperPO.navegarParaNetshopper();
	}

	/** Metodo a ser realizado antes de cada teste. */
	@Before
	public void deve_atualizar_pagina(){
		netShopperPO.atualizarPagina(5);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor , nomeVendedor);
	}

	//#region Regiao dos testes
	
	/** Testar aplicacao de duas promocoes: uma para compras acima de R$ 2.000,00, ser aplicado 20% de desconto.
	 * E a outra a cada 20 itens incluídos ao carrinho é descontado R$ 1,00. */
	@Test
	public void T0001_devem_acumular_duas_promocoes() {
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		
		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("21");

		netShopperPO.adicionarProdutoAoCarrinho("COLCHÃO", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
		
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

		valorTotal = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Itens"));
		valorTotalAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("Total"));
	
		valorDescontoPromocao = netShopperPO.valorASerDescontadoOuAcrescido(porcentagemDesconto, valorTotal - 1) + 1;
		valorLiquido = valorTotal - valorDescontoPromocao;
		valorAPagar = valorLiquido + Double.valueOf(netShopperPO.obterValorResumoFrete());
				
		Assert.assertEquals(Double.toString(valorAPagar), Double.toString(valorTotalAPagar));	
	}

    /** Testar aplicacao de tres promocoes: uma para um desconto de 10% de um cliente que faca parte do grupo de clientes.
	 * Outra a cada 20 itens incluídos ao carrinho é descontado R$ 1,00.
	 * E aplicar desconto de 5,00 a cada 5 brincos. */
	@Test
	public void T0002_deve_aplicar_tres_promocoes() {
		pinPO.inserirPin("grupoclientes");

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "VANIA GONCALVES DOS SANTOS");

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("50");

		netShopperPO.adicionarProdutoAoCarrinho("BRINCO ROSE ARGOLA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

		valorTotal = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Itens"));
		valorTotalAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("Total"));
	
		valorDescontoPromocao = netShopperPO.valorASerDescontadoOuAcrescido(10, valorTotal) + 1 + 5;
		valorLiquido = valorTotal - valorDescontoPromocao;
		valorAPagar = valorLiquido + Double.valueOf(netShopperPO.obterValorResumoFrete());
				
		Assert.assertEquals(Double.toString(valorAPagar), Double.toString(valorTotalAPagar));	
	}

    /** Testar aplicacao de promocao que a cada 20 produtos aplique R$ 1,00 de desconto. Inserindo 40 produtos, deve ser inserido R$2,00
	 * de desconto.
	*/
	@Ignore("IN-9101")
	@Test
	public void T0003_deve_aplicar_promocao_a_cada_vinte_produtos_um_real_de_desconto_para_quarenta_produtos_dois_reais_de_desconto() {
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("40");

		netShopperPO.adicionarProdutoAoCarrinho("26068", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("2.00", netShopperPO.obterValorResumoVenda("Promoção"));
				
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

    /** Testar aplicacao de desconto de 10% em um item que esteja em promocao que pode ser ativado varias vezes. */
	@Test
	public void T0004_deve_aplicar_desconto_no_item_em_promocao_varias_vezes() {
		pinPO.inserirPin("28");

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("3");

		netShopperPO.adicionarProdutoAoCarrinho("29018", indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);
	
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("30", netShopperPO.obterPorcentagemPromocaoAplicada());

		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

    /** Testar aplicacao de desconto de 5% a partir de 2 itens qualquer, 10% a partir de 3 itens qualquer e  15% a partir de
	 * 4 itens qualquer. */
	@Test
	public void T0005_deve_aplicar_descontos_progressivos_conforme_aumentar_quantidade_de_itens() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		pinPO.inserirPin("741");

		netShopperPO.adicionarProdutoAoCarrinho("BRINCO BAILARINA 3M", indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());

		editarItemPO.editarQuantidade(2);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", netShopperPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(3);
		netShopperPO.aguardarTrocaDeAplicacaoDaPromocao();

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", netShopperPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(5);
		netShopperPO.aguardarTrocaDeAplicacaoDaPromocao();

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("15", netShopperPO.obterPorcentagemPromocaoAplicada());
	}

    /** Testar aplicacao de desconto de 5% a cada 2 itens qualquer, sendo aplicada varias vezes. */
	@Test
	public void T0006_deve_aplicar_descontos_progressivos_a_cada_dois_itens() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		
		pinPO.inserirPin("14700");

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("2");
		
		netShopperPO.adicionarProdutoAoCarrinho("BRINCO BAILARINA 3M", indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", netShopperPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(4);
		netShopperPO.aguardarTrocaDeAplicacaoDaPromocao();

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", netShopperPO.obterPorcentagemPromocaoAplicada());

		editarItemPO.editarQuantidade(6);
		netShopperPO.aguardarTrocaDeAplicacaoDaPromocao();
		
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("15", netShopperPO.obterPorcentagemPromocaoAplicada());
	}
	//#endregion
}
