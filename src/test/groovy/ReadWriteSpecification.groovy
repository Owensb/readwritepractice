import spock.lang.Specification
import readwrite.Run

class ReadWriteSpecification extends Specification {

    def " a zipcode input that is <5 numbers long will be formatted correctly"() {
        given: "an invalid zipcode"
        def badZipcode = "1234toÿ"
        def csvReader = new Run()

        when: "we format it"
        def formattedZipcode = csvReader.formatZipcode(badZipcode)

        then: "it is correctly formatted"
        assert formattedZipcode == "01234"
    }


    def " the fullname is formatted correctly"() {
        given: "an invalid full name"
        def badFullName = "Roberttoÿ"
        def csvReader = new Run()

        when: "we format it"
        def formattedFullName = csvReader.formateFullName(badFullName)

        then: "it is correctly formatted"
        assert formattedFullName == "ROBERTTO?"
    }

    def " the address is formatted correctly"() {
        given: "an invalid address"
        def badAddress = "überTown"
        def csvReader = new Run()

        when: "we format it"
        def formattedAddress = csvReader.formatAddress(badAddress)

        then: "it is correctly formatted"
        assert formattedAddress == "?berTown"
    }

   //todo test formatDuration


    def " the timestamp is converted to the correct timezone"() {
        given: "an invalid time stamp"
        def badTimestamp = "4/1/11 11:00:00 AM"
        def csvReader = new Run()

        when: "we format it"
        def formattedTimestamp = csvReader.formatTimestamp(badTimestamp)

        then: "it is correctly formatted"
        assert formattedTimestamp == "2011-04-01T11:00"
    }
}