SYSTÉMOVÉ INFORMACE:

Min. SDK = 21
Target SDK = 32

Aplikace testována na virtuálním stroji s API 28, i na Android verze 13.



POPIS APLIKACE:

Aplikace je tvořena dvěma samostatnými jednotkami (aplikacemi), 
které odděluje právě úvodní menu aplikace, na které uživatel 
narazí po spuštění aplikace. 

Pokud uživatel zvolí aplikaci 1. verze (Protokol):
- Aplikace uživateli umožňuje práci 
s databází uživatelů (přidávání a odebírání). 
Tato aplikace je také připravena na další práci se serverem,
který bude pracovat s daty z databáze.

Při výběru aplikace 2. verze (Firebase):
- Aplikace uživateli umožňuje práci s kryptografickými primitivy. 
Po stisknutí tlačítka Crypto v menu, v dolní části obrazovky si uživatel zvolí požadované primitivum, zadá vstupní text a případně klíče (pokud to primitivum vyžaduje) a po následném stisknutí tlačítka ZAŠIFROVAT se provede požadovaný algoritmus zvoleného primitiva. Pokud to primitivum umožňuje, tak po stisknutí tlačítka DEŠIFROVAT, proběhne dešifrování primitiva a vypsání původní verze textu.
- Aplikace dále umožňuje uživateli práci s QR kódy. Po zadání vstupního libovolného textu nebo přihlašovacích údajů k službě Firebase (ve formátu email mezera heslo) je vygenerován patřičný QR kód. Po načtení tohoto QR kódu (na jiném zařízení) přes tlačítko SCAN, je možné přihlášení do systému služby Firebase, kde se data automaticky převedou do formulářů pro přihlášení. Pokud uživatel není v systému zaregistrován, může tak učinit po stisknutí na KLIKNOUT PRO REGISTRACI.
- Do služby Firebase je možné se přihlásit i bez načtení QR kódu po stisknutí více možností v pravém horním rohu na stránce s QR kódy.

Kdekoli se po stisknutí na tlačítko HOME (v menu) dostanete na původní rozcestník mezi jednotlivými aplikacemi (menu).





