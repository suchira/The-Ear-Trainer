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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.*;
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

public class PerformanceGenerator{
    private ReportHandler reportHandler;
    
    private List<Integer> scalesTodayDetails = new ArrayList<Integer>();
    private List<Integer> notesTodayDetails = new ArrayList<Integer>();
    private List<Integer> intervalsTodayDetails = new ArrayList<Integer>();
    private List<Integer> chordsTodayDetails = new ArrayList<Integer>();
    
    private List<Integer> scalesMonthDetails = new ArrayList<Integer>();
    private List<Integer> notesMonthDetails = new ArrayList<Integer>();
    private List<Integer> intervalsMonthDetails = new ArrayList<Integer>();
    private List<Integer> chordsMonthDetails = new ArrayList<Integer>();
    
    private List<Integer> scalesOverallDetails = new ArrayList<Integer>();
    private List<Integer> notesOverallDetails = new ArrayList<Integer>();
    private List<Integer> intervalsOverallDetails = new ArrayList<Integer>();
    private List<Integer> chordsOverallDetails = new ArrayList<Integer>();
    
    
    
    public PerformanceGenerator(){
        reportHandler = new ReportHandler();
    }
    
    public void setMainMenuStat(){
        MainMenuJFrame menuJFrame = MainMenuJFrame.getMainMenuJFrame(User.getUser());
        
        List<Report> list = reportHandler.getDailyPerformance();
        for (Report report : list) {
            if(report.getCategory().equals("Chords")){
                menuJFrame.setChordsScore(report.getFinalScore());
            }
            
            if(report.getCategory().equals("Intervals")){
                menuJFrame.setIntervalsScore(report.getFinalScore());                
            }
            
            if(report.getCategory().equals("Scales") ){
                menuJFrame.setScalesScore(report.getFinalScore());                
            }
            
            if(report.getCategory().equals("Notes") ){
                menuJFrame.setNotesScore(report.getFinalScore());                
            }            
        }  
    }
    
    public JPanel creatDayPerformance(){
        List<Report> performanceList = reportHandler.getDailyPerformance();
        
        final String chords = "CHORDS";              
      final String intervals = "INTERVALS";              
      final String scales = "SCALES";              
      final String notes = "NOTES";              
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        for (Report report : performanceList) {
            if(report.getCategory().equals("Chords")){
                dataset.addValue(report.getFinalScore(), chords, "");
                chordsTodayDetails.add(report.getExTried());
                chordsTodayDetails.add(report.getExCorrect());
                chordsTodayDetails.add(report.getExFullMarks());
                chordsTodayDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Intervals")){
                dataset.addValue(report.getFinalScore(), intervals, "");
                intervalsTodayDetails.add(report.getExTried());
                intervalsTodayDetails.add(report.getExCorrect());
                intervalsTodayDetails.add(report.getExFullMarks());
                intervalsTodayDetails.add(report.getExMoreThanTwise());
                
            }
            
            if(report.getCategory().equals("Scales") ){
                dataset.addValue(report.getFinalScore(), scales, "");  
                scalesTodayDetails.add(report.getExTried());
                scalesTodayDetails.add(report.getExCorrect());
                scalesTodayDetails.add(report.getExFullMarks());
                scalesTodayDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Notes") ){
                dataset.addValue(report.getFinalScore(), notes, "");  
                notesTodayDetails.add(report.getExTried());
                notesTodayDetails.add(report.getExCorrect());
                notesTodayDetails.add(report.getExFullMarks());
                notesTodayDetails.add(report.getExMoreThanTwise());
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
      final String Intervals = "INTERVALS";              
      final String scales = "SCALES";              
      final String notes = "NOTES";              
      
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        for (Report report : performanceList) {
            if(report.getCategory().equals("Chords")){
                dataset.addValue(report.getFinalScore(), chords, "");
                chordsMonthDetails.add(report.getExTried());
                chordsMonthDetails.add(report.getExCorrect());
                chordsMonthDetails.add(report.getExFullMarks());
                chordsMonthDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Intervals")){
                dataset.addValue(report.getFinalScore(), Intervals, "");
                intervalsMonthDetails.add(report.getExTried());
                intervalsMonthDetails.add(report.getExCorrect());
                intervalsMonthDetails.add(report.getExFullMarks());
                intervalsMonthDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Scales") ){
                dataset.addValue(report.getFinalScore(), scales, "");
                scalesMonthDetails.add(report.getExTried());
                scalesMonthDetails.add(report.getExCorrect());
                scalesMonthDetails.add(report.getExFullMarks());
                scalesMonthDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Notes") ){
                dataset.addValue(report.getFinalScore(), notes, "");
                notesMonthDetails.add(report.getExTried());
                notesMonthDetails.add(report.getExCorrect());
                notesMonthDetails.add(report.getExFullMarks());
                notesMonthDetails.add(report.getExMoreThanTwise());
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
        final String intervals = "INTERVALS";
        final String scales = "SCALES";
        final String notes = "NOTES";                   
      
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 

        for (Report report : performanceList) {
            System.out.println(report.getCategory() + " & " + report.getFinalScore());
            if(report.getCategory().equals("Chords")){
                dataset.addValue(report.getFinalScore(), chords, "");
                chordsOverallDetails.add(report.getExTried());
                chordsOverallDetails.add(report.getExCorrect());
                chordsOverallDetails.add(report.getExFullMarks());
                chordsOverallDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Intervals")){
                dataset.addValue(report.getFinalScore(), intervals, "");  
                intervalsOverallDetails.add(report.getExTried());
                intervalsOverallDetails.add(report.getExCorrect());
                intervalsOverallDetails.add(report.getExFullMarks());
                intervalsOverallDetails.add(report.getExMoreThanTwise());
            }
            
            if(report.getCategory().equals("Scales") ){
                dataset.addValue(report.getFinalScore(), scales, "");
                scalesOverallDetails.add(report.getExTried());
                scalesOverallDetails.add(report.getExCorrect());
                scalesOverallDetails.add(report.getExFullMarks());
                scalesOverallDetails.add(report.getExMoreThanTwise());
            }
 
            if(report.getCategory().equals("Notes") ){
                dataset.addValue(report.getFinalScore(), notes, "");
                notesOverallDetails.add(report.getExTried());
                notesOverallDetails.add(report.getExCorrect());
                notesOverallDetails.add(report.getExFullMarks());
                notesOverallDetails.add(report.getExMoreThanTwise());
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
        final XYSeries intervals = new XYSeries( "INTERVALS" );          
        final XYSeries notes = new XYSeries( "NOTES" );          
        int i = 1, j = 1, k = 1, l = 1;
        
        for (Report report : reportsList) {
            if(report.getCategory().equals("Chords")){
                chords.add(i, report.getFinalScore());
                i++;
            }
            
            if(report.getCategory().equals("Intervals")){
                intervals.add(j, report.getFinalScore());
                j++;
            }
            
            if(report.getCategory().equals("Scales") ){
                scales.add(k, report.getFinalScore());
                k++;
            }
            
            if(report.getCategory().equals("Notes") ){
                notes.add(l, report.getFinalScore());
                l++;
            }
        }
        
               
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( scales );          
      dataset.addSeries( intervals );          
      dataset.addSeries( chords );
      dataset.addSeries( notes );
      
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
      renderer.setSeriesPaint( 3 , Color.BLUE );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.5f ) );
      renderer.setSeriesStroke( 3 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      
      ChartPanel chartPanel = new ChartPanel(xylineChart);
      return chartPanel;
    }

    public List<Integer> getScalesTodayDetails() {
        return scalesTodayDetails;
    }

    public List<Integer> getNotesTodayDetails() {
        return notesTodayDetails;
    }

    public List<Integer> getIntervalsTodayDetails() {
        return intervalsTodayDetails;
    }

    public List<Integer> getChordsTodayDetails() {
        return chordsTodayDetails;
    }

    public List<Integer> getScalesMonthDetails() {
        return scalesMonthDetails;
    }

    public List<Integer> getNotesMonthDetails() {
        return notesMonthDetails;
    }

    public List<Integer> getIntervalsMonthDetails() {
        return intervalsMonthDetails;
    }

    public List<Integer> getChordsMonthDetails() {
        return chordsMonthDetails;
    }

    public List<Integer> getScalesOverallDetails() {
        return scalesOverallDetails;
    }

    public List<Integer> getNotesOverallDetails() {
        return notesOverallDetails;
    }

    public List<Integer> getIntervalsOverallDetails() {
        return intervalsOverallDetails;
    }

    public List<Integer> getChordsOverallDetails() {
        return chordsOverallDetails;
    }
    
    
}
