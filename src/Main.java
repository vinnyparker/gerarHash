import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Main {
    public static void main(String[] arg) {
        /*** esta licenca vem do cadastro salva no banco de dados***/
        String licencaAlfaNumerica = "81D0-828F-A500";
        String licencaNumerica = "708841119";

        String nome = "o nome que vem do cadastro";
        String dataValidade = "2020-01-01"; /** ou a data q vem do cadastro ***/
        String cnpj = "44.555.666/0001-99"; /*** cnpj tbm vem do cadastro ***/

        String stringToHash = nome + dataValidade + cnpj;
        String codigo = nome + dataValidade + cnpj;

        codigo = geraHash(codigo);
        System.out.println("Gera Um Hash Numerico = >> " + codigo);
        System.out.println("@@@@@@   a licença Numericaa combina com a do banco?  >>>> " + licencaNumerica.matches(codigo));

        String hashControl = stringHexa(gerarHash2(codigo, "SHA-256")).toUpperCase();
        String codeControl = hashControl.substring(2, 6) + "-" + hashControl.substring(7, 11) + "-" + hashControl.substring(12, 16);
        System.out.println("Gera um Hash AlfaNumerico = >> " + codeControl);
        System.out.println("######   a licença Alfa Numerica combina com a do banco? >>>  " + licencaAlfaNumerica.matches(codeControl));

//        System.out.println(stringHexa(gerarHash2(stringToHash, "MD5")));
//        System.out.println(stringHexa(gerarHash2(stringToHash, "SHA-1")));
//        System.out.println(stringHexa(gerarHash2(stringToHash, "SHA-256")));
    }

    private static String geraHash(String value) {
        String result;
        result = String.valueOf(value.hashCode());

        return result;
    }

    private static String stringHexa(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    private static byte[] gerarHash2(String frase, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
