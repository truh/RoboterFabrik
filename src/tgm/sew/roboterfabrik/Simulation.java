package tgm.sew.roboterfabrik;

import org.apache.commons.cli.*;
import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Mainklasse der RoboterFabrik
 */
public class Simulation {
    /**
     * @param args
     *      --lager Pfad zum Lager
     *      --logs Pfad zum Loggen
     *      --lieferanten Anzahl an Lieferanten
     *      --monteuere Anzahl an Montagemitarbeitern
     *      --laufzeit Laufzeit in Milliesekunden
     * @throws IOException
     */
	public static void main(String [] args) throws IOException
    {

        Logger log = Logger.getLogger("Simulation, initialisierung");

        Options options = new Options();
        options.addOption("la", "lager", true, "Pfad zum Lager");
        options.addOption("lo", "logs", true, "Pfad wo geloggt werden soll");
        options.addOption("li", "lieferanten", true, "Anzahl an Lieferanten");
        options.addOption("m", "monteure", true, "Anzahl an Monteuren");
        options.addOption("t", "laufzeit", true, "Laufzeit in Milliesekunden");

        CommandLineParser parser = new GnuParser();
        CommandLine cmd = null;
        try
        {
            cmd = parser.parse(options, args);
        } catch (ParseException e)
        {
            log.throwing("Simulation", "main", e);
            log.warning("Fehlerhafte Kommandozeilen Argumente");
        }
        log.info("RoboterFabrik gestartet mit Argumenten");
        log.info("lager:\t" + cmd.getOptionValue("lager"));
        log.info("logs:\t" + cmd.getOptionValue("logs"));
        log.info("lieferanten:\t" + cmd.getOptionValue("lieferanten"));
        log.info("monteure:\t" + cmd.getOptionValue("monteure"));
        log.info("laufzeit:\t" + cmd.getOptionValue("laufzeit"));

        //Dateipfade aus args auslesen und ueberpruefen
        String lagerPfad = cmd.getOptionValue("lager");
        String logsPfad = cmd.getOptionValue("logs");
        if(lagerPfad==null || logsPfad == null){
            showHelp();
            System.exit(0);
        }
        try {
            Paths.get(lagerPfad);
            Paths.get(logsPfad);
        } catch (InvalidPathException ipe) {
            showHelp();
            System.exit(0);
        }

        //zahlen aus den args auslesen und ueberpruefen
        int numLieferanten=0;
        int numMonteure=0;
        int laufzeit=0;
        try{
            numLieferanten = Integer.parseInt(cmd.getOptionValue("lieferanten"));
            numMonteure = Integer.parseInt(cmd.getOptionValue("monteure"));
            laufzeit = Integer.parseInt(cmd.getOptionValue("laufzeit"));
        } catch (NumberFormatException nfe) {
            showHelp();
            System.exit(0);
        }
        if(numLieferanten <= 0 || numMonteure <= 0 || laufzeit <= 0) {
            showHelp();
            System.exit(0);
        }

        LoggerFactory loggerFactory = new LoggerFactory(logsPfad + File.separator + "log.txt");

        log = loggerFactory.getLogger(Simulation.class);

        Fabrik fabrik = new Fabrik(laufzeit, numLieferanten, numMonteure, lagerPfad);
    }

    /**
     * Gibt eine Hilfestellung aus
     */
    public static void showHelp(){
        System.out.println("RoboterFabrik muss mit folgenden Argumenten gestartet werden");
        System.out.println("--lager <filepath> Pfad um Lagerdateien zu erstellen");
        System.out.println("--logs <filepath> Pfad um Logdateien zu erstellen");
        System.out.println("--lieferanten <Nummer > 0> Anzahl an Lieferanten");
        System.out.println("--monteure <Nummer > 0> Anzahl an MontageMitarbeitern");
        System.out.println("--laufzeit <Nummer > 0> Laufzeit in Milliesekunden");
    }
}
