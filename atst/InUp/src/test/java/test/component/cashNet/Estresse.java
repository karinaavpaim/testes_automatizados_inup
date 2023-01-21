package test.component.cashNet;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import page.cashNet.CashNetPO;
import test.BaseTest;

/** Classe para testar o estresse das ações da tela de CashNet. */
public class Estresse extends BaseTest {

	// #region Regiao dos atributos
	private static CashNetPO cashNetPO;
	private static String nomeCliente = "KARINA PAIM";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanho = 0;
	// #endregion

	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes() {
		cashNetPO = new CashNetPO(driver);

		cashNetPO.navegarParaCashNet();
	}

	/** Testa a troca estressante de cliente. */
	@Test
	public void T0001_deve_estressar_a_troca_de_cliente() {
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, nomeLoja);

		Assert.assertEquals(nomeCliente, cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "LUCAS PADILHA");

		Assert.assertEquals("LUCAS PADILHA", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "VANIA GONCALVES DOS SANTOS");

		Assert.assertEquals("VANIA GONCALVES DOS SANTOS", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "ADRIANA MARIA MARINS");

		Assert.assertEquals("ADRIANA MARIA MARINS", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "ERON ROBERTO");

		Assert.assertEquals("ERON ROBERTO", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.limparCampoInput(cashNetPO.inputCliente);
	}

	/** Testa a troca estressante de produto. 
	 * @throws InterruptedException */
	@Test
	public void T0002_deve_estressar_a_troca_de_produto_no_carrinho() throws InterruptedException {
		cashNetPO.atualizarPagina(10);

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);

		Assert.assertTrue(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals(nomeProduto, cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		Thread.sleep(2000);
		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		cashNetPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanho);

		Assert.assertTrue(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals("SANDALIA RASTEIRA IPANEMA", cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		cashNetPO.adicionarProdutoAoCarrinho("COLCHÃO ORTOPÉDICO NASA", indexCorOuTamanho);

		Assert.assertTrue(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals("COLCHÃO ORTOPÉDICO NASA", cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		cashNetPO.adicionarProdutoAoCarrinho("BRINCO ARGOLAS", indexCorOuTamanho);

		Assert.assertTrue(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals("BRINCO ARGOLAS", cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(cashNetPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		cashNetPO.botaoExpandirCliente.click();

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Testa a troca estressante de loja. */
	@Test
	public void T0003_deve_estressar_a_troca_de_loja() {
		cashNetPO.atualizarPagina(10);

		Select selectLojas = new Select(cashNetPO.selectLoja);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, nomeLoja);

		Assert.assertEquals(nomeLoja, selectLojas.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "ESCRITORIO");
		cashNetPO.fecharModaisAvisos();

		Assert.assertEquals("ESCRITORIO", selectLojas.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "DEFEITO");
		cashNetPO.fecharModaisAvisos();

		Assert.assertEquals("DEFEITO", selectLojas.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "FASHION MALL");
		cashNetPO.fecharModaisAvisos();

		Assert.assertEquals("FASHION MALL", selectLojas.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, nomeLoja);

		Assert.assertEquals(nomeLoja, selectLojas.getFirstSelectedOption().getAccessibleName());
	}

	/** Testa a inclusão e exclusão estressante de produtos. */
	@Test
	public void T0004_deve_estressar_a_inclusao_e_exclusao_de_produtos_no_carrinho() {
		cashNetPO.atualizarPagina(10);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);

		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
		cashNetPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanho);
		cashNetPO.adicionarProdutoAoCarrinho("COLCHÃO ORTOPÉDICO NASA", indexCorOuTamanho);
		cashNetPO.adicionarProdutoAoCarrinho("BRINCO ARGOLAS", indexCorOuTamanho);

		Assert.assertEquals("4", cashNetPO.obterValorResumoVenda("Carrinho"));

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("100");
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);

		Assert.assertEquals("100", cashNetPO.obterValorResumoVenda("Carrinho"));

		cashNetPO.botaoExcluirItem.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("10");
		cashNetPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanho);

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("10");

		cashNetPO.adicionarProdutoAoCarrinho("COLCHÃO ORTOPÉDICO NASA", indexCorOuTamanho);

		cashNetPO.limparCampoInput(cashNetPO.inputQuantidade);
		cashNetPO.inputQuantidade.sendKeys("10");

		cashNetPO.adicionarProdutoAoCarrinho("BRINCO ARGOLAS", indexCorOuTamanho);

		Assert.assertEquals("30", cashNetPO.obterValorResumoVenda("Carrinho"));
	}

	/** Testa a troca estressante de vendedor. */
	@Test
	public void T0005_deve_estressar_a_troca_de_vendedor() {
		cashNetPO.atualizarPagina(10);

		Select selectVendedores = new Select(cashNetPO.selectVendedor);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, nomeLoja);
		
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);

		Assert.assertEquals("Marysol", selectVendedores.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor, "Qld Vendedor");

		Assert.assertEquals("Qld Vendedor", selectVendedores.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor, "Vendedor 1");

		Assert.assertEquals("Vendedor 1", selectVendedores.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor, "Vendedor 8");

		Assert.assertEquals("Vendedor 8", selectVendedores.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor, "Venda Rápida");

		Assert.assertEquals("Venda Rápida", selectVendedores.getFirstSelectedOption().getAccessibleName());

		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor, "Thais Santana");

		Assert.assertEquals("Thais Santana", selectVendedores.getFirstSelectedOption().getAccessibleName());
	}

	/** Testa a abertura e fechamento estressante da aba de vendas. */
	@Test
	public void T0006_deve_estressar_a_aba_de_vendas() {
		cashNetPO.atualizarPagina(20);

		cashNetPO.adicionarNovaAbaDeVenda();
		cashNetPO.adicionarNovaAbaDeVenda();
		cashNetPO.adicionarNovaAbaDeVenda();

		cashNetPO.selecionarAbaDeVenda(0);

		Assert.assertTrue(cashNetPO.verificarCamposVazios());

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "LUCAS PADILHA");

		cashNetPO.selecionarAbaDeVenda(1);

		Assert.assertTrue(cashNetPO.verificarCamposVazios());

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "KARINA PAIM");

		cashNetPO.selecionarAbaDeVenda(2);

		Assert.assertTrue(cashNetPO.verificarCamposVazios());

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "VANIA GONCALVES DOS SANTOS");

		cashNetPO.selecionarAbaDeVenda(3);

		Assert.assertTrue(cashNetPO.verificarCamposVazios());

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "WOLVERINE");

		cashNetPO.selecionarAbaDeVenda(1);
		cashNetPO.aguardarTextoNoElementoInput(cashNetPO.inputClienteSelecionado, "KARINA PAIM");

		Assert.assertEquals("KARINA PAIM", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.selecionarAbaDeVenda(3);
		cashNetPO.aguardarTextoNoElementoInput(cashNetPO.inputClienteSelecionado, "WOLVERINE");

		Assert.assertEquals("WOLVERINE", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.selecionarAbaDeVenda(0);
		cashNetPO.aguardarTextoNoElementoInput(cashNetPO.inputClienteSelecionado, "LUCAS PADILHA");

		Assert.assertEquals("LUCAS PADILHA", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.selecionarAbaDeVenda(2);
		cashNetPO.aguardarTextoNoElementoInput(cashNetPO.inputClienteSelecionado, "VANIA GONCALVES DOS SANTOS");

		Assert.assertEquals("VANIA GONCALVES DOS SANTOS", cashNetPO.inputClienteSelecionado.getAttribute("value"));

		cashNetPO.excluirAbaDeVenda(1);
		cashNetPO.excluirAbaDeVenda(2);
		cashNetPO.excluirAbaDeVenda(0);

		List<WebElement> abas = cashNetPO.barraNovasVendas
				.findElements(By.xpath("//li[@class='nav-item ng-star-inserted']"));

		Assert.assertEquals(1, abas.size());
	}
}
