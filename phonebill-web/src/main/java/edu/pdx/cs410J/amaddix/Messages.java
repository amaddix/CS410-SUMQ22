package edu.pdx.cs410J.amaddix;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{

    /**
     * MISSING REQUIRED PARAMETER -
     * @param parameterName - PARAMETER PASSED TO DISPLAY
     * @return string
     */
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    /*
    public static String getMappingCount( int count )
    {
        return String.format( "Server contains %d key/value pairs", count );
    }

    public static String formatKeyValuePair( String key, String value )
    {
        return String.format("  %s -> %s", key, value);
    }

    public static String mappedKeyValue( String key, String value )
    {
        return String.format( "Mapped %s to %s", key, value );
    }
*/
    /*
    public static String printBill(PhoneBill tbill){
        String customer =tbill.getCustomer();
        int number = tbill.getCallNum();
        String holder = "";
        for(int i=0; i<number;i++) {
            String caller =tbill.getCaller(i);
            String callee =tbill.getCallee(i);
            String start =tbill.getStartTime(i);
            String end =tbill.getEndTime(i);
            holder = String.format(holder,"%s\n%s\n%s\n%s\n%s\n", customer, caller, callee, start, end);
        }
        return holder;
    } */
/*
    public static String printCall(PhoneCall tcall){
        String customer =tcall.getCustomer();
       // int number = t.getCallNum();

            String caller =tcall.getCaller();
            String callee =tcall.getCallee();
            String start =tcall.getBeginTimeString();
            String end = tcall.getEndTimeString();
            String holder = String.format(" Phone Call of : %s \n %s -> %s \n %s - %s \n", customer, caller, callee, start, end);
        return holder;
    }
*/

    /**
     * DEFINEDCALL - displays a phone call contents
     * @param customer -string customer name
     * @param caller - string phone number
     * @param callee- string phone number
     * @param start -string start date
     * @param end-string end date
     * @return -string with message
     */
    public static String definedCall(String customer, String caller, String callee, String start, String end){
        return String.format("%s\n%s\n%s\n%s\n%s\n", customer, caller, callee, start, end);
    }

/*
    public static String definedWordAs(String word, String definition )
    {
        return String.format( "Defined %s as %s", word, definition );
    }

    public static String allDictionaryEntriesDeleted() {
        return "All dictionary entries have been deleted";
    }
*/
}
