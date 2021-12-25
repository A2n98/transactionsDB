package chart.printer;

import classes.Transaction;
import models.TransactionSumModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChartPrinter {
    public void createChart(List<TransactionSumModel> models){
        var dataset = new DefaultCategoryDataset();
        for (var model : models){
            dataset.addValue(
                    model.sum,
                    model.month,
                    "Сумма перевода"
            );
        }
        var barChart = ChartFactory.createBarChart(
                "Сумма переводов по месяца",
                "Месяцы",
                "Сумма переводов",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        try {
            ChartUtils.saveChartAsPNG(new File("Сумма переводов.png"), barChart, 800, 480);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
