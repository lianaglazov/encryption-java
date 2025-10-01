public class AESCBC extends AES {

    AESCBC(String key) {
        super(key);
    }

    // randomly generate the 128-bit init vector
    String generateIV()
    {
        String IV = "";
        for (int i = 0; i < 128; i++) {
            double rand = Math.random();
            if (rand <= 0.5)
            {
                IV = IV + "0";
            }
            else  {
                IV = IV + "1";
            }
        }
        return op.bin2hex(IV);
    }

    // the IV is given as an argument, supposing that it is not used repeatedly
    public String encryptCBC(String msg, String IV)
    {
        String[] blocks = sepBlocks(msg);
        String cipher = "";

        String e = IV;
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = op.xorHex(blocks[i], e);

            String encBlock = encryptBlock(blocks[i]);
            cipher += encBlock;

            e = encBlock;
        }

        return cipher;
    }

    public String decryptCBC(String cipher, String IV)
    {
        cipher = op.hex2bin(cipher);

        // separate intro blocks
        String msg = "";
        String e = IV;
        for(int i = 0; i < cipher.length()/128; i++){
            String block = cipher.substring(i*128,(i+1)*128);
            // decrypt the block and then xor with the previous ciphertext or IV
            String decBlock = decryptBlock(op.bin2hex(block));
            decBlock = op.xorHex(decBlock, e);
            e = op.bin2hex(block);
            msg += decBlock;
        }
        msg = op.hex2bin(msg);
        // remove the padding
        int i = msg.length() - 1;
        while(i >= 0 & msg.charAt(i) == '0')
        {
            i--;
        }
        msg = msg.substring(0, i);

        msg = op.bin2hex(msg);
        msg = op.hex2str(msg);
        return msg;
    }
}
