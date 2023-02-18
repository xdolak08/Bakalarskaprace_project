package com.example.semestralproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class MainActivity extends AppCompatActivity {

    EditText inputKey, inputText;
    TextView finalText;
    Button encrypt;
    Button decrypt;
    RadioGroup radioGroupLeft;
    RadioGroup radioGroupRight;
    String outputString;
    String AES = "AES";
    RadioButton radioECB;
    RadioButton radioCBC;
    RadioButton radioGCM;
    RadioButton radioRSA;
    RadioButton radioCTR;
    RadioButton radioSHA256;


    public void openNextPage(View view){
        Intent intent = new Intent(this, Signatures.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputKey = findViewById(R.id.inputKey);
        encrypt = findViewById(R.id.encrypt);
        inputText = findViewById(R.id.inputText);
        finalText = findViewById(R.id.outputText);
        decrypt = findViewById(R.id.decrypt);
        radioGroupLeft = findViewById(R.id.radiogroupLeft);
        radioGroupRight = findViewById(R.id.radiogroupRight);
        radioECB = findViewById(R.id.radioECB);
        radioGCM = findViewById(R.id.radioGCM);
        radioCBC = findViewById(R.id.radioCBC);
        radioRSA = findViewById(R.id.radioRSA);
        radioCTR = findViewById(R.id.radioCTR);
        radioSHA256 = findViewById(R.id.radioSHA256);





        radioGroupRight.clearCheck();
        radioGroupRight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });

        radioGroupLeft.clearCheck();
        radioGroupLeft.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });





        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int selectedIdleft = radioGroupLeft.getCheckedRadioButtonId();
                    int selectedIdright = radioGroupRight.getCheckedRadioButtonId();
                    if(selectedIdleft == radioCBC.getId()) {
                        Toast.makeText(MainActivity.this,"Vybrali jste CBC", Toast.LENGTH_LONG).show();
                        mereni();
                        outputString = CBCencrypt(inputText.getText().toString(), inputKey.getText().toString());
                        finalText.setText(outputString);

                    } else if (selectedIdleft == radioECB.getId()) {
                        Toast.makeText(MainActivity.this,"Vybrali jste ECB", Toast.LENGTH_LONG).show();
                        outputString = ECBencrypt(inputText.getText().toString(), inputKey.getText().toString());
                        finalText.setText(outputString);
                    } else if (selectedIdleft == radioGCM.getId()){
                        Toast.makeText(MainActivity.this,"Vybrali jste GCM", Toast.LENGTH_LONG).show();
                        outputString = GCMencrypt(inputText.getText().toString());
                        finalText.setText(outputString);
                    }
                    else if (selectedIdleft == radioCTR.getId()){
                        Toast.makeText(MainActivity.this,"Vybrali jste CTR", Toast.LENGTH_LONG).show();
                        outputString = CTRencrypt(inputText.getText().toString(), inputKey.getText().toString());
                        finalText.setText(outputString);
                    }
                    else if (selectedIdright == radioRSA.getId()){
                        Toast.makeText(MainActivity.this,"Vybrali jste RSA", Toast.LENGTH_LONG).show();
                        outputString = RSAencrypt(inputText.getText().toString());
                        finalText.setText(outputString);
                        radioGroupRight.clearCheck();
                    } else if (selectedIdright == radioSHA256.getId()) {
                        Toast.makeText(MainActivity.this,"Vybrali jste SHA-256", Toast.LENGTH_LONG).show();
                        outputString = sha256String(inputText.getText().toString());
                        finalText.setText(outputString);
                        radioGroupRight.clearCheck();
                    } else Toast.makeText(MainActivity.this,"Nebyla vybrána šifra!", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int selectedId = radioGroupLeft.getCheckedRadioButtonId();
                    if(selectedId == radioECB.getId()) {
                        Toast.makeText(MainActivity.this,
                                "Vybrali jste dešifrování ECB",
                                Toast.LENGTH_LONG).show();
                        outputString = ECBdecrypt(outputString, inputKey.getText().toString());
                        finalText.setText(outputString);
                        radioGroupLeft.clearCheck();
                    } else if (selectedId == radioRSA.getId()){
                        Toast.makeText(MainActivity.this,"Vybrali jste dešifrování RSA",
                                Toast.LENGTH_LONG).show();
                        outputString = RSAdecrypt(outputString);
                        finalText.setText(outputString);
                    }
                    else if (selectedId == radioCBC.getId()) {
                        Toast.makeText(MainActivity.this,"Vybrali jste dešifrování CBC",
                                Toast.LENGTH_LONG).show();
                        outputString = CBCdecrypt(outputString, CBCkey, iv);
                        finalText.setText(outputString);
                        radioGroupLeft.clearCheck();
                    }
                    else if (selectedId == radioGCM.getId()){
                        Toast.makeText(MainActivity.this,"Vybrali jste dešifrování GCM",
                                Toast.LENGTH_LONG).show();
                        outputString = GCMdecrypt(outputString);
                        finalText.setText(outputString);
                        radioGroupLeft.clearCheck();
                    }
                    else Toast.makeText(MainActivity.this,"Nebyla vybrána šifra!",
                                Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //AES----------------------------------------------------------------

    public void mereni() throws Exception {
        for (int i = 0; i < 100; ++i)
        {
            CTRencrypt("Testovaci text", "hodnesilneheslo");
            Log.d("Měření", "pokus" + i);
        }
    }

    public static IvParameterSpec getIVSecureRandom(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] iv = new byte[Cipher.getInstance(algorithm).getBlockSize()];
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    IvParameterSpec iv;
    SecretKeySpec CBCkey;
    private String CBCencrypt(String Data, String password) throws Exception {

        iv = getIVSecureRandom("AES/CBC/PKCS5Padding");
        CBCkey = generateKey(password);
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, CBCkey, iv);
        byte[] encode = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encode, Base64.DEFAULT);

        return encryptedValue;
    }

    IvParameterSpec ivCTR;
    SecretKeySpec CTRkey;
    private String CTRencrypt(String Data, String password) throws Exception {

        ivCTR = getIVSecureRandom("AES/CTR/PKCS5Padding");
        CTRkey = generateKey(password);
        Cipher c = Cipher.getInstance("AES/CTR/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, CTRkey, ivCTR);
        byte[] encode = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encode, Base64.DEFAULT);

        return encryptedValue;
    }

    public static String CBCdecrypt (String cipherText, SecretKey key, IvParameterSpec IV)
    {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getIV());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            String decryptedValue = Base64.encodeToString(cipherText.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
            byte[] Text = cipher.doFinal(decryptedValue.getBytes(StandardCharsets.UTF_8));

            return Text.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private String ECBencrypt(String Data, String password) throws Exception {

        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] enc = c.doFinal(Data.getBytes());
        String enryptedValue = Base64.encodeToString(enc, Base64.DEFAULT);

        return enryptedValue;
    }


    Cipher GCMencryptionCipher = null;
    SecretKey GCMkey;
    public String GCMencrypt(String message) throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        GCMkey = generator.generateKey();
        System.out.println(GCMkey.getEncoded().toString());
        byte[] messageInBytes = message.getBytes();
        GCMencryptionCipher = Cipher.getInstance("AES_128/GCM/NoPadding");
        GCMencryptionCipher.init(Cipher.ENCRYPT_MODE,GCMkey);
        byte[] encryptedBytes = GCMencryptionCipher.doFinal(messageInBytes);
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
    }

    public String GCMdecrypt(String encryptedMessage) throws Exception{
        byte[] messageInBytes = Base64.decode(encryptedMessage,Base64.DEFAULT);
        Cipher decryptionCipher = Cipher.getInstance("AES_128/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, GCMencryptionCipher.getIV());
        decryptionCipher.init(Cipher.DECRYPT_MODE,GCMkey,spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return decryptedBytes.toString();
    }



   public String ECBdecrypt(String outputString, String password)
            throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] dec = Base64.decode(outputString, Base64.DEFAULT);
        byte[] decadicValue = cipher.doFinal(dec);
        String decryptString = new String(decadicValue);
        return decryptString;
    }


    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;

    }








    // RSA ----------------------------


    KeyPairGenerator kpg;
    KeyPair kp;
    PublicKey publicKey;
    PrivateKey privateKey;
    byte[] encryptedBytes, decryptedBytes;
    Cipher cipher;
    String decrypted;

    private void generatePair() throws NoSuchAlgorithmException {
        kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(515);
        kp = kpg.genKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();
        System.out.println("Soukromý " + privateKey + " Veřejný " + publicKey);
    }

    private String RSAencrypt(String inputText) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException
    {
        generatePair();
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        encryptedBytes = cipher.doFinal(inputText.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeToString(encryptedBytes,Base64.DEFAULT);
    }

    public String RSAdecrypt(String inputText) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        decryptedBytes = cipher.doFinal(Base64.decode(inputText, Base64.DEFAULT));
        decrypted = new String(decryptedBytes);

        return decrypted;
    }

    public static String sha256String(String text) {
        byte[] hash = null;
        String hashCode = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (hash != null) {
            StringBuilder hashBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(hash[i]);
                if (hex.length() == 1) {
                    hashBuilder.append("0");
                    hashBuilder.append(hex.charAt(hex.length() - 1));
                } else {
                    hashBuilder.append(hex.substring(hex.length() - 2));
                }
            }
            hashCode = hashBuilder.toString();
        }

        return hashCode;
    }
}
