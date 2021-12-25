import chart.printer.ChartPrinter;
import date.base.DBworker;
import models.TransactionSumModel;
import parse.Parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {
    private static HashMap<String, String> months = new HashMap();
    static{
        months.put("2020.01", "Январь");
        months.put("2020.02", "Февраль");
        months.put("2020.03", "Март");
        months.put("2020.04", "Апрель");
        months.put("2020.05", "Май");
        months.put("2020.06", "Июнь");
        months.put("2020.07", "Июль");
        months.put("2020.08", "Август");
        months.put("2020.09", "Сентябрь");
        months.put("2020.1", "Октябрь");
        months.put("2020.11", "Ноябрь");
        months.put("2020.12", "Декабрь");
    }
    public static void main(String[] args) throws IOException, SQLException {
        var filePath = "./transactions.csv";
        var parser = new Parser();
        var transList = parser.ParseTransactionsCsv(filePath);
        var db = DBworker.getInstance();
        var result = db.getTransactionSumModels().stream().map(model -> {
            var newModel = new TransactionSumModel();
            newModel.sum = model.sum;
            newModel.month = months.get(model.month);
            return newModel;
        }).collect(Collectors.toList());
        var chartPrinter = new ChartPrinter();
        chartPrinter.createChart(result);           //создаёт файл с таблицей
        db.printTask2(); //принтит в консоль значения из второго задания
    }


}
