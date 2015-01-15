/*
 * My Program using for only education 
 * CPE4199 The Senior Project
 * Producted by Mr.Thanthathon and Mr.Kittiphong from Thailand
 */
package com.seniorproject.thehand.main;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

/**
 *
 * @author RainWhileLoop
 */
public class App extends javax.swing.JFrame {

    Webcam webcam = Webcam.getDefault();

    public App() {
        initComponents();
        /* The available Dimension are : 
         (176, 144) 
         (320, 240) 
         (352, 288) 
         (480, 400) 
         (640, 480) 
         (1024, 768)
         */
        webcam.setViewSize(new Dimension(320, 240));
        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                webcam.open();
                while (isVisible()) {
                    final BufferedImage bufferedImage = webcam.getImage();
                    webCamRenderer1.setImage(bufferedImage);
                    edgeRenderer1.setImage(bufferedImage);
                    webCamRenderer1.repaint();
                    edgeRenderer1.repaint();

                    checkCurrentSliderState(hLowSlider, hHighSlider);
                    checkCurrentSliderState(sLowSlider, sHighSlider);
                    checkCurrentSliderState(vLowSlider, vHighSlider);

                    hueLow.setText(hLowSlider.getValue() + "");
                    hueHigh.setText(hHighSlider.getValue() + "");
                    saturateLow.setText(sLowSlider.getValue() + "");
                    saturateHigh.setText(sHighSlider.getValue() + "");
                    valueLow.setText(vLowSlider.getValue() + "");
                    valueHigh.setText(vHighSlider.getValue() + "");

                    hueLow.repaint();
                    hueHigh.repaint();
                    saturateLow.repaint();
                    saturateHigh.repaint();
                    valueLow.repaint();
                    valueHigh.repaint();
                    
                    if (isSliderChangeValue(hLowSlider) || isSliderChangeValue(sLowSlider) || isSliderChangeValue(vLowSlider)) {
                        edgeRenderer1.setLowThreshold(hLowSlider.getValue(), sLowSlider.getValue(), vLowSlider.getValue());
                    }
                    if (isSliderChangeValue(hHighSlider) || isSliderChangeValue(sHighSlider) || isSliderChangeValue(vHighSlider)) {
                        edgeRenderer1.setHighThreshold(hHighSlider.getValue(), sHighSlider.getValue(), vHighSlider.getValue());
                    }
//                    if (isSliderChangeValue(hLowSlider)) {
//                        int value
//                        checkCurrentSliderState(hLowSlider,hHighSlider);
//                        edgeRenderer1.setLowThreshold(hLowSlider.getValue());
//                        hueLow.setText(hLowSlider.getValue() + "");
//
//                    }
//                    if (hHighSlider.getValueIsAdjusting()) {
//                        if (hLowSlider.getValue() > hHighSlider.getValue()) {
//                            hLowSlider.setValue(hHighSlider.getValue());
//                        }
//                        edgeRenderer1.setHighThreshold(hHighSlider.getValue());
//                        hueHigh.setText(hHighSlider.getValue() + "");
//                    }
//                    if (sLowSlider.getValueIsAdjusting()) {
//                        if (sLowSlider.getValue() > sHighSlider.getValue()) {
//                            sHighSlider.setValue(sLowSlider.getValue());
//                        }
//                        edgeRenderer1.setLowThreshold(sLowSlider.getValue());
//                        hueLow.setText(sLowSlider.getValue() + "");
//                        
//                    }
//                    if (sHighSlider.getValueIsAdjusting()) {
//                        if (sLowSlider.getValue() > sHighSlider.getValue()) {
//                            sLowSlider.setValue(sHighSlider.getValue());
//                        }
//                        edgeRenderer1.setHighThreshold(sHighSlider.getValue());
//                        hueHigh.setText(sHighSlider.getValue() + "");
//                    }

                }
                return null;
            }

            @Override
            protected void done() {
                super.done();
            }
        };

        worker.execute();

    }

    private boolean isSliderChangeValue(JSlider slider) {
        return slider.getValueIsAdjusting();
    }

    private void checkCurrentSliderState(JSlider lowSlider, JSlider highSlider) {
        int value;
        if (isSliderChangeValue(lowSlider)) {
            if ((value = lowSlider.getValue()) > highSlider.getValue()) {
                highSlider.setValue(value);
            }
        }
        if (isSliderChangeValue(highSlider)) {
            if ((value = highSlider.getValue()) < lowSlider.getValue()) {
                lowSlider.setValue(value);
            }
        }

    }

    private boolean isValueSliderWrongState() {
        return vLowSlider.getValue() > vHighSlider.getValue();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        webCamRenderer1 = new com.seniorproject.thehand.main.WebCamRenderer();
        jPanel3 = new javax.swing.JPanel();
        edgeRenderer1 = new com.seniorproject.thehand.main.EdgeRenderer();
        huePanel = new javax.swing.JPanel();
        hueTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        hLowSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        hHighSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        hueHigh = new javax.swing.JLabel();
        hueLow = new javax.swing.JLabel();
        saturatePanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sLowSlider = new javax.swing.JSlider();
        jLabel11 = new javax.swing.JLabel();
        sHighSlider = new javax.swing.JSlider();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        saturateHigh = new javax.swing.JLabel();
        saturateLow = new javax.swing.JLabel();
        valuePanel = new javax.swing.JPanel();
        valueTxt = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        vLowSlider = new javax.swing.JSlider();
        jLabel43 = new javax.swing.JLabel();
        vHighSlider = new javax.swing.JSlider();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        valueHigh = new javax.swing.JLabel();
        valueLow = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The Hand");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusCycleRoot(false);
        setPreferredSize(new java.awt.Dimension(700, 700));
        setResizable(false);

        webCamRenderer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        webCamRenderer1.setText("webCamRenderer1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(webCamRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(webCamRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(345, 265));

        edgeRenderer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        edgeRenderer1.setText("edgeRenderer1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edgeRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edgeRenderer1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        hueTxt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        hueTxt.setText("Hue");

        jLabel1.setText("Low");

        hLowSlider.setMaximum(360);
        hLowSlider.setValue(0);
        hLowSlider.setAlignmentX(1.0F);
        hLowSlider.setAlignmentY(0.0F);

        jLabel2.setText("High");

        hHighSlider.setMaximum(360);
        hHighSlider.setValue(360);
        hHighSlider.setAlignmentX(0.01F);
        hHighSlider.setAlignmentY(0.0F);

        jLabel3.setText("0");

        jLabel5.setText("180");

        jLabel4.setText("360");

        hueHigh.setText("0");

        hueLow.setText("0");

        javax.swing.GroupLayout huePanelLayout = new javax.swing.GroupLayout(huePanel);
        huePanel.setLayout(huePanelLayout);
        huePanelLayout.setHorizontalGroup(
            huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(huePanelLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(hueTxt)
                .addGap(18, 18, 18)
                .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, huePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hLowSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, huePanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(hHighSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(huePanelLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel4)))))
                .addGap(18, 18, 18)
                .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hueLow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hueHigh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        huePanelLayout.setVerticalGroup(
            huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(huePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(huePanelLayout.createSequentialGroup()
                            .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(hLowSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(hueLow))
                            .addGap(18, 18, 18)
                            .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(hHighSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addComponent(hueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hueHigh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(huePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        hHighSlider.getAccessibleContext().setAccessibleParent(jPanel2);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Saturate");

        jLabel10.setText("Low");

        sLowSlider.setValue(0);
        sLowSlider.setAlignmentX(1.0F);
        sLowSlider.setAlignmentY(0.0F);

        jLabel11.setText("High");

        sHighSlider.setValue(100);
        sHighSlider.setAlignmentX(0.01F);
        sHighSlider.setAlignmentY(0.0F);

        jLabel12.setText("0");

        jLabel13.setText("0.5");

        jLabel14.setText("1");

        saturateHigh.setText("0");

        saturateLow.setText("0");

        javax.swing.GroupLayout saturatePanelLayout = new javax.swing.GroupLayout(saturatePanel);
        saturatePanel.setLayout(saturatePanelLayout);
        saturatePanelLayout.setHorizontalGroup(
            saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saturatePanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saturatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sLowSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, saturatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sHighSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(saturatePanelLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel14)))))
                .addGap(18, 18, 18)
                .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(saturateLow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saturateHigh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        saturatePanelLayout.setVerticalGroup(
            saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(saturatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saturateHigh)
                    .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(saturatePanelLayout.createSequentialGroup()
                            .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sLowSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(saturateLow))
                            .addGap(18, 18, 18)
                            .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(sHighSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(saturatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        valueTxt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        valueTxt.setText("Value");

        jLabel42.setText("Low");

        vLowSlider.setValue(0);
        vLowSlider.setAlignmentX(1.0F);
        vLowSlider.setAlignmentY(0.0F);

        jLabel43.setText("High");

        vHighSlider.setValue(100);
        vHighSlider.setAlignmentX(0.01F);
        vHighSlider.setAlignmentY(0.0F);

        jLabel44.setText("0");

        jLabel45.setText("0.5");

        jLabel46.setText("1");

        valueHigh.setText("0");

        valueLow.setText("0");

        javax.swing.GroupLayout valuePanelLayout = new javax.swing.GroupLayout(valuePanel);
        valuePanel.setLayout(valuePanelLayout);
        valuePanelLayout.setHorizontalGroup(
            valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuePanelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(valueTxt)
                .addGap(18, 18, 18)
                .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, valuePanelLayout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vLowSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, valuePanelLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(vHighSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(valuePanelLayout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel45)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel46)))))
                .addGap(18, 18, 18)
                .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valueLow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueHigh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        valuePanelLayout.setVerticalGroup(
            valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(valuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(valuePanelLayout.createSequentialGroup()
                            .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(vLowSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel42)
                                .addComponent(valueLow))
                            .addGap(18, 18, 18)
                            .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(vHighSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel43)))
                        .addComponent(valueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(valueHigh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(valuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jLabel46)
                    .addComponent(jLabel45))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(huePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saturatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(huePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saturatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.seniorproject.thehand.main.EdgeRenderer edgeRenderer1;
    private javax.swing.JSlider hHighSlider;
    private javax.swing.JSlider hLowSlider;
    private javax.swing.JLabel hueHigh;
    private javax.swing.JLabel hueLow;
    private javax.swing.JPanel huePanel;
    private javax.swing.JLabel hueTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSlider sHighSlider;
    private javax.swing.JSlider sLowSlider;
    private javax.swing.JLabel saturateHigh;
    private javax.swing.JLabel saturateLow;
    private javax.swing.JPanel saturatePanel;
    private javax.swing.JSlider vHighSlider;
    private javax.swing.JSlider vLowSlider;
    private javax.swing.JLabel valueHigh;
    private javax.swing.JLabel valueLow;
    private javax.swing.JPanel valuePanel;
    private javax.swing.JLabel valueTxt;
    private com.seniorproject.thehand.main.WebCamRenderer webCamRenderer1;
    // End of variables declaration//GEN-END:variables
}
