package framework;

/**
 *
 * @author Mamun
 */
enum Notification {

    FIRSTCHARINVALID("Use aplhabets in first position"),
    INVALIDINPUT("Use only Letters, numbers & '_'"),
    ONEWORD("No space can be used."),
    OK("Input is Successful"),
    ONLYALPHABETS("Enter only alphabets"),
    MUSTUSECHAR("Must use atleast one alphabet"),
    MUSTUSEINT("Input integer only"),
    ATAGAINFOUND("@ found several times. Unvalid Email address"),
    INVALIDEMAIL("Not a valid Email address"),
    MUSTUSEFLOAT("Input float only");
    public String msg;

    Notification(String s) {
        this.msg = s;
    }
}

public class InputValidation {

    public static Notification checkUserName(String s) {

        if ((s.charAt(0) <= 'a' || s.charAt(0) >= 'z') || (s.charAt(0) <= 'A' && s.charAt(0) >= 'Z')) {
            return Notification.FIRSTCHARINVALID;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                return Notification.ONEWORD;
            }
        }


        {
            boolean flag = false;
            for (int i = 0; i < s.length(); i++) {
                if (!((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                        || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                        || (s.charAt(i) == '_')
                        || (s.charAt(i) >= '0' && s.charAt(i) <= '9'))) {

                    flag = true;
                }

            }

            if (flag == false) {
                return Notification.OK;
            }
        }


        return Notification.INVALIDINPUT;
    }

    public static Notification checkName(String s) {


        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            if (!((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                    || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))) {

                flag = true;
            }

        }

        if (flag == false) {
            return Notification.OK;
        }

        return Notification.ONLYALPHABETS;

    }

    public static Notification checkPassword(String s) {
        boolean charFound = false;
        boolean numFound = false;
        boolean isChar = false;
        boolean isNum = false;
        for (int i = 0; i < s.length(); i++) {
            isChar = false;
            if (((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                    || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))) {

                charFound = true;
                isChar = true;
            }

            if ((s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                numFound = true;
                isNum = true;
            }

            if (((s.charAt(i) != '.' || s.charAt(i) != '_') && isChar == false)) {
                return Notification.INVALIDINPUT;
            }
        }

        if (charFound == false || numFound == false) {
            return Notification.MUSTUSECHAR;
        }

        return Notification.OK;

    }

    public static Notification checkInt(String s) {

        boolean isNegative = false;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '-') {
                isNegative = true;
                continue;
            }

            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                return Notification.MUSTUSEINT;
            }

        }
        return Notification.OK;
    }

    public static Notification checkFloat(String s) {

        boolean dotFound = false;
        boolean dotAlreadyFound = false;
        boolean isNegative = false;
        for (int i = 0; i < s.length(); i++) {


            if (s.charAt(i) == '-') {
                isNegative = true;
                continue;
            }


            if (!((s.charAt(i) >= '0' && s.charAt(i) <= '9') || s.charAt(i) == '.')) {
                return Notification.MUSTUSEFLOAT;
            }

            if (s.charAt(i) == '.') {

                if (dotFound == true) {
                    dotAlreadyFound = true;
                }
                if (dotFound == false) {
                    dotFound = true;
                }
            }
        }

        if (dotAlreadyFound == true || dotFound == false) {
            return Notification.MUSTUSEFLOAT;
        }

        return Notification.OK;

    }

    public static Notification checkEmail(String s) {

        boolean isAtFound = false;
        boolean isdotAfterAtFound = false;
        boolean isAnotherDotAfterAtFound = false;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '@') {
                if (isAtFound == false) {
                    isAtFound = true;
                } else {
                    return Notification.ATAGAINFOUND;
                }
            }

            if (s.charAt(i) == '.' && isAtFound == true) {
                if (isdotAfterAtFound == false) {
                    isdotAfterAtFound = true;
                } 
            }
        }
        
        if(isAtFound == false || isdotAfterAtFound == false)
            return Notification.INVALIDEMAIL;

        return Notification.OK;
    }
    
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        System.out.println(InputValidation.checkEmail("Kakahsi.Mamun@yahoo.om").msg);

    }
}