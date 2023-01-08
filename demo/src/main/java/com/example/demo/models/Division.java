package com.example.demo.models;

import javax.persistence.*;


@Entity
@Table(name = "tb_m_division")

public class Division {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "name", nullable = false) 
    private String Name;

    @ManyToOne
    @JoinColumn(name = "regionId", referencedColumnName = "id")
    private Region region;

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

    public void setRegion(Region region){
        this.region = region;
        
    }

    public Region getRegion(){
        return region;
    }

}
