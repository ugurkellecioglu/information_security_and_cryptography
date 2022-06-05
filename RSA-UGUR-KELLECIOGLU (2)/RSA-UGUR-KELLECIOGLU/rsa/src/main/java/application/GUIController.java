package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class GUIController {
    KeyGeneration keyGeneration = new KeyGeneration();
    FermatPrimality fermatPrimality = new FermatPrimality();
    @FXML
    private TextArea pTextArea;
    @FXML
    private TextArea toitentTextArea;

    @FXML
    private TextArea qTextArea;

    @FXML
    private ComboBox privateKeys;
    @FXML
    private ComboBox publicKeys;
    @FXML
    private Button encrypt;
    @FXML
    private Button decrypt;
    @FXML
    private TextArea plainText;

    @FXML
    private TextArea cipherText;
    @FXML
    private TextArea publicKeyTextArea;

    @FXML
    private TextArea alicesKeyTextArea;
    @FXML
    private TextArea bobsKeyTextArea;

    @FXML
    private TextArea testPrimalityTextArea;


    Person sender = new Person(PersonType.Sender);
    Person receiver = new Person(PersonType.Receiver);

    HashMap<Integer, Key> hmap = new HashMap<Integer, Key>();

    @FXML
    protected void createKeyPair() {
       try {
           keyGeneration.getConstants();
           pTextArea.setText(keyGeneration.getP().toString());
           qTextArea.setText(keyGeneration.getQ().toString());
           toitentTextArea.setText(keyGeneration.getToitent().toString());

           sender.setName("Sender");
           sender.setPrivateKey(new Key(KeyType.PRIVATE, BigIntegerAdapter.String(keyGeneration.getE())));
           sender.setPublicKey(new Key(KeyType.PUBLIC, BigIntegerAdapter.String(keyGeneration.getN())));
           receiver.setName("Receiver");
           receiver.setPrivateKey(new Key(KeyType.PRIVATE, BigIntegerAdapter.String(keyGeneration.getD())));
           receiver.setPublicKey(new Key(KeyType.PUBLIC, BigIntegerAdapter.String(keyGeneration.getN())));

           publicKeyTextArea.setText(sender.getPublicKey().getVal());
           alicesKeyTextArea.setText(sender.getPrivateKey().getVal());
           bobsKeyTextArea.setText(receiver.getPrivateKey().getVal());
           privateKeys.getItems().add("Priv. Sender: " + (sender.getPrivateKey().getVal().substring(0,20) +"..."));
           privateKeys.getItems().add("Publ. Sender: " + sender.getPublicKey().getVal().substring(0,20) +"...");
           privateKeys.getItems().add("Priv. Receiver: " + receiver.getPrivateKey().getVal().substring(0,20) +"...");
           privateKeys.getItems().add("Publ. Receiver: " + receiver.getPublicKey().getVal().substring(0,20) +"...");

           publicKeys.getItems().add("Priv. Sender: " + (sender.getPrivateKey().getVal().substring(0,20) +"..."));
           publicKeys.getItems().add("Publ. Sender: " + sender.getPublicKey().getVal().substring(0,20) +"...");
           publicKeys.getItems().add("Priv. Receiver: " + receiver.getPrivateKey().getVal().substring(0,20) +"...");
           publicKeys.getItems().add("Publ. Receiver: " + receiver.getPublicKey().getVal().substring(0,20) +"...");
           publicKeys.setDisable(false);
           privateKeys.setDisable(false);
           encrypt.setDisable(false);
           decrypt.setDisable(false);
           hmap.put(0,sender.getPrivateKey());
           hmap.put(1,sender.getPublicKey());
           hmap.put(2,receiver.getPrivateKey());
           hmap.put(3,receiver.getPublicKey());
       }
       catch (Exception e) {
           alert("Error", "Error occured when creating key pairs", e.toString());
       }


    }
    public static ArrayList<String> encrypted = new ArrayList<String>();
    @FXML
    protected void encrypt() {
        try {
            encrypted = new ArrayList<>();
            int privateIndex = privateKeys.getSelectionModel().getSelectedIndex();
            int publicIndex = publicKeys.getSelectionModel().getSelectedIndex();
            sender.setPlainText(new Message(StringToBinary.convert(this.plainText.getText())));
            String plainText= sender.getPlainText().getVal();
            String [] splitted = plainText.split(" ");
            String str= "";
            for(String s : splitted) {
              encrypted.add( StringToBinary.convert(Encryption.encrypt(new Message(s), hmap.get(privateIndex),hmap.get(publicIndex))));
            }
            this.plainText.setText("");
            String sx = "";
            for(String s : encrypted) {
                sx+=s;
            }
            this.cipherText.setText(sx);
            sender.setCipherText(new Message(sx));
        }
        catch (Exception e) {
          alert("Error on Encryption", "You might forgot to select both public and private key.", e.toString());
        }
    }


    @FXML
    protected void decrypt() {
        try {
            int privateIndex = privateKeys.getSelectionModel().getSelectedIndex();
            int publicIndex = publicKeys.getSelectionModel().getSelectedIndex();
            String res = "";
            String total = "";
            for (String s : encrypted) {
                for(String sx : s.split(" ")){
                    char c =  (char)Integer.parseInt(sx, 2);
                    res += c;
                }
                Message decrypted = new Message(Decryption.decryptPlainText(new Message(res), hmap.get(privateIndex),hmap.get(publicIndex)));
                res = "";
                total += (char) Integer.parseInt(decrypted.getVal(), 2);
            }
            plainText.setText(total);
            receiver.setPlainText(new Message(total));

        }
        catch (Exception e) {
            alert("Error", "Error occured.", e.toString());
        }
    }

    @FXML private Label primalityStatus;

    @FXML
    protected void testPrimality() {
        try {
            Boolean result = fermatPrimality.isPrime(new BigInteger(testPrimalityTextArea.getText()), 20);
            String str = result == true ? "prime" : "not prime";
            alert("Success", "Given number is a " + str, "Number was: " + testPrimalityTextArea.getText());
        }
        catch (Exception e) {
            alert("Error", "Couldn't test the primality.", e.toString());
        }
    }


    private void alert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}