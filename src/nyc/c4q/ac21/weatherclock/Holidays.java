package nyc.c4q.ac21.weatherclock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Holidays {
    final static int numCols = TerminalSize.getNumColumns();
    final static AnsiTerminal terminal = new AnsiTerminal();

    /**
     * Loads holidays from a file.
     *
     * @param holidayType
     *   The type of holiday.  Use "National holiday" for U.S. federal holidays.
     * @return
     *   A map from date to holiday name for holidays.
     */
    public static HashMap<Calendar, String> getHolidays(String holidayType) {
        ArrayList<String> lines = FileTools.readLinesFromFile("holidays.csv");
        HashMap<Calendar, String> holidays = new HashMap<Calendar, String>();
        for (String line : lines) {
            // Each line is of the form "date,name,type", where "date" is a date
            // in YYYY-MM-DD format, "name" is the holiday name, and "type" is
            // the holiday type.  Include only those lines where "type" matches
            // the 'holidayType' parameter.
            // FIXME: Write this.
            int comma1 = line.indexOf(',');
            String date = line.substring(0, comma1);
            int comma2 = line.indexOf(',', comma1 + 1);
            String name = line.substring(comma1 + 1, comma2);
            String type = line.substring(comma2 + 1);
            if (type.equals(holidayType)) {
                Calendar cal = DateTime.parseDate(date);
                holidays.put(cal, name);
            }
        }
        return holidays;
    }
    
    public static void printHolidays(HashMap<Calendar, String> holidays, Calendar cal){
        int yPosition = numCols / 2 - 11;
        String smiley = new String(new int[] {0x263A}, 0, 1);
        String holiday;

        if(holidays.containsKey(cal)) {
            holiday = "National Holiday:  " + holidays.get(cal);
        }
        else {
            holiday = smiley + " It's a normal day " + smiley;
        }

        terminal.setTextColor(AnsiTerminal.Color.BLUE);
        terminal.moveTo(14, yPosition + 2);
        terminal.write(holiday);
    }
}
