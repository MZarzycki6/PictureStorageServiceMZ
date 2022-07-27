# Aplikacja do obsługi systemu przechowywania obrazków i metadanych obrazków

## Założenia teoretyczne

- REST API do przesłania/modyfikacji/pobrania/usunięcia obrazka i do generowania linków dostępu dla użytkowników nieautoryzowanych
- wykorzystanie bazy PostgreSQL, systemu kolejek RabbitMQ i cache Hazelcast
- przetworzenie obrazka do postaci thumbnail przed zapisem
- OIDC: Keycloak z uzytkownikami typu user i admin
- Testy jednostkowe i integracyjne
- Architektura mikroserwisowa

### Aktualne endpointy 

#### (w przypadku aplikacji w Main portem aplikacji jest 8080, jak na przykładzie "localhost:8080/getAllPictures", zaś w przypadku brancha dot. Keycloaka został on zmieniony na 8888, żeby keycloak mógł wystartować na 8080):
  - @GET /getAllPictures - pobiera listę wszystkich obrazków z bazy danych 
  - @GET /getPicture?id=1 - pobiera z bazy obrazek o identyfikatorze 1 
  - @POST /addPicture - zapisuje obrazek w bazie. Obrazek należy przesłać w body w postaci JSON o następujących polach:
  
        "name": String, nazwa która ma być przypisana do obrazka,
        
        "description": String, opis obrazka,
        
        "encodedImage": String, obrazek zakodowany w Base64, który zostanie obrobiony przez narzędzia obróbki obrazu do rozmiarów 100x100 px (Tools package), a następnie zapisany w DB jako large object (LOB).       
  
  
  - @DELETE /deletePicture?id=1 - usuwa obrazek o wybranym id z bazy.

### Obiekt obrazka

Obrazek w systemie posiada na chwilę obecną następujące parametry (pola w tabeli w DB):

Long id - generowane automatycznie dla kazdego nowego obrazka poprzez id_ostatniego_obrazka+1

String name - nazwa obrazka

String description - opis obrazka

@LOB String encodedImage - zakodowany w Base64 obrazek(thumbnail).


## Wykonano:
- Rest API do przesłania/usuwania/pobrania(wybranego lub wszystkich) obrazów
- zestaw narzędzi służących do obróbki obrazu do postaci 100x100 (thumbnail) wraz z testami dla tych narzędzi
- zintegrowano bazę PostgreSQL z projektem
- dodano system kolejek RabbitMQ dla zapisywania nowego obrazka i usuwania obrazka
- dodano cache Hazelcast dla zapytania pobrania konkretnego obrazka po id i przetestowano poprzez pomiar czasów odpowiedzi dla dwukrotnie powtórzonego tego samego zapytania
- dodano Keycloak jako dostawcę zabezpieczeń z wyszczególnieniem 2 typów użytkowników, user i admin

## TODO: 
- Metoda i endpoint REST do modyfikacji wybranego obrazka z wykorzystaniem zaptania UPDATE w DB - tutaj nie sądzę aby były jakieś problemy
- Metoda i endpoint REST do generowania czasowych linków dostępu do danych dla nieautoryzowanych użytkowników - do zrobienia po zaimplementowaniu autoryzacji - nalezałoby wówczas dodać do obiektu obrazka pole, które przechowywałoby dane nt. jego właściciela
- Podział aplikacji na formę architektury mikroserwisowej
- Uzupełnienie testów
- Uzupełenienie wykorzystania systemu kolejek i cache dla wszystkich zapytań
- Cleanup
- Mozna by na koniec wystawic demonstracyjnie aplikację na platformie np. Heroku


