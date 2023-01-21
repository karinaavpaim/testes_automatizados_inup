package test.netShopper;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import page.cashNet.RestaurarVendaOuPedidoPO;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe para testar o componente de restaurar vendas na tela do CashNet. */
public class RestaurarPedidoTest extends BaseTest {

    // #region Regiao dos atributos
    private static NetShopperPO netShopperPO;
    private static RestaurarVendaOuPedidoPO restaurarVendaPO;
    private static Select selectVendedores;
    private static Select selectLojas;
    private static String frete = "CORREIOS";
    // #endregion

    /** Metodo para inciar os testes. */
    @BeforeClass
    public static void iniciarTestes() {
        netShopperPO = new NetShopperPO(driver);
        restaurarVendaPO = new RestaurarVendaOuPedidoPO(driver);

        netShopperPO.navegarParaNetshopper();
    }

    /** Metodo a ser realizado antes de cada teste. */
    @Before
    public void deve_atualizar_pagina() {
        netShopperPO.atualizarPagina(5);

        netShopperPO.botaoRestaurarPedido.click();
    }

    /** Metodo a ser realizado depois desta classe de testes. */
    @AfterClass
    public static void deve_atualizar_pagina_ao_finalizar() {
        netShopperPO.atualizarPagina(5);
    }

    /**
     * Testa a restauração de pedido para a loja defeito, sem cliente, com um produto.
     */
    @Test
    public void T0001_testa_restaurar_pedido_para_loja_defeito_sem_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000036");

        netShopperPO.aguardarVisibilidadeDoElemento(driver, 5, netShopperPO.botaoExpandirCliente);
        netShopperPO.botaoExpandirCliente.click();

        selectVendedores = new Select(netShopperPO.selectVendedor);
        selectLojas = new Select(netShopperPO.selectLoja);

        Assert.assertEquals(
            "Produto adicionado\nCARTEIRA KARINA",
            netShopperPO.obterTextoMensagemFlutuante(driver, 5));

        Assert.assertTrue(netShopperPO.inputCliente.getAttribute("value").isEmpty());

        Assert.assertEquals(
                "CARTEIRA KARINA",
                netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "Gabriel Barreto Baya",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "DEFEITO",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "1",
                netShopperPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "55.00",
                netShopperPO.obterValorResumoVenda("A pagar"));

        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
    }

    /**
     * Testa a restauração de pedido para a loja defeito, com cliente, com um produto.
     */
    @Test
    public void T0002_testa_restaurar_pedido_para_loja_defeito_com_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000037");

        Assert.assertEquals(
            "Produto adicionado\nCARTEIRA KARINA",
            netShopperPO.obterTextoMensagemFlutuante(driver, 5));

        netShopperPO.selecionarFrete(frete);

        netShopperPO.aguardarVisibilidadeDoElemento(driver, 5, netShopperPO.botaoExpandirCliente);
        netShopperPO.botaoExpandirCliente.click();

        selectVendedores = new Select(netShopperPO.selectVendedor);
        selectLojas = new Select(netShopperPO.selectLoja);

        Assert.assertEquals(
                "LUCAS PADILHA",
                netShopperPO.inputClienteSelecionado.getAttribute("value"));

        Assert.assertEquals(
                "CARTEIRA KARINA",
                netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "Gabriel Barreto Baya",
                selectVendedores.getFirstSelectedOption().getAccessibleName());
        
        Assert.assertEquals(
                "DEFEITO",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "1",
                netShopperPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "55.00",
                netShopperPO.obterValorResumoVenda("A pagar"));

        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
    }

    /**
    * Testa a restauração de pedido para a loja defeito, com cliente, com mais de um produto.
     */
    @Ignore ("IN-10282")
    @Test
    public void T0003_testa_restaurar_pedido_para_loja_defeito_com_cliente_com_mais_de_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000038");

        netShopperPO.selecionarFrete(frete);

        netShopperPO.aguardarVisibilidadeDoElemento(driver, 10, netShopperPO.conteudoMensagemFlutuante);
        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 10, netShopperPO.conteudoMensagemFlutuante);

        netShopperPO.botaoExpandirCliente.click();

        selectVendedores = new Select(netShopperPO.selectVendedor);
        selectLojas = new Select(netShopperPO.selectLoja);

        Assert.assertEquals(
                "LUCAS PADILHA",
                netShopperPO.inputClienteSelecionado.getAttribute("value"));

        Assert.assertEquals(
                "Gabriel Barreto Baya",
                selectVendedores.getFirstSelectedOption().getAccessibleName());
                
        Assert.assertEquals(
                "DEFEITO",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "3",
                netShopperPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "980.90",
                netShopperPO.obterValorResumoVenda("A pagar"));

        List<WebElement> listaCarrinho = driver.findElements(By.xpath("//div/div[2]/div/div/div/div[1]/span"));
        System.out.println(listaCarrinho.size());

        System.out.println(listaCarrinho.get(1).getText());
        System.out.println(listaCarrinho.get(2).getText());
        System.out.println(listaCarrinho.get(3).getText());

        for (WebElement item : listaCarrinho) {
                if(item.getText().equals("CARTEIRA KARINA")) {
                        item.click();
                }       
        }

        for (WebElement item : listaCarrinho) {
                if(item.getText().equals("SANDALIA RASTEIRA IPANEMA")) {
                        item.click();
                }   
        }

        for (WebElement item : listaCarrinho) {
                if(item.getText().equals("COLCHÃO ORTOPÉDICO NASA")) {
                        item.click();
                }   
        }

        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
    }

    /**
    * Testa a restauração de pedido para a loja norte shopping, sem cliente, com um produto.
     */
    @Test
    public void T0004_testa_restaurar_pedido_para_loja_norte_shopping_sem_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000041");

        netShopperPO.aguardarVisibilidadeDoElemento(driver, 5, netShopperPO.botaoExpandirCliente);
        netShopperPO.botaoExpandirCliente.click();

        selectVendedores = new Select(netShopperPO.selectVendedor);
        selectLojas = new Select(netShopperPO.selectLoja);

        Assert.assertEquals(
                "CARTEIRA KARINA",
                netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "Marysol",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "NORTE SHOPPING",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "Produto adicionado\nCARTEIRA KARINA",
                netShopperPO.obterTextoMensagemFlutuante(driver, 5));

        Assert.assertEquals(
                "1",
                netShopperPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "55.00",
                netShopperPO.obterValorResumoVenda("A pagar"));

        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
    }

    /**
    * Testa a restauração de pedido para a loja norte shopping, com cliente, com um produto.
     */
    @Test
    public void T0005_testa_restaurar_pedido_para_loja_norte_shopping_com_cliente_com_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000042");

        Assert.assertEquals(
            "Produto adicionado\nCARTEIRA KARINA",
            netShopperPO.obterTextoMensagemFlutuante(driver, 5));

        netShopperPO.selecionarFrete(frete);
        
        netShopperPO.aguardarVisibilidadeDoElemento(driver, 5, netShopperPO.botaoExpandirCliente);
        netShopperPO.botaoExpandirCliente.click();

        selectVendedores = new Select(netShopperPO.selectVendedor);
        selectLojas = new Select(netShopperPO.selectLoja);

        Assert.assertEquals(
                "LUCAS PADILHA",
                netShopperPO.inputClienteSelecionado.getAttribute("value"));

        Assert.assertEquals(
                "CARTEIRA KARINA",
                netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "Marysol",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "NORTE SHOPPING",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "1",
                netShopperPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "55.00",
                netShopperPO.obterValorResumoVenda("A pagar"));

        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 5, netShopperPO.conteudoMensagemFlutuante);
    }

    /**
    * Testa a restauração de pedido para a loja norte shopping, com cliente, com mais de um produto.
     */
    @Ignore ("IN-10282")
    @Test
    public void T0006_testa_restaurar_pedido_para_loja_norte_shopping_com_cliente_com_mais_de_um_produto() {
        restaurarVendaPO.restaurarPreVendaPeloCodigo("0000000043");
   
        Assert.assertEquals(
                "CARTEIRA KARINA",
                netShopperPO.primeiroProdutoAdicionadoAoCarrinho.getText());

        Assert.assertEquals(
                "CARTEIRA KARINA",
                driver.findElement(By.cssSelector(".card:nth-child(3) .h4")).getText());
    
        Assert.assertEquals(
                "Produto adicionado\nCARTEIRA KARINA\nProduto adicionado\nCARTEIRA KARINA",
                netShopperPO.obterTextoMensagemFlutuante(driver, 10));
        
        netShopperPO.aguardarInvisibilidadeDoElemento(driver, 10, netShopperPO.conteudoMensagemFlutuante);

        driver.findElement(By.xpath("(//button[@type='button'])[25]")).click();
        driver.findElement(By.xpath("(//button[@type='button'])[22]")).click();

     //   netShopperPO.selecionarFrete(frete);

        netShopperPO.aguardarVisibilidadeDoElemento(driver, 5, netShopperPO.botaoExpandirCliente);
        netShopperPO.botaoExpandirCliente.click();

        selectVendedores = new Select(netShopperPO.selectVendedor);
        selectLojas = new Select(netShopperPO.selectLoja);

        Assert.assertEquals(
                "LUCAS PADILHA",
                netShopperPO.inputClienteSelecionado.getAttribute("value"));

        Assert.assertEquals(
                "Marysol",
                selectVendedores.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "NORTE SHOPPING",
                selectLojas.getFirstSelectedOption().getAccessibleName());

        Assert.assertEquals(
                "2",
                netShopperPO.obterValorResumoVenda("Carrinho"));

        Assert.assertEquals(
                "110.00",
                netShopperPO.obterValorResumoVenda("A pagar"));
    }

    /**
     * Verifica a quantidade de pre vendas cadastradas para uma loja
     */
    @Test
    public void T0007_verifica_quantidade_de_pedido_suspenso_para_cada_loja() {
        Assert.assertEquals(9, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.botaoFechar.click();

        netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , "DEFEITO");
        
        restaurarVendaPO.aguardarVisibilidadeDoElemento(driver, 5, netShopperPO.textoMensagemModal);

        Assert.assertEquals(
                "Filial selecionada não possui configuração de Link Cartão",
                netShopperPO.textoMensagemModal.getText());

        netShopperPO.botaoOK.click();
        
        netShopperPO.botaoRestaurarPedido.click();

        Assert.assertEquals(4, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.botaoFechar.click();

        netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , "FASHION MALL");
        netShopperPO.botaoRestaurarPedido.click();

        Assert.assertEquals(2, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.fecharModais();
        netShopperPO.fecharModalAvisoLinkCartao();

        netShopperPO.selecionarElementoSelect(netShopperPO.selectLoja , "NORTE SHOPPING");
        netShopperPO.botaoRestaurarPedido.click();

        Assert.assertEquals(3, restaurarVendaPO.retornarQuantidadeDePreVendas());

        restaurarVendaPO.fecharModais();
    }
}
