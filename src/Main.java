//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DES des = new DES(234);

        System.out.println(des.bin2hex("1000010"));
        System.out.println(des.hex2bin("2E"));
    }
}