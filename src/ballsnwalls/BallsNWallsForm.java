package ballsnwalls;

import java.awt.Color;
import javax.swing.*;


public class BallsNWallsForm extends javax.swing.JFrame {
    
    static final int WALL_DRAWING_MODE = 1, BILLIARDS_MODE = 2;
    WallDrawingCollisionSimulator wdcs;
    BilliardsSimulator bs;
    int simulatorMode;  //other choice is "BILLIARDS"
    boolean mouseDown;
    
    
    public BallsNWallsForm() {
        initComponents(); 
        wdcs = new WallDrawingCollisionSimulator( drawingPanel );
        bs = new BilliardsSimulator( this );
        mouseDown = false;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        drawingPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        startRandomDistributionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        numBallsText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        sizeSlider1 = new javax.swing.JSlider();
        speedSlider1 = new javax.swing.JSlider();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        startEpidemicButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numHealersText = new javax.swing.JTextField();
        numHealthyText = new javax.swing.JTextField();
        numSickText = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        startBilliardRackButton = new javax.swing.JButton();
        shootButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        startTwoSidedChargeButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        numBallsText2 = new javax.swing.JTextField();
        sizeSlider2 = new javax.swing.JSlider();
        jLabel9 = new javax.swing.JLabel();
        speedSlider2 = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        drawingPanel.setBackground(new java.awt.Color(153, 153, 153));
        drawingPanel.setPreferredSize(new java.awt.Dimension(700, 700));
        drawingPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mouseDraggedHandler(evt);
            }
        });
        drawingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mousePressedHandler(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseReleasedHandler(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseClickHandler(evt);
            }
        });

        javax.swing.GroupLayout drawingPanelLayout = new javax.swing.GroupLayout(drawingPanel);
        drawingPanel.setLayout(drawingPanelLayout);
        drawingPanelLayout.setHorizontalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        drawingPanelLayout.setVerticalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Random Distribution", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 0, 255))); // NOI18N

        startRandomDistributionButton.setText("Start");
        startRandomDistributionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startRandomDistributionButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Num balls");

        numBallsText.setText("2");
        numBallsText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numBallsTextActionPerformed(evt);
            }
        });

        jLabel2.setText("Max initial speed");

        jLabel10.setText("Ball size");

        speedSlider1.setMaximum(20);
        speedSlider1.setMinimum(2);
        speedSlider1.setValue(10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numBallsText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(startRandomDistributionButton))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sizeSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(numBallsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(sizeSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(startRandomDistributionButton))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(speedSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Custom Distribution", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 0, 255))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 59, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Epidemic Simulator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 0, 255))); // NOI18N

        startEpidemicButton.setText("Start");
        startEpidemicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startEpidemicButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Num healers");

        jLabel5.setText("Num healthy people");

        jLabel6.setText("Num sick people");

        numHealersText.setText("10");

        numHealthyText.setText("20");

        numSickText.setText("5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numSickText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numHealthyText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(startEpidemicButton)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numHealersText, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numHealersText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(numHealthyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(numSickText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startEpidemicButton))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Billiards Rack", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 0, 255))); // NOI18N

        startBilliardRackButton.setText("Set Up Rack");
        startBilliardRackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startBilliardRackButtonActionPerformed(evt);
            }
        });

        shootButton.setText("Shoot!");
        shootButton.setEnabled(false);
        shootButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shootButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startBilliardRackButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shootButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startBilliardRackButton)
                    .addComponent(shootButton)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Two Sided Charge", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(0, 0, 255))); // NOI18N

        startTwoSidedChargeButton.setText("Start");
        startTwoSidedChargeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTwoSidedChargeButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Num balls");

        jLabel8.setText("Speed");

        numBallsText2.setText("100");

        jLabel9.setText("Ball size");

        speedSlider2.setMaximum(12);
        speedSlider2.setMinimum(2);
        speedSlider2.setValue(7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numBallsText2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(startTwoSidedChargeButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sizeSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(speedSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(numBallsText2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(sizeSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(startTwoSidedChargeButton))
                    .addComponent(speedSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(drawingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(drawingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void animate( CollisionSimulator cs ) {
        cs.setWalls( this.drawingPanel );
        cs.animator = new Thread( cs );
        cs.animator.start();
        cs.run();
    }
    
    public int getRadius(int numBalls, JSlider js) {
        int w = drawingPanel.getWidth();
        
        int minRadius = (int)((0.8 * w)/numBalls);
        int maxRadius = 3 * w/numBalls;
        
        double sliderSetting = js.getValue();
        
        int radius = (int) (minRadius + sliderSetting/100 * (maxRadius-minRadius));
        if (radius > 150)
            radius = 150;
        
        return radius;
    }
    
    private void startTwoSidedChargeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTwoSidedChargeButtonActionPerformed
        
        simulatorMode = WALL_DRAWING_MODE;
        
        int numBalls = getTextFieldValue( numBallsText2 ) * 2; 
        
        if (numBalls > 250) {
            numBalls = 250;
            setTextFieldValue( numBallsText2, 250);           
        }
        
        int speed = speedSlider2.getValue();
        int radius = getRadius( numBalls, sizeSlider2 );
        
        wdcs.makeTwoSidedCharge( numBalls, radius,  speed );
        
        bs.animator = null;
        animate( wdcs );
    }//GEN-LAST:event_startTwoSidedChargeButtonActionPerformed

    private void startEpidemicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startEpidemicButtonActionPerformed
        simulatorMode = WALL_DRAWING_MODE;
        
        int numHealers = getTextFieldValue( numHealersText );
        int numHealthy = getTextFieldValue( numHealthyText );
        int numSick = getTextFieldValue( numSickText );
        int total = numHealers + numHealthy + numSick;
        int radius = this.drawingPanel.getWidth() / (1*total);
        
        wdcs.makeEpidemicSimulation(numHealers, numHealthy, numSick, 5, radius );
        
        bs.animator = null;
        animate(wdcs);
    }//GEN-LAST:event_startEpidemicButtonActionPerformed

    private void startBilliardRackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startBilliardRackButtonActionPerformed
        simulatorMode = BILLIARDS_MODE;
               
        bs.animator = null;
        wdcs.animator = null;
        
        bs.reset();
        bs.drawScreen();
    }//GEN-LAST:event_startBilliardRackButtonActionPerformed

    private void mouseClickHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClickHandler
    
        mouseDown = true;
        
        if (simulatorMode == BILLIARDS_MODE) {
            
            if( bs.mode == bs.PLACING_CUEBALL ) {
                bs.xCueBall = evt.getX();
                bs.yCueBall = evt.getY(); 
                bs.drawScreen();
            }
            
            else if( bs.mode == bs.AIMING_CUEBALL ) {
                bs.xAim = evt.getX();
                bs.yAim = evt.getY();
                bs.drawScreen();
            }
        }
        
        else if (simulatorMode == WALL_DRAWING_MODE) {
            wdcs.xWallStart = evt.getX();
            wdcs.yWallStart = evt.getY();
        }
    }//GEN-LAST:event_mouseClickHandler

    private void mouseDraggedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDraggedHandler
        
        mouseDown = true;
        
        if (simulatorMode == BILLIARDS_MODE) {
            
            if( bs.mode == bs.PLACING_CUEBALL ) {

                bs.xCueBall = evt.getX();
                bs.yCueBall = evt.getY();
                bs.balls[0].setPosition( bs.xCueBall, bs.yCueBall );
                bs.drawScreen();     
            }
            
            else if( bs.mode == bs.AIMING_CUEBALL ) {
                bs.xAim = evt.getX();
                bs.yAim = evt.getY();
                bs.setCueBallSpeed();
                bs.drawScreen();
             }
        }
           
        else if (simulatorMode == WALL_DRAWING_MODE) {
            wdcs.lineBeingDrawn = true; 
            wdcs.xWallEnd = evt.getX();
            wdcs.yWallEnd = evt.getY();
            
        }
    }//GEN-LAST:event_mouseDraggedHandler

    private void mouseReleasedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseReleasedHandler
        
        mouseDown = false;
        
        if (simulatorMode == WALL_DRAWING_MODE) {
        
            wdcs.lineBeingDrawn = false;
            wdcs.xWallEnd = evt.getX();
            wdcs.yWallEnd = evt.getY();    
            wdcs.walls.add( new Wall(wdcs.xWallStart, wdcs.yWallStart, wdcs.xWallEnd, wdcs.yWallEnd, 0, Color.white, 3, Integer.toString(wdcs.walls.size())));
         }
        
        else if ( simulatorMode == BILLIARDS_MODE ) {
            
            if( bs.mode == bs.PLACING_CUEBALL ) {
                bs.mode = bs.AIMING_CUEBALL;
            }
            
            else if( bs.mode == bs.AIMING_CUEBALL ) {
                bs.setCueBallSpeed();
                shootButton.setEnabled(true);
                bs.mode = bs.ANIMATING;
            }
        }
    }//GEN-LAST:event_mouseReleasedHandler

    private void mousePressedHandler(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mousePressedHandler
        
        mouseDown = true;
        
        if ( simulatorMode == BILLIARDS_MODE ) {
            
            if( bs.mode == bs.PLACING_CUEBALL ) {
                bs.xCueBall = evt.getX();
                bs.yCueBall = evt.getY(); 
                bs.drawScreen();
            }
            
            if( bs.mode == bs.AIMING_CUEBALL ) {
                bs.xAim = evt.getX();
                bs.yAim = evt.getY();
                bs.drawScreen();
            }
        }
        
        else if ( simulatorMode == WALL_DRAWING_MODE ) {
            wdcs.xWallStart = evt.getX();
            wdcs.yWallStart = evt.getY();
        } 
        
    }//GEN-LAST:event_mousePressedHandler

    private void startRandomDistributionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startRandomDistributionButtonActionPerformed
        
        simulatorMode = WALL_DRAWING_MODE;
        
        int numBalls = getTextFieldValue( numBallsText );
        
        if (numBalls > 500) {
            numBalls = 500;
            setTextFieldValue( numBallsText, 500);
        }
        int radius = getRadius( numBalls, sizeSlider1 ) / 2;
        int maxSpeed = speedSlider1.getValue();
        
        wdcs.makeRandomDistribution( numBalls, radius, maxSpeed );
        
        bs.animator = null;
        animate(wdcs);
    }//GEN-LAST:event_startRandomDistributionButtonActionPerformed

    private void numBallsTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numBallsTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numBallsTextActionPerformed

    private void shootButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shootButtonActionPerformed
        animate( bs );
        shootButton.setEnabled(false);
    }//GEN-LAST:event_shootButtonActionPerformed

    private int getTextFieldValue( JTextField tf ) {
        return Integer.parseInt(tf.getText());
    }

    private void setTextFieldValue( JTextField tf, int value ) {
        tf.setText( Integer.toString(value));
    }

    public static void main(String args[]) {
       
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
            java.util.logging.Logger.getLogger(BallsNWallsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BallsNWallsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BallsNWallsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BallsNWallsForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BallsNWallsForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    public javax.swing.JPanel drawingPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField numBallsText;
    private javax.swing.JTextField numBallsText2;
    private javax.swing.JTextField numHealersText;
    private javax.swing.JTextField numHealthyText;
    private javax.swing.JTextField numSickText;
    public javax.swing.JButton shootButton;
    public javax.swing.JSlider sizeSlider1;
    private javax.swing.JSlider sizeSlider2;
    public javax.swing.JSlider speedSlider1;
    private javax.swing.JSlider speedSlider2;
    private javax.swing.JButton startBilliardRackButton;
    private javax.swing.JButton startEpidemicButton;
    private javax.swing.JButton startRandomDistributionButton;
    private javax.swing.JButton startTwoSidedChargeButton;
    // End of variables declaration//GEN-END:variables
}
