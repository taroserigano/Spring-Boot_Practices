
1. log in with your username n pwd 
2. it will validate
3. it will save that you are real in Spring Context 
4. it will return you the 1. accesstoken and 2. tokenType: "Bearer" 
  
  - from here, you can make POST reqs and such  
  


@Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
