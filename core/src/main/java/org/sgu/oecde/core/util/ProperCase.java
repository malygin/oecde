package org.sgu.oecde.core.util;

import org.springframework.util.StringUtils;

/**
 *
 * класс, изменяющий регистр слова: первые буквы прописные, остальные строчные
 * @author ShihovM
 */
public class ProperCase {

    private ProperCase() {
        throw new AssertionError();
    }

    /**
     * слово разделяется на 2 части - первая буква, и остальное слово.
     * Регистр первой буквы изменяется на прописной, остального слова - на строчный
     * @param word - слово, регистр которого будет изменён
     * @return
     */
    public static String bringTo(String word){
       StringBuilder finalWord = new StringBuilder();
       if(StringUtils.hasText(word)){
           String[] wordAr = word.split("-");
           for(String s:wordAr){
               finalWord.append(s.substring(0,1).toUpperCase()).append(s.substring(1).toLowerCase());
               if(wordAr.length>1&&!word.endsWith(s))
                   finalWord.append("-");
           }
       }else
           return word;
       return finalWord.toString();
    }
}
