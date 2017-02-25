package pt.ipb.ejbclient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import pt.ipb.controller.*;
import pt.ipb.ejb.entity.Location;
import pt.ipb.ejb.entity.WeatherInfo;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class WeatherCentral {

	private JFrame frmWeatherCentral;
	private JTable weatherInfoTable;
	private ChartPanel chartPanel;
	private JPanel chartJPanel;
	
	private WeatherInfoController weatherInfoController;
	private LocationController locationController;
	
	private List<WeatherInfo> allWeatherInfo;
	private List<WeatherInfo> allWeatherInfoAscending;
	private List<Location> allLocations;
	
	private void updateWeatherTable(Object selectedItem) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Date");
		model.addColumn("Forecast");
        model.addColumn("Temperature");
        
        for (WeatherInfo info:allWeatherInfo) {
        	if (info.getLocation().getId() == ((Location)selectedItem).getId()) {
        		model.addRow(new String[]{info.getDatestring(), info.getForecast(), String.valueOf(info.getTemperature()) + " ºC"});
        	}
        }
        
        weatherInfoTable.setModel(model);
	}
	
	private void createWeatherChart(Object selectedItem) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		Location location = (Location)selectedItem;
		
		for (WeatherInfo info:allWeatherInfoAscending) {
        	if (info.getLocation().getId() == location.getId()) {
        		dataset.addValue(info.getTemperature(), "Temperature (ºC)", info.getDatestring());
        	}
        }
		
		if (dataset.getRowCount() != 0) {
			JFreeChart lineChart = ChartFactory.createLineChart(
			         "Weather Record for " + ((Location)selectedItem).getName(),
			         "Date","Temperature (ºC)",
			         dataset,
			         PlotOrientation.VERTICAL,
			         true,true,false);
			
			chartPanel = new ChartPanel(lineChart);
			
			chartJPanel = new JPanel();
			chartJPanel.setBounds(484, 137, 850, 518);
			frmWeatherCentral.getContentPane().add(chartJPanel);
			chartJPanel.setLayout(new java.awt.BorderLayout());
			chartJPanel.add(chartPanel, BorderLayout.CENTER);
			chartJPanel.validate();
		}	
	}
	
	private void updateWeatherChart(Object selectedItem) {
		Location location = (Location)selectedItem;
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	
    	for (WeatherInfo info:allWeatherInfoAscending) {
    		if (info.getLocation().getId() == location.getId()) {
        		dataset.addValue(info.getTemperature(), "Temperature (ºC)", info.getDatestring());
        	}
    	}
    	
    	if (dataset.getRowCount() != 0) {
    		JFreeChart lineChart = ChartFactory.createLineChart(
			         "Weather Record for " + ((Location)selectedItem).getName(),
			         "Date","Temperature (ºC)",
			         dataset,
			         PlotOrientation.VERTICAL,
			         true,true,false);
    		
    		chartPanel.setChart(lineChart);
    	}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherCentral window = new WeatherCentral();
					window.frmWeatherCentral.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WeatherCentral() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		locationController = new LocationController();
		allLocations = locationController.getLocations();
		
		weatherInfoController = new WeatherInfoController();
		allWeatherInfo = weatherInfoController.getAllWeatherInfo();
		allWeatherInfoAscending = weatherInfoController.getAllWeatherInfoAscending();
		
		frmWeatherCentral = new JFrame();
		frmWeatherCentral.setTitle("Weather Central");
		frmWeatherCentral.setBounds(100, 100, 1360, 705);
		frmWeatherCentral.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWeatherCentral.getContentPane().setLayout(null);
		
		JLabel placeSelectionLabel = new JLabel("Select a location:");
		placeSelectionLabel.setBounds(546, 93, 97, 20);
		placeSelectionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		frmWeatherCentral.getContentPane().add(placeSelectionLabel);
		
		JLabel appNameLabel = new JLabel("Weather Central Client");
		appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		appNameLabel.setBounds(10, 11, 1324, 37);
		appNameLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frmWeatherCentral.getContentPane().add(appNameLabel);
		
		JComboBox placeComboBox = new JComboBox();
		placeComboBox.setBounds(653, 91, 158, 25);
		placeComboBox.setModel(new DefaultComboBoxModel(allLocations.toArray()));
		frmWeatherCentral.getContentPane().add(placeComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 137, 464, 518);
		frmWeatherCentral.getContentPane().add(scrollPane);
		
		weatherInfoTable = new JTable();
		weatherInfoTable.setRowSelectionAllowed(false);
		scrollPane.setViewportView(weatherInfoTable);
		
		createWeatherChart(placeComboBox.getSelectedItem());
		updateWeatherTable(placeComboBox.getSelectedItem());
		
		placeComboBox.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	            updateWeatherTable(placeComboBox.getSelectedItem());
	            updateWeatherChart(placeComboBox.getSelectedItem());
	        }
	    });
	}
}
