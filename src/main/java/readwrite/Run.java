package readwrite;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Run {

    public static void main(String[] args) throws IOException {
        String fileLocation =
                "src/main/java/readwrite/sample.csv";
        readWriteCSV(fileLocation);
    }

    public static void readWriteCSV(String fileLocation) throws FileNotFoundException, IOException {


        String datetime = LocalDate.now().toString();
        String fileOutputLocation = "src/main/java/readwrite/" + datetime + ".txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileOutputLocation));

             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withHeader( "Timestamp",
                             "Address",
                             "ZIP",
                             "FullName",
                             "FooDuration",
                             "BarDuration",
                             "TotalDuration",
                             "Notes"))
        ) {
            CSVParser parser = new CSVParser(new FileReader(fileLocation), CSVFormat.DEFAULT.withHeader());
            for (CSVRecord record : parser) {

                // TODO: UTF-8 character set
                // If a character is invalid, please replace it with the Unicode Replacement
                // Character. If that replacement makes data invalid (for example,
                // because it turns a date field into something unparseable), print a
                // warning to `stderr` and drop the row from your output.

                String formattedTimestamp = formatTimestamp(record.get("Timestamp"));
                String formattedAddress = formatAddress(record.get("Address"));
                String formattedFullName = formateFullName(record.get("FullName"));
                String formattedZipcode = formatZipcode(record.get("ZIP"));
                String formattedFooDuration = formatDuration(record.get("FooDuration"));
                String formattedBarDuration = formatDuration(record.get("BarDuration"));
                String totalDuration = formattedBarDuration + formattedFooDuration;
                String formattedNotes = record.get("Notes").replace("[^\\p{Print}]", "\uFFFD");

                Person person = new Person(formattedTimestamp,
                        formattedAddress,
                        formattedZipcode,
                        formattedFullName,
                        formattedFooDuration,
                        formattedBarDuration,
                        totalDuration,
                        formattedNotes);

                System.out.printf(
                        "%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s%n",
                        record.get("Timestamp"),
                        record.get("Address"),
                        record.get("ZIP"),
                        record.get("FullName"),
                        record.get("FooDuration"),
                        record.get("BarDuration"),
                        record.get("TotalDuration"),
                        record.get("Notes"));


                //TODO: don't like the all string entries. Can lead to problems with mismatched data
                csvPrinter.printRecord(person.getTimestamp(),
                        person.getAddress(),
                        person.getZip(),
                        person.getFullName(),
                        person.getFooDuration(),
                        person.getBarDuration(),
                        person.getTotalDuration(),
                        person.getNotes());
                csvPrinter.flush();

            }parser.close();
        }


    }

    public static String formateFullName(String full_name) {
        return full_name.replaceAll("[^\\p{Print}]", "?").toUpperCase();
    }

    private static String formatDuration(String duration) {
        String[] h1= duration.split(":");
        int hour = Integer.parseInt(h1[0]);
        int minute = Integer.parseInt(h1[1]);
        float second = Float.parseFloat(h1[2]); //includes milliseconds
        float totalSeconds = ((hour *60) + minute)*60 + second;

        return String.valueOf(totalSeconds);
    }

    public static String formatZipcode(String zip) {
        String digitsOnlyZip = zip.replaceAll("[^\\d]", "");

        //Todo: change the for loop to not a for loop?
        StringBuilder zipBuilder = new StringBuilder(digitsOnlyZip);

        if(digitsOnlyZip.length()<5){
            for(int n =0; n<=5-zipBuilder.length(); n++){
                zipBuilder.insert(0, "0");
            }
        }

        return zipBuilder.toString();
    }

    public static String formatAddress(String address) {
        return address.replaceAll("[^\\p{Print}]", "?");
    }

    public static String formatTimestamp(String csvDateTime) {
        // turn sting into dateTime, TODO: * The Timestamp column should be assumed to be in US/Pacific time please convert it to US/Eastern.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy h:mm:ss a");
        LocalDateTime convertedDateTime = LocalDateTime.parse(csvDateTime, formatter);

        //turn dateTime into ISO8401 TODO: get the seconds to follow through.
        return convertedDateTime.toString();
    }

    //TODO: write some tests for the

}
