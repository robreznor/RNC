package cl.minsal.api.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (authorizedUser(userName, password))
		{
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				//grantedAuths.add(()-> {return "AUTH_USER";});
				grantedAuths = AuthorityUtils.createAuthorityList("AUTH_USER");
				Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
				System.out.println(auth.getAuthorities());
				return auth;
		}
		else
		{
				throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
		}
	}

	private boolean authorizedUser(String userName, String password)
	{
		System.out.println("username is :" + userName+" and password is "+password );
		if("Chandan".equals(userName) && "Chandan".equals(password))
				return true;
		return false;
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
			return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}

