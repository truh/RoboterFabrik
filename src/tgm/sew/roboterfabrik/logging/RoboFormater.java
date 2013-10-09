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
        String loggerName = record.getLoggerName();
        String [] lP = loggerName.split("\\.");
        String shortLoggerName = lP.length <= 0 ? "/" : lP[lP.length -1];

        //StringBuilder zum zusammenstellen der Nachricht
        StringBuilder sb = new StringBuilder();

        //Prefix der Nachricht wird hinzugefuegt
        sb.append("[" + record.getLevel() + "|" + shortLoggerName + "] " + record.getMessage());
        sb.append("; ");

        //moegliche Parameter anhängen
        if(record.getParameters() != null)
            for(Object obj : record.getParameters()) {
                sb.append(obj);
                sb.append("; ");
            }

        //moegliche Exceptions ausgeben
        if(record.getThrown() != null){
            sb.append(record.getThrown().getStackTrace().toString());
            sb.append("; ");
        }

        return sb.toString() + System.getProperty("line.separator");
    }
}
