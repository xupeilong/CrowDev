package org.crowdev.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigTMPLoader {
	
	private String content = "";

	public void loadTMPContent()
	{
		BufferedReader in1 = null;  
		try {
			in1 = new BufferedReader(new InputStreamReader(ConfigTMPLoader.class.getClassLoader().getResourceAsStream(  
			                "arch-config-template.xml"), "utf-8"));
			
			String str = null;  
            while ((str = in1.readLine()) != null) {  
                content = content + str + "\n";
            }  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseTMP()
	{
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new ByteArrayInputStream(content.getBytes()));
			Element root = document.getRootElement();
			Context.archConfigTarget = parseTMPElement(root);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Target parseTMPElement(Element element)
	{
		String name = element.getName();
		Attribute attr = element.attribute("TMP_MULTI");
		boolean isMulti = false;
		if (attr != null && attr.getValue() != null && attr.getValue().equals("true"))
			isMulti = true;
		Target  target = new Target(name, isMulti);
		
		List<Attribute> attributes = element.attributes();
		for (Attribute a: attributes)
			target.attrs.add(a.getName());
		
		List<Element> elements = element.elements();
		for (Element e: elements)
			target.subTarget.add(parseTMPElement(e));
		
		return target;
	}
}
