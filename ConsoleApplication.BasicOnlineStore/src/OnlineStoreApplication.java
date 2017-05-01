/**
 * Created by barto on 19.10.16.
 *
 * Program: BasicOnlineStore
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 19 pazdziernika 2016
 *
 * Główny plik programu. Implementuje wszystkie inne klasy, odpowiada za menu programu i kilka metod.
 *
 */

/**
 * Główny plik programu implementujący interfejs użytkownika i obsługę sklepu internetowego (klasy OnlineStore)
 *
 * Zawiera komunikaty wyświetlane w menu i metody je wyświetlające lub uruchamiające metody odpowiedzialne za edycje sklepu.
 *
 * @author Bartosz Rodziewicz, 226105
 * @version 19 października 2016 r.
 */

public class OnlineStoreApplication {
	/** Główna metoda uruchamiająca program */
	public static void main(String[] args) {
		new OnlineStoreApplication();
	}

	/** Implementacja konsolowego interfejsu użytkownika */
	private UserDialog UI = new ConsoleUserDialog();

	/** Nazwa pliku, z którego sklep zostanie wczytany lub do którego zostanie zapisany */
	private static final String DataFileName = "store.bin";

	/** Widomość powitalna, zawierająca nazwę programu, autora i date jego utworzenia */
	private static final String GreetingMessage =
			"Program Sklep Internetowy - wersja konsolowa\n" +
			"Autor: Bartosz Rodziewicz, 226105\n" +
			"Data: 19 października 2016r.\n";

	/** Menu Główne */
	private static final String MainMenu =
			"Sklep internetowy - menu główne\n" +
			"1. Zaloguj się jako sprzedawca\n" +
			"2. Rejestracja nowego klienta\n" +
			"3. Zaloguj się jako klient\n" +
			"------\n" +
			"4. Wykonaj backup do pliku\n" +
			"5. Wczytaj backup z pliku\n" +
			"------\n" +
			"0. Zakończ program\n";

	/** Panel Administratora/Sprzedawcy */
	private static final String SellerPanel =
			"1. Dodaj nowy towar do magazynu\n" +
			"2. Przeglądaj zawartość magazynu\n" +
			"3. Aktualizacja stanu magazynowego\n" +
			"4. Zmiana ceny towaru\n" +
			"5. Wycofaj towar z oferty\n" +
			"6. Sprawdź utarg sklepu\n" +
			"7. Lista zarejestrowanych klientów\n" +
			"8. Wystaw fakturę\n" +
			"9. Zmiana hasła sprzedawcy\n" +
			"------\n" +
			"0. Wyloguj się\n";

	/** Panel Użytkownika */
	private static final String UserPanel =
			"1. Pokaż stan konta\n" + //wiem że duplikacja i że to wyświetla się samo, ale jest w poleceniu to zrobiłem
			"2. Doładuj konto\n" +
			"3. Przeglądanie dostępnych towarów\n" +
			"4. Zakup towaru\n" +
			"5. Historia zakupów\n" +
			"6. Zmiana hasła\n" +
			"------\n" +
			"0. Wyloguj się\n";

	/** Instancja sklepu */
	private OnlineStore store = new OnlineStore();

	/** Konstruktor tej klasy, w którym zapętlone jest menu główne */
	OnlineStoreApplication() {
		/*int x = (int)((UI.enterDouble("Podaj cene: "))*100);
		UI.printMessage(String.format("%d",x));
		UI.printMessage(String.format("%.2f zl",(((double)x)/100)));
		System.exit(0);*/
		UI.printMessage(GreetingMessage);

		try {
			store.loadStoreFromFile(DataFileName);
			UI.printMessage("Sklep został wczytany z pliku " + DataFileName);
		} catch (Exception e) {
			UI.printMessage(e.getMessage());
		}

		while (true) {
			UI.clearConsole();

			try {
				switch (UI.enterInt(MainMenu + "==>> ")) {
					case 1:
						LoginAsSeller();
						break;
					case 2:
						try {
							createUser();
						} catch (Exception e){
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 3:
						LoginAsUser();
						break;
					case 4:
						try {
							store.saveStoreToFile(DataFileName);
							UI.printMessage("Sklep został zapisany do pliku " + DataFileName);
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 5:
						try {
							store.loadStoreFromFile(DataFileName);
							UI.printMessage("Sklep został wczytany z pliku " + DataFileName);
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 0:
						try {
							store.saveStoreToFile(DataFileName);
							UI.printMessage("Sklep został zapisany do pliku " + DataFileName);
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						UI.printMessage("\nProgram zakończy działanie.");
						System.exit(0);
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}

	/** Metoda realizująca logowanie do panelu administartora i wyświetlająca panel administratora */
	void LoginAsSeller() {
		UI.printMessage("Logowanie jako sprzedawca\n");
		if(!(store.checkSellerPassword(UI.enterString("Podaj hasło (domyślne hasło: admin): ")))) {
			UI.printErrorMessage("Błędne hasło!");
			return;
		}
		while(true) {
			UI.clearConsole();
			UI.printMessage("Jesteś zalogowany jako sprzedawca");

			try {
				switch (UI.enterInt(SellerPanel + "==>> ")) {
					case 1:
						store.addProduct(UI.enterLong("Podaj ISBN dodawanej książki: "),UI.enterString("Podaj tytuł: "), UI.enterString("Podaj autora: "), (int)((UI.enterDouble("Podaj cenę: "))*100), UI.enterInt("Podaj aktualną ilość towaru na stanie: "));
						UI.printMessage("Produkt został pomyślnie dodany!");
						break;
					case 2:
						UI.printMessage(store.listProducts());
						break;
					case 3:
						try {
							changeAvailabilityOfProduct();
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 4:
						try {
							changePriceOfProduct();
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 5:
						try {
							removeProduct();
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 6:
						UI.printMessage("Aktualny utarg sklepu wynosi: " + (((double)(store.getStoreMoney()))/100) + "\n");
						break;
					case 7:
						UI.printMessage(store.listUsers());
						break;
					case 8:
						UI.printMessage(store.listUsers());
						User user = store.findUser(UI.enterString("Podaj nazwę użytkownika: "));
						if(user == null){
							UI.printErrorMessage("Użytkownik o takiej nazwie nie istnieje!");
							break;
						}
						UI.printMessage(store.printInvoice(user));
						break;
					case 9:
						try {
							store.setSellerPassword(UI.enterString("Podaj stare hasło: "), UI.enterString("Podaj nowe hasło: "));
							UI.printMessage("Hasło zostało zmienione.\n");
						} catch (Exception e) {
							UI.printErrorMessage(e.getMessage());
						}
						break;
					case 0:
						UI.printMessage("Nastąpiło wylogowanie z panelu sprzedawcy.\n");
						return;
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	/** Metoda realizująca zmianę ilości towaru na stanie
	 *
	 * @throws Exception gdy podanego produktu nie ma w bazie lub próba zmiany na ujemną ilość towaru.
	 */
	void changeAvailabilityOfProduct() throws Exception{
		long ISBN = UI.enterLong("Podaj ISBN książki do zmiany: ");
		Product product = store.findProduct(ISBN);
		if (product == null) throw (new Exception("Podana książka nie jest w bazie, dodaj ją przed zmiana ilości na stanie.\n"));
		UI.printMessage("Aktualna ilość na stanie: " + product.getAmountAvailable());
		int newAmount = UI.enterInt("Podaj nową ilość: ");
		if(newAmount<0) throw (new Exception("Nie można mieć ujemnej ilości produktu na stanie!\n"));
		product.setAmountAvailable(newAmount);
		UI.printMessage("Stan magazynowy został zaaktualizowany!");
	}
	
	/** Metoda realizująca zmianę ceny towaru na stanie
	 *
	 * @throws Exception gdy podanego produktu nie ma w bazie lub próba zmiany na ujemną cenę.
	 */
	void changePriceOfProduct() throws Exception{
		long ISBN = UI.enterLong("Podaj ISBN książki do zmiany: ");
		Product product = store.findProduct(ISBN);
		if (product == null) throw (new Exception("Nie ma takiego produktu.\n"));
		UI.printMessage("Aktualna cena: " + (((double)(product.getPrice()))/100));
		int newPrice = (int)(UI.enterDouble("Podaj nową cenę: ")*100);
		if (newPrice<0) throw (new Exception("Cena nie może być ujemna!"));
		product.setPrice(newPrice);
		UI.printMessage("Cena została zaaktualizowana!");
	}
	
	/** Metoda realizująca usunięcię towaru z bazy sklepu
	 *
	 * @throws Exception gdy nie ma produktu, który usuwa
	 */
	void removeProduct() throws Exception{
		long ISBN = UI.enterLong("Podaj ISBN książki do usunięcia: ");
		Product product = store.findProduct(ISBN);
		if (product == null) throw (new Exception("Nie ma takiego produktu.\n"));
		store.removeProductFromList(product);
		UI.printMessage("Produkt został usunięty z oferty!");
	}
	
	/** Metoda realizująca utworzenie nowego użytkownika
	 *
	 * @throws Exception gdy uzytkownik o podanej nazwie juz istnieje
	 */
	void createUser() throws Exception{
		UI.printMessage("Dodawanie nowego użytkownika.\n");
		String name = UI.enterString("Podaj nazwę użytkownika: ");
		if(store.findUser(name)!=null) throw (new Exception("Użytkownik o takiej nazwie już istnieje"));
		User newUser = new User(name, UI.enterString("Podaj imię i nazwisko właściciela konta: "), UI.enterString("Podaj adres zamieszkania: "), (UI.enterString("Podaj nowe hasło: ").hashCode()));
		store.addUserToList(newUser);
	}
	
	/** Metoda realizująca logowanie jako użytkownik i wyświetlająca panel użytkownika */
	void LoginAsUser() {
		UI.printMessage("Logowanie jako kupujący: \n");
		User user;
		user = store.findUser(UI.enterString("Podaj nazwę konta: "), (UI.enterString("Podaj hasło: ")).hashCode());
		if(user == null) {
			UI.printErrorMessage("Podane hasło jest nieprawidlowe!");
			return;
		}


		while (true){
			UI.clearConsole();
			UI.printMessage("Jesteś zalogowany jako kupujący:");
			UI.printMessage("Nazwa konta: " + user.getName());
			UI.printMessage("Właściciel: " + user.getOwner());
			UI.printMessage("Saldo konta: " + user.getMoneyD() + "\n");

			switch (UI.enterInt(UserPanel + "==>>")){
				case 1:
					UI.printMessage("Saldo konta: " + user.getMoneyD() + "\n");
					break;
				case 2:
					int amount = (int)(UI.enterDouble("Podaj kwotę wpłaty: ")*100);
					try {
						user.payIn(amount);
						UI.printMessage("Poprawnie wpłacono kwotę: " + (((double)amount)/100) + " zł\n");
					} catch (Exception e) {
						UI.printErrorMessage(e.getMessage());
					}
					break;
				case 3:
					UI.printMessage(store.listProducts());
					break;
				case 4:
					UI.printMessage(store.listProducts());
					try{
						Product product = store.findProduct(UI.enterLong("Podaj ISBN książki, którą chcesz kupić: "));
						store.buyProduct(product, user, UI.enterInt("Podaj ilość sztuk: "));
						UI.printMessage("Transakcja zakończona pomyślnie!");
					} catch (Exception e) {
						UI.printErrorMessage(e.getMessage());
					}
					break;
				case 5:
					UI.printMessage(user.printOrderHistory());
					break;
				case 6:
					try {
						user.setPass(UI.enterString("Podaj stare hasło: ").hashCode(), UI.enterString("Podaj nowe hasło: ").hashCode());
						UI.printMessage("Hasło zostało zmienione!\n");
					} catch (Exception e){
						UI.printErrorMessage(e.getMessage());
					}
					break;
				case 0:
					user = null;
					UI.printMessage("Nastąpiło wylogowanie z konta!\n");
					return;
			}
		}
	}
}
