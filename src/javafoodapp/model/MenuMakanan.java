package javafoodapp.model;

public class MenuMakanan {
    private int id;
    private String kodeMenu;
    private String menuMakanan;
    private double harga;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeMenu() {
        return kodeMenu;
    }

    public void setKodeMenu(String kodeMenu) {
        this.kodeMenu = kodeMenu;
    }

    public String getMenuMakanan() {
        return menuMakanan;
    }

    public void setMenuMakanan(String menuMakanan) {
        this.menuMakanan = menuMakanan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
