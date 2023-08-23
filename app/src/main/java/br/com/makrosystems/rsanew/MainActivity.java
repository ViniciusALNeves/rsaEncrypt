package br.com.makrosystems.rsanew;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity {
    //    static String filePathPublic = "D:/Users/vinicius.luz.MAQ007/Downloads/AES-RSA/RSA/public_key.pem";
//    static String filePathPrivate = "D:\\Users\\vinicius.luz.MAQ007\\Downloads\\AES-RSA\\RSA\\private_key.pem";
    static String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApKhwwJK0ZulrNzIk/YK3GVRQJ/27Wp4je0GcqYWkqHxfSl9EsqHO48Ufap9kzTJ1hLUofNFj2RjCXkOo+zGnQYWMkUSKgcOdGupjNneHYccTgv011qdJ9txjU9RgUkLCWHeOZos0n16V3mZ/2mKoI6kU/clizDTOkvdIKJIyKevAOANcou6k5ccSagNWtpZSSMSwOPOALbF0n5abBMRRkU4iMc673PhSMl5R/CNC73PKIJdpJ5Z1jvVthgYgui3Bmp64RPSztmBgNP8Kwj2inSDT1ShwKQ2nUtaGyOzdFGMQTiW15k0ZLyyplRVUmDIaWABps+NjTLK7aAdLtaeNXQIDAQAB";
    static String privateKeyString = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCkqHDAkrRm6Ws3MiT9grcZVFAn/btaniN7QZyphaSofF9KX0Syoc7jxR9qn2TNMnWEtSh80WPZGMJeQ6j7MadBhYyRRIqBw50a6mM2d4dhxxOC/TXWp0n23GNT1GBSQsJYd45mizSfXpXeZn/aYqgjqRT9yWLMNM6S90gokjIp68A4A1yi7qTlxxJqA1a2llJIxLA484AtsXSflpsExFGRTiIxzrvc+FIyXlH8I0Lvc8ogl2knlnWO9W2GBiC6LcGanrhE9LO2YGA0/wrCPaKdINPVKHApDadS1obI7N0UYxBOJbXmTRkvLKmVFVSYMhpYAGmz42NMsrtoB0u1p41dAgMBAAECggEAahIAIG3hPPJdGP96iLzuCqkfwcNYL/XzUXFg5GutOMR19kkMzi4iQuQ3as4nd3Br+ANjg5S3x6CFhYf8kWTZJAJOHT6UIyfKSw52vx2c3sYJsbZAOnkl0psCeXw0WImc/LR3zWMbkRazYMOS1TH8sow3c42J0ZarBE279DbJjeq8Nww3rWH+Ub407ImzPly7oa0L1eVnCdADgfRphKIu/gTs4a/EX6tvqCA0+HfNdqOIceHLK3HxO/o0F51RsrGrDpOf8l0HzLeDHEcttXH94vHyReBW+btrX5/k4HNgFUuVAPcJZy6reZ6rpHsdCJVa+FR/+StzBBcTOji/VYLw2QKBgQD92XEIYsGbcbWASgJk9gWBs3geXNjT0lBtqP16PruKZ2VE/wjjJsn2wBNMY8eZc2bMglRv4HiSACa5IvQBXJL7CUWmowHeaayMPy6875GW2okm776/l+VFedGnMpR69nY8MFyKwcQDyH3YmEYLOXa4eVWTqWA2lUrlpeeES5DLwwKBgQCmDY6dGmmtJwHTQeiRwBSqUYCKCCTUmdE5HF0fzAoKoL9uUyMkq3Yk5kT5x2JIun1F3Y3CAVDq2+4HlTvbhi/ozWMekqAlj6aMo7VkoYuVqBUtABPbp9urPLlQ2zdItKXnrLuVXc0dc7gWFU2AMDVaAD1SmtuVTplU4iD7dtVQXwKBgQD3GB53rDGLDs8+tkvIXNYVX03QOE5zOahCZhl+UewZl/WyiJHtEkfLYUInvISA8R0iesaYNCcI7w7CbayYWrVTmL3sq4VZKwHjbmkfTg8oiUax2PKS6tconqCeIJT5cEi849Jg2qXjjNg66lZb/K58eNb9KgTWfNHv4mMOmsOyIwKBgH8TnPJpxjm2wlTT+ZGg96+oE8COnvD8jfsw06uD0KN1hUp+4UQs24s9lce5aGPUJA6VkOjJbQ1vJegzss07EesNUy+QcnKMTp8OocDTSClNqnb3ujq9aQTQwdk5CkPfy59St3OFh3Vkm5p6zwas3gd0a3mN/UF0Fy+4phkLRg/tAoGBANLK955AtWNDx8NOZh+UVwuUJLhnh+pbA+rHWJuFLZGi9kZ5/idx5kKXTBI3DQCfrKB0JJJ7pym40i19MrTg+XiX38BC5oxrF34pLVw7ShyUUup4zLfz9GJDGDp7rZy9yWaXzg2tpZFgrAeYMmxtg26aBd0+mDrS99ZvcO+kZ4ko";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String message = "Bankai";

        PublicKey publicKey;
        PrivateKey privateKey;
        try {
            publicKey = readPublicKey();
            privateKey = readPrivateKey();

        } catch (IOException e) {
            System.out.println("Erro ao ler as chaves: " + e.getMessage());
            return;
        }

        byte[] encrypt;
        try {
            encrypt = encryptRSA(publicKey, message);
            String dataEncrypt = Base64.getEncoder().encodeToString(encrypt);
            System.out.println("ENCRIPTADO: "+dataEncrypt);

            byte[] decrypt = decryptRSA(privateKey, dataEncrypt);
            System.out.println(new String(decrypt, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("Erro ao processar a criptografia: " + e.getMessage());
        }

    }

    public static byte[] encryptRSA(PublicKey publicKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] decryptRSA(PrivateKey privateKey, String ciphertextBase64) throws Exception {
        byte[] ciphertext = Base64.getDecoder().decode(ciphertextBase64);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(ciphertext);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PublicKey readPublicKey() throws IOException {
//        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(filePathPublic));
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            throw new IOException("Erro ao ler a chave pública: " + e.getMessage());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static PrivateKey readPrivateKey() throws IOException {
//        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(filePathPrivate));
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IOException("Erro ao ler a chave privada: " + e.getMessage());
        }
    }
}