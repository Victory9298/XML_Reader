package com.service;

import com.dto.Adress;
import com.handlers.AdressHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 implements Operations {
    private List<String> objectIdList;
    private LocalDate targetDate;
    private final String FILE_ROUTE = "src/main/resources/xml/AS_ADDR_OBJ.XML";
    public void setObjectIdList(List<String> objectIdList) {
        this.objectIdList = objectIdList;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    @Override
    public void process()  {
        try {
            handleAdressXMLFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void  handleAdressXMLFile()
            throws ParserConfigurationException, SAXException, IOException {

        AdressHandler handler = new AdressHandler();

        File xmlFile = new File(FILE_ROUTE);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(xmlFile, handler);

        HashMap<String, Adress> adressMap = handler.getAdressMap();
        filterAddress(adressMap);
    }

    void filterAddress(HashMap<String, Adress> adressMap) {

        List<Adress> filteredAddresses = adressMap.values().stream()
                    .filter(address -> this.targetDate.isAfter(address.getStartDate()) && targetDate.isBefore(address.getEndDate()))
                    .filter(address -> this.objectIdList.contains(address.getObjectId()))
                    .collect(Collectors.toList());
        System.out.println("Результат метода 1:");
        for(Adress adress : filteredAddresses) System.out.println(adress);

    }
}

