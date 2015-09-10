/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package the.ear.trainer;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Suchira
 */
public class PerformanceJFrame extends javax.swing.JFrame {

    JPanel dayChartPanel1;
    JPanel monthChartPanel1;
    JPanel overallChartPanel1;
    JPanel scatterPlotChartPanel1;
    JPanel dayChartPanel2;
    JPanel monthChartPanel2;
    JPanel overallChartPanel2;
    JPanel scatterPlotChartPanel2;
    private PerformanceGenerator pGen;

    /**
     * Creates new form PerformanceJFrame
     */
    public PerformanceJFrame() {
        try {
            BufferedImage img = ImageIO.read(new File("images/background4.png"));
            this.setContentPane(new JLabel(new ImageIcon(img)));
        } catch (IOException ex) {
            Logger.getLogger(LoginJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();
        pGen = new PerformanceGenerator();
        dayChartPanel1 = pGen.creatDayPerformance();
        monthChartPanel1 = pGen.createMonthPerformance();
        overallChartPanel1 = pGen.createOverallPerformance();
        scatterPlotChartPanel1 = pGen.createScatterPlot();
        dayChartPanel2 = pGen.creatDayPerformance();
        monthChartPanel2 = pGen.createMonthPerformance();
        overallChartPanel2 = pGen.createOverallPerformance();
        
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        setPanel(dayChartPanel, dayChartPanel1);
        setPanel(monthChartPanel, monthChartPanel1);
        setPanel(overallChartPanel, overallChartPanel1);
        setPanel(scatterPlotChartPanel, scatterPlotChartPanel1);
        
        
        //Set the chartPanel in detailed TAB or initializing the second TAB
        setSecondTab(choiceComboBox.getSelectedItem().toString());
    }

    private void setSecondTab(String chartName){
        if(chartName.equals("Today's Performance")){
            setPanel(chartPanel, dayChartPanel2);
            setDetailsInPanel(pGen.getChordsTodayDetails(), chordsPanel);
            setDetailsInPanel(pGen.getIntervalsTodayDetails(), intervalPanel);
            setDetailsInPanel(pGen.getNotesTodayDetails(), notesPanel);
            setDetailsInPanel(pGen.getScalesTodayDetails(), scalesPanel);
        }
        if(chartName.equals("This Month's Performance")){
            setPanel(chartPanel, monthChartPanel2);
            setDetailsInPanel(pGen.getChordsMonthDetails(), chordsPanel);
            setDetailsInPanel(pGen.getIntervalsMonthDetails(), intervalPanel);
            setDetailsInPanel(pGen.getNotesMonthDetails(), notesPanel);
            setDetailsInPanel(pGen.getScalesMonthDetails(), scalesPanel);
        }
        if(chartName.equals("Overall Performance")){
            setPanel(chartPanel, overallChartPanel2);
            setDetailsInPanel(pGen.getChordsOverallDetails(), chordsPanel);
            setDetailsInPanel(pGen.getIntervalsOverallDetails(), intervalPanel);
            setDetailsInPanel(pGen.getNotesOverallDetails(), notesPanel);
            setDetailsInPanel(pGen.getScalesOverallDetails(), scalesPanel);
        }
    }
    
    private void setDetailsInPanel(List<Integer> list, JPanel panel){
        triedLabel.setText("0");
            passedLabel.setText("0");
            fullMarksLabel.setText("0");
            moreThanTwiseLabel.setText("0");
            triedLabelNotes.setText("0");
            passedLabelNotes.setText("0");
            fullmarksLabelNotes.setText("0");
            moreThanTwiseLabelNotes.setText("0");
            triedLabelIntervals.setText("0");
            passedLabelIntervals.setText("0");
            fullmarksLabelIntervals.setText("0");
            moreThanTwiseLabelIntervals.setText("0");
            triedLabelScales.setText("0");
            passedLabelScales.setText("0");
            fullmarksLabelScales.setText("0");
            moreThanTwiseLabelScales.setText("0");
            
            
        if(panel.equals(chordsPanel)){
            triedLabel.setText("0");
            passedLabel.setText("0");
            fullMarksLabel.setText("0");
            moreThanTwiseLabel.setText("0");
            triedLabel.setText(Integer.toString(list.get(0)));
            passedLabel.setText(Integer.toString(list.get(1)));
            fullMarksLabel.setText(Integer.toString(list.get(2)));
            moreThanTwiseLabel.setText(Integer.toString(list.get(3)));
        }
        if(panel.equals(notesPanel)){
            triedLabelNotes.setText("0");
            passedLabelNotes.setText("0");
            fullmarksLabelNotes.setText("0");
            moreThanTwiseLabelNotes.setText("0");
            triedLabelNotes.setText(Integer.toString(list.get(0)));
            passedLabelNotes.setText(Integer.toString(list.get(1)));
            fullmarksLabelNotes.setText(Integer.toString(list.get(2)));
            moreThanTwiseLabelNotes.setText(Integer.toString(list.get(3)));
        }
        if(panel.equals(intervalPanel)){
            triedLabelIntervals.setText("0");
            passedLabelIntervals.setText("0");
            fullmarksLabelIntervals.setText("0");
            moreThanTwiseLabelIntervals.setText("0");
            triedLabelIntervals.setText(Integer.toString(list.get(0)));
            passedLabelIntervals.setText(Integer.toString(list.get(1)));
            fullmarksLabelIntervals.setText(Integer.toString(list.get(2)));
            moreThanTwiseLabelIntervals.setText(Integer.toString(list.get(3)));
        }
        if(panel.equals(scalesPanel)){
            triedLabelScales.setText("0");
            passedLabelScales.setText("0");
            fullmarksLabelScales.setText("0");
            moreThanTwiseLabelScales.setText("0");
            triedLabelScales.setText(Integer.toString(list.get(0)));
            passedLabelScales.setText(Integer.toString(list.get(1)));
            fullmarksLabelScales.setText(Integer.toString(list.get(2)));
            moreThanTwiseLabelScales.setText(Integer.toString(list.get(3)));
        }
    }
    
    public void setPanel(JPanel jPanel1, JPanel jPanel2){
        jPanel1.setLayout(new BorderLayout());
        jPanel1.removeAll();
        jPanel1.add(jPanel2, BorderLayout.CENTER);
        jPanel1.repaint();
        this.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basePanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        monthChartPanel = new javax.swing.JPanel();
        dayChartPanel = new javax.swing.JPanel();
        overallChartPanel = new javax.swing.JPanel();
        scatterPlotChartPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        chordsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        triedLabel = new javax.swing.JLabel();
        passedLabel = new javax.swing.JLabel();
        fullMarksLabel = new javax.swing.JLabel();
        moreThanTwiseLabel = new javax.swing.JLabel();
        notesPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        triedLabelNotes = new javax.swing.JLabel();
        passedLabelNotes = new javax.swing.JLabel();
        fullmarksLabelNotes = new javax.swing.JLabel();
        moreThanTwiseLabelNotes = new javax.swing.JLabel();
        intervalPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        triedLabelIntervals = new javax.swing.JLabel();
        passedLabelIntervals = new javax.swing.JLabel();
        fullmarksLabelIntervals = new javax.swing.JLabel();
        moreThanTwiseLabelIntervals = new javax.swing.JLabel();
        scalesPanel = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        triedLabelScales = new javax.swing.JLabel();
        passedLabelScales = new javax.swing.JLabel();
        fullmarksLabelScales = new javax.swing.JLabel();
        moreThanTwiseLabelScales = new javax.swing.JLabel();
        choiceComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        basePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        basePanel.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.setOpaque(false);

        monthChartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        monthChartPanel.setOpaque(false);

        javax.swing.GroupLayout monthChartPanelLayout = new javax.swing.GroupLayout(monthChartPanel);
        monthChartPanel.setLayout(monthChartPanelLayout);
        monthChartPanelLayout.setHorizontalGroup(
            monthChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        monthChartPanelLayout.setVerticalGroup(
            monthChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        dayChartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dayChartPanel.setOpaque(false);

        javax.swing.GroupLayout dayChartPanelLayout = new javax.swing.GroupLayout(dayChartPanel);
        dayChartPanel.setLayout(dayChartPanelLayout);
        dayChartPanelLayout.setHorizontalGroup(
            dayChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dayChartPanelLayout.setVerticalGroup(
            dayChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        overallChartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        overallChartPanel.setOpaque(false);

        javax.swing.GroupLayout overallChartPanelLayout = new javax.swing.GroupLayout(overallChartPanel);
        overallChartPanel.setLayout(overallChartPanelLayout);
        overallChartPanelLayout.setHorizontalGroup(
            overallChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
        overallChartPanelLayout.setVerticalGroup(
            overallChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        scatterPlotChartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scatterPlotChartPanel.setOpaque(false);

        javax.swing.GroupLayout scatterPlotChartPanelLayout = new javax.swing.GroupLayout(scatterPlotChartPanel);
        scatterPlotChartPanel.setLayout(scatterPlotChartPanelLayout);
        scatterPlotChartPanelLayout.setHorizontalGroup(
            scatterPlotChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        scatterPlotChartPanelLayout.setVerticalGroup(
            scatterPlotChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(overallChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dayChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scatterPlotChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dayChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(overallChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scatterPlotChartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Overall Performance", jPanel1);

        jPanel3.setOpaque(false);

        chartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        chartPanel.setOpaque(false);

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );

        scrollPane.setOpaque(false);

        jPanel6.setOpaque(false);

        chordsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("CHORDS"));

        jLabel1.setText("Number of exercises tried");

        jLabel2.setText("Number of exercises that you have got full marks");

        jLabel3.setText("Number of exercises you have passed");

        jLabel4.setText("Number of exercises that you have tried more than twise to recognize the correct answer");

        triedLabel.setText("jLabel5");

        passedLabel.setText("jLabel5");

        fullMarksLabel.setText("jLabel5");

        moreThanTwiseLabel.setText("jLabel5");

        javax.swing.GroupLayout chordsPanelLayout = new javax.swing.GroupLayout(chordsPanel);
        chordsPanel.setLayout(chordsPanelLayout);
        chordsPanelLayout.setHorizontalGroup(
            chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chordsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chordsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(triedLabel))
                    .addGroup(chordsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(moreThanTwiseLabel))
                    .addGroup(chordsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passedLabel))
                    .addGroup(chordsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fullMarksLabel)))
                .addContainerGap())
        );
        chordsPanelLayout.setVerticalGroup(
            chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chordsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(triedLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passedLabel))
                .addGap(9, 9, 9)
                .addGroup(chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fullMarksLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                    .addComponent(moreThanTwiseLabel))
                .addContainerGap())
        );

        notesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("NOTES"));

        jLabel9.setText("Number of exercises tried");

        jLabel10.setText("Number of exercises that you have got full marks");

        jLabel11.setText("Number of exercises you have passed");

        jLabel12.setText("Number of exercises that you have tried more than twise to recognize the correct answer");

        triedLabelNotes.setText("jLabel5");

        passedLabelNotes.setText("jLabel5");

        fullmarksLabelNotes.setText("jLabel5");

        moreThanTwiseLabelNotes.setText("jLabel5");

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(notesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(triedLabelNotes))
                    .addGroup(notesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(moreThanTwiseLabelNotes))
                    .addGroup(notesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passedLabelNotes))
                    .addGroup(notesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fullmarksLabelNotes)))
                .addContainerGap())
        );
        notesPanelLayout.setVerticalGroup(
            notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(triedLabelNotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(passedLabelNotes))
                .addGap(9, 9, 9)
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(fullmarksLabelNotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                    .addComponent(moreThanTwiseLabelNotes))
                .addContainerGap())
        );

        intervalPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("INTERVALS"));

        jLabel17.setText("Number of exercises tried");

        jLabel18.setText("Number of exercises that you have got full marks");

        jLabel19.setText("Number of exercises you have passed");

        jLabel20.setText("Number of exercises that you have tried more than twise to recognize the correct answer");

        triedLabelIntervals.setText("jLabel5");

        passedLabelIntervals.setText("jLabel5");

        fullmarksLabelIntervals.setText("jLabel5");

        moreThanTwiseLabelIntervals.setText("jLabel5");

        javax.swing.GroupLayout intervalPanelLayout = new javax.swing.GroupLayout(intervalPanel);
        intervalPanel.setLayout(intervalPanelLayout);
        intervalPanelLayout.setHorizontalGroup(
            intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intervalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(intervalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(triedLabelIntervals))
                    .addGroup(intervalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(moreThanTwiseLabelIntervals))
                    .addGroup(intervalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passedLabelIntervals))
                    .addGroup(intervalPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fullmarksLabelIntervals)))
                .addContainerGap())
        );
        intervalPanelLayout.setVerticalGroup(
            intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intervalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(triedLabelIntervals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(passedLabelIntervals))
                .addGap(9, 9, 9)
                .addGroup(intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(fullmarksLabelIntervals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(intervalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                    .addComponent(moreThanTwiseLabelIntervals))
                .addContainerGap())
        );

        scalesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("SCALES"));

        jLabel25.setText("Number of exercises tried");

        jLabel26.setText("Number of exercises that you have got full marks");

        jLabel27.setText("Number of exercises you have passed");

        jLabel28.setText("Number of exercises that you have tried more than twise to recognize the correct answer");

        triedLabelScales.setText("jLabel5");

        passedLabelScales.setText("jLabel5");

        fullmarksLabelScales.setText("jLabel5");

        moreThanTwiseLabelScales.setText("jLabel5");

        javax.swing.GroupLayout scalesPanelLayout = new javax.swing.GroupLayout(scalesPanel);
        scalesPanel.setLayout(scalesPanelLayout);
        scalesPanelLayout.setHorizontalGroup(
            scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scalesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scalesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(triedLabelScales))
                    .addGroup(scalesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(moreThanTwiseLabelScales))
                    .addGroup(scalesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passedLabelScales))
                    .addGroup(scalesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fullmarksLabelScales)))
                .addContainerGap())
        );
        scalesPanelLayout.setVerticalGroup(
            scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scalesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(triedLabelScales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(passedLabelScales))
                .addGap(9, 9, 9)
                .addGroup(scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(fullmarksLabelScales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(scalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                    .addComponent(moreThanTwiseLabelScales))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chordsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(notesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(intervalPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scalesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(chordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(intervalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(notesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scalesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        scrollPane.setViewportView(jPanel6);

        choiceComboBox.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        choiceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Today's Performance", "This Month's Performance", "Overall Performance" }));
        choiceComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choiceComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(choiceComboBox, 0, 173, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(choiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Discriptive Analysis", jPanel3);

        javax.swing.GroupLayout basePanelLayout = new javax.swing.GroupLayout(basePanel);
        basePanel.setLayout(basePanelLayout);
        basePanelLayout.setHorizontalGroup(
            basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        basePanelLayout.setVerticalGroup(
            basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void choiceComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choiceComboBoxActionPerformed
        setSecondTab(choiceComboBox.getSelectedItem().toString());

    }//GEN-LAST:event_choiceComboBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PerformanceJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PerformanceJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PerformanceJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PerformanceJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PerformanceJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basePanel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JComboBox choiceComboBox;
    private javax.swing.JPanel chordsPanel;
    private javax.swing.JPanel dayChartPanel;
    private javax.swing.JLabel fullMarksLabel;
    private javax.swing.JLabel fullmarksLabelIntervals;
    private javax.swing.JLabel fullmarksLabelNotes;
    private javax.swing.JLabel fullmarksLabelScales;
    private javax.swing.JPanel intervalPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel monthChartPanel;
    private javax.swing.JLabel moreThanTwiseLabel;
    private javax.swing.JLabel moreThanTwiseLabelIntervals;
    private javax.swing.JLabel moreThanTwiseLabelNotes;
    private javax.swing.JLabel moreThanTwiseLabelScales;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JPanel overallChartPanel;
    private javax.swing.JLabel passedLabel;
    private javax.swing.JLabel passedLabelIntervals;
    private javax.swing.JLabel passedLabelNotes;
    private javax.swing.JLabel passedLabelScales;
    private javax.swing.JPanel scalesPanel;
    private javax.swing.JPanel scatterPlotChartPanel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel triedLabel;
    private javax.swing.JLabel triedLabelIntervals;
    private javax.swing.JLabel triedLabelNotes;
    private javax.swing.JLabel triedLabelScales;
    // End of variables declaration//GEN-END:variables
}
