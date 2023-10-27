package com.handlers;

import com.dto.AdressHierarchy;
import com.util.UtilClass;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.time.LocalDate;
import java.util.HashMap;

public class AdressHierarchyHandler extends DefaultHandler {
    private String objectId;
    private String parentObjId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private HashMap<String, String> adressHierarchyList = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("ITEM".equals(qName)) {
            objectId = attributes.getValue("OBJECTID");
            parentObjId = attributes.getValue("PARENTOBJID");
            String startDateStr = attributes.getValue("STARTDATE");
            startDate = UtilClass.parseDate(startDateStr);
            String endDateStr = attributes.getValue("ENDDATE");
            endDate = UtilClass.parseDate(endDateStr);
            String isActiveStr = attributes.getValue("ISACTIVE");
            isActive = Boolean.valueOf(Integer.parseInt(isActiveStr) == 1);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("ITEM".equals(qName)) {
            AdressHierarchy adressHierarchy = new AdressHierarchy(objectId, parentObjId, startDate, endDate, isActive);
            adressHierarchyList.put(adressHierarchy.getObjectId(), adressHierarchy.getParentObjId());
        }
    }

    public HashMap<String, String> getAdressHierarchyMap() {
        return adressHierarchyList;
    }
}
