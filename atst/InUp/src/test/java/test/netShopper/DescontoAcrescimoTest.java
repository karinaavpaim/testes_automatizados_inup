package test.netShopper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import page.DescontoPO;
import page.DescontoPO.desconto;
import page.EditarItemPO;
import page.EditarItemPO.tipoEditarItem;
import page.EditarItemPO.tipoValor;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe de testes para aplicacao de descontos e acrescimos. */
public class DescontoAcrescimoTest extends BaseTest{

    //#region Regiao dos atributos
	private static NetShopperPO netShopperPO;
	private static DescontoPO descontoPO;
    private static EditarItemPO editarItemPO;
    private static String nomeCliente = "Karina Paim";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanhoDoProduto = 0;
    private static String frete = "CORREIOS";
	private static String valorInicial;
	private static String valor = "1000";
	private static double valorDescontoAcrescimo = 10;
	private static double valorFinal;
	//#endregion

    /** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		netShopperPO = new NetShopperPO(driver);
		descontoPO = new DescontoPO(driver);
        editarItemPO = new EditarItemPO(driver);
        
		netShopperPO.navegarParaNetshopper();
	}
	
	//#region Regiao dos testes

    @Before
    public void deve_selecionar_cliente_e_incluir_produto(){
        netShopperPO.atualizarPagina(5);

        netShopperPO.selecionarNomeAutocomplete(netShopperPO.inputCliente, nomeCliente);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , nomeLoja);
		netShopperPO.selecionarElementoSelect(netShopperPO.selectVendedor , nomeVendedor);
        netShopperPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
        netShopperPO.selecionarFrete(frete);
        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 3, netShopperPO.conteudoMensagemFlutuante);
    }

    /** Testa a aplicacao de desconto em real e em porcentagem. */
	@Test
	public void T0001_deve_testar_aplicacao_de_desconto() {
		double valorAPagar = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Itens"));

		descontoPO.inserirValorDesconto(desconto.VALOR, "1000", "normal", "normal");

		double valorDescontoReal = Double.valueOf(netShopperPO.obterValorResumoVenda("Desconto"));
		double valorLiquido = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Liquido"));

		double valorTotalLiquido = valorAPagar - valorDescontoReal;

		Assert.assertEquals(Double.toString(valorTotalLiquido), Double.toString(valorLiquido));

		descontoPO.inserirValorDesconto(desconto.PORCENTAGEM, "1000", "normal", "normal");

		double valorDescontoPorcento = Double.valueOf(netShopperPO.obterValorResumoVenda("Desconto"));
		double valorLiquidoFinal = Double.valueOf(netShopperPO.obterValorResumoVenda("Total Liquido"));

		double valorTotal = valorAPagar - valorDescontoPorcento;

		Assert.assertEquals(Double.toString(valorTotal), Double.toString(valorLiquidoFinal));
		
		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 10, netShopperPO.conteudoMensagemFlutuante);
	}

	/** Testa a aplicacao de desconto total. */
	@Test
	public void T0002_deve_testar_desconto_total() {
		valorInicial = netShopperPO.obterValorResumoVenda("Total Itens");

		descontoPO.inserirValorDesconto(desconto.VALOR, valorInicial, "normal", "normal");

		String descontoTotalReal = netShopperPO.obterValorResumoVenda("Desconto");

		Assert.assertEquals(descontoTotalReal, netShopperPO.obterValorResumoVenda("Total Itens"));

		descontoPO.inserirValorDesconto(desconto.PORCENTAGEM, "0", "normal", "normal");
		descontoPO.inserirValorDesconto(desconto.PORCENTAGEM, "10000", "normal", "normal");

		String descontoTotalPorcento = netShopperPO.obterValorResumoVenda("Desconto");

		Assert.assertEquals(descontoTotalPorcento, netShopperPO.obterValorResumoVenda("Total Itens"));

		netShopperPO.aguardarInvisibilidadeDoElemento(driver, 10, netShopperPO.conteudoMensagemFlutuante);
	}
    
    /** Testa a aplicacao de desconto no valor do item, primeiro com 10% e depois com R$10,00. */
	@Test
	public void T0003_deve_editar_item_e_aplicar_desconto() {
        valorInicial = netShopperPO.obterValorProdutoAdicionado(nomeProduto);
		
		double valorInicialDecimal = Double.valueOf(valorInicial);
	    double valorDoDesconto = netShopperPO.valorASerDescontadoOuAcrescido(valorDescontoAcrescimo, valorInicialDecimal);

	    valorFinal = valorInicialDecimal - valorDoDesconto;

        editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.PERCENTUAL, valor);

        Assert.assertEquals(netShopperPO.formatarValorDecimal(valorFinal), netShopperPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal - valorDescontoAcrescimo;

        Assert.assertEquals(netShopperPO.formatarValorDecimal(valorFinal), netShopperPO.obterValorProdutoAdicionado(nomeProduto));
    }

	/** Testa a aplicacao de acrescimo no valor do item, primeiro com 10% e depois com R$10,00. */
	@Test
	public void T0004_deve_editar_item_e_aplicar_acrescimo() {
        valorInicial = netShopperPO.obterValorProdutoAdicionado(nomeProduto);
		
		double valorInicialDecimal = Double.valueOf(valorInicial);
	    double valorDoAcrescimo = netShopperPO.valorASerDescontadoOuAcrescido(valorDescontoAcrescimo, valorInicialDecimal);

	    valorFinal = valorInicialDecimal + valorDoAcrescimo;

        editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.PERCENTUAL, valor);

        Assert.assertEquals(netShopperPO.formatarValorDecimal(valorFinal), netShopperPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal + valorDescontoAcrescimo;

        Assert.assertEquals(netShopperPO.formatarValorDecimal(valorFinal), netShopperPO.obterValorProdutoAdicionado(nomeProduto));
    }

	/** Testa a aplicacao e retirada de acrescimo no valor do item. */
	@Ignore ("IN-10118")
	@Test
	public void T0005_deve_editar_item_aplicar_e_retirar_o_acrescimo() {
        valorInicial = netShopperPO.obterValorProdutoAdicionado(nomeProduto);

		double valorInicialDecimal = Double.valueOf(valorInicial);

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal + valorDescontoAcrescimo;

        Assert.assertEquals(netShopperPO.formatarValorDecimal(valorFinal), netShopperPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.VALOR, "0");

		double valorOriginal = valorFinal - valorDescontoAcrescimo;

        Assert.assertEquals(valorOriginal, netShopperPO.obterValorProdutoAdicionado(nomeProduto));
    }

	/** Testa a aplicacao e retirada de desconto no valor do item. */
	@Ignore ("IN-10118")
	@Test
	public void T0006_deve_editar_item_aplicar_e_retirar_o_desconto() {
        valorInicial = netShopperPO.obterValorProdutoAdicionado(nomeProduto);

		double valorInicialDecimal = Double.valueOf(valorInicial);

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal - valorDescontoAcrescimo;

        Assert.assertEquals(netShopperPO.formatarValorDecimal(valorFinal), netShopperPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.VALOR, "0");

		double valorOriginal = valorFinal + valorDescontoAcrescimo;

        Assert.assertEquals(valorOriginal, netShopperPO.obterValorProdutoAdicionado(nomeProduto));
	}
	//#endregion
}
