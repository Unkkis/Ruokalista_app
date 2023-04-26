# Ruokalista_app

App made with Spring Boot, front with Thymeleaf.
Live version in ruokalista.unkkis.com


--------------

Appsi, johon voi tallettaa eri ruokia (reseptejä?)

appsi myös arpoo ruokalistan viikoksi (tai useammaksi) (yksi ruoka/päivä) tietokannassa olevista resepteistä.
  - samalla viikolla ei kahta kertaa samaa ruokaa (tai jos useampi viikko, niin kahdella perättäisellä ei samaa, jos ruokia riittää)


Appsi arpoo myös yhden reseptin.

Databasessa taulut siis (tämä ei vielä valmis):
- ruoka ja siihen tarvittavat ainekset
- ainekset (tämän ja reseptien välillä many-to-many)
- missä tehdään (uuni, pannu, grilli, pata?)


