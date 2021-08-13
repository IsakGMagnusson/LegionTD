package game.display;

public class LetterSizes {
    public static int stringWidth(String string){
        int size = 0;
        for(char c : string.toCharArray()){
            switch(c) {
                case ' ':
                case '2':
                case ',':
                case ')':
                case '(':
                    size+=3;
                    break;
                case '!':
                case ';':
                case ':':
                case '.':
                case '\'':
                    size+=2;
                    break;
                case '"':
                case 'E':
                case 'e':
                case 'D':
                case 'd':
                case 'C':
                case 'c':
                case 'B':
                case 'b':
                case 'A':
                case 'a':
                case '?':
                case '>':
                case '=':
                case '<':
                case '9':
                case '8':
                case '7':
                case '6':
                case '5':
                case '4':
                case '3':
                case '/':
                case '*':
                case 'F':
                case 'f':
                case 'H':
                case 'h':
                case 'I':
                case 'i':
                case 'J':
                case 'j':
                case 'K':
                case 'k':
                case 'L':
                case 'l':
                case 'P':
                case 'p':
                case 'R':
                case 'r':
                case 'S':
                case 's':
                case 'T':
                case 't':
                case 'X':
                case 'x':
                case 'Y':
                case 'y':
                case 'Z':
                case 'z':
                    size+=4;
                    break;
                case '%':
                case 'W':
                case 'w':
                case '-':
                case '+':
                case 'M':
                case 'm':
                case 'V':
                case 'v':
                    size+=6;
                    break;
                case '0':
                case 'G':
                case 'g':
                case 'N':
                case 'n':
                case 'O':
                case 'o':
                case 'Q':
                case 'q':
                case 'U':
                case 'u':
                    size+=5;
                    break;
                default:
                    size += 0;
            }
        }
        return size;
    }

}
