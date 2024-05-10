
public class Token {
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getString() {
        return this.token;
    }

    @Override
    public String toString() {
        return this.token;
    }
}
