package main.java.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;


/**
* create an empty frame with an empty panel in the given width and height. 
*/
public class FrameTemplate {
    protected JFrame frame;
    protected JPanel mainPanel;
    protected int frameH;
    protected int frameW;
    protected int panelH;
    protected int panelW;
    
    /**
    * create a frame with one empty panel with the given size
    * @param width the given panel width
    * @param height the given panel height 
    * @param title the title of the frame
    */    
    public FrameTemplate(int width, int height, String title){
        this.panelW = width;
        this.panelH = height;
        
        frame = new JFrame();
        frame.setTitle(title);
        frame.setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(width, height));
        mainPanel.setBackground(Color.CYAN);
        frame.add(mainPanel);
        frame.pack();

        frameW = frame.getWidth();
        frameH = frame.getHeight();
    }

    /**
    * create a frame with one empty panel with the given size
    * @param width the given panel width
    * @param height the given panel height 
    */    
    public FrameTemplate(int width, int height){
        this(width, height, null);
    }

    /**
    * display the chess game frame
    */
    public void display() {
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public int getFrameH() {
        return frameH;
    }

    public int getFrameW() {
        return frameW;
    }

    public int getPanelH() {
        return panelH;
    }

    public int getPanelW() {
        return panelW;
    }

    
}
