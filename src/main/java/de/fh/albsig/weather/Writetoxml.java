package de.fh.albsig.weather;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Writetoxml {

	private Document document;

	public void xmlOutput(Weather weather, final String path)
			throws IOException {

		this.document = DocumentHelper.createDocument();
		final Element root = this.document.addElement("weather");
		final Element data = root.addElement("data");

		data.addElement("City").addText("Current weather in:"+weather.getCity());
		data.addElement("Region").addText(weather.getRegion());
		data.addElement("Country").addText(weather.getCountry());
		data.addElement("Temperature").addText(weather.getTemp() + "°C");
		data.addElement("Condition").addText(weather.getCondition());
		data.addElement("Humidity").addText(weather.getHumidity() + "%");
		data.addElement("Chill").addText(weather.getChill() + "°C");

		Files.createFile(Paths.get(path + "Weather"+weather.getCity() + ".xml"));
		Files.write(Paths.get(path + "Weather"+weather.getCity() + ".xml"),
				this.document.asXML().getBytes(Charset.forName("UTF-8")),
				StandardOpenOption.TRUNCATE_EXISTING);

	}
}
