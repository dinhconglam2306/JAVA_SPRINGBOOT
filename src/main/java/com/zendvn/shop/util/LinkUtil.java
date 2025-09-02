package com.zendvn.shop.util;

import org.springframework.stereotype.Component;

@Component
public class LinkUtil {
    public String buildSortLinkIcon(String namDisplay, String pathBase, String currentSort, String sortField){
        String sortDirection = "asc";
        String iconClass = "fa-sort";
        
        if(currentSort.equals(sortField + ",asc")){
            sortDirection = "desc";
            iconClass = "fa-sort-up";
        }else if(currentSort.equals(sortField + ",desc")){
            sortDirection = "asc";
            iconClass = "fa-sort-down";
        }

        String href = pathBase + "?sort=" + sortField + "," + sortDirection;
        
        return String.format(
            "<a href='%s' class='text-decoration-none'>" + 
                "<i class='fa %s'></i> %s" + 
            "</a>", href, iconClass, namDisplay
        );

    }
}
