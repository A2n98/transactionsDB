import parse.*;
import date_base.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        var filePath = "./transactions.csv";
        var parser = new Parser();
        var transList = parser.ParseTransactionsCsv(filePath);
        var db = DBworker.getInstance();
        //for(var transaction:transList){
            //db.addTransaction(transaction);
        //}

    }
}
