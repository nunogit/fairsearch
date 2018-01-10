package com.ipeirotis.readability;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.stanford.nlp.process.TokenizerFactory;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;

/** Use SentenceModel to find sentence boundaries in text */
public class SentenceExtractor {

   /* final TokenizerFactory TOKENIZER_FACTORY = new IndoEuropeanTokenizerFactory();
    final SentenceModel SENTENCE_MODEL = new IndoEuropeanSentenceModel();*/

   /* public String[] getSentences(String text) {

        ArrayList<String> tokenList = new ArrayList<String>();
        ArrayList<String> whiteList = new ArrayList<String>();
        Tokenizer tokenizer = TOKENIZER_FACTORY.tokenizer(text.toCharArray(), 0, text.length());
        tokenizer.tokenize(tokenList, whiteList);

        String[] tokens = new String[tokenList.size()];
        String[] whites = new String[whiteList.size()];
        tokenList.toArray(tokens);
        whiteList.toArray(whites);
        int[] sentenceBoundaries = SENTENCE_MODEL.boundaryIndices(tokens, whites);

        if (sentenceBoundaries.length < 1) {
            return new String[0];
        }

        String[] result = new String[sentenceBoundaries.length];

        int sentStartTok = 0;
        int sentEndTok = 0;
        for (int i = 0; i < sentenceBoundaries.length; ++i) {
            sentEndTok = sentenceBoundaries[i];
            // System.out.println("SENTENCE "+(i+1)+": ");
            StringBuffer sb = new StringBuffer();
            for (int j = sentStartTok; j <= sentEndTok; j++) {
                sb.append(tokens[j] + whites[j + 1]);
                
                // System.out.print(tokens[j]+whites[j+1]);
            }
            result[i] = sb.toString();
            // System.out.println();
            sentStartTok = sentEndTok + 1;
        }
        return result;
    }*/
    
	public static String[] getSentences(String str){
		return getSentences(str, false, "");
	}
    
    public static String[] getSentences(String str, boolean invertible, String options) {
        List<List<HasWord>> sentences = new ArrayList<List<HasWord>>();
        StringReader reader = new StringReader(str);
        DocumentPreprocessor dp = new DocumentPreprocessor(reader);
        TokenizerFactory factory = null;

        if( invertible ) {
          factory = PTBTokenizer.factory(true, true);
          if( options != null && options.length() > 0 ) 
            options = "invertible=true, " + options;
          else 
            options = "invertible=true";
        } else {
          factory = PTBTokenizer.factory();
        }

//        System.out.println("Setting splitter options=" + options);
        factory.setOptions(options);
        dp.setTokenizerFactory(factory);
        
        Iterator<List<HasWord>> iter = dp.iterator();
        
        //while( iter.hasNext() ) {
        //  List<HasWord> sentence = iter.next();
        //  sentences.add(sentence);
        //}
        //return sentences;
        
        List<String> stringList = new ArrayList();
        
        while(iter.hasNext()){
        	List<HasWord> sentence = iter.next();
        	Iterator<HasWord> siterator = sentence.iterator();
        	String ssentence = "";
        	while(siterator.hasNext()){
        		HasWord word =  siterator.next();
        		ssentence += word.word() + " ";
        	}
        	System.out.println(ssentence);
        	stringList.add(ssentence);
        	
        	
        }
        String[] aSentences = new String[stringList.size()];
        for(int i=0; i<stringList.size(); i++){
        	aSentences[i] = stringList.get(i);	
        }
        
        
        return aSentences;
        
      }
}