package com.zendvn.shop.util;

import org.springframework.stereotype.Component;

@Component
public class TextUtil {
    public String highlightSearchTerm (String text, String search){
        if(search != null && !search.isEmpty()){
            String highlightedText = "<mark><strong>" + search + "</mark></strong>";
            return text.replace(search, highlightedText);
        }
        return text;
    }
}
