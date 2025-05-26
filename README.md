# Travel Space BackEnd

## Descrizione:

Backoffice per la gestione di entità quali città e attrazioni turistiche ad essa collegate. Gestione di tutte le operazioni CRUD di città e attrazioni turistiche con persistenza dei dati garantita da database mysql. Implementato con pattern MVC per divisione logica più manutenibile e successivamente con REST-API per utilizzo endpoint in app react. Sicurezza garantita tramite Spring Security.

### Tecnologie utilizzate:

-Database: Mysql, hibernate e JPA Repository per creazione servizi
-Backoffice: Java e Spring framework(Spring boot, devtools, spring security), Thymeleaf come template engine per utilizzo variabili in template html, Valid per validazione campi form, Maven per gestione dipendenze

#### Setup e installazione:

git clone https://github.com/Andre050101/Travels_Space_BE
cd travels_space

creazione database su mysql in locale con CREATE DATABASE nome_db

aggiornamento impostazioni application.properties

Scaffholding secondo pattern MVC

avvio: ./mvnw spring-boot:run o da tasto play vscode in file NomeProgettoApplication.java

##### Folder principali:

-controller: contiene cityController, TouristAttractionController, CityPhotoController e TouristAttractionController per gestione delle medesime, più relativi restController e adminUserInitializer per creare profilo admin

-exception: gestione notFound

-model: entità City, TouristAttraction, CityPhoto, TouristAttractionPhoto per gestione risorse del progetto e user e role per autenticazione;

-Repository: un repository che estende JPARepository per ogni entità, per creazione servizi

-Service: Servizi per ogni entità implementate con repository annessi

-Security:
1- DatabaseUserDetails: implementa UserDetails e quindi i suoi annessi metodi isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, ...
2- DatabaseUserDetailService: implementa UserDetailsService e quindi loadUserByUsername
3- SecurityConfiguration: contiene la SecurityFilterChain per i permessi, inizializza
authenticationProvider settando UserDetailsService con databaseUserDetailService e passwordEncoder con createDelegatingPasswordEncoder
