package ua.nure.bj.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebListener
public class LogManager implements ServletContextListener {

	final static Logger logger = Logger.getLogger(LogManager.class
			.getSimpleName());

	public LogManager() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		Properties props = new Properties();
		InputStream is=getClass().getResourceAsStream("/config/log4j.properties");
		try {
			props.load(is);
			PropertyConfigurator.configure(props);
			logger.debug("logger in contextlistener started!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
