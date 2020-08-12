package com.forcont.digsigproto.common.sandbox.signing;


import com.forcont.digsigproto.common.model.RecipientDigSig;
import com.forcont.digsigproto.common.model.WebhookNotification;
import org.joda.time.DateTime;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class ParseWebhooksStatus
{
    private Map<String, List<WebhookNotification>> webhookNotificationsMap = new LinkedHashMap<>();


    public Map<String, List<WebhookNotification>> parseSignerStatusXML(String xml) throws ParserConfigurationException, IOException, org.xml.sax.SAXException
    {
        List<RecipientDigSig> recipientDigSigs = new ArrayList<>();
        Map<Integer, RecipientDigSig> recipientDigSigsOrderMap = new HashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        Document document = builder.parse(is);
        document.getDocumentElement().normalize();

        NodeList nListRec = document.getElementsByTagName("RecipientStatus");
        NodeList nListEnv = document.getElementsByTagName("DocuSignEnvelopeInformation");

        Node nodeEnv = nListEnv.item(0);
        Element elementEnv = (Element) nodeEnv;

        WebhookNotification webhookNotification = new WebhookNotification();
        webhookNotification.setEnvelopeId(elementEnv.getElementsByTagName("EnvelopeID").item(0).getTextContent());

        for (Element element : getDirectChildElements(elementEnv))
        {
            if (element.getTagName().equals("EnvelopeStatus"))
            {
                for (Element element1 : getDirectChildElements(element))
                {
                    if (element1.getTagName().equals("Status"))
                    {
                        webhookNotification.setEnvelopeStatus(element1.getFirstChild().getTextContent());
                    }
                }
            }
        }

        webhookNotification.setDateTime(DateTime.parse(elementEnv.getElementsByTagName("TimeGenerated").item(0).getTextContent()));

        List<WebhookNotification> webhookList;
        if (this.webhookNotificationsMap.containsKey(webhookNotification.getEnvelopeId()))
        {
            webhookList = this.webhookNotificationsMap.get(webhookNotification.getEnvelopeId());
        }
        else
        {
            webhookList = new ArrayList<>();
        }

        for (int i = 0; i < nListRec.getLength(); i++)
        {
            Node nodeRec = nListRec.item(i);

            if (nodeRec.getNodeType() == Node.ELEMENT_NODE)
            {
                Element elementRec = (Element) nodeRec;
                RecipientDigSig recipientDigSig = new RecipientDigSig(elementRec.getElementsByTagName("UserName").item(0).getTextContent(),
                                                                      elementRec.getElementsByTagName("Email").item(0).getTextContent());
                recipientDigSig.setStatus(elementRec.getElementsByTagName("Status").item(0).getTextContent());
                String routingOrder = elementRec.getElementsByTagName("RoutingOrder").item(0).getTextContent();

                if(!recipientDigSigsOrderMap.containsKey(Integer.parseInt(routingOrder))) {
                    recipientDigSigsOrderMap.put(Integer.parseInt(routingOrder), recipientDigSig);
                }
                else {
                    recipientDigSigsOrderMap.put(Integer.parseInt(routingOrder)+i, recipientDigSig);
                }
            }
        }

        for (int i = 1; i <= recipientDigSigsOrderMap.keySet().size(); i++) {
            recipientDigSigs.add(recipientDigSigsOrderMap.get(i));
        }

        webhookNotification.setRecipientDigSigs(recipientDigSigs);
        webhookList.add(webhookNotification);
        Collections.sort(webhookList);
        this.webhookNotificationsMap.put(webhookNotification.getEnvelopeId(), webhookList);

        return this.webhookNotificationsMap;
    }

    public List<Element> getDirectChildElements(Element parentElement)
    {
        List<Element> childElements = new ArrayList<>();
        Node nextChildNode = parentElement.getFirstChild();

        while (nextChildNode != null)
        {
            if (nextChildNode.getNodeType() == Node.ELEMENT_NODE)
            {
                childElements.add((Element) nextChildNode);
            }
            nextChildNode = nextChildNode.getNextSibling();
        }

        return childElements;
    }


    public WebhookNotification getNotification(String envelopeId)
    {
        if (this.webhookNotificationsMap.containsKey(envelopeId))
        {
            List<WebhookNotification> webhookNotifications = this.webhookNotificationsMap.get(envelopeId);
            return webhookNotifications.get(webhookNotifications.size() - 1);
        }
        return new WebhookNotification();
    }

    public List<String> getAvailableEnvelopeIds()
    {
        return new ArrayList<>(this.webhookNotificationsMap.keySet());
    }

}
