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

/** Classe para testar o recurso de motor de promocao. */
public class PromocaoTest extends BaseTest{

    //#region Regiao dos atributos
	private static CashNetPO cashNetPO;
	private static PinPO pinPO;
	private static EditarItemPO editarItemPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "COLCHÃO";
	private static int indexCorOuTamanhoDoProduto = 1;
	private static double valorDescontoPromocao;
	private static double valorTotalItens;
	private static double valorTotalAPagar;
	private static double valorTotalFinal;
	private static double troco;
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
		
	/** Testar aplicacao de promocao somente para cliente que faca parte de um grupo aplicando 10% de desconto a cada 1 produto.
	 * Tal promocao e aplicada no valor total final. 
	 * Os clientes que fazem parte do grupo sao: VANIA GONCALVES DOS SANTOS e IVETE SANGALO.*/
	@Test
	public void T0001_deve_aplicar_promocao_grupo_cliente_e_retirar_promocao_quando_cliente_nao_e_do_grupo() {
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		pinPO.inserirPin("grupoclientes");
		
		cashNetPO.botaoExpandirCliente.click();

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "VANIA GONCALVES DOS SANTOS");
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
        Assert.assertEquals("10", cashNetPO.promocaoAplicadaPorcentagem.getText());

        cashNetPO.botaoAdicionarCliente.click();
        cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.aguardarAplicacaoOuRetiradaDaPromocaoNaVenda(5);

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());

        cashNetPO.botaoAdicionarCliente.click();
        cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "Ivete Sangalo");

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
        Assert.assertEquals("10", cashNetPO.promocaoAplicadaPorcentagem.getText());
    }

	/** Testar aplicacao de promocao somente para o produto SANDALIA RASTEIRA IPANEMA 10% de desconto a cada 1 produto. */
	@Test
	public void T0002_deve_aplicar_promocao_a_somente_um_produto() {
		cashNetPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanhoDoProduto);
       
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", cashNetPO.obterPorcentagemPromocaoAplicada());

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();
		
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());
	}

	/** Testar aplicacao de promocao somente para o produto SANDALIA RASTEIRA IPANEMA 10% de desconto a cada 1 produto
	 * junto com um cliente do grupo de promocao tambem com 10% de desconto.
	 * Como as promocoes nao sao cumulativas, deve ser aplicada somente os 10% de somente uma das promocoes. */
	@Test
	public void T0003_deve_aplicar_somente_uma_promocao_quando_nao_cumulativas() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "VANIA GONCALVES DOS SANTOS");
		pinPO.inserirPin("grupoclientes");

		cashNetPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanhoDoProduto);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", cashNetPO.obterPorcentagemPromocaoAplicada());
	}		

	/** Testar promocao que aplique um desconto acima do valor gerando um troco para o cliente. */
	@Test
	public void T0004_deve_aplicar_promocao_com_desconto_acima_do_valor() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		
		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("10");

		cashNetPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());

		pinPO.inserirPin("0123456");
        cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
		
        Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());

		valorTotalItens = Double.valueOf(cashNetPO.obterValorResumoVenda("Total Itens"));
		valorDescontoPromocao = Double.valueOf(cashNetPO.obterValorResumoVenda("Promoção"));
		valorTotalFinal = Double.valueOf(cashNetPO.obterValorResumoVenda("Total"));

		troco = valorTotalItens - valorDescontoPromocao;

      	Assert.assertEquals(Double.toString(troco), Double.toString(valorTotalFinal));
	}

	/** Testar promocao que aplique um desconto abaixo do valor não gerando troco para o cliente. */
	@Test
	public void T0005_deve_aplicar_promocao_com_desconto_abaixo_do_valor() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		
		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("20");

		cashNetPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
	
		valorTotalItens = Double.valueOf(cashNetPO.obterValorResumoVenda("Total Itens"));
		valorDescontoPromocao = Double.valueOf(cashNetPO.obterValorResumoVenda("Promoção"));
		valorTotalAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("Total"));

		valorAPagar = valorTotalItens - valorDescontoPromocao;

		Assert.assertEquals(Double.toString(valorAPagar), Double.toString(valorTotalAPagar));	
	}

	/** Testar aplicacao de promocao de desconto de R$ 10,00 para um determinado item com o uso de PIN. */
	@Test
	public void T0006_deve_aplicar_promocao_de_item_com_pin() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
	
		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());
		
		pinPO.inserirPin("123");

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("10.00", cashNetPO.obterValorResumoVenda("Promoção"));
	}

	/** Testar aplicacao de promocao de 100% de desconto.*/
	@Test
	public void T007_deve_aplicar_promocao_de_100_porcento_de_desconto_no_valor_total_da_venda() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());

		pinPO.inserirPin("123456");

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());

		Assert.assertEquals("100", cashNetPO.obterPorcentagemPromocaoAplicada());
		Assert.assertEquals("0.00", cashNetPO.obterValorResumoVenda("A pagar"));
				
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Testar aplicacao de promocao de R$ 1,00 de desconto no valor total da venda quando
	 * adicionados uma quantidade a partir de dois produtos através do uso do pin.*/
	@Ignore ("IN-9141")
	@Test
	public void T0008_deve_aplicar_promocao_acima_de_dois_itens_com_pin() {
		pinPO.inserirPin("0001");

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("2");

		cashNetPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
	   
    	Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("1.00", cashNetPO.obterValorResumoVenda("Promoção"));

		editarItemPO.editarQuantidade(4);
    	    
    	Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("1.00", cashNetPO.obterValorResumoVenda("Promoção"));

		editarItemPO.editarQuantidade(6);

    	cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

    	Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
    	Assert.assertEquals("1.00", cashNetPO.obterValorResumoVenda("Promoção"));
	}

	/** Testar aplicacao de promocao que na compra de dois produtos ganha um determinado item de menor valor. */
	@Test
	public void T0009_deve_aplicar_promocao_com_dois_itens_ganha_uma_bolsa() {
		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("2");

		cashNetPO.adicionarProdutoAoCarrinho("38415", indexCorOuTamanhoDoProduto);
		cashNetPO.adicionarProdutoAoCarrinho("24076", indexCorOuTamanhoDoProduto);

		String valorProdutoBrinde = cashNetPO.obterValorProdutoAdicionado("BOLSA CARTEIRA CONS");
    	
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals(cashNetPO.obterValorResumoVenda("Promoção"), valorProdutoBrinde);
		
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Testar aplicacao de promocao que na compra de tres produtos determinados ganha desconto de 50% no item de menor valor. */
	@Test
	public void T0010_deve_aplicar_promocao_com_tres_itens_ganha_desconto_no_item_de_menor_valor() {
		pinPO.inserirPin("007");

		cashNetPO.adicionarProdutoAoCarrinho("24540", indexCorOuTamanhoDoProduto);
		cashNetPO.adicionarProdutoAoCarrinho("25624", indexCorOuTamanhoDoProduto);
		cashNetPO.adicionarProdutoAoCarrinho("10545", indexCorOuTamanhoDoProduto);

		String valorProdutoDesconto = cashNetPO.obterValorProdutoAdicionado("CAPA PARA CHAVES CAMISETA");
		String valorFinalDesconto = cashNetPO.formatarValorDecimal(cashNetPO.valorASerDescontadoOuAcrescido(50, Double.valueOf(valorProdutoDesconto)));

		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals(cashNetPO.obterValorResumoVenda("Promoção"), valorFinalDesconto);
		
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}
	
	/** Testar aplicacao de promocao somente para grupo de itens "Tiara" aplicando 5% de desconto no total da venda. */
	@Test
	public void T0011_deve_aplicar_promocao_grupo_item_tiara() {
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		pinPO.inserirPin("grupotiaras");

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());
		
		cashNetPO.botaoExpandirCliente.click();

		cashNetPO.adicionarProdutoAoCarrinho("tiara", indexCorOuTamanhoDoProduto);
      
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", cashNetPO.obterPorcentagemPromocaoAplicada());

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Verificar que o campo "Promoção" foi retirado do resumo da venda, ao excluir o produto em promoção do carrinho 
	 * @throws InterruptedException */
	@Test
	public void T0012_deve_retirar_campo_promocao_do_resumo_ao_excluir_item() throws InterruptedException {
		cashNetPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanhoDoProduto);
       
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());

		Thread.sleep(2000);
		
		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();
		
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());
	}
	//#endregion
}
