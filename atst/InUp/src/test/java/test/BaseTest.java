package test;

import java.time.Duration;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import page.LoginPO;
import util.gerenciadorDePropriedades.GerenciadorDePropriedades;

/**
 * Classe base para todos os testes do sistema. Todos os testes do sistema devem herdar dessa classe.
 */
public abstract class BaseTest {

    //#region região dos atributos.
    public static WebDriver driver;
    private static LoginPO loginPO;
    protected static Faker faker = new Faker(new Locale("pt-BR"));
    protected GerenciadorDePropriedades gerenciadorDePropriedades = new GerenciadorDePropriedades();
    //#endregion

    /**
     * Este metodo é executado sempre antes de qualquer outra classe de testes.
     */
    @BeforeClass
    public static void iniciaOsTestes() {
        configuracoesDoNavegador();
        realizaAcessoAoInUp();
    }

    /**
     * Este método realiza as configurações do navegador sem a necessidade de baixar os drivers
     */
    public static void configuracoesDoNavegador() {
        String navegador = GerenciadorDePropriedades.getValorPropriedade("NAVEGADOR");

        if (navegador.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get(GerenciadorDePropriedades.getValorPropriedade("URLBASE"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
   
    /**
     * Este método é responsavel por realizar login no InUp
     */
    public static void realizaAcessoAoInUp() {
        loginPO = new LoginPO(driver);
        loginPO.aguardarElemento(driver, 10);
        loginPO.inputEmail.sendKeys(GerenciadorDePropriedades.getValorPropriedade("LOGIN"));
        loginPO.inputSenha.sendKeys(GerenciadorDePropriedades.getValorPropriedade("SENHA"));
        loginPO.botaoEntrar.click();
        loginPO.aguardarElemento(driver, 1000);
    }

    /**
     * Este método é responsavel por realizar logoff no InUp
     */
    public static void realizarLogoffInUp() {
        loginPO.aguardarElemento(driver, 20);
        loginPO.menuUsuario.click();
        loginPO.aguardarElemento(driver, 20);
        loginPO.menuUsuario.click();
        loginPO.aguardarElemento(driver, 20);
        loginPO.menuUsuario.click();
        loginPO.aguardarElemento(driver, 20);
        loginPO.menuSair.click();
        loginPO.aguardarElemento(driver, 10);
    }

    /**
    * Metodo para fechar o navegador sempre ao final de uma classe de testes.
    */
    @AfterClass
    public static void encerraNavegador() {
    //    realizarLogoffInUp();
        driver.quit();
    }
    // #endregion
}
