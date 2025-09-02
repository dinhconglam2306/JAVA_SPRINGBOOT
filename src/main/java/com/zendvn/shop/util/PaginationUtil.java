package com.zendvn.shop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {
    public List<Map<String, Object>> buildPagination (String pathAndQueryStr, Page<?> page){
        List<Map<String, Object>> paginationInfo = new ArrayList<>(); 
        int currentPage = page.getNumber() + 1;
        int totalPages = page.getTotalPages();

        //Trang đầu tiên
        paginationInfo.add(Map.of(
            "label", "<i class='fas fa-angle-double-left'></i>",
            "href", currentPage > 1 ? pathAndQueryStr + "page=1" : "#",
            "disabled", currentPage == 1,
            "class",""
        ));

        //Trang trước đó
        paginationInfo.add(Map.of(
            "label", "<i class='fas fa-angle-left'></i>",
            "href", currentPage > 1 ? pathAndQueryStr + "page=" + (currentPage -1 ) : "#",
            "disabled", currentPage == 1,
            "class",""
        ));

        for(int i = 1; i <= totalPages; i++){
            paginationInfo.add(Map.of(
                "label", String.valueOf(i),
                "href", pathAndQueryStr + "page=" + i,
                "disabled", false,
                "class", i == currentPage ? "datatable-active" : ""
        ));
        }

        //Trang tiếp theo
        paginationInfo.add(Map.of(
            "label", "<i class='fas fa-angle-right'></i>",
            "href", currentPage < totalPages ? pathAndQueryStr + "page=" + (currentPage +1) : "#",
            "disabled", currentPage == totalPages,
            "class",""
        ));

        //Trang cuối cùng
        paginationInfo.add(Map.of(
            "label", "<i class='fas fa-angle-double-right'></i>",
            "href", currentPage < totalPages ? pathAndQueryStr + "page=" + totalPages  : "#",
            "disabled", currentPage == totalPages,
            "class",""
        ));

        return paginationInfo;
    }

    public String getPaginationSummary(Page<?> page){
        int pageSize = page.getNumber();
        int currentPage = page.getNumber();
        int totalElement = (int) page.getTotalElements();

        int start = currentPage * pageSize + 1;
        int end = Math.min(start + page.getNumberOfElements() - 1 , totalElement);

        return "Hiển thị từ " + start + " đến " + end + " trong tổng số " + totalElement + " mục";

    }

    public List<Map<String, Object>> buildPaginationCategory (String pathAndQueryStr, Page<?> page){
        List<Map<String, Object>> paginationInfo = new ArrayList<>(); 
        int currentPage = page.getNumber() + 1;
        int totalPages = page.getTotalPages();

        if (totalPages <= 1) {
            return paginationInfo;
        }

        for(int i = 1; i <= totalPages; i++){
            String href = pathAndQueryStr + "page=" + i;
            paginationInfo.add(Map.of(
                "label", String.valueOf(i),
                "href", href,
                "disabled", false,
                "class", i == currentPage ? "pagi-active" : ""
            ));
        }
        return paginationInfo;
    }
}
