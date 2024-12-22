package models.credit_cards.payment_systems;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public abstract class AbstractCard  {
    protected String firstName, familyName, email;
    protected String number;
    protected String expiryDate;
    protected String CCV;
    protected double balance;

    protected final String expiryDateFormat = "MM/yyyy";

    public AbstractCard(String firstName, String familyName, String email, String number, String expiryDate, String CCV, double balance) {
        this.setFirstName(firstName);
        this.setFamilyName(familyName);
        this.setEmail(email);
        this.setNumber(number);
        this.setExpiryDate(expiryDate);
        this.setCCV(CCV);
        this.setBalance(balance);
    }

    AbstractCard() {
        this.setFirstName("");
        this.setFamilyName("");
        this.setEmail("");
        this.setNumber("");
        this.setExpiryDate("");
        this.setCCV("");
        this.setBalance(0);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNumber() {
        return this.number;
    }

    public String getExpiryDate() {
        return this.expiryDate;
    }

    public String getCCV() {
        return this.CCV;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.isEmpty())
            throw new IllegalArgumentException(">! FirstName cannot be null or empty, [AbstractCard, setFirstName()].");

        this.firstName = firstName.toUpperCase().charAt(0) + firstName.substring(1).toLowerCase();
    }

    public void setFamilyName(String familyName) {
        if(familyName == null || familyName.isEmpty())
            throw new IllegalArgumentException(">! FamilyName cannot be null or empty, [AbstractCard, setFamilyName()].");

        this.familyName = familyName.toUpperCase().charAt(0) + familyName.substring(1).toLowerCase();
    }

    public void setEmail(String email) {
        if(email == null || email.isEmpty())
            throw new IllegalArgumentException(">! User's email cannot be empty, [User, setEmail()].");

        if(email.endsWith("@gmail.com") || email.endsWith("@abv.bg"))
            this.email = email.toLowerCase();

        else
            throw new IllegalArgumentException(">! Invalid email domain, [User, setEmail()].");
    }

    public void setNumber(String number) {
        if(number == null || number.isEmpty())
            throw new IllegalArgumentException(">! Number of credit card cannot be null or empty, [AbstractCard, setNumber()].");

        else if(number.length() != 16)
            throw new IllegalArgumentException(">! Invalid number length - must contain 16 digits, [AbstractCard, setNumber()].");

        boolean hasSymbol = false;
        for(char ch : number.toCharArray()) {
            if (!Character.isDigit(ch)) {
                hasSymbol = true;
                break;
            }
        }

        if(!hasSymbol)
            this.number = number;

        else
            throw new IllegalArgumentException(">! Invalid number format - must only contain digits, [AbstractCard, setNumber()].");
    }

    void setExpiryDate(String expiryDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(expiryDateFormat);
        YearMonth date = null;

        try {
            date = YearMonth.parse(expiryDate, formatter);
        } catch (DateTimeException e) {
            throw new DateTimeException(">! Invalid date format, [AbstractCard, setExpiryDate()].", e);
        }

        this.expiryDate = expiryDate;
    }

    void setCCV(String CCV) {
        if(CCV == null || CCV.isEmpty())
            throw new IllegalArgumentException(">! CCV cannot be null or empty, [AbstractCard, setCCV()].");

        else if(CCV.length() != 3)
            throw new IllegalArgumentException(">! Invalid CCV length - must contain 3 digits, [AbstractCard, setCCV()].");

        boolean hasSymbol = false;
        for(char ch : CCV.toCharArray()) {
            if(!Character.isDigit(ch)) {
                hasSymbol = true;
                break;
            }
        }

        if(!hasSymbol)
            this.CCV = CCV;

        else
            throw new IllegalArgumentException(">! Invalid CCV - must only contain digits, [AbstractCard, setCCV()].");
    }

    public void setBalance(double balance) {
        if(balance < 0)
            throw new IllegalArgumentException(">! Balance cannot be negative, [AbstractCard, setBalance()].");

        this.balance = balance;
    }
}