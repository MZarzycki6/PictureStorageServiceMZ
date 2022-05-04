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
- dodano cache Hazelcast i przetestowano poprzez pomiar czasów odpowiedzi dla dwukrotnie powtórzonego tego samego zapytania

## TODO: 
- Metoda i endpoint REST do modyfikacji wybranego obrazka z wykorzystaniem zaptania UPDATE w DB - tutaj nie sądzę aby były jakieś problemy
- autoryzacja użytkowników z wykorzystaniem Keycloak i podziałem na user/admin - wykryto błąd polegający na niepoprawnej weryfikacji tokenów i w konsekwencji ciągłego zwracania 401.Unauthorized. Opis prób rozwiązania błędu znajduje się poniżej.
- Metoda i endpoint REST do generowania czasowych linków dostępu do danych dla nieautoryzowanych użytkowników - do zrobienia po zaimplementowaniu autoryzacji - nalezałoby wówczas dodać do obiektu obrazka pole, które przechowywałoby dane nt. jego właściciela
- Podział aplikacji na formę architektury mikroserwisowej
- Uzupełnienie testów
- Uzupełenienie wykorzystania systemu kolejek dla wszystkich zapytań
- Zespolenie wszystkich plików yml i .properties w jeden
- Mozna by na koniec wystawic demonstracyjnie aplikację na platformie np. Heroku

## Problem ciągłego zwracania błędu 401 Unauthorized przy zastosowaniu Keycloak

Problem polega na tym, że niezależnie od zastosowanej metody implementacji, ciągle, na każde zapytanie, nawet takie które nie powinno być zabezpieczone, zwracany jest błąd 401. Należy przy tym zauważyć, że tokeny są generowane prawidłowo, tzn po podaniu w Postmanie w "authorization" poprawnych danych uwierzytelniających zarówno dla użytkownika jak i admina, uzyskiwany jest poprawny (sprawdzane przez jwt.io) token. Problem leży najprawdopodobniej po stronie konfiguracji bezpieczeństwa w aplikacji (Security.SecurityConfig w Keycloak branch), która nie autoryzuje zapytań zawierających poprawne tokeny. Znaleziono problemy o podobnej tematyce pod adresami: https://keycloak.discourse.group/t/always-receiving-error-401-from-web-app-using-keycloak/3922/6 oraz https://stackoverflow.com/questions/46882610/keycloak-api-always-returns-401, jednak nie udało się uzyskać na ich podstawie rozwiązania. Próbowano wykorzystać i modyfikować 3 znalezione rozwiązania: poprzez uwzględnienie zabezpieczeń w .properties file np. 

keycloak.securityConstraints[0].authRoles[0] = admin
keycloak.securityConstraints[0].securityCollections[0].patterns[0] = /getPicture*,

poprzez dodanie i edycję klasy adaptera SecurityConfig takiej, jaka jest dostępna w https://www.keycloak.org/docs/latest/securing_apps/#_spring_security_adapter

i poprzez wykorzystanie tej samej klasy z dodatkową adnotacja @EnableGlobalMethodSecurity(jsr250Enabled=true) i dodawanie @RolesAllowed w PictureController.

Zadna z tych prób nie okazała sie być do tej pory skuteczna. 



