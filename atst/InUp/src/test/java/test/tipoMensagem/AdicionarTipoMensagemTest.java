package test.tipoMensagem;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import page.TipoMensagemPO;
import page.cashNet.CashNetPO;
import page.netShopper.NetShopperPO;
import test.BaseTest;

/** Classe responsável pelos testes da tela "Tipos de mensagens" do Interfacenet up*/
public class AdicionarTipoMensagemTest extends BaseTest {

    //#region Regiao dos atributos
    private static TipoMensagemPO tipoMensagemPO;
    private static NetShopperPO netShopperPO;
    private static CashNetPO cashNetPO;
    private final String mensagem = faker.name().fullName();
    //#endregion

    /** Metodo para inciar os testes desta classe. */
    @BeforeClass
    public static void iniciarTestes() {
        tipoMensagemPO = new TipoMensagemPO(driver);
        netShopperPO = new NetShopperPO(driver);
        cashNetPO = new CashNetPO(driver);

        tipoMensagemPO.navegarParaTiposDeMensagens();
    }

    /** Teste para criar uma nova mensagem. */
    @Test
    public void CT01_valida_a_criacao_com_sucesso_de_uma_nova_mensagem(){
        tipoMensagemPO.criaUmaNovaMensagem(mensagem);

        Assert.assertEquals("Sucesso!\nSucesso ao gravar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));
       
        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();
        
        Assert.assertTrue(tipoMensagemPO.verificarSeNomeEstaNaTabela(mensagem));
    }

    /** Teste para alterar uma mensagem. */
    @Test
    public void CT02_valida_a_alteracao_de_uma_mensagem_ja_criada(){
        tipoMensagemPO.atualizarPagina(5);
        tipoMensagemPO.pesquisar();

        tipoMensagemPO.realizaAcoes("EDIÇÃO", TipoMensagemPO.tipoAcao.EDITAR);

        tipoMensagemPO.verificarSeElementoEstaVisivelEDisponivel(tipoMensagemPO.tituloDaMensagemFlutuante);

        tipoMensagemPO.limparCampoInput(tipoMensagemPO.inputTipo);
        tipoMensagemPO.inputTipo.sendKeys("EDICAO");
        tipoMensagemPO.botaoSalvar.click();

        Assert.assertEquals("Sucesso!\nSucesso ao gravar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();

        Assert.assertTrue(tipoMensagemPO.verificarSeNomeEstaNaTabela("EDICAO"));

        tipoMensagemPO.realizaAcoes("EDICAO", TipoMensagemPO.tipoAcao.EDITAR);

        tipoMensagemPO.verificarSeElementoEstaVisivelEDisponivel(tipoMensagemPO.tituloDaMensagemFlutuante);

        tipoMensagemPO.limparCampoInput(tipoMensagemPO.inputTipo);
        tipoMensagemPO.inputTipo.sendKeys("EDIÇÃO");
        tipoMensagemPO.botaoSalvar.click();

        Assert.assertEquals("Sucesso!\nSucesso ao gravar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();

        Assert.assertTrue(tipoMensagemPO.verificarSeNomeEstaNaTabela("EDIÇÃO"));
    }

    /** Teste para excluir uma mensagem. */
    @Test
    public void CT03_valida_a_exclusao_de_uma_mensagem(){
        tipoMensagemPO.atualizarPagina(5);

        tipoMensagemPO.criaUmaNovaMensagem("EXCLUIR");
        tipoMensagemPO.fecharMensagensFlutuantes();

        tipoMensagemPO.realizaAcoes("EXCLUIR", TipoMensagemPO.tipoAcao.EXCLUIR);

        Assert.assertEquals("Sucesso!\nSucesso ao inativar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();
        
        Assert.assertFalse(tipoMensagemPO.verificarSeNomeEstaNaTabela("EXCLUIR"));
    }

    /** Teste para reativar uma mensagem desativada. */
    @Test
    public void CT04_valida_a_reativacao_de_uma_mensagem_desativada(){
        tipoMensagemPO.atualizarPagina(5);

        tipoMensagemPO.criaUmaNovaMensagem(mensagem);

        Assert.assertEquals("Sucesso!\nSucesso ao gravar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();

        Assert.assertTrue(tipoMensagemPO.verificarSeNomeEstaNaTabela(mensagem));

        tipoMensagemPO.realizaAcoes(mensagem, TipoMensagemPO.tipoAcao.EXCLUIR);

        Assert.assertEquals("Sucesso!\nSucesso ao inativar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();

        Assert.assertFalse(tipoMensagemPO.verificarSeNomeEstaNaTabela(mensagem));

        tipoMensagemPO.verificarSeElementoEstaVisivelEDisponivel(tipoMensagemPO.inputAtivos);
        tipoMensagemPO.inputAtivos.click();

        tipoMensagemPO.pesquisar();
        tipoMensagemPO.realizaAcoes(mensagem, TipoMensagemPO.tipoAcao.REATIVAR);

        Assert.assertEquals("Sucesso!\nSucesso ao gravar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.inputAtivos.click();
        tipoMensagemPO.pesquisar();

        Assert.assertTrue(tipoMensagemPO.verificarSeNomeEstaNaTabela(mensagem));
    }

    /** Teste para criticar a criacao de uma mensagem ja existente. */
    @Test
    @Ignore("IN-9014")
    public void CT05_valida_a_criacao_de_uma_mensagem_ja_existente(){
    }

    /** Teste para criticar o campo em branco de nome de uma nova mensagem. */
    @Test
    @Ignore("IN-9012")
    public void CT06_valida_a_criacao_de_uma_mensagem_sem_preencher_o_campo_com_o_nome_da_mensagem(){
    }

    /** Teste para verificar a criação da mensagem em CashNet. */
    @Test
    public void CT07_valida_que_a_mensagem_criada_foi_refletida_com_sucesso_no_cashnet_e_netshopper(){
        tipoMensagemPO.atualizarPagina(5);

        tipoMensagemPO.criaUmaNovaMensagem("TESTE PRESENTE CASHNET E NETSHOPPER");

        Assert.assertEquals("Sucesso!\nSucesso ao gravar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        cashNetPO.navegarParaCashNet();

        tipoMensagemPO.verificarSeElementoEstaVisivelEDisponivel(cashNetPO.inputCliente);
        cashNetPO.inputPresente.click();

        tipoMensagemPO.verificarSeValorExisteSelectBox(netShopperPO.selectBoxPresente, "TESTE PRESENTE CASHNET E NETSHOPPER");

        netShopperPO.navegarParaNetshopper();

        tipoMensagemPO.verificarSeElementoEstaVisivelEDisponivel(netShopperPO.inputPresente);
        netShopperPO.inputPresente.click();

        tipoMensagemPO.verificarSeValorExisteSelectBox(netShopperPO.selectBoxPresente, "TESTE PRESENTE CASHNET E NETSHOPPER");

        tipoMensagemPO.navegarParaTiposDeMensagens();
        tipoMensagemPO.pesquisar();

        tipoMensagemPO.realizaAcoes("TESTE PRESENTE CASHNET E NETSHOPPER", TipoMensagemPO.tipoAcao.EXCLUIR);
        
        Assert.assertEquals("Sucesso!\nSucesso ao inativar a mensagem!", tipoMensagemPO.obterTextoMensagemFlutuante(driver, 5));

        tipoMensagemPO.fecharMensagensFlutuantes();
        tipoMensagemPO.pesquisar();

        Assert.assertFalse(tipoMensagemPO.verificarSeNomeEstaNaTabela("TESTE PRESENTE CASHNET E NETSHOPPER"));
    }
    //#endregion
}
