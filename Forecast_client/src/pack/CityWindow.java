package pack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JProgressBar;

public class CityWindow extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JPanel panel;
	private JPanel panel_1;
	public JLabel getLblCurrentlyTemperature() {
		return lblCurrentlyTemperature;
	}

	public static void setLblCurrentlyTemperatureText(String currentlyTemp) {
		lblCurrentlyTemperature.setText(currentlyTemp);
	}
	
	public static void setLblCurrentlyTemperatureValueText(String currentlyTemp) {
		lblCurrentlyValue.setText(currentlyTemp);
	}

	public JLabel getLblHourlyTemperature() {
		return lblHourlyTemperature;
	}

	public static void setLblHourlyTemperatureText(String hourlyTemperature) {
		lblHourlyTemperatureValue.setText(hourlyTemperature);
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	private JPanel detailsCityPanel;
	private JLabel lblCity;
	private JPanel currentlyPanel;
	private static JLabel lblCurrentlyTemperature;
	private JPanel hourlyPanel;
	private static JPanel imageWeatherPanel;
	private static JLabel lblHourlyTemperature;
	private static JLabel lblCurrentlyValue;
	private static JLabel lblHourlyTemperatureValue;
	private static JProgressBar progressBar;
	private static JLabel lblWeatherImage;

	/**
	 * Create the frame.
	 */
	public CityWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		progressBar = new JProgressBar();
		panel.add(progressBar);
		updateDateLabel();
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewCity = new JButton("ajouter une ville");
		panel_1.add(btnNewCity, BorderLayout.SOUTH);
		
		detailsCityPanel = new JPanel();
		panel_1.add(detailsCityPanel, BorderLayout.NORTH);
		detailsCityPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblCity = new JLabel("Nom Ville");
		detailsCityPanel.add(lblCity);
		
		currentlyPanel = new JPanel();
		detailsCityPanel.add(currentlyPanel);
		currentlyPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblCurrentlyTemperature = new JLabel("en ce moment");
		currentlyPanel.add(lblCurrentlyTemperature);
		
		lblCurrentlyValue = new JLabel("--°");
		currentlyPanel.add(lblCurrentlyValue);
		
		hourlyPanel = new JPanel();
		detailsCityPanel.add(hourlyPanel);
		hourlyPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblHourlyTemperature = new JLabel("dans l'heure");
		hourlyPanel.add(lblHourlyTemperature);
		
		lblHourlyTemperatureValue = new JLabel("--°");
		hourlyPanel.add(lblHourlyTemperatureValue);
		
		imageWeatherPanel = new JPanel();
		detailsCityPanel.add(imageWeatherPanel);
		imageWeatherPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblWeatherImage = new JLabel("");
		lblWeatherImage.setPreferredSize(new Dimension(100, 100));
		lblWeatherImage.setHorizontalTextPosition(JLabel.CENTER);
		imageWeatherPanel.add(lblWeatherImage);
		
		
	}
		
	public void setLblCity(String city)
	{
		this.lblCity.setText(city);
	}

	public void updateDateLabel()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
	}
	
	public JList getListCities()
	{
		return list;
	}
	
	public void updateListWithData(ArrayList<String> data)
	{
		
		list = new JList(data.toArray());
		contentPane.add(list, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void updateCityWeather(Forecast forecast)
	{
		setLblCurrentlyTemperatureValueText(""+Convert.getTempCelsius(forecast.getCurrently().getTemperature()));
		setLblHourlyTemperatureText(""+Convert.getTempCelsius(forecast.getHourly().getData().get(5).getTemperature()));
		try {
			this.updateWeatherImage(forecast.getCurrently().getIcon());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void launchProgressBar()
	{
		progressBar.setVisible(true);
	       progressBar.setIndeterminate(true);
	}
	public static void stopProgressBar()
	{
		progressBar.setVisible(false);
	}
	
	public void updateWeatherImage(String name) throws IOException
	{
		//System.out.println(name);
		
		BufferedImage buffImage = ImageIO.read(getClass().getResource("/pack/"+name+".png"));
		lblWeatherImage.setIcon(new ImageIcon(buffImage));

	}
	

}
