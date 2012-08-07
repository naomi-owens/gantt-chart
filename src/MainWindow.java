
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jfree.chart.*; 
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class MainWindow extends ApplicationFrame {
	private Map<String, List<String>> words;
	private JMenuBar mb = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem openMenuItem = new JMenuItem("Open");
	private File selectedFile;
	private FileImporter fi;

	/**
	 * Creates a new demo.
	 *
	 * @param title  the frame title.
	 */
	public MainWindow(final String title) {
		super(title);

		fileMenu.add(openMenuItem);
		mb.add(fileMenu);
		setJMenuBar(mb);

		//OPEN ACTION LISTENER
		openMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Create a FileChooser
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				int  a= fc.showOpenDialog(null);
				if(a==JFileChooser.APPROVE_OPTION) {
					selectedFile = fc.getSelectedFile();
					try {
						fi = new FileImporter(selectedFile);
						words = fi.getWords();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//This creates the dataset	
					final IntervalCategoryDataset dataset = createDataset();
					//Create the chart using the dataset
					final JFreeChart chart = createChart(dataset);
					// add the chart to a panel
					final ChartPanel chartPanel = new ChartPanel(chart);
					//set Size of chartPanel
					chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
					setContentPane(chartPanel);
			}
		});
		//END of OPEN ACTIONLISTENER
	}

	/**
	public IntervalCategoryDataset createDataset() {
	}

	/**
	private JFreeChart createChart(final IntervalCategoryDataset dataset) {
	}

	/**
	public static void main(String[] args) throws IOException {
		//Create a mainWindow
	}
}