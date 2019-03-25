package company.Networking.RMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.time.LocalDateTime;

public class Token implements Remote, Serializable {
    private LocalDateTime time;
    private String token;
    public Token(int len) {
        time = LocalDateTime.now();
        token = RandomStringGenerator.generate(len);
    }

    @Override
    public boolean equals(Object obj) {
        Token tk = (Token) obj;
        return token == tk.getToken();
    }

    public void refresh() {
        time = LocalDateTime.now();
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
