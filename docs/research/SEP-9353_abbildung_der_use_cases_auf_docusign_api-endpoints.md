`SEP-9353`
---
# *Abbildung der Use Case aif DocuSign Api-Endpoints*
# Use Cases 

#### (1) Ein Dokument soll von einem Nutzer unterschrieben werden.
   * Der Nutzer soll das Dokument innerhalb einer bestimmten Zeit unterschreiben.

##### Envelopes::createEnvelope
    
   * **'status'** Parameter gesetzt auf **'created'** erzeugt ein envelope ohne es zu versenden
   * **'status'** Parameter gesetzt auf **'sent'** sendet den envelope zum Empfänger
    
##### Envelopes::createRecipientView
     
   * returnt die URL und bettet die Empfängersicht ein
   * URL muss, nach dem Generieren, innerhalb von Minuten aufgerufen werden 


**Frist?**
  * laut dieser [Antwort](https://support.docusign.com/s/question/0D51W000069k7T2SAI/can-you-set-the-time-and-date-that-an-envelopes-expires) (stand 2018) 
  im Supportforum von DocuSign ist es nicht möglich, ein individuelle Frist für einzelne Envelopes zu setzen.
  * die DocuSign Website erlaubt allerdings das setzten einer Frist -> Untersuchung, was diese Frist bewirkt ***TODO***
  * ein Workaround wäre, das Dokument nach Ablauf einer Frist zu sperren - allerdings müsste die Frist dann außerhalb von DocuSign implementiert werden
  
  ##### Envelopes::createLock (*workaround*)
  
  * sperrt ein Envelope
  
*** 
(ist schon umgesetzt!)
#### (2) Ein Dokument wird als Entwurf (mit Wasserzeichen versehen) zur Ansicht an einen Nutzer gegeben, der im Falle einer Zustimmung den Entwurf signiert.
   * Der Nutzer kann den Entwurf im Falle der Ablehnung kommentieren. 
   


##### Envelopes::getDocument
* **'watermark'** Parameter vorhanden
    * wenn auf 'true' gesetzt, dann ist der watermark feature freigegeben und der envelope ist unvollständig
##### Envelopes::createEnvelope
* **'status'** gesetzt auf **'created'** speicher den envelope als Entwurf
    * ohne jeweilige Notification wurde der Entwurf nach 30 Tagen gelöscht
* **'decline'** tab erlaubt dem Empfänger den envelope abzulehnen
    * unter dem Parameter **'declineReason'** kann ein Ablehngrund angegeben werden

***

#### (3) Ein Dokument soll von mehreren Nutzern unterschrieben werden.
   * Beteiligte Nutzer unterschreiben das Dokument gleichzeitig.
   * Beteiligte Nutzer unterschreiben das Dokument in einer bestimmten Reihenfolge.

##### SigningGroups::createList
* man kann eine Signiergruppe erstellen
* wenn ein Gruppenmitglied den envelope öffnet, ist der envelope temporär für andere Gruppenmitgleider gesperrt
    * -> also nicht ganz gleichzeitig? *gleichzeitig im Sinne von beliebiger Reihenfolge der Unterzeichnungen*
##### Envelopes::createRecipient
* unter **'routingOrder'** bestimmt man die Reihenfolge unterschreibenden Empfänger



#### (4) Mehrere Dokumente sind an einem Unterschriftenprozess beteiligt, von denen eines unterschrieben werden muss während die anderen als Zusatzdokumente beiliegen müssen, aber keiner Unterschrift bedürfen

##### Envelopes::createRecipient
* Es gibt verschiedene Empfängertypen
    * darunter Empfänger die entweder unterschreiben müssen oder nicht unterschreiben müssen
***

##### Envelopes::putAttachments

* man kann Anlagen hinzufügen
* allerdings kann man diese **nicht** als "muss unterschrieben werden" kennzeichnen

* Dokumente als "muss unterschrieben werden" kennzeichnen?
  * Supplemental Documents : https://developers.docusign.com/esign-rest-api/guides/concepts/documents
  * ***TODO*** noch unklar, wie Dokumente im Envolpe klassifiziert werden können

##### Envelopes::createEnvelope &rarr; Document.signerMustAcknowledge

* ein Dokument kann als [Supplement](https://developers.docusign.com/esign-rest-api/guides/concepts/documents#supplemental-documents) deklariert werden
* mit `signerMustAcknowledge` kann festgelegt, wie der Empfänger das Dokument zu bestätigen hat (!= unterschrift)
	* garnicht (`no_interaction`)
	* gesichtet (`view`)
	* akzeptiert (`accept`)
	* gesichtet & akzeptiert (`view_accept`);
	* gesichtet & gelesen & akzeptiert (`view_read_accept`)

#### (5) Man soll benachrichtigt werden wenn ein Kunde ein Dokument signiert, ablehnt oder sonstig damit interagiert hat.

* es werden dafür webhooks verwendet
* die von DocuSign geschickten Informationen zum Status eines Envolpes enthalten Information zum Status jedes einzelnen Empfängers (wenn entsprechend konfiguriert)
	* die Reihenfolge der Unterzeichnungen ist aus diesen Informationen ablesbar (`RoutingOrder<int>`)

* ##### Envelopes::createEnvelope
    * unter '**envelopeEventStatusCode**' wird der envelope Status angegeben unter welchem ein webhook aufgerufen werden soll
        * Values: Draft, Sent, Delivered, Completed, Declined, or Voided
    * '**recipientEventStatusCode**' sendet mit einer webhook Notifikation den status eines Empfängers
        * Sent, Delivered, Completed, Declined, AuthenticationFailed, and AutoResponded
    * '**envelopeEvents**': eine Liste von envelope-level Status welche **Connect** zum Senden der Updates zu den Endpunkten in der URL property triggert
    * '**recipientEvents**': ein Array von Empfänger event Status welche **Connect** triggern eine Notifikation zum webhook listener am URL Endpunkt zu senden