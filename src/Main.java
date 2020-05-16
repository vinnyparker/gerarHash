import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Main {
    public static void main(String[] arg) {

        String nome = "o nome que vem do cadastro";
        Date dataValidade = new Date(); /** ou a data q vem do cadastro ***/
        String cnpj = "44.555.666/0001-99"; /*** cnpj tbm vem do cadastro ***/

        String hash = geraHash(nome + dataValidade + cnpj);
        System.out.println("Gera Um Hash Numerico = >> " + hash);

        String hashControl = stringHexa(gerarHash(hash, "SHA-256")).toUpperCase();
        String codeControl = hashControl.substring(2, 6) + "-" + hashControl.substring(7, 11) + "-" + hashControl.substring(12, 16);
        System.out.println("Gera um Hash AlfaNumerico = >> " + codeControl);
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

    private static byte[] gerarHash(String frase, String algoritmo) {
        try {
            MessageDigest md = MessageDigest.getInstance(algoritmo);
            md.update(frase.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
