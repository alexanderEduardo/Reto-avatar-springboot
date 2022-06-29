package com.avatar.retoavatarspring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats implements Serializable {
    private Integer base_stat;
    private Stat stat;
}
