//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DES des = new DES("nvm");
//
//        System.out.println(des.bin2hex("1000010"));
//        System.out.println(des.hex2bin("2E"));
//
//        String str = "bro";
//        StringBuffer sb = new StringBuffer();
//        char ch[] = str.toCharArray();
//        for(int i = 0; i < ch.length; i++) {
//            String hexString = Integer.toHexString(ch[i]);
//            sb.append(hexString);
//        }
//        String result = sb.toString();
//        System.out.println(result);
//
//        System.out.println(des.hex2str("62726f"));
//
//        String h = "123456ABCD132536";
//        String b = des.hex2bin(h);
//        String perm = des.permutation(b, des.IP);
//        String hperm = des.bin2hex(perm);
//        System.out.println(hperm);
//
//        String s = "0123";
//        System.out.println(s.substring(0,2));
//        System.out.println(s.substring(2));

        des.generateKeys("AABB09182736CCDD");
//        for (int i = 0; i < 16; i++)
//        {
//            System.out.println(des.bin2hex(des.keys[i]));
//        }

    }
}