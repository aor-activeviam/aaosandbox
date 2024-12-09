# Security Configuration
Spring Beans used for authentication of Atoti Server.


## TechnicalAuthenticationSecirutyConfig
    uses PasswordEncoder bean
    and passwords stored in hashed form in properties
    for users pivot and sba ( hardcoded ) and their roles
    to define
        @Bean AuthenticationProvider technicalAuthenticationProvider
        @Bean UserDetailsService UserDetailService

## InMemoryAuthenticationConfig
    uses passwordEncoder and and user details from property section
    to define
        @Bean UserDetailsService inMemoryUserDetailsService
        @Bean AuthenticationProvider inMemoryAuthenticationProvider
    
# GlobalSecurityConfig
    uses inMemoryUserDetailsService and technicalAuthenticationProvider
    to define
        @Bean(primary) UserDetailsService userDetailsService
        @Bean AuthenticationManager globalAuthenticationManager

# from sprint-security-config.jar
    @Bean HttpSecurity - object where you can register handlers for varius urls and their access rules

# CustomWebSecurityFiltersConfig
    @Bean SecurityFilterChain h2ConsoleSecurityFilterChain
    // if h2console enabled, allow all access to h2 console rel path
    @Bean SecurityFilterChain actuatorSecurityFilterChain
    // if swagger.ui enabled, allow all access to swagger ui rel path
    @Bean SecurityFilterChain swaggerUiSecurityFilterChain
    //same rule for /custom
    @Bean SecurityFilterChain customSecurityFilterChain
