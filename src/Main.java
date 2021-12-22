import classes.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        var filePath = "./transactions.csv";
        var transList = ParseTransactionsCsv(filePath);
        System.out.println(transList.get(0));

    }

    private static List<Transaction> ParseTransactionsCsv(String filePath) throws IOException {
        var transactions = new ArrayList<Transaction>();
        var fileLines = Files.readAllLines(Paths.get(filePath));

        for (String fileLine : fileLines) {
            var lineArr = ParseLine(fileLine);
            if (Objects.equals(lineArr.get(0), "Series_reference")) continue;

            var trInfo = new TransInfo(lineArr.get(3), lineArr.get(4), lineArr.get(5), lineArr.get(6), lineArr.get(7), lineArr.get(8));
            var seTitles = new SerieTitles(lineArr.get(9), lineArr.get(10), lineArr.get(11), lineArr.get(12), lineArr.get(13));
            var doub = Objects.equals(lineArr.get(2), "") ? 0.0 : Double.parseDouble(lineArr.get(2));

            var transaction = new Transaction(lineArr.get(0), lineArr.get(1), doub, trInfo, seTitles);

            transactions.add(transaction);
        }
        return transactions;
    }

    private static ArrayList<String> ParseLine(String line){
        var res = new ArrayList<String>();
        StringBuilder value = new StringBuilder();
        for(var b: line.toCharArray()){
            if(Objects.equals(b,',')){
                if (value.length() == 0){
                    res.add("");
                }
                else{
                    res.add(value.toString());
                }
                value = new StringBuilder();
            }
            else{
                value.append(b);
            }
        }

        if (res.size() != 14){
            while (res.size() != 14) res.add("");
        }

        return res;
    }
}
