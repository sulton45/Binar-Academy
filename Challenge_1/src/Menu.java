public class Menu {

    public String menu;
    public Integer quantity;
    public Integer harga;
    public Integer totalHarga;

    public Menu(String menu, Integer quantity, Integer harga, Integer totalHarga){
        this.menu = menu;
        this.quantity = quantity;
        this.harga = harga;
        this.totalHarga = totalHarga;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(Integer totalHarga) {
        this.totalHarga = totalHarga;
    }
}