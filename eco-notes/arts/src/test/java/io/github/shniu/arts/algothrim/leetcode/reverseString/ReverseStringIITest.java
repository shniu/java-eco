package io.github.shniu.arts.algothrim.leetcode.reverseString;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReverseStringIITest {

    @Test
    public void reverseStr() {
        ReverseStringII reverseStringII = new ReverseStringII();
        String s = reverseStringII.reverseStr("abcdefg", 3);
        System.out.println(s);

        s = reverseStringII.reverseStr("abcdefg", 2);
        System.out.println(s);

        s = reverseStringII.reverseStr("uxzpsogzkwgwacxxvvzlhkaahjaqagdfjkmyutvhxclzskvxckjvfgzptlzldjwhrpocfugzqkpaxexezbvggtkoxriysqivupofrcoxbrdgccvphvdtvrjtsbospmgyfduvaslnvxwuepleziodaaqmonsxjszyjwjmvgdqgowjjtwdmynvirnlujimedfyntgacntvyqujvehhvruiolfkeprqpzdvmapeukemmzxdtyolxeixatgsupvpidmeyifjyxkzudxvsunghtklzgxsjhrxgxgqcdebukrarpkpqmusempvulagashxpaisfvetrmiqiordsyjgyjmkvavxorrmnxbiikuxmezpkhgkjzmapldnmjvfxtmckskiwhdnuqpqrsrdspxuixxnibjxoyagijmlbhjtuchzbdpaxommfvlbpxfnzkkcdentdhhxracunvrtqxrbqanufaglncjqiwofanuznfmbtjalehlqidtcmqbsgppqyoaoglifareljluigqyxtveukstzepridpmdltpxjmzdvatgzmqexrauywreoslyoydmiyipyqiaihfjqncelefiaxjhdaamrvahbvoznsfvsdknlktsifioxjdsqldzlyzqkqxkwjfrehqbhlaanbcvxomxyypqfxbwmtaiegcjlzeslmpghirzsaprxdcbobflvbupwahxwjgrcqskewvjsjyyggozkvwwytrwpmuguclssmrshlwukkjapiwnkybydergdqkhttbakooghbskiqlesocfrjuxotecnhkfmwtmzcysppmffnskvfabunfzsibqrerfstonzjhtcpnscpteflsnmqqphelpngnlnczritcjxewlhftujrpaeaxylqkswaisvzgciaemvodvcnqtuwcjkmzjzkikaqifymwwlvyxndgwwlauwiyiflgoahyaavkudvemfftzwlxdltwicouwboeaddxmvin",
                22);
        System.out.println(s);
    }

    @Test
    public void print() {
        String s = "uxzpsogzkwgwacxxvvzlhkaahjaqagdfjkmyutvhxclzskvxckjvfgzptlzldjwhrpocfugzqkpaxexezbvggtkoxriysqivupofrcoxbrdgccvphvdtvrjtsbospmgyfduvaslnvxwuepleziodaaqmonsxjszyjwjmvgdqgowjjtwdmynvirnlujimedfyntgacntvyqujvehhvruiolfkeprqpzdvmapeukemmzxdtyolxeixatgsupvpidmeyifjyxkzudxvsunghtklzgxsjhrxgxgqcdebukrarpkpqmusempvulagashxpaisfvetrmiqiordsyjgyjmkvavxorrmnxbiikuxmezpkhgkjzmapldnmjvfxtmckskiwhdnuqpqrsrdspxuixxnibjxoyagijmlbhjtuchzbdpaxommfvlbpxfnzkkcdentdhhxracunvrtqxrbqanufaglncjqiwofanuznfmbtjalehlqidtcmqbsgppqyoaoglifareljluigqyxtveukstzepridpmdltpxjmzdvatgzmqexrauywreoslyoydmiyipyqiaihfjqncelefiaxjhdaamrvahbvoznsfvsdknlktsifioxjdsqldzlyzqkqxkwjfrehqbhlaanbcvxomxyypqfxbwmtaiegcjlzeslmpghirzsaprxdcbobflvbupwahxwjgrcqskewvjsjyyggozkvwwytrwpmuguclssmrshlwukkjapiwnkybydergdqkhttbakooghbskiqlesocfrjuxotecnhkfmwtmzcysppmffnskvfabunfzsibqrerfstonzjhtcpnscpteflsnmqqphelpngnlnczritcjxewlhftujrpaeaxylqkswaisvzgciaemvodvcnqtuwcjkmzjzkikaqifymwwlvyxndgwwlauwiyiflgoahyaavkudvemfftzwlxdltwicouwboeaddxmvin";
        int k = 22;
        print(s, k);

        print("khlzvvxxcawgwkzgospzxuaahjaqagdfjkmyutvhxclzprhwjdlzltpzgfvjkcxvksocfugzqkpaxexezbvggtkoccgdrbxocrfopuviqsyirxvphvdtvrjtsbospmgyfduvnomqaadoizelpeuwxvnlsasxjszyjwjmvgdqgowjjtwdncagtnyfdemijulnrivnymtvyqujvehhvruiolfkeprqexloytdxzmmekuepamvdzpixatgsupvpidmeyifjyxkzxgxrhjsxgzlkthgnusvxdugqcdebukrarpkpqmusempvoiqimrtevfsiapxhsagalurdsyjgyjmkvavxorrmnxbijmndlpamzjkghkpzemxukivfxtmckskiwhdnuqpqrsrdhblmjigayoxjbinxxiuxpsjtuchzbdpaxommfvlbpxfnxqtrvnucarxhhdtnedckkzrbqanufaglncjqiwofanuzpgsbqmctdiqlhelajtbmfnpqyoaoglifareljluigqyxmjxptldmpdirpeztskuevtzdvatgzmqexrauywreoslyelecnqjfhiaiqypiyimdyofiaxjhdaamrvahbvoznsfvylzdlqsdjxoifistklnkdszqkqxkwjfrehqbhlaanbcvzljcgeiatmwbxfqpyyxmoxeslmpghirzsaprxdcbobfljsjvweksqcrgjwxhawpubvyyggozkvwwytrwpmuguclsedybyknwipajkkuwlhsrmsrgdqkhttbakooghbskiqleczmtwmfkhncetoxujrfcosysppmffnskvfabunfzsibqlfetpcsnpcthjznotsfrersnmqqphelpngnlnczritcjawskqlyxaeaprjutfhlwexisvzgciaemvodvcnqtuwcjdnxyvlwwmyfiqakikzjzmkgwwlauwiyiflgoahyaavkuobwuociwtldxlwztffmevdnivmxddae",
                22);

        print("abcdefgh", 2);
        print("abcdefgh", 3);
    }

    private void print(String s, int k) {
        int p = 0;
        while (p + k < s.length()) {
            System.out.println(s.substring(p, p + k));
            p += k;
        }

        int left = s.length() % k;
        System.out.println(s.substring(s.length() - left, s.length()));
    }
}