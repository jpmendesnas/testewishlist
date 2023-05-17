package com.wishlist.app.security;

import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

/**
 * Solução para o retornar o id da entidade pelo JSON.
 */
@Component
public class ExposeEntityIdRestConfiguration implements RepositoryRestConfigurer {


}
