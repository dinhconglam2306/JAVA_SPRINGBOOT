package com.zendvn.shop.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zendvn.shop.enums.Status;
import com.zendvn.shop.model.Authors;
import com.zendvn.shop.repository.AuthorsRepository;
import com.zendvn.shop.util.MessageUtil;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/authors")
public class AuthorsController {

    public static final String PATH_BASE = "/admin/authors";
    public static final String PATH_FOLDER = "authors";
    public static final String PATH_ELEMENTS = "admin/elements/";

    public static final String PATH_BASE_TEMPLATE = "admin/pages/" + PATH_FOLDER + "/";
    @Autowired
    private AuthorsRepository itemsRepository;
    
    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String listItems(@RequestParam(value="statusFilter", required = false) String statusFilter, 
                            @RequestParam(value="search", required = false) String search, 
                            @RequestParam(value="sort", defaultValue = "name,asc") String sortParam,
                            @RequestParam(value="page", defaultValue = "1") int page,
                            @RequestParam(value="size", defaultValue = "5") int size,
                            Model model) {

        String [] sortParams    = sortParam.split(",");
        String sortField        = sortParams[0];
        String sortDirection    = sortParams[1];

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<Authors> items;
 

        if(statusFilter != null && !statusFilter.isEmpty()){
            Status status = Status.valueOf(statusFilter);
            items = itemsRepository.findByStatus(status, pageable);
        }else if(search != null && !search.isEmpty()){
            items = itemsRepository.findByNameContainingIgnoreCase(search, pageable);
        }else{
            items = itemsRepository.findAll(pageable);
        }

        model.addAttribute("pathBase", PATH_BASE);
        model.addAttribute("pathElements", PATH_ELEMENTS);

        model.addAttribute("items", items);
        model.addAttribute("search", search);
        model.addAttribute("sort", sortParam);
        model.addAttribute("listSizes", List.of(5,10,15,20));
        model.addAttribute("statusFilter", statusFilter);
        model.addAttribute("totalAllItems", itemsRepository.count());
        model.addAttribute("totalActiveItems", itemsRepository.countByStatus(Status.ACTIVE));
        model.addAttribute("totalInactiveItems", itemsRepository.countByStatus(Status.INACTIVE));

        return PATH_BASE_TEMPLATE + "list";
    }
    @GetMapping({"/form", "form/{id}"})
    public String formItem(Model model, @PathVariable(required = false) Long id, RedirectAttributes redirectAttributes, Locale locale) {
        Authors item;
        if(id != null){
            Optional<Authors> itemFind = itemsRepository.findById(id);
            if(itemFind.isPresent()){
                item = itemFind.get();
            }else {
                messageUtil.addMessage(redirectAttributes, "message.id.error", "error", locale);
                return "redirect:" + PATH_BASE;
            }
        }else {
            item = new Authors();
            item.setStatus(Status.ACTIVE);
        }
        model.addAttribute("item", item);
        model.addAttribute("pathBase", PATH_BASE);
        model.addAttribute("pathElements", PATH_ELEMENTS);
        return PATH_BASE_TEMPLATE + "form";
    }
    @PostMapping({"/save", "/save/{id}"})
    public String saveItem(Model model, @PathVariable(required = false) Long id,  @Valid @ModelAttribute("item") Authors item, BindingResult result,RedirectAttributes redirectAttributes, Locale locale) {
        
        boolean slugExists = false;

        if(id != null) {
            slugExists = itemsRepository.existsBySlugAndIdNot(item.getSlug(), id);
        } else {
            slugExists = itemsRepository.existsBySlug(item.getSlug());
        }

        if(slugExists){
            result.rejectValue("slug", "message.slug.exists", 
            messageSource.getMessage("message.slug.exists", null, locale));
        }

        if(result.hasErrors()){
            model.addAttribute("pathBase", PATH_BASE);
            model.addAttribute("pathElements", PATH_ELEMENTS);
            return PATH_BASE_TEMPLATE + "form";
        }


        if(id != null){
            Optional<Authors> existingItem = itemsRepository.findById(id);
            if(existingItem.isPresent()){
                item.setId(id);
                itemsRepository.save(item);
                messageUtil.addMessage(redirectAttributes, "message.update.success", "success", locale);
            }else {
                messageUtil.addMessage(redirectAttributes, "message.id.error", "error", locale);
            }
        }else {
            itemsRepository.save(item);
            messageUtil.addMessage(redirectAttributes, "message.create.success", "success", locale);
        }   
        return "redirect:" + PATH_BASE;
    }
    @GetMapping("/delete/{id}")
    public String deleteItem(Model model , @PathVariable Long id, RedirectAttributes redirectAttributes, Locale locale) {
        Optional<Authors> item = itemsRepository.findById(id);
        if(item.isPresent()){
            itemsRepository.deleteById(id);
            messageUtil.addMessage(redirectAttributes, "message.delete.success", "success", locale);
        }else {
            messageUtil.addMessage(redirectAttributes, "message.id.error", "error", locale);
        }
        return "redirect:" + PATH_BASE;
    }

    @PostMapping({"/batch-delete"})
    public String batchDelete(@RequestParam List<Long> selectedItems, RedirectAttributes redirectAttributes, Locale locale){
        itemsRepository.deleteAllById(selectedItems);
        messageUtil.addMessage(redirectAttributes, "message.delete.success", "success", locale);

        return "redirect:" + PATH_BASE;
    }

    @PostMapping({"/batch-update-status"})
    public String batchUpdateStatus(@RequestParam List<Long> selectedItems, @RequestParam String status,  RedirectAttributes redirectAttributes, Locale locale){
        List<Authors> items = itemsRepository.findAllById(selectedItems);

        for (Authors item : items){
            item.setStatus(Status.valueOf(status));
        }
        itemsRepository.saveAll(items);
        messageUtil.addMessage(redirectAttributes, "message.update.success", "success", locale);
        return "redirect:" + PATH_BASE;
    }

    @PostMapping({"/batch-update-ordering"})
    public String batchUpdateOrdering(@RequestParam List<Long> selectedItems, @RequestParam("ordering") List<Integer> orderings,  RedirectAttributes redirectAttributes, Locale locale){
        List<Authors> items = itemsRepository.findAllById(selectedItems);

        for (int i = 0; i < items.size(); i++ ){
            items.get(i).setOrdering(orderings.get(i));;
        }
        itemsRepository.saveAll(items);
         messageUtil.addMessage(redirectAttributes, "message.update.success", "success", locale);
        return "redirect:" + PATH_BASE;
    }

    @PostMapping({"/change-status"})
    public ResponseEntity <Map<String, Object>> changeStatus(@RequestParam ("id") Long id, @RequestParam ("status") String status , Locale locale){
        Map<String, Object> response = new HashMap<>();

        try {
        Authors item = itemsRepository.findById(id).orElseThrow(() -> new RuntimeException("Invaild id"));
        item.setStatus(Status.valueOf(status));
        itemsRepository.save(item);

        response.put("status","success");
        response.put("message",messageSource.getMessage("message.change.status.success", null, locale));

        return ResponseEntity.ok(response);

        } catch (Exception e) {
        response.put("status","error");
        response.put("message",messageSource.getMessage("message.change.status.error", null, locale));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }
       
    }
}
