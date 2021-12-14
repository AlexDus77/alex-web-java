package database.ZADANIE;

import java.util.ArrayList;
import java.util.List;

public interface DAO {
    boolean connect();
    boolean disconnect();
    boolean signIn();
    void signUp();
    List isContain(String c, String v, String d);

}
