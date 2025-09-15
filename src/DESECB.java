public class DESECB extends DES {
    DESECB(String key) {
        super(key);
    }

    public String encryptECB(String msg) {
        String[] blocks = sepBlocks(msg);
        String cipher = "";
        for (int i = 0; i < blocks.length; i++)
        {
            String encBlock = encryptBlock(blocks[i]);
            cipher += encBlock;
        }
        cipher = op.bin2hex(cipher);
        return cipher;
    }

    public String decryptECB(String cipher) {
        cipher = op.hex2bin(cipher);

        // separate intro blocks
        String msg = "";
        for(int i = 0; i < cipher.length()/64; i++){
            String block = cipher.substring(i*64,(i+1)*64);
            String decBlock = decryptBlock(block);
            msg += decBlock;
        }

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
