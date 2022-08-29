package com.mashcode.mtit2022pastpaper.answers.question02.main;

import com.mashcode.mtit2022pastpaper.answers.question02.common.CommonConstant;

import java.io.File;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * Authored By MASH
 * 2022/08/27
 * this is sample answer for 2022 mtit past paper Q2
 */
public class Question02 {


    /**
     * @param args
     */
    public static void main(String[] args) {
        buildXmlFile();
    }

    /**
     * build xml file
     */
    public static void buildXmlFile() {
        Element school, students, student, name, address;
        Attr gender, initials, no, street;

        //call build document function
        Document document = buildDocument();

        //create element school
        school = createElement(document, CommonConstant.XML_ELEMENT_SCHOOL);
        document.appendChild(school);

        //create element students
        students = createElement(document, CommonConstant.XML_ELEMENT_STUDENTS);
        school.appendChild(students);

        //create element student
        student = createElement(document, CommonConstant.XML_ELEMENT_STUDENT);
        students.appendChild(student);


        //create attribute gender and set value
        gender = createAttribute(document, CommonConstant.XML_ATTRIBUTE_GENDER, CommonConstant.XML_DOCUMENT_GENDER_VALUE);
        student.setAttributeNode(gender);
        //create initials gender and set value
        initials = createAttribute(document, CommonConstant.XML_ATTRIBUTE_INITIALS, CommonConstant.XML_DOCUMENT_INITIALS_VALUE);

        //create element name
        name = createElement(document, CommonConstant.XML_ELEMENT_NAME);
        name.setAttributeNode(initials);
        //set value to name
        name.appendChild(document.createTextNode(CommonConstant.XML_DOCUMENT_NAME_VALUE));
        student.appendChild(name);

        //create element address and set attributes and values
        address = createElement(document, CommonConstant.XML_ELEMENT_ADDRESS);
        no = createAttribute(document, CommonConstant.XML_ATTRIBUTE_NO, CommonConstant.XML_DOCUMENT_NO_VALUE);
        street = createAttribute(document, CommonConstant.XML_ATTRIBUTE_STREET, CommonConstant.XML_DOCUMENT_STREET_VALUE);

        address.setAttributeNode(no);
        address.setAttributeNode(street);
        student.appendChild(setAttributeForElement(document, address, CommonConstant.XML_DOCUMENT_ADDRESS_VALUE));

        //xml generate method call
        transformToXml(document);
    }

    /**
     * build document
     *
     * @return
     */
    public static Document buildDocument() {
        Logger log = Logger.getLogger(Question02.class.getName());
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException exception) {
            log.info("Error getting generate Document ".concat(exception.getMessage()));
            throw new RuntimeException("Error getting generate Document ".concat(exception.getMessage()));
        }
    }

    /**
     * @param document
     * @param element
     * @param textNode
     * @return
     */
    public static Element setAttributeForElement(Document document, Element element, String textNode) {
        element.appendChild(document.createTextNode(textNode));
        return element;
    }


    /**
     * @param document
     * @param type
     * @param value
     * @return
     */
    public static Attr createAttribute(Document document, String type, String value) {
        Attr attribute = document.createAttribute(type);
        attribute.setValue(value);
        return attribute;
    }

    /**
     * @param document
     * @param elementName
     * @return
     */
    public static Element createElement(Document document, String elementName) {
        return document.createElement(elementName);
    }

    /**
     * @param document
     */
    public static void transformToXml(Document document) {

        Logger log = Logger.getLogger(Question02.class.getName());
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            transformer.transform(domSource, new StreamResult(new File(CommonConstant.XML_DOCUMENT_FILE_NAME)));
            transformer.transform(domSource, new StreamResult(System.out));
            log.info("Xml build successfully ");

        } catch (TransformerException exception) {
            log.info("Error getting generate Xml document  ".concat(exception.getMessage()));
            throw new RuntimeException("Error getting generate Xml document ".concat(exception.getMessage()));
        }
    }
}
