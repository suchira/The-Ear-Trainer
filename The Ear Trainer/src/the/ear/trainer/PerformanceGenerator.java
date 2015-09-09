/*
 * Generate charts regarding the performances
 */

package the.ear.trainer;

/**
 *
 * @author Suchira
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class PerformanceGenerator {
    private ReportHandler reportHandler;
    
    public PerformanceGenerator(){
        reportHandler = new ReportHandler();
    }
    public JPanel creatDayPerformance(){
        List<Report> performanceList = reportHandler.getDailyPerformance();
        
        final String chords = "CHORDS";              
      final String pitch = "PITCH";              
      final String scales = "SCALES";              
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        for (Report report : performanceList) {
            if(report.getCategory().equals("Chords")){
                dataset.addValue(report.getFinalScore(), chords, "");
            }
            
            if(report.getCategory().equals("Pitch")){
                dataset.addValue(report.getFinalScore(), pitch, "");
                
            }
            
            if(report.getCategory().equals("Scale") ){
                dataset.addValue(report.getFinalScore(), scales, "");
                
            }
            
        }          
        
                       
      JFreeChart barChart = ChartFactory.createBarChart3D(
         "Todays' Performance",             
         "Category",             
         "Score",             
         dataset,            
         PlotOrientation.VERTICAL,             
         true, true, false);
      ChartPanel panel = new ChartPanel(barChart);
      return panel;
    }

    public JPanel createMonthPerformance(){
        List<Report> performanceList = reportHandler.getMonthPerformance();
        final String chords = "CHORDS";              
      final String pitch = "PITCH";              
      final String scales = "SCALES";              
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        for (Report report : performanceList) {
            if(report.getCategory().equals("Chords")){
                dataset.addValue(report.getFinalScore(), chords, "");
            }
            
            if(report.getCategory().equals("Pitch")){
                dataset.addValue(report.getFinalScore(), pitch, "");
                
            }
            
            if(report.getCategory().equals("Scale") ){
                dataset.addValue(report.getFinalScore(), scales, "");
                
            }
            
        }          
        
                       
      JFreeChart barChart = ChartFactory.createBarChart3D(
         "This Month's Performance",             
         "Category",             
         "Score",             
         dataset,            
         PlotOrientation.VERTICAL,             
         true, true, false);
      ChartPanel panel = new ChartPanel(barChart);
      return panel;

    }

    public JPanel createOverallPerformance(){
        List<Report> performanceList = reportHandler.getOverallPerformance();
        final String chords = "CHORDS";              
      final String pitch = "PITCH";              
      final String scales = "SCALES";              
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        for (Report report : performanceList) {
            if(report.getCategory().equals("Chords")){
                dataset.addValue(report.getFinalScore(), chords, "");
            }
            
            if(report.getCategory().equals("Pitch")){
                dataset.addValue(report.getFinalScore(), pitch, "");
                
            }
            
            if(report.getCategory().equals("Scale") ){
                dataset.addValue(report.getFinalScore(), scales, "");
                
            }
            
        }          
        
                       
      JFreeChart barChart = ChartFactory.createBarChart3D(
         "Overall Performance",             
         "Category",             
         "Score",             
         dataset,            
         PlotOrientation.VERTICAL,             
         true, true, false);
      ChartPanel panel = new ChartPanel(barChart);
      return panel;

    }
    
    public JPanel createScatterPlot(){
        List<Report> reportsList = reportHandler.getAllReports();
        final XYSeries chords = new XYSeries( "CHORDS" );          
        final XYSeries scales = new XYSeries( "SCALES" );          
        final XYSeries pitch = new XYSeries( "PITCH" );          
        int i = 1, j = 1, k = 1;
        
        for (Report report : reportsList) {
            if(report.getCategory().equals("Chords")){
                chords.add(i, report.getFinalScore());
                i++;
            }
            
            if(report.getCategory().equals("Pitch")){
                pitch.add(j, report.getFinalScore());
                j++;
            }
            
            if(report.getCategory().equals("Scale") ){
                scales.add(k, report.getFinalScore());
                k++;
            }
        }
        
               
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( scales );          
      dataset.addSeries( pitch );          
      dataset.addSeries( chords );
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         "Scatter Plot" ,
         "Sequence of Reports" ,
         "Score" ,
         dataset ,
         PlotOrientation.VERTICAL ,
         true , true , false);
      
      
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      return chartPanel;
    }
}
