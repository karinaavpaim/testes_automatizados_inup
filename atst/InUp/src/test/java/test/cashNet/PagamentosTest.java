package test.cashNet;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import builder.cashNet.CartaoPosBuilder;
import page.DinheiroPO;
import page.LoginPO;
import page.cashNet.CartaoPO;
import page.cashNet.CartaoPO.tipoCartao;
import page.cashNet.CashNetPO;
import page.cashNet.PixPO;
import test.BaseTest;

/** Classe de testes para realizar pagamentos no CashNet. */
public class PagamentosTest extends BaseTest {
	
	//#region Atributos
	private static CashNetPO cashNetPO;
	private static LoginPO loginPO;
	private static DinheiroPO dinheiroPO;
	private static PixPO pixPO;
	private static CartaoPO cartaoPO;
	private static CartaoPosBuilder cartaoPosBuilder;
	private static String usuario = "interfacenetup@alterdata.com.br";
	private static String senha = "123456";
	private static String nomeCliente = "Carla Dias";
	private static String nomeLoja = "ALTERDATA TECNOLOGIA";
	private static String nomeVendedor = "Vendedor Up";
	private static String nomeProduto = "VESTUARIO MÃE BABADOR";
	private static int indexCorOuTamanhoDoProduto = 0;
	private static String valorAPagar;
	private static double valorTroco;
	private static double valorPago;
	//#endregion

	//#region Regiao dos testes

	/** Metodo para inciar os testes. */
	@BeforeClass
	public static void iniciarTestes(){
		cashNetPO = new CashNetPO(driver);
		loginPO = new LoginPO(driver);
		dinheiroPO = new DinheiroPO(driver);
		pixPO = new PixPO(driver);
		cartaoPO = new CartaoPO(driver);
		cartaoPosBuilder = new CartaoPosBuilder(cartaoPO);
		
		realizarLogoffInUp();
		loginPO.realizaNovoAcessoAoInUp(usuario, senha);
			
		loginPO.aguardarVisibilidadeDoElemento(driver, 5, loginPO.redeQLDIN);
		loginPO.redeQLDIN.click();
		
		cashNetPO.navegarParaCashNet();
	}

	/**Metodo para encerrar esta classe de testes */
	@AfterClass
	public static void deve_fechar_mensagens_flutuantes(){
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);
	}

	/** Metodo para ser realizado a cada teste, selecionando o cliente e incluindo um produto ao carrinho. */
	@Before
	public void deve_selecionar_cliente_e_incluir_produto(){
		cashNetPO.atualizarPagina(5);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.aguardarElementoSerClicavel(driver, 5, cashNetPO.inputCliente);
		
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, nomeCliente);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
	}

	/** Testa pagamento em dinheiro. */
	@Test
	public void T0001_deve_realizar_pagamento_em_dinheiro() {
		valorAPagar = cashNetPO.obterValorResumoVenda("A pagar");

		dinheiroPO.realizarPagamentoEmDinheiro(valorAPagar);

		cashNetPO.botaoFinalizarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.aguardarGeracaoDaNotaFiscal();

		Assert.assertEquals("Venda realizada com sucesso!", cashNetPO.mensagemVendaRealizadaComSucesso.getText());
		Assert.assertEquals("NFC-e autorizada.", cashNetPO.statusVenda.getText());
		
		cashNetPO.fecharVendaFinalizadaComNotaFiscal();
	}

	/** Testa pagamento com cartao pós debito. */
	@Test
	public void T0002_deve_realizar_pagamento_com_cartao_pos_debito() {
		valorAPagar = cashNetPO.obterValorResumoVenda("A pagar");
		
		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comBandeira("105 - CIELO")
			.comValor(valorAPagar)
			.preencherDadosCartaoPOS(tipoCartao.DEBITO);
		
		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		cartaoPO.aguardarInvisibilidadeDoElemento(driver, 10, cartaoPO.conteudoMensagemFlutuante);
		cartaoPO.botaoFechar.click();

		cashNetPO.botaoFinalizarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.aguardarGeracaoDaNotaFiscal();
		
		Assert.assertEquals("Venda realizada com sucesso!", cashNetPO.mensagemVendaRealizadaComSucesso.getText());
		Assert.assertEquals("NFC-e autorizada.", cashNetPO.statusVenda.getText());
		
		cashNetPO.fecharVendaFinalizadaComNotaFiscal();
	}

	/** Testa pagamento com cartao pós credito. */
	@Test
	public void T0003_deve_realizar_pagamento_com_cartao_pos_credito() {
		valorAPagar = cashNetPO.obterValorResumoVenda("A pagar");
		
		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor(valorAPagar)
			.comBandeira("125 - CARTÃO TESTE")
			.preencherDadosCartaoPOS(tipoCartao.CREDITO);
		
		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		cartaoPO.aguardarInvisibilidadeDoElemento(driver, 10, cartaoPO.conteudoMensagemFlutuante);
		cartaoPO.botaoFechar.click();

		cashNetPO.botaoFinalizarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.aguardarGeracaoDaNotaFiscal();
		
		Assert.assertEquals("Venda realizada com sucesso!", cashNetPO.mensagemVendaRealizadaComSucesso.getText());
		Assert.assertEquals("NFC-e autorizada.", cashNetPO.statusVenda.getText());
		
		cashNetPO.fecharVendaFinalizadaComNotaFiscal();
	}
		
	/** Testa o pagamento em parte com dinheiro e outra parte com cartao pos, debito e crédito. 
	 * @throws InterruptedException */
	@Test
	public void T0004_deve_realizar_pagamento_com_cartao_pos_debito_credito_e_dinheiro() throws InterruptedException{
		dinheiroPO.realizarPagamentoEmDinheiro("0001");
		
		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor("0001")
			.comBandeira("105 - CIELO")
			.preencherDadosCartaoPOS(tipoCartao.DEBITO);
		
		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoFechar.click();

		valorAPagar = cashNetPO.obterValorResumoVenda("A pagar");

		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor(valorAPagar)
			.comBandeira("125 - CARTÃO TESTE")
			.preencherDadosCartaoPOS(tipoCartao.CREDITO);
	
		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoFechar.click();

		cashNetPO.botaoFinalizarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.aguardarGeracaoDaNotaFiscal();
		
		Assert.assertEquals("Venda realizada com sucesso!", cashNetPO.mensagemVendaRealizadaComSucesso.getText());
		Assert.assertEquals("NFC-e autorizada.", cashNetPO.statusVenda.getText());
		
		cashNetPO.fecharVendaFinalizadaComNotaFiscal();
	}

	/** Testa a finalizacao da venda sem preenchimento do pagamento. */
	@Test
	public void T0005_deve_testar_realizar_pagamento_sem_preencher_o_valor() {
		cashNetPO.fecharMensagensFlutuantes();
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);

		cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.botaoFinalizarVenda);
		cashNetPO.botaoFinalizarVenda.click();

		Assert.assertEquals("Ops!\nO valor à pagar não foi totalmente preenchido.", cashNetPO.obterTextoMensagemFlutuante(driver, 5));
	}

	/** Testa a conferência de valores com troco para dinheiro. */
	@Test
	public void T0006_deve_testar_preenchendo_um_valor_acima_da_venda_e_confirmar_troco_dinheiro() {
		double valorAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("A pagar"));

		dinheiroPO.realizarPagamentoEmDinheiro(String.valueOf(valorAPagar) + "00");

		valorTroco = Double.valueOf(cashNetPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(cashNetPO.obterValorResumoVenda("Dinheiro"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}

	/** Testa a conferencia de valores com troco para cartao de debito. */
	@Test
	public void T0007_deve_testar_preenchendo_um_valor_acima_da_venda_e_confirmar_troco_cartao_debito() {
		double valorAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("A pagar"));

		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor(valorAPagar + "00")
			.comBandeira("105 - CIELO")
			.preencherDadosCartaoPOS(tipoCartao.DEBITO);

		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		cartaoPO.aguardarInvisibilidadeDoElemento(driver, 10, cartaoPO.conteudoMensagemFlutuante);
		cartaoPO.botaoFechar.click();

		valorTroco = Double.valueOf(cashNetPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(cashNetPO.obterValorResumoVenda("Cartão"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}

	/** Testa a conferencia de valores com troco para cartao de credito. */
	@Test
	public void T0008_deve_testar_preenchendo_um_valor_acima_da_venda_e_confirmar_troco_cartao_credito() {
		double valorAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("A pagar"));

		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor(valorAPagar + "00")
			.comBandeira("125 - CARTÃO TESTE")
			.preencherDadosCartaoPOS(tipoCartao.CREDITO);

		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		cartaoPO.aguardarInvisibilidadeDoElemento(driver, 10, cartaoPO.conteudoMensagemFlutuante);
		cartaoPO.botaoFechar.click();

		valorTroco = Double.valueOf(cashNetPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(cashNetPO.obterValorResumoVenda("Cartão"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}

	/** Testa a conferência de valores com troco para pagamento com cartao e dinheiro. 
	 * @throws InterruptedException */
	@Test
	public void T0009_deve_testar_preenchendo_um_valor_para_cartao_debito_credito_e_dinheiro_com_troco() throws InterruptedException {
		cashNetPO.fecharMensagensFlutuantes();

		double valorAPagar = Double.valueOf(cashNetPO.obterValorResumoVenda("A pagar"));

		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor("100")
			.comBandeira("125 - CARTÃO TESTE")
			.preencherDadosCartaoPOS(tipoCartao.CREDITO);

		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor("100")
			.comBandeira("ELO")
			.preencherDadosCartaoPOS(tipoCartao.DEBITO);

		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoFechar.click();

		dinheiroPO.realizarPagamentoEmDinheiro(valorAPagar + "00");
	
		valorTroco = Double.valueOf(cashNetPO.obterValorResumoVenda("Troco"));
		valorPago = Double.valueOf(cashNetPO.obterValorResumoVenda("Cartão")) + Double.valueOf(cashNetPO.obterValorResumoVenda("Dinheiro"));

		double troco = valorPago - valorAPagar;

		Assert.assertEquals(Double.toString(troco), Double.toString(valorTroco));
	}

	/** Testa a realização de venda em dinheiro sem selecionar o cliente. */
	@Test
	public void T0010_deve_realizar_venda_sem_cliente_em_dinheiro() {
		cashNetPO.atualizarPagina(5);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);

		valorAPagar = cashNetPO.obterValorResumoVenda("A pagar");

		dinheiroPO.realizarPagamentoEmDinheiro(valorAPagar);

		cashNetPO.botaoFinalizarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.aguardarGeracaoDaNotaFiscal();

		Assert.assertEquals("Venda realizada com sucesso!", cashNetPO.mensagemVendaRealizadaComSucesso.getText());
		Assert.assertEquals("NFC-e autorizada.", cashNetPO.statusVenda.getText());
		
		cashNetPO.fecharVendaFinalizadaComNotaFiscal();
	}

	/** Testa o pagamento com cartao pos, debito e crédito sem selecionar o cliente. 
	 * @throws InterruptedException */
	@Test
	public void T0011_deve_realizar_pagamento_com_cartao_pos_debito_credito_sem_cliente() throws InterruptedException{
		cashNetPO.atualizarPagina(5);

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , nomeLoja);
		cashNetPO.selecionarElementoSelect(cashNetPO.selectVendedor , nomeVendedor);
		cashNetPO.adicionarProdutoAoCarrinho(nomeProduto, indexCorOuTamanhoDoProduto);
		
		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor("0001")
			.comBandeira("105 - CIELO")
			.preencherDadosCartaoPOS(tipoCartao.DEBITO);
		
		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoFechar.click();

		valorAPagar = cashNetPO.obterValorResumoVenda("A pagar");

		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor(valorAPagar)
			.comBandeira("125 - CARTÃO TESTE")
			.preencherDadosCartaoPOS(tipoCartao.CREDITO);
	
		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoFechar.click();

		cashNetPO.botaoFinalizarVenda.click();
		cashNetPO.botaoConfirmarMensagemFlutuante.click();

		cashNetPO.aguardarGeracaoDaNotaFiscal();
		
		Assert.assertEquals("Venda realizada com sucesso!", cashNetPO.mensagemVendaRealizadaComSucesso.getText());
		Assert.assertEquals("NFC-e autorizada.", cashNetPO.statusVenda.getText());
		
		cashNetPO.fecharVendaFinalizadaComNotaFiscal();
	}

	/** Testa o componente de Pix. */
	@Ignore ("Aguardar um token para os testes")
	@Test
	public void T0012_deve_testar_o_componente_de_pix() {
		cashNetPO.adicionarProdutoAoCarrinho("FAIXA MEIA LAÇO", indexCorOuTamanhoDoProduto);

		String valorTotalCarrinho = cashNetPO.obterValorResumoVenda("A pagar");

		cashNetPO.botaoPix.click();
		pixPO.botaoPix.click();

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertEquals("Dados para o PIX", pixPO.tituloModal.getText());
		Assert.assertEquals(valorTotalCarrinho, pixPO.inputValorDoPix.getAttribute("value").replace(",", "."));
		
		pixPO.botaoConfirmar.click();

		Assert.assertEquals("Dados para o PIX", pixPO.tituloModalQRCode.getText());

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 15, cashNetPO.conteudoMensagemFlutuante);
		
		Assert.assertEquals("Aguardando pagamento do PIX\nPix", pixPO.obterTextoMensagemFlutuante(driver, 20));

		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 15, cashNetPO.conteudoMensagemFlutuante);
		
		pixPO.botaoVerificarPix.click();
		
		Assert.assertEquals("Aguardando a confirmação do PIX\nPix", pixPO.obterTextoMensagemFlutuante(driver, 5));
	
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 15, cashNetPO.conteudoMensagemFlutuante);

		pixPO.botaoFecharModalQRCode.click();
		pixPO.fecharModais();
	}

	/** Testa o pagamento em dinheiro, cartão de crédito, débito e pix. 
	 * @throws InterruptedException */
	@Ignore ("Aguardar um token para os testes")
	@Test
	public void T0013_deve_testar_o_pagamento_em_dinheiro_credito_debito_e_componente_de_pix() throws InterruptedException {
		cashNetPO.fecharMensagensFlutuantes();

		cashNetPO.botaoCartao.click();
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor("100")
			.comBandeira("125 - CARTÃO TESTE")
			.preencherDadosCartaoPOS(tipoCartao.CREDITO);

		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoCartaoPOS.click();

		cartaoPosBuilder
			.comValor("100")
			.comBandeira("ELO")
			.preencherDadosCartaoPOS(tipoCartao.DEBITO);

		cartaoPO.botaoConfirmarMensagemFlutuante.click();
		Thread.sleep(2000);
		cartaoPO.botaoFechar.click();

		dinheiroPO.realizarPagamentoEmDinheiro("100");
	
		String valorTotalCarrinho = cashNetPO.obterValorResumoVenda("A pagar");

		cashNetPO.botaoPix.click();
		pixPO.botaoPix.click();

		Assert.assertEquals(valorTotalCarrinho, pixPO.inputValorDoPix.getAttribute("value").replace(",", "."));
		
		pixPO.botaoConfirmar.click();

		Assert.assertEquals("Dados para o PIX", pixPO.tituloModalQRCode.getText());
		
		pixPO.botaoVerificarPix.click();
		
		Assert.assertEquals("Aguardando a confirmação do PIX\nPix", pixPO.obterTextoMensagemFlutuante(driver, 5));
		pixPO.aguardarInvisibilidadeDoElemento(driver, 5, pixPO.conteudoMensagemFlutuante);

		pixPO.botaoFecharModalQRCode.click();
		pixPO.fecharModais();
	}

	/** Verifica saldo CashBack do cliente ao abrir nova aba de vendas. */
	@Ignore ("IN-10198")
	@Test
	public void T0014_verifica_saldo_zerado_cashback_em_nova_aba_de_vendas() {
		T0001_deve_realizar_pagamento_em_dinheiro();

		deve_selecionar_cliente_e_incluir_produto();
		
		cashNetPO.fecharMensagensFlutuantes();

		Assert.assertEquals("(Saldo 3.00) 0.00", cashNetPO.obterValorResumoVenda("Cashback"));		

		cashNetPO.adicionarNovaAbaDeVenda();
		cashNetPO.adicionarNovaAbaDeVenda();

		cashNetPO.selecionarAbaDeVenda(1);

		Assert.assertEquals("0.00", cashNetPO.obterValorResumoVenda("Cashback"));		

		cashNetPO.selecionarAbaDeVenda(2);

		Assert.assertEquals("0.00", cashNetPO.obterValorResumoVenda("Cashback"));		
	}

	/** Verificar a troca de saldo de Cashback ao trocar de cliente. */
	@Test
	public void T0015_verifica_troca_de_saldo_cashback_ao_trocar_cliente() {
		T0001_deve_realizar_pagamento_em_dinheiro();

		deve_selecionar_cliente_e_incluir_produto();
		
		cashNetPO.fecharMensagensFlutuantes();

		Assert.assertEquals("(Saldo 3.00) 0.00", cashNetPO.obterValorResumoVenda("Cashback"));		

		cashNetPO.botaoExpandirCliente.click();
		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "TESTE AUTOMATIZADO");

		cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertEquals("TESTE AUTOMATIZADO KARINA", cashNetPO.inputClienteSelecionado.getAttribute("value"));
		Assert.assertEquals("0.00", cashNetPO.obterValorResumoVenda("Cashback"));

		cashNetPO.botaoAdicionarCliente.click();
		cashNetPO.selecionarNomeAutocomplete(cashNetPO.inputCliente, "Carla Dias");
		
		cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
		cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);

		Assert.assertEquals("CARLA DIAS", cashNetPO.inputClienteSelecionado.getAttribute("value"));
		Assert.assertEquals("(Saldo 3.00) 0.00", cashNetPO.obterValorResumoVenda("Cashback"));		
	}		
	//#endregion
}
