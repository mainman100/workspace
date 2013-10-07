package Testing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  public class mouse extends JFrame
  {
     private JPanel mousePanel; // panel in which mouse events will occur
     private JLabel statusBar; // label that displays event information

     // MouseTrackerFrame constructor sets up GUI and
     // registers mouse event handlers
     public mouse()
     {
        super( "Demonstrating Mouse Events" );

        mousePanel = new JPanel(); // create panel                      
        mousePanel.setBackground( Color.WHITE ); // set background color
        add( mousePanel, BorderLayout.CENTER ); // add panel to JFrame  

        statusBar = new JLabel( "Mouse outside JPanel" );           
        add( statusBar, BorderLayout.SOUTH ); // add label to JFrame

        // create and register listener for mouse and mouse motion events
        MouseHandler handler = new MouseHandler();                       
        mousePanel.addMouseListener( handler );                          
        mousePanel.addMouseMotionListener( handler );                    
     } // end MouseTrackerFrame constructor

     private class MouseHandler implements MouseListener,
        MouseMotionListener                              
     {
        // MouseListener event handlers
        // handle event when mouse released immediately after press
        public void mouseClicked( MouseEvent event )
        {
          statusBar.setText( String.format( "Clicked at [%d, %d]",
              event.getX(), event.getY() ) );
        } // end method mouseClicked

        // handle event when mouse pressed
       public void mousePressed( MouseEvent event )
        {
           statusBar.setText( String.format( "Pressed at [%d, %d]",
              event.getX(), event.getY() ) );
        } // end method mousePressed

        // handle event when mouse released after dragging
        public void mouseReleased( MouseEvent event )
        {
           statusBar.setText( String.format( "Released at [%d, %d]",
              event.getX(), event.getY() ) );
        } // end method mouseReleased

        // handle event when mouse enters area
        public void mouseEntered( MouseEvent event )
        {
           statusBar.setText( String.format( "Mouse entered at [%d, %d]",              event.getX(), event.getY() ) );
           mousePanel.setBackground( Color.GREEN );
        } // end method mouseEntered

        // handle event when mouse exits area
        public void mouseExited( MouseEvent event )
        {
           statusBar.setText( "Mouse outside JPanel" );
           mousePanel.setBackground( Color.WHITE );      
           } // end method mouseExited
        public void mouseDragged( MouseEvent event )
             {
                 statusBar.setText( String.format( "Dragged at [%d, %d]",
                    event.getX(), event.getY() ) );
             }
       public void mouseMoved( MouseEvent event )
        {
           statusBar.setText( String.format( "Moved at [%d, %d]",
              event.getX(), event.getY() ) );
        } // end method mouseMoved
     } // end inner class MouseHandler
     public static void main(String[]args)
     {
    	 mouse m=new mouse();
    	 m.setSize(600,600);
    	 m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 m.setVisible(true);
     }
  }