package com.wishlist.app.api.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    @Id
    private String id;

    @Field("client_id")
    private String clientId;

    @Field("product_ids")
    private Set<String> productIds = new HashSet<>(); // Inicialização aqui
}
