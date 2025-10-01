import javax.sound.midi.Soundbank;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        // testing the classes

        // des block
        DES des = new DES("8f83adb5dcce5302");
        String blockDES = op.hex2bin("c6c0767c4996c10e");
        String encBD = des.encryptBlock(blockDES);
        System.out.println(op.bin2hex(encBD));
        System.out.println(op.bin2hex(des.decryptBlock(encBD)));

        System.out.println(" ");

        // des ecb
        DESECB desE = new DESECB("8f83adb5dcce5302");
        String msg = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ";
        String encDE = desE.encryptECB(msg);
        System.out.println(encDE);
        System.out.println(desE.decryptECB(encDE));

        System.out.println(" ");

        // dec cbc
        DESCBC desC = new DESCBC("8f83adb5dcce5302");
        String IV1 = desC.generateIV();
        System.out.println(op.bin2hex(IV1));
        String encDC = desC.encryptCBC(msg,IV1);
        System.out.println(encDC);
        System.out.println(desC.decryptCBC(encDC,IV1));

        System.out.println(" ");

        // aes block
        AES aes = new AES("01d207bcaa1a9121d97ee059c528cf1a");
        String blockAES = "f2dbd3f314b0ca6fc46a6740d32205ce";
        String encBA = aes.encryptBlock(blockAES);
        System.out.println(encBA);
        System.out.println(aes.decryptBlock(encBA));

        System.out.println(" ");

        //aes ecb

        AESECB aesE = new AESECB("01d207bcaa1a9121d97ee059c528cf1a");
        String encAE = aesE.encryptECB(msg);
        System.out.println(encAE);
        System.out.println(aesE.decryptECB(encAE));

        System.out.println(" ");

        //aes cbc
        AESCBC aesC = new AESCBC("01d207bcaa1a9121d97ee059c528cf1a");
        String IV2 = aesC.generateIV();
        System.out.println(IV2);
        String encAC = aesC.encryptCBC(msg,IV2);
        System.out.println(encAC);
        System.out.println(aesC.decryptCBC(encAC,IV2));
    }

}