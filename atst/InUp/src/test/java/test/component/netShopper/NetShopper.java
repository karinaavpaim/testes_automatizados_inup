package test.component.netShopper;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.DinheiroPO;
import page.netShopper.LinkDeCartaoPO;
import page.netShopper.NetShopperPO;
import test.BaseTest;

public class NetShopper extends BaseTest{

    // #region Regiao dos atributos
    private static NetShopperPO netShopperPO;
	private static DinheiroPO dinheiroPO;
	private static LinkDeCartaoPO linkDeCartaoPO;
	private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
    //#endregion

    /** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes() {
		netShopperPO = new NetShopperPO(driver);
		dinheiroPO = new DinheiroPO(driver);
		linkDeCartaoPO = new LinkDeCartaoPO(driver);

		netShopperPO.navegarParaNetshopper();
	}

	//#region Regiao dos testes

    /** Testa o campo quantidade de produtos. */
	@Test
	public void T0001_deve_testar_quantidade_produto() {
		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("999999999999999999999");

		Assert.assertEquals("999999", netShopperPO.inputQuantidade.getAttribute("value"));

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("-100");

		Assert.assertEquals("100", netShopperPO.inputQuantidade.getAttribute("value"));

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("1,2");

		Assert.assertEquals("21", netShopperPO.inputQuantidade.getAttribute("value"));

		netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
		netShopperPO.inputQuantidade.sendKeys("0000");

		Assert.assertEquals("0", netShopperPO.inputQuantidade.getAttribute("value"));
		Assert.assertTrue(netShopperPO.verificarCampoObrigatorio(netShopperPO.inputQuantidade));
	}
    
	/** Testa o preenchimento do pagamento em dinheiro e link de cartao com valor negativo. */
	@Test
	public void T0002_deve_testar_preenchendo_um_valor_negativo_no_campo_dinheiro() {
		dinheiroPO.botaoDinheiro.click();

		dinheiroPO.limparCampoInput(dinheiroPO.inputValorDinheiro);
		dinheiroPO.inputValorDinheiro.sendKeys("-" + "1000");

		Assert.assertEquals("10,00", dinheiroPO.inputValorDinheiro.getAttribute("value"));

		dinheiroPO.botaoCancelarMensagemFlutuante.click();
	}

	/** Testa o preenchimento do pagamento com link de cartao com valor negativo. */
	@Test
	public void T0003_deve_testar_preenchendo_um_valor_negativo_no_campo_dinheiro_e_link_de_cartao() {
		linkDeCartaoPO.botaoLinkDeCartao.click();
		linkDeCartaoPO.inputValorCartao.sendKeys("-" + "1000");

		Assert.assertEquals("10,00", linkDeCartaoPO.inputValorCartao.getAttribute("value"));

		linkDeCartaoPO.botaoCancelarMensagemFlutuante.click();
	}

	/** Testa os campos de opcao para presente. */
	@Test
	public void T0004_deve_selecionar_a_opcao_para_presente() {
		netShopperPO.selecionarOpcaoParaPresente("Karina", "Livia", "Feliz Aniversario");

		Assert.assertEquals("Karina", netShopperPO.inputPresenteDe.getAttribute("value"));
		Assert.assertEquals("Livia", netShopperPO.inputPresentePara.getAttribute("value"));
		Assert.assertEquals("Feliz Aniversario", netShopperPO.inputMensagemPresente.getAttribute("value"));

		netShopperPO.inputPresente.click();
	}

	/** Testa mensagem de campos em branco da opcao para presente. */
	@Test
	public void T0005_deve_conferir_campos_em_branco_opcao_para_presente() {
		netShopperPO.selecionarOpcaoParaPresente("", "", "");

		Assert.assertEquals("", netShopperPO.inputPresenteDe.getAttribute("value"));
		Assert.assertEquals("", netShopperPO.inputPresentePara.getAttribute("value"));
		Assert.assertEquals("", netShopperPO.inputMensagemPresente.getAttribute("value"));

		netShopperPO.inputPresente.click();
	}

	/** Testa a mensagem de produto não encontrado e o botão de limpar o campo produto. */
	@Test
	public void T0006_deve_conferir_campo_produto_nao_encontrado() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor , nomeVendedor);
		
		netShopperPO.limparCampoInput(netShopperPO.inputProduto);
		netShopperPO.inputProduto.sendKeys("CORTADOR DE GRAMA");

		Assert.assertEquals("Desculpa, mas nenhum produto foi localizado.", netShopperPO.mensagemProdutoNaoEncontrado.getText());
		Assert.assertTrue(netShopperPO.verificarCampoObrigatorio(netShopperPO.inputProduto));
		Assert.assertTrue(netShopperPO.verificarSeElementoEstaNaTela(".fa-xmark"));

		netShopperPO.botaoLimparInputProduto.click();

		Assert.assertTrue(netShopperPO.inputProduto.getAttribute("value").isEmpty());
	}

	/** Testa o campo produto desabilitado quando não houver cliente selecionado. */
	@Test
	public void T0007_deve_conferir_campo_produto_desabilitado_quando_cliente_nao_for_selecionado() {
		netShopperPO.atualizarPagina(5);

		Assert.assertFalse(netShopperPO.verificarSeInputProdutoEstaHabilitado());
	}
	//#endregion
}
