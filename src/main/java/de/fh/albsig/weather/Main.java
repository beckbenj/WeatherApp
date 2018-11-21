package de.fh.albsig.weather;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

public class Main {

	public static void main(String[] args) throws Exception {
		// Configure Log4J
		PropertyConfigurator.configure(
				Main.class.getClassLoader().getResource("log4J.properties"));

		String city = "nome";
		String countrydefault = "ak";

		try {
			city = args[0];
			countrydefault = args[1];
		} catch (final Exception e) {
		}

		// Start the program
		try {
			new Main(city, countrydefault).start();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private String ci;
	private String co;

	public Main(String city, String country) {
		this.ci = city;
		this.co = country;
	}

	public void start() throws Exception {

		final Writetoxml xml = new Writetoxml();

		// Retrieve Data
		final InputStream dataIn = new YahooRetriever().retrieve(this.ci,
				this.co);

		// Parse Data
		final Weather weather = new YahooParser().parse(dataIn);

		// Formate (Print) Data
		System.out.print(new WeatherFormatter().format(weather));

		xml.xmlOutput(weather, "C:\\Users\\Ich\\Desktop\\Weather\\");
	}
}
