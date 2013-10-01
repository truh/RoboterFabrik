package tgm.sew.roboterfabrik.logging;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class RoboFormater
    extends Formatter
{
    /**
     * Format the given log record and return the formatted string.
     * <p/>
     * The resulting formatted String will normally include a
     * localized and formatted version of the LogRecord's message field.
     * It is recommended to use the {@link java.util.logging.Formatter#formatMessage}
     * convenience method to localize and format the message field.
     *
     * @param record the log record to be formatted.
     * @return the formatted log record
     */
    @Override
    public String format(LogRecord record)
    {   //Normalerweise hat eine Logger einen Namen wie "package.class" ich will nur die class ausgeben
        String [] lP = record.getLoggerName().split(".");
        String shortLoggerName = lP.length <= 0 ? "/" : record.getLoggerName().split(".")[lP.length -1];
        return "[" + record.getLevel() + "|" + shortLoggerName + "] " + record.getMessage() + System.getProperty("line.separator");
    }
}
