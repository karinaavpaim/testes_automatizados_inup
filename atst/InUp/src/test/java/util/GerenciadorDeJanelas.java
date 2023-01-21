package util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;

/**Classe responsável por gerenciar as janelas do navegador*/
public class GerenciadorDeJanelas {
    
    //#region Região dos atributos.

    /**Set responsável por armazenar os ID's de todas as janelas abertas pelo sistema.*/
    private Set<String> identificadoresJanelasAbertas;
    /**Identificador da primeira aba aberta pelo sistema.*/
    private String identificadorPrimeiraJanelaAberta;
   /**Contador utilizado para controlar o tempo máximo de looping em uma estrutura de repetição*/
    private int contador;

    //#endregion

    //#region Região dos métodos.

    /**
     * Método responsável por obter os identificadores de todas as janelas abertas pelo sistema.
     * @param driver Driver utilizado pelo sistema.
     * @param sizeWindowHandles Quantidade de identificadores que é esperado, ao atingir essa quantidade o sistema dará continuidade em sua execução.
     * @param tempoMaximoEspera Tempo máximo (em segundos) que o sistema deve aguardar enquanto não é atingido a quantidade de identificadores esperados.
     * caso atinja o tempo máximo e não tenha chegado a quantidade de identificadores esperada, o sistema dará continuidade em sua execução.
     * @return retorna um Set<String> com os identificadores das janelas abertas pelo sistema.
     */
    public Set<String> obterIdentificadoresJanelasAbertas(WebDriver driver, int sizeWindowHandles, int tempoMaximoEspera) throws InterruptedException {
        identificadoresJanelasAbertas = new HashSet<String>();

        contador = 1;

        while(identificadoresJanelasAbertas.size() != sizeWindowHandles && contador != tempoMaximoEspera) {
            Thread.sleep(1000);
            identificadoresJanelasAbertas = driver.getWindowHandles();
            contador ++;
        }

        return identificadoresJanelasAbertas;
    }

    /**
     * Método responsável por obter o identificador da primeira janela aberta pelo sistema.
     * @param driver Drive utilizado pelo sistema.
     * @return Retorna o identificador da primeira janela aberta pelo sistema.
     */
    public String obterIdentificadorPrimeiraJanelaAberta(WebDriver driver) {
        identificadorPrimeiraJanelaAberta = driver.getWindowHandle();
        return identificadorPrimeiraJanelaAberta;
    }

    /**
     * Método responsável pela navegação entre as janelas do sistema.
     * @param driver Driver utilizado pelo sistema.
     * @param identificadorJanelaParaNavegar Identificador da janela para qual o sistema deve navegar.
     */
    public void navegarEntreJanelas(WebDriver driver, String identificadorJanelaParaNavegar) {
        driver.switchTo().window(identificadorJanelaParaNavegar);
    }

    /**Método responsável por fechar a janela de impressão
     * @param driver Driver utilizado pelo sistema.
    */
    public void fecharJanelaImpressao(WebDriver driver) throws InterruptedException {
        
        identificadorPrimeiraJanelaAberta = obterIdentificadorPrimeiraJanelaAberta(driver);
        identificadoresJanelasAbertas = obterIdentificadoresJanelasAbertas(driver, 3, 5);
        
        Stream<String> stream = identificadoresJanelasAbertas.stream();
            List<String> listaIdentificadores = stream.filter(identificadorJanelaAtual -> identificadorJanelaAtual != null)
            .filter(identificadorJanelaAtual -> !identificadorJanelaAtual.equals(identificadorPrimeiraJanelaAberta))
            .collect(Collectors.toList());
            try {
                if(listaIdentificadores.size() == 2) {
                    navegarEntreJanelas(driver, listaIdentificadores.get(0));
                    String urlAtual = driver.getCurrentUrl();
                    if(urlAtual.contains("chrome://print/")) {
                        navegarEntreJanelas(driver, listaIdentificadores.get(1));
                        driver.close();
                    }
                }
            }catch(Exception e) {
                System.out.println(e);
            }
            navegarEntreJanelas(driver, identificadorPrimeiraJanelaAberta);
    }

    /**
     * Método responsável por criar uma nova Thread de trabalho.
     * @param driver Driver utilizado pelo sistema.
     */
    public void criarNovaThread(WebDriver driver) throws InterruptedException {
        
        final Runnable codeToRun = new Thread() {
            @Override
            public void run() {
                System.out.println("Conteúdo da nova Thread");
            }
        };
        
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<?> future = executor.submit(codeToRun);
        
        try {
            future.get(30, TimeUnit.SECONDS);
            System.out.println("Conteúdo da Thread Original");
            
          } catch (ExecutionException | TimeoutException | InterruptedException e) {
            System.out.println(e);
            executor.shutdownNow();
          }
    }
    //#endregion
}