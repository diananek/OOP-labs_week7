package org.example;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import org.example.entity.Benchmark;
import org.example.entity.Fibonacci;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Map;

public class App extends JFrame {

    public App() {
        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

        var seriesFirst = new XYSeries("Классическая реализация");
        var seriesSecond = new XYSeries("Реализация с кэшированием");

        Map<Integer, Long> timesFirst = Benchmark.getTimes((x) -> {
            Fibonacci fibonacci = new Fibonacci();
            fibonacci.getFibonacci(x);
        });
        Map<Integer, Long> timesSecond = Benchmark.getTimes((x) -> {
            Fibonacci fibonacci = new Fibonacci();
            fibonacci.getCachedFibonacci(x);
        });

        for (Map.Entry<Integer, Long> entry: timesFirst.entrySet()) {
            seriesFirst.add((double) entry.getKey(), (double) entry.getValue());
        }
        for (Map.Entry<Integer, Long> entry: timesSecond.entrySet()) {
            seriesSecond.add((double) entry.getKey(), (double) entry.getValue());
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(seriesFirst);
        dataset.addSeries(seriesSecond);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Зависимость времени выполнения функции от числа Фибоначчи",
                "Число",
                "Время выполнения (мс)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(0, Color.BLUE);

        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Зависимость времени выполнения функции от числа Фибоначчи",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new App();
            ex.setVisible(true);
        });
    }
}
