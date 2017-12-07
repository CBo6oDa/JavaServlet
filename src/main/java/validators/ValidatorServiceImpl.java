package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorServiceImpl implements ValidatorService {
    private static final String NAME_PATTERN = "([A-Z]([a-zA-Z]*(\\s?)))+";
    private static final String PRICE_PATTERN = "\\d{1,6}((\\.0)?(\\.\\d{2})?)";
    private static final String FLIGHTTIME_PATTERN = "\\d{1,3}";

    @Override
    public  boolean isValidId(int id) {
        return id >= 0;
    }

    @Override
    public boolean isValidName(String string) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher mather = pattern.matcher(string);
        return mather.matches();
    }

    @Override
    public boolean isValidPrice(Double string) {
        Pattern pattern = Pattern.compile(PRICE_PATTERN);
        Matcher matcher = pattern.matcher(Double.toString(string));
        return matcher.matches();
    }

    @Override
    public boolean isValidTimeInFlight(Integer string) {
        Pattern pattern = Pattern.compile(FLIGHTTIME_PATTERN);
        Matcher matcher = pattern.matcher(Integer.toString(string));
        return matcher.matches();
    }
}

