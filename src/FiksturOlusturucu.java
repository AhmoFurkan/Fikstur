import java.util.*;

public class FiksturOlusturucu {

    private final List<String> takimlar;
    private final List<List<String[]>> round;

    public FiksturOlusturucu(List<String> takimlar) {
        this.takimlar = new ArrayList<>(takimlar);

        // Takım sayısı tek ise "Bay" takımı ekle
        if (this.takimlar.size() % 2 != 0) {
            this.takimlar.add("Bay");
        }

        this.round = new ArrayList<>();
    }

    public void fiksturOlustur() {
        int takimSayisi = takimlar.size();
        int toplamRound = (takimSayisi - 1) * 2;
        int yari = takimSayisi / 2;

        List<String> donenListe = new ArrayList<>(takimlar);
        donenListe.removeFirst(); // İlk takımı sabit tut
        //0       //5->sabit
        for (int round = 0; round < toplamRound / 2; round++) {
            List<String[]> maclar = new ArrayList<>();
            int donenBoyut = donenListe.size();
            //                    5->sabit                                 0
            // Sabit takımın maçı   GALATASARAY      //bursaspor      0          5-> sabit
            maclar.add(new String[]{takimlar.getFirst(), donenListe.get(round % donenBoyut)});

            // Diğer maçlar     3->sabit
            for (int i = 1; i < yari; i++) {
                //2
                int evSahibiIndex = (round + i) % donenBoyut;
                //3
                int deplasmanIndex = (round + donenBoyut - i) % donenBoyut;

                maclar.add(new String[]{donenListe.get(evSahibiIndex), donenListe.get(deplasmanIndex)});
            }
//
            this.round.add(maclar);
        }

        // Rövanş maçlarını oluştur (ev-deplasman yer değiştir)
        //2         //10->sabit
        for (int i = 0; i < toplamRound / 2; i++) {
            List<String[]> ilkYari = this.round.get(i);
            List<String[]> rovanS = new ArrayList<>();
            for (String[] mac : ilkYari) {
                rovanS.add(new String[]{mac[1], mac[0]});
            }
            this.round.add(rovanS);
        }
    }

    public void fiksturYazdir() {
        int roundNumarasi = 1;
        for (List<String[]> hafta : this.round) {
            System.out.println("Round " + roundNumarasi);
            for (String[] mac : hafta) {
                System.out.println(mac[0] + " vs " + mac[1]);
            }
            System.out.println();
            roundNumarasi++;
        }
    }

    public static void main(String[] args) {
        List<String> takimListesi = Arrays.asList(
                "Galatasaray",
                "Bursaspor",
                "Fenerbahçe",
                "Beşiktaş",
                "Trabzonspor"
                // Eğer tek takım girersen buraya "Boluspor" gibi bir tane daha ekle, yoksa "Bay" eklenecek
        );

        FiksturOlusturucu fikstur = new FiksturOlusturucu(takimListesi);
        fikstur.fiksturOlustur();
        fikstur.fiksturYazdir();
    }
}

