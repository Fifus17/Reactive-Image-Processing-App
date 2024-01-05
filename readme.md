# Changelog


## M1 - 15.12.2023

- **Inicjalizacja projektu**
- **Podpięcie testowej bazy H2**
- **Zrealizowanie architektury**
  ![Architektura](arch.png)
- **Dodanie przyjmowania pojedynczego zdjęcia w requeście**
- **Dodanie zapisywania zdjęć w bazie**
- **Dodanie GET endpointów do pobierania zdjęć z bazy**
- **Dodanie przyjmowania wielu zdjęć w requeście**
- **Dodanie tworzenia miniatur**
- **Dodanie reaktywnego przetwarzania wysłanych zdjęć**

## Najbliższe plany
- **Zmiana bazy H2 na docelową bazę aplikacji**
- **Stworzenie cache'u po stronie bazy, aby uodpornić aplikację na crash'e**
- **Frontend i komunikacja**
- **Dodanie testów**
- **Dodanie rozróżnienia w odpowiedzi endpoitów czy proces zmiejszania obrazu się nie powiódł, czy jeszcze nie został wykonany**


## Endpunkty

### 1. Testowy Endpunkt

- **Metoda:** `GET`
- **Ścieżka:** `/photos/test`
- **Opis:** Testowy endpoint zwracający napis "Testing" w odpowiedzi.

### 2. Upload pojedynczego pliku

- **Metoda:** `POST`
- **Ścieżka:** `/photos/upload`
- **Parametry:** `file` (MultipartFile) - przesyłany plik
- **Opis:** Endpoint umożliwiający przesyłanie pojedynczego pliku. Tworzy bilet w bazie danych, przetwarza plik za pomocą `HandlingService` i zwraca odpowiedź, czy plik został pomyślnie przesłany.

### 3. Upload wielu plików

- **Metoda:** `POST`
- **Ścieżka:** `/photos/upload/bulk`
- **Parametry:** `files` (List\<MultipartFile\>) - lista przesyłanych plików
- **Opis:** Endpoint umożliwiający przesyłanie wielu plików. Podobnie jak poprzedni endpoint, tworzy bilet w bazie danych, przetwarza pliki za pomocą `HandlingService` i zwraca odpowiedź.

### 4. Pobieranie zdjęcia po identyfikatorze

- **Metoda:** `GET`
- **Ścieżka:** `/photos/{imageId}`
- **Parametry:** `imageId` (Long) - identyfikator zdjęcia, `imageSize` (opcjonalny, domyślnie "original") - rozmiar zdjęcia
- **Opis:** Endpoint umożliwiający pobieranie zdjęcia o określonym identyfikatorze. Zwraca odpowiedź zawierającą obraz w formie bajtów.

### 5. Pobieranie zdjęć związanych z biletem

- **Metoda:** `GET`
- **Ścieżka:** `/photos/tickets/{ticketID}`
- **Parametry:** `ticketID` (String) - identyfikator biletu, `imageSize` (opcjonalny, domyślnie "original") - rozmiar zdjęcia
- **Opis:** Endpoint umożliwiający pobieranie zdjęć związanych z danym biletem. Zwraca odpowiedź zawierającą listę obrazów w formie bajtów.

### 6. Pobieranie wszystkich zdjęć

- **Metoda:** `GET`
- **Ścieżka:** `/photos/photos`
- **Parametry:** `imageSize` (opcjonalny, domyślnie "original") - rozmiar zdjęcia
- **Opis:** Endpoint umożliwiający pobieranie wszystkich zdjęć. Zwraca odpowiedź zawierającą listę obrazów w formie bajtów.

## M2 05.01.2024

- **Stworzenie frontu w JavaFX**
- **Podpięcie bazy MongoDB w chmurze**
- **Aplikacja backendowa po wstaniu sprawdza czy zostały jakieś nieprzeprocesowane zdjęcia**
- **Odświeżanie frontu co 2s względem bazy**
- **Wybór miniatur po rozmiarze**
- **Podgląd oryginalnego zdjęcia**
- **Placeholder po stronie frontu podczas przetwarzania zdjęcia**
- **Dodawanie plików do aplikacji frontendowej poprzed drag & drop**
- **Wysyłanie zdjęć do zminimalizowania dodanych we froncie**

## Instrukcje

Aby cały projekt zadziałał, należy włączyć zarówno backend oraz osobno frontend. Po wysłaniu ~5 plików możemy zaobserwować placeholdery w miejscach jeszcze nieprzetworzonych obrazków.

## Najbliższe plany

- **Dodanie do komunikacji message brokera, żeby front dostawał informację kiedy coś zostało zapisane do bazy żeby się zaktualizował**
- **Wymagania M3**

  ![App M2](app-m2.png)