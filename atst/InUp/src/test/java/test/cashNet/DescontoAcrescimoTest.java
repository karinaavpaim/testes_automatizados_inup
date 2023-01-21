package test.cashNet;

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
import page.cashNet.CashNetPO;
import test.BaseTest;

/** Classe de testes para aplicacao de descontos e acrescimos. */
public class DescontoAcrescimoTest extends BaseTest{
    
    //#region Regiao dos atributos
	private static CashNetPO cashNetPO;
	private static DescontoPO descontoPO;
    private static EditarItemPO editarItemPO;
	private static String nomeCliente = "KARINA PAIM";
	private static String nomeLoja = "NORTE SHOPPING";
	private static String nomeVendedor = "Marysol";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanhoDoProduto = 0;
	private static String valorInicial;
	private static String valor = "1000";
	private static double valorDescontoAcrescimo = 10;
	private static double valorFinal;
	//#endregion
	
	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		cashNetPO = new CashNetPO(driver);
		descontoPO = new DescontoPO(driver);
        editarItemPO = new EditarItemPO(driver);
        
		cashNetPO.navegarParaCashNet();
	}
	
	//#region Regiao dos testes

	/** Metodo para ser realizado a cada teste, incluindo um produto ao carrinho. */
    @Before
    public void deve_selecionar_cliente_e_incluir_produto(){
        cashNetPO.atualizarPagina(5);

		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
        cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
        cashNetPO.aguardarInvisibilidadeDoElemento(driver, 3, cashNetPO.conteudoMensagemFlutuante);
    }
	
	/** Testa aplicacao de desconto. */
	@Test
	public void T0001_deve_testar_aplicacao_de_desconto() {
		double valorAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("A pagar"));

		descontoPO.inserirValorDesconto(desconto.VALOR, "1000", "normal", "normal");

		double valorDescontoReal = Double.valueOf(cashNetPO.obterValorResumoVenda("Desconto"));
		double valorLiquido = Double.valueOf(cashNetPO.obterValorResumoVenda("Total Liquido"));

		double valorTotalLiquido = valorAPagar - valorDescontoReal;

		Assert.assertEquals(Double.toString(valorTotalLiquido), Double.toString(valorLiquido));

		descontoPO.inserirValorDesconto(desconto.PORCENTAGEM, "1000", "normal", "normal");

		double valorDescontoPorcento = Double.valueOf(cashNetPO.obterValorResumoVenda("Desconto"));
		double valorLiquidoFinal = Double.valueOf(cashNetPO.obterValorResumoVenda("Total Liquido"));

		double valorTotal = valorAPagar - valorDescontoPorcento;

		Assert.assertEquals(Double.toString(valorTotal), Double.toString(valorLiquidoFinal));
	
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Testa a aplicacao de desconto total. */
	@Test
	public void T0002_deve_testar_desconto_total() {
		valorInicial = cashNetPO.obterValorResumoVenda("A pagar");

		descontoPO.inserirValorDesconto(desconto.VALOR, valorInicial, "normal", "normal");

		String descontoTotalReal = cashNetPO.obterValorResumoVenda("Desconto");

		Assert.assertEquals(descontoTotalReal, cashNetPO.obterValorResumoVenda("Total Itens"));

		descontoPO.inserirValorDesconto(desconto.PORCENTAGEM, "0", "normal", "normal");
		descontoPO.inserirValorDesconto(desconto.PORCENTAGEM, "10000", "normal", "normal");

		String descontoTotalPorcento = cashNetPO.obterValorResumoVenda("Desconto");

		Assert.assertEquals(descontoTotalPorcento, cashNetPO.obterValorResumoVenda("Total Itens"));
	
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);
	}

    /** Testa a aplicacao de desconto no valor do item, primeiro com 10% e depois com R$10,00. */
	@Test
	public void T0003_deve_editar_item_e_aplicar_desconto() {
        valorInicial = cashNetPO.obterValorProdutoAdicionado(nomeProduto);
		
		double valorInicialDecimal = Double.valueOf(valorInicial);
	    double valorDoDesconto = cashNetPO.valorASerDescontadoOuAcrescido(valorDescontoAcrescimo, valorInicialDecimal);

	    valorFinal = valorInicialDecimal - valorDoDesconto;

        editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.PERCENTUAL, valor);

        Assert.assertEquals(cashNetPO.formatarValorDecimal(valorFinal), cashNetPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal - valorDescontoAcrescimo;

        Assert.assertEquals(cashNetPO.formatarValorDecimal(valorFinal), cashNetPO.obterValorProdutoAdicionado(nomeProduto));
    }

	 /** Testa a aplicacao de acrescimo no valor do item, primeiro com 10% e depois com R$10,00. */
	@Test
	public void T0004_deve_editar_item_e_aplicar_acrescimo() {
        valorInicial = cashNetPO.obterValorProdutoAdicionado(nomeProduto);
		
		double valorInicialDecimal = Double.valueOf(valorInicial);
	    double valorDoAcrescimo = cashNetPO.valorASerDescontadoOuAcrescido(valorDescontoAcrescimo, valorInicialDecimal);

	    valorFinal = valorInicialDecimal + valorDoAcrescimo;

        editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.PERCENTUAL, valor);

        Assert.assertEquals(cashNetPO.formatarValorDecimal(valorFinal), cashNetPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal + valorDescontoAcrescimo;

        Assert.assertEquals(cashNetPO.formatarValorDecimal(valorFinal), cashNetPO.obterValorProdutoAdicionado(nomeProduto));
    }

	/** Testa a aplicacao e retirada de acrescimo no valor do item. */
	@Ignore ("IN-10118")
	@Test
	public void T0005_deve_editar_item_aplicar_e_retirar_o_acrescimo() {
        valorInicial = cashNetPO.obterValorProdutoAdicionado(nomeProduto);

		double valorInicialDecimal = Double.valueOf(valorInicial);

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal + valorDescontoAcrescimo;

        Assert.assertEquals(cashNetPO.formatarValorDecimal(valorFinal), cashNetPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.ACRESCIMO, tipoValor.VALOR, "0");

		double valorOriginal = valorFinal - valorDescontoAcrescimo;

        Assert.assertEquals(valorOriginal, cashNetPO.obterValorProdutoAdicionado(nomeProduto));
    }

	/** Testa a aplicacao e retirada de desconto no valor do item. */
	@Ignore ("IN-10118")
	@Test
	public void T0006_deve_editar_item_aplicar_e_retirar_o_desconto() {
        valorInicial = cashNetPO.obterValorProdutoAdicionado(nomeProduto);

		double valorInicialDecimal = Double.valueOf(valorInicial);

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.VALOR, valor);

	    valorFinal = valorInicialDecimal - valorDescontoAcrescimo;

        Assert.assertEquals(cashNetPO.formatarValorDecimal(valorFinal), cashNetPO.obterValorProdutoAdicionado(nomeProduto));

		editarItemPO.aplicarDescontoOuAcrescimo(tipoEditarItem.DESCONTO, tipoValor.VALOR, "0");

		double valorOriginal = valorFinal + valorDescontoAcrescimo;

        Assert.assertEquals(valorOriginal, cashNetPO.obterValorProdutoAdicionado(nomeProduto));
	}
	//#endregion
}
