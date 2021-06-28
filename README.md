# Waldemar Smagacki
## QUIZER

### Nauka programowania
Aplikacja wspomagająca naukę programowania poprzez wykonywanie gotowych quizów oraz samodzielne tworzenie nowych quizów. Tematyka quizów obejmuje zagadnienia z obszaru języków programowania. Funkcjonalność rejestracji użytkowników pozwala gromadzić historię aktywności. Możliwe jest śledzenie i analizowanie swoich postępów w nauce. Sekcja statystyk pozwala analizować swoje postępy w relacji do innych użytkowników aplikacji.

### Opis programu

#### Technologie
Program został napisany w oparciu o następujące technologie:
- Java
- Maven
- Spring Boot
- Thymeleaf
- MySQL, PostgreSQL

#### Dane testowe
Podczas uruchomienia programu w bazie danych umieszczany jest zestaw gotowych pytań quizowych.
Pytania obejmują języki: Java, JavaScript, SQL.

#### Logowanie
Na ekranie startowym programu dostępne są opcje rejestracji nowego usera oraz logowanie usera istniejącego.

#### Główny panel obsługi quizów
Na ekranie do obsługi quizów dostępne są opcje:
- rozpoczęcie nowego quizu
- dodawanie nowych pytań do kartoteki pytań
- statystyka bieżącego usera
- statystyki wszystkich userów

#### Logika biznesowa
Przyjęto, że każdy quiz zawiera 5 losowych pytań z całego zakresu dostępnych pytań.
Zmiany ilości pytań należy dokonać bezpośrednio w kodzie programu.
Każde pytanie zawiera od 2 do 4 odpowiedzi, z których tylko jedna jest prawidłowa.
Rozpoczęcie danego quizu oraz jego przebieg rejestrowany jest w bazie danych programu.

Po skończeniu każdego quizu wyświetlane jest podsumowanie.
W każdym momencie z poziomu głównego menu istnieje możliwość podejrzenia statystyk bieżącego usera oraz statystyk dla wszystkich userów.
Statystyka wszystkich userów przedstawia swego rodzaju ranking trafności odpowiedzi dla wszystkich userów.
Tabela z wynikami jest sortowana malejąco wg trafności wszystkich dotychczasowych odpowiedzi.

Istnieje możliwość dodawania własnych pytań. Podczas dodawania pytania należy:
- wybrać język programowania jakiego dotyczy pytanie
- wpisać tekst pytania
- wpisać prawidłową odpowiedż
- wpisać przynajmniej jedną złą odpowiedź (maksymalnie 3)

----------------------------------------------------------------------------------------------------------------------------

## Link do aplikacji na Heroku
https://smagacki-quizer.herokuapp.com/
