package readwrite;

public class Person {
    private final String timestamp;
    private final String address;
    private final String zip;
    private final String fooDuration;
    private final String barDuration;
    private final String totalDuration;
    private final String notes;
    private String fullName;

    Person(String timestamp, String address, String zip, String fullName, String fooDuration,
           String barDuration, String totalDuration, String notes) {
        this.timestamp = timestamp;
        this.address = address;
        this.zip = zip;
        this.fullName = fullName;
        this.fooDuration = fooDuration;
        this.barDuration = barDuration;
        this.totalDuration = totalDuration;
        this.notes = notes;
    }

    String getTimestamp() {
        return timestamp;
    }

    String getAddress() {
        return address;
    }

    String getZip() {
        return zip;
    }

    String getFullName() {
        return fullName;
    }

    String getFooDuration() {
        return fooDuration;
    }

    String getBarDuration() {
        return barDuration;
    }

    String getTotalDuration() {
        return totalDuration;
    }

    String getNotes() {
        return notes;
    }
}
