package test.component;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import page.TipoMensagemPO;
import test.BaseTest;

public class TiposDeMensagens extends BaseTest {

    //#region Região dos atributos
    private static TipoMensagemPO tipoMensagemPO;
    //#endregion

    /** Metodo para inciar os testes desta classe. */
    @BeforeClass
    public static void iniciarTestes() {
        tipoMensagemPO = new TipoMensagemPO(driver);
    }

    //#region Regiao dos testes

    /** Teste para validar o limite do campo de mensagens em 50 caractereres. */
    @Test
    public void T0001_valida_o_limite_do_campo_da_mensagem_de_50_caracteres(){
        tipoMensagemPO.navegarParaTiposDeMensagens();

        Assert.assertEquals("Gerencie os tipos de mensagens para presentes", tipoMensagemPO.subtituloDaPagina.getText());

        tipoMensagemPO.verificarSeElementoEstaVisivelEDisponivel(tipoMensagemPO.botaoAdicionarItem);
        tipoMensagemPO.botaoAdicionarItem.click();

        Assert.assertEquals(tipoMensagemPO.inputTipo.getAttribute("maxlength"), "50");

        tipoMensagemPO.botaoCancelar.click();
    }
    //#endregion
}
