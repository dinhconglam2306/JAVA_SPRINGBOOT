package com.zendvn.shop.controller.news;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zendvn.shop.enums.Status;
import com.zendvn.shop.model.Articles;
import com.zendvn.shop.model.Authors;
import com.zendvn.shop.repository.ArticlesRepository;
import com.zendvn.shop.repository.AuthorsRepository;
import com.zendvn.shop.util.PaginationUtil;

@Controller
@RequestMapping("/authors")
@Service("newsAuthorsController")
public class AuthorsController {
    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private PaginationUtil paginationUtil;
    
    // public static final String PATH_BASE = "/items";
    public static final String PATH_ELEMENTS = "news/elements/";

    // public static final String PATH_BASE_TEMPLATE = "admin/pages/" + PATH_BASE + "/";
    public static final String PATH_BASE_TEMPLATE = "news/pages/";

    @GetMapping("/{slug}")
    public String Authors(Model model , @PathVariable(required = false) String slug,  
                            @RequestParam(value="sort", defaultValue = "name,asc") String sortParam,
                            @RequestParam(value="page", defaultValue = "1") int page,
                            @RequestParam(value="size", defaultValue = "2") int size) {

        Authors authorBySlug = authorsRepository.findBySlug(slug).orElseThrow();
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Articles> items = articlesRepository.findByAuthorAndStatus(authorBySlug, Status.ACTIVE, pageable);

        String baseUrl = "/authors/" + slug + "?";
        List<Map<String, Object>> paginationInfo = paginationUtil.buildPaginationCategory(baseUrl, items);

        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("authorBySlug", authorBySlug);
        model.addAttribute("items", items);
        model.addAttribute("pathElements", PATH_ELEMENTS);

        return PATH_BASE_TEMPLATE + "authors";
    }
}
