package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import main.MainDocument;
import main.URLPath;

/**
 *
 * @author Noemi Guzman
 */
public final class BrowserFrame extends JFrame implements ActionListener {
    
    private final JFrame frame; 
    private final URLPath urlPath;    
    private JTextField textField;   
    private JLabel urlLabel;
    private JPanel mainHTML;
    private JPanel URLpanel;
    
    private MainDocument mainDocument ;
             
    public BrowserFrame(){ 
        frame = new JFrame("Browser");
        urlPath= new URLPath();      
        displayBrowser();       
    }
    
    private void displayBrowser(){        
        textField = new JTextField(100);
        textField.addActionListener(this);
        textField.setBounds(40, 0, 500, 25);
        textField.setText("index.html");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);    
                      
        urlLabel = new JLabel("URL:"); 
        urlLabel.setBounds(0, 0, 55, 25);
        mainHTML = new JPanel();
        mainHTML.setLayout(null);
        mainHTML.setBounds(0,200, 600, 600);
        
        URLpanel = new JPanel();
        URLpanel.add(urlLabel);
        URLpanel.add(textField);        
        URLpanel.setLayout(null);
        URLpanel.setBounds(0,0, 600, 30);        

        frame.add(URLpanel);  
        frame.add(mainHTML);       
        
        frame.setVisible(true);   
        frame.setResizable(false);     
    }
   
    @Override
    public void actionPerformed(ActionEvent evt) {
        String textPath = textField.getText();    
        mainHTML.removeAll(); 
        textField.selectAll();
        textPath = urlPath.setPath(textPath);
        if (urlPath.isPathValid(textPath)){         
            try {
                readHTMLFile(textPath);
            } catch (IOException ex) {
                Logger.getLogger(BrowserFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {  urlPath.displayErrorMsn(textPath);}
    }
    
    private void readHTMLFile(String textPath) throws IOException {
        boolean externalFile = false;
        mainDocument = new MainDocument(textPath, externalFile);      
        JPanel bFrame = mainDocument.showPage();       
        
        mainHTML.add(bFrame); 
        frame.revalidate();
        frame.repaint();
    }   
}
