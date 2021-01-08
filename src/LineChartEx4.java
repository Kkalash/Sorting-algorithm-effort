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

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class LineChartEx4 extends JFrame {
	
	ProcessHilper quickSortDataset;
	ProcessHilper bubbleSortDataset;
	ProcessHilper insertionSortDataset;
	ProcessHilper selectionSortDataset;
	
	   public static void main(String[] args) {

//	        EventQueue.invokeLater(() -> {
//
//	        	LineChartEx4 ex = new LineChartEx4();
//	            ex.setVisible(true);
//	        });
	    }

    public LineChartEx4(ProcessHilper quickSortDataset, ProcessHilper bubbleSortDataset, ProcessHilper insertionSortDataset, ProcessHilper selectionSortDataset) {
    	this.quickSortDataset = quickSortDataset;
    	this.bubbleSortDataset = bubbleSortDataset;
    	this.insertionSortDataset = insertionSortDataset;
    	this.selectionSortDataset = selectionSortDataset;
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

    	 XYSeriesCollection dataset = new XYSeriesCollection();

    	    XYSeries bubbleSort = new XYSeries(bubbleSortDataset.algorithmName);
    	    for(int i = 0; i < bubbleSortDataset.multiplikativDataSet.length; i++) {
    	    	bubbleSort.add(bubbleSortDataset.multiplikativDataSet[i].numberCount, bubbleSortDataset.multiplikativDataSet[i].elapsedTime);
    	    }
    	    
    	    XYSeries quickSort = new XYSeries(quickSortDataset.algorithmName);
    	    for(int i = 0; i < quickSortDataset.multiplikativDataSet.length; i++) {
    	    	quickSort.add(quickSortDataset.multiplikativDataSet[i].numberCount, quickSortDataset.multiplikativDataSet[i].elapsedTime);
    	    }
    	    
    	    XYSeries insertionSort = new XYSeries(insertionSortDataset.algorithmName);
    	    for(int i = 0; i < insertionSortDataset.multiplikativDataSet.length; i++) {
    	    	insertionSort.add(insertionSortDataset.multiplikativDataSet[i].numberCount, insertionSortDataset.multiplikativDataSet[i].elapsedTime);
    	    }
    	    
    	    XYSeries selectionSort = new XYSeries(selectionSortDataset.algorithmName);
    	    for(int i = 0; i < selectionSortDataset.multiplikativDataSet.length; i++) {
    	    	selectionSort.add(selectionSortDataset.multiplikativDataSet[i].numberCount, selectionSortDataset.multiplikativDataSet[i].elapsedTime);
    	    }
    	  
    	    
    	    dataset.addSeries(bubbleSort);
    	    dataset.addSeries(quickSort);
    	    dataset.addSeries(insertionSort);
    	    dataset.addSeries(selectionSort);
    	    
        return dataset;
    }

    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sortierung die Zahlen per Nano-Zeit",
                "Anzahl der Zahlen",
                "Dauer der Auflösung in Nano-Zeit",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.GREEN);
        renderer.setSeriesPaint(3, Color.YELLOW);
        
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setOutlinePaint(Color.BLUE);
        plot.setDomainGridlinePaint(Color.BLACK);

        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(true);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Aufwand der Sortierklassen",
                        new Font("Serif", Font.BOLD, 30)
                )
        );

        return chart;
    }
}