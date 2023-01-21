package test.gestorDePedidos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import page.GestorDePedidosPO;
import test.BaseTest;

/** Classe para testar a tela de Gestor de Pedidos. */
public class GestorDePedidosTest extends BaseTest {

    // #region Regiao dos atributos
    private static GestorDePedidosPO gestorDePedidosPO;
    private static String nomeCliente = "WOLVERINE";
    private static String nomeProduto = "COLCHÃO";
    private static String frete = "CORREIOS";
    private static int indexCorOuTamanho = 1;
    private static String codigoVenda;
    // #endregion

    /** Metodo para inciar os testes desta classe. */
    @BeforeClass
    public static void iniciarTestes() {
        gestorDePedidosPO = new GestorDePedidosPO(driver);

        gestorDePedidosPO.navegarParaGestorDePedidos();
    }

    /** Teste para realizar uma venda em NetShopper e passar para as etapas "Aguardando Pagamento", 
     * "Prontos para Expedir" e "Aguardando Notas". 
     * @throws InterruptedException */
    @Test
	public void T0001_deve_realizar_venda_em_netshopper_e_passar_por_todas_as_etapas_em_gestor_de_pedidos() throws InterruptedException {
        codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
        
        gestorDePedidosPO.atualizarPagina(5);
        gestorDePedidosPO.navegarParaGestorDePedidos();
      
        gestorDePedidosPO.localizarPedido(codigoVenda);

        Thread.sleep(2000);

        Assert.assertEquals(codigoVenda, gestorDePedidosPO.primeirCodigoDeVendaDaListagem.getText());

        gestorDePedidosPO.checkbox.click();
        
        gestorDePedidosPO.botaoAprovarPagamento.click();

        Assert.assertEquals("Aprovação de Pagamento\nPagamentos aprovados com sucesso!", gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 5));
        gestorDePedidosPO.conteudoMensagemFlutuante.click();
        
        gestorDePedidosPO.botaoProntosParaExpedir.click();

        gestorDePedidosPO.localizarPedido(codigoVenda);
        
        Thread.sleep(2000);

        Assert.assertEquals(codigoVenda, gestorDePedidosPO.primeirCodigoDeVendaDaListagem.getText());

        gestorDePedidosPO.checkbox.click();
            
        Assert.assertEquals(nomeCliente, gestorDePedidosPO.primeiroClienteDaListagem.getText());

        gestorDePedidosPO.botaoGerarSeparacao.click();

        String numeroSeparacao = gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 5).replace("Separações Geradas\n", "");
        Assert.assertEquals("Separações Geradas\n" + numeroSeparacao, gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 5));

        gestorDePedidosPO.botaoAguardandoNotas.click();

        gestorDePedidosPO.localizarPedido(codigoVenda);

        Thread.sleep(2000);

        Assert.assertEquals(nomeCliente, gestorDePedidosPO.primeiroClienteDaListagemAguardandoNotas.getText());
    }

    /** Teste para realizar uma venda e excluí-la em "Aguardando Pagamentos". 
     * @throws InterruptedException */
    @Test
	public void T0002_deve_excluir_venda_aguardando_pagamentos() throws InterruptedException {
        codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
        
        gestorDePedidosPO.atualizarPagina(5);
        gestorDePedidosPO.navegarParaGestorDePedidos();

        gestorDePedidosPO.localizarPedido(codigoVenda);

        Thread.sleep(3000);
        
        Assert.assertEquals(codigoVenda, gestorDePedidosPO.primeirCodigoDeVendaDaListagem.getText());

        gestorDePedidosPO.checkbox.click();
        gestorDePedidosPO.botaoCancelarVenda.click();
        gestorDePedidosPO.botaoConfirmarMensagemFlutuante.click();

        String numeroBoleta = gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 2).replace("Cancelamento\nCancelamento do boleto ", "").replace(" efetuado com sucesso!", "");

        Assert.assertEquals("Cancelamento\nCancelamento do boleto " + numeroBoleta + " efetuado com sucesso!", gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 2));
    
        gestorDePedidosPO.conteudoMensagemFlutuante.click();
    }

    /** Teste para realizar uma venda e excluí-la em "Prontos para Expedir". 
     * @throws InterruptedException */
    @Test
	public void T0003_deve_excluir_venda_prontos_para_expedir() throws InterruptedException {
        codigoVenda = gestorDePedidosPO.obterCodigoDeVenda(nomeCliente, nomeProduto, indexCorOuTamanho, frete);
       
        gestorDePedidosPO.atualizarPagina(5);
        gestorDePedidosPO.navegarParaGestorDePedidos();
        
        gestorDePedidosPO.localizarPedido(codigoVenda);  

        Thread.sleep(3000);

        Assert.assertEquals(codigoVenda, gestorDePedidosPO.primeirCodigoDeVendaDaListagem.getText());

        gestorDePedidosPO.checkbox.click();
        gestorDePedidosPO.botaoAprovarPagamento.click();

        Assert.assertEquals("Aprovação de Pagamento\nPagamentos aprovados com sucesso!", gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 5));
        gestorDePedidosPO.conteudoMensagemFlutuante.click();
        
        gestorDePedidosPO.botaoProntosParaExpedir.click();

        gestorDePedidosPO.localizarPedido(codigoVenda);
     
        Thread.sleep(3000);

        Assert.assertEquals(nomeCliente, gestorDePedidosPO.primeiroClienteDaListagem.getText());
        
        gestorDePedidosPO.checkbox.click();
        gestorDePedidosPO.botaoCancelarVenda.click();
        gestorDePedidosPO.botaoConfirmarMensagemFlutuante.click();

        String numeroBoleta = gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 2).replace("Cancelamento\nCancelamento do boleto ", "").replace(" efetuado com sucesso!", "");

        Assert.assertEquals("Cancelamento\nCancelamento do boleto " + numeroBoleta + " efetuado com sucesso!", gestorDePedidosPO.obterTextoMensagemFlutuante(driver, 2));
    
        gestorDePedidosPO.conteudoMensagemFlutuante.click();
    }

    /** Teste para localizar uma venda em "Aguardando Notas" e alterar a transportadora. */
    @Ignore ("Aguardando IN-8983")
    @Test
	public void T0004_deve_alterar_transportadora_em_aguardando_notas() {
        gestorDePedidosPO.atualizarPagina(7);

        gestorDePedidosPO.botaoAguardandoNotas.click();
		gestorDePedidosPO.aguardarCarregamentoDaPagina();

        gestorDePedidosPO.localizarPedido("1064000006");
        gestorDePedidosPO.localizarPedidoESelecionar(codigoVenda);

        gestorDePedidosPO.alterarTransportadora("JUDITE EXPRESS LTDA");

        Assert.assertEquals("JUDITE EXPRESS", gestorDePedidosPO.primeiraTransportadoraDaListagem);
    }
    //#endregion
}
