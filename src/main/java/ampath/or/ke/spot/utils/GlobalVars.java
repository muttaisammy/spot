package ampath.or.ke.spot.utils;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

public class GlobalVars {
    // create generateSecurePassword() method that find the secure password and returns it to the main() method
    public static String generateSecurePassword() {

        // create character rule for lower case
        CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);
        // set number of lower case characters
        LCR.setNumberOfCharacters(2);

        // create character rule for upper case
        CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);
        // set number of upper case characters
        UCR.setNumberOfCharacters(2);

        // create character rule for digit
        CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);
        // set number of digits
        DR.setNumberOfCharacters(2);

        // create character rule for lower case
        CharacterRule SR = new CharacterRule(EnglishCharacterData.Special);
        // set number of special characters
        SR.setNumberOfCharacters(2);

        // create instance of the PasswordGenerator class
        PasswordGenerator passGen = new PasswordGenerator();

        // call generatePassword() method of PasswordGenerator class to get Passay generated password
        String password = passGen.generatePassword(8, SR, LCR, UCR, DR);

        // return Passay generated password to the main() method
        return password;
    }
    public static String CurrMonth(int m){
        String charname=null;
        if(m==1){
            charname="January";
        }
        if(m==2){
            charname="February";
        }
        if(m==3){
            charname="March";
        }
        if(m==4){
            charname="April";
        }
        if(m==5){
            charname="May";
        }
        if(m==6){
            charname="June";
        }
        if(m==7){
            charname="July";
        }
        if(m==8){
            charname="August";
        }
        if(m==9){
            charname="September";
        }
        if(m==10){
            charname="October";
        }
        if(m==11){
            charname="November";
        }
        if(m==12){
            charname="December";
        }
        return charname;
    }
}
