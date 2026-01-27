# Akademia Tarnowska

## Kurs

Testowanie i Jakość Oprogramowania / Projekt

## Autor

Tomasz Gądek

## Temat projektu

Testowanie gońca na planszy szachowej 8x8

## Opis projektu

**Szachy** to jedna z najstarszych i najbardziej popularnych gier strategicznych na świecie, która rozgrywana 
jest na kwadratowej planszy o wymiarach 8x8. Każdy z graczy kontroluje 16 pionków, w tym króla, królową, wieże, 
gońce, skoczki i piony. Celem gry jest zbicie króla przeciwnika ("szach mat").

**Szachownica** to plansza podzielona na 64 pola o równych bokach. Pola te są kolorowane na przemian na białe i 
czarne, tworząc charakterystyczny szachownicowy wzór. Plansza ta stanowi tło dla rozgrywki i pomaga w identyfikacji 
ruchów pionków.

**Goniec** to jedna z figur w szachach. Każdy gracz kontroluje dwa gońce na początku partii. Gońce poruszają się po 
planszy na skosie. Oznacza to, że każdy goniec zawsze pozostaje na polach o tym samym kolorze (np. biały goniec zawsze 
będzie poruszać się po polach białych, a czarny goniec po polach czarnych).

## Uruchomienie projektu

```bash
mvn spring-boot:run
```

Plansza do gry będzie dostępna pod adresem: `http://localhost:8081/index.html`

## Uruchomienie testów jednostkowych i integracyjnych

```bash
mvn test
```

## Dokumentacja API

- Adres usługi: `/api/chess/is-correct-move`,
- Typ: **POST**,
- Przyjmuje: `{"start": "a_1", "destination":"b_3", "type": "BISHOP"}`,
- Zwraca: *true* lub *false*.

## Scenariusze testowe dla testera manualnego

Oto 10  różnorodnych testów dla **gońca** w grze w szachy:

| Test Case ID | Opis                                                           | Kroki testowe                                                                                          | Oczekiwany wynik                                                              |
|--------------|----------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| TC_001       | Ruch gońca na pole czarne po skosie w lewym górny róg planszy. | 1. Wybierz gońca na polu "c_1". 2. Przesuń gońca na pole "e_3" po przekątnej.                          | Goniec powinien przesunąć się na pole "e_3" po przekątnej.                    |
| TC_002       | Ruch gońca na pole czarne po skosie w prawy górny róg planszy. | 1. Wybierz gońca na polu "f_1". 2. Przesuń gońca na pole "d_3" po przekątnej.                          | Goniec powinien przesunąć się na pole "d_3" po przekątnej.                    |
| TC_003       | Przesunięcie gońca na pole wykraczające poza planszę.          | 1. Wybierz gońca oraz przesuń go poza planszę.                                                         | Goniec nie może przesuwać się poza granice planszy, ruch ten jest nielegalny. |

## Technologie użyte w projekcie

- Java 17
- Maven 3
- Spring-Boot
- Markdown
- HTML
- CSS
- JavaScript
