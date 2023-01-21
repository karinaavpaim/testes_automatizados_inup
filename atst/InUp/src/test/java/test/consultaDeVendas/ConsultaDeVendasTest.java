package test.consultaDeVendas;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.ConsultaDeVendasPO;
import page.GestorDePedidosPO;
import test.BaseTest;

/** Classe de testes para a pagina de Consulta de Vendas */
public class ConsultaDeVendasTest extends BaseTest {

	//#region Regiao dos atributos
	private static ConsultaDeVendasPO consultaDeVendasPO;
	private static GestorDePedidosPO gestorDePedidosPO;
	private static String nomeCliente = "WOLVERINE";
	private static String nomeProduto = "CARTEIRA KARINA";
	private static int indexCorOuTamanho = 0;
	private static String frete = "JUDITE";
	private static String codigoVenda;
	//#endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes(){
		consultaDeVendasPO = new ConsultaDeVendasPO(driver);
		gestorDePedidosPO = new GestorDePedidosPO(driver);
		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
	}

	//#region Regiao dos testes.

	/** Deve cancelar uma venda com status "pendente". */
	@Test
	public void T0001_deve_cancelar_uma_venda_com_status_pendente() {
		codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
                
        consultaDeVendasPO.botaoFechar.click();
       		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
		consultaDeVendasPO.atualizarPagina(7);
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);
		
		Assert.assertEquals(codigoVenda, consultaDeVendasPO.numeroBoletaListagem.getText());
		Assert.assertEquals("Pendente", consultaDeVendasPO.statusListagem.getText());

		consultaDeVendasPO.botaoCancelarVenda.click();

		Assert.assertEquals("Você deseja realmente cancelar a venda boleta número " + codigoVenda +"?", consultaDeVendasPO.mensagemConfirmacao.getText());

		consultaDeVendasPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertEquals("Cancelamento\nCancelamento efetuado com sucesso!", consultaDeVendasPO.obterTextoMensagemFlutuante(driver, 5));

		consultaDeVendasPO.conteudoMensagemFlutuante.click();

		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);
		consultaDeVendasPO.aguardarInvisibilidadeDoElemento(driver, 10, consultaDeVendasPO.carregamentoDaPagina);

		Assert.assertFalse(consultaDeVendasPO.verificarSeElementoEstaNaTela(".text-left:nth-child(2)"));
	}

	/** Deve cancelar uma venda com status "aprovado". 
	 * @throws InterruptedException */
	@Test
	public void T0002_deve_cancelar_uma_venda_com_status_aprovado() throws InterruptedException {
		codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
                
        consultaDeVendasPO.botaoFechar.click();
       		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
		gestorDePedidosPO.atualizarPagina(7);
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);
		
		Assert.assertEquals(codigoVenda, consultaDeVendasPO.numeroBoletaListagem.getText());
		Assert.assertEquals("Pendente", consultaDeVendasPO.statusListagem.getText());

		gestorDePedidosPO.navegarParaGestorDePedidos();
		gestorDePedidosPO.atualizarPagina(7);
		
		gestorDePedidosPO.aguardarElementoSerClicavel(driver, 10, gestorDePedidosPO.inputProcurar);
        gestorDePedidosPO.localizarPedido(codigoVenda);

		Thread.sleep(2000);

        gestorDePedidosPO.checkbox.click();
        gestorDePedidosPO.botaoAprovarPagamento.click();

        Assert.assertEquals("Aprovação de Pagamento\nPagamentos aprovados com sucesso!", gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 5));
        gestorDePedidosPO.aguardarInvisibilidadeDoElemento(driver, 5, gestorDePedidosPO.conteudoMensagemFlutuante);
		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
		consultaDeVendasPO.atualizarPagina(7);
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);

		Assert.assertEquals(codigoVenda, consultaDeVendasPO.numeroBoletaListagem.getText());
		Assert.assertEquals("Aprovado", consultaDeVendasPO.statusListagem.getText());		
				
		consultaDeVendasPO.botaoCancelarVenda.click();

		Assert.assertEquals("Você deseja realmente cancelar a venda boleta número " + codigoVenda +"?", consultaDeVendasPO.mensagemConfirmacao.getText());

		consultaDeVendasPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertEquals("Cancelamento\nCancelamento efetuado com sucesso!", consultaDeVendasPO.obterTextoMensagemFlutuante(driver, 5));

		consultaDeVendasPO.conteudoMensagemFlutuante.click();

		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);
		consultaDeVendasPO.aguardarInvisibilidadeDoElemento(driver, 10, consultaDeVendasPO.carregamentoDaPagina);

		Assert.assertFalse(consultaDeVendasPO.verificarSeElementoEstaNaTela(".text-left:nth-child(2)"));
	}

	/** Deve cancelar uma venda com status "aprovado". */
	@Test
	public void T0003_deve_cancelar_uma_venda_e_verificar_na_lista_de_excluidos() {
		codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
                
		consultaDeVendasPO.atualizarPagina(7);
       	consultaDeVendasPO.navegarParaConsultaDeVendas();
		
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);
		
		Assert.assertEquals(codigoVenda, consultaDeVendasPO.numeroBoletaListagem.getText());
			
		consultaDeVendasPO.botaoCancelarVenda.click();

		Assert.assertEquals("Você deseja realmente cancelar a venda boleta número " + codigoVenda +"?", consultaDeVendasPO.mensagemConfirmacao.getText());

		consultaDeVendasPO.botaoConfirmarMensagemFlutuante.click();

		Assert.assertEquals("Cancelamento\nCancelamento efetuado com sucesso!", consultaDeVendasPO.obterTextoMensagemFlutuante(driver, 5));

		consultaDeVendasPO.checkboxVendasExcluidas.click();
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);

		Assert.assertTrue(consultaDeVendasPO.verificarSeNomeEstaNaTabela(codigoVenda));
	}

	/** Deve testar a alteracao o status da venda de "pendente" para "aprovado". 
	 * @throws InterruptedException */
	@Test
	public void T0004_deve_alterar_status_da_venda() throws InterruptedException {
		codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
                
		consultaDeVendasPO.atualizarPagina(7);
       		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);
		
		Assert.assertEquals("Pendente", consultaDeVendasPO.statusListagem.getText());
		
		consultaDeVendasPO.atualizarPagina(7);
		gestorDePedidosPO.navegarParaGestorDePedidos();
		
		gestorDePedidosPO.aguardarElementoSerClicavel(driver, 10, gestorDePedidosPO.inputProcurar);
        gestorDePedidosPO.localizarPedido(codigoVenda);
		
		Thread.sleep(2000);

		gestorDePedidosPO.checkbox.click();
        gestorDePedidosPO.botaoAprovarPagamento.click();
        
        Assert.assertEquals("Aprovação de Pagamento\nPagamentos aprovados com sucesso!", gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 5));
	
		consultaDeVendasPO.atualizarPagina(7);
		consultaDeVendasPO.navegarParaConsultaDeVendas();
		
		consultaDeVendasPO.buscarCodigoVenda(codigoVenda);

		Assert.assertEquals("Aprovado", consultaDeVendasPO.statusListagem.getText());
	}
	//#endregion
}