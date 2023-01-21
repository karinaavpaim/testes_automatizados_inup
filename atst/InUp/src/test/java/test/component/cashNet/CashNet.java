package test.component.cashNet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.DinheiroPO;
import page.cashNet.CartaoPO;
import page.cashNet.CashNetPO;
import test.BaseTest;

/** Classe para testar os elementos da tela CashNet. */
public class CashNet extends BaseTest{

    // #region Regiao dos atributos
    private static CashNetPO cashNetPO;
	private static DinheiroPO dinheiroPO;
	private static CartaoPO cartaoPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
    //#endregion

    /** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes() {
		cashNetPO = new CashNetPO(driver);
		dinheiroPO = new DinheiroPO(driver);
		cartaoPO = new CartaoPO(driver);

		cashNetPO.navegarParaCashNet();
	}

	//#region Regiao dos testes

    /** Testa o campo quantidade de produtos. */
	@Test
	public void T0001_deve_testar_quantidade_produto() {
		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("999999999999999999999");

		Assert.assertEquals("999999", cashNetPO.inputQuantidade.getAttribute("value"));
	
		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("-100");

		Assert.assertEquals("100", cashNetPO.inputQuantidade.getAttribute("value"));

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("1,2");

		Assert.assertEquals("21", cashNetPO.inputQuantidade.getAttribute("value"));

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("0000");

		Assert.assertEquals("0", cashNetPO.inputQuantidade.getAttribute("value"));
		Assert.assertTrue(cashNetPO.verificarCampoObrigatorio(cashNetPO.inputQuantidade));
	}

	/** Testa o preenchimento do pagamento em dinheiro com valor negativo. */
	@Test
	public void T0002_deve_testar_preenchendo_um_valor_negativo_no_campo_dinheiro() {
		dinheiroPO.botaoDinheiro.click();

		dinheiroPO.limparCampoInput(dinheiroPO.inputValorDinheiro);
		dinheiroPO.inputValorDinheiro.sendKeys("-" + "1000");

		Assert.assertEquals("10,00", dinheiroPO.inputValorDinheiro.getAttribute("value"));

		dinheiroPO.botaoCancelarMensagemFlutuante.click();
	}

	/** Testa o preenchimento do pagamento em cartao de credito e debito com um valor negativo. */
	@Test
	public void T0003_deve_testar_preenchendo_um_valor_negativo_no_campo_cartao_para_credito() {
		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPO.inputCartaoCredito.click();
		cartaoPO.inputValorPOS.sendKeys("-" + "1000");
		
		Assert.assertEquals("10,00", cartaoPO.inputValorPOS.getAttribute("value"));

		cartaoPO.inputCartaoDebito.click();
		cartaoPO.limparCampoInput(cartaoPO.inputValorPOS);
		cartaoPO.inputValorPOS.sendKeys("-" + "1000");
		
		Assert.assertEquals("10,00", cartaoPO.inputValorPOS.getAttribute("value"));

		dinheiroPO.botaoCancelarMensagemFlutuante.click();
		cartaoPO.botaoFechar.click();
	}

	/** Testa os campos de opcao para presente. */
	@Test
	public void T0004_deve_selecionar_a_opcao_para_presente() {
	    cashNetPO.selecionarOpcaoParaPresente("Karina", "Livia", "Feliz Aniversario");
        
		Assert.assertEquals("Karina", cashNetPO.inputPresenteDe.getAttribute("value"));
		Assert.assertEquals("Livia", cashNetPO.inputPresentePara.getAttribute("value"));
		Assert.assertEquals("Feliz Aniversario", cashNetPO.inputMensagemPresente.getAttribute("value"));

		cashNetPO.inputPresente.click();
	}

    /** Testa mensagem de campos em branco da opcao para presente. */
	@Test
	public void T0005_deve_conferir_campos_em_branco_opcao_para_presente() {
		cashNetPO.selecionarOpcaoParaPresente("", "", "");

		Assert.assertEquals("", cashNetPO.inputPresenteDe.getAttribute("value"));
		Assert.assertEquals("", cashNetPO.inputPresentePara.getAttribute("value"));
		Assert.assertEquals("", cashNetPO.inputMensagemPresente.getAttribute("value"));

		cashNetPO.inputPresente.click();
	}

	/** Testa a mensagem de produto não encontrado e o botão de limpar o campo produto. */
	@Test
	public void T0006_deve_conferir_campo_produto_nao_encontrado() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		
		cashNetPO.limparCampoInput(cashNetPO.inputProduto);
		cashNetPO.inputProduto.sendKeys("CORTADOR DE GRAMA");

		Assert.assertEquals("Desculpa, mas nenhum produto foi localizado.", cashNetPO.mensagemProdutoNaoEncontrado.getText());
		Assert.assertTrue(cashNetPO.verificarCampoObrigatorio(cashNetPO.inputProduto));
		Assert.assertTrue(cashNetPO.verificarSeElementoEstaNaTela(".fa-xmark"));

		cashNetPO.botaoLimparInputProduto.click();

		Assert.assertTrue(cashNetPO.inputProduto.getAttribute("value").isEmpty());
	}

	/** Testa a mensagem de loja não configurada para NFC-e. */
	@Test
	public void T0007_deve_conferir_mensagem_de_loja_nao_configurada_para_nfce() {
		cashNetPO.atualizarPagina(5);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "DEFEITO");

		cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.tituloModal);
		
		Assert.assertEquals(
			"Filial selecionada não possui configuração para emissão de NFC-e, por favor realize a configuração através do Retaguarda ou selecione outra filial.",
			cashNetPO.textoModalConfiguracaoNFCe());

		cashNetPO.fecharModaisAvisos();

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "NORTE SHOPPING");
		
		cashNetPO.aguardarElementoSerClicavel(driver, 10, cashNetPO.botaoCartao);
	}

	/** Testa a mensagem de loja não configurada para NFC-e. */
	@Test
	public void T0008_deve_conferir_mensagem_de_loja_nao_configurada_para_cartoes() {
		cashNetPO.atualizarPagina(5);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "ESCRITORIO");
		
		Assert.assertEquals(
			"Filial selecionada não possui cartões configurados\na forma de pagamento informada ficará bloqueada",
			cashNetPO.textoModalConfiguracaoCartao());
		
		cashNetPO.fecharModaisAvisos();

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "NORTE SHOPPING");
		
		cashNetPO.aguardarElementoSerClicavel(driver, 10, cashNetPO.botaoCartao);
	}

	/** Testa o campo produto desabilitado quando não houver cliente selecionado. */
	@Test
	public void T0009_deve_conferir_campo_produto_desabilitado_quando_cliente_nao_for_selecionado() {
		cashNetPO.atualizarPagina(5);

		Assert.assertFalse(cashNetPO.verificarSeInputProdutoEstaHabilitado());
	}
    //#endregion
}
