package test.component;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.ConsultaDeVendasPO;
import test.BaseTest;

/** Classe para testar os componentes da tela de Consulta de Vendas. */
public class ConsultaDeVendas extends BaseTest{
    
    //#region Regiao dos atributos
	private static ConsultaDeVendasPO consultaDeVendasPO;
	//#endregion

	/** Metodo para inciar os testes desta classe. */
	@BeforeClass
	public static void iniciarTestes(){
		consultaDeVendasPO = new ConsultaDeVendasPO(driver);
		
		consultaDeVendasPO.navegarParaConsultaDeVendas();
	}

    //#region Regiao dos testes.

	/** Teste para selecionar uma data inicial e final. */
	@Test
	public void T0001_deve_testar_campo_periodo_com_data_inicial_e_data_final() {
        consultaDeVendasPO.atualizarPagina(5);

        consultaDeVendasPO.botaoCalendario.click();

        consultaDeVendasPO.selecionarDataInicial("1", "Jan", "2019");
        consultaDeVendasPO.selecionarDataFinal("31", "Dez", "2021");

        Assert.assertEquals("1/1/2019 até 31/12/2021", consultaDeVendasPO.inputPeriodo.getAttribute("value"));

        consultaDeVendasPO.selecionarDataInicial("13", "Abr", "2013");
        consultaDeVendasPO.selecionarDataFinal("30", "Out", "2020");

        Assert.assertEquals("13/4/2013 até 30/10/2020", consultaDeVendasPO.inputPeriodo.getAttribute("value"));
		
		consultaDeVendasPO.atualizarPagina(5);
    }

    /** Teste para selecionar uma data inicial e final. */
	@Test
	public void T0002_deve_testar_campo_periodo_com_data_predefinida() {
        consultaDeVendasPO.atualizarPagina(5);

        consultaDeVendasPO.botaoCalendario.click();
		consultaDeVendasPO.aguardarInvisibilidadeDoElemento(driver, 5, consultaDeVendasPO.carregamentoDaPagina);

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Hoje"); 
        String hoje = consultaDeVendasPO.inputPeriodo.getAttribute("value").replace(" até " + consultaDeVendasPO.obterDataDeHoje(), "");
		
        Assert.assertEquals(consultaDeVendasPO.obterDataDeHoje(), hoje);

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Ontem"); 
        String ontem = consultaDeVendasPO.inputPeriodo.getAttribute("value").replace(" até " + consultaDeVendasPO.obterDataDeOntem(), "");

        Assert.assertEquals(consultaDeVendasPO.obterDataDeOntem(), ontem);

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Últimos 30 dias"); 
        Assert.assertEquals(consultaDeVendasPO.obterPeriodoUltimosTrintaDias() + " até " + consultaDeVendasPO.obterDataDeHoje(), consultaDeVendasPO.inputPeriodo.getAttribute("value"));

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Esta semana"); 
        Assert.assertEquals(consultaDeVendasPO.obterPeriodoEstaSemana(), consultaDeVendasPO.inputPeriodo.getAttribute("value"));

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Este mês"); 
        Assert.assertEquals(consultaDeVendasPO.obterPeriodoEsteMes(), consultaDeVendasPO.inputPeriodo.getAttribute("value"));

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Este ano"); 
        Assert.assertEquals(consultaDeVendasPO.obterPeriodoEsteAno(), consultaDeVendasPO.inputPeriodo.getAttribute("value"));

        consultaDeVendasPO.selecionarElementoSelect(consultaDeVendasPO.selectDataPreEstabelecida, "Mês anterior"); 
        Assert.assertEquals(consultaDeVendasPO.obterPeriodoMesAnterior(), consultaDeVendasPO.inputPeriodo.getAttribute("value"));

        consultaDeVendasPO.botaoCalendario.click();
    }

    /** Teste para conferir o tipo de venda selecionado. */
	@Test
	public void T0003_deve_conferir_tipo_de_vendas_selecionado() {
		List<WebElement> lista = consultaDeVendasPO.selectTipoDeVendas.findElements(By.tagName("option"));

		consultaDeVendasPO.selecionarTipoDeVenda(0);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Todos", lista.get(0).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(1);
		consultaDeVendasPO.botaoPesquisarVendas.click();
		
		Assert.assertEquals("CF-e S@T", lista.get(1).getText());
		
		consultaDeVendasPO.selecionarTipoDeVenda(2);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("NFC-e", lista.get(2).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(3);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Nota Manual", lista.get(3).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(4);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Nota manual C/Nº", lista.get(4).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(5);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Nota manual S/Nº", lista.get(5).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(6);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("P.Venda + Atend. C/Nº", lista.get(6).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(7);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Pedido de Venda", lista.get(7).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(8);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Ponto Venda", lista.get(8).getText());

		consultaDeVendasPO.selecionarTipoDeVenda(9);
		consultaDeVendasPO.botaoPesquisarVendas.click();
	
		Assert.assertEquals("Retiradas", lista.get(9).getText());
	}
	//#endregion
}
