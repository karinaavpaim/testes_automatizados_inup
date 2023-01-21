package test.component;

import org.junit.Assert;
import org.junit.Test;

import page.AcompanhamentoDeMetasPO;
import page.ConsultaDeVendasPO;
import page.GestorDePedidosPO;
import test.BaseTest;

/** Classe para testar o componente de filiais. */
public class Lojas extends BaseTest{
    
    // #region Regiao dos atributos
    private static GestorDePedidosPO gestorDePedidosPO;
    private static ConsultaDeVendasPO consultaDeVendasPO;
    private static AcompanhamentoDeMetasPO acompanhamentoDeMetasPO;
    // #endregion
 
    //#region Regiao dos testes
 
    /** Teste para selecionar as filiais e depois retirar a seleção. */
    @Test
    public void T0001_deve_testar_componente_filiais_em_gestor_de_pedidos() {
        gestorDePedidosPO = new GestorDePedidosPO(driver);
       
        gestorDePedidosPO.navegarParaGestorDePedidos();

        gestorDePedidosPO.aguardarVisibilidadeDoElemento(driver, 5, gestorDePedidosPO.inputLoja);

        gestorDePedidosPO.selecionarFiliais("NORTE SHOPPING");
        Assert.assertEquals("NORTE SHOPPING", gestorDePedidosPO.obterValorInputLoja());

        gestorDePedidosPO.retirarSelecaoFiliais("NORTE SHOPPING");

        gestorDePedidosPO.selecionarFiliais("DEFEITO");
        Assert.assertEquals("DEFEITO", gestorDePedidosPO.obterValorInputLoja());

        gestorDePedidosPO.retirarSelecaoFiliais("DEFEITO");

        gestorDePedidosPO.selecionarFiliais("FASHION MALL");
        Assert.assertEquals("FASHION MALL", gestorDePedidosPO.obterValorInputLoja());

        gestorDePedidosPO.selecionarFiliais("DEFEITO");
        Assert.assertEquals("FASHION MALLDEFEITO", gestorDePedidosPO.obterValorInputLoja());

        gestorDePedidosPO.retirarSelecaoFiliais("DEFEITO");
        gestorDePedidosPO.retirarSelecaoFiliais("FASHION MALL");

        gestorDePedidosPO.limparCampoInput(gestorDePedidosPO.inputLoja);

        Assert.assertEquals("", gestorDePedidosPO.inputLoja.getAttribute("value"));
    }

    /** Teste para selecionar as filiais e depois retirar a seleção. */
    @Test
    public void T0002_deve_testar_componente_filiais_em_consulta_de_vendas() {
        consultaDeVendasPO = new ConsultaDeVendasPO(driver);
       
        consultaDeVendasPO.navegarParaConsultaDeVendas();

        consultaDeVendasPO.aguardarVisibilidadeDoElemento(driver, 5, consultaDeVendasPO.inputLoja);

        consultaDeVendasPO.selecionarFiliais("NORTE SHOPPING");
        Assert.assertEquals("NORTE SHOPPING", consultaDeVendasPO.obterValorInputLoja());

        consultaDeVendasPO.retirarSelecaoFiliais("NORTE SHOPPING");

        consultaDeVendasPO.selecionarFiliais("DEFEITO");
        Assert.assertEquals("DEFEITO", consultaDeVendasPO.obterValorInputLoja());

        consultaDeVendasPO.retirarSelecaoFiliais("DEFEITO");

        consultaDeVendasPO.selecionarFiliais("FASHION MALL");
        Assert.assertEquals("FASHION MALL", consultaDeVendasPO.obterValorInputLoja());

        consultaDeVendasPO.selecionarFiliais("DEFEITO");
        Assert.assertEquals("FASHION MALLDEFEITO", consultaDeVendasPO.obterValorInputLoja());

        consultaDeVendasPO.retirarSelecaoFiliais("DEFEITO");
        consultaDeVendasPO.retirarSelecaoFiliais("FASHION MALL");

        consultaDeVendasPO.limparCampoInput(consultaDeVendasPO.inputLoja);

        Assert.assertEquals("", consultaDeVendasPO.inputLoja.getAttribute("value"));
    }

    /** Teste para selecionar as filiais e depois retirar a seleção. */
    @Test
    public void T0003_deve_testar_componente_filiais_em_acompanhamento_de_metas() {
        acompanhamentoDeMetasPO = new AcompanhamentoDeMetasPO(driver);
       
        acompanhamentoDeMetasPO.navegarParaAcompanhamentoDeMetas();

        acompanhamentoDeMetasPO.aguardarVisibilidadeDoElemento(driver, 5, acompanhamentoDeMetasPO.inputLoja);

        acompanhamentoDeMetasPO.selecionarFiliais("NORTE SHOPPING");
        Assert.assertEquals("NORTE SHOPPING", acompanhamentoDeMetasPO.obterValorInputLoja());

        acompanhamentoDeMetasPO.retirarSelecaoFiliais("NORTE SHOPPING");

        acompanhamentoDeMetasPO.selecionarFiliais("DEFEITO");
        Assert.assertEquals("DEFEITO", acompanhamentoDeMetasPO.obterValorInputLoja());

        acompanhamentoDeMetasPO.retirarSelecaoFiliais("DEFEITO");

        acompanhamentoDeMetasPO.selecionarFiliais("FASHION MALL");
        Assert.assertEquals("FASHION MALL", acompanhamentoDeMetasPO.obterValorInputLoja());

        acompanhamentoDeMetasPO.selecionarFiliais("DEFEITO");
        Assert.assertEquals("FASHION MALLDEFEITO", acompanhamentoDeMetasPO.obterValorInputLoja());

        acompanhamentoDeMetasPO.retirarSelecaoFiliais("DEFEITO");
        acompanhamentoDeMetasPO.retirarSelecaoFiliais("FASHION MALL");

        acompanhamentoDeMetasPO.limparCampoInput(acompanhamentoDeMetasPO.inputLoja);

        Assert.assertEquals("", acompanhamentoDeMetasPO.inputLoja.getAttribute("value"));
    }
    //#endregion
}
