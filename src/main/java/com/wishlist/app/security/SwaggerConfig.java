package com.wishlist.app.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private final ResponseMessage m201 = simpleMessage(201, "Recurso criado");
	private final ResponseMessage m204put = simpleMessage(204, "Atualização OK");
	private final ResponseMessage m204Del = simpleMessage(204, "Deleção OK");
	private final ResponseMessage m403 = simpleMessage(403, "Não autorizado");
	private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
	private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");
	

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securitySchemes(Collections.singletonList(apiKey()))
				.useDefaultResponseMessages(true)
				.securityContexts(Collections.singletonList(securityContext()))
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204Del, m403, m404, m500))
				.globalResponseMessage(RequestMethod.PATCH, Arrays.asList(m204put, m403, m404, m500))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.wishlist.app.api"))
				.paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	private ResponseMessage simpleMessage(int codigo, String msg) {
		return new ResponseMessageBuilder().code(codigo).message(msg).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("REST API", "Descrição da API WishList", "Versão: 1.0.0-SNAPSHOTS ", "Termos de uso ",
				new Contact("Contato wishlist", "https://www.sishlist.com.br/", "tjgo@.wishlist"), "", "",
				Collections.emptyList());
	}

	
	@Bean
	SecurityContext securityContext() {
	    return SecurityContext.builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.any())
	            .build();
	}

	List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope
	            = new AuthorizationScope("global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Collections.singletonList(
	            new SecurityReference("JWT", authorizationScopes));
	}

	private ApiKey apiKey() {
	    return new ApiKey("JWT", "Authorization", "header");
	}
}
