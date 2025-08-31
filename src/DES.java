import java.util.*;
public class DES {

    String key;
    String[] keys = new String[16];
    int[][][] sboxes = {s1, s2, s3, s4, s5, s6, s7, s8};

    static int[] IP =
    {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    static int[] FP =
    {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };


    // expansion function: 32 bits -> 48 bits
    static int[] E =
    {
        32, 1,	2,	3,	4,	5,
        4,	5,	6,	7,	8,	9,
        8,	9,	10,	11,	12,	13,
        12,	13,	14,	15,	16,	17,
        16,	17,	18,	19,	20,	21,
        20,	21,	22,	23,	24,	25,
        24,	25,	26,	27,	28,	29,
        28,	29,	30,	31,	32,	1,
    };

    // permutation for shuffling the bits of a 32-bit half block
    static int[] P =
    {
        16, 7,	20,	21,	29,	12, 28,	17,
        1,	15,	23,	26,	5,	18,	31,	10,
        2,	8,	24,	14,	32,	27,	3,	9,
        19,	13,	30,	6,	22,	11,	4,	25
    };

    static int[] PC1 =
    {
        57, 49, 41, 33, 25, 17,  9,
        1, 58, 50, 42, 34, 26, 18,
        10,  2, 59, 51, 43, 35, 27,
        19, 11,  3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14,  6, 61, 53, 45, 37, 29,
        21, 13,  5, 28, 20, 12,  4
    };

    static int[] PC2 =
    {
        14, 17, 11, 24,  1,  5,
        3, 28, 15,  6, 21, 10,
        23, 19, 12,  4, 26,  8,
        16,  7, 27, 20, 13,  2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };

    // the number of times the bits are shifted to the left each round in round key schedule

    static int[] bitRotation = {
        1, 1, 2, 2,	2, 2, 2, 2,
        1, 2, 2, 2,	2, 2, 2, 1
    };

    // S-boxes
    static int[][] s1 = {
        {14, 4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11,  9,  5,  3,  8},
        {4, 1, 14,  8, 13,  6, 2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    };

    static int[][] s2 = {
        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13,  4, 7, 15,  2,  8, 14, 12,  0, 1, 10,  6,  9, 11,  5},
        {0, 14, 7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14,  9}
    };

    static int[][] s3 = {
        {10, 0, 9, 14, 6, 3, 15, 5,  1, 13, 12, 7, 11, 4, 2,  8},
        {13, 7, 0, 9, 3,  4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14,  7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
    };

    static int[][] s4 = {
        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,  9},
        {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9,  4, 5, 11, 12, 7, 2, 14}
    };

    static int[][] s5 = {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12,  4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
    };

    static int[][] s6 = {
        {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
    };

    static int[][] s7 = {
        {4, 11, 2, 14, 15,  0, 8, 13 , 3, 12, 9 , 7,  5, 10, 6, 1},
        {13 , 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
    };

    static int[][] s8 = {
        {13, 2, 8,  4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6 ,11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2,  0, 6, 10 ,13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6 ,11}
    };

    // the key is a 16-digit hex number
    DES(String key)
    {
        this.key = key;
        generateKeys();
    }

    String hex2bin(String hex)
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

    String  bin2hex(String bin)
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

    String str2hex(String str)
    {
        StringBuilder sb = new StringBuilder();
        char ch[] = str.toCharArray();
        for (char c : ch) {
            String hexString = Integer.toHexString(c);
            sb.append(hexString);
        }
        return sb.toString();
    }

    String hex2str(String hex) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

    String dec2bin(int dec)
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

    int bin2dec(String bin)
    {
        int dec = 0;
        for  (int i = 0; i < bin.length(); i++) {
            int bit = bin.charAt(i) - 48;
            int x = bin.length() - i - 1;
            dec = (int) (dec + bit * Math.pow(2, x));
        }
        return dec;
    }

    // separates the message into 64-bit blocks, adding padding (1 followed by how many 0 are necessary)
    // the padding will be added whether the message length is a multiple of 64 or not
    // to avoid losing data on the decryption in the case in which we have a msg of length divisible by 64
    // that finishes with an 1 and a seq of 0s
    String[] sepBlocks(String msg)
    {
        msg = str2hex(msg);
        msg = hex2bin(msg);
        int x = 64 - msg.length() % 64 - 1;
        String padding = "1" + "0".repeat(x);
        msg = msg + padding;

        String[] blocks = new String[msg.length()/64 ];
        for  (int i = 0; i< blocks.length; i++)
        {
            blocks[i] = msg.substring(i*64, i*64+64);
        }

        return blocks;
    }

    String retrieveFromBlocks(String[] blocks)
    {
        String msg = "";

        for  (int i = 0; i < blocks.length - 1; i++)
        {
            msg = msg + blocks[i];
        }

        String lastBlock = blocks[blocks.length-1];
        int i = 63;
        while(i >= 0 & lastBlock.charAt(i) == '0')
        {
            i--;
        }
        lastBlock = lastBlock.substring(0, i);
        msg = msg + lastBlock;

        msg = bin2hex(msg);
        msg = hex2str(msg);
        return msg;
    }

    // apply the permutation table to a binary block
    String permutation(String block, int[] perm)
    {
        String p = "";
        for (int i = 0; i < perm.length; i ++) {
            p = p + block.charAt(perm[i] - 1);
        }
        return p;
    }

    // shifting n bits to left
    String shiftLeft(String bin, int n)
    {
        String s = "";

        for (int i = 0; i < n; i++){
            for (int j = 1; j < bin.length(); j++){
                s = s + bin.charAt(j);
            }
            s = s + bin.charAt(0);
            bin = s;
            s = "";
        }

        return bin;
    }

    String xor(String a, String b)
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

    // a is always a 6 bit binary number
    String applySbox(String a, int[][] S)
    {
        // the outer bits
        String b = Character.toString(a.charAt(0)) + Character.toString(a.charAt(5));
        int x = bin2dec(b);

        // the inter bits
        b =  Character.toString(a.charAt(1)) + Character.toString(a.charAt(2)) + Character.toString(a.charAt(3)) +  Character.toString(a.charAt(4));
        int y = bin2dec(b);

        int s = S[x][y];
        return dec2bin(s);
    }

    public void generateKeys()
    {
        String binKey = hex2bin(key);
        // apply PC-1 64 bits to 56
        binKey = permutation(binKey, PC1);

        String left = binKey.substring(0, 28);
        String right = binKey.substring(28);

        for (int i = 0; i < 16; i++){

            left = shiftLeft(left, bitRotation[i]);
            right = shiftLeft(right, bitRotation[i]);

            binKey = left + right;

            // apply PC-2 56 to 48 bits
            binKey = permutation(binKey, PC2);
            keys[i] = binKey;
        }
    }

    // the round int is actually round number - 1, taken the index of the for in the main function
    public String feistel(String block, int round){
        // expanding the half block of 32 bits to 48 using the expansion permutation
        block = permutation(block, E);
        // xor with the round key
        block = xor(block, keys[round]);
        // apply the sboxes
        String sBlock = "";
        for (int i = 0; i < block.length(); i = i+6){
            String s = block.substring(i, i+6);
            sBlock = sBlock + applySbox(s, sboxes[i/6]);
        }

        // final permutation
        return permutation(sBlock, P);
    }

    // the block is given in binary
    public String encryptBlock(String block)
    {

        // apply the initial permutation
        block = permutation(block, IP);
        String left = block.substring(0, 32);
        String right = block.substring(32);

        // 16 rounds
        for (int i = 0; i < 16; i++){
            // the right block goes into the feistel function and it is xor-ed with the left block
            String f = feistel(right, i);
            // the result of the feistel function is xor-ed with the left half
            left = xor(left, f);
            // the switch the left half (that is now the xor result) with the right half (the original, not the feistel result)
            String a = left;
            left = right;
            right = a;
        }

        // after the last round the two halves of the block will be switched, so do right + left instead of left + right
        block = right + left;
        block = permutation(block, FP);

        return block;
    }

    // decrypting is the same as encrypting but with the keys in reverse order
    public String decryptBlock(String block)
    {

        block = permutation(block, IP);
        String left = block.substring(0, 32);
        String right = block.substring(32);

        for (int i = 15; i >= 0; i--){
            String f = feistel(right, i);
            left = xor(left, f);
            String a = left;
            left = right;
            right = a;
        }
        block = right + left;
        block = permutation(block, FP);

        return block;
    }



}
