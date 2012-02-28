package com.krasdevmeetup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.krasdevmeetup.procrastinoid.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class procrastinoidActivity extends Activity {
    //a handle to the application's resources  
    private Resources resources;
    
    private TextView console;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        console = (TextView) findViewById(R.id.console);
        console.setText("Change me please!\n");
        
        resources = getResources();
    }
    
    @Override
    public void onStart() {
    	super.onStart();
    	
    	InputStream in = resources.openRawResource(R.raw.iprocrastinate);
    	DocumentBuilder builder;
    	
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(in, null);
	    	NodeList tasks = doc.getElementsByTagName("task");
	    	for (int i=0;i<tasks.getLength();i++) {
	    	  String name = ( (Element)tasks.item(i) ).getAttribute("name");
	    	  console.append((i+1) + "." + decode(name) + "\n");
	    	}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }      
    
    private String decode(String str)
    {
    	try {
			return new String(Base64.decode(str), "UTF-16");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
}