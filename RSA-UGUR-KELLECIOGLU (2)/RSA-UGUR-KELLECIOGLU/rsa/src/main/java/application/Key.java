package application;

public class Key {
    KeyType keyType;
    String val;

    public Key(KeyType keyType, String val) {
        this.keyType = keyType;
        this.val = val;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
