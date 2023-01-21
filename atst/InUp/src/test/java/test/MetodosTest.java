package test;


import org.junit.Test;

import page.cashNet.CashNetPO;


/** Classe para testar os metodos diretamente de forma independente. */
public class MetodosTest extends BaseTest {

  // #region regiao dos testes

  /** Testa os metodos criados. */
  @Test
  public void testarMetodos() throws InterruptedException {
    CashNetPO cashNetPO = new CashNetPO(driver);
    
    cashNetPO.navegarParaCashNet();

		cashNetPO.selecionarElementoSelect(cashNetPO.selectLoja, "ESCRITORIO");
  }
}
// #endregion
