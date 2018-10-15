package mx.com.tp.smc.conf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import mx.com.tp.smc.entity.Role;
import mx.com.tp.smc.manager.RolManager;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.service.RolService;
import mx.com.tp.smc.service.TokenService;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private RolService rolService;

	@Autowired
	private TokenService tokenService;

//	Marco

	@Autowired
	private RolManager rolManager;

	private static String ROL_WS = "ROLE_TPE";

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "";
		String rolStr = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		System.out.println("=== VALOR DE GRATEDAUTHORITY ===" + authorities);
		List<String> roles = new ArrayList<String>();
		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(authentication.getAuthorities());

		try {

			for (GrantedAuthority a : authorities) {
				roles.add(a.getAuthority());
				rolStr = a.getAuthority();

				System.out.println("GrantedAuthority " + rolStr);

			}

			System.out.println("**************GrantedAuthority fuera del ciclo******* " + rolStr);

			RoleList responseManager = rolManager.returnAllRoles();
			Role rol = new Role();

			boolean rolValido = false;

			if ((Boolean) responseManager.getSuccess()) {

				for (mx.com.tp.smc.response.Role role : responseManager.getListRole()) {

					if (role.getRolRole() != null) {
						rol.setRolRole((String) role.getRolRole());
					}

					System.out.println("JSON " + rol.getRolRole());

					if (rol.getRolRole().equals(rolStr)) {

						System.out.println("ROL VALIDO " + rol.getRolRole());

						updatedAuthorities.add(new SimpleGrantedAuthority(rol.getRolRole()));

						rolValido = true;
						break;

					}

				}

				if (roles.contains(ROL_WS)) {
					url = "/operator";
					System.out.println("ROL_WS/operator " + url);

				} else if (rolValido) {
					url = "/admin";
					System.out.println("rolValido/admin " + rolValido);

				} else {
					url = "/accessdenied";
					System.out.println("accessdenied " + url);

				}

				Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
						authentication.getCredentials(), updatedAuthorities);

				SecurityContextHolder.getContext().setAuthentication(newAuth);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * if (roles.contains("ROLE_TPE") ) { url = "/operator"; } else if
		 * (roles.contains("ROLE_USER_MASTER") || roles.contains("ROLE_USER_LOCAL") ||
		 * roles.contains("ROLE_USER_ADMINISTRATOR") ||
		 * roles.contains("ROLE_USER_QUALITY") || roles.contains("ROLE_USER_OPERATION")
		 * || roles.contains("ROLE_USER_CLIENTE") || roles.contains("ROL_PRUEBAII")) {
		 * ("ROLE_USER_MASTER" ) ||("ROLE_USER_ADMINISTRATOR" ) ||("ROLE_USER_QUALITY" )
		 * ||("ROLE_USER_OPERATION" ) ||("ROLE_USER_CLIENTE" ) ||("ROLE_USER_LOCAL" )
		 * ||("ROLE_PRUEBA" ) || url = "/admin"; } else { url = "/accessdenied"; }
		 */

		return url;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
}