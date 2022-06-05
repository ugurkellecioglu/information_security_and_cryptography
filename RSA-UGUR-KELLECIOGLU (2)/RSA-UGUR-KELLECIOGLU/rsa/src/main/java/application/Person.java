package application;

public class Person {
    private Message plainText;
    private Message cipherText;
    private Key privateKey;
    private Key publicKey;
    private String name;
    PersonType personType;

    public Person(PersonType personType) {
        this.personType = personType;
    }

    public Message getPlainText() {
        return plainText;
    }

    public void setPlainText(Message plainText) {
        this.plainText = plainText;
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Key privateKey) {
        this.privateKey = privateKey;
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(Key publicKey) {
        this.publicKey = publicKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getCipherText() {
        return cipherText;
    }

    public void setCipherText(Message cipherText) {
        this.cipherText = cipherText;
    }
}
