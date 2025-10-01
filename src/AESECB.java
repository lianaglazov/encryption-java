public class AESECB extends AES{

    AESECB(String key){
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
        return cipher;
    }

    public String decryptECB(String cipher) {

        cipher = op.hex2bin(cipher);
        // separate intro blocks
        String msg = "";
        for(int i = 0; i < cipher.length()/128; i++){
            String block = cipher.substring(i*128,(i+1)*128);
            String decBlock = decryptBlock(op.bin2hex(block));
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
