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

    protected static String filePattern = "";

    /**
     * @param filePattern pattern für den FileHandler
     *
     * @throws IOException Problem beim erzeugen der Logdatei
     * @throws IllegalArgumentException FilePath wurde ungültig gesetzt
     */
    public LoggerFactory(String filePattern) throws IOException, IllegalArgumentException
    {
        if(filePattern == null || filePattern.equals("")) {
            throw new IllegalArgumentException("filePattern darf nicht \"\" oder null sein");
        }
        //Um in die Kommandozeile zu loggen
        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new RoboFormater());
        //Um in eine Datei zu loggen
        fileHandler = new FileHandler(filePattern);
        fileHandler.setFormatter(new RoboFormater());
    }

    /**
     * Verwendet den zuletzt verwendeten filePath, funktioniert nur wenn
     * bereits eine Instanz mit gültigen Pfad aufgerufen wurde.
     *
     * @throws IOException Problem beim erzeugen der Logdatei
     * @throws IllegalArgumentException FilePath wurde wohl noch nicht gesetzt/oder ungültig gesetzt
     */
    public LoggerFactory() throws IOException, IllegalArgumentException
    {
        this(LoggerFactory.filePattern);
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
