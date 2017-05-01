import java.io.Serializable;

/**
 * Created by barto on 21.10.16.
 *
 * Program: BasicOnlineStore
 * Autor: Bartosz Rodziewicz, 226105
 * Data: 21 pazdziernika 2016
 *
 * Klasa reprezentująca poszczególne produkty, jak i zamówienia. 
 *
 */

/**
 * Klasa reprezuntuje poszczególne produkty dostępne w sklepie. Jest również wykorzystywana jako reprezentacja konkretnego zamówienia.
 *
 * Każdy produkt (w sklepie dostępne są tylko książki) posiada swój numer identyfikacyjny ISBN, tytuł, dane autora, cenę i ilość na stanie. Dy objekt klasy jest wykorzystywany jako reprezentacja zamówienia ilość na stanie oznacza ilość zamówionego towaru w zamówieniu.
 *
 *
 * @author Bartosz Rodziewicz, 226105
 * @version 21 października 2016 r.
 */

class Product implements Serializable {
	/** Zmienna wymagana do zapisu programu do pliku */
	private static final long serialVersionUID = 1L;

	/** Numer identyfikacyjny towaru */
	private long ISBN;
	/** Tytuł książki */
	private String title;
	/** Imię i nazwisko autora */
	private String author;
	/** Cena produktu (w groszach */
	private int price; //cana produktu w GROSZACH!!
	/** Ilość towaru na stanie */
	private int amountAvailable;

	String getTitle(){
		return title;
	}

	String getAuthor() {
		return author;
	}

	long getISBN(){
		return ISBN;
	}

	int getAmountAvailable() {
		return amountAvailable;
	}

	void setAmountAvailable(int amountAvailable) {
		this.amountAvailable = amountAvailable;
	}

	void setPrice(int price){
		this.price = price;
	}

	int getPrice() {
		return price;
	}

	Product(long ISBN, String title, String author, int price, int amountAvailable) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.price = price;
		this.amountAvailable = amountAvailable;
	}

	/** @return towar w postaci tekstowej */
	@Override
	public String toString() {
		return String.format("%d - %s - %s, %.2f zl, %d",ISBN,author,title,(((double)price)/100),amountAvailable);
	}
}