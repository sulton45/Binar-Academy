import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String pilihan;
        Map<String, Menu> pesanan = new HashMap<String, Menu>();
        List<Menu> menuList = new ArrayList<>();
        boolean loop = true;

        while(loop){
            System.out.println("===========================");
            System.out.println("Selamat datang di Binarfund");
            System.out.println("===========================");

            System.out.println("\nSilahkan pilih makanan : ");
            System.out.println("1. Nasi Goreng  | 15.000");
            System.out.println("2. Mie Goreng   | 13.000");
            System.out.println("3. Nasi + Ayam  | 18.000");
            System.out.println("4. Es Teh Manis | 3.000");
            System.out.println("5. Es Jeruk     | 5.000");
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar Aplikasi");

            System.out.print("\n=> ");
            pilihan = input.next();

            switch (pilihan){
                case "1":
                    pilihPesanan("Nasi Goreng", 15000, input, menuList);
                    break;
                case "2":
                    pilihPesanan("Mie Goreng", 13000, input, menuList);
                    break;
                case "3":
                    pilihPesanan("Nasi + Ayam", 18000, input, menuList);
                    break;
                case "4":
                    pilihPesanan("Es Teh Manis", 3000, input, menuList);
                    break;
                case "5":
                    pilihPesanan("Es Jeruk", 5000, input, menuList);
                    break;
                case "99":
                    loop = totalPesanan(menuList, input);
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("Maaf anda memasukan nomor yang salah");
            }
        }
    }

    public static void pilihPesanan(String menu, Integer harga, Scanner input, List<Menu> pesanan){
        int qty, totalHarga;

        System.out.println("===========================");
        System.out.println("Berapa Pesanan Anda");
        System.out.println("===========================");

        System.out.printf("\n%s\t| %d", menu, harga);
        System.out.println("\n(input 0 untuk kembali)");

        System.out.print("\nqty => ");
        qty = input.nextInt();
        totalHarga = qty * harga;
        Menu menuPesanan = new Menu(menu, qty, harga, totalHarga);
        pesanan.add(menuPesanan);
    }

    public static boolean totalPesanan(List<Menu> menuList, Scanner input){
        int totalQuantity = 0;
        int totalHarga = 0;
        String pilihan;
        System.out.println("===========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("===========================\n");
        for(Menu menu : menuList){
            if(menu.getMenu().equals("Es Teh Manis")){
                System.out.println(menu.getMenu() + "\t" + menu.getQuantity() + "\t" + menu.getTotalHarga());
            }else {
                System.out.println(menu.getMenu() + "\t\t" + menu.getQuantity() + "\t" + menu.getTotalHarga());
            }
            totalQuantity += menu.getQuantity();
            totalHarga += menu.getTotalHarga();
        }
        System.out.println("---------------------------+");
        System.out.printf("Total\t\t\t%d\t%d", totalQuantity, totalHarga);

        System.out.println("\n\n1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("\n=> ");
        pilihan = input.next();

        switch (pilihan){
            case "1":
                try {
                    PrintWriter writer = new PrintWriter("pesanan.txt", "UTF-8");
                    printPesanan(writer, menuList);
                    return true;
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

            case "2":
                return true;

            case "0":
                return false;

            default:
                return true;
        }
    }

    public static void printPesanan(PrintWriter writer, List<Menu> menuList){
        int totalQuantity = 0;
        int totalHarga = 0;
        writer.println("================================");
        writer.println("Konfirmasi & Pembayaran");
        writer.println("================================\n");

        writer.println("Terima kasih sudah memesan");
        writer.println("di Binarfund");
        writer.println("\nDibawah ini adalah pesanan anda\n");
        for(Menu menu : menuList){
            writer.println(menu.getMenu() + "\t" + menu.getQuantity() + "\t" + menu.getTotalHarga());
            totalQuantity += menu.getQuantity();
            totalHarga += menu.getTotalHarga();
        }
        writer.println("--------------------------------+");
        writer.printf("Total\t\t%d\t%d\n", totalQuantity, totalHarga);

        writer.println("\nPembayaran: BinarCash");
        writer.println("\n================================");
        writer.println("Simpan struk ini sebagai");
        writer.println("bukti pembayaran");
        writer.println("================================\n");
        writer.close();
    }
}