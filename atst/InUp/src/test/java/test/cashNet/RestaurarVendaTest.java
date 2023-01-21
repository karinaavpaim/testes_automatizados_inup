package test.cashNet;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import page.cashNet.CashNetPO;
import page.cashNet.RestaurarVendaOuPedidoPO;
import test.BaseTest;

/** Classe para testar o componente de restaurar vendas na tela do CashNet. */
public class RestaurarVendaTest extends BaseTest {

    // #region Regiao dos atributos
    private static CashNetPO cashNetPO;
    private static RestaurarVendaOuPedidoPO restaurarVendaPO;
    private static Select selectVendedores;
    private static Select selectLojas;
    // #endregion

    /** Metodo para inciar os testes. */
    @BeforeClass
    public static void iniciarTestes() {
        cashNetPO = new CashNetPO(driver);
        restaurarVendaPO = new RestaurarVendaOuPedidoPO(driver);

        cashNetPO.navegarParaCashNet();
    }

    /** Metodo a ser realizado antes de cada teste. */
    @Before
    public void deve_atualizar_pagina() {
        cashNetPO.atualizarPagina(5);

        cashNetPO.botaoRestaurarVenda.click();
    }

    /** Metodo a ser realizado depois desta classe de testes. */
    @AfterClass
    public static void deve_atualizar_pagina_ao_finalizar() {
        cashNetPO.atualizarPagina(5);
    }

    /**
     * Testa a não restauração de venda sem cliente, quando uma loja não está configurada para
     * nfc-e.
     */
    @Test
    public void T0001_testa_loja_sem_configuracao_de_nfce_sem_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000036");

        restaurarVendaPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.textoMensagemModal);
       
        Assert.assertEquals(
                "Filial selecionada não possui configuração para emissão de NFC-e, por favor realize a configuração através do Retaguarda ou selecione outra filial.",
                cashNetPO.textoMensagemModal.getText());

        cashNetPO.botaoOK.click();

        Assert.assertTrue(cashNetPO.verificarCamposVazios());
    }

    /**
     * Testa a não restauração de venda com cliente, quando uma loja não está configurada para
     * nfc-e.
     */
    @Test
    public void T0002_testa_loja_sem_configuracao_de_nfce_com_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000037");

        Assert.assertEquals(
                "Filial selecionada não possui configuração para emissão de NFC-e, por favor realize a configuração através do Retaguarda ou selecione outra filial.",
                cashNetPO.textoMensagemModal.getText());

        cashNetPO.botaoOK.click();

        Assert.assertTrue(cashNetPO.verificarCamposVazios());
    }

    /**
     * Testa a não restauração de venda com cliente e mais de um produto, quando uma loja não está configurada para
     * nfc-e.
     */
    @Test
    public void T0003_testa_loja_sem_configuracao_de_nfce_com_cliente_com_mais_de_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000038");

        restaurarVendaPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.textoMensagemModal);

        Assert.assertEquals(
                "Filial selecionada não possui configuração para emissão de NFC-e, por favor realize a configuração através do Retaguarda ou selecione outra filial.",
                cashNetPO.textoMensagemModal.getText());

        cashNetPO.botaoOK.click();

        Assert.assertTrue(cashNetPO.verificarCamposVazios());
    }

    /**
     * Testa a restauração de venda quando uma loja está configurada para nfc-e
     * sem cliente e com um produto.
     */
    @Test
    public void T0004_testa_loja_com_configuracao_de_nfce_sem_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000041");

        cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.botaoExpandirCliente);
        cashNetPO.botaoExpandirCliente.click();

        selectVendedores = new Select(cashNetPO.selectVendedor);
        selectLojas = new Select(cashNetPO.selectLoja);

        Assert.assertEquals(
                "CARTEIRA KARINA",
                cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "Marysol",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "NORTE SHOPPING",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "Produto adicionado\nCARTEIRA KARINA",
                cashNetPO.obterTextoMensagemFlutuante(driver, 5));

        Assert.assertEquals(
                "1",
                cashNetPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "55.00",
                cashNetPO.obterValorResumoVenda("A pagar"));

        cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
    }

    /**
     * Testa a restauração de venda quando uma loja está configurada para nfc-e
     * com cliente e com um produto.
     */
    @Test
    public void T0005_testa_loja_com_configuracao_de_nfce_com_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000042");
        
        cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.botaoExpandirCliente);
        cashNetPO.botaoExpandirCliente.click();

        selectVendedores = new Select(cashNetPO.selectVendedor);
        selectLojas = new Select(cashNetPO.selectLoja);

        Assert.assertEquals(
                "LUCAS PADILHA",
                cashNetPO.inputClienteSelecionado.getAttribute("value"));

        Assert.assertEquals(
                "CARTEIRA KARINA",
                cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "Marysol",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "NORTE SHOPPING",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "Produto adicionado\nCARTEIRA KARINA",
                cashNetPO.obterTextoMensagemFlutuante(driver, 5));

        Assert.assertEquals(
                "1",
                cashNetPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "55.00",
                cashNetPO.obterValorResumoVenda("A pagar"));

        cashNetPO.aguardarInvisibilidadeDoElemento(driver, 5, cashNetPO.conteudoMensagemFlutuante);
    }

    /**
     * Testa a restauração de venda quando uma loja está configurada para nfc-e
     * com cliente e mais de um produto.
     */
    @Test
    public void T0006_testa_loja_com_configuracao_de_nfce_com_cliente_com_mais_de_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000043");

        Assert.assertEquals(
                "CARTEIRA KARINA",
                cashNetPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "CARTEIRA KARINA",
                driver.findElement(By.cssSelector(".card:nth-child(3) .h4")).getText());

        Assert.assertEquals(
                "Produto adicionado\nCARTEIRA KARINA\nProduto adicionado\nCARTEIRA KARINA",
                cashNetPO.obterTextoMensagemFlutuante(driver, 10));
     
        cashNetPO.aguardarInvisibilidadeDoElemento(driver, 10, cashNetPO.conteudoMensagemFlutuante);

        cashNetPO.aguardarVisibilidadeDoElemento(driver, 5, cashNetPO.botaoExpandirCliente);
        cashNetPO.botaoExpandirCliente.click();

        selectVendedores = new Select(cashNetPO.selectVendedor);
        selectLojas = new Select(cashNetPO.selectLoja);

        Assert.assertEquals(
                "LUCAS PADILHA",
                cashNetPO.inputClienteSelecionado.getAttribute("value"));

        Assert.assertEquals(
                "Marysol",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "NORTE SHOPPING",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "2",
                cashNetPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "110.00",
                cashNetPO.obterValorResumoVenda("A pagar"));
    }

    /**
     * Verifica a quantidade de pre vendas cadastradas para uma loja
 * @throws InterruptedException
     */
    @Test
    public void T0007_verifica_quantidade_de_venda_suspensa_para_cada_loja() throws InterruptedException {
        Assert.assertEquals(9, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.botaoFechar.click();

        cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , "DEFEITO");
        Thread.sleep(2000);
        cashNetPO.fecharModaisAvisos();

        cashNetPO.botaoRestaurarVenda.click();

        Assert.assertEquals(4, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.botaoFechar.click();

        cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , "FASHION MALL");
        Thread.sleep(2000);
        cashNetPO.fecharModaisAvisos();

        cashNetPO.botaoRestaurarVenda.click();

        Assert.assertEquals(2, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.botaoFechar.click();

        cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja , "NORTE SHOPPING");
        cashNetPO.botaoRestaurarVenda.click();

        Assert.assertEquals(3, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.botaoFechar.click();
    }
}
