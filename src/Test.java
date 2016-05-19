import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import org.json.simple.parser.ParseException;

public class Test extends JPanel
                             implements ActionListener {
	private static final long serialVersionUID = 1L;
	static private final String newline = "\n";
    JButton openButton, saveButton;
    JTextArea log;
    JFileChooser fc;

    public Test() {
    	
        super(new BorderLayout());
        
        //Create the log first, because the action listeners
        //need to refer to it.
        log = new JTextArea(5,20);
        log.setMargin(new Insets(20,20,20,20));
        log.setEditable(false);
        Font font = log.getFont();
        float size = font.getSize() + 5.0f;
        log.setFont( font.deriveFont(size) );
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        fc = new JFileChooser();
        openButton = new JButton("Choose a JSON File...");
        openButton.addActionListener(this);


        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(Test.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.setText("");
                log.append("\n");
                log.append("Opening: " + file.getAbsolutePath() + "." + newline);
                log.append("\n");
            
                String PATH = file.getAbsolutePath();
        		JsonOp json = null;
        		try {
        			json = new JsonOp(PATH);
        		} catch (IOException | ParseException ex) {
        			log.append("LOL IT IS NOT A JSON FILE");
        		}
        		RepresentAsGraph graph = new RepresentAsGraph(json.getJobsList(),json.getConnections());
        		
        		String print =" ";
        		for(String s : json.getJobsList())
        			print +=s + " ";
        		log.append("---!HERE IS YOUR JOB LIST!---\n");
        		log.append(print);
        		log.append("\n");
        		log.append("\n");
        		log.append("---!HERE IS OUR CONNECTIONS!---\n");
        		for(ArrayList<Integer> i : json.getConnections()){
        			for(int k=i.size()-1; 0<=k; k--){
        				if(k%2 == 0)
        					log.append("job" + (i.get(k)+1) + "\n");
        				else
        					log.append("job" + (i.get(k)+1)   + " is dependent on ");
        			}
        		}
        		log.append("\n");
        		print=" ";
        		for(String s : graph.sortJobs())
        			print+=s +" ";
        		log.append("---!JOBS ARE SORTED!---\n");
        		log.append(print);
        		log.append("\n");
        		log.append("\n");
        		log.append("---!HERE IS OUR ADJACENCY MATRIX!---\n");
        		for (int i = 0; i < graph.getAdjacency().length; i++) {
        			log.append("job" + (i + 1) + " " + "{");
        			for (int j = 0; j < graph.getAdjacency()[i].length; j++) {
        				log.append(Boolean.toString(graph.getAdjacency()[i][j]));
        				if (j != graph.getAdjacency()[i].length - 1)
        					log.append(",");
        			}
        			log.append("}");
        			log.append("\n");
        			
        		}
            }

        } 
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Test.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("JobSort");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        //Add content to the window.
        frame.add(new Test());

        //Display the window.
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}
