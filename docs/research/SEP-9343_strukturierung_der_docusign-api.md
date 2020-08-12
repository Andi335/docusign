`SEP-9343`
---
# Strukturierung der DocuSign-API

## Account
* erstellen und löschen der Accounts
* herausfiltern der Account-Informationen und ihrer Bedeutung
* markieren des Accounts mit Farben, Message Text
* Account Gebühren
* Endpunkte
    * **AccountBrands**
        * bietet Methoden die das Erstellen und löschen der Account Brands in einem Account ermöglichen
        * "Branding" sorgt für den Look des Brands der Organisation beim Senden, Signieren, und Email Prozess zum leichteren Erfassen der Organisation im Envelope
        * es gibt ein DocuSign Account Branding feature welches das Anpassen der Farben, Logo und Text ermöglicht
        * man kann mehrere Brand Profile mit verschiedenen Einstellungen speichern
        * das erstellen und ändern der branding Profile beinflusst jeden der das Profil nutzt und alle envelopes die mit diesem Profil versendet wurden
    * **AccountConsumerDisclosures**
        * bietet Methoden welche das Abrufen der Consumer Angaben ermöglicht die mit dem Account zu tun haben
    * **AccountCustomFields**
        * bietet eine Methode welche das Abrufen der benutzerdefinierten "fields" in einem Account ermöglicht
        * diese fields können zum Speicher von Informationen über die envelopes verwendet werden
        * die fields können vom DocuSign User eingesehen werden (beim Erstellen des envelopes) aber nicht vom envelope Empfänger
    * **AccountPasswordRules**
        * bietet Methoden welche erlauben die Update Account Passwort Regeln, Mietgliedschaft und Accoutn Regeln zu erhalten
    * **AccountPermissionProfiles**
        * bietet Methoden um permission Profile zu managen
    * **AccountSealProviders**
    * **AccountSignatureProviders**
        * bietet Informationen zu Standard basierten Signatur Anbietern welche für den Account bereitgestellt wurde
    * **AccountTabSettings**
        * bietet Methoden die das Managen der Tab Einstellungen erlaubt
    * **AccountWatermarks**
        * bietet Metoden welche das Einsehen der preview und update Wasserzeichen Informationen ermöglichen
    * **Accounts**
        * bietet Methoden zum Erstellen, Löschen und managen des Accounts
    * **ENoteConfigurations**
        * bietet Methoden zum managen der Informationen zur Integration zu eNote eOriginal
    * **IdentifyVerifications**
    

***
## Connect
* ermöglicht Anfrage via HTTPS
* erstellen der connect Configurations (manuell per Web Service oder programmtechnisch durch API)
* abrufen und managen des event logs der connect Configurations
* 
* Endpunkte
    * **ConnectConfigurations**
        * die Methoden ermöglichen das konfigurieren der DocuSign Connect Services welches mit dem Account gebunden ist
    * **ConnectEvents**
        * bietet Methoden zum lesen, löschen und aktualisieren des connect logs welches mit einem envelope gebunden ist
        
        
   
   
    
## Envelopes
* enthält Ressourcen und Methoden für das Senden und managen der envelopes und envelope Daten
* Endpunkte
    * **ChunkedUploads**
        * bietet Methoden für Integritäts Checks und Hinzufügen, Commiten, Abrufen, Initiieren und Löschen von bulk Uploads
    * **DocumentResponsiveHtmlPreview**
    * **EnvelopeAttachements**
        * bietet Methoden zum managen der Anlagen
    * **EnvelopeConsumerDisclosures** 
        * bietet Methoden zum Abrufen der Consumer Offenlegeung für ein envelope
    * **EnvelopeCustomFields**
        * bietet Methoden zum managen der benutzerdefinierten fields in einem envelope
    * **EnvelopeDocumentFields**
        * bietet Methoden zum managen der benutzerdefinierten fields in einem Dokument
        * man kann benutzerdefnierte Versionen der Standard fields erstellen welche eine Kombination aus documentField Eigenschaften sind (wie z.B. font type, size, Validierungseigenschaft)
    * **EnvelopeDocumentHtmlDefinitions**
    * **EnvelopeDocumentTabs**
        * bietet Methoden zum managen verschiedener tabs in envelopes
    * **EnvelopeDocumentVisibility** 
        * bietet Methoden zum managen der Documenten Sicht und Einsicht in einem envelope
    * **EnvelopeDocuments**
        * managed die Dokumente im envelope
        * man kann ein oder mehrere Dokumente einem envelope hinzufügen 
        * ein oder mehrere Dokumente aus einem envelope abrufen
        * Dokumente aus einem envelope löschen
        * alle Methoden operieren nur bei existierenden envelopes
    * **EnvelopeEmailSettings**
        * Methoden zum managen der Email override Einstellungen für einen envelope
        * es werden Email Adresse für die Rückmeldung, Name, BCC für Email Archiv Informationen für den envelope geändert
        * (es werden nur Email Kommunikationen beeinflusst die nach den Änderungen entstehen)
    * **EnvelopeFormDat**
        * managed forms in einem envelope
    * **EnvelopeHtmlDefinitions**
        * managed Sperrungen in einem envelope
        * man kann envelope blockieren und eine Zeit setzen bis die Sperrung aufgelöst wird
    * **EnvelopeRecipientTabs**
        * Methoden ermöglichen das Hinzufügen, Updaten und Löschen von tabs aus einem envelope
    * **EnvelopeRecipients**
        * managed die Empfänger eines envelopes
        * (es gibt 7 verschiedene Empfängertypen)
    * **EnvelopeTemplates**
        * Methoden zum Hinzufügen und löschen von Templates auf envelopes und Dokumente
    * **EnvelopeViews**
        * Methoden geben URLs die man in die Applikation einbetten kann um Zugriff auf die DocuSign UI zu gewährleisten
        * Views: 
            * Console View
            * Correct View
            * Edit View (gleiche Funktionen wie bei Sender)
            * Recipient View
            * Sender View
    * **Envelopes**
        * Methoden erlauben das Manipulieren und Überwachen der envelopes
        * Ist der User authetifiziert kann er die createEnvelope Methode zum erstellen eines envelopes nutzen
        * mit EnvelopeDocuments Methode update oder updateList kann man Dokumente weitere Dokumente hinzufügen
        * stellt man die "status" Eigenschaft im envelope auf "sent", kann man es senden; auf "created" kann man es als Entwurf speichern
        * man kann envelope event Notifikationen erhalten durch anpassen der "eventNotification" Einstellung
        * neben den Notifikationen kann man desweiteren den Status der envelopes mit folgenden Methoden überwachen
            * getEvelope - status des envelopes
            * listStatus - envelope Status für eine Menge an envelopes
            * listStatusChanges - Status-Änderungen-Informationen für eine oder mehrere envelopes
        * zum löschen einer Seite aus dem Dokument im envlope: deleteDocumentPage
        * es beinhaltet noch ein paar Methoden die das Abrufen und setzen der Inintialien und Signaturen für bestimmte Empfängertypen auf einem Dokument erlauben
    * **NotaryJournals**
    * **ResponsiveHtmlPreview**
***
## SignignGroups
* managed Signing Gruppen und erlaubt jedem in der Gruppe ein Dokument signieren zu lassen
* es werden signing gruppen erstellt und managed die User in der Gruppe
* Endpunkte
    * **SigninGroupUsers**
        * Methoden zum managen der User in Signier Gruppen
    * **SigningGroups**
        * managed Signier gruppen
        * erstellen von Gruppen aus Leuten die ein envelope gesendet bekommen
        * jedes Mitglied kann Dokumente im envelope signieren
        * öffnet ein Mitglied ein envelope, ist dieser für andere Mieglieder temporär gesperrt bis das Mitglied den envelope verlässt
        * ist der envelope fertig, bekommen alle Gruppenmitglieder eine Benachrichtigung 
        * die envelope history und das Zertifikat der Fertigstellung zeigt, dass der envelope einer Signiergruppe gesendet wurde und hält fest welche Mitglieder den envelope gesehen und signiert haben
        * max. 50 Signier Gruppen pro Account
        * max. 50 Gruppenmitglieder pro Signiergruppe
        * (Siegniergruppen gibt es nur in bestimmten DocuSign Angeboten)

## Templates
* managed Account Templates
* man kann templates erstellen, listen, updaten und löschen
* managed Template Benachrichtungen und group sharing Einstellungen
* Templates können programmtechnisch erstellt werden oder via DocuSign web Interface und anschließend in der Applikation genutzt werden
* Endpunkte
    * **TemplateBulkRecipients**
        * Methoden managen die bulk Empfänger Datei für einen Template
    * **TemplateCustomFields**
        * Methoden zum managen der benutzerdefinierten fields in einem Template
    * **TemplateDocumentsFields**
        * Methoden zum managen der benutzerdefinierten fields in einem Dokument
    * **TemplateDocumentHtmlDefinitions**
    * **TemplateDocumentResponsiveHtmlPreview**
    * **TemplateDocumentTabs**
    * **TemplateDocumentVisibility**
    * **TemplateDocuments**
        * Methoden zum managen von Dokumenten in einem Template (hinzufügen,abrufen,löschen)
    * **TemplateHtmlDefinitions**
    * **TemplateLocks**
        * Methoden zum managen von Sperren auf einem Template
    * **TemplateRecipientTabs**
        Methoden zum hinzufügen, updaten und löschen von tabs aus einem envelope
    *  **TemplateRecipients**   
        * managed die Empfänger in einem Template
        * die Parameter die zu einem Empfänger gehören sind vom Empfängertypen abhängig
        * Agents, Carbon Copies, Certified Deliveries, Editors, In preson Signer, Intermetiares, Signers
    * **TemplateResponsiveHtmlPreview**
    * **TemplateViews**
        * Methoden geben URL zurück in die Applikation eingebettet werden können um Zugang zu DocuSign UI anzubieten
        * ein template View: Edit View
        * (ist mit EnvelopeViews verwandt, beide sind für DocuSign UI Einbettung zuständig)
    * **Templates**
        * Methoden zum Manipulieren und Überwachen von Templates
        * ist der User authentifiziert kann man mit createTemplate ein Template erstellen
        * mit update oder updateList (TemplateDucument) können mehrere Dokumente hinzugefügt werden
        * neben den Notifikationen kann man desweiteren den Status der Templates mit folgenden Methoden überwachen
             * getTemplate - status des Templates
             * listStatus - envelope Status für eine Menge an Templates
             * listStatusChanges - Status-Änderungen-Informationen für eine oder mehrere Templates
        * zum löschen einer Seite in einem Dokument im Template: deleteDocumentpage
        * es gibt noch weitere Methoden zum Abrufen und setzen der Initialien und Signaturen für bestimmte Typen an Empfänger im Dokument
## UserGroups
* managed permission Gruppen
* erstellt und löscht Gruppen
* fügt User in Gruppen ein und kann diese dort wieder löschen
* managed die brand Informationen die mit einer Gruppe zu tun haben
* Endpunkte
    * **GroupBrands**    
        * Methoden zum managen von brands in einer Gruppe
    * **GroupUsers**
        * Methoden zum managen der User in einer Gruppe
         
    
## Users
* managed User im Account
* erstellt benutzerdefnierte User Einstellungen
* managed das Profil des Users
* fügt User hinzu und kann diese löschen
* fügt Initialien und Signature Abbildungen für einen User ein und kann diese löschen
* Enpunkte
    * **Contacts**
        * Methoden zum managen von Kontakten
    * **UserCustomSettings**
        * Methoden zum managen von benutzerdefinierten Einstellungen für einen User
        * benutzerdefnierte Eigenschaften sind eine flexible Möglichkeit uzm Speichern und Abrufen von benutzerdefinierten User Informationen die anschließend im eigenen System genutzt werden können
        * alle benutzerdefinierten User Einstellungen sind für einen einzelnen User auf 4000 characters limitiert, dazu gehören xml und json Strukturen
    * **UserProfiles**
        * Methoden zum managen der des User Profils
    * **UserSignatures**
        * Methoden zum managen der Initialien und Signaturen Abbildungen für einen User
    * **Users**
        * Methoden zum Managen der User eines Accounts
    