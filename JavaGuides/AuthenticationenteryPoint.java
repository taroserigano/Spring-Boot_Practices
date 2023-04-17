Authentication Entry Point 

4


It is an interface implemented by ExceptionTranslationFilter, basically a filter which is the first point of entry for 
  Spring Security. It is the entry point to check if a user is authenticated and logs the person in or throws exception 
  (unauthorized). Usually the class can be used like that in simple applications but when using Spring security in REST, 
JWT etc one will have to extend it to provide better Spring Security filter chain management.

AuthenticationEntryPoint is used to send an HTTP response that requests credentials from a client.

Sometimes a client will proactively include credentials such as a username/password to request a resource. In these cases, Spring Security does not need to provide an HTTP response that requests credentials from the client since they are already included.

In other cases, a client will make an unauthenticated request to a resource that they are not authorized to access. In this case, an implementation of AuthenticationEntryPoint is used to request credentials from the client. The AuthenticationEntryPoint implementation might perform a redirect to a log in page, respond with an WWW-Authenticate header, etc.

AuthenticationEntryPoint is used in Spring Web Security to configure an application to perform certain actions whenever an unauthenticated client tries to access private resources.
  
  
  
