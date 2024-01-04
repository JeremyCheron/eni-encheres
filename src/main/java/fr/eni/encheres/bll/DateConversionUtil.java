package fr.eni.encheres.bll;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConversionUtil {

    public static LocalDate convertInputDateToLocalDate(String inputDate) {
        // Formatter correspondant au format de votre inputDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Mettez le format correct

        // Conversion de la chaîne en LocalDate
        LocalDate localDate = LocalDate.parse(inputDate, formatter);

        return localDate;
    }
}