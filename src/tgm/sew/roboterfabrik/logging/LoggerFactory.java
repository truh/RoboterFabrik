package tgm.sew.roboterfabrik.logging;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LoggerFactory
{
    private ConsoleHandler consoleHandler;

    private static String filePattern ="log/log.txt"; //Default logging path
    private static Map<Class<?>, Logger> loggerMapping = Collections.synchronizedMap(new HashMap<Class<?>, Logger>());

    private String actualDirectory;

    /**
     * @param filePattern Verzeichnis pattern für den FileHandler
     *
     * @throws IllegalArgumentException FilePath wurde ungültig gesetzt
     */
    public LoggerFactory(String filePattern) throws IllegalArgumentException
    {
        //weil ich mir nicht sicher ob ein || den zweiten ausdruck auswertet wenn  der erste true ist, falls ja könnte
        // es eine NPE geben
        if(filePattern == null ? true : filePattern.equals("")) {
            throw new IllegalArgumentException("filePattern darf nicht \"\" oder null sein");
        }
        //Um in die Kommandozeile zu loggen
        consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new RoboFormater());
    }

    /**
     * Verwendet den zuletzt verwendeten filePath, funktioniert nur wenn
     * bereits eine Instanz mit gültigen Pfad aufgerufen wurde.
     *
     * @throws IllegalArgumentException FilePath wurde wohl noch nicht gesetzt/oder ungültig gesetzt
     */
    public LoggerFactory() throws IllegalArgumentException
    {
        this(LoggerFactory.filePattern);
    }

    /**
     * Erzeugt einen Logger anhand eines Namens
     * Dient nur der inneren Logik
     * @param clazzName Name für den der Logger erstellt werden soll
     * @return Logger
     */
    private Logger getLogger(String clazzName)
    {
        //Logger mit dem Klassennamen erstellen
        Logger logger = Logger.getLogger(clazzName);
        //Neue Handler hinzufügen
        logger.addHandler(consoleHandler);

        //Um in eine Datei zu loggen
        FileHandler fileHandler = null;
        try
        {
            new File(filePattern).getParentFile().mkdirs();
            fileHandler = new FileHandler(filePattern);
            fileHandler.setFormatter(new RoboFormater());
        } catch (IOException e)
        {
            Logger.getLogger("LoggerFactory").warning("Loggen an diesen Filepfad nicht möglich");
        }

        if(fileHandler != null)
            logger.addHandler(fileHandler);

        logger.setUseParentHandlers(false);

        return logger;
    }

    /**
     * Erstellt einen Logger für eine angegebene Klasse
     *
     * @param clazz Klasse für die ein Logger erstellt werden soll
     * @return angeforderter Logger
     */
    public Logger getLogger(Class clazz)
    {   //Überprüfen ob der erfragte Logger existiert
        Logger logger = loggerMapping.get(clazz);
        if(logger != null) {
            return logger;
        }

        //logger cachen
        logger = getLogger(clazz.getName());
        loggerMapping.put(clazz, logger);
        return logger;
    }
}
