package company.View.Views;

import java.io.IOException;
import java.sql.SQLException;

public interface IView {
    String getName();
    void run() throws IOException, SQLException;
}
