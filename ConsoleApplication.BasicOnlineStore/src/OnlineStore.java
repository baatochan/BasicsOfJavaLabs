import java.io.*;
import java.util.ArrayList;

/**
 * Created by barto on 19.10.16.
 *
 * Program: BasicOnlineStore
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 19 pazdziernika 2016
 *
 * Klasa odpowiadająca za całe działanie sklepu implementująca użytkowników i produkty.
 *
 */

/**
 * Klasa realizująca działanie sklepu internetowego.
 *
 * Zawiera listę zarejestrowanych użytkowników i dostępnych towarów na magazynie, hasło administratora i utarg sklepu.
 *
 * @author Bartosz Rodziewicz, 226105
 * @version 19 października 2016 r.
 */

class OnlineStore implements Serializable {
	/** Zmienna wymagana do zapisu programu do pliku */
	private static final long serialVersionUID = 1L;

	/** Lista zarejestrowanych użytkowników */
	private ArrayList<User> listOfUsers;
	/** Lista zarejestrowanych prodktów w sklepie.
	 * <p>Towar nie musi być obecny na magazynie, wystarczy, że kiedykolwiek został dodany do bazy.</p>
	 */
	private ArrayList<Product> listOfProducts;
	
	/** Hasło sprzedawcy
	 * <p>Domyślne hasło: admin</p>
	 * <p><b>Hasło przechowywane w postaci zahashowanej!</b></p>
	 */
	private long SellerPasswordCode;
	/** Utarg sklepu w groszach */
	private int StoreMoney;

	OnlineStore() {
		SellerPasswordCode = "admin".hashCode();
		StoreMoney = 0;
		listOfUsers = new ArrayList<>();
		listOfProducts = new ArrayList<>();
	}
	
	/** Metoda realizująca dodanie produktu do bazy sklepu (ilość na stanie może być równa 0)
	 *
	 * @param ISBN isbn książki, musi być 10 lub 13 cyfrowy!
	 * @param title tytuł książki
	 * @param author autor
	 * @param price cena w groszach, nie może być ujemna!
	 * @param amountAvailable ilość na stanie, nie może być ujemna!
	 * @throws Exception gdy błędny numer ISBN, ujemna cena lub ilość na stanie ujemna
	 */
	void addProduct(long ISBN, String title, String author, int price, int amountAvailable) throws Exception {
		if (ISBN<1000000000L  || (ISBN>9999999999L && ISBN<10000000000000L) || ISBN>9999999999999L) throw (new Exception("ISBN jest błędny! Upewnij się że podałeś poprawny numer ISBN."));
		if (price<0) throw (new Exception("Cena nie może być ujemna!"));
		if (amountAvailable<0) throw (new Exception("Nie można mieć na stanie ujemnej liczby towaru!"));
		Product newProduct = new Product(ISBN, title, author, price, amountAvailable);
		listOfProducts.add(newProduct);

	}
	
	/** Metoda sprawdzająca czy podane hasło zgadza się z hasłem sprzedawcy
	 *
	 * @param pass hasło do porównania
	 * @return prawda, jeśli hasła zgodne
	 */
	boolean checkSellerPassword(String pass) {
		if (pass == null) return false;
		return pass.hashCode()==SellerPasswordCode;
	}
	
	/** Metoda zmieniająca hasło sprzedawcy, po uprzednim porównaniu czy podane stare hasło zgadza się z hasłem sprzedawcy
	 *
	 * @param oldPass stare hasło do porównania
	 * @param newPass nowe hasło do ustawienia
	 * @throws Exception gdy stare hasło nei zgadza się z aktualnie ustawionym hasłem. Dopiero, gdy te hasła są zgodne można ustawić nowe hasło.
	 */
	void setSellerPassword(String oldPass, String newPass) throws Exception{
		if (!checkSellerPassword(oldPass)) throw new Exception("Błędne hasło!");
		SellerPasswordCode = newPass.hashCode();
	}
	
	/** Metoda zapisująca sklep do pliku
	 *
	 * @param fileName nazwa pliku do wyeksportowania sklepu
	 * @throws Exception gdy plik do którego chcemy zapisać jest niedostępny.
	 */
	void saveStoreToFile(String fileName) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(listOfProducts);
		out.writeObject(listOfUsers);
		out.writeObject(SellerPasswordCode);
		out.writeObject(StoreMoney);
		out.close();
	}
	
	/** Metoda wczytująca sklep z pliku
	 *
	 * @param fileName nazwa pliku
	 * @throws Exception gdy plik nie istnieje lub zawiera niepoprawne dane.
	 */
	void loadStoreFromFile(String fileName) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		listOfProducts = (ArrayList<Product>)in.readObject();
		listOfUsers = (ArrayList<User>)in.readObject();
		SellerPasswordCode = (long)in.readObject();
		StoreMoney = (int)in.readObject();
		in.close();
	}

	/** @return produkty w bazie sklepu w postacii tekstowej */
	String listProducts() {
		StringBuilder sb = new StringBuilder();
		int n = 0;
		sb.append("Produkty aktualnie na stanie: (ISBN - Autor - Tytuł, Cena, Ilość na stanie)\n");
		for (Product product : listOfProducts){
			sb.append(String.format("%d. ",++n));
			sb.append(product.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/** @return użytkownicy zarejestrowani w sklepie w postacii tesktowej */
	String listUsers(){
		StringBuilder sb = new StringBuilder();
		int n = 0;
		sb.append("Zarejestrowani uzytkownicy: (Nazwa konta - Właściciel, Stan konta, Adres)\n");
		for (User user : listOfUsers){
			sb.append(String.format("%d. ",++n));
			sb.append(user.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Metoda zwracająca referencję do produktu o konkretnym ISBN
	 *
	 * @param ISBN isbn produktu
	 * @return referencja do szukanego produktu
	 */
	Product findProduct(long ISBN) {
		for(Product product : listOfProducts) {
			if(product.getISBN() == ISBN) return product;
		}
		return null;
	}
	
	/** Metoda zwracająca referencję do użytkownika o konkretnej nazwie
	 *
	 * @param name nazwa szukanego użytkownika
	 * @return referencja do suzkanego użytkownika
	 */
	User findUser(String name){
		for (User user : listOfUsers) {
			if (user.getName().equals(name)) return user;
		}
		return null;
	}
	
	/** Metoda zwracająca referęncje do użytkownika o konkretnej nazwie, gdy podane hasło zgadza się z hasłem tego użytkownika
	 *
	 * @param name nazwa użytkownika
	 * @param passwordCode zahashowane wpisane hasło
	 * @return refenecja do szukanego użytkownika
	 */
	User findUser(String name, long passwordCode){
		User user = findUser(name);
		if (user!= null){
			if(user.checkPass(passwordCode)) return user;
		}
		return null;
	}

	/** Metoda usuwająca dany produkt z bazy sklepu */
	void removeProductFromList (Product product) {
		listOfProducts.remove(product);
	}
	
	int getStoreMoney() {
		return StoreMoney;
	}
	
	/** Metoda dodająca użytkownika do bazy sklepu
	 *
	 * @param user użytkownik do dodania
	 */
	void addUserToList (User user) {
		listOfUsers.add(user);
	}
	
	/** Metoda realizująca kupno produktu po stronie sklepu
	 *
	 * @param product produkt do kupna
	 * @param user użytkownik kupujący produkt
	 * @param amount ilość towarów które chce kupić użytkownik
	 * @throws Exception gdy na stanie brakuje wystarczającej ilości towaru lub użytkownik nie ma wystarczjącej sumy na koncie.
	 */
	void buyProduct(Product product, User user, int amount) throws Exception{
		int amountAvailable = product.getAmountAvailable();
		if(amountAvailable<amount) throw new Exception("Brak wystarczającej ilości towarów na magazynie!");
		if(((product.getPrice())*amount)>user.getMoneyI()) throw new Exception("Nie masz wystarczającej ilości środków na koncie!");
		product.setAmountAvailable(amountAvailable - amount);
		user.buyProduct(product, amount);
		StoreMoney += ((product.getPrice())*amount);
	}
	
	/** Metoda generująca fakturę dla konkretnego użytkownika, zawierającą historię jego zamówień
	 *
	 * @param user użytkownik dla którego generuje fakturę
	 * @return faktura w formie tekstowej
	 */
	String printInvoice(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("FAKTURA\n\n\n");
		sb.append("Sprzedający: \n");
		sb.append("Księgarnia Twilight\n");
		sb.append("ul. Biblioteczna 35\n45-686 Ponyville\nEquestria\n\n");
		sb.append("Kupujący: \n");
		sb.append(user.getOwner()+"\n");
		sb.append(user.getAddress()+"\n\n");
		sb.append(user.printOrderHistory());
		return sb.toString();
	}
}
