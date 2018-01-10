package nl.dtl.fairsearchengine.util;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.impl.LiteralImpl;
import org.eclipse.rdf4j.model.impl.SimpleLiteral;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;

import nl.dtl.fairmetadata4j.model.DistributionMetadata;
import nl.dtl.fairmetadata4j.utils.RDFUtils;
import nl.dtl.fairsearchengine.util.license.License;

public class Uri2Label {
	
	URI uri;
	
	public Uri2Label(URI uri) {
		this.uri = uri;
	}
	
	private Optional<Model> connectAndGetModel() throws Exception {
		HttpURLConnect http = new HttpURLConnect();
		
		LinkedDataResolver ldr = new LinkedDataResolver();
		Optional<URI> resolvedUri = ldr.resolve(this.uri);
	    
		System.out.println("resolved uri "+resolvedUri);
		
		Model model = null;
		if(resolvedUri.isPresent()) {
			String s = http.sendGet(resolvedUri.get().toString());
			
			//System.out.println(s);
			
			
		   
			try {
				
				model = Rio.parse(new StringReader(s), uri.toString() , RDFFormat.TURTLE);
			} catch (RDFParseException | UnsupportedRDFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Optional<Model> optional;
		optional = model == null ? Optional.empty() : Optional.of(model);
		
		return optional;
	}
	
	//TODO change string to ISO language code
	public Optional<String> getLabel(String language) {
		
		
		try {
			
			Optional<Model> model = connectAndGetModel();
			
			if(!model.isPresent()) return Optional.empty();
		    
	        //IRI fdpURI = (IRI) statements.get(0).getSubject();
		    for(Statement statement: model.get()){
		    		if(statement.getPredicate().equals(RDFS.LABEL)){
		    		Value val = statement.getObject();
		    		//TODO check if it has a language and return based on it
		    		
		    		SimpleLiteral literal = (SimpleLiteral) statement.getObject();
		    		
		    		Optional<String> literalLanguage= literal.getLanguage();
		    		
		    		if(language==null) return Optional.of( statement.getObject().stringValue() );
		    		else if(!language.isEmpty() && literalLanguage.get().equals(language)) {
		    			System.out.println("DUTCH "+statement.getObject().stringValue());
		    			return Optional.of( statement.getObject().stringValue() );
		    		}
		    		
		    		//return statement.getObject().stringValue();
		    	}
		    }
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return other than null
		return null;
	}
	
	public Optional<String> getLabel() {
		return getLabel(null);
	}
	
	/* to be deleted */
	private String delGetLabel() {
		
		HttpURLConnect http = new HttpURLConnect();
		
		try {
			
			LinkedDataResolver ldr = new LinkedDataResolver();
			Optional<URI> resolvedUri = ldr.resolve(this.uri);
		    
			System.out.println("resolved uri"+resolvedUri);
			
			if(!resolvedUri.isPresent()) return null;
			
			String s = http.sendGet(resolvedUri.get().toString());
			
			//System.out.println(s);
			
			
		    Model model = null;
			try {
				
				model = Rio.parse(new StringReader(s), uri.toString() , RDFFormat.TURTLE);
			} catch (RDFParseException | UnsupportedRDFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	        //IRI fdpURI = (IRI) statements.get(0).getSubject();
		    for(Statement statement: model){
		    		if(statement.getPredicate().equals(RDFS.LABEL)){
		    		Value val = statement.getObject();
		    		//TODO check if it has a language and return based on it
		    		
		    		
		    		System.out.println(statement.getObject().stringValue());
		    		return statement.getObject().stringValue();
		    	}
		    }
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return other than null
		return null;		
	}
	
	public List<String> getLabels(String language){
		
		List<String> labelList = new Vector<String>();
		
		try {
			
			Optional<Model> model = connectAndGetModel();
		    
			if(!model.isPresent()) return new Vector();
			
	        //IRI fdpURI = (IRI) statements.get(0).getSubject();
		    for(Statement statement: model.get()){
		    		if(statement.getPredicate().equals(RDFS.LABEL)){
		    		Value val = statement.getObject();
		    		//TODO check if it has a language and return based on it
		    		
		    		SimpleLiteral literal = (SimpleLiteral) statement.getObject();
		    		
		    		Optional<String> literalLanguage= literal.getLanguage();
		    		
		    		if(language==null) labelList.add(statement.getObject().stringValue());
		    		else if(!language.isEmpty() && literalLanguage.get().equals(language)) {
		    			labelList.add(statement.getObject().stringValue());
		    		}
		    		
		    		//return statement.getObject().stringValue();
		    	}
		    }
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return other than null
		return labelList;
	}
	
	public List<String> getLabels(){
		return this.getLabels(null);
	}
	
}
