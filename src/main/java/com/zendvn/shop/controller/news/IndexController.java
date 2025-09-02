package com.zendvn.shop.controller.news;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zendvn.shop.model.Articles;
import com.zendvn.shop.model.Categories;
import com.zendvn.shop.repository.ArticlesRepository;
import com.zendvn.shop.repository.CategoriesRepository;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    // public static final String PATH_BASE = "/items";
    public static final String PATH_ELEMENTS = "news/elements/";

    // public static final String PATH_BASE_TEMPLATE = "admin/pages/" + PATH_BASE + "/";
    public static final String PATH_BASE_TEMPLATE = "news/pages/";

    @GetMapping
    public String Index(Model model) {
        List <Articles> articlesList  = articlesRepository.findSpecialArticles();
        List <Categories> categoriesList = categoriesRepository.findCategoriesbyStatus();
        
        // list categories
        Map<Categories, List<Articles>> itemsArticle = new LinkedHashMap();

         for (Categories category : categoriesList) {
            List<Articles> articlesForCategory = articlesList.stream()
                .filter(article -> article.getCategory().getId().equals(category.getId()))
                .collect(Collectors.toList());

                if (!articlesForCategory.isEmpty()) {
                    itemsArticle.put(category, articlesForCategory);
                }
        }



        model.addAttribute("pathElements", PATH_ELEMENTS);
        model.addAttribute("itemsArticle", itemsArticle);
        model.addAttribute("articlesList", articlesList);
        model.addAttribute("pathBaseTemplate", PATH_BASE_TEMPLATE);

        return PATH_BASE_TEMPLATE + "index";
    }
}

