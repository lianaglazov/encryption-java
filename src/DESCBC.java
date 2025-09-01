import java.util.HashMap;

public class DESCBC extends DES {

    DESCBC(String key) {
        super(key);
    }

    // randomly generate the 64-bit init vector
    String generateIV()
    {
        String IV = "";
        for (int i = 0; i < 64; i++) {
            double rand = Math.random();
            if (rand <= 0.5)
            {
                IV = IV + "0";
            }
            else  {
                IV = IV + "1";
            }
        }
        return IV;
    }

    // the IV is given as an argument, supposing that it is not used repeatedly
    public String encryptCBC(String msg, String IV)
    {
        String[] blocks = sepBlocks(msg);
        String cipher = "";

        String e = IV;
        for (int i = 0; i < blocks.length; i++)
        {
            blocks[i] = xor(blocks[i], e);

            String encBlock = encryptBlock(blocks[i]);
            cipher += encBlock;

            e = encBlock;
        }

        cipher = bin2hex(cipher);

        return cipher;
    }

    public String decryptCBC(String cipher, String IV)
    {
        cipher = hex2bin(cipher);

        // separate intro blocks
        String msg = "";
        String e = IV;
        for(int i = 0; i < cipher.length()/64; i++){
            String block = cipher.substring(i*64,(i+1)*64);
            // decrypt the block and then xor with the previous ciphertext or IV
            String decBlock = decryptBlock(block);
            decBlock = xor(decBlock, e);
            e = block;
            msg += decBlock;
        }

        // remove the padding
        int i = msg.length() - 1;
        while(i >= 0 & msg.charAt(i) == '0')
        {
            i--;
        }
        msg = msg.substring(0, i);

        msg = bin2hex(msg);
        msg = hex2str(msg);
        return msg;
    }
}
