package passwordGenerator;

public class Password {
    String value;
    int length;

    public Password(String s) {
        value = s;
        length = s.length();
    }

    public int CharType(char c) {
        int val;

        //Char is uppercase letter
        if((int) c >= 65 && (int) c<=90) {
            val = 1;
        }

        //Char is lowercase letter
        if((int) c >=97 && (int) c<=122) {
            val = 2;
        }

        if((int) c>=60 && (int) c<=122) {
            val = 3;
        }
        //Char is symbol
        else {
            val = 4;
        }
        return val;
    }
    public int passwordStrength() {
        String s = this.value;
        boolean usedUpperCase = false;
        boolean usedLowerCase = false;
        boolean usedNum = false;
        boolean usedSym = false;

        int type;
        int score = 0;

        for(int i=0; i<s.length(); i++) {
            char C = s.charAt(i);
            type = CharType(C);

            if(type==1) usedUpperCase = true;
            if(type==2) usedLowerCase = true;
            if(type==3) usedNum = true;
            if(type==4) usedSym = true;
        }
        if(usedUpperCase) score += 1;
        if(usedLowerCase) score += 1;
        if(usedNum) score += 1;
        if(usedSym) score += 1;

        if(s.length() >= 8) score += 1;
        if(s.length() >=16) score += 1;

        return score;
    }

    public String calculateScore() {
        int score = this.passwordStrength();
        if(score == 6) {
            return "this is a very good password :D check the Useful information section to make sure it satisfies the guidelines";
        }
        else if(score>=4) {
            return "This is good password :) but you can still do better";
        }
        else if(score>=3) {
            return "This is a medium password :/ try making it better";
        }
        else {
            return "This is a weak password :( definitely find a new one)";
        }
    }

    @Override
    public String toString() {
        return value;
    }


}
