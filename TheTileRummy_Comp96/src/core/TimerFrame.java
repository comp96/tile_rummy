package core;

import core.Interfaces.TimerListner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.Timer;

/**
 *
 * @author Comp96
 */
public class TimerFrame extends javax.swing.JFrame {

    private Timer timer;
    private long startTime = -1;
    private long duration = 120000;
    //private long duration = 12000;
    private TimerListner listner;
    /**
     * Creates new form TimerFrame
     */
    public TimerFrame(TimerListner listner) {
        initComponents();
        setResizable(false);
        this.listner=listner;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    stop();
                    
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                jLabel_timer.setText(df.format(duration - clockTime));
            }
        });
        timer.setInitialDelay(2);
    }

    public void start() {
        if (!timer.isRunning()) {
            startTime = -1;
            timer.start();
        }

    }
    public void stop()
    {
         if (timer.isRunning()) {
             timer.stop();
             listner.timerEnd();
         }
    }
    public boolean isRunning(){
        return timer.isRunning();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_timer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setForeground(new java.awt.Color(204, 0, 0));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Timer");

        jLabel_timer.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel_timer.setForeground(new java.awt.Color(204, 0, 0));
        jLabel_timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_timer.setText("00000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_timer, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_timer, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(1100, 700, 320, 170);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_timer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
