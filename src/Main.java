//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
//        DES des = new DES("AABB09182736CCDD");
//
//        DESECB desECB= new DESECB("AABB09182736CCDD");
//
//        String cipherText = desECB.encryptECB("hei ce faci??");
//        System.out.println(cipherText);
//
//        String decripted = desECB.decryptECB(cipherText);
//        System.out.println(decripted);
//
//        DESCBC desCBC = new DESCBC("AABB09182736CCDD");
//
//        String IV1 = desCBC.generateIV();
//        String IV2 = desCBC.generateIV();
//        String c1 = desCBC.encryptCBC("Salut", IV1);
//        System.out.println(c1);
//        String c2 = desCBC.encryptCBC("Hello World!", IV2);
//        System.out.println(c2);
//
//        System.out.println(desCBC.decryptCBC(c1, IV1));
//        System.out.println(desCBC.decryptCBC(c2, IV2));
//
        AES aes = new AES("3A94F62BC17E5D08A9B0C4E672F1D5AC");
//
//        System.out.println(aes.applySbox("90"));

//        aes.generateKeys();

        String[] test = {"63", "7B", "C0", "D2"};
        String[] res = aes.mixColumns(test);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
        }

    }

}