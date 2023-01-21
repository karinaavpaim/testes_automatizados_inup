package test.gestorDePedidos;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.GestorDePedidosPO;
import test.BaseTest;

/** Classe para testar a consulta de pedidos por filtros. */
public class ConsultaPorFiltrosTest extends BaseTest{
    
     // #region Regiao dos atributos
     private static GestorDePedidosPO gestorDePedidosPO;
     // #endregion

    /** Metodo para inciar os testes desta classe. */
    @BeforeClass
    public static void iniciarTestes() {
        gestorDePedidosPO = new GestorDePedidosPO(driver);

        gestorDePedidosPO.navegarParaGestorDePedidos();
    }

    // #region Regiao dos testes.

    /** Teste para realizar uma busca por filiais. 
     * @throws InterruptedException */
    @Test
    public void T0001_deve_pesquisar_por_filiais() throws InterruptedException {
        gestorDePedidosPO.atualizarPagina(10);

        Thread.sleep(2000);
        gestorDePedidosPO.botaoProntosParaExpedir.click();
        
        gestorDePedidosPO.selecionarFiliais("NORTE SHOPPING");
        gestorDePedidosPO.pesquisarPedidos();
     
        Assert.assertEquals("NORTE SHOPPING", gestorDePedidosPO.primeiraFilialDaListagem.getText());

        gestorDePedidosPO.retirarSelecaoFiliais("NORTE SHOPPING");

        gestorDePedidosPO.selecionarFiliais("ESCRITORIO");
        gestorDePedidosPO.pesquisarPedidos();

        Assert.assertEquals("ESCRITORIO", gestorDePedidosPO.primeiraFilialDaListagem.getText());

        gestorDePedidosPO.retirarSelecaoFiliais("ESCRITORIO");

        gestorDePedidosPO.selecionarFiliais("DEFEITO");
        gestorDePedidosPO.pesquisarPedidos();

        Assert.assertEquals("DEFEITO", gestorDePedidosPO.primeiraFilialDaListagem.getText());

        gestorDePedidosPO.atualizarPagina(10);
    }

    /** Teste para realizar uma busca por nome do cliente. 
     * @throws InterruptedException */
    @Test
    public void T0002_deve_pesquisar_por_nome_cliente() throws InterruptedException {
        gestorDePedidosPO.atualizarPagina(10);

        Thread.sleep(2000);
        
        gestorDePedidosPO.localizarPedido("TESTE QLD");
    
        Thread.sleep(2000);

        Assert.assertEquals("TESTE QLD", gestorDePedidosPO.primeiroClienteDaListagem.getText());

        gestorDePedidosPO.localizarPedido("KARINA PAIM");

        Thread.sleep(2000);

        Assert.assertEquals("KARINA PAIM", gestorDePedidosPO.primeiroClienteDaListagem.getText());

        gestorDePedidosPO.localizarPedido("LUCAS PADILHA");

        Thread.sleep(2000);

        Assert.assertEquals("LUCAS PADILHA", gestorDePedidosPO.primeiroClienteDaListagem.getText());

        gestorDePedidosPO.limparCampoInput(gestorDePedidosPO.inputProcurar);
    }

    /** Teste para realizar uma busca por nome da transportadora. 
     * @throws InterruptedException */
    @Test
    public void T0003_deve_pesquisar_por_transportadora() throws InterruptedException {
        gestorDePedidosPO.atualizarPagina(5);
        
        Thread.sleep(2000);

        gestorDePedidosPO.localizarPedido("CORREIOS");
        gestorDePedidosPO.aguardarTextoNoElemento(gestorDePedidosPO.primeiraTransportadoraDaListagem, "CORREIOS");
        
        Assert.assertEquals("CORREIOS", gestorDePedidosPO.primeiraTransportadoraDaListagem.getText());

        gestorDePedidosPO.localizarPedido("PANDA LOGGI");
        gestorDePedidosPO.aguardarTextoNoElemento(gestorDePedidosPO.primeiraTransportadoraDaListagem, "PANDA LOGGI");

        Assert.assertEquals("PANDA LOGGI", gestorDePedidosPO.primeiraTransportadoraDaListagem.getText());

        gestorDePedidosPO.localizarPedido("JUDITE EXPRESS");
        gestorDePedidosPO.aguardarTextoNoElemento(gestorDePedidosPO.primeiraTransportadoraDaListagem, "JUDITE EXPRESS");

        Assert.assertEquals("JUDITE EXPRESS", gestorDePedidosPO.primeiraTransportadoraDaListagem.getText());

        gestorDePedidosPO.limparCampoInput(gestorDePedidosPO.inputProcurar);
    }

    /** Teste para pesquisar resultados para mais de uma loja e verificar a busca na listagem.". 
     * @throws InterruptedException */
    @Test
    public void T0004_deve_pesquisar_duas_lojas_e_verificar_na_listagem() throws InterruptedException {
        gestorDePedidosPO.atualizarPagina(10);

        Thread.sleep(2000);
        gestorDePedidosPO.botaoProntosParaExpedir.click();

        gestorDePedidosPO.selecionarFiliais("DEFEITO");
        gestorDePedidosPO.selecionarFiliais("ESCRITORIO");

        gestorDePedidosPO.pesquisarPedidos();

        Assert.assertTrue(gestorDePedidosPO.verificarSeNomeEstaNaTabela("ESCRITORIO"));
            
        gestorDePedidosPO.selecionarPaginacao(4);
        gestorDePedidosPO.aguardarVisibilidadeDoElemento(driver, 5, gestorDePedidosPO.tabela);

        Assert.assertTrue(gestorDePedidosPO.verificarSeNomeEstaNaTabela("DEFEITO"));
    }
    //#endregion
}
