package tgm.sew.roboterfabrik.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerFactory
{
    private ConsoleHandler consoleHandler;
    private FileHandler fileHandler;

    /**
     * @param filePatter pattern für den FileHandler
     */
    public LoggerFactory(String filePatter) throws IOException
    {   //Um in die Kommandozeile zu loggen
        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new RoboFormater());
        //Um in eine Datei zu loggen
        fileHandler = new FileHandler(filePatter);
        fileHandler.setFormatter(new RoboFormater());
    }

    /**
     * Erstellt einen Logger für eine angegebene Klasse
     *
     * @param clazz Klasse für die ein Logger erstellt werden soll
     * @return angeforderter Logger
     */
    public Logger getLogger(Class clazz)
    {
        //Logger mit dem Klassennamen erstellen
        Logger logger = Logger.getLogger(clazz.getName());
        //Neue Handler hinzufügen
        logger.addHandler(consoleHandler);
        logger.addHandler(fileHandler);

        logger.setUseParentHandlers(false);

        return logger;
    }
}
