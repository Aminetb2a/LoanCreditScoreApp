package patika.dev.definex.loancreditscore.service.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateFormatter {
    /**
     * Method that formats localDate to Date with a iso date time format.
     *
     * @param date date
     * @return formatted date
     * @throws ParseException
     */
    public Date getFormattedDate(LocalDate date) throws ParseException {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return isoFormat.parse(date.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

}
