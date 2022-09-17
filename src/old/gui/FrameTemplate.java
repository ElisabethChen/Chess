package old.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;


/**
* create an empty frame with the given scales if given. screenScale 
  is the scale btween the frame height and the screen height and frame 
  scale is the scale between the frame height and width.

  height = screen resulotion * screenScale
  width = height * frameScale
*/
public class FrameTemplate {
    protected JFrame frame;
    protected JPanel mainPanel;
    protected int frameH;   // NOTE: 648
    protected int frameW;   // NOTE: 972
    protected int panelH;
    protected int panelW;
    protected double screenScale;   // default: 0.9
    protected double frameScale;    // default: 1.5
    
    /**
    * create a empty frame
    * @param screenScale the scale btween the frame height and the screen height. 
                         range: (0, 1]
    * @param frameScale the scale between the frame height and width. 
    */    
    public FrameTemplate(double screenScale, double frameScale){
        this.screenScale = screenScale;
        this.frameScale = frameScale;
        
        calSize();
        frame = new JFrame();
        frame.setSize(frameW, frameH);

        mainPanel = new JPanel();
        frame.add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        panelH = mainPanel.getHeight();
        panelW = mainPanel.getWidth();
    }

    public FrameTemplate(){
        this(0.9, 1.5);
    }

    private void calSize() {    // TODO: ajust to screen size
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        frameH = (int) (screenScale * size.getHeight());
        frameW = (int) (((double) frameH) * frameScale);

        // if (width > size.getWidth()) {
        //     width = (int) size.getWidth();
        //     height = (int) ((double) width / frameScale); 
        // }
    } 

    public static void main(String[] args) {
        FrameTemplate ft = new FrameTemplate();
    }
}
