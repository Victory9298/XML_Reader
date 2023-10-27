package com.handlers;

import com.dto.Adress;
import com.util.UtilClass;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.HashMap;

public class AdressHandler extends DefaultHandler {
    private String objectId;
    private String name;
    private String typeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActual;
    private boolean isActive;
    private HashMap<String, Adress> adressMap= new HashMap();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("OBJECT".equals(qName)) {
            objectId = attributes.getValue("OBJECTID");
            name = attributes.getValue("NAME");
            typeName = attributes.getValue("TYPENAME");
            String startDateStr = attributes.getValue("STARTDATE");
            startDate = UtilClass.parseDate(startDateStr);
            String endDateStr = attributes.getValue("ENDDATE");
            endDate = UtilClass.parseDate(endDateStr);
            String isActualStr = attributes.getValue("ISACTUAL");
            isActual = Integer.parseInt(isActualStr) == 1;
            String isActiveStr = attributes.getValue("ISACTIVE");
            isActive = Integer.parseInt(isActiveStr) == 1;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("OBJECT".equals(qName)) {
            Adress adress = new Adress(objectId, name, typeName, startDate, endDate, isActual, isActive);
            adressMap.put(adress.getObjectId(), adress);;
        }
    }

    public HashMap<String, Adress> getAdressMap() {
        return adressMap;
    }
}

