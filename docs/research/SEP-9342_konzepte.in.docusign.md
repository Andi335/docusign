`SEP-9342`
---
# Konzepte in DocuSign 

## Authentication
* Authorization Code Grant
    * OAuth2
    * jede DocuSign-API Anfrage benötigt ein access token
* Client Applikation fragt Autorisationscode an und kann gegen access token eingetauscht werden welche für API Anfragen benötigt werden
    * Anwendung leitet den User zur DocuSign authorization URI
        * gewähren Zustimmung und bekommen authorization Code
    * Anfrage wird in den Account Server geleitet
* Login URI Syntax:
    * https://account-d.docusign.com/oauth/auth?
                      response_type=code
                      &scope=YOUR_REQUESTED_SCOPES
                      &client_id=YOUR_INTEGRATOR_KEY
                      &state=YOUR_CUSTOM_STATE
                      &redirect_uri=YOUR_REDIRECT_URI

* erhalten des Tokens
    * wird für API Aufruf benötigt
    * mit /oauth/token Endpunkt Zugriff gewähren


* User Account abrufen
    * neben token wird auch die base URI benötigt
        * einzigartig für jeden User auf dem die API gecallt wird
    * für base URI folgender Endpunkt: /oauth/userinfo 
    * access token der Anwendung kommt in den header
    

* eSignature REST API Anfrage
    * dafür wird benötigt
        * access token im Authorization header 
        * die base URI
 
## Benutzeroberfläche
* Übersicht mit
    * Signaturen ausstehend
    * bald ablaufend
    * abgeschlossen
    * wo eine Aktion erforderlich ist
* Envelope senden
    * Dokument wird hinzugefügt oder man benutzt eine Vorlage
    * Empfänger und email wird hinzugefügt
    * man kann eine Vorschau einsehen
    * klickt man auf "weiter" kann man seinem Dokument Formularfelder hinzufügen und anschließend versenden
* Dokument signieren
* Template verwenden
* PowerForm erstellen
* unter "Templates"
    * man kann neue Templates erstellen
    * Templates werden in Ordner gespeichert

## Templates
* Envelope-ähnliche Objekte die man immer wieder für die Erstellung von Envelopes verwenden kann
* für das Editieren eines Templates (Hinzufügen von fields/tabs) wird ein Empfänger benötigt, ansonsten kann man ein Dokument dem Template hinzufügen, es aber nicht mit fields versehen
    * eine Rollenzuweisung reicht auch aus (z.B. Signierer, Viewer...), welche dann später einen Empfänger kriegt

## Envelopes
* allgemeiner Behälter für Transaktionen
    * beinhaltet: 
        * Dokumente für eSignature Transktionen
        * Sender Informationen
        * Empfänger Informationen
        * Status Informationen für Signatur Fortschritt 
* folgende Eigenschaften müssen mindestens da sein
    * emailSubject
        * Gegenstand der email
    * documents
        * Dukument für den Empfänger zum signieren oder anschauen
    * recipients
        * Empfänger welche den envelope bekommen
* die Eigenschaft "status" ist für das Einstellen und berichten des envelope Fortschritts
* envelopes werden per email gesendet an den angegebenen Empfänger (als Link)