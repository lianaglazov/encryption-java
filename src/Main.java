//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DES des = new DES("AABB09182736CCDD");

        DESECB desECB= new DESECB("AABB09182736CCDD");

        String cipherText = desECB.encryptECB("hei ce faci??");
        System.out.println(cipherText);

        String decripted = desECB.decryptECB(cipherText);
        System.out.println(decripted);

    }
}