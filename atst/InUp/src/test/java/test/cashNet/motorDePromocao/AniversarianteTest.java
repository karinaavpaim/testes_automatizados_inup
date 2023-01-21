package test.cashNet.motorDePromocao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import page.PinPO;
import page.cashNet.CashNetPO;
import test.BaseTest;

/** Classe para testar o motor de promoção para aniversariantes. */
public class AniversarianteTest extends BaseTest{

    //#region Regiao dos atributos
	private static CashNetPO cashNetPO;
	private static PinPO pinPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
    private static String nomeProduto = "COLCHÃO";
	private static int indexCorOuTamanhoDoProduto = 1;
	//#endregion
	
	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		cashNetPO = new CashNetPO(driver);
		pinPO = new PinPO(driver);

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
	
    /** Testar aplicacao de promocao de 10% de desconto no valor total da venda.*/
	@Test
	public void T0001_deve_aplicar_promocao_de_10_porcento_de_desconto_para_aniversariante_do_mes() {
        cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
       
		pinPO.inserirPin("aniversariante");
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
		
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("10", cashNetPO.promocaoAplicadaPorcentagem.getText());	
	}

    /** Testar aplicacao de promocao somente para cliente que faca parte de um grupo somente se for aniversariante do mes,
	 * aplicando 5,00 de desconto no total da venda. */
	@Test
	public void T0002_deve_aplicar_promocao_grupo_cliente_aniversariante() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "VANIA GONCALVES DOS SANTOS");
		
		pinPO.inserirPin("grupoaniversariante");
		
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
	
		Assert.assertTrue(cashNetPO.verificarPromocaoAplicada());
		Assert.assertEquals("5.00", cashNetPO.obterValorResumoVenda("Promoção"));
		
		cashNetPO.botaoExpandirCliente.click();
		cashNetPO.botaoAdicionarCliente.click();

        cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "Wolverine");
		cashNetPO.aguardarAplicacaoOuRetiradaDaPromocaoNaVenda(5);

		Assert.assertFalse(cashNetPO.verificarPromocaoAplicada());
    }
	//#endregion
}
