package com.zendvn.shop.model;

import com.zendvn.shop.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Không được rỗng")
    @Size(min=3, max= 100,message ="Tên phải từ 3 đến 100 ký tự")
    private String name;

    @NotNull(message ="Không được rỗng")
    @Min(value=1, message="Ordering phải lớn hơn hoặc bằng 1")
    private Integer ordering;

    @Enumerated(EnumType.STRING)
    @NotNull(message ="Không được rỗng")
    private Status status;

    public Long getId(){
        return id;
    }

     public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

     public void setName(String name){
        this.name = name;
    }

    public Integer getOrdering(){
        return ordering;
    }

     public void setOrdering(Integer ordering){
        this.ordering = ordering;
    }

    public Status getStatus(){
        return status;
    }

     public void setStatus(Status status){
        this.status = status;
    }
}
