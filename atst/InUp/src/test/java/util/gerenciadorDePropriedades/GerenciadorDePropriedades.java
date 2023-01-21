package util.gerenciadorDePropriedades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GerenciadorDePropriedades {

    //#region Metodos

    /**
     * Metodo para obter o valor de uma propriedade configurada no arquivo "propriedades.properties"
     * @param valor é o valor a ser localizado no arquivo
     */

    public static String getValorPropriedade(String valor) {
        Properties properties = new Properties();
        File file = new File("src/test/resources");
        try {
            properties.load(new FileInputStream(file.getAbsolutePath() + "/propriedades.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(valor);
    }


    /**
     * Metodo para inserir uma propriedade e valor no arquivo "propriedades.properties"
     * @param chave será utilizado para definir a chave da propriedade
     * @param valor será o valor atribuido a chave
     */

    public static void setValorPropriedade(String chave, String valor){
        Properties properties = new Properties();
        File file = new File("src/test/resources");
        try {
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath()+"/propriedades.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
            FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath()+"/propriedades.properties");
            properties.setProperty(chave, valor);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //#endregion
}
