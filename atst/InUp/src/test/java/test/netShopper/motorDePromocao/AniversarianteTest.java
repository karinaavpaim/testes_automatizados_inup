package test.netShopper.motorDePromocao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import page.PinPO;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe para testar o motor de promoção para aniversariantes. */
public class AniversarianteTest extends BaseTest{

    //#region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static PinPO pinPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
    private static String nomeProduto = "COLCHÃO";
	private static int indexCorOuTamanhoDoProduto = 1;
    private static String frete = "JUDITE";
	//#endregion
	
	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		netShopperPO = new NetShopperPO(driver);
		pinPO = new PinPO(driver);

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
	
    /** Testar aplicacao de promocao de 10% de desconto no valor total da venda.*/
	@Test
	public void T0001_deve_aplicar_promocao_de_10_porcento_de_desconto_para_aniversariante_do_mes() {
        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);

		pinPO.inserirPin("aniversariante");
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
		
		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", netShopperPO.promocaoAplicadaPorcentagem.getText());	
	}

    /** Testar aplicacao de promocao somente para cliente que faca parte de um grupo somente se for aniversariante do mes,
	 * aplicando 5,00 de desconto no total da venda. */
	@Test
	public void T0002_deve_aplicar_promocao_grupo_cliente_aniversariante() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "VANIA GONCALVES DOS SANTOS");
		pinPO.inserirPin("grupoaniversariante");
		
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarPromocaoAplicada());
		Assert.assertEquals("5.00", netShopperPO.obterValorResumoVenda("Promoção"));
		
		netShopperPO.botaoExpandirCliente.click();
		netShopperPO.botaoAdicionarCliente.click();

        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "Wolverine");
		netShopperPO.selecionarFrete(frete);

		Assert.assertFalse(netShopperPO.verificarPromocaoAplicada());
    }
	//#endregion
}
