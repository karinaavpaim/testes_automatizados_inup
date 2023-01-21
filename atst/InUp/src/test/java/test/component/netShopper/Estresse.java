package test.component.netShopper;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe para testar o estresse das ações da tela de NetShopper. */
public class Estresse extends BaseTest {

    // #region Regiao dos atributos
	private static NetShopperPO netShopperPO;
    private static String nomeCliente = "KARINA PAIM";
    private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanho = 0;
    private static String frete = "CORREIOS";
	// #endregion

	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes() {
		netShopperPO = new NetShopperPO(driver);
		
		netShopperPO.navegarParaNetshopper();
    }

    /** Testa a troca estressante de cliente. */
	@Test
	public void T0001_deve_estressar_a_troca_de_cliente() {
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);

		Assert.assertEquals(nomeCliente, netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "LUCAS PADILHA");

		Assert.assertEquals("LUCAS PADILHA", netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "VANIA GONCALVES DOS SANTOS");

		Assert.assertEquals("VANIA GONCALVES DOS SANTOS", netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "ADRIANA MARIA");

		Assert.assertEquals("ADRIANA MARIA MARINS", netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();
		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "ERON ROBERTO");

		Assert.assertEquals("ERON ROBERTO", netShopperPO.inputClienteSelecionado.getAttribute("value"));

		netShopperPO.botaoAdicionarCliente.click();
		netShopperPO.limparCampoInput(netShopperPO.inputCliente);
    }

 	/** Testa a troca estressante de produto. */
 	@Test
 	public void T0002_deve_estressar_a_troca_de_produto_no_carrinho() {
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor , nomeVendedor);

    	netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
    	netShopperPO.selecionarFrete(frete);

		Assert.assertTrue(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals(nomeProduto, netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		netShopperPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanho);

		Assert.assertTrue(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals("SANDALIA RASTEIRA IPANEMA", netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		netShopperPO.adicionarProdutoAoCarrinho("COLCHÃO ORTOPÉDICO NASA", indexCorOuTamanho);

		Assert.assertTrue(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals("COLCHÃO ORTOPÉDICO NASA", netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		netShopperPO.adicionarProdutoAoCarrinho("BRINCO ARGOLAS", indexCorOuTamanho);

		Assert.assertTrue(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));
		Assert.assertEquals("BRINCO ARGOLAS", netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

		netShopperPO.botaoExcluirItem.click();
		netShopperPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertFalse(netShopperPO.verificarSeElementoEstaNaTela(".flex-grow-1 > .h4"));

		netShopperPO.botaoExpandirCliente.click();

		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 10, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Testa a troca estressante de loja. */
	@Test
	public void T0003_deve_estressar_a_troca_de_loja() {
		netShopperPO.atualizarPagina(10);

		Select selectLojas = new Select(netShopperPO.selectLoja);

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, "ESCRITORIO");
		netShopperPO.botaoOK.click();

		Assert.assertEquals("ESCRITORIO", selectLojas.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, "FASHION MALL");
		netShopperPO.botaoOK.click();

		Assert.assertEquals("FASHION MALL", selectLojas.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, "DEFEITO");
		netShopperPO.botaoOK.click();

		Assert.assertEquals("DEFEITO", selectLojas.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);

		Assert.assertEquals(nomeLoja, selectLojas.getFirstSelectedOption().getAccessibleName());
	}

	/** Testa a inclusão e exclusão estressante de produtos. */
	@Test
	public void T0004_deve_estressar_a_inclusao_e_exclusao_de_produtos_no_carrinho() {
		netShopperPO.atualizarPagina(10);

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor , nomeVendedor);	   
		netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);
	   	netShopperPO.selecionarFrete(frete);
	   
	   	netShopperPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanho);
	   	netShopperPO.adicionarProdutoAoCarrinho("COLCHÃO ORTOPÉDICO NASA", indexCorOuTamanho);
	   	netShopperPO.adicionarProdutoAoCarrinho("BRINCO ARGOLAS", indexCorOuTamanho);

	  	Assert.assertEquals("4", netShopperPO.obterValorResumoVenda("Carrinho"));
	 
	   	netShopperPO.botaoExcluirItem.click();
	   	netShopperPO.botaoConfirmarMensagemFlutuante.click();

	   	netShopperPO.botaoExcluirItem.click();
	   	netShopperPO.botaoConfirmarMensagemFlutuante.click();

	   	netShopperPO.botaoExcluirItem.click();
	   	netShopperPO.botaoConfirmarMensagemFlutuante.click();

	   	netShopperPO.botaoExcluirItem.click();
	   	netShopperPO.botaoConfirmarMensagemFlutuante.click();

	   	netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
	   	netShopperPO.inputQuantidade.sendKeys("100");
	   	netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanho);

	   	Assert.assertEquals("100", netShopperPO.obterValorResumoVenda("Carrinho"));

	   	netShopperPO.botaoExcluirItem.click();
	   	netShopperPO.botaoConfirmarMensagemFlutuante.click();

	   	netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
	   	netShopperPO.inputQuantidade.sendKeys("10");
	   	netShopperPO.adicionarProdutoAoCarrinho("SANDALIA RASTEIRA IPANEMA", indexCorOuTamanho);

	   	netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
	   	netShopperPO.inputQuantidade.sendKeys("10");
	 
	   	netShopperPO.adicionarProdutoAoCarrinho("COLCHÃO ORTOPÉDICO NASA", indexCorOuTamanho);

	   	netShopperPO.limparCampoInput(netShopperPO.inputQuantidade);
	   	netShopperPO.inputQuantidade.sendKeys("10");

	   	netShopperPO.adicionarProdutoAoCarrinho("BRINCO ARGOLAS", indexCorOuTamanho);

	   	Assert.assertEquals("30", netShopperPO.obterValorResumoVenda("Carrinho"));
   }

   /** Testa a troca estressante de vendedor. */
	@Test
	public void T0005_deve_estressar_a_troca_de_vendedor() {
		netShopperPO.atualizarPagina(10);

		Select selectVendedores = new Select(netShopperPO.selectVendedor);
		
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja, nomeLoja);

		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor , nomeVendedor);	   
		
		Assert.assertEquals("Marysol", selectVendedores.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, "Qld Vendedor");
		
		Assert.assertEquals("Qld Vendedor", selectVendedores.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, "Vendedor 1");
		
		Assert.assertEquals("Vendedor 1", selectVendedores.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, "Vendedor 8");
		
		Assert.assertEquals("Vendedor 8", selectVendedores.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, "Venda Rápida");
		
		Assert.assertEquals("Venda Rápida", selectVendedores.getFirstSelectedOption().getAccessibleName());

		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor, "Thais Santana");
		
		Assert.assertEquals("Thais Santana", selectVendedores.getFirstSelectedOption().getAccessibleName());
	}

	 /** Testa a abertura e fechamento estressante da aba de vendas. */
	 @Test
	 public void T0006_deve_estressar_a_aba_de_vendas() {
		netShopperPO.atualizarPagina(20);

		netShopperPO.adicionarNovaAbaDeVenda();
		netShopperPO.adicionarNovaAbaDeVenda();
		netShopperPO.adicionarNovaAbaDeVenda();

		netShopperPO.selecionarAbaDeVenda(0);

		Assert.assertTrue(netShopperPO.verificarCamposVazios());

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "LUCAS PADILHA");
	
		netShopperPO.selecionarAbaDeVenda(1);

		Assert.assertTrue(netShopperPO.verificarCamposVazios());

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "KARINA PAIM");

		netShopperPO.selecionarAbaDeVenda(2);

		Assert.assertTrue(netShopperPO.verificarCamposVazios());

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "VANIA GONCALVES DOS SANTOS");

		netShopperPO.selecionarAbaDeVenda(3);

		Assert.assertTrue(netShopperPO.verificarCamposVazios());

		netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, "WOLVERINE");

		netShopperPO.selecionarAbaDeVenda(1);
		netShopperPO.aguardarTextoNoElementoInput(netShopperPO.inputClienteSelecionado, "KARINA PAIM");
		
		Assert.assertEquals("KARINA PAIM", netShopperPO.obterNomeDoClienteSelecionado());

		netShopperPO.selecionarAbaDeVenda(3);
		netShopperPO.aguardarTextoNoElementoInput(netShopperPO.inputClienteSelecionado, "WOLVERINE");

		Assert.assertEquals("WOLVERINE", netShopperPO.obterNomeDoClienteSelecionado());

		netShopperPO.selecionarAbaDeVenda(0);
		netShopperPO.aguardarTextoNoElementoInput(netShopperPO.inputClienteSelecionado, "LUCAS PADILHA");

		Assert.assertEquals("LUCAS PADILHA", netShopperPO.obterNomeDoClienteSelecionado());

		netShopperPO.selecionarAbaDeVenda(2);
		netShopperPO.aguardarTextoNoElementoInput(netShopperPO.inputClienteSelecionado, "VANIA GONCALVES DOS SANTOS");

		Assert.assertEquals("VANIA GONCALVES DOS SANTOS", netShopperPO.obterNomeDoClienteSelecionado());
	
		netShopperPO.excluirAbaDeVenda(1);
		netShopperPO.excluirAbaDeVenda(2);
		netShopperPO.excluirAbaDeVenda(0);

		List<WebElement> abas = netShopperPO.barraNovasVendas.findElements(By.xpath("//li[@class='nav-item ng-star-inserted']"));

		Assert.assertEquals(1, abas.size());
	 }
}
