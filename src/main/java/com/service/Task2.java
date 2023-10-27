package com.service;

import com.dto.Adress;
import com.handlers.AdressHandler;
import com.handlers.AdressHierarchyHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 implements Operations {
    private final String HIERARCHY_FILE_ROUTE_PATH = "src/main/resources/xml/AS_ADM_HIERARCHY.XML";
    private final String ADRESS_FILE_ROUTE_PATH = "src/main/resources/xml/AS_ADDR_OBJ.XML";
    @Override
    public void process() {
        try {
            AdressHierarchyHandler adressHierarchyHandler = new AdressHierarchyHandler();
            File xmlFileHierarchy = new File(HIERARCHY_FILE_ROUTE_PATH);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFileHierarchy, adressHierarchyHandler);

            AdressHandler adressHandler = new AdressHandler();
            File xmlFile = new File(ADRESS_FILE_ROUTE_PATH);
            saxParser.parse(xmlFile, adressHandler);

            HashMap<String, Adress> adressHashMap = adressHandler.getAdressMap();
            HashMap<String, String> adressHierarchyHashMap = adressHierarchyHandler.getAdressHierarchyMap();

            String targetTypeName = "проезд";

            List<Adress> filteredAdresses = adressHashMap.values().stream()
                    .filter(adress -> targetTypeName.equals(adress.getTypeName()))
                    .filter(Adress::isActual)
                    .sorted(Comparator.naturalOrder())
                    .collect(Collectors.toList());

            System.out.println("Результат выполнения метода 2:");
            for (Adress adress : filteredAdresses) {
                List <Adress> foundList = new ArrayList<>();
                recursiveFindParents(adress, adressHashMap, adressHierarchyHashMap, foundList);
                StringBuilder fullAdressBuilder = new StringBuilder();
                for (int i = foundList.size() - 1; i >= 0; i--) {
                    fullAdressBuilder.append(foundList.get(i).toStringForTask2()).append(", ");
                }
                fullAdressBuilder.append(adress.toStringForTask2());

                String fullAdress = fullAdressBuilder.toString();
                System.out.println(fullAdress);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recursiveFindParents(Adress adress, HashMap <String, Adress> adressHashMap, HashMap <String, String> adressHierarchyHashMap, List<Adress> foundList) {
        String parentId = adressHierarchyHashMap.get(adress.getObjectId());
        Adress parentAdress = adressHashMap.get(parentId);
        if (parentAdress != null) {
            foundList.add(parentAdress);
            recursiveFindParents(parentAdress, adressHashMap, adressHierarchyHashMap, foundList);
        }
    }
}
