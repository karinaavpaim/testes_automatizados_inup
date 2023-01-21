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

/** Classe para testar o recurso de motor de promocao. */
public class PromocaoTest extends BaseTest{

    //#region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static PinPO pinPO;
	private static EditarItemPO editarItemPO;
	private static String nomeCliente = "Karina Paim";
    private static String nomeProduto = "COLCHÃO";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static int indexCorOuTamanhoDoProduto = 1;
    private static String frete = "JUDITE";
	private static double valorDescontoPromocao;
	private static double valorTotal;
	private static double valorTotalAPagar;
	private static double valorTotalFinal;
	private static double troco;
	private static double valorAPagar;
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
	
	/** Testar aplicacao de promocao somente para cliente que faca parte de um grupo aplicando 10% de desconto a cada 1 produto.
	 * Tal promocao e aplicada no valor total final. 
	 * Os clientes que fazem parte do grupo sao: VANIA GONCALVES DOS SANTOS e IVETE SANGALO.*/
	@Test
	public void T0001_deve_aplicar_promocao_grupo_cliente_e_retirar_promocao_quando_cliente_nao_e_do_grupo() {
		pinPO.inserirPin("grupoclientes");
		
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "VANIA GONCALVES DOS SANTOS");
        netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
	
        Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
        Assert.assertEquals("10", netShopperPO.promocaoAplicadaPorcentagem.getText());
        
        netShopperPO.botaoExpandirCliente.click();
        netShopperPO.botaoAdicionarCliente.click();

        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
        netShopperPO.selecionarFrete(frete);
       
        Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());

        netShopperPO.botaoAdicionarCliente.click();
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Ivete Sangalo");
        netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
        Assert.assertEquals("10", netShopperPO.promocaoAplicadaPorcentagem.getText());
    }

	/** Testar aplicacao de promocao somente para o produto SANDALIA RASTEIRA IPANEMA 10% de desconto a cada 1 produto. */
	@Test
	public void T0002_deve_aplicar_promocao_a_somente_um_produto() {
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
        netShopperPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
       
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
	    Assert.assertEquals("10", netShopperPO.obterPorcentagemPromocaoAplicada());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);

		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());
		
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Testar aplicacao de promocao somente para o produto SANDALIA RASTEIRA IPANEMA 10% de desconto a cada 1 produto
	 * junto com um cliente do grupo de promocao tambem com 10% de desconto.
	 * Como as promocoes nao sao cumulativas, deve ser aplicada somente os 10% de somente uma das promocoes. */
	@Test
	public void T0003_deve_aplicar_somente_uma_promocao_quando_nao_cumulativas() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "VANIA GONCALVES DOS SANTOS");
		pinPO.inserirPin("grupoclientes");
		
		netShopperPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
	
		netShopperPO.aguardarTrocaDeAplicacaoDaPromocao();

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", netShopperPO.obterPorcentagemPromocaoAplicada());
		
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}		

	/** Testar promocao que aplique um desconto acima do valor gerando um troco para o cliente. */
	@Test
	public void T0004_deve_aplicar_promocao_com_desconto_acima_do_valor() {
		pinPO.inserirPin("0123456");

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		
		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("10");

		netShopperPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);

        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
		
        Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

		valorTotal = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Itens")) + Double.valueOf(netShopperPO.obterValorResumoFrete());
		valorDescontoPromocao = Double.valueOf(netShopperPO.obterValorResumoVenda("Promoção"));
		valorTotalFinal = Double.valueOf(netShopperPO.obterValorResumoVenda("Total"));

		troco = valorTotal - valorDescontoPromocao;

      	Assert.assertEquals(Double.toString(troco), Double.toString(valorTotalFinal));
	}

	/** Testar promocao que aplique um desconto abaixo do valor acarretando um troco para o cliente. */
	@Test
	public void T0005_deve_aplicar_promocao_com_desconto_abaixo_do_valor() {
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		
		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("20");

		netShopperPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
	
		valorTotal = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Itens")) + Double.valueOf(netShopperPO.obterValorResumoFrete());
		valorDescontoPromocao = Double.valueOf(netShopperPO.obterValorResumoVenda("Promoção"));
		valorTotalAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("Total"));

		valorAPagar = valorTotal - valorDescontoPromocao;

		Assert.assertEquals(Double.toString(valorAPagar), Double.toString(valorTotalAPagar));	
	}

	/** Testar aplicacao de promocao de desconto de R$ 10,00 para um determinado item com o uso de PIN. */
	@Test
	public void T0006_deve_aplicar_promocao_de_item_com_pin() {
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
	
		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());
		
		pinPO.inserirPin("123");

		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("10.00", netShopperPO.obterValorResumoVenda("Promoção"));
	}

	/** Testar aplicacao de promocao de 100% de desconto.*/
	@Test
	public void T0007_deve_aplicar_promocao_de_100_porcento_de_desconto_no_valor_total_da_venda() {
		pinPO.inserirPin("123456");

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		netShopperPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

		Assert.assertEquals("100", netShopperPO.obterPorcentagemPromocaoAplicada());
		Assert.assertEquals(netShopperPO.obterValorResumoFrete(), netShopperPO.obterValorResumoVenda("A pagar"));
				
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Testar aplicacao de promocao de R$ 1,00 de desconto no valor total da venda quando
	 * adicionados uma quantidade a partir de dois produtos através do uso do pin.*/
	@Ignore ("IN-9141")
	@Test
	public void T0008_deve_aplicar_promocao_acima_de_dois_itens_com_pin() {
		pinPO.inserirPin("0001");

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("2");

		netShopperPO.adicionarProdutoAoCarrinho("CARTEIRA KARINA", indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
		    
    	Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

    	editarItemPO.editarQuantidade(3);
    
    	Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

		editarItemPO.editarQuantidade(4);
    
    	netShopperPO.aguardarTrocaDeAplicacaoDaPromocao();

    	Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
    	Assert.assertEquals("1.00", netShopperPO.obterValorResumoVenda("Promoção"));
	}

	/** Testar aplicacao de promocao que na compra de dois cintos ganha uma bolsa. */
	@Test
	public void T0009_deve_aplicar_promocao_com_dois_itens_ganha_uma_bolsa() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("2");

		netShopperPO.adicionarProdutoAoCarrinho("38415", indexCorOuTamanhoDoProduto);

		netShopperPO.selecionarFrete(frete);
	
		netShopperPO.adicionarProdutoAoCarrinho("24076", indexCorOuTamanhoDoProduto);

		String valorProdutoBrinde = netShopperPO.obterValorProdutoAdicionado("BOLSA CARTEIRA CONS");
    	
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals(netShopperPO.obterValorResumoVenda("Promoção"), valorProdutoBrinde);	
		
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Testar aplicacao de promocao que na compra de tres produtos determinados ganha desconto de 50% no item de menor valor. */
	@Test
	public void T0010_deve_aplicar_promocao_com_tres_itens_ganha_desconto_no_item_de_menor_valor() {
		pinPO.inserirPin("007");

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		netShopperPO.adicionarProdutoAoCarrinho("24540", indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		netShopperPO.adicionarProdutoAoCarrinho("25624", indexCorOuTamanhoDoProduto);
		netShopperPO.adicionarProdutoAoCarrinho("10545", indexCorOuTamanhoDoProduto);

		String valorProdutoDesconto = netShopperPO.obterValorProdutoAdicionado("CAPA PARA CHAVES CAMISETA");
		String valorFinalDesconto = netShopperPO.formatarValorDecimal(netShopperPO.valorASerDescontadoOuAcrescido(50, Double.valueOf(valorProdutoDesconto)));

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals(netShopperPO.obterValorResumoVenda("Promoção"), valorFinalDesconto);
		
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Testar aplicacao de desconto de 5% para o item 14187 somente para a Loja Centro. */
	@Test
	public void T0011_deve_aplicar_desconto_em_item_para_uma_loja() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, "FASHION MALL");
		netShopperPO.botaoOK.click();

		netShopperPO.adicionarProdutoAoCarrinho("14187", indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", netShopperPO.obterPorcentagemPromocaoAplicada());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		netShopperPO.botaoExpandirCliente.click();

		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, "NORTE SHOPPING");
		netShopperPO.adicionarProdutoAoCarrinho("14187", indexCorOuTamanhoDoProduto);

		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());
	}

	/** Testar aplicacao de promocao somente para grupo de itens "Tiara" aplicando 5% de desconto no total da venda. */
	@Test
	public void T0012_deve_aplicar_promocao_grupo_item_tiara() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		pinPO.inserirPin("grupotiaras");

		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());
		
		netShopperPO.botaoExpandirCliente.click();

		netShopperPO.adicionarProdutoAoCarrinho("tiara", indexCorOuTamanhoDoProduto);
      
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("5", netShopperPO.obterPorcentagemPromocaoAplicada());

		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Verificar que o campo "Promoção" foi retirado do resumo da venda, ao excluir o produto em promoção do carrinho 
	*/
	@Test
	public void T0013_deve_retirar_campo_promocao_do_resumo_ao_excluir_item() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();
		
		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());
	}
	//#endregion
}
