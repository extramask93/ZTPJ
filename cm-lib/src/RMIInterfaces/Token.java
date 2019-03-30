package RMIInterfaces;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDateTime;
import java.util.Base64;
@XmlRootElement(name="Token")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Token implements Remote, Serializable {
    private LocalDateTime time;
    private String token;
    public Token(int len) {
        time = LocalDateTime.now();
        token = RandomStringGenerator.generate(len);
        token = Base64.getEncoder().encodeToString(token.getBytes());
    }
    public Token() {
    }
    @Override
    public boolean equals(Object obj) {
        Token tk = (Token) obj;
        return token.equals(tk.getToken());
    }

    public LocalDateTime getTime() {
        return time;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
