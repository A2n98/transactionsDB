import parse.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        var filePath = "./transactions.csv";
        var parser = new Parser();
        var transList = parser.ParseTransactionsCsv(filePath);
        System.out.println(transList.get(0));

    }
}
