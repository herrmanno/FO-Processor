package com.github.herrmanno.folang;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.Jade4J.Mode;
import de.neuland.jade4j.JadeConfiguration;
import de.neuland.jade4j.template.JadeTemplate;

public class JadeGenerator {

	public void  generate(String in, String out) throws Exception {
		//PipedInputStream is = new PipedInputStream(); 
		//Writer writer = new OutputStreamWriter(new PipedOutputStream(is));
		//Jade4J.render(in, new HashMap<String, Object>(), new PrintWriter(System.out));
		
		JadeConfiguration jConfig = new JadeConfiguration();
		jConfig.setMode(Mode.XHTML);
		jConfig.setPrettyPrint(true);
		JadeTemplate template = Jade4J.getTemplate(in);
		Map<String, Object> model = new HashMap<String, Object>();
		String xml = jConfig.renderTemplate(template, model);
		
		InputStream is = new ByteArrayInputStream(xml.getBytes());

		generate(is, out);
	}
	
	private void generate(InputStream is, String out) throws Exception {
		generate_internal(is, out);
	}


	private void generate_internal(InputStream inStream, String outName) throws Exception {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(outName));
		Source xslt = new StreamSource(Thread.currentThread().getContextClassLoader().getResourceAsStream("stylesheet.xsl"));
		FopFactory fopFactory = FopFactory.newInstance();

		try {
		    Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

		    TransformerFactory factory = TransformerFactory.newInstance();
		    JarfileResolver jarfileResolver = new JarfileResolver();
		    factory.setURIResolver(jarfileResolver);
		    
		    Transformer transformer = factory.newTransformer(xslt);


		    Source src = new StreamSource(inStream);

		    Result res = new SAXResult(fop.getDefaultHandler());

		    transformer.transform(src, res);

		} finally {
		    out.close();
		}
	}
}
