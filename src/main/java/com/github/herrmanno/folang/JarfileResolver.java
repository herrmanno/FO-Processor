package com.github.herrmanno.folang;

import java.io.InputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

public class JarfileResolver implements URIResolver
{
    public Source resolve(String fileName, String base) throws TransformerException
    {
    	 InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
         return new StreamSource(inputStream);
    }

}
