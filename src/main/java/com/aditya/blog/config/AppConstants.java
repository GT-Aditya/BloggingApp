package com.aditya.blog.config;

public class AppConstants {
	 public static final String PAGE_NUMBER = "0";
	 public static final String PAGE_SIZE = "2";
	 public static final String SORT_BY = "id";
	 public static final String SORT_DIR = "asc";
	 public static final Integer ADMIN_ROLE = 289372;
	 public static final Integer NORMAL_ROLE = 498237;
	 
	 public static final String[] PUBLIC_URLS =
		 {
				 "/api/v1/auth/**",
				 "/v3/api-docs",
				 "/v2/api/docs",
				 "/swagger-resources/**",
				 "/swagger-ui/**",
				 "/webjars/**"
		 };
	 
	 public static final String AUTHORIZATION_HEADER = "Authorization";
	
	
}
	