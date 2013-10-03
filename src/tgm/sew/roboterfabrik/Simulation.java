package tgm.sew.roboterfabrik;

import org.apache.commons.cli.*;
import tgm.sew.roboterfabrik.logging.LoggerFactory;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Simulation {
    public static LoggerFactory loggerFactory;
	public static void main(String [] args) {
        try
        {
            loggerFactory = new LoggerFactory("log.txt"); //todo CLI eingabe als Pfad wählen
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Logger log = loggerFactory.getLogger(Simulation.class);

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

        String lagerPfad = cmd.getOptionValue("lager");
        String logsPfad = cmd.getOptionValue("logs");
        int numLieferanten = Integer.parseInt(cmd.getOptionValue("lieferanten"));
        int numMonteure = Integer.parseInt(cmd.getOptionValue("monteure"));
        int laufzeit = Integer.parseInt(cmd.getOptionValue("laufzeit"));

        Fabrik fabrik = new Fabrik(laufzeit, numLieferanten, numMonteure, lagerPfad);
    }
}
