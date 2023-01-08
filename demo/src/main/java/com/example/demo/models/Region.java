package com.example.demo.models;


import javax.persistence.*;

@Entity
@Table(name = "tb_m_region")

public class Region {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "name", nullable = false)
    private String Name;

    public void setId(Integer id){
        Id = id;
    }

    public Integer getId(){
        return Id;
    }

    public void setName(String name){
        Name = name;
    }

    public String getName(){
        return Name;
    }
}