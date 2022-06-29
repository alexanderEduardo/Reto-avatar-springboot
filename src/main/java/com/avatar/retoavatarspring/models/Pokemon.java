package com.avatar.retoavatarspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon implements Serializable {
    private Integer id;
    private List<Abilities> abilities;
    private Integer weight;
    private Integer height;
    private String name;
    private List<Types> types;
    private Sprites sprites;
}
