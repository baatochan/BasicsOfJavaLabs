import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by barto on 21.10.16.
 *
 * Program: BasicOnlineStore
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 21 pazdziernika 2016
 *
 * Klasa reprezentująca użytkowników.
 *
 */

/**
 * Klasa reprezentuje użytkowników sklepu internetowego.
 *
 * Każdy użytkownik posiada swoją nazwę użytkownika, imię i nazwisko, adres, prywatny portfel z saldem konta i hasło do logowania.
 *
 *
 * @author Bartosz Rodziewicz, 226105
 * @version 21 października 2016 r.
 */
class User implements Serializable {
	/** Zmienna wymagana do zapisu programu do pliku */
	private static final long serialVersionUID = 1L;

	/** Lista dokonanych przez użytkownika zamówień. */
	private ArrayList<Product> orderHistory;

	/** Nazwa użytkownika */
	private String name;
	/** Imię i nazwisko użytkownika */
	private String owner;
	/** Saldo portfela użytkownika
	 *
	 *  <p><b>Uwaga!</b> Saldo nie może być ujemne!</p>
	 *  <p>Saldo przechowywane jest w groszach, aby zniwelować do minimum, konieczność wykonywania operacji na liczbach zmiennoprzecinkowych.</p>
	 */
	private int money; //saldo w groszach
	/** Hasło użytkownika
	 *
	 * <p><b>Przechowywane w formie zahashowanej formie!</b></p>
	 */
	private long passwordCode;
	/** Adres zamieszkania użytkownika
	 *
	 *  <p>Potrzebny do wystawienia faktury.</p>
	 */
	private String address;

	User (String name, String owner, String address, long passwordCode) {
		this.name = name;
		this.owner = owner;
		money = 0;
		this.address = address;
		orderHistory = new ArrayList<>();
		this.passwordCode = passwordCode;
	}

	String getName() {
		return name;
	}

	String getOwner(){
		return owner;
	}
	
	/** @return saldo konta w gorszach */
	int getMoneyI(){
		return money;
	}
	/** @return saldo konta przeliczone na złotówki */
	double getMoneyD(){
		double money = this.money;
		money /= 100;
		return money;
	}
	
	String getAddress() {
		return address;
	}
	
	/** Metoda sprawdza czy podane hasło jest zgodne z hasłem użytkownika
	 *
	 * @param passwordCode stare hasło
	 * @return prawda, gdy hasła są zgodne
	 */
	boolean checkPass (long passwordCode) {
		if (passwordCode == this.passwordCode) return true;
		return false;
	}
	
	/** Metoda zmienia hasło na nowe, gdy podane stare hasło zgadza się z aktualnym
	 *
	 * @param oldPass stare hasło
	 * @param newPass nowe hasło
	 * @throws Exception stare hasło, nie zgadza się z aktualnym, przypisanym do konta
	 */
	void setPass(long oldPass, long newPass) throws Exception{
		if(!checkPass(oldPass)) throw new Exception("Błędne hasło!");
		passwordCode = newPass;
	}
	
	/** Metoda realizująca wpłatę środków do portfela
	 *
	 * @param amount kwota wpłaty (w groszach)
	 * @return prawda, gdy wpłata dokonana poprawnie
	 * @throws Exception gdy próba wpłaty ujemnej kwoty
	 */
	boolean payIn (int amount) throws Exception{
		if (amount<=0) throw new Exception("Kwota wpłaty musi być dodatnia!");
		money += amount;
		return true;
	}
	
	/** Metoda realizująca kupno produktu
	 *
	 * @param product produkt do kupna
	 * @param amount ilość sztuk w zamówieniu
	 */
	void buyProduct(Product product, int amount) {
		money -= ((product.getPrice())*amount);
		Product order = new Product(product.getISBN(),product.getTitle(),product.getAuthor(),product.getPrice(), amount);
		orderHistory.add(order);
	}
	
	/** @return historia zamówień w formie tekstowej */
	String printOrderHistory() {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		int total = 0;
		sb.append("Zamówienia: (ISBN - Autor - Tytuł, Cena jednostkowa, Ilość w zamówieniu, Cena całkowita)\n");
		for (Product product : orderHistory){
			sb.append(String.format("%d. ", ++n));
			sb.append(product.toString());
			int totalI = product.getPrice()*product.getAmountAvailable();
			double totalD = (((double)(totalI))/100);
			sb.append(String.format(", %.2f zł", totalD));
			sb.append("\n");
			total += totalI;
		}
		sb.append("Całkowita kwota za zakupy wynosi: " + (((double)total)/100) + " zł.");
		return sb.toString();
	}

	/** @return konto użytkownika w formie tekstowej (nie zawiera historii zamówień) */
	@Override
	public String toString() {
		return String.format("%s - %s, %.2f zl, %s",name,owner,(((double)money)/100),address);
	}

}
