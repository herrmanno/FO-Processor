package com.github.herrmanno.folang;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

public class Generator {

	public void generate(Token root, String out) throws Exception {
		InputStream is = getInputStream(root);
		generate_internal(is, out);
	}

	private InputStream getInputStream(Token root) {
		String str = new XslBuilder().build(root);
		return new ByteArrayInputStream(str.getBytes());
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
