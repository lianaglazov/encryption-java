import java.util.HashMap;

public class op {
    public static String hex2bin(String hex)
    {
        String binary = "";

        hex = hex.toUpperCase();

        HashMap<Character, String> hashMap = new HashMap<Character, String>();

        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        for (i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);

            if (hashMap.containsKey(ch))
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
        return binary;
    }

    public static String  bin2hex(String bin)
    {
        String hex = "";
        HashMap<String, Character> hashMap = new HashMap<String, Character>();

        hashMap.put("0000", '0');
        hashMap.put("0001", '1');
        hashMap.put("0010", '2');
        hashMap.put("0011", '3');
        hashMap.put("0100", '4');
        hashMap.put("0101", '5');
        hashMap.put("0110", '6');
        hashMap.put("0111", '7');
        hashMap.put("1000", '8');
        hashMap.put("1001", '9');
        hashMap.put("1010", 'A');
        hashMap.put("1011", 'B');
        hashMap.put("1100", 'C');
        hashMap.put("1101", 'D');
        hashMap.put("1110", 'E');
        hashMap.put("1111", 'F');

        int i;
        String ch;
        while (bin.length() % 4 != 0) {
            bin = "0" + bin;
        }
        for (i = 0; i < bin.length(); i = i+4) {
            ch = "";
            ch = ch + bin.charAt(i);
            ch = ch + bin.charAt(i+1);
            ch = ch + bin.charAt(i+2);
            ch = ch + bin.charAt(i+3);
            hex = hex + hashMap.get(ch);
        }

        return hex;
    }

    public static String str2hex(String str)
    {
        StringBuilder sb = new StringBuilder();
        char ch[] = str.toCharArray();
        for (char c : ch) {
            String hexString = Integer.toHexString(c);
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String hex2str(String hex) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    public static String dec2bin(int dec)
    {
        String bin = "";

        while(dec > 0){
            bin = dec%2 + bin;
            dec = dec/2;
        }

        if (bin == "")
            bin = "0000";
        while (bin.length()%4 != 0) {
            bin = "0" + bin;
        }

        return bin;
    }

    public static int bin2dec(String bin)
    {
        int dec = 0;
        for  (int i = 0; i < bin.length(); i++) {
            int bit = bin.charAt(i) - 48;
            int x = bin.length() - i - 1;
            dec = (int) (dec + bit * Math.pow(2, x));
        }
        return dec;
    }

    public static String dec2hex(int dec)
    {
        String bin = dec2bin(dec);
        return bin2hex(bin);
    }

    public static int hex2dec(String hex)
    {
        String bin =  hex2bin(hex);
        return bin2dec(bin);
    }

    public static String xor(String a, String b)
    {
        String xor = "";
        for (int i = 0; i < a.length(); i++){
            if (a.charAt(i) == b.charAt(i)){
                xor = xor + "0";
            }
            else  {
                xor = xor + "1";
            }
        }
        return xor;
    }

    public static String xorHex(String a, String b)
    {
        a = hex2bin(a);
        b = hex2bin(b);
        String xor = xor(a, b);
        return bin2hex(xor);

    }
}
